/*
Brendan Boyle
Homework 6 11/6/2019
Jeremy Ezell
The purpose of this assignment is to allow the application program to create
instance objects of the Class BankAccount and create instance objects of the
Class Transaction that are stored in an array data field of BankAccount
*/
package Homework6;
// Class Definition File (CDF)

public class Transaction
{
    // Data Fields
    public int transactionID;
    private String description;
    private double amount;
    
    public static int nextTransactionID;
    
    // Constructors
    public Transaction(String description, double amount)
    {
        this.transactionID = nextTransactionID++;
        this.description = description;
        this.amount = amount;
    }

    // Methods
    public double getAmount()
    {
        return this.amount;
    }
    public String getDescription()
    {
        return this.description;
    }
    public void setDescription(String description)
    {
       this.description = description; 
    }
    public String describeTransaction()
    {
        //in order to count the number of transactions per bankaccount, we do
        //not need the transactionID, we only need to count how many times
        //the loop executes in the listAllTransactions method in bankaccount
        String answer = ("Transaction ID: " + this.transactionID
                        + ", Description: " + this.description
                        + ", Amount: $" + this.amount + "\n");
        return answer;
    }
}
