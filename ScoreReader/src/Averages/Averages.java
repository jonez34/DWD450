/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Averages;

import java.io.IOException;


/**
 *
 * @author hjones2368
 */
public class Averages {
    public static void main(String[] args) throws IOException{
        double average; // Test average
        int studentNumber = 1; // Control variable
        
        // Create a ScoreReader object.
        
        ScoreReader scoreReader = new ScoreReader("Grades.csv");
        
        // Display the average.
        while (scoreReader.readNextLine()){
            // Get the average from the ScoreReader.
            average = scoreReader.getAverage();
            
            // Display the student's average.
            System.out.println("Average for student " + studentNumber + " Is " 
                    +  average);
            //Increment the student number.
            studentNumber++;
        }
        //Close the ScoreReader
        scoreReader.close();
        System.out.println("No more scores.");
    }
}
