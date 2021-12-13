package ru.sber.demo;

import ru.sber.encryption.ShiftEncryption;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static ru.sber.Utils.readLine;

public class ShiftEncApp {
    public static void main(String[] args) throws IOException {
        String line = readLine("src\\main\\resources\\shiftEncSource");

        String delimiter = "  ";
        String initialText = line.split(delimiter)[0];
        String s = line.split(delimiter)[1];
        int shift = Integer.parseInt(s);

        Path path = Paths.get("src\\main\\resources\\dest");
        ShiftEncryption.encryptAndSaveToFile(initialText, shift, path);
    }
}
