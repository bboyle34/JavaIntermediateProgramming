/*
Brendan Boyle
9/17/19
Section 3
The purpose of this code is to have the user input various details to a shipment
and for the output to tell that user the shipping time and cost.
*/
package Homework3;
import java.util.*;

public class Hw3 
{

    public static void main(String[] args) 
    {
        Scanner input = new Scanner(System.in);
        
        //user input height, weight, and width
        System.out.print("Enter Height, Width, and Length as # # #: ");
        double height = input.nextDouble();
        double width = input.nextDouble();
        double length = input.nextDouble();
        
        boolean loop = true;
        while (loop)
        {
            if ((height <= 0) || (width <= 0) || (length <= 0))
            {
                System.out.println("Please enter a positive number");
                loop = false;
            }
        }  
   
        
        //user inputs package weight
        System.out.print("Enter package weight: ");
        double weight = input.nextDouble();
        boolean loop2 = true;
        
        while (loop2)
        {
            if (weight <= 0)
            {
                System.out.println("Please enter a positive number");
                System.exit(0);
            }
        }
        
        //user inputs distance
        System.out.print("Please enter the distance: ");
        double distance = input.nextDouble();
        
        if (distance <= 0)
        {
            System.out.println("Please enter a positive number");
            System.exit(0);
        }
        
        //create cost, cubic, and shipping time variables
        double cost = 0.0;
        double cubic = (height * width * length);
        int shipping = 0;
        
        //box volume categorie          
        if (cubic <= .5)
        {
            cost += 5.0;
        }
        else if (cubic <= 2.0)
        {
            cost += 10.0;
        }
        else
        {
            cost += 20.0;
            shipping++;
        }
        
        //weight categories
        if (weight <= 1.0)
        {
            cost += 2.0;
        }
        else if (weight <= 5.0)
        {
            cost += 5.0;
        }
        else
        {
            cost += 10.0;
            shipping += 2;
        }
        
        //distance categories
        if (distance <= 100.0)
        {
            cost += 5.0;
            shipping++;
        }
        else if (distance <= 1000.0)
        {
            cost += 15.0;
            shipping += 3;
        }
        else
        {
            cost += 25.0;
            shipping += 5;
        }
        
        switch(shipping)
        {
            case 8: System.out.println("\nYou have hit the maximum shipping time"
                    + "of 8 days.\n");
                break;
            default:
                break;
        }
        
        //final output        
        System.out.printf("Package with volume %.2f cubic feet, "
                + "weight of %.1f lbs and travel distance "
                        + "of %.1f miles\n will cost $%.2f "
                + "to ship, transit time: %d days.\n", 
                cubic, weight, distance, cost, shipping);
        
    }

}
