package com.example.demo.game;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class RandomLetters {
    public int numberLetters;
    public List<String> chosenLetters = new ArrayList<>();
    public static char[] alphabet = "abcdefghijklmnopqrstuvwxyzșăîțâ".toCharArray();
    public static char[] vocale = "aeiouăîâ".toCharArray();
    public static char[] consoane = "bcdfghjklmnprstvwxzșț".toCharArray();
    public final int minLetters=5;
    public final int maxLetters=12;

    /**
     * constructor
     * the list of letters will contains consonants and vowels
     */
    public RandomLetters() {
        numberLetters = (int) Math.floor(Math.random() * (maxLetters - minLetters + 1) + minLetters);
        Random rand1 = new Random();
        for (int i = 0; i <= numberLetters / 2 + 1; i++) {
            int ok = 1;
            while (ok == 1) {
                int check = 1;
                int int_random = rand1.nextInt(vocale.length - 1);
                String toBeAdded = String.valueOf(vocale[int_random]);
                if (chosenLetters.stream().anyMatch(s -> s.equals(toBeAdded))) {
                    check = 0;
                }
                if (check == 1) {
                    chosenLetters.add(toBeAdded);
                    ok = 0;
                }
            }
        }
        for (int i = numberLetters / 2 + 2; i <= numberLetters+1; i++) {
            int ok = 1;
            while (ok == 1) {
                int check = 1;
                int int_random = rand1.nextInt(consoane.length - 1);
                String toBeAdded = String.valueOf(consoane[int_random]);
                if (chosenLetters.stream().anyMatch(s -> s.equals(toBeAdded))) {
                    check = 0;
                }
                if (check == 1) {
                    chosenLetters.add(toBeAdded);
                    ok = 0;
                }
            }
        }
    }

    public List<String> getChosenLetters() {
        return chosenLetters;
    }

    public int getNumberLetters() {
        return numberLetters;
    }
}
