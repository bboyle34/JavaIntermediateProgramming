
package ch7;

public class ArrayMethods 
{
    public static void main(String[] args) 
    {
        //{ass by value - copy
        //pass by reference - copy of a reference to an object that is on the heap
        
        int[] numberArray = new int[5];
        for (int i = 0; i < numberArray.length; i++)
        {
            numberArray[i] = i;
        }
        printArray(numberArray);
        doubleArrayValue(numberArray);
        System.out.println("");
        printArray(numberArray);
        
    }
    //Class level
    public static void doubleArrayValue(int[] array)
    {
        for (int i = 0; i < array.length; i++)
        {
            array[i] = array[i] * 2;
            //array[i] *= 2;
        }
        //no return - void return type
    }
    public static void printArray(int[] array)
    {
        for (int e: array)
        {
            System.out.print(e + " ");
        }
    }
    
}
