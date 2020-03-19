
package ch11;
// Class Definition File (CDF)

public class BankAccount 
{
    // Data Fields
    private int accountID;
    private String accountOwner;
    private double accountBalance;
    
    private static int nextID = 100;

    // Constructors
    public BankAccount()
    {
        this.accountOwner = "This account has no owner";
        this.accountBalance = 1.0;
        this.accountID = nextID++;
    }
    public BankAccount(String accountOwner, 
            double accountBalance)
    {
        this.accountID = nextID++;
        this.accountOwner = accountOwner;
        this.accountBalance = accountBalance;
    }

    // Methods
    public void setOwner(String accountOwner)
    {
        this.accountOwner = accountOwner;
    }
    public void setBalance(double accountBalance)
    {
        this.accountBalance = accountBalance;
    }
    public String getOwner()
    {
        return this.accountOwner;
    }
    public double getBalance()
    {
        return this.accountBalance;
    }
    public void makeTransaction(double amount)
    {
        this.accountBalance += amount;
        if (this.accountBalance <= 0.0)
        {
            System.out.println("You are out of money dipshit.");
            if (this.accountBalance < 0.0)
            {
                System.out.println("Your account is overdrawn by "
                            + (-1 * this.accountBalance));
            }
        }
    }
    public void displayAccount()
    {
        System.out.println("Personable Identifiable Information\nAccount ID: "
                            + this.accountID + "\nAccount Owner: " 
                            + this.accountOwner + "\nAccount Balance: "
                            + this.accountBalance + "\n");                            
    }
    public static void numberOfAccounts()
    {
        int count = 0;
        for (int i = 100; i < nextID; i++)
        {
           count++; 
        }
        System.out.println("The bank currently has " + count + " accounts.");
    }
}
