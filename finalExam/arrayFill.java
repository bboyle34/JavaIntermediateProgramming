
package finalExam;

public class arrayFill 
{
    public static void main(String[] args) 
    {
        arrayFill(new int[10][10]);
        System.out.println("");
        arrayFill(new int[4][4]);
        System.out.println("");
        arrayFill(new int[5][5]);
    }
    public static void arrayFill(int[][] table)
    {
        int count = -1;
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
                //this counts down from 99 down each column
                table[j][i] = count--;
                //table[i][j] =  count--;
                //this would count down from 99 down each row
            }
        }
        for (int i = 0; i < table.length; i++)
        {
            for (int j = 0; j < table[i].length; j++)
            {
                System.out.print("[" + table[i][j] + "]");
            }
            System.out.println();
        }
    }
}
