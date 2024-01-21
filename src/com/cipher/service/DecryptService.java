package com.cipher.service;

import com.cipher.model.Mode;

public class DecryptService extends CipherService {
    public DecryptService(String path) {
        super(path);
    }

    public void decryptFile(int key) {
        this.cipherFile(key, Mode.DECRYPTION, "[DECRYPTED]");
    }
}