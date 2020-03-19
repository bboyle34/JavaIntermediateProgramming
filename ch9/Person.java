
package ch9;
import java.util.Scanner;

public class Person 
{
    //Blueprint for our future instance objects
    //Class Definition File (CDF)
    
    // Data Fields - variables that contain values of the characteristics 
    // of each instance object and they are stored within the 
    // instance objects themselves.
    public String name;
    public double height;
    public String eyeColor;
    public int age;
    public String hairColor;
    private double salary;
    // private hides the data field from other java files
    // SSN would be a good example of a private variable
    public int ID;
    public int[] favoriteNumbers;
    private static int nextID = 100;
    /*
    Static Data Field:
    a data field of a class that is not tied to any one instance object in 
    particular. there will only ever be one single copy of each static data
    field in a class. because it is standalone, its life san is not affected
    by the creation and garbage collection of instanct objects
    */
    
    // Constructors - special methods that are called/invoked once and only
    // once per newly created instance object. Their job is to initialize
    // the newly creted instance object. This puts data inside the objects
    // and gets them ready for their first usage.
    /*
    Rules for Constructors:
    1. Must have the same name as the class
    2. They will hace NO return type at all, not even "void"
    3. You should have at least one Constructor defined in your Class
    4. You can have more than one Constructor in your Class, as long as you
    follow the chapter 6 rules for method overloading:
        a. the new versions must differ in the order, quantity, and/or
        the types of parameters
    */
    //  Zero argument Constructor
    public Person()
    {
        this.name = "";
        this.height = 0;
        this.eyeColor = "";
        this.age = 0;
        this.hairColor = "";
        this.salary = 0;
        this.ID = nextID++;
        this.favoriteNumbers = new int[3];
    }
    // Full Constructor
    public Person(String name, double height, String eyeColor, int age, 
            String hairColor, double salary)
    {
        this.name = name;
        this.height = height;
        this.eyeColor = eyeColor;
        this.age = age;
        this.hairColor = hairColor;
        this.salary = salary;
        this.ID = nextID++;
    }    
    
    // Member Methods - The actions that are performable upon instance objects
    // and that are performable by the instance objects.
    Scanner in = new Scanner(System.in);
    public double heightInFeet()
    {
        this.height /= 12;
        return this.height;
    }
    public void displayInformation()
    {
       System.out.println("Personal Identifiable Information \nName: " + this.name + "\n"
                + "Height: " + this.height + "\nEye Color: " + this.eyeColor
                + "\nAge: " + this.age + "\nHair Color: " + this.hairColor 
                + "\nSalary: " + this.salary + "\nID Number: " + this.ID
                + "\n"); 
    }
    public void createPerson()
    {
        System.out.print("Would you like to create a person? ");
        String answer = in.next();
        if (answer.equalsIgnoreCase("yes"))
        {
            System.out.print("What is the name of your person? ");
            this.name = in.next();
            System.out.print("How tall is your person? ");
            this.height = in.nextDouble();
            heightInFeet();
            System.out.print("What color eyes does your person have? ");
            this.eyeColor = in.next();
            System.out.print("How old is your person? ");
            this.age = in.nextInt();
            System.out.print("What color hair does your person have? ");
            this.hairColor = in.next();
            System.out.print("What is your salary? ");
            this.salary = in.nextDouble();
            System.out.println("");
        }
    }
    public double getSalary()
    {
        return this.salary; 
    }
    public void setSalary(double salary)
    {
        Scanner in = new Scanner(System.in);
        while (salary < 0 || salary >= 500000)
        {
            System.out.print("Please enter a valid salary between 0 and 500000: ");
            salary = in.nextDouble();
        }
        this.salary = salary;
    }
    
    // Static Member Method
    public static int getNextID()
    {
        
        return nextID;
    }
}
