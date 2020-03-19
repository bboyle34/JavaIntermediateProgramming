/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch3;
import java.util.Scanner;
/**
 *
 * @author Brendan Boyle
 */
public class Ch3Class 
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        Scanner input = new Scanner(System.in);
        
        int myAge = 20;
        int theirAge = 30;
        
        System.out.println(myAge >= theirAge);
        
        boolean ageCompare = (myAge == theirAge);
        
        System.out.println(ageCompare);
        
//        theirAge = 101;
//        if (theirAge > 100)
//        {
//            if (myAge > 18) 
//            {
//                System.out.println("Ok now I can buy some shite");
//            }
//        }

        if (myAge <= 13)
        {
            System.out.println("Child");
        }
        else if (myAge <= 18)
        {
            System.out.println("Teenager");
        }
        else if (myAge <= 20)
        {
            System.out.println("Adult");
        }
        else
        {
            System.out.println("Woohoo");
        }
        
        
        if ((myAge <= 18) && (myAge >= 13))
        {
            System.out.println("my age is in between 18 and 13");
        }
        else if ((myAge <= 20) && (myAge > 18))
        {
            System.out.println("adult");
        }
        else
        {
            System.out.println("idk");
        }
        
        
        // swtich segments
        System.out.print("insert a new age: ");
        myAge = input.nextInt();
        switch (myAge)
        {
            case 18:    System.out.println("you can buy tobacco");
                        theirAge = 41;
                        break;
            case 21:    System.out.println("you can buy alcohol");
                        break;
            default:    System.out.println("fuck off");
        }
        
        
        //conditional expression
        
        int amount = 0;
        amount = (myAge <= 65) ? 6500 : 100000;
        System.out.println("the amount is " + amount);
        
    }
    
}
