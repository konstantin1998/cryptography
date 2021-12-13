package ru.sber.demo;

import ru.sber.encryption.GammaEncryption;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static ru.sber.Utils.readLine;

public class GammaEncApp {
    public static void main(String[] args) throws IOException {
        String line = readLine("src\\main\\resources\\gammaEncSource");

        String delimiter = "  ";
        String initialText = line.split(delimiter)[0];
        String key = line.split(delimiter)[1];

        Path path = Paths.get("src\\main\\resources\\dest");
        GammaEncryption.encryptAndSaveToFile(initialText, key, path);
    }
}
