
package test1;
import java.util.Scanner;

public class errorCheck 
{
    public static void main(String[] args) 
    {
        Scanner in = new Scanner(System.in);
//        String phrase = "abcdefghijklmnop";
//        for (int i = 0; i <= phrase.length(); i++)
//            System.out.println(phrase.substring(i, i + 2));
        
        System.out.println("Enter an age: ");
        int age = in.nextInt();
        int level = 0;
        if ((age < 10) && (age > 0))
            level = 1;
        else if ((age > 10) && (age < 20))
            level = 2;
        else if ((age > 20) && (age < 30))
            level = 3;
        else
            level = 4;
        System.out.println(level);
        
    }

}
