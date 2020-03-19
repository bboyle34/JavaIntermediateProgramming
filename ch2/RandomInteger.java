
package ch2;
import java.util.Scanner;

public class RandomInteger 
{
    public static void main(String[] args) 
    {
        Scanner input = new Scanner(System.in);
        
        String play = "y";        
        do
        {
            System.out.print("Would you like to play a game? (y/n) ");
            play = input.next();
            if (play.equalsIgnoreCase("y"))
                game();
                System.out.println("Congrats, you won!");
        } while (play.equalsIgnoreCase("y"));
        System.out.println("Fuck off");
    }
    public static void game()
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter two numbers: ");
        int num1 = in.nextInt();
        int num2 = in.nextInt();
        if (num1 > num2)
        {
            int temp = num1;
            num1 = num2;
            num2 = temp;
        }
        System.out.println("Getting a random number . . . Please wait . . .");
        int answer = randomNumber(num1, num2);
        int guess;
        do
        {
            System.out.print("Guess the number between " + num1 + " and " + num2 + ": ");
            guess = in.nextInt();
            if  (guess != answer)
                System.out.println("NOPE! Try again dipshit.");
        } while (guess != answer);        
    }
    
    public static int randomNumber(int num1, int num2)
    {
        int num3 = (num2 - num1) + 1;
        int answer = (int)(num1 + Math.random() * num3);
        return answer;
    }

}
