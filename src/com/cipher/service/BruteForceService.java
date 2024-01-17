package com.cipher.service;

import com.cipher.model.Mode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BruteForceService extends CipherService {
    private final List<Double> letterFreqAnalysis;
    public BruteForceService(String filePath, String filePathForAnalysis) {
        super(filePath);
        FileService file = new FileService(filePathForAnalysis);
        String strForAnalysis = file.readFile();
        letterFreqAnalysis = freqMapToList(calcLetterFreq(strForAnalysis), getLanguage().getAlphabet());
    }

    public void bruteForceFile() {
        int key = this.bruteForceDecryption();
        String status;
        if (key == 0) {
            status = "[FAILED]";
        }
        else {
            status = "[KEY=" + key + "]";
        }
        this.cipherFile(key, Mode.DECRYPTION, status);
    }

    //Search for right key
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
        return 0;
    }

    //Parsing text into letter-frequency map
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

    //letterFreqMap to letterFreqList according to letter placement in alphabet
    private List<Double> freqMapToList(Map<Character, Double> letterFreqMap, List<Character> alphabet) {
        List<Double> freqList = new ArrayList<>();
        for (Character letter : alphabet) {
            if (letterFreqMap.containsKey(letter)) {
                double freq = letterFreqMap.get(letter);
                freqList.add(freq);
            } else {
                freqList.add(0.0);
            }
        }
        return freqList;
    }

    //Comparison of static analysis values and input text values
    private boolean compareFreq(List<Double> analyzed, List<Double> bruteForced) {
        int matchCounter = 0;
        for (int i = 0; i < analyzed.size(); i++) {
            if (Math.abs(analyzed.get(i) - bruteForced.get(i)) <= 0.015)
                matchCounter++;
        }
        return matchCounter >= 15;
    }
}