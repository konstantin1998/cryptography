package ru.sber.encryption;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static ru.sber.Utils.saveToFile;

public class GammaEncryption {

    private enum Mode {
        DECRYPTION,
        ENCRYPTION
    }

    public static String encrypt(String initialText, String key) {
        return transformText(initialText, key, Mode.ENCRYPTION);
    }

    public static String decrypt(String encryptedText, String key) {
        return transformText(encryptedText, key, Mode.DECRYPTION);
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

    public static void encryptAndSaveToFile(String initialText, String key, Path path) {
        saveToFile(encrypt(initialText, key), path);
    }

    public static void decryptAndSaveToFile(String initialText, String key, Path path) {
        saveToFile(decrypt(initialText, key), path);
    }

    public static String dragMostProbableWord(String encryptedText, String word, int keyLength) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < encryptedText.length() - word.length() + 1; i++) {
            String substr = encryptedText.substring(i, i + word.length());
            String keySource = decrypt(substr, word);
            String key;
            try {
                key = findKey(substr, word, keySource, keyLength);
                return decrypt(encryptedText, key);
            } catch (RuntimeException ignored) { }

        }
        return null;
    }

    private static String findKey(String encryptedWord, String actualWord, String keySource, int keyLength) {
        for (int i = 0; i < keySource.length() - keyLength + 1; i++) {
            String potentialKey = keySource.substring(i, i + keyLength);
            if (decrypt(encryptedWord, potentialKey).equals(actualWord)) {
                return potentialKey;
            }
        }
        throw new RuntimeException("Couldn't find key");
    }
}
