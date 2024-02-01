package com.cipher.validation;

import com.cipher.exception.InvalidArgumentException;
import com.cipher.model.Mode;
import java.nio.file.Files;
import java.nio.file.Path;

public class InputValidation {
    public static void argsNumber(int size, int supposedArgsNumber) {
        if (size != supposedArgsNumber) {
            throw new InvalidArgumentException("Incorrect number of arguments");
        }
    }

    public static void command(String command) {
        for (Mode mode : Mode.values()) {
            if (mode.toString().equals(command)) {
                return;
            }
        }
        throw new InvalidArgumentException("Invalid command");
    }

    public static void key(int key) {
        if (key < 0) {
            throw new InvalidArgumentException("Invalid key");
        }
    }

    public static void filePath(String path) {
        if (!Files.exists(Path.of(path)) || path.isBlank()) {
            throw new InvalidArgumentException("Invalid file path");
        }
    }
}
