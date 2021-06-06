package com.example.demo.game;

import java.util.ArrayList;
import java.util.List;

public class GenerateAnagrams {
    private int numberOfAnagrams;
    List<String> anagrams = new ArrayList<>();
    public String word="";

    /**
     * this method generates a word from a list of letters
     * @param chosenLetters
     * @return
     */
    public String generateWord(List<String> chosenLetters){
        for(int i=0; i<chosenLetters.size(); i++){
            word = word+chosenLetters.get(i);
        }
        return word;
    }
}
