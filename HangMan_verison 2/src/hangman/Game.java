/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangman;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author hjones2368
 */
public class Game {

    private int[] scores = new int[2];
    private String[] pNames;
    private ArrayList<String> words = new ArrayList<String>();

    public Game(String[] userNames) {
        pNames = userNames;
        
        getWords();

    }
    private void getWords(){
        while (inputFile.hasNext() && w < words.length) {
            words.add(inputFile.nextLine());
            w++;
            
            
            
            
        }
    }
    public int[] playGame() {
        

            //Start of game
            // Get random word
            //open file to String array
            scores = (0,0);
            index = rand.nextInt(words.size());
            String word = words.get(index);//To get 
            words.remove(index);
            
            
            
            char[] guessWord = new char[word.length()];
            for (int i = 0; i < word.length(); i++) {
                guessWord[i] = '-';
            }
            //System.out.println(word);
            System.out.println(guessWord);
            //Asking for there guess
            while (notRevealed) {
                if (correctGuess == false) {
                    playerController++;
                    if (playerController == 2) {
                        playerController = 0;
                    }
                }
                correctGuess = false;
                System.out.printf("%s, please guess:", pNames[playerController]);
                String guess = input.next();
                notRevealed = false;
                //check to see if guess is in the word
                for (int i = 0; i < word.length(); i++) {
                    if (word.charAt(i) == guess.charAt(0)) {
                        //replace dashes with correct letter
                        guessWord[i] = guess.charAt(0);
                        correctGuess = true;
                        scores[playerController]++;

                    }
                    if (guessWord[i] == '-') {
                        notRevealed = true;
                    }

                }
                //Ask players again
                System.out.println(guessWord);

            }
            

        return score;
    }

}
