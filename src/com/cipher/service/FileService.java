package com.cipher.service;

import java.io.*;

public class FileService {
    private final String path;

    public FileService(String path) {
        this.path = path;
    }

    public String readFile() {
        StringBuilder stringBuilder = new StringBuilder();
        try (FileReader fileReader = new FileReader(this.path);
             BufferedReader reader = new BufferedReader(fileReader)
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append("\n");
            }
        } catch (IOException e) {
            System.err.println("Invalid file path");
        }
        return stringBuilder.toString();
    }

    public void writeNewFile(String text, String status) {
        int buf = this.path.lastIndexOf('.');
        String newPath = this.path.substring(0, buf) + status + this.path.substring(buf);
        try (FileWriter fileWriter = new FileWriter(newPath);
             BufferedWriter writer = new BufferedWriter(fileWriter)
        ) {
            writer.write(text);
        } catch (IOException e) {
            System.err.println("Couldn't write new file");
        }
    }
}