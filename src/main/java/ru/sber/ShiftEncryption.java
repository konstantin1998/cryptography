package ru.sber;

import java.nio.file.Path;
import static ru.sber.Utils.saveToFile;

public class ShiftEncryption {
    public static String encrypt(String initialText, int shift) {
        return shiftText(initialText, shift);
    }

    public static String decrypt(String encryptedText, int shift) {
        return shiftText(encryptedText, -shift);
    }

    private static String shiftText(String encryptedText, int shift) {
        String normalizedText = encryptedText.toLowerCase();
        StringBuilder result = new StringBuilder();
        for (char c: normalizedText.toCharArray()) {
            if(isLetter(c)) {
                int offset = ((int)c - (int)'a' + shift) % ((int)'z' - (int)'a' + 1);
                if (offset < 0) {
                    offset = offset + ((int)'z' - (int)'a' + 1);
                }
                int asciiCode = offset + (int)'a';
                result.append((char)asciiCode);
            } else {
                result.append(c);
            }

        }
        return result.toString();
    }

    private static boolean isLetter(char c) {
        return (int)c <= (int)'z' && (int)c >= (int)'a';
    }

    public static void encryptAndSaveToFile(String initialText, int shift, Path path) {
        saveToFile(encrypt(initialText, shift), path);
    }


    public static void decryptAndSaveToFile(String encryptedText, int shift, Path path) {
        saveToFile(decrypt(encryptedText, shift), path);
    }

    public static String dragMostProbableWord(String encryptedText, String word) {
        for (int i = 0; i < encryptedText.length() - word.length() + 1; i++) {
            int shift = encryptedText.charAt(i) - word.charAt(0);
            boolean isRightShift = true;
            for (int j = 0; j < word.length(); j++) {
                isRightShift = isRightShift && (encryptedText.charAt(i + j) - word.charAt(j) == shift);
            }
            if (isRightShift) {
                return decrypt(encryptedText, shift);
            }
        }
        throw new RuntimeException("Couldn't decrypt text");
    }
}
