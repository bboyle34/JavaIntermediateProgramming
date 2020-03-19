
package ch11;

public class BikeApplication 
{

    public static void main(String[] args) 
    {
        MountainBike mb = new MountainBike(3, 100, 25);
        mb.speedUp(50);
        mb.applyBrake(20);
        System.out.println(mb.riding());
        // mb has access to gear, speed, and seat height
        System.out.println(mb.toString());
         
        print();
        Bicycle mine = new MountainBike(1, 2, 3);
        mine.speedUp(50);
        System.out.println(mine.riding());
        // mine only has access to gear and speed, not seat height
        System.out.println(mine.toString());
        
        print();
        Bicycle regular = new Bicycle(4, 5);
        regular.speedUp(50);
        System.out.println(regular.riding());
        System.out.println(regular.toString());
        
        print();
    }
    public static void print()
    {
        System.out.println("");
    }
    
}
