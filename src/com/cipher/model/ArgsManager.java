package com.cipher.model;

public class ArgsManager {
    private static final int MAX_ARGUMENTS = 3;
    private static final int COMMAND_POSITION = 0;
    private static final int FILE_PATH_POSITION = 1;
    private static final int KEY_POSITION = 2;
    private static final int ANALYSIS_FILE_PATH_POSITION = 2;
    private final String[] args;

    public ArgsManager(String[] args) {
        this.args = args;
    }

    public int getMaxArgs() {
        return MAX_ARGUMENTS;
    }

    public int getLength() {
        return args.length;
    }

    public int getKey() {
        return Integer.parseInt(args[KEY_POSITION]);
    }

    public String getAnalysisPath() {
        return args[ANALYSIS_FILE_PATH_POSITION];
    }

    public String getInitialPath() {
        return args[FILE_PATH_POSITION];
    }

    public String getCommand() {
        return args[COMMAND_POSITION];
    }

    public Mode getMode() {
        return Mode.valueOf(getCommand());
    }
}
