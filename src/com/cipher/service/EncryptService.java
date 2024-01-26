package com.cipher.service;

import com.cipher.model.Mode;

public class EncryptService extends CipherService {
    private final static Mode ENCRYPT_MODE = Mode.ENCRYPTION;
    private final static String STATUS = "[ENCRYPTED]";
    private final int key;

    public EncryptService(String path, int key) {
        super(path);
        this.key = key;
    }

    @Override
    public void cipher() {
        cipherFile(key, ENCRYPT_MODE, STATUS);
    }
}