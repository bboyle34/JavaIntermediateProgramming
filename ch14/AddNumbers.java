
package ch14;

import javafx.application.Application;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class AddNumbers extends Application
{
    public void start(Stage primaryStage) 
    {
        // Create controls and set their properties
        Label lblFirstNum = new Label("First Number");
        TextField txtFirstNumber = new TextField();
        Label lblSecondNum = new Label("Second Number");
        TextField txtSecondNumber = new TextField();
        Label lblSum = new Label("Result");
        TextField txtAnswer = new TextField("Answer will display here");
        Button btAdd = new Button("    Add   ");
        Button btSub = new Button("Subtract");
        Button btMult = new Button("Multiply");
        Button btDiv = new Button("  Divide  ");
        Button btSave = new Button("File Save");
        txtAnswer.setOnMouseEntered(e ->
        {
            txtAnswer.setText("");
        });
        txtAnswer.setOnMouseExited(e ->
        {
            txtAnswer.setText("Answer will display here");
        });
        btAdd.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event)
            {
                double first = Double.parseDouble(txtFirstNumber.getText());
                double second = Double.parseDouble(txtSecondNumber.getText());
                double addition = first + second;
                txtAnswer.setText(first + " + " + second + " = " + addition);
            }
        });
        btSub.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event)
            {
                double first = Double.parseDouble(txtFirstNumber.getText());
                double second = Double.parseDouble(txtSecondNumber.getText());
                double subtraction = first - second;
                txtAnswer.setText(first + " - " + second + " = " + subtraction);
            }
        }); 
        btMult.setOnAction(e ->
        {
                double first = Double.parseDouble(txtFirstNumber.getText());
                double second = Double.parseDouble(txtSecondNumber.getText());
                double multiplication = first * second;
                txtAnswer.setText(first + " * " + second + " = " + multiplication);
        });
        btDiv.setOnAction(e ->                 
        {
            double first = Double.parseDouble(txtFirstNumber.getText());
            double second = Double.parseDouble(txtSecondNumber.getText());
            double division = first / second;
            txtAnswer.setText(first + " / " + second + " = " + division);
        });
//        btSave.setOnAction(new EventHandler<ActionEvent>()
//        {
//            public void handle(ActionEvent event)
//            {
//                try
//                {
//                    BufferedWriter writer = new BufferedWriter(new FileWriter(".//test.txt"));
//                    writer.write(answer.getText());
//                    writer.close();
//                } catch (IOException e)
//                {
//                    e.printStackTrace();
//                }
//            }
//        });
        
        // Create a pane object and arrange my controls
        GridPane primaryPane = new GridPane();
        primaryPane.setAlignment(Pos.CENTER);
        //primaryPane.setPadding(new Insets(10,10,10,10));
        primaryPane.setVgap(10);
        primaryPane.setHgap(10);
        
        primaryPane.add(lblFirstNum, 0, 0);
        primaryPane.add(txtFirstNumber, 1, 0);
        primaryPane.add(lblSecondNum, 0, 1);
        primaryPane.add(txtSecondNumber, 1, 1);
        primaryPane.add(btAdd, 2, 0, 1, 1);
        primaryPane.add(btSub, 3, 0, 1, 1);
        primaryPane.add(btMult, 2, 1, 1, 1);
        primaryPane.add(btDiv, 3, 1, 1, 1);
        primaryPane.add(lblSum, 0, 2);
        primaryPane.add(txtAnswer, 1, 2);
        primaryPane.add(btSave, 2, 3);
        
        
        // Place my pane on a scene and set its properties
        Scene primaryScene = new Scene(primaryPane, 500, 300);
        
        primaryScene.setOnMousePressed(e ->
        {
            txtFirstNumber.setText("The mouse was clicked");
        });
                
        // Assign my scene to a stage and cause the stage to appear
        primaryStage.setTitle("Add Numbers");
        primaryStage.setScene(primaryScene);
        primaryStage.show();
        
    }
    public void stop()
    {
        //this will run before any program ends
        //code in here is guaranteed to run before the program closes
        //cleanup method
    }
    public static void main(String args[])
    {
        Application.launch(args);
    }
}
