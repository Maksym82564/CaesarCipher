package com.cipher.service;

import com.cipher.model.Language;
import com.cipher.model.Mode;

import java.util.HashMap;

public class MappingHelper {
    public static HashMap<Character, Character> fillMap(int key, Language language, Mode mode) {
        return switch (mode) {
            case ENCRYPTION -> fillEncryptMap(key, language);
            case DECRYPTION -> fillDecryptMap(key, language);
        };
    }

    private static HashMap<Character, Character> fillEncryptMap(int key, Language language) {
        HashMap<Character, Character> encryptMap = new HashMap<>();
        var alphabet = language.getAlphabet();
        for (int i = 0; i < alphabet.size(); i++) {
            char value;
            int calcBuf = i + key;
            if (calcBuf < alphabet.size()) {
                value = alphabet.get(calcBuf);
            } else {
                value = alphabet.get(calcBuf - alphabet.size());
            }
            encryptMap.put(alphabet.get(i), value);
        }
        return encryptMap;
    }

    private static HashMap<Character, Character> fillDecryptMap(int key, Language language) {
        HashMap<Character, Character> decryptMap = new HashMap<>();
        var alphabet = language.getAlphabet();
        for (int i = 0; i < alphabet.size(); i++) {
            char value;
            int calcBuf = i - key;
            if (calcBuf < 0) {
                value = alphabet.get(calcBuf + alphabet.size());
            } else {
                value = alphabet.get(calcBuf);
            }
            decryptMap.put(alphabet.get(i), value);
        }
        return decryptMap;
    }
}
