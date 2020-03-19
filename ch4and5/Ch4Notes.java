
package ch4and5;
import java.util.Scanner;

public class Ch4Notes {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
//        System.out.println("Input a new variable: ");
//        int number = input.nextInt();
//        
//        int i;
//        i = 3;
//        while (i > 0)
//        {
//            System.out.println("You number is " + number);
//            number ++;
//            System.out.println("Let's add one to your number: " + number);
//            i--;
//            System.out.println("The countdown timer: " + i);
//        }

//        int var = 3;
//        var = var-- + 1;
//        System.out.println(var);
        
//        //pages 55-56
//        int var = 1;
//        int var2 = 0;
//        var2 = var++ + var++ + 1;
//        System.out.println(var2);
        
        String nameOne = "Welcome to Java";
        String nameTwo = "Lets take Database";
        
        System.out.println(nameOne.equalsIgnoreCase(nameTwo));
        
        System.out.println(nameOne.indexOf(" "));
        System.out.println(nameTwo.indexOf(" "));
        
        System.out.println(nameOne.indexOf(" ", nameOne.indexOf(" ") + 1));
        
        //get the rest of the string after your first space
        System.out.println(nameOne.substring(nameOne.indexOf(" ") + 1));
        
        //get the rest of the string after your second space
        System.out.println(nameOne.substring((nameOne.indexOf(" ", nameOne.indexOf(" ") + 1))));
        
        //this will give me 4 characters
        System.out.println(nameOne.substring(4, 8));
        
        //this printline will output the middle word in a three word String
        System.out.println(nameTwo.substring(nameTwo.indexOf(" ") + 1, nameTwo.indexOf(" ", nameTwo.indexOf(" ") + 1)));
        
        //formatting strings
        
        
    }

}
