/*
Brendan Boyle
Homework 5 10/26/2019
Jeremy Ezell
The purpose of this assignment is to give the user the option to add a new 
loan amortization schedule to an amortization database, adjust a current
amortization schedule that is saved in the database, print any current
users and their loans that are saved in the database, and finally exit
the program.
*/
package Homework5;
import java.util.Scanner;

public class HW5butLikeReallyFuckedUp 
{
    public static void main(String[] args) 
    {
        double [][][] amortDB = new double[1000][][];
        String [][] nameTable = new String[1000][2];
        Scanner in = new Scanner(System.in);
        int menuChoice = 0;
        
        System.out.println("Welcome to DukeLoanSystem!");
        do
        {
            System.out.println("\n-------------------------");
            System.out.println("1. Add New Loan");
            System.out.println("2. Adjust Customer Loan");
            System.out.println("3. Print Loan Schedule");
            System.out.println("4. Exit Program");
            System.out.print("Enter Choice: ");
            menuChoice = in.nextInt();
            handleLoanMenuChoice(menuChoice, amortDB, nameTable);
        } while (menuChoice != 4);
        System.out.println("\nProgram Exiting . . . ");
        
    }
    public static void handleLoanMenuChoice(int menuChoice, 
            double [][][] amortDB, String [][] nameTable)
    {
        //this function will drive the program to the correct function depending
        //on the users menu choice
        //use a switch statement to control the user's choice
        //default should make an error statement and loop back to main()
        switch (menuChoice)
        {
            case 1: addLoan(amortDB, nameTable); 
                break;
            case 2: adjustLoan(amortDB, nameTable);
                break;
            case 3: printAmort(amortDB, nameTable, chooseCustomer(nameTable));
                break;
            case 4:
                break;
            default: System.out.println("Please enter a valid option.");                
        }
    }
    public static void addLoan (double[][][] amortDB, String[][] nameTable)
    {        
        //this function will capture customer information and all information
        //needed to create a new loan        
        Scanner in = new Scanner(System.in);
        System.out.println("====================");
        System.out.print("Enter Customer Name: ");
        String newCustomer = in.nextLine();        
        nameTable[nameTableFreePosition(nameTable)] = splitName(newCustomer);
        
        //we need to find the next empty slot in the database to put this new
        //user information and amortization scheddy
        //because each time we create a user, we also create a table, that means
        //whereever there is the next null value in the nameTable array
        //that means that that same index number is the next available slot 
        //in the amortDB array
        int insertPosition = nameTableFreePosition(nameTable);
        insertPosition -= 1; //since we create a name in nameTable, the spot we
        //want is not the next null spot, but its the spot right before that
        //so we find the next null spot and subtract one
        amortInfo(amortDB, nameTable, insertPosition);       
    }
    public static void adjustLoan(double[][][] amortDB, String[][] nameTable)
    {
        //need to call choose customer and get the right user from our nameTable
        //database
        //call the amortInfo and input new data
        int customerNumber = chooseCustomer(nameTable);
        amortInfo(amortDB, nameTable, customerNumber);        
    }
    public static void printAmort(double[][][] amortDB, String[][] nameTable, 
            int insertPosition)
    {
        //this function will print the amortization table
        //simple outprint,utilize print format to make it all look nice 
        System.out.println("\nAmortization Schedule for " 
                + nameTable[insertPosition][1] + ", " + 
                nameTable[insertPosition][0]);
        
        System.out.println("=========================================");
        System.out.printf("%-11s%-11s%-12s%-13s\n","Payment #:", "Interest:", 
                "Principal: ", "Balance: ");
        
        for (int i = 0; i < amortDB[insertPosition].length; i++)
        {    
            int payment = (i + 1);
            double interestPaid = amortDB[insertPosition][i][0];
            double principalPaid = amortDB[insertPosition][i][1];
            double newBalance = amortDB[insertPosition][i][2];
            System.out.printf("%-11d$%-10.2f$%-11.2f$%-12.2f\n",
            payment, interestPaid, principalPaid, newBalance);
        }
        System.out.println("=========================================");
    }
    public static String[] splitName(String inputName)
    {
        //this function will take in a string name, and put the first and last
        //name into a String array
        String[] customerName = {"", ""};
        int placeHolder = 0;
        for (int i = 0; i < inputName.length(); i++)
        {
            if (inputName.charAt(i) != ' ')
            {
                customerName[placeHolder] += inputName.charAt(i);
            }
            else
            {
                placeHolder++;
            }            
        }
        return customerName;
    }
    public static int nameTableFreePosition(String[][] nameTable)
    {    
        //find the next null value so you know where the next table will be
        int position = 0;
        while (nameTable[position][1] != null)
        {
            position++;
        }
        return position;
    }
    public static void populateAmort(double[][][] amortDB, String[][] nameTable, 
            int insertPosition, double principal, double annualInterestRate, 
            double numberOfYears)
    {
        //this function will do something
        //given the right placement in the database, input the interest, 
        //principal, and new balance into the correct database table in the 
        //correct row
        //since the database is a 3d ragged array, the number of rows is
        //equivalent to the number of months
        double interestPaid, principalPaid, newBalance;
        double monthlyInterestRate, monthlyPayment;
        double numMonths = numberOfYears * 12;
        amortDB[insertPosition] = new double[(int)numMonths][3];

        monthlyInterestRate = annualInterestRate / 12;
        monthlyInterestRate /= 100;
        monthlyPayment = principal * monthlyInterestRate / 
                (1 - 1 / Math.pow(1 + monthlyInterestRate, numberOfYears * 12));
        
        for (int i = 0; i < numMonths; i++)
        {
            interestPaid  = principal * monthlyInterestRate;
            principalPaid = monthlyPayment - interestPaid;
            newBalance = principal - principalPaid;
            amortDB[insertPosition][i][0] = interestPaid;
            amortDB[insertPosition][i][1] = principalPaid;
            amortDB[insertPosition][i][2] = newBalance;
            principal = newBalance;
        }
        printAmort(amortDB, nameTable, insertPosition);
    }
    public static int chooseCustomer(String[][] nameTable)
    {
        //this function will choose a customer and print their amortization
        //find next null spot in nameTable and output everything before that
        Scanner in = new Scanner(System.in);
        System.out.println("Please Choose a Customer:");
        System.out.println("-------------------------");
        
        for (int i = 0; i < nameTable.length; i++)
        {
            if (nameTable[i][0] != null)
            {
                System.out.println(i + ": " + nameTable[i][1] + ", " + 
                        nameTable[i][0]);
            }
        }
        
        System.out.println("-------------------------");
        System.out.print("Choice?: ");
        int choice = in.nextInt();
        return choice;
    }
    public static void amortInfo(double[][][] amortDB,String[][] nameTable,
            int insertPosition)
    {
        //gather user input for loan amount, interest rate, and loan years
        //send this over to populateAmort to take this info and make a table
        Scanner in = new Scanner(System.in);
        
        System.out.print("Please enter amount of full loan principal: ");
        double loanAmount = in.nextDouble();
        
        System.out.print("Please enter your Annual Interest Rate: ");
        double interestRate = in.nextDouble();

        System.out.print("Please enter # of years of the loan: ");
        double loanYears = in.nextDouble();
        
        populateAmort(amortDB, nameTable, insertPosition, loanAmount, 
                interestRate, loanYears);
    }
}