
package ch7;
import java.util.Scanner;

public class reverseArray 
{
    public static void main(String[] args) 
    {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter how long you want your array to be: ");
        int n = input.nextInt();
        int[] original = new int[n];
        for (int i = 0; i < n; i++)
        {
            original[i] = (int)(Math.random() * 100);
            System.out.println("Integer for array[" + i + "] is " + original[i]);
            
        }
        System.out.print("Original Array: ");
        printArray(original);
        System.out.println("");
        System.out.print("Reversed Array: ");
        reverseArray(original);
        System.out.println("");
    }
    public static void reverseArray(int[] array)
    {
        int[] reversed = new int[array.length];
        for (int i = 0; i < array.length; i++)
        {
            reversed[i] = array[(array.length - 1) - i];
        }
        printArray(reversed);
    }
    public static void printArray(int[] array)
    {
        for (int e: array)
        {
            System.out.print("[" + e + "]");
        }
//        for (int i = 0; i < array.length; i++)
//            System.out.print("[" + array[i] + "]");
    }
    
}