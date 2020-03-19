/*
Brendan Boyle
Homework 4 9/24/2019
Jeremy Ezell
The purpose of this program is to take a phrase and replace any word in the phrase
with a word of the user's choice. Other aspects are a word counter, character counter,
and also a loop that determines the longest and shortest word in the phrase.
*/
package Homework4;
import java.util.Scanner;

public class Hw4 
{
    //no methods because no duplication of code
    public static void main(String[] args) 
    {        
        //input phrase
        Scanner input = new Scanner(System.in);
        
        System.out.println("Please enter phrase to adjust: ");
        String phrase = input.nextLine();        
        String operator = "n";
        String incorrectWord;
        String fixedWord;             
        
        /*
        do while loop that executes as long as the user wants to continue
        replacing words from the phrase.
        */
        int placement = 0;
        do
        {
            //another while loop to make sure the user's replacement word is in the phrase
            int y = 0;
            while (y < 1)
            {
                System.out.print("\nEnter word to be replaced: ");
                incorrectWord = input.next();
                System.out.print("Enter word to replace with: ");
                fixedWord = input.next();
                //replacing code goes here
                if (phrase.indexOf(incorrectWord) != -1)
                {
                    while (phrase.indexOf(incorrectWord) != -1)
                    {
                        /*
                        take the incorrect word's placement in the phrase and its length
                        and use those two numbers to create a space to input the 
                        fixed word into the phrase.
                        */
                        //extract incorrect word first then input new word
                        placement = phrase.indexOf(incorrectWord);
                        String phrase1 = phrase.substring(0, placement); 
                        String phrase2 = phrase.substring((placement + incorrectWord.length()), phrase.length());
                        phrase = (phrase1 + fixedWord + phrase2);
                        //increment y to get out of the loop
                        y++;
                    }                    
                }
                else if (phrase.indexOf(incorrectWord) == -1)
                {
                    /*
                    if the user wants to replace a word that is NOT in the phrase
                    then they get these error messages and back to the top of the loop
                    */
                    System.out.println("Could not find: \"" + incorrectWord + "\" in your phrase");
                }   
            }
            
            /*
            ask the user if they want to continue to replace words
            if they dont input a y or a n, it will loop until they do
            */
            System.out.println("Current Phrase Version: \n\"" + phrase + "\"");
            boolean answer = true;
            while (answer)
            {
                System.out.print("\nContinue Replacing Words? (y or n): ");
                operator = input.next();
                if ((operator.equalsIgnoreCase("y")) || (operator.equalsIgnoreCase("n")))
                {
                    answer = false;
                }
                else
                {
                    System.out.println("Please enter either \"y\" or \"n\"");
                    answer = true;
                }
            }           
        } while (operator.equalsIgnoreCase("y"));       
        
        /*
        I want to use charAt to search if there is a space
        if there is a space, we can assume there was a word before it
        the wourdCount will start at 1 because the final word in the phrase 
        does not have a space after it hopefully
        */
        int z = 0;
        int wordCount = 1;       
        while (z != phrase.length())
        {            
            if (phrase.charAt(z) == ' ')
            {              
                wordCount++;               
            }
            z++;
        }        
        
        /*
        in order to parse each word from the string and compare them to each other
        I decide to use a char and build each word into a different string and then
        separate once I hit a space. then I compare them to each other word by word
        to find the shortest and longest word
        */
        String word = "";
        String longWord = "";
        int longWordLength = 0;
        String shortWord = "";
        int shortWordLength = 1000;
        char character;
        phrase = phrase + " ";
        for (int j = 0; j < phrase.length(); j++)
        {
            character = phrase.charAt(j);
            if (character != ' ')
            {
                //here we build each word by identifying where the spaces are
                //and adding each character onto char ch until there's a space
                word = word + character;
            }
            else
            {
                if (word.length() >= longWordLength)
                { 
                    longWord = word;
                    longWordLength = longWord.length();
                }
                if (word.length() <= shortWordLength)
                {
                    shortWord = word;
                    shortWordLength = shortWord.length();
                }
                word = "";
            }
        }      
        //output        
        System.out.println("\nFinal Phrase: ");
        System.out.println("============================");
        System.out.println(phrase);
        System.out.println("# of Words: " + wordCount);
        System.out.println("# of Characters: " + (phrase.length() - 1));
        System.out.println("Longest Word: \"" + longWord + "\"");
        System.out.println("Shortest Word: \"" + shortWord + "\"");
        System.out.println("============================");        
    }
}
