package com.cipher.service;

import com.cipher.model.Mode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BruteForceService extends CipherService {
    private static final int DECRYPT_FAILED = 0;
    private static final int MATCH_THRESHOLD = 15;
    private static final double ALLOWED_FREQ_DIFF = 0.015;
    private static final double NO_SUCH_LETTER = 0.0;
    private final List<Double> letterFreqAnalysis;

    public BruteForceService(String filePath, String filePathForAnalysis) {
        super(filePath);
        FileService file = new FileService(filePathForAnalysis);
        String strForAnalysis = file.readFile();
        letterFreqAnalysis = freqMapToList(calcLetterFreq(strForAnalysis), getLanguage().getAlphabet());
    }

    public void bruteForceFile() {
        int key = bruteForceDecryption();
        String status;
        if (key == DECRYPT_FAILED) {
            status = "[FAILED]";
        } else {
            status = "[KEY=" + key + "]";
        }
        cipherFile(key, Mode.DECRYPTION, status);
    }

    private int bruteForceDecryption() {
        for (int i = 1; i < getLanguage().getAlphabet().size(); i++) {
            String decryptedText = cipherText(i, Mode.DECRYPTION);
            Map<Character, Double> bruteForcedFreqMap = calcLetterFreq(decryptedText);
            List<Character> alphabet = getLanguage().getAlphabet();
            List<Double> bruteForcedList = freqMapToList(bruteForcedFreqMap, alphabet);
            if (compareFreq(letterFreqAnalysis, bruteForcedList)) {
                return i;
            }
        }
        return DECRYPT_FAILED;
    }

    private Map<Character, Double> calcLetterFreq(String text) {
        Map<Character, Integer> letterCount = new HashMap<>();
        for (char ch : text.toCharArray()) {
            if (Character.isLetter(ch)) {
                char upperCaseLetter = Character.toUpperCase(ch);
                int letterCounter = letterCount.getOrDefault(upperCaseLetter, 0) + 1;
                letterCount.put(upperCaseLetter, letterCounter);
            }
        }
        int totalLetters = 0;
        for (int count : letterCount.values()) {
            totalLetters += count;
        }
        Map<Character, Double> letterFrequencyMap = new HashMap<>();
        for (Map.Entry<Character, Integer> entry : letterCount.entrySet()) {
            char letter = entry.getKey();
            int count = entry.getValue();
            double frequency = count * 1.0 / totalLetters;
            letterFrequencyMap.put(letter, frequency);
        }
        return letterFrequencyMap;
    }

    private List<Double> freqMapToList(Map<Character, Double> letterFreqMap, List<Character> alphabet) {
        List<Double> freqList = new ArrayList<>();
        for (Character letter : alphabet) {
            if (letterFreqMap.containsKey(letter)) {
                double freq = letterFreqMap.get(letter);
                freqList.add(freq);
            } else {
                freqList.add(NO_SUCH_LETTER);
            }
        }
        return freqList;
    }

    private boolean compareFreq(List<Double> analyzed, List<Double> bruteForced) {
        int matchCounter = 0;
        for (int i = 0; i < analyzed.size(); i++) {
            double diff = Math.abs(analyzed.get(i) - bruteForced.get(i));
            if (diff <= ALLOWED_FREQ_DIFF) {
                matchCounter++;
            }
        }
        return matchCounter >= MATCH_THRESHOLD;
    }
}