package com.cipher.service;

import com.cipher.model.Mode;

public class DecryptService extends CipherService{
    public DecryptService(String path) {
        super(path);
    }
    public void cipherFile(int key) {
        super.cipherFile(key, Mode.DECRYPTION, "[DECRYPTED]");
    }
}
