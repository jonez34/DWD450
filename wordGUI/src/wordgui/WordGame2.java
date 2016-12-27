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
import java.util.Scanner;

/**
 *
 * @author hjones2368
 */
public class WordGame2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
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
                viewScore();
            } else if (menu == 3) {
                addWords();
            }
        } while (menu != 4);
        /*1- Your gameController method for every player will call a 
        userController method from a “Data” class and will get a name
        from userController. */

        /*5- Your “Data” class will have two methods to set and get highscores 
        from usersInfo file and userController method. */
    }

    private static void gameController() throws FileNotFoundException, IOException {
        /*3- Create a “Game” class and call its playGame method from your 
        gameController. Your gameController will pass usernames to your 
        playGame method, and after finishing one round of game, it will 
        return an array of scores to gameController 
        (containing two scores for player 1 and player 2).*/

        /*4- gameController method then will announce the winner and ask 
        users if they want to continue the game or not if yes, it will 
        call playGame again else it will send the winner and the total 
        score of the winner to setScores method of your “Data” class.*/
        String nextRound;
        String[] pNames = new String[2];
        int[] scores = new int[2];
        //creates user database and connection to Data class
        Data myDB = new Data();
        
        

        Scanner input = new Scanner(System.in);
        //connects to Data base array on Data Class and gets users and scores
        pNames[0] = myDB.userController();
        scores[0] = myDB.getScores(pNames[0]);
        pNames[1] = myDB.userController();
        scores[1] = myDB.getScores(pNames[1]);
        //creats a new game run and connects to Game class
        Game myGame = new Game(pNames);
        do {
            //sets the return of the game just played
            int[] gScores = myGame.playGame();
            //convert gscores into ints and scores to int to add together
            scores[0] = gScores[0] + scores[0];
            scores[1] = gScores[1] + scores[1];
            //checks if they want to play again
            System.out.println("Do you want play another round?\t");
            nextRound = new String(input.next());
            
        } while (nextRound.charAt(0) == 'y'); // If yes go to n new word
        
        //sets the new high score to the data base 
        myDB.setScores(scores);
    }

    private static void addWords() throws IOException {
        int choice = 0;
        String answer;
        String word = new String();

        // filename 
        String gamewords = "hangmanwords.txt"; //File Name
        Scanner keyboard = new Scanner(System.in);
        Scanner checkWord = new Scanner(System.in);
        //Open the file
        do {
            //menu
            System.out.println("Do you want to \n 1. add a word  "
                    + "\n 2. see avaiable words "
                    + "\n 3. Return to Main Menu");
            choice = keyboard.nextInt();
            word = (keyboard.nextLine());

            if (choice == 1) {
                // runs user through adding a new word into the file
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
                //shows all the avaiable words in the file
            } else if (choice == 2) {
                File file = new File(gamewords);
                Scanner inputFile = new Scanner(file);
                while (inputFile.hasNext()) {
                    String line = inputFile.nextLine();
                    System.out.println(line);
                }
                inputFile.close();

            }

            
            //end game
        } while (choice != 3);

    }

    private static void viewScore() throws FileNotFoundException {

        String filename = "userinfo.txt"; //File Name
        //Open the file
        File file = new File(filename);
        Scanner inputFile = new Scanner(file);
        //Allows user to see current high scores
        while (inputFile.hasNext()) {
            String line = inputFile.nextLine();
            System.out.println(line);
        }

        //close the file
        inputFile.close();
    }
}
