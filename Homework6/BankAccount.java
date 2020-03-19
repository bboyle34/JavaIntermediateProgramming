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

public class BankAccount 
{
    // Data Fields
    private int ID;
    private String owner;
    private double balance;
    //make the activity array an array of objects so we can store transaction
    //instance objects in it later
    private Transaction[] activity = new Transaction[1000];
    private int nextTransactionPosition;
    
    //I want the ID numbers to start at 1
    public static int nextID = 1;
    
    // Constructors
    public BankAccount(String owner, double balance)
    {
        this.ID = nextID++;
        this.owner = owner;
        this.balance = balance;
    }
    
    // Methods
    public double getBalance()
    {
        return this.balance;
    }
    public String getOwner()
    {
        return this.owner;
    }
    public void setOwner(String owner)
    {
        this.owner = owner;
    }
    public boolean transactAccount(double amount, String description)
    {
        //create new transaction instance object and store it in the next 
        //available null position in the activity array
        Transaction a = new Transaction(description, amount);
        activity[this.nextTransactionPosition++] = a;
        this.balance += amount;
        if (this.balance <= 0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    public String listAllTransactions()
    {
        String answer = "";
        //need to find only the number of array positions that are not null
        //value so we know how many times to loop through and print out
        //the array values
        int position = this.findNextSpot();
        for (int i = 0; i < position; i++)
        {
            answer += this.activity[i].describeTransaction();
        }                
        return answer;
    }
    public String describeAccount()
    {
        System.out.println("----------------------------------------------------------------------");
        String answer = "";
        answer = ("ID: " + this.ID + ", Name: " + this.owner 
                        + ", Balance: $" + this.balance + "\n");
        answer += this.listAllTransactions();
        return answer;
    }
    public int findNextSpot()
    {
        int position = 0;
        for (int i = 0; i < this.activity.length; i++)
        {
            if (this.activity[i] != null)
            {
                position++;
            }
            else
            {
                break;
            }
        }
        return position;
    }
    public String toString()
    {
        String answer = "";
        answer += ("ID: " + this.ID + "\nAccount Owner: " + this.getOwner() + "\nAccount Balance: $" + this.getBalance());
        
        return answer;
    }
}
