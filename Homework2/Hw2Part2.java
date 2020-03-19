
package Homework2;
import java.util.Scanner;
/*
Brendan Boyle
CIS 331 Section 3
9/7/19
*/
public class Hw2Part2 
{
    public static void main(String[] args) 
    {
        //input new Scanner class
        Scanner input = new Scanner(System.in);
        
        //setup table and get investment, rate, and year inputs
        System.out.println("==== Calculate Investment Value ====");
        System.out.print("Enter Investment: ");
        double invest = input.nextDouble();
        System.out.print("Enter Interest Rate as Percentage: ");
        double yearRate = input.nextDouble();
        System.out.print("Enter Number of Years: ");
        int years = input.nextInt();
        
        //build formula for future rate
        //break down yearly rate into monthly
        //turn rate into a percentage
        double yearRate2 = (yearRate / 100);
        double monthRate = (yearRate2 / 12);
        monthRate++;
        double future = (invest * (Math.pow(monthRate, years * 12)));
        
        //output and use printf to format into 2 decimal places
        System.out.printf("Accumulated Value: $%4.2f\n", future);
        
    }
    
}
