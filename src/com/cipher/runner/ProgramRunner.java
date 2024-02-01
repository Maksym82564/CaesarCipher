package com.cipher.runner;

import com.cipher.factory.CipherFactory;
import com.cipher.model.ArgsManager;
import com.cipher.validation.ArgsValidation;

public class ProgramRunner {
    public void run(String[] args) {
        ArgsManager argsManager = new ArgsManager(args);
        new ArgsValidation(argsManager).validateArgs();
        CipherFactory.getCipherInstance(argsManager).cipher();
    }
}