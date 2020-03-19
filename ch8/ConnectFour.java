
package ch8;
import java.util.Scanner;

public class ConnectFour 
{
    public static void main(String[] args) 
    {
        String[][] table = new String[6][7];
        fillTable(table);
        boolean operator = true;
        while (operator)
        {
            printTable(table);
            makeMove(table);
            printTable(table);
        }
    }
    public static void fillTable(String[][] table)
    {
        for (int i = 0; i < table.length; i++)
        {
            for (int j = 0; j < table[i].length; j++)
            {
                table[i][j] = " ";
            }
        }
    }
    public static void printTable(String[][] table)
    {
        for (int i = 0; i < table.length; i++)
        {
            System.out.print("|");
            for (int j = 0; j < table[i].length; j++)
            {
                System.out.print(table[i][j] + "|");
            }
            System.out.println("");
        }
        System.out.println("—————————");
    }
    public static void makeMove(String[][] table)
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Player X, Make a move. (0-5) ");
        int Xmove = in.nextInt();
        boolean operator1 = false;
        
        for (int i = 0; i < table.length; i++)
        {
            for (int j = 0; j < table[i].length; j++)
            {
                if (table[Xmove][j].equalsIgnoreCase(" "))
                {
                    table[Xmove][j] = "X";
                    operator1 = true;
                    break;
                }
                if (operator1)
                    break;
            }
            if (operator1)
                break;
        }
        
        System.out.print("Player Y, Make a move. (0-5) ");
        int Ymove = in.nextInt();
        boolean operator2 = false;
        
        for (int i = 0; i < table.length; i++)
        {
            for (int j = 0; j < table[i].length; j++)
            {
                if (table[Ymove][j].equalsIgnoreCase(" "))
                {
                    table[Ymove][j] = "Y";
                    operator2 = true;
                    break;
                }
                if (operator2)
                    break;
            }
            if (operator2)
                break;
        }
    }

}
