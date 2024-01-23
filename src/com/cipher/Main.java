package com.cipher;

import com.cipher.model.Mode;
import com.cipher.service.BruteForceService;
import com.cipher.service.DecryptService;
import com.cipher.service.EncryptService;
import com.cipher.validation.InputValidation;

public class Main {
    private static final int COMMAND = 0;
    private static final int FILE_PATH = 1;
    private static final int KEY = 2;
    private static final int ANALYSIS_FILE_PATH = 2;

    public static void main(String[] args) {
        InputValidation.argsNumber(args.length);
        String command = args[COMMAND];
        String path = args[FILE_PATH];
        Mode mode = InputValidation.command(command);
        InputValidation.filePath(path);
        switch (mode) {
            case ENCRYPTION -> {
                int key = InputValidation.key(args[KEY]);
                EncryptService encryption = new EncryptService(path);
                encryption.encryptFile(key);
            }
            case DECRYPTION -> {
                int key = InputValidation.key(args[KEY]);
                DecryptService decryption = new DecryptService(path);
                decryption.decryptFile(key);
            }
            case BRUTE_FORCE -> {
                String analysisPath = args[ANALYSIS_FILE_PATH];
                InputValidation.filePath(analysisPath);
                BruteForceService bruteForcing = new BruteForceService(path, analysisPath);
                bruteForcing.bruteForceFile();
            }
        }
    }
}