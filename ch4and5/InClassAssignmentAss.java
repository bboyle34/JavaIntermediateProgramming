
package ch4and5;
import java.util.Scanner;

public class InClassAssignmentAss 
{
    public static int gameNumber = 1;

    public static void main(String[] args) 
    {
      numberGame();
      numberGame();
      numberGame();
      
      System.out.println("Game Over!\n");
    }
    
    public static void numberGame()
    {
       Scanner input = new Scanner(System.in);
       //
       //
       //The Number Game
       int number1 = ((int)(Math.random() * 10));
       int number2 = ((int)(Math.random() * 10));
       int answer = 0;
       int guess;
       int guessCount = 0;
       char operator;
       int a = ((int)(Math.random() * 4));
       
       switch(a)
       {
            case 0: operator = '+';
                answer = number1 + number2;
                break;
            case 1: operator = '-';
                answer = number1 - number2;
                break;
            case 2: operator = '*';
                answer = number1 * number2;
                break;
            case 3: operator = '/';
                answer = number1 / number2;
                break;
            default: operator = '?';
       }
              
       do
       {
           System.out.print("What is " + number1 + " " + 
                   operator + " " + number2 + "? ");
           guess = input.nextInt();
           if (guess != answer)
           {
               System.out.println("Incorrect, try again!");
               guessCount++;
               if (guessCount >= 3)
               {
                   System.out.println("You lose Game #" + gameNumber + "\n");
                   System.out.println("Game Over!\n");
                   System.exit(0);
               }
           }
           
       } while (guess != answer);
       System.out.println("You Win Game #" + gameNumber++ + "\n");
    }
}