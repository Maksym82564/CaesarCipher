package com.cipher.service;

import com.cipher.model.Language;
import com.cipher.model.Mode;

import java.util.Map;

public class CipherService {
    private final FileService fileService;
    private final Language language;
    private final String originalText;

    public Language getLanguage() {
        return language;
    }

    public CipherService(String originalFilePath) {
        this.fileService = new FileService(originalFilePath);
        this.originalText =  this.fileService.readFile();
        this.language = Language.detectLanguage(this.originalText);
    }
    protected String cipherText(int key, Mode mode) {
        Map<Character, Character> cipherMap = MappingHelper.fillMap(key, this.language, mode);
        StringBuilder cipherText = new StringBuilder();

        for (char character : this.originalText.toCharArray()) {
            if (Character.isLetter(character)) {
                if (Character.isLowerCase(character)) {
                    char upperCase = Character.toUpperCase(character);
                    char encryptedLetter = Character.toLowerCase(cipherMap.get(upperCase));
                    cipherText.append(encryptedLetter);
                }
                else {
                    cipherText.append(cipherMap.get(character));
                }
            }
            else {
                cipherText.append(character);
            }
        }
        return cipherText.toString();
    }
    protected void cipherFile(int key, Mode mode, String status) {
        this.fileService.writeNewFile(this.cipherText(key, mode), status);
    }
}
