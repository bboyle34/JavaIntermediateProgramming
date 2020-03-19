
package Homework2;
import java.util.Scanner;
/*
Brendan Boyle
CIS 331 Section 3
9/7/19
*/
public class Hw2Part1 
{
    public static void main(String[] args) 
    {
        //input new Scanner class
        Scanner input = new Scanner(System.in);
                     
        //setup table and get inputs
        System.out.println("==== Print Math Table ====");
        System.out.print("Enter a and b on one line: ");
        int a = input.nextInt();
        int b = input.nextInt();
        
        //get first power set
        double pow1 = (Math.pow(a,b));
        
        //align output with table and increment each variable by one
        //each power must be a different variable so we can add it at the end
        System.out.println("a     b     pow(a,b)");
        System.out.println(a + "     " + b + "     " + (int)pow1);
        a++;
        b++;
        double pow2 = (Math.pow(a, b));
        System.out.println(a + "     " + b + "     " + (int)pow2);
        a++;
        b++;
        double pow3 = (Math.pow(a, b));
        System.out.println(a + "     " + b + "     " + (int)pow3);
        a++;
        b++;
        double pow4 = (Math.pow(a, b));
        System.out.println(a + "     " + b + "     " + (int)pow4);
        a++;
        b++;
        double pow5 = (Math.pow(a, b));
        System.out.println(a + "     " + b + "     " + (int)pow5);
        
        //add powers together and final output.
        double finalPow = (pow1 + pow2 + pow3 + pow4 + pow5);
        System.out.println("Final sum: " + (int)finalPow);
    }
    
}
