
package ch9;

public class CoffeeCup 
{
    // Data Fields
    public double capacity;
    public String cupColor;
    public double currentAmount;
    
    // Constructors
    public CoffeeCup()
    {
        // Deafult Constructor
        //set the deafult values(capacity and color) of the cup
        this.capacity = 8.0;
        this.cupColor = "JMU Purple";
        this.currentAmount = 0.0;
    }
    
    public CoffeeCup(int capacity, String cupColor)
    {
        // Full Constructor
        //allow us the set the color and capacity of the cup
        this.capacity = capacity;
        this.cupColor = cupColor;
        this.currentAmount = 0.0;
    }
    
    //Methods
    public void fill()
    {
        //fill the cup
        this.currentAmount = (capacity * 1.0);
    }
    
    public void empty()
    {
        //empty the cup
        this.currentAmount = 0.0;
    }
    
    public void empty(int percentage)
    {
        //empty the cup a certain amount
        double amountToRemove = capacity * (percentage / 100.0);
        this.currentAmount = capacity - amountToRemove;
    }
    
}
