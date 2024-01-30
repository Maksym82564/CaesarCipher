package com.cipher.factory;

import com.cipher.model.ArgsManager;
import com.cipher.model.Mode;
import com.cipher.service.BruteForceService;
import com.cipher.service.CipherService;
import com.cipher.service.DecryptService;
import com.cipher.service.EncryptService;

public class CipherFactory {
    public static CipherService getCipherInstance(ArgsManager args) {
        Mode mode = args.getMode();
        String initialPath = args.getInitialPath();
        switch (mode) {
            case ENCRYPTION -> {
                int key = args.getKey();
                return new EncryptService(initialPath, key);
            }
            case DECRYPTION -> {
                int key = args.getKey();
                return new DecryptService(initialPath, key);
            }
            case BRUTE_FORCE -> {
                String analysisPath = args.getAnalysisPath();
                return new BruteForceService(initialPath, analysisPath);
            }
            default -> {
                return null;
            }
        }
    }
}