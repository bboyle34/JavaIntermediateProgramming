
package ch8;

public class Ch8Notes 
{
    public static void main(String[] args) 
    {
        //ragged arrays are uneven arrays
        int[][] ragged = new int[2][];
        int[][] array = new int [10][10];
        for (int i = 0; i < array.length; i++)
        {
            for (int j = 0; j < array[i].length; j++)
            {
                array[i][j] = i*j;
            }
        }
        //this array will have 4 rows but have undetermined column lengths
        printTable(doubleTable(array));
    }
    public static void printArray(int[][] array)
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
    public static int[][] doubleTable(int[][] array)
    {
        for (int i = 0; i < array.length; i++)
        {
            for (int j = 0; j < array[i].length; j++)
            {
                array[i][j] *= 2;
            }
        }
        return array;
    }
    public static void printTable(int[][] array)
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
