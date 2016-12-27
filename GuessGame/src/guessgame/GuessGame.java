/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guessgame;


import java.io.FileWriter;
import java.io.*;
import java.io.PrintWriter;
import static java.lang.Math.abs;
import java.util.Scanner;
import java.util.Random;
import javafx.scene.shape.Line;



/**
 *
 * @author hjones2368
 */
public class GuessGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        //main menu
        Scanner keyboard = new Scanner(System.in);
        int menu;
        
        do{ //menu for start of application
            System.out.println("Chose one of the options \n 1. Start Game " +
             "\n 2. Show Scores \n  3. Exit");
            
            //gamer unput
            menu = keyboard.nextInt();
            // Menu options 
            if (menu == 1){
                gamePlay();
            }else if(menu == 2){
                showScore();
            }
        }while(menu != 3);
        // TODO code application logic here
    }
    private static void gamePlay() throws IOException{
        Scanner keyboard = new Scanner(System.in);
        String filename = "gamedata.txt"; //File Name
        FileWriter fw = new FileWriter(filename, true);
        PrintWriter outputFile = new PrintWriter(fw);
        int secretNum = 0;        
        int number1 = 0;
        int number2 = 0;
        int score1 = 0;
        int score2 = 0;
        int newGame;
        Random rand = new Random();
        
        // Entering players names
        System.out.println("First Player enter your name.");
        String player1 = keyboard.nextLine();
        System.out.println("Second Player enter your name.");
        String player2 = keyboard.nextLine();
        
        // start of the game
        do
        {//has players chose a number
        System.out.println( player1 + " please chose a number between 1 "
                + "and 100.");
        number1 = keyboard.nextInt();
        while (number1 < 0 || number1 > 101){
            System.out.println( player1 + "please chose another number. " +
                    "Your number is not with in 1 to 100. ");
                    number1 = keyboard.nextInt();
        }
        System.out.println( player2 + " please chose a number between 1 "
                + "and 100.");
        number2 = keyboard.nextInt();
        while (number2 < 0 || number2 > 101){
            System.out.println(player2 +  " please chose another number. " +
                    "Your number is not with in 1 to 100. ");
                    number2 = keyboard.nextInt();
        }
        //Random number created
        secretNum = rand.nextInt(100);
        // Test who got close to the secret number
        if(abs(number1 - secretNum) == abs(number2 - secretNum)){
            //prints winner to the screen 
            System.out.println( player1 + player2 + " Ties!!!");
           
        }else if(abs(number1 - secretNum) < abs(number2 - secretNum)){
            //prints winner to the screen and increases score
            System.out.println( player1 +" Wins!!!");
            score1++;
        }else{
            //prints winner to the screen and increases score
            System.out.println( player2 +" Wins!!!");
            score2++;
        }
        System.out.println("Current Score is " + player1 + " " + score1 + 
                " to " + player2 + " " + score2);
        //end of game check
        System.out.println("Do you want to play again? 1 for no");
            newGame = keyboard.nextInt();
        }while(newGame != 1);
        //declare a winner of the game
        if(score1 == score2){
            //prints winner to the screen
            System.out.println(player1 + " and " + player2 + " tied....");
            outputFile.println(player1 + " and " + player2 + " tied....");
        }else if(score1 > score2){
            //prints winner to the screen
            System.out.println(player1 + " is the WINNER !!!!!!!" + 
                    " With the score of " + score1 + " to " + score2);
            //writes winner to the screen
            outputFile.println(player1 + " is the WINNER !!!!!!!" + 
                    " With the score of " + score1 + " to " + score2);
        }else{
            //prints winner to the screen
            System.out.println(player2 + " is the WINNER !!!!!!!" + 
                    " With the score of " + score2 + " to " + score1);
            //writes winner to the screen
            outputFile.println(player2 + " is the WINNER !!!!!!!" + 
                    " With the score of " + score2 + " to " + score1);
        }
        outputFile.close();
        
    }
    private static void showScore() throws IOException{
        // filename 
        String filename = "gamedata.txt"; //File Name
        //Open the file
        File file = new File(filename);
        Scanner inputFile = new Scanner(file);
        while (inputFile.hasNext()){
            String line = inputFile.nextLine();
            System.out.println(line);
        }
        
        //close the file
        inputFile.close();
        
        
        
    }
}
