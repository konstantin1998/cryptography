package ru.sber;

import java.nio.file.Path;

import static ru.sber.Utils.saveToFile;

public class GammaEncryption {

    private enum Mode {
        DECRYPTION,
        ENCRYPTION
    }

    public static String encrypt(String initialText, String key) {
        return transformText(initialText, key, Mode.ENCRYPTION);
    }

    public static String decrypt(String initialText, String key) {
        return transformText(initialText, key, Mode.DECRYPTION);
    }

    private static String transformText(String initialText, String key, Mode mode) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < initialText.length(); i++) {
            char c = key.charAt(i % key.length());
            if (Mode.DECRYPTION.equals(mode)) {
                result.append((char) (initialText.charAt(i) - c));
            } else {
                result.append((char) (initialText.charAt(i) + c));
            }

        }
        return result.toString();
    }

    public void encryptAndSaveToFile(String initialText, String key, Path path) {
        saveToFile(encrypt(initialText, key), path);
    }

    public void decryptAndSaveToFile(String initialText, String key, Path path) {
        saveToFile(decrypt(initialText, key), path);
    }
}
