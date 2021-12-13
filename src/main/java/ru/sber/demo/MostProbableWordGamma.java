package ru.sber.demo;

import ru.sber.encryption.GammaEncryption;

public class MostProbableWordGamma {
    public static void main(String[] args) {
        String encryptedText = "sfcvrjtz";
        String initialText = "SECURITY";
        String mostProbableWord = "RITY";
        int keyLength = 2;
        System.out.println(GammaEncryption.dragMostProbableWord(encryptedText, mostProbableWord, keyLength));
    }
}
