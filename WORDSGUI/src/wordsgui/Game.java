/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordsgui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.Random;
import javax.swing.*;
import static jdk.nashorn.internal.objects.NativeString.toLowerCase;

/**
 *
 * @author bjozeabbaschian
 */
public class Game extends JPanel {

    int playerCounter;
    String[] pNames;
    int[] scores;
    JPanel pnl;
    PlayerPane[] player;
    JTextField wordField;
    String[] words = new String[100];
    int w = 0;
    char[] guessWord;
    String word;

    public Game(String[] pnames, int[] oldscores) throws IOException {
        pNames = pnames;
        scores = oldscores;
        playerCounter = 0;
        player = new PlayerPane[2];
        player[0] = new PlayerPane(pNames[0]);
        player[1] = new PlayerPane(pNames[1]);
        player[1].playerInput.setEnabled(false);
        JPanel words = new JPanel();
        getWords();

        player[0].playerInput.addKeyListener(new TextListener());
        player[1].playerInput.addKeyListener(new TextListener());
        String guessWord = new String(wordSetup());
        wordField = new JTextField(guessWord);
        words.setMinimumSize(new Dimension(300, 300));
        words.add(wordField);

        pnl = new JPanel();
        pnl.setLayout(new BoxLayout(pnl, BoxLayout.X_AXIS));
        pnl.add(player[0]);
        pnl.add(words);
        pnl.add(player[1]);
        add(pnl);

    }

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

    public char[] wordSetup() {
        Random rand = new Random();
        word = words[rand.nextInt(w)];
        guessWord = new char[word.length()];
        for (int i = 0; i < word.length(); i++) {
            guessWord[i] = '-';
        }

        return guessWord;
    }

    public char[] replaceWord(char input) {
        String guess = new String(Character.toString(input));

        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == guess.charAt(0)) {
                //replace dashes with correct letter
                guessWord[i] = guess.charAt(0);
                //correctGuess = true;
                //score[playerController]++;

            }

        }
        return guessWord;
    }

    public boolean checkWord(char input) {
        boolean found = false;
        String guess = new String(Character.toString(input));
        
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == guess.charAt(0)) {
                found = true;
                return found;
            }
        }
        return found;
        
    }

    private class TextListener implements KeyListener {

        private void checkGuess(char text) {
            if (true) {
                player[playerCounter].playerGuess = new JList(player[playerCounter].playerGuessList);
                player[playerCounter].playerGuessList.addElement(text);
                if (checkWord(text)) {
                    String guessWord = new String(replaceWord(text));
                    wordField.setText((guessWord));
                } else {
                    player[playerCounter].playerInput.setEnabled(false);
                    
                    playerCounter++;
                    if (playerCounter == 2) {
                        playerCounter = 0;
                    }
                    player[playerCounter].playerInput.setEnabled(true);       
                }
                
            }
        }

        public void keyTyped(KeyEvent e) {
            System.out.print(e.getKeyChar());
            checkGuess(e.getKeyChar());
        }

        public void keyPressed(KeyEvent e) {
            return;
        }

        public void keyReleased(KeyEvent e) {
            return;
        }
    }

    private class PlayerPane extends JPanel {

        JLabel playerName, playerScore;
        JTextField playerInput;
        JList playerGuess;
        DefaultListModel playerGuessList = new DefaultListModel();

        public PlayerPane(String pName) {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            playerName = new JLabel(pName);
            playerInput = new JTextField(3);

            playerGuess = new JList(playerGuessList);
            playerScore = new JLabel("SCORE");

            add(playerName);
            add(playerInput);
            add(playerGuess);
            add(playerScore);

        }
    }

}
