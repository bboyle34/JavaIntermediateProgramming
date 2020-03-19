/*
Brendan Boyle
Homework 6 11/6/2019
Jeremy Ezell
The purpose of this assignment is to allow the application program to create
instance objects of the Class BankAccount and create instance objects of the
Class Transaction that are stored in an array data field of BankAccount
*/
package Homework6;

public class HW6Application 
{
    public static void main(String[] args) 
    {       
        BankAccount jeremy = new BankAccount("Jeremy Ezell", 100.00);
        
        jeremy.transactAccount(52.34, "Sold a Jar of Pickles");
        jeremy.transactAccount(-43.16, "Bought a Jar of Water");
        jeremy.transactAccount(-21.00, "Bough a Pizza");
        jeremy.transactAccount(150.00, "PayCheck!");
        System.out.println(jeremy.describeAccount());
        
        /*
        Since you want 2-3 accounts with 5-10 transactions each, I created
        an object array of 3 accounts and ran it through a loop that created
        10 transactions for each account. The balances of the transactions are 
        between -100 and 500. The descriptions are dependent on whether the 
        balance is positive or negative. I created a random balance method  
        as well as a random place method that randomly selects one of 
        my four positive or negative descriptions
        */
        String[] names = {"Brendan Boyle", "Donald Trump", "Wayne Gretzky"};
        String[] positive = {"Paycheck", "Bonus", "Birthday", "I Stole it"};
        String[] negative = {"Food", "Alcohol", "Caps Tickets", "I got Mugged"};
        BankAccount[] section3 = new BankAccount[3];

        for (int i = 0; i < section3.length; i++)
        {
            double balance;
            section3[i] = new BankAccount(names[i], 0);
            for (int j = 0; j < 10; j++)
            {
                balance = randomBalance();
                if (balance > 0)
                {
                    section3[i].transactAccount(balance, 
                        positive[randomPlace()]);  
                }
                else if (balance < 0)
                {
                    section3[i].transactAccount(balance,
                        negative[randomPlace()]);
                }                
            }
            System.out.println(section3[i].describeAccount());
        }
    }
    public static double randomBalance()
    {
        double answer = (int)(-100.0 + Math.random() * 600.0);
        return answer;
    }
    public static int randomPlace()
    {
        int answer = (int)(Math.random() * 4.0);
        return answer;
    }
}
