/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordgui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author hjones2368
 */
public class Data{

    String[][] userInfo1;
    int numberofRecords;
    String pNames;
    int[] indexKeeper;
    
    //constructor
    public Data() throws FileNotFoundException {
        //populates user database
        getInfoFromFile();
        indexKeeper = new int[2];
        indexKeeper[0] = -1;
        indexKeeper[0] = -1;
        
    }

    private void getInfoFromFile() throws FileNotFoundException {
        //read in your userInfo file
        //Scanner scan = new Scanner(System.in);       
        ArrayList<String>userInfo = new ArrayList<>();
        File myFile = new File("userinfo.txt");
        Scanner inputFile = new Scanner(myFile);
        //Load all data from text file
        while (inputFile.hasNext()) {
            userInfo.add(inputFile.nextLine());

        }   
        inputFile.close();
        numberofRecords = userInfo.size();
        userInfo1 = new String[100][];
        //populate user info into an array
        for (int i = 0; i < numberofRecords; i++) {
            System.out.println(userInfo.get(i));
            userInfo1[i] = userInfo.get(i).split(" ");
        }
        
    }

    private void setInfoToFile() throws FileNotFoundException {
        String filename = "userinfo.txt";
        PrintWriter outputfile = new PrintWriter(filename);
        for (int i = 0; i < numberofRecords; i++) {
            for (int j = 0; j < userInfo1[i].length; j++) {
                outputfile.printf("%s ", userInfo1[i][j]);
            }
            outputfile.printf("\n");

        }
        outputfile.close();
    }

    public String userController() throws FileNotFoundException {
        
        
        
        
        userGUI frame = new userGUI();
        
        Scanner scan = new Scanner(System.in);
        pNames = ("");
        /* 
        userController method will ask for user name 
        
        if it doesn’t exist 
        in your usersInfo Array 
        it will ask for a password to create a new user, 
        
        else it will ask for your previously saved password to let 
        you play the game with that username. 
        
        If password was correct it 
        will return the username to gameController. 
         */
        //asks for returning users or creates a new user for games
        int flag = -1;        
        do {
            System.out.println("Enter a username:");
            String userName = new String(scan.nextLine());
            
            for (int i = 0; i < numberofRecords; i++) {
                if (userInfo1[i][0].equalsIgnoreCase(userName)) {
                    flag = i;
                }
            }
            if (flag == -1) {
                System.out.println("Enter a password:");
                String password = new String(scan.nextLine());
                userInfo1[numberofRecords] = new String[3];
                userInfo1[numberofRecords][0] = userName;
                userInfo1[numberofRecords][1] = password;
                userInfo1[numberofRecords][2] = "0";
                flag = numberofRecords;
                numberofRecords++;
            } else {
                System.out.println("Enter a password:");
                String password = new String(scan.nextLine());
                if (password.equals(userInfo1[flag][1])) {
                    System.out.println("Correct");

                } else {
                    System.out.println("False");
                }
            }
            pNames = userName;
            
        } while (flag == -1);
        //sets a hold place for the users in game to be found quicker
        if (indexKeeper[0] == -1){
            indexKeeper[0]= flag;
               
        }else{
            indexKeeper[1]= flag;
        }
        
        return (pNames);
    }
    //replace new high score with old one
    public void setScores(int[] scores) throws FileNotFoundException {
        
        userInfo1[indexKeeper[0]][2]= String.valueOf(scores[0]);
        userInfo1[indexKeeper[1]][2]= String.valueOf(scores[1]);
        setInfoToFile();

    }
    //used to get current player scores
    public int getScores(String pName) {
       int scores = 0;
       
        for (int i = 0; i < numberofRecords; i++){
            if (userInfo1[i][0].equalsIgnoreCase(pName)) {
                   scores = Integer.parseInt(userInfo1[i][2]);
                }
        }
        
        return scores;

    }

    

    

    

    
}
