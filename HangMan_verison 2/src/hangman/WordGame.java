/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangman;

import java.io.FileWriter;
import java.io.*;
import java.io.PrintWriter;
import static java.lang.Math.abs;
import java.util.Scanner;
import java.util.Random;
import javafx.scene.shape.Line;
import static java.lang.Math.abs;

/**
 *
 * @author Henry Jones
 */
public class WordGame {

    /**
     * @param args the command line arguments
     */
    public void main(String[] args) throws IOException {
        //main menu
        Scanner keyboard = new Scanner(System.in);
        int menu;

        do { //menu for start of application
            System.out.println("Chose one of the options \n 1. Start Game "
                    + "\n 2. Show Scores \n 3. Add Word \n 4. Exit");

            //gamer unput
            menu = keyboard.nextInt();
            // Menu options 
            if (menu == 1) {

                gameController();

            } else if (menu == 2) {
                showScore();
            } else if (menu == 3) {
                addWord();
            }
        } while (menu != 4);
        // TODO code application logic here
    }

    private void gameController() throws IOException {
        Scanner keyboard = new Scanner(System.in);
        Scanner input = new Scanner(System.in);
        String filename = "gamedata.txt"; //File Name
        String gamewords = "hangmanwords.txt";
        String nextRound;
        String[] pNames = new String[2];
        String[] words = new String[100];
        //file writing
        
        File file = new File(gamewords);
        Scanner inputFile = new Scanner(file);
        FileWriter fw = new FileWriter(filename, true);
        PrintWriter outputFile = new PrintWriter(fw);
        int[] scores = new int[2];
        int w = 0;
        int playerController = 1;// player1 == -1 and player2 == +2
        boolean correctGuess = false;
        boolean notRevealed = true;
        Data myDB = new Data();
        Random rand = new Random();

        // Entering players names add names to array
        pNames[0] = myDB.userController();
        pNames[1] = myDB.userController();

        Game ourGame = new Game(pNames);

        scores = ourGame.playGame();

        System.out.printf("%s, %d\n%s: %d\n", pNames[0], scores[0], pNames[1], scores[1]);
        //ask them to see if they want another round
        //if yes goto a new word
        //if no save the winner in scores tables
        System.out.println("Do you want play another round?\t");
        nextRound = new String(input.next());
        notRevealed = true;
    }

    while (nextRound.charAt (0) == 'y');
    
    myDB.setScore (pName(winner),scores(winner));
        
        
    }
    
    
    
    
    
    private static void scoreView() throws IOException {
        // filename 
        Data myDB = new Data();

        scores = myDB.getScores();
        

    }

    private static void addWord() throws IOException {
        int choice = 0;
        String answer;
        String word = new String();
        
        // filename 
        String gamewords = "hangmanwords.txt"; //File Name
        Scanner keyboard = new Scanner(System.in);
        Scanner checkWord = new Scanner(System.in);
        //Open the file
        do {
            System.out.println("Do you want to \n 1. add a word  "
                    + "\n 2. see avaiable words "
                    + "\n 3. Return to Main Menu");
            choice = keyboard.nextInt();
            word = (keyboard.nextLine());
            
            if (choice == 1) {
                
                do {
                    FileWriter fw = new FileWriter(gamewords, true);
                    PrintWriter outputFile = new PrintWriter(fw);
                    System.out.println("Please enter a word.");
                    word = (keyboard.nextLine());
                    while (word.length() < 5) {
                        System.out.println("Please enter longer word.");
                        word = keyboard.nextLine();
                    }
                    outputFile.println(word);
                    outputFile.close();
                    System.out.println("Do you want to add another word.");
                    answer = new String(checkWord.next());;
                } while (answer.charAt(0) == 'y');

            } else if (choice == 2) {
                File file = new File(gamewords);
                Scanner inputFile = new Scanner(file);
                while (inputFile.hasNext()) {
                    String line = inputFile.nextLine();
                    System.out.println(line);
                }
                inputFile.close();

            }

            //close the file
        } while (choice != 3);

    }
    
    

    

}
