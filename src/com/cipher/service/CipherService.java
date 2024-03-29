package com.cipher.service;

import com.cipher.model.Language;
import com.cipher.model.Mode;

import java.util.Map;

public abstract class CipherService {
    private final FileService fileService;
    private final Language language;
    private final String originalText;

    CipherService(String originalFilePath) {
        fileService = new FileService(originalFilePath);
        originalText = fileService.readFile();
        language = Language.detectLanguage(originalText);
    }

    Language getLanguage() {
        return language;
    }

    String cipherText(int key, Mode mode) {
        key = key % language.getAlphabet().size();
        Map<Character, Character> cipherMap = MappingHelper.fillMap(key, language, mode);
        StringBuilder cipherText = new StringBuilder();
        for (char character : originalText.toCharArray()) {
            if (Character.isLetter(character)) {
                if (Character.isLowerCase(character)) {
                    char upperCase = Character.toUpperCase(character);
                    char encryptedLetter = Character.toLowerCase(cipherMap.get(upperCase));
                    cipherText.append(encryptedLetter);
                } else {
                    cipherText.append(cipherMap.get(character));
                }
            } else {
                cipherText.append(character);
            }
        }
        return cipherText.toString();
    }

    void cipherFile(int key, Mode mode, String status) {
        fileService.writeNewFile(cipherText(key, mode), status);
    }

    public abstract void cipher();
}
