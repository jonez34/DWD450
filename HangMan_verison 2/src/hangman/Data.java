/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangman;

import java.io.*;
import java.util.*;

/**
 *
 * @author hjones2368
 */
public class Data {

    private ArrayList<String> userInfo = new ArrayList<>();

    //Constructor
    public Data() {

    }

    public static String userController() {
        Scanner input = new Scanner(System.in);
        System.out.println("First Player enter your name.");
        pNames[0] = input.next();

        readUserInfo();

        // to search for the name in userinfo file 
        //if it was found we will call a password method;
        //if it was not found we will ask for a new password and ad it to file
        //return the name
        String name = new String("");
        return name;
    }

    public static void setScores(String userName[], int score) throws FileNotFoundException {
        //Sreach for name in the file and replace its score with our score
        File file = new File(filename);
        Scanner inputFile = new Scanner(file);
        while (inputFile.hasNext()) {
            String line = inputFile.nextLine();
            System.out.println(line);

        }

        inputFile.close();
    }

    public static void getScores() {

    }

    private ArrayList<String> getUserInfo() throws FileNotFoundException {
        //read from users file into ArrayList
        File file = newFile(filename);
        Scanner inputFile = new Scanner(file);
        return new ArrayList<String>();
    }

    public void readDB() throws FileNotFoundException {
        // read database file
        ArrayList<String> dataBase = new ArrayList<>();
        File myFile = new File("scores.txt");
        Scanner inputFile = new Scanner(myFile);
        while (inputFile.hasNext()) {
            dataBase.add(inputFile.nextLine());

        }
        for (int i = 0; i < dataBase.size(); i++) {
            System.out.println(dataBase.get(i));
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

    private static void setUserInfo() {

    }
}
