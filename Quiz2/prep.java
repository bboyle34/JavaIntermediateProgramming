
package Quiz2;

public class prep 
{

    public static void main(String[] args) 
    {
        //given 1d array, values in the array become the length of columns in the 2d array
        
        int[][] matrix = new int[5][];
        matrix[0] = new int[10];
        matrix[1] = new int[4]; 
        
        
        
        
        int[] array1 = {3, 4, 5, 6, 1, 10};
        int[][] table = new int[array1.length][];
        
        for (int i = 0; i < table.length; i++)
        {
            table[i] = new int[array1[i]];
        }
        print(table);

    }
    public static void print(int[][] array)
    {
        for (int i = 0; i< array.length; i++)
        {
            for (int j = 0; j < array[i].length; j++)
                System.out.print(array[i][j] + " ");
            System.out.println("");
        }
    }
    
}
