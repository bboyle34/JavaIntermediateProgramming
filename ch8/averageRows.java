
package ch8;
import java.util.Scanner;

public class averageRows 
{
    public static void main(String[] args) 
    {
        Scanner input = new Scanner(System.in);
        System.out.print("How many Rows? ");
        int n = input.nextInt();
        System.out.print("How many columns per row? ");
        int m = input.nextInt();
        int[][] array = new int[n][m];
        for (int i = 0; i < array.length; i++)
        {
            for (int j = 0; j < array[i].length; j++)
            {
                array[i][j] = (int)(Math.random() * 100);
                System.out.print("Here is a random number: " + array[i][j]);
                System.out.println("");
            }
            System.out.println("\nNext row.");
        }
        System.out.println("");
        averageRows(array);
    }
    public static void averageRows(int[][] twoDim)
    {
        double[] oneDim = new double[twoDim.length];
        for (int i = 0; i < twoDim.length; i++)
        {
            double sum = 0;
            double average = 0;
            for (int j = 0; j < twoDim[i].length; j++)
            {
                sum += twoDim[i][j];
            }
            average = (sum / twoDim[i].length);
            oneDim[i] = average;
        }
        printTwoDim(twoDim);
        System.out.println("This next table is an average per "
                + "row of the previous table");
        printTable(oneDim);
    }
    public static void printTwoDim(int[][] table)
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
    public static void printTable(double[] table)
    {
        for (int i = 0; i < table.length; i++)
        {
            System.out.println("[" + table[i] + "]");
        }
        //System.out.println("");
    }
    
}
