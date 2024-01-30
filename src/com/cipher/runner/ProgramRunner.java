package com.cipher.runner;

import com.cipher.factory.CipherFactory;
import com.cipher.model.ArgsManager;
import com.cipher.validation.ArgsValidation;

public class ProgramRunner {
    public void run(String[] args) {
        ArgsManager argsManager = new ArgsManager(args);
        ArgsValidation ArgsValidation = new ArgsValidation(argsManager);
        ArgsValidation.validateArgs();
        CipherFactory.getCipherInstance(argsManager).cipher();
    }
}
