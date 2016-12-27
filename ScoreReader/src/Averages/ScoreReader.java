/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Averages;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author hjones2368
 */
public class ScoreReader {
    private Scanner inputFile;
    private String line;
    /**
     * @param args the command line arguments
     */
    
    public ScoreReader (String filename)throws IOException{
        File file = new File(filename);
        inputFile = new Scanner(file);
    }
    public boolean readNextLine() throws IOException{
        boolean lineRead; // Flag variable
        
        //Determine whether there is more to read.
        lineRead = inputFile.hasNext();
        
        // If so, read the next line.
        if (lineRead)
            line = inputFile.nextLine();
        
        return lineRead;
    }
    public double getAverage(){
        int total = 0; //Accumulator
        double average; // The average test score
        
        //Tokenize the last line read from the file.
        String[] tokens = line.split(",");
        
        //Calculate the total of the test scores.
        for (String str : tokens){
            total += Integer.parseInt(str);
        }
        
        //Calculate the average of the scores.
        //Use a cast to avoid integer division.
        average = (double) total / tokens.length;
        //Return the average
        return average;
    }
    
    public  void close() throws IOException{
        inputFile.close();
    }
    
}
