package ru.sber;

import org.junit.Test;
import ru.sber.encryption.Key;
import ru.sber.encryption.ScrambleEncryption;

import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;

public class ScrambleEncryptionTest {

    private final List<Integer> permutation = Arrays.asList(2, 5, 3, 4, 0, 1, 0, 4, 3, 2, 1);
    private final int groupSize = 6;
    private final Key key = new Key(groupSize, permutation);

    @Test
    public void shouldEncryptCorrectly() {
        String initialText = "initialtext";

        String expectedEncryptedText = "iatiinltxet";
        String actualEncryptedText = ScrambleEncryption.encrypt(initialText, key);
        assertEquals(expectedEncryptedText, actualEncryptedText);
    }

    @Test
    public void shouldDecryptCorrectly() {
        String encryptedText = "iatiinltxet";
        String expectedInitialText = "initialtext";
        assertEquals(expectedInitialText, ScrambleEncryption.decrypt(encryptedText, key));
    }
}