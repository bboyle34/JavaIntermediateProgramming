
package Quiz2;

public class CustomerApplication 
{

    public static void main(String[] args) 
    {
        Customer[] custArray = new Customer[10];
        custArray[0] = new Customer();
        custArray[1] = new Customer("Tim", 65123);
        
        custArray[0].info();
        custArray[1].info();
    }
    
}
