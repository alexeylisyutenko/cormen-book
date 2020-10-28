package ru.alexeylisyutenko.other;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public final class UniqueWordPermutations {

    public static int findUniqueWordPermutationsCount(String originalWord) {
        Objects.requireNonNull(originalWord, "originalWord cannot be null");

        Set<String> wordSet = new HashSet<>();
        generateCharPermutations(originalWord, 0, originalWord.length() - 1, wordSet);
        return wordSet.size();
    }

    private static void generateCharPermutations(String source, int l, int r, Set<String> wordResultSet) {
        if (l == r) {
            wordResultSet.add(source);
        } else {
            for (int i = l; i <= r; i++) {
                source = swap(source, l, i);
                generateCharPermutations(source, l + 1, r, wordResultSet);
                source = swap(source, l, i);
            }
        }
    }

    public static String swap(String source, int i, int j) {
        char temp;
        char[] charArray = source.toCharArray();
        temp = charArray[i];
        charArray[i] = charArray[j];
        charArray[j] = temp;
        return String.valueOf(charArray);
    }

    public static void main(String[] args) {
        System.out.println("шнурок: " + findUniqueWordPermutationsCount("шнурок"));
        System.out.println("курок: " + findUniqueWordPermutationsCount("курок"));
        System.out.println("колобок: " + findUniqueWordPermutationsCount("колобок"));
        System.out.println("ааааабббббб: " + findUniqueWordPermutationsCount("ааааабббббб"));
    }
}
