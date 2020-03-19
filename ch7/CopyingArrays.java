
package ch7;

public class CopyingArrays 
{
    public static void main(String[] args) 
    {
        //Arrays - copying
        int[] array1 = {2, 3, 4};
        int[] array2 = new int[3]; // this is empty
        //copy array1 into array2
        
        for (int i = 0; i < array1.length; i++)
        {
            array2[i] = array1[i];
        }
        
        System.out.println();
    }
    
}
