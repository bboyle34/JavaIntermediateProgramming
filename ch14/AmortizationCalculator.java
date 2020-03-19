
package ch14;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.event.*;
import javafx.geometry.Pos;

public class AmortizationCalculator extends Application
{
    public void start(Stage primaryStage) 
    {
        // Controls
        Label lblLoanAmount = new Label("Loan Amount: ");
        TextField loanAmount = new TextField();
        Label lblInterestRate = new Label("Annual Interest Rate: ");
        TextField interestRate = new TextField();
        Label lblYears = new Label("Number of Years: ");
        TextField years = new TextField();
        Label lblMonthlyPayment = new Label("Monthly Payment: ");
        TextField monthlyPayment = new TextField();
        Label lblTotalPayment = new Label("Total Payment: ");
        TextField totalPayment = new TextField();
        totalPayment.setText(" ");
        Button btCalculate = new Button("Calculate");
        TextField schedule = new TextField();
        btCalculate.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event)
            {
                monthlyPayment.setText(monthlyPayment(years, interestRate, loanAmount));
                totalPayment.setText(totalPayment(monthlyPayment, years));
                schedule.setText("Here\nis\na\t\ntest");
            }
        });
        
        // Pane
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.add(lblLoanAmount, 0, 0);
        root.add(loanAmount, 1, 0);
        root.add(lblInterestRate, 0, 1);
        root.add(interestRate, 1, 1);
        root.add(lblYears, 0, 2);
        root.add(years, 1, 2);
        root.add(lblMonthlyPayment, 0, 3);
        root.add(monthlyPayment, 1, 3);
        root.add(lblTotalPayment, 0, 4);
        root.add(totalPayment, 1, 4);
        root.add(btCalculate, 1, 5);
        //root.add(schedule, 0, 5);
        
        // Scene
        Scene scene = new Scene(root, 500, 500);
        
        // Stage
        primaryStage.setTitle("Amortization Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
    public static String monthlyPayment(TextField years, TextField annualInterestRate, TextField loanAmount)
    {
        double numberOfYears = Double.parseDouble(years.getText());
        double annualRate = Double.parseDouble(annualInterestRate.getText());
        double principal = Double.parseDouble(loanAmount.getText());
        double monthlyInterestRate, monthlyPayment;
        double numMonths = numberOfYears * 12;
        monthlyInterestRate = annualRate / 12;
        monthlyInterestRate /= 100;
        monthlyPayment = principal * monthlyInterestRate / (1 - 1 / Math.pow(monthlyInterestRate, numberOfYears));
        
        String answer = "";
        answer += monthlyPayment;
        return answer;
    }
    public static String totalPayment(TextField monthlyPayment, TextField years)
    {
        double numMonths = Double.parseDouble(years.getText());
        double monthPayment = Double.parseDouble(monthlyPayment.getText());
        double totalPayment = numMonths * monthPayment;
        
        String answer = "";
        answer += totalPayment;
        return answer;
    }
    public static void main(String args[])
    {
        Application.launch(args);
    }

}
