
package ch9;

public class PersonApplication 
{
    public static void main(String[] args) 
    {
        Person newPerson = new Person();
        newPerson.createPerson();
        newPerson.displayInformation();
        Person susie = new Person("Susie", 63, "Blue", 21, "Blonde", 50000);
        Person bob = new Person(); // Constructor call
        susie.age = 22;
        susie.age = 23;
        bob.name  = "Bob";
        bob.eyeColor = "Empty";
        bob.age = 67;
        bob.hairColor = "Bald";
        bob.setSalary(500000.0);
        Person sarah = new Person("Sarah", 72, "Glow in the Dark", 21, "Brown", 50000);
        
        sarah.heightInFeet();
        susie.heightInFeet();
        bob.heightInFeet();
        System.out.println(sarah.getSalary());
        
        susie.displayInformation();
        bob.displayInformation();
        sarah.displayInformation();
        
        System.out.println(Person.getNextID());
        
        // Array of Persons
        Person[] CIS331 = new Person[20];
        CIS331[0] = new Person();
        CIS331[1] = susie; // this is a copy
        //an instance object can have multiple references
        susie = null;
        
        CIS331[1].favoriteNumbers[1] = 3;
        
    }
}
