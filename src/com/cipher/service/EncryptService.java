package com.cipher.service;

import com.cipher.model.Mode;

public class EncryptService extends CipherService {
    public EncryptService(String path) {
        super(path);
    }
    public void cipherFile(int key) {
        super.cipherFile(key, Mode.ENCRYPTION, "[ENCRYPTED]");
    }
}
