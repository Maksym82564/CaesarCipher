package com.cipher.model;

import java.util.Arrays;
import java.util.List;

public enum Language {
    ENGLISH(Arrays.asList('A','B','C','D','E','F','G','H','I','J','K','L','M',
                          'N','O','P', 'Q','R','S','T','U','V','W','X','Y','Z')),
    UKRAINIAN(Arrays.asList('А','Б','В','Г','Ґ','Д','Е','Є','Ж','З','И','І','Ї','Й','К','Л','М',
                            'Н','О','П','Р','С','Т','У','Ф','Х','Ц','Ч','Ш','Щ','Ь','Ю','Я'));
    private final List<Character> alphabet;

    Language(List<Character> alphabet) {
        this.alphabet = alphabet;
    }

    public List<Character> getAlphabet() {
        return this.alphabet;
    }

    public static Language detectLanguage(String text) {
        for (Language language : Language.values()) {
            List<Character> alphabet = language.getAlphabet();

            boolean detectedLang = false;
            for (char ch : text.toUpperCase().toCharArray()) {
                if (Character.isLetter(ch)) {
                    if (alphabet.contains(ch)) {
                        detectedLang = true;
                    }
                    else { //if language didn't match
                        detectedLang = false;
                        break;
                    }
                }
            }
            if (detectedLang) return language;
        }
        throw new IllegalStateException("Unknown language");
    }
}