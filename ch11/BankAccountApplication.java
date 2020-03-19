package ch11;

import java.util.Scanner;

public class BankAccountApplication {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        BankAccount myAccount = new BankAccount();
        myAccount.setOwner("Brendan Boyle");
        myAccount.makeTransaction(100000);
        System.out.println("Owner: " + myAccount.getOwner() + " Balance: $"
                + myAccount.getBalance());
        myAccount.displayAccount();

        BankAccount yourAccount = new BankAccount("Jeremy Ezell", 500000);
        yourAccount.displayAccount();

        BankAccount[] customers = new BankAccount[10];
        boolean operator = true;
        int num = 0;
        while (operator) {
            System.out.print("Would you like to create a new customer? (Y/N)");
            String answer = in.next();
            if (answer.equalsIgnoreCase("Y")) {
                System.out.print("Customer Name: ");
                String name = in.next();
                System.out.print("Account Balance: ");
                double accountBalance = in.nextDouble();
                customers[num] = new BankAccount(name, accountBalance);
                customers[num].displayAccount();
                num++;
            } else {
                operator = false;
            }
            if (num >= customers.length) {
                operator = false;
            }
        }
        BankAccount.numberOfAccounts();
        print();

        SavingsAccount sAccount = new SavingsAccount(.5);
        sAccount.displayAccount();
        print();

        SavingsAccount.numberOfAccounts();
        BankAccount.numberOfAccounts();
        print();

        SavingsAccount sAccount2 = new SavingsAccount("SAVER", 10.0, 12.0);
        sAccount2.displayAccount();

        /*
        Polymorphism        
        Poly = meaning many
        Morphism = meaning forms
        
        The ability of object oriented programs to generate different 
        behaviors with the same call to the same method on objects with 
        the same data type.
        This occurs through an activity called Dyanmic Binding, which is a 
        run time decision about method version based upon an object's data type.
        
        Scenario:
        - ClassA (parent class)
        - ClassB (child class)
        - methodA() is in both classes (overidden in ClassB)
        ClassA obj - new ClassA();
        obj.methodA();
        ClassA obj2 = new ClassB();
        obj2.methodA();
        
         */
        BankAccount polymorphism = new SavingsAccount("Sarah", 1000.0, 12.5);
        // Allows for future compatibility with non existent code
        polymorphism.makeTransaction(500.0);

        System.out.println(polymorphism.toString());

    }

    public static void print() {
        System.out.println("");
    }

}
