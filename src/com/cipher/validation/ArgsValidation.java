package com.cipher.validation;

import com.cipher.model.ArgsManager;

public class ArgsValidation {
    private final ArgsManager args;

    public ArgsValidation(ArgsManager args) {
        this.args = args;
    }

    public void validateArgs() {
        InputValidation.argsNumber(args.getLength(), args.getMaxArgs());
        String command = args.getCommand();
        String path = args.getInitialPath();
        InputValidation.command(command);
        InputValidation.filePath(path);
        modeDependantValidation();
    }

    private void modeDependantValidation() {
        switch (args.getMode()) {
            case ENCRYPTION, DECRYPTION -> InputValidation.key(args.getKey());
            case BRUTE_FORCE -> {
                String analysisPath = args.getAnalysisPath();
                InputValidation.filePath(analysisPath);
            }
        }
    }
}