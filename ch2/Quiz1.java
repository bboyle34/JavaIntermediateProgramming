
package ch2;


public class Quiz1 
{

    public static void main(String[] args) 
    {
        int number = 0;
        double value = 5;
        number = (int)value/3;
        value += 6;
        value += 10;
        System.out.println("The result is: " + value);
        
        number = 0;
        number += 5%3;
        number += 9%7;
        number += 6/5;
        number -= Math.pow(3,3);
        System.out.println("Sum: " + number);
        
        


    }

}
