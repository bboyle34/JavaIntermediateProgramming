
package test2;

public class ArrayFill 
{

    public static void main(String[] args) 
    {
        arrayFill(new int[10][10]);
        System.out.println("");
        arrayFill(new int[4][4]);
        System.out.println("");
        arrayFill(new int[3][3]);
        //arrayFill(new int[4][5]);
    }
    public static void arrayFill(int[][] table)
    {
        int count = 0;
        for (int i = 0; i < table.length; i++)
        {
            for (int j = 0; j < table[i].length; j++)
            {
                count++;
            }
        }
        for (int i = 0; i < table.length; i++)
        {
            for (int j = 0; j < table[i].length; j++)
            {
                table[j][i] = --count;
            }
        }
        for (int i = 0; i < table.length; i++)
        {
            for (int j = 0; j < table[i].length; j++)
            {
                if (table[i][j] < 10)
                    System.out.print("[0" + table[i][j] + "]");
                else
                    System.out.print("[" + table[i][j] + "]");
            }
            System.out.println("");
        }
    }
    
}
