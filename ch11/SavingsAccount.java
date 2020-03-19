
package ch11;
// Class Definition File (CDF)

public class SavingsAccount extends BankAccount
{
    // Data Fields - data fields inherited from parent do NOT need to be restated
    private double interestRate;
    private double totalInterestEarned;    

    // Constructors
    public SavingsAccount(double interestRate)
    {
        //a child class constructor that doesn't address all of the data fields
        //from the parent class will have the default values from the parent 
        //class constructor
        this.interestRate = interestRate;
        this.totalInterestEarned = 0.0;        
    }
    public SavingsAccount(String accountOwner, double accountBalance, 
            double interestRate)
    {
        super(accountOwner, accountBalance);

        this.interestRate = interestRate;
        this.totalInterestEarned = totalInterestEarned;
    }

    // Methods
//    public void makeTransaction(double amount)
//    {
//        this.accountBalance += amount;
//        if (this.accountBalance <= 0.0)
//        {
//            System.out.println("You are out of money dipshit.");
//            if (this.accountBalance < 0.0)
//            {
//                System.out.println("Your account is overdrawn by "
//                            + (-1 * this.accountBalance));
//            }
//        }
//    }
}
