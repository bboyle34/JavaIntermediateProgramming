
package Homework5;
import java.util.Scanner;

public class ScratchPaper 
{
    public static void main(String[] args) 
    {
        //monthlyPayment = monthlyPayment(principal, monthlyInterestRate, 
                //numberOfYears);
//        static double monthlyPayment(double loanAmount, double monthlyInterestRate, 
//            double numberOfYears) 
//    {
//        //create a function for monthly payment cause I was getting my calculations
//        //wrong and separating this into a function helps
//        monthlyInterestRate /= 100;
//        return loanAmount * monthlyInterestRate /
//                (1 - 1 / Math.pow(1 + monthlyInterestRate, numberOfYears * 12));
//    }
        
//        String[] name = {"", ""};
//        String me = "Brendan Boyle";
//        
//        int place = 0;
//        for (int i = 0; i < me.length(); i++)
//        {
//            if (me.charAt(i) == ' ')
//            {
//                place++;
//            }
//            else
//            {
//                name[place]+= me.charAt(i);
//            }
//            System.out.println(place);
//            System.out.println(name[1] + ",  " + name[0]);
//        }
//    int[][] table = new int[3][2];
//    
//    for (int i = 0; i < table.length; i++)
//    {
//        for (int j = 0; j < table[i].length; j++)
//        {
//            System.out.println(table[i][j]);
//        }
//    }
// 
      Scanner sc = new Scanner(System.in);

        System.out.print("Loan Amount: ");
        double loanAmount = sc.nextDouble();

        System.out.print("Number of Years: ");
        int numYears = sc.nextInt();

        System.out.print("Annual Interest Rate (in %): ");
        double annualInterestRate = sc.nextDouble();

        System.out.println(); 
        printAmortizationSchedule(loanAmount, annualInterestRate, numYears);
    }

    /**
     * Prints amortization schedule for all months.
     * @param principal - the total amount of the loan
     * @param annualInterestRate in percent
     * @param numYears
     */
    public static void printAmortizationSchedule(double principal, double annualInterestRate,
                                                 int numYears) {
        double interestPaid, principalPaid, newBalance;
        double monthlyInterestRate, monthlyPayment;
        int month;
        int numMonths = numYears * 12;

        // Output monthly payment and total payment
        monthlyInterestRate = annualInterestRate / 12;
        monthlyPayment      = monthlyPayment(principal, monthlyInterestRate, numYears);
        System.out.format("Monthly Payment: %8.2f%n", monthlyPayment);
        System.out.format("Total Payment:   %8.2f%n", monthlyPayment * numYears * 12);

        // Print the table header
        printTableHeader();

        for (month = 1; month <= numMonths; month++) {
            // Compute amount paid and new balance for each payment period
            interestPaid  = principal      * (monthlyInterestRate / 100);
            principalPaid = monthlyPayment - interestPaid;
            newBalance    = principal      - principalPaid;

            // Output the data item
            printScheduleItem(month, interestPaid, principalPaid, newBalance);

            // Update the balance
            principal = newBalance;
        }
    }

    /**
     * @param loanAmount
     * @param monthlyInterestRate in percent
     * @param numberOfYears
     * @return the amount of the monthly payment of the loan
     */
    static double monthlyPayment(double loanAmount, double monthlyInterestRate, int numberOfYears) {
        monthlyInterestRate /= 100;  // e.g. 5% => 0.05
        return loanAmount * monthlyInterestRate /
                ( 1 - 1 / Math.pow(1 + monthlyInterestRate, numberOfYears * 12) );
    }

    /**
     * Prints a table data of the amortization schedule as a table row.
     */
    private static void printScheduleItem(int month, double interestPaid,
                                          double principalPaid, double newBalance) {
        System.out.format("%8d%10.2f%10.2f%12.2f\n",
            month, interestPaid, principalPaid, newBalance);
    }

    /**
     * Prints the table header for the amortization schedule.
     */
    private static void printTableHeader() {
        System.out.println("\nAmortization schedule");
        for(int i = 0; i < 40; i++) {  // Draw a line
            System.out.print("-");
        }
        System.out.format("\n%8s%10s%10s%12s\n",
            "Payment#", "Interest", "Principal", "Balance");
        System.out.format("%8s%10s%10s%12s\n\n",
            "", "paid", "paid", "");  
        
        
    }
    

}
