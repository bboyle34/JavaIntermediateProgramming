
package ch7;
import java.util.*;

public class Ch7Notes 
{
    public static void main(String[] args) 
    {
        /*
        NOTES
        array is basically a sequence of the same data type
        any valid data type can be used to create an array
        
        Rules for Java arrays:
        1. Arrays use index notation, meaning that the first "chunk"
        of data in an array is at index position 0, and the last "chunk"
        of data in an array is at index position length - 1
        2. Array sizes are fixed, meaning once you create an array you
        cannot change its size letters*
        3. Each "chunks" in an array is called an Element
        "Element at index position 3 is ..."
        4. Arrays are treated as complex variables and are passed by
        reference when used as parameters in methods
        5. An array that hasn't had Elements declared but does have
        its size declared, the Elements are defaulted to 0
        6. Attempting to declare an Element at index greater than the 
        size of an array will return an 
        ArrayIndexOutOfBoundsException error
        
        Example:
        If an Array is size 10, then it has elements at index
        positions 0 through 9
        */
       Scanner input = new Scanner(System.in);
       int[] myList = new int[9];
       for (int x = 0; x < myList.length; x++)
       {
            myList[x] = x + 1;
       }
       printList(myList);
       System.out.println("My list's length: " + myList.length);
       
       int[] anotherList = {9, 8, 7, 6, 5, 4, 3, 2, 1};
       printList(anotherList); 
       ln();
       
       System.out.print("Enter the number of items: ");
       int n = input.nextInt();
       int[] numbers = new int[n];       
       for (int x = 0; x < n; x++)
       {
           System.out.print("Enter a number: ");
           numbers[x] = input.nextInt();
       }       
       printList(numbers);
       ln();
       
       System.out.print("Enter the number of items: ");
       int m = input.nextInt();
       String[] words = new String[m];
       for (int y = 0; y < m; y++)
       {
           System.out.print("Enter a word: ");
           words[y] = input.next();
       }
       printList(words);
       ln();
       
       int aNum = 7;
       int[] anArray = new int[5];
       anArray[0] = 42; 
       anArray[4] = 100;
       anArray[0] = 41;
       String operator;
       do{
           System.out.print("Here is your list: "); 
           printList(anArray);
           System.out.print("Do you want to replace one of the Elements? (y/n) ");
           operator = input.next();
           if (operator.equalsIgnoreCase("n"))
           {
               break;
           }
           System.out.print("What index do you want to replace? "
                   + "(0-" + (anArray.length - 1) + ") ");
           int place = input.nextInt();
           System.out.print("What number you like to put in? ");
           int replacement = input.nextInt();
           anArray[place] = replacement;
           
       } while (operator.equalsIgnoreCase("y"));
       System.out.println(anArray);
       ln();
       
       int[] shortArray = {8, 7, 6, 5};
       //arrays have a length property
       //strings have a length method
       for (int i = 0; i < shortArray.length; i++)
       {
           //i = index
           System.out.print(shortArray[i] + " ");
       }
       ln();
       
       /*
       for each loop
       figures out beginning, end, and each element of 
       the array automatically
       for each element in array, run loop
       e is not an index position, but it is a copy of each element
       */
       newPrint(anArray);
       ln();
       newPrint(shortArray);
       ln(); 
       
       //copying arrays
       //passing arrays to methods
       // chapter 8, 2 dimensional arrays
    }
    public static void newPrint(int[] input)
    {
        for (int e: input)
        {
            System.out.print(e + " ");
        }
    }
    public static void ln()
    {
        System.out.println("");
    }
    public static void printList(int[] input)
    {
        for (int i = 0; i < input.length; i++)
        {
            if (i == (input.length - 1))
            {
                System.out.println(input[i]);
            }
            else
            {
            System.out.print(input[i] + ", ");
            }
        }
    }
    public static void printList(String[] input)
    {
        for (int i = 0; i < input.length; i++)
        {
            if (i == (input.length - 1))
            {
                System.out.println(input[i]);
            }
            else
            {
            System.out.print(input[i] + ", ");
            }
        }
    }
    public static void printList(double[] input)
    {
        for (int i = 0; i < input.length; i++)
        {
            if (i == (input.length - 1))
            {
                System.out.println(input[i]);
            }
            else
            {
            System.out.print(input[i] + ", ");
            }
        }
    }
    public static void printList(char[] input)
    {
        for (int i = 0; i < input.length; i++)
        {
            if (i == (input.length - 1))
            {
                System.out.println(input[i]);
            }
            else
            {
            System.out.print(input[i] + ", ");
            }
        }
    }

}
