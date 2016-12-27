/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordgui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.Random;
import java.util.Scanner;
import static jdk.nashorn.internal.objects.NativeString.toLowerCase;

/**
 *
 * @author hjones2368
 */
public class Game extends userGUI{

    String[] gScores = new String[2];
    String[] pNames;
    String[] words = new String[100];

    int w = 0;

    public Game(String[] names) throws IOException {
        //sets players for game
        pNames = names;
        // creates word list
        getWords();
    }
    // creates data base of words to be used
    public String[] getWords() throws FileNotFoundException, IOException {
        String gamewords = "hangmanwords.txt";
        File file = new File(gamewords);
        Scanner inputFile = new Scanner(file);
        while (inputFile.hasNext() && w < words.length) {
            words[w] = toLowerCase(inputFile.nextLine());
            w++;
        }
        return words;
    }
        //Main game operation
    public int[] playGame() throws FileNotFoundException, IOException {
        System.out.printf("Hello %s and %s \n", pNames[0], pNames[1]);
        Scanner input = new Scanner(System.in);
        int[] score = new int[2];
        int playerController = 1;// player1 == -1 and player2 == +2
        boolean correctGuess = false;
        boolean notRevealed = true;
        
        Random rand = new Random();

        //Start of game
        // Get random word
        String word = words[rand.nextInt(w)];
        char[] guessWord = new char[word.length()];
        for (int i = 0; i < word.length(); i++) {
            guessWord[i] = '-';
        }
        
        System.out.println(guessWord);
        //setting up next user guess
        while (notRevealed) {
            if (correctGuess == false) {
                playerController++;
                if (playerController == 2) {
                    playerController = 0;
                }
            }
            correctGuess = false;
            // asking player for guess
            System.out.printf("%s, please guess:", pNames[playerController]);
            String guess = input.next();
            notRevealed = false;
            //check to see if guess is in the word
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == guess.charAt(0)) {
                    //replace dashes with correct letter
                    guessWord[i] = guess.charAt(0);
                    correctGuess = true;
                    score[playerController]++;

                }
                if (guessWord[i] == '-') {
                    notRevealed = true;
                }

            }
            //Ask players again
            System.out.println(guessWord);

        }
        System.out.printf("%s, %d\n%s: %d\n", pNames[0], score[0], pNames[1], score[1]);

        //play the wordGame
        //return the scores
        return score;
    }
}
