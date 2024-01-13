package com.cipher.service;

import java.io.*;

public class FileService {
    private final String path;
    //private final String originalText;
    public FileService(String path) {
        this.path = path;
        //this.originalText = readFile();
    }
    public String readFile() {
        StringBuilder stringBuilder = new StringBuilder();
        try (FileReader fileReader = new FileReader(this.path);
             BufferedReader reader = new BufferedReader(fileReader)
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
    public void writeNewFile(String text, String status) {
        int buf = this.path.lastIndexOf('.');
        String newPath = text.substring(0, buf) + status + text.substring(buf);
        try (FileWriter fileWriter = new FileWriter(newPath);
             BufferedWriter writer = new BufferedWriter(fileWriter)
        ) {
            writer.write(text);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}