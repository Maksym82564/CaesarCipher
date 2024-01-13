package com.cipher.model;

import java.util.ArrayList;
import java.util.List;

public enum Language {
    ENGLISH("ABCDEFGHIJKLMNOPQRSTUVWXYZ"),
    UKRAINIAN("АБВГҐДЕЄЖЗИІЇЙКЛМНОПРСТУФХЦЧШЩЬЮЯ");

    private final List<Character> alphabet;

    Language(String alphabet) {
        this.alphabet = new ArrayList<>();
        for (char character : alphabet.toCharArray()) {
            this.alphabet.add(character);
        }
    }

    public List<Character> getAlphabet() {
        return this.alphabet;
    }

    public static Language detectLanguage(String text) {
        for (Language language : Language.values()) {
            List<Character> alphabet = language.getAlphabet();

            int charCounter = 0;
            for (char character : text.toUpperCase().toCharArray()) {
                if (Character.isLetter(character) && alphabet.contains(character)) {
                    charCounter++;
                }
                if (charCounter == 3) return language;
            }
        }
        throw new IllegalStateException("Unknown language");
    }
}