
package ch11;

public class Bicycle 
{
    public int gear;
    public int speed;
    
    public Bicycle(int gear, int speed)
    {
        this.gear = gear;
        this.speed = speed;
    }
    
    public void applyBrake(int decrement)
    {
        this.speed -= decrement;
        System.out.println("This regular bike has slowed down");
    }
    public void speedUp(int increment)
    {
        this.speed += increment;
        System.out.println("The regular bike has sped up");
    }
    public String toString()
    {
        return ("No of gears are " + this.gear + "\nSpeed of bicycle is " + this.speed);
    }
    public String riding()
    {
        String answer = "";
        answer += ("I am riding a regular bike");
        return answer;
    }
}
///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////
class MountainBike extends Bicycle
{
    public int seatHeight;
    
    public MountainBike(int gear, int speed, int startHeight)
    {
        super(gear, speed);
        seatHeight = startHeight;
    }
    
    public void setHeight(int newValue)
    {
        this.seatHeight = newValue;
        System.out.println("The Mountain Bike has a new seat height");
    }
    public void speedUp(int increment)
    {
        this.speed += increment;
        System.out.println("The Mountain Bike has sped up");
    }
    public String toString()
    {
        return (super.toString() + "\nSeat height is " + this.seatHeight);
    }
    public String riding()
    {
        String answer = "";
        answer += (super.riding() + " And my bike is a Mountain Bike");
        return answer;
    }
}
