
package finalExam;

public class raggedArray 
{
    public static void main(String[] args) 
    {
        int[][] ragged = new int[12][];
        for (int i = 0; i < ragged.length; i++)
        {
            ragged[i] = new int[i];
            for (int j = 0; j < ragged[i].length; j++)
            {
                ragged[i][j] = j;
            }
        }
        printArray(ragged);       
    }
    public static void printArray(int[][] array)
    {
        for (int i = 0; i < array.length; i++)
        {
            for (int j = 0; j < array[i].length; j++)
            {
                System.out.print("[" + array[i][j] + "]");
            }
            System.out.println();
        }
    }

}
