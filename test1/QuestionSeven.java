
package test1;
import java.util.Scanner;

public class QuestionSeven 
{
    public static void main(String[] args) 
    {
        Scanner input = new Scanner(System.in);
        
        System.out.print("Enter your number: ");
        double number = input.nextDouble();
        String answer = "[";
        
        for (int i = 1; i < 11; i++)
        {
            if ((number * 10) >= i)
                answer += "#";
            else
                answer += " ";
        }
        answer += "]";
        
        System.out.println(answer);
            
    }

}
