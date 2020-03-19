
package ch10;
// Class Definition File (CDF)

public class Automobile 
{
    // Data Fields
    private int ID;
    private String brand;
    private double weight;
    public double cost;
    public Owner theOwner;
    public Insurance theInsurance;
    
    public static int nextID = 0;

    // Constructors
    public Automobile()
    {
        this.ID = nextID++;
        this.brand = "";
        this.weight = 0.0;
        this.cost = 0.0;
        this.theOwner = new Owner("", 0);
    }
    public Automobile(String brand, double weight, double cost)
    {
        this.ID = nextID++;
        this.brand = brand;
        this.weight = weight;
        this.cost = cost;
    }
    public Automobile(String brand, double weight, double cost, Owner theOwner)
    {
        this.ID = nextID++;
        this.brand = brand;
        this.weight = weight;
        this.cost = cost;
        this.theOwner = theOwner;
    }

    // Methods
    public void setBrand(String brand)
    {
        this.brand = brand;
    }
    public void setWeight(double weight)
    {
        this.weight = weight;
    }
    public void setCost(double cost)
    {
        this.cost = cost;
    }
    public String getBrand()
    {
        return this.brand;
    }
    public double getWeight()
    {
        return this.weight;
    }
    public String driveVehicle(int numberOfMiles)
    {
        String answer = "";
        answer += "The vehicle was driven " + numberOfMiles + " miles";
        return answer;
    }
    public String honkHorn(int numberOfTimes)
    {
        String answer = "";
        answer += "The horn was honked " + numberOfTimes + " times";
        return answer;
    }
    public String displayInformation()
    {
        String answer = "";
        answer += ("Car ID: " + this.ID + "\nBrand: " + this.brand 
                + "\nWeight: " + this.weight + "\nCost: $" + this.cost 
                + "\nOwner\n\t" + this.theOwner.displayInformation() 
                + "\nInsurance\n" + this.theInsurance.displayInformation());
        return answer;
    }
    public static int countAutomobiles()
    {
        int count = 0;        
        for (int i = 0; i < nextID; i++)
            count++;
        return count;
    }
}
