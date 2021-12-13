package ru.sber;

import java.util.List;

public class Key {
    private final int groupSize;
    private final List<Integer> permutation;

    public Key(int groupSize, List<Integer> permutation) {
        this.groupSize = groupSize;
        this.permutation = permutation;
    }

    public int getGroupSize() {
        return groupSize;
    }

    public List<Integer> getPermutation() {
        return permutation;
    }
}
