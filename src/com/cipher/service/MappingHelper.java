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
            if (calcBuf < alphabet.size()) {  //ascending, key = 25 orig = 'a' encrypt = 'z'
                value = alphabet.get(calcBuf);
            } else {                          //descending, key = 25 orig = 'b' encrypt = 'a'
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
            if (calcBuf < 0) {     //ascending, key = 25 orig = 'a' decrypt = 'b'
                value = alphabet.get(calcBuf + alphabet.size());
            } else {               //descending, key = 25 orig = 'z' decrypt = 'a'
                value = alphabet.get(calcBuf);
            }
            decryptMap.put(alphabet.get(i), value);
        }
        return decryptMap;
    }
}
