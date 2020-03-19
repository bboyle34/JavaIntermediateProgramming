
package ch9;
import java.util.Scanner;

public class Car 
{
    Scanner input = new Scanner(System.in);
    
    // Data Fields
    public String carColor;
    public double mileage;
    public String licensePlate;
    public int wheels;

    // Constructors
    public Car()
    {
        this.carColor = "Black";
        this.licensePlate = " ";
        this.mileage = 0.0;
        this.wheels = 4;
    }
    
    public Car(String carColor, double mileage, String licensePlate, int wheels)
    {
        this.carColor = carColor;
        this.licensePlate = licensePlate;
        this.mileage = mileage;
        this.wheels = wheels;
    }
    
    // Methods
    public void paintCar()
    {
        System.out.print("Enter your new car color: ");
        this.carColor = input.next();
        System.out.println("Your new Car's color is " + this.carColor);
    }
    
    public void driveCar()
    {
        String operator = "Yes";
        do
        {
            System.out.print("Would you like to go for a drive? ");
            operator = input.next();
            if (operator.equalsIgnoreCase("Yes"))
            {
                System.out.print("How many miles did you drive? ");
                this.mileage += input.nextDouble();
            }
            
        } while (operator.equalsIgnoreCase("Yes"));
        System.out.println("You have driven a total of " + this.mileage + ""
                + " miles");
    }
    
    public void orderLicense()
    {
        System.out.print("What is your new license plate: ");
        this.licensePlate = input.next();
        System.out.println("Your new license plate is " + this.licensePlate);
    }
}
