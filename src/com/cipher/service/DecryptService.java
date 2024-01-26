package com.cipher.service;

import com.cipher.model.Mode;

public class DecryptService extends CipherService {
    private final static Mode MODE = Mode.DECRYPTION;
    private final static String STATUS = "[DECRYPTED]";
    private final int key;

    public DecryptService(String path, int key) {
        super(path);
        this.key = key;
    }

    @Override
    public void cipher() {
        cipherFile(key, MODE, STATUS);
    }
}