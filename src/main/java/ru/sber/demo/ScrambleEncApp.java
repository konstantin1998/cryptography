package ru.sber.demo;

import ru.sber.encryption.Key;
import ru.sber.encryption.ScrambleEncryption;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static ru.sber.Utils.readLine;

public class ScrambleEncApp {
    public static void main(String[] args) throws IOException {
        List<Integer> permutation = Arrays.asList(2, 5, 3, 4, 0, 1, 0, 4, 3, 2, 1);
        int groupSize = 6;
        Key key = new Key(groupSize, permutation);

        String initialText = readLine("src\\main\\resources\\scrambleEncSource");

        Path path = Paths.get("src\\main\\resources\\dest");
        ScrambleEncryption.encryptAndSaveToFile(initialText, key, path);
    }
}
