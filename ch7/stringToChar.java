
package ch7;
import java.util.Scanner;

public class stringToChar 
{
    public static void main(String[] args) 
    {
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter a phrase: ");
        String phrase = input.nextLine();
        System.out.println("Phrase: \"" + phrase + "\"");
        stringToChar(phrase);
        System.out.println("");
    }
    public static void stringToChar(String phrase)
    {
        char[] array = new char[phrase.length()];
        for (int i = 0; i < phrase.length(); i++)
        {
            array[i] = phrase.charAt(i);
            System.out.print("[" + array[i] + "]");
        }
    }
    
}
