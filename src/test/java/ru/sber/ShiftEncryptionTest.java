package ru.sber;

import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class ShiftEncryptionTest {
    @Test
    public void shouldEncryptCorrectly() {
        String initialText = "initial text";
        int shift = 3;
        String expectedEncryptedText = "lqlwldo whaw";
        String actualEncryptedText = ShiftEncryption.encrypt(initialText, shift);
        assertEquals(expectedEncryptedText, actualEncryptedText);
    }

    @Test
    public void shouldDecryptCorrectly() {
        String encryptedText = "lqlwldo whaw";
        int shift = 3;
        String initialText = "initial text";
        String actualEncryptedText = ShiftEncryption.decrypt(encryptedText, shift);
        assertEquals(initialText, actualEncryptedText);
    }

    @Test
    public void shouldNotThrowExceptions() {
        String text = "text";
        int shift = 0;
        Path path = Paths.get("file.txt");
        ShiftEncryption.encryptAndSaveToFile(text, shift, path);
        ShiftEncryption.decryptAndSaveToFile(text, shift, path);
    }

    @Test
    public void shouldDecryptWithMostProbableWord() {
        String encryptedText = "abc dzf";
        String mostProbableWord = "DZF";
        String initialText = "ABC DZF";
        assertEquals(initialText, ShiftEncryption.dragMostProbableWord(encryptedText, mostProbableWord));
    }
}