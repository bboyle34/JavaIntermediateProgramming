
package ch4and5;
import java.util.Scanner;

public class Ch5Notes {
    
    public static void main(String[] args) 
    {
        Scanner input = new Scanner(System.in);
        //Pre test Loops
        
//        for (int i = 0; i <= 10; i++)
//        {
//            System.out.print(i + " ");
//        }
//        System.out.println(" ");
//        int r = 0;
//        while (r <= 10)
//        {
//            System.out.print(r + " ");
//            r++;
//        }
//        System.out.println(" ");
//        //Post test Loops
//        int a = 0;
//        do
//        {
//            System.out.print(a + " ");
//            a++;
//        } while (a <= 10);
        
        //multiplication table
        // outer for loop / higher level for loop
        //how many rows
//        for (int i = 0; i <= 10; i++)
//        {
//            // inner for loop / lower level for loop
//            //columns per row
//            for (int j = 0; j <= 10; j++)
//            {
//                int num = (i*j);
//                if (num < 10)
//                {
//                 System.out.print(num + "  ");   
//                }
//                else
//                {
//                 System.out.print(num + " ");
//                }          
//            }
//            //the println makes a new row
//            System.out.println();
//        }
//        
//        //while loop
//        int count = 0;
//        while (count < 10)
//        {
//            double random = (Math.random() * 100);
//            System.out.println("Random number #" + (count) + ", the number is"
//                    + ": " + (int)(random));
//            count++;
//        }
        
        String password = ("password");
        String guess = (" ");
        do
        {
            System.out.println("Enter your password: ");
            guess = input.next();
                    
        } while (!(guess.equalsIgnoreCase(password)));

        int r = ((int)(Math.random() * 4));
        int a = ((int)(Math.random() * 4));
        int b = ((int)(Math.random() * 4));
        int c = ((int)(Math.random() * 4));
        int d = ((int)(Math.random() * 4));
        
        System.out.println(r);
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);        
        System.out.println(d);        


    }

}
