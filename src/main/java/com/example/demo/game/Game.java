package com.example.demo.game;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Game {

    /**
     * this method verify if the word chosen by player is a romanian word or not
     * the file Listacuvinte.txt contains 169333 common romanian words
     * @param filePath
     * @param searchQuery
     * @return
     * @throws IOException
     */
    public static int searchUsingBufferedReader(String filePath, String searchQuery) throws IOException {
        searchQuery = searchQuery.trim();
        BufferedReader br = null;

        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-16"));
            String line;
            while ((line = br.readLine()) != null) {
                if (line.equals(searchQuery)) {
                    return 1;
                } else {
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
            } catch (Exception e) {
                System.err.println("Exception while closing bufferedreader " + e.toString());
            }
        }

        return -1;
    }

    /**
     * this method verify if the chosen word is correct or not
     * the word is correct if and only if the word contains words from the list of letters and the word is a romanian word
     * @param chosenLetters
     * @param word
     * @return
     * @throws IOException
     */
    public String Game(String chosenLetters, String word) throws IOException{
        for (int i = 0; i < word.length(); i++) {
            if (chosenLetters.indexOf(word.charAt(i)) == -1) {
                return "Litera " + word.charAt(i) + " nu se afla in multimea ceruta";
            }
        }
        try {
            if (searchUsingBufferedReader("C:\\Users\\Alexandra\\IdeaProjects\\demo\\Listacuvinte.txt", word) == -1) {
                return "Cuvantul nu exista in limba romana";
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return "Felicitari!";
    }
}
