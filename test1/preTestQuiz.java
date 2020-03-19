
package test1;
import java.util.Scanner;

public class preTestQuiz 
{
    public static void main(String[] args) 
    {
        Scanner input = new Scanner(System.in);
        for (int i = 0; i < 10; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                if (i == 0)
                {
                    System.out.print(j + " ");
                }
                else if (i == 9)
                {
                    System.out.print(j + " ");
                }
                else if ((9 - j) == i)
                {
                    System.out.print(j + " ");
                }
                else if (j == 0)
                {
                    System.out.print("0 ");
                }
                else if (j == 9)
                {
                    System.out.print("9 ");
                }
                else
                {
                    System.out.print("  ");
                }
            }
            System.out.println("");
        }
        System.out.println("");
        System.out.println("Enter a number for rows and a number for columns: ");
        int rows = input.nextInt();
        int cols = input.nextInt();
        printMatrix(rows, cols);        
    }
    
    public static void printMatrix(int rows, int cols)
    {
        for (int x = 0; x < rows; x++)
        {
            for (int y = 0; y < cols; y++)
            {
                double random = (Math.random() * 100);
                if (((int)(random) % 2) == 0)
                {
                    System.out.print("0 ");
                }
                else
                {
                    System.out.print("1 ");
                }
            }
            System.out.println("");
        }
    }

}
