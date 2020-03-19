
package ch4and5;
import java.util.Scanner;

public class InClassAssignment 
{

    public static void main(String[] args) 
    {
       Scanner input = new Scanner(System.in);
       //
       //
       //The (A) First Game
       int numberA1 = ((int)(Math.random() * 10));
       int numberA2 = ((int)(Math.random() * 10));
       int answerA = 0;
       int guessA;
       int guessCountA = 0;
       char operatorA;
       int a = ((int)(Math.random() * 4));
       
       switch(a)
       {
            case 0: operatorA = '+';
                answerA = numberA1 + numberA2;
                break;
            case 1: operatorA = '-';
                answerA = numberA1 - numberA2;
                break;
            case 2: operatorA = '*';
                answerA = numberA1 * numberA2;
                break;
            case 3: operatorA = '/';
                answerA = numberA1 / numberA2;
                break;
            default: operatorA = '?';
       }
              
       do
       {
           System.out.print("What is " + numberA1 + " " + 
                   operatorA + " " + numberA2 + "? ");
           guessA = input.nextInt();
           if (guessA != answerA)
           {
               System.out.println("Incorrect, try again!");
               guessCountA++;
               if (guessCountA >= 3)
               {
                   System.out.println("You lose Game #1\n");
                   System.out.println("Game Over!\n");
                   System.exit(0);
               }
           }
           
       } while (guessA != answerA);
       System.out.println("You Win Game #1\n");
       //
       //
       //The (B) Second Game
       int numberB1 = ((int)(Math.random() * 10));
       int numberB2 = ((int)(Math.random() * 10));
       int answerB = 0;
       int guessB;
       int guessCountB = 0;
       char operatorB;
       int b = ((int)(Math.random() * 4));
       
       switch(b)
       {
            case 0: operatorB = '+';
                answerB = numberB1 + numberB2;
                break;
            case 1: operatorB = '-';
                answerB = numberB1 - numberB2;
                break;
            case 2: operatorB = '*';
                answerB = numberB1 * numberB2;
                break;
            case 3: operatorB = '/';
                answerB = numberB1 / numberB2;
                break;
            default: operatorB = '?';
       }
              
       do
       {
           System.out.print("What is " + numberB1 + " " + 
                   operatorB + " " +  numberB2 + "? ");
           guessB = input.nextInt();
           if (guessB != answerB)
           {
               System.out.println("Incorrect, try again!");
               guessCountB++;
               if (guessCountB >= 3)
               {
                   System.out.println("You lose Game #2\n");
                   System.out.println("Game Over!\n");
                   System.exit(0);
               }
           }
           
       } while (guessB != answerB);
       System.out.println("You Win Game #2\n");
       //
       //
       //The (C) Third Game
       int numberC1 = ((int)(Math.random() * 10));
       int numberC2 = ((int)(Math.random() * 10));
       int answerC = (numberC1 + numberC2);
       int guessC;
       int guessCountC = 0;
       char operatorC;
       int c = ((int)(Math.random() * 4));
       
       switch(c)
       {
            case 0: operatorC = '+';
                answerC = numberC1 + numberC2;
                break;
            case 1: operatorC = '-';
                answerC = numberC1 - numberC2;
                break;
            case 2: operatorC = '*';
                answerC = numberC1 * numberC2;
                break;
            case 3: operatorC = '/';
                answerC = numberC1 / numberC2;
                break;
            default: operatorC = '?';
       }
              
       do
       {
           System.out.print("What is " + numberC1 + " " 
                   + operatorC + " " + numberC2 + "? ");
           guessC = input.nextInt();
           if (guessC != answerC)
           {
               System.out.println("Incorrect, try again!");
               guessCountC++;
               if (guessCountC >= 3)
               {
                   System.out.println("You lose Game #3\n");
                   System.out.println("Game Over!\n");
                   System.exit(0);
               }
           }
           
       } while (guessC != answerC);
       System.out.println("You Win Game #3\n");
       System.out.println("You Win the Game!\n");
    }

}
