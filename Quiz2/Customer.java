
package Quiz2;

public class Customer 
{
    public String name;
    public int accountNumber;
    
    public Customer()
    {
        this.name = "";
        this.accountNumber = 0;
    }
    public Customer(String name, int accountNumber)
    {
        this.name = name;
        this.accountNumber = accountNumber;
    }
    public boolean accountCheck()
    {
        if (accountNumber > 6500)
            return true;
        else
            return false;
    }
    public void info()
    {
        System.out.println("Name: " + this.name + "\nAccount: " + this.accountNumber
         + "\nAccount Check: " + this.accountCheck() + "\n");
    }
}
