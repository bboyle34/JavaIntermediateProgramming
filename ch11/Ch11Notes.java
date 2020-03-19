
package ch11;

public class Ch11Notes 
{
    public static void main(String[] args) 
    {
        /*
        Inheritance:
        
        Method of ClassA is only usable by instance objects of ClassA.
        What if I want to use that method in ClassB?
        How can I link these two classes together to use each other's methods?         
        
        Overloaded methods are bonded using static binding while overridden 
        methods are bonded using dynamic binding at runtime.
        
        public class StaticBindingTest 
        {  
            public static void main(String args[]) 
            {
                Collection c = new HashSet();
                StaticBindingTest et = new StaticBindingTest();
                et.sort(c);
            }
            //overloaded method takes Collection argument
            public Collection sort(Collection c) 
            {
                System.out.println("Inside Collection sort method");
                return c;
            }
            //another overloaded method which takes HashSet argument which is sub class
            public Collection sort(HashSet hs) 
            {
                System.out.println("Inside HashSet sort method");
                return hs;
            }
        }
        
        public class DynamicBindingTest 
        {   
            public static void main(String args[]) 
            {
                Vehicle vehicle = new Car(); //here Type is vehicle but object will be Car
                vehicle.start(); //Car's start called because start() is overridden method
            }
        }

        class Vehicle 
        {
            public void start() 
            {
                System.out.println("Inside start method of Vehicle");
            }
        }

        class Car extends Vehicle 
        {
            public void start() 
            {
                System.out.println("Inside start method of Car");
            }
        }
        
        */
        
                
    }

}