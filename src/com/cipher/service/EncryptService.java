package com.cipher.service;

import com.cipher.model.Mode;

public class EncryptService extends CipherService {
    public EncryptService(String path) {
        super(path);
    }
    public void encryptFile(int key) {
        this.cipherFile(key, Mode.ENCRYPTION, "[ENCRYPTED]");
    }
}