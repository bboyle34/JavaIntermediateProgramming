/*
Brendan Boyle
Calista Ramadhanty
Travis Cannella
Section 03
Purpose: Allow user to enter any size matrix and calculate the sum of 
each column and each row.
*/
package ch8;
import java.util.Scanner;

public class SumColumn 
{
    public static void main(String[] args) 
    {
        /*
        method will accept matrix of any size
        print sum of all columns
        print sum of all rows
        public static void
        leave out column index
        */
       Scanner input = new Scanner(System.in);
       System.out.print("Enter the number of rows and columns of the array: ");
       int rows = input.nextInt();
       int columns = input.nextInt();
       double[][] array = new double[rows][columns];
       System.out.println("Enter the array: ");
       for (int i = 0; i < array.length; i++)
       {
           for (int j = 0; j < array[i].length; j++)
           {
               array[i][j] = input.nextDouble();
           }
       }
       System.out.println("");
       printTable(array);
       System.out.println("");
       sumMatrix(array);
    }
    public static void sumMatrix(double[][] array)
    {
        //rows sums
        for (int i = 0; i < array.length; i++)
        {
            double rowSum = 0.0;
            for (int j = 0; j < array[i].length; j++)
            {
                rowSum += array[i][j];
            }
            System.out.println("Sum of elements at row " + i + " is " + rowSum);                
        }
        System.out.println("");
        //column sums
        boolean operator = true;
        int var = 0;
        while (operator)
        {
            double columnSum = 0.0;
            for (int i = 0; i < array.length; i++)
            {
                for (int j = 0; j < array[i].length; j++)
                {
                    if (var > (array[i].length - 1))
                        operator = false;
                    else if (var == j)
                        columnSum += array[i][j];
                }
            }
            if (operator != false)
                System.out.println("Sum of elements at column " + var + " is " + columnSum);
            var++;
        }
    }
    public static void printTable(double[][] array)
    {
        for (int i = 0; i < array.length; i++)
        {
            for (int j = 0; j < array[i].length; j++)
            {
                System.out.print("[" + array[i][j] + "]");
            }
            System.out.println("");
        }
    }        
}
