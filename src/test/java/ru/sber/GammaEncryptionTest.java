package ru.sber;

import org.junit.Test;

import static org.junit.Assert.*;

public class GammaEncryptionTest {
    @Test
    public void encrypt() {
        String initialText = "ABCDEF";
        String key = "   ";
        String expectedEncryptedText = "abcdef";
        assertEquals(expectedEncryptedText, GammaEncryption.encrypt(initialText, key));
    }

    @Test
    public void decrypt() {
        String encryptedText = String.valueOf((char) 1000) + (char) 65000 + (char) 100;
        String key = String.valueOf((char) 1500) + (char) 64000;
        String expectedDecryptedText = String.valueOf((char) 65036) + (char)1000 + (char)64136;
        assertEquals(expectedDecryptedText, GammaEncryption.decrypt(encryptedText, key));
    }
}