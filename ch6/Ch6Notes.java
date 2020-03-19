
package ch6;
import java.util.*;

public class Ch6Notes 
{
    public static void main(String[] args) 
    {
        //methods
        Scanner input = new Scanner(System.in);
        System.out.print("Input two numbers: ");
        int num1 = input.nextInt();
        int num2 = input.nextInt();
        loopThrough(num1, num2); 
        System.out.println("What is your inflation variable? ");
        int num3 = input.nextInt();
        
        System.out.println("\nthe inflated sum is " 
                + inflatedSum(num1,num2, num3));
    }
    //class level - global - program scope
    //structure and use of a method
    /*
    1. define a method
    a. method header
    b. method body
    [access specifier(s)][return type][method name]
    {
        //method body
        //method's code that executes when the method is invoked
        
        [return statement ?]
    }
    2. invoke or call or use a method
            [method name]([acrguement(s)])
    */
    //public = publicly accessible
    //static = return type    
    public static void printNumbers(int a, int b)
    {
        for (int i = b; i < a; i++)
        {
            if ((i % 10) == 0)
            {
                System.out.println("");
            }
            System.out.print(i + " ");
        }                
    }
    public static void loopThrough(int num1, int num2)
    {
        if (num2 > num1)
        {
            int temp = num1;
            num1 = num2;
            num2 = temp;            
            System.out.println("Reversing the numbers .... corrected.");            
        }
        printNumbers(num1, num2);
    }
    public static int inflatedSum(int lowerNumber, int higherNumber, int inflation)
    {
        int sum = 0;
        lowerNumber *= inflation;
        higherNumber *= inflation;
        
        for (int i = lowerNumber; i < higherNumber; i++)
        {
            sum += i;
        }
        return sum;       
    }
    //method overloading
    /*
    how?
    create a second version of the method that has the exact same name
    but differs from the original with a change in the order, the quantity, 
    and/or the data types of its arguments/parameters
    */

}
