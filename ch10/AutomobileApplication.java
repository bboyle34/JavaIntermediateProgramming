
package ch10;

public class AutomobileApplication 
{
    public static void main(String[] args) 
    {
//        Automobile firstCar = new Automobile();
//        Automobile secondCar = new Automobile("Toyota", 3000.0, 5000.0);
//        firstCar.cost = 4500.0;
//        firstCar.setBrand("Ford");
//        firstCar.setWeight(5000.0);
//        System.out.println(firstCar.driveVehicle(120));
//        System.out.println(firstCar.honkHorn(4));
//        
//        Automobile aCar = new Automobile();
//        changeCar(aCar);
//        System.out.println("Car cost: " + aCar.cost);
//        System.out.println("Car Brand: " + aCar.getBrand());
//        System.out.println("Car Weight: " + aCar.getWeight());
//        
//        Automobile oneCar = new Automobile();
//        Automobile twoCar = new Automobile("Chevy", 1000.0, 2000.0);
//        oneCar = secondCar;
//        Automobile[] refs = new Automobile[2];        
//        for (int i = 0; i < 2; i++)
//        {
//            refs[i] = new Automobile();
//        }
        
        Automobile ownedCar = new Automobile();
        Owner carOwner = new Owner("Bob", 2019);
        ownedCar.setBrand("Tesla");
        ownedCar.setWeight(50.0);
        ownedCar.setCost(10.0);
        ownedCar.theOwner = carOwner;
        Owner insuranceHolder = new Owner("Suzy's Parents", 2010);
        Insurance carInsurance = new Insurance("You're car won't crash", 
                insuranceHolder, insuranceHolder.ownerYear);
        ownedCar.theInsurance = carInsurance;
        
        System.out.println(ownedCar.displayInformation());
        ownedCar.theInsurance.renewPolicy(10);
        print();
        print();
        System.out.println(ownedCar.displayInformation());       
                   
    }
    public static void print()
    {
        System.out.println("");
    }
    public static void changeCar(Automobile someCar)
    {
        someCar.cost = 5;
        someCar.setBrand("Generic");
        someCar.setWeight(10);        
    }    

}
