/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication8;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author hjones2368
 */
public class JavaApplication8 {

    /**
     * @param args the command line arguments
     */
    public void main(String[] args) throws FileNotFoundException, IOException {

        Scanner keyboard = new Scanner(System.in);
        int menu;

        do { //menu for start of application
            System.out.println("Chose one of the options \n 1. Start Game "
                    + "\n 2. Show Scores \n 3. Add Word \n 4. Exit");

            //gamer unput
            menu = keyboard.nextInt();
            // Menu options 
            if (menu == 1) {

                readDB();

            } else if (menu == 2) {
                writeDB();
            } else if (menu == 3) {

            }
        } while (menu != 4);

        // TODO code application logic here
    }

    public void readDB() throws FileNotFoundException {
        // read database file
        Scanner scan = new Scanner(System.in);
        ArrayList<String> dataBase = new ArrayList<>();
        File myFile = new File("scores.txt");
        Scanner inputFile = new Scanner(myFile);
        while (inputFile.hasNext()) {
            dataBase.add(inputFile.nextLine());

        }
        String[][] dBase = new String[100][3];

        for (int i = 0; i < dataBase.size(); i++) {
            System.out.println(dataBase.get(i));
            dBase[i] = dataBase.get(i).split(" ");
        }

        for (int i = 0; i < dataBase.size(); i++) {
            for (int j = 0; j < 3; j++) {
                System.out.println(dBase[i][j]);
            }
        }
        System.out.println("Enter a username:");
        String userName = new String(scan.nextLine());
        int flag = -1;
        for (int i = 0; i < dataBase.size(); i++) {
            if (dBase[i][0].equalsIgnoreCase(userName)) {
                flag = i;
            }
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
    }

    public void writeDB() throws IOException {

        String filename = "scores.txt";

        FileWriter fw = new FileWriter(filename, true);
        PrintWriter outputfile = new PrintWriter(fw);
        System.out.println("Enter a record");
        Scanner scan = new Scanner(System.in);
        outputfile.printf("%s\n", scan.nextLine());
        outputfile.close();

    }

}
