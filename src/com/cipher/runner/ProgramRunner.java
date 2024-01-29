package com.cipher.runner;

import com.cipher.model.Mode;
import com.cipher.service.BruteForceService;
import com.cipher.service.CipherService;
import com.cipher.service.DecryptService;
import com.cipher.service.EncryptService;
import com.cipher.validation.InputValidation;

public class ProgramRunner {
    private static final int COMMAND_POSITION = 0;
    private static final int FILE_PATH_POSITION = 1;
    private static final int KEY_POSITION = 2;
    private static final int ANALYSIS_FILE_PATH_POSITION = 2;

    private final String[] args;

    public ProgramRunner(String[] args) {
        this.args = args;
    }

    private CipherService getCipherInstance(Mode mode, String path) {
        switch (mode) {
            case ENCRYPTION -> {
                int key = InputValidation.key(args[KEY_POSITION]);
                return new EncryptService(path, key);
            }
            case DECRYPTION -> {
                int key = InputValidation.key(args[KEY_POSITION]);
                return new DecryptService(path, key);
            }
            case BRUTE_FORCE -> {
                String analysisPath = args[ANALYSIS_FILE_PATH_POSITION];
                InputValidation.filePath(analysisPath);
                return new BruteForceService(path, analysisPath);
            }
            default -> {
                return null;
            }
        }
    }

    public void run() {
        InputValidation.argsNumber(args.length);
        String command = args[COMMAND_POSITION];
        String path = args[FILE_PATH_POSITION];
        Mode mode = InputValidation.command(command);
        InputValidation.filePath(path);
        CipherService service = getCipherInstance(mode, path);
        service.cipher();
    }
}
