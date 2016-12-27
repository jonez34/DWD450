/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordgui;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;


/**
 *
 * @author hjones2368
 */
public class MainMenuGUI extends JFrame{
   private  JButton playBtn, scoreBtn, wordBtn, exitBtn; 
   private JPanel mainMenupanel,pnl;
   String[] pNames;
   int[] scores;
   
    public MainMenuGUI() {
        JPanel mainMenupanel = new JPanel();
        playBtn = new JButton("Play");
        scoreBtn = new JButton("Show Score");
        wordBtn = new JButton("Add Words");
        exitBtn = new JButton("Exit");
        playBtn.addActionListener(new Buttons());
        scoreBtn.addActionListener(new Buttons());
        wordBtn.addActionListener(new Buttons());
        exitBtn.addActionListener(new Buttons());
        mainMenupanel.setLayout(new GridLayout(4,0,10,10));
        mainMenupanel.add(playBtn);
        mainMenupanel.add(scoreBtn);
        mainMenupanel.add(wordBtn);
        mainMenupanel.add(exitBtn);
        JPanel pnl = new JPanel();
        pnl.add(mainMenupanel);
        add(pnl);
        setSize(300,200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }

    private class Buttons implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == playBtn) {
                try {
                    gameController();
                } catch (IOException ex) {
                    Logger.getLogger(MainMenuGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (e.getSource() == scoreBtn) {
                try {
                    viewScore();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(MainMenuGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (e.getSource() == wordBtn) {
                try {
                    addWords();
                } catch (IOException ex) {
                    Logger.getLogger(MainMenuGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (e.getSource() == exitBtn) {
                try {
                    addWords();
                } catch (IOException ex) {
                    Logger.getLogger(MainMenuGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            JOptionPane.showMessageDialog(null, "yahoo");
            
        }
        
        
    }
    private void gameController() throws FileNotFoundException, IOException {
        //open a window and ask for player one signin/signup usercontroller
        //open a window and ask for player two signin/signup
        //open the game window playgame
        //ask to try again or not as option pane
        
        String nextRound;
        pNames = new String[2];
        scores = new int[2];
        //creates user database and connection to Data class
        Data myDB = new Data();
        //userBUI myDB = new userGUI();
        
        

        Scanner input = new Scanner(System.in);
        //connects to Data base array on Data Class and gets users and scores
        //pNames[0] = myDB.userGUIController();
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
            //Joption Pane
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
