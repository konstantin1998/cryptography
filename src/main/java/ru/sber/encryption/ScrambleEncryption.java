package ru.sber.encryption;

import org.apache.commons.collections4.ListUtils;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static ru.sber.Utils.saveToFile;

public class ScrambleEncryption {
//    public static String encrypt(String initialText, Key key) {
//        if(key.getPermutation().size() != initialText.length()) {
//            throw new IllegalArgumentException("Permutation and initial text must have the same size");
//        }
//        StringBuilder result = new StringBuilder();
//        for (int i = 0; i < getGroupsNumber(key); i++) {
//            for (int j = 0; j < Math.min(key.getPermutation().size() - i * key.getGroupSize(), key.getGroupSize()); j++) {
//                int indexInPermutation = i * key.getGroupSize() + j;
//                int indexInInitialText = key.getPermutation().get(indexInPermutation) + i * key.getGroupSize();
//                char c = initialText.charAt(indexInInitialText);
//                result.append(c);
//            }
//        }
//        return result.toString();
//    }

//    private static int getGroupsNumber(Key key) {
//        if (key.getPermutation().size() % key.getGroupSize() == 0) {
//            return key.getPermutation().size() / key.getGroupSize();
//        }
//        return key.getPermutation().size() / key.getGroupSize() + 1;
//    }

    public static String encrypt(String initialText, Key key) {
        checkSize(initialText, key);
        StringBuilder result = new StringBuilder();

        List<List<Character>> textGroups = ListUtils.partition(strToList(initialText), key.getGroupSize());
        List<List<Integer>> permutationGroups = ListUtils.partition(key.getPermutation(), key.getGroupSize());

        for (int i = 0; i < textGroups.size(); i++) {
            String encryptedGroup = encryptSingleGroup(textGroups.get(i), permutationGroups.get(i));
            result.append(encryptedGroup);
        }

        return result.toString();
    }

    private static List<Character> strToList(String str) {
        char[] chars = str.toCharArray();
        List<Character> list = new ArrayList<>();
        for(char c: chars) {
            list.add(c);
        }
        return list;
    }

    private static String encryptSingleGroup(List<Character> symbols, List<Integer> permutation) {
        StringBuilder result = new StringBuilder();
        for (Integer p: permutation) {
            result.append(symbols.get(p));
        }
        return result.toString();
    }

    private static String decryptSingleGroup(List<Character> symbols, List<Integer> permutation) {
        List<Character> decryptedSequence = new ArrayList<>(symbols);

        for(int i = 0; i < permutation.size(); i++) {
            char c = symbols.get(i);
            int p = permutation.get(i);
            decryptedSequence.set(p, c);
        }
        StringBuilder result = new StringBuilder();
        for(Character c: decryptedSequence) {
            result.append(c);
        }
        return result.toString();
    }

    public static String decrypt(String encryptedText, Key key) {
        checkSize(encryptedText, key);

        StringBuilder result = new StringBuilder();

        List<List<Character>> textGroups = ListUtils.partition(strToList(encryptedText), key.getGroupSize());
        List<List<Integer>> permutationGroups = ListUtils.partition(key.getPermutation(), key.getGroupSize());

        for (int i = 0; i < textGroups.size(); i++) {
            String decryptedGroup = decryptSingleGroup(textGroups.get(i), permutationGroups.get(i));
            result.append(decryptedGroup);
        }

        return result.toString();
    }

    private static void checkSize(String initialText, Key key) {
        if (key.getPermutation().size() != initialText.length()) {
            throw new IllegalArgumentException("Permutation and initial text must have the same size");
        }
    }

    public static void encryptAndSaveToFile(String initialText, Key key, Path path) {
        saveToFile(encrypt(initialText, key), path);
    }

    public static void decryptAndSaveToFile(String initialText, Key key, Path path) {
        saveToFile(decrypt(initialText, key), path);
    }


}
