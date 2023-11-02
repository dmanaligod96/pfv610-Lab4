package edu.utsa.cs3443.pfv610_lab4.model;

import android.app.Activity;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.util.Scanner;

public class Trivia {
    private String question;
    private String option1;
    private String option2;
    private String option3;

    //private String[] options = new String[3];

    private String descriptionAnswer;

    //Do we need to identify which answer is the correct one?
    private String correctAnswer;

    public Trivia(String question, String option1, String option2, String option3, String descriptionAnswer){
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.descriptionAnswer = descriptionAnswer;
    }

    public Trivia(){
        System.out.println("Explicit default constructor");
    }

    //TODO: Write the setters and getters for all the instance variables

    public String getCorrectAnswer(){
        return this.correctAnswer;
    }


    /*
    @param: Activity activity
    @return: Trivia object
    loadTrivia: This method takes in an Activity from input as an argument, it reads the file
    and stores 1 piece of trivia from that file.
     */
    public Trivia loadTrivia(Activity activity){
        //Get an instance of AssetManager
        //Read the file in the assets folder using InputStream
        //How many lines are in this file, the number of lines are equivalent to the number of trivia questions
        //Randomly select a random number smaller or equal to the number of lines
        //Create an object of Trivia from that line's information
        //Return the object

        AssetManager manager = activity.getAssets();
        Scanner scanner;
        try{
            InputStream input = manager.open("trivia.csv");
            scanner = new Scanner(input);
            int i = 0;
            while(scanner.hasNextLine()){
                i++;
            }
            System.out.println("Number of lines: "+ i);
            //Randomly select a number from 1 to i
            SecureRandom sRandom = new SecureRandom();
            //if i = 7 => A random number from 0 to 6
            int lineNumber = sRandom.nextInt(i) + 1;

            int j = 1;
            String line = "";
            while(j<lineNumber){
                line = scanner.nextLine();
                j++;
            }
            //when this loop is over, I am standing right by the line that I want to return

            line = scanner.nextLine();
            String[] lineSplit = line.trim().split(",");
            this.question = lineSplit[0].trim();
            this.option1 = lineSplit[1].trim();
            this.option2 = lineSplit[2].trim();
            this.option3 = lineSplit[3].trim();
            this.descriptionAnswer = lineSplit[4];
            //From index 4 forward, we have the description
            for (int k = 5; k< lineSplit.length; k++){
                this.descriptionAnswer = this.descriptionAnswer + "," + lineSplit[k];
            }
            identifyCorrectAnswer();
            return (this);
        } catch(FileNotFoundException e){
            Log.d("Exception: ", "File not found");
        }
        catch (IOException e){
            //IO Exception can happen for various reasons: file not found, access permission, corrupt file
            Log.d("Exception: ", "IO Exception");
        }
        //if catch happens, meaning we have an error reading or accessing the file:
        //Program control will be going through catch, we see a print statement (Log.d)
        //Then the program control return (this)
        //this is pointing to the current object of Trivia, but all instance variables (such as question, options, etc.)
        //are going to be NULL! Because we weren't successul in reading them from the file
        return (this);
    }

    private void identifyCorrectAnswer(){
        //takes all the options and checks if the description contains any of the options, if so, that
        //option becomes the correctAnswer
        if(this.descriptionAnswer.contains(this.option1)){
            this.correctAnswer = this.option1;
        }
        else if(this.descriptionAnswer.contains(this.option2)){
            this.correctAnswer = this.option2;
        }
        else
            this.correctAnswer = this.option3;
    }



}
