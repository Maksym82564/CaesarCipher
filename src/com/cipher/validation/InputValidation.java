package com.cipher.validation;

import com.cipher.exception.InvalidArgumentException;
import com.cipher.model.Mode;
import java.nio.file.Files;
import java.nio.file.Path;

public class InputValidation {
    private static final int MAX_ARGUMENTS = 3;
    public static void argsNumber(int size) {
        if (size != MAX_ARGUMENTS) {
            throw new InvalidArgumentException("Incorrect number of arguments");
        }
    }
    public static Mode command(String command) {
        for (Mode mode : Mode.values()) {
            if (mode.toString().equals(command)) {
                return Mode.valueOf(command);
            }
        }
        throw new InvalidArgumentException("Invalid command");
    }

    public static int key(String keyStr) {
        int key = Integer.parseInt(keyStr);
        if (key < 0) {
            throw new InvalidArgumentException("Invalid key");
        }
        return key;
    }

    public static void filePath(String path) {
        if (!Files.exists(Path.of(path)) || path.isBlank()) {
            throw new InvalidArgumentException("Invalid file path");
        }
    }
}
