/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordgame;

import java.io.File;
import java.io.FileNotFoundException;
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
public class WordGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Scanner keyboard = new Scanner(System.in);
        int menu;

        do { //menu for start of application
            System.out.println("Chose one of the options \n 1. Start Game "
                    + "\n 2. Show Scores \n 3. Add Word \n 4. Setup Users \n "
                    + "5. Exit");

            //gamer unput
            menu = keyboard.nextInt();
            // Menu options 
            if (menu == 1) {
                gamePlay();
            } else if (menu == 2) {
                showScore();
            } else if (menu == 3) {
                addWord();
            } else if (menu == 4) {
                userController();
            }
        } while (menu != 5);
        // TODO code application logic here

    }

    public static void userController() throws FileNotFoundException, IOException {
        Scanner keyboard = new Scanner(System.in);
        int menu;

        do { //menu for start of application
            System.out.println("Chose one of the options \n 1. Read DB "
                    + "\n 2. Write DB \n 3. Exit");

            //gamer unput
            menu = keyboard.nextInt();
            // Menu options 
            if (menu == 1) {

                readDB();

            } else if (menu == 2) {
                writeDB();
            }
        } while (menu != 3);
    }

    public static void readDB() throws FileNotFoundException {
        // read database file
        Scanner scan = new Scanner(System.in);
        ArrayList<String> dataBase = new ArrayList<>();
        File myFile = new File("scores.txt");
        Scanner inputFile = new Scanner(myFile);
        while (inputFile.hasNext()) {
            dataBase.add(inputFile.nextLine());

        }
        inputFile.close();
        int numberofRecords = dataBase.size();
        String[][] dBase = new String[100][];

        for (int i = 0; i < numberofRecords; i++) {
            System.out.println(dataBase.get(i));
            dBase[i] = dataBase.get(i).split(" ");
        }

        for (int i = 0; i < numberofRecords; i++) {
            for (int j = 0; j < dBase[i].length; j++) {
                System.out.println(dBase[i][j]);
            }
        }
        System.out.println("Enter a username:");
        String userName = new String(scan.nextLine());
        int flag = -1;
        for (int i = 0; i < numberofRecords; i++) {
            if (dBase[i][0].equalsIgnoreCase(userName)) {
                flag = i;
            }
        }
        if (flag == -1) {
            System.out.println("Enter a password:");
            String password = new String(scan.nextLine());
            dBase[numberofRecords] = new String[3];
            dBase[numberofRecords][0] = userName;
            dBase[numberofRecords][1] = password;
            dBase[numberofRecords][2] = "0";
            numberofRecords++;
        }
        if (flag != -1) {
            System.out.println("Enter a password:");
            String password = new String(scan.nextLine());
            if (password.equals(dBase[flag][1])) {
                System.out.println("Correct");

            } else {
                System.out.println("False");
            }
        }
        if (flag != -1) {
            System.out.println("Enter a score");
            dBase[flag][2] = scan.nextLine();
        }
        String filename = "scores.txt";
        PrintWriter outputfile = new PrintWriter(filename);
        for (int i = 0; i < numberofRecords; i++) {
            for (int j = 0; j < dBase[i].length; j++) {
                outputfile.printf("%s ", dBase[i][j]);
            }
            outputfile.printf("\n");

        }
        outputfile.close();
    }

    public static void writeDB() throws IOException {

        String filename = "scores.txt";

        FileWriter fw = new FileWriter(filename, true);
        PrintWriter outputfile = new PrintWriter(fw);
        System.out.println("Enter a record");
        Scanner scan = new Scanner(System.in);
        outputfile.printf("%s\n", scan.nextLine());
        outputfile.close();

    }

    private static void gamePlay() throws IOException {
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

        Random rand = new Random();

        // Entering players names add names to array
        System.out.println("First Player enter your name.");
        pNames[0] = input.next();
        System.out.println("Second Player enter your name.");
        pNames[1] = input.next();
        do {

            //Start of game
            // Get random word
            //open file to String array
            while (inputFile.hasNext() && w < words.length) {
                words[w] = inputFile.nextLine();
                w++;
            }
            String word = words[rand.nextInt(w)];//To get a random word

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
            System.out.printf("%s, %d\n%s: %d\n", pNames[0], scores[0], pNames[1], scores[1]);
            //ask them to see if they want another round
            //if yes goto a new word
            //if no save the winner in scores tables
            System.out.println("Do you want play another round?\t");
            nextRound = new String(input.next());
            notRevealed = true;
        } while (nextRound.charAt(0) == 'y'); // If yes go to n new word
        outputFile.printf("%s, %d\n%s: %d\n", pNames[0], scores[0], pNames[1], scores[1]);
        outputFile.close();
        inputFile.close();
    }

    private static void showScore() throws IOException {
        // filename 
        String filename = "gamedata.txt"; //File Name
        //Open the file
        File file = new File(filename);
        Scanner inputFile = new Scanner(file);

        while (inputFile.hasNext()) {
            String line = inputFile.nextLine();
            System.out.println(line);
        }

        //close the file
        inputFile.close();
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
