//8.13
package ch8;
import java.util.Scanner;

public class LocateLargestInteger 
{
    public static void main(String[] args) 
    {
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
       printTable(array);
       System.out.println("The location of the largest element is at "
               + "(" + locateLargest(array)[0] + ", " + locateLargest(array)[1] + ")");        
    }
    public static int[] locateLargest(double[][] a)
    {
       double largest = 0.0;
       int row = 0;
       int column = 0;
       for (int i = 0; i < a.length; i ++)
       {
           for (int j = 0; j < a[i].length; j++)
           {
               //double number = a[i][j];
               if (a[i][j] >= largest)
               {
                   largest = a[i][j];
                   row = i;
                   column = j;
               }
           }
       }
       int[] answer = {row, column};
       return answer;
    }
    public static void printTable(double[][] table)
    {
        for (int i = 0; i < table.length; i++)
        {
            for (int j = 0; j < table[i].length; j++)
            {
                System.out.print("[" + table[i][j] + "]");
            }
            System.out.println("");
        }
    }

}
