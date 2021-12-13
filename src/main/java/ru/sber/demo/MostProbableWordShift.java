package ru.sber.demo;

import ru.sber.encryption.ShiftEncryption;

public class MostProbableWordShift {
    public static void main(String[] args) {
        String encryptedText = "abc dzf";
        String mostProbableWord = "cye";

        System.out.println(ShiftEncryption.dragMostProbableWord(encryptedText, mostProbableWord));
    }
}
