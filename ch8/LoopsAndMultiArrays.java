
package ch8;

public class LoopsAndMultiArrays 
{
    public static void main(String[] args) 
    {
        int[][] table = new int[10][10];        
        //table.length tell us how many rows we have
        for (int i = 0; i < table.length; i++)
        {
            //table[i].length gives us length of each column at each row i
            for (int j = 0; j < table[i].length; j++)
            {
                table[i][j] = i * j;
            }
        }              
        int[][] array = new int[10][10];
        int count = 0;
        for (int i = 0; i < array.length; i++)
        {
            for (int j = 0; j < array[i].length; j++)
            {
                array[i][j] = count++;
            }
        }
        for (int i = 0; i < table.length; i++)
        {
            //table[i].length gives us length of each column at each row i
            for (int j = 0; j < table[i].length; j++)
            {
                if (table[i][j] < 10)
                    System.out.print("[0" + table[i][j] + "]");
                else
                    System.out.print("[" + table[i][j] + "]");
            }
            System.out.println("");
        }
        System.out.println("");
        for (int i = 0; i < array.length; i++)
        {
            //table[i].length gives us length of each column at each row i
            for (int j = 0; j < array[i].length; j++)
            {
                if (array[i][j] < 10)
                    System.out.print("[0" + array[i][j] + "]");
                else
                    System.out.print("[" + array[i][j] + "]");
            }
            System.out.println("");
        }
        
        
        
    }
    
}
