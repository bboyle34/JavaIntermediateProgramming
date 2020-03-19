// Author: Jeremy D. Ezell
// Date: 11/18/2019
// Purpose: Demonstrate three methods of handling events in
//  a JavaFX application.

package ch14;

import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class EventsExample extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Button btnFirst = new Button("Event Handling Class ->");
        Button btnSecond = new Button("Anonymous Inner Class ->");
        Button btnThird = new Button ("Lambda Expression \"Shortcut\"");
        TextField txtFirst = new TextField();
        TextField txtSecond = new TextField();
        TextField txtThird = new TextField();
        
        txtFirst.setMinWidth(350);
        txtSecond.setMinWidth(350);
        txtThird.setMinWidth(350);
        
        GridPane primaryPane = new GridPane();
        primaryPane.setAlignment(Pos.CENTER);
        primaryPane.add(btnFirst, 0, 0);
        primaryPane.add(btnSecond, 0, 1);
        primaryPane.add(btnThird, 0, 2);
        primaryPane.add(txtFirst, 1, 0);
        primaryPane.add(txtSecond, 1, 1);
        primaryPane.add(txtThird, 1, 2);
        
        primaryPane.setVgap(20);
        primaryPane.setHgap(20);
        
        Scene primaryScene = new Scene(primaryPane, 650, 200);
        
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("Ch. 15 Events Example");
        primaryStage.show();
        
        // Method #1: Inner Event Handling Class
        
        // Inner Class - Class inside a class!
        class HandleFirstButtonClass implements EventHandler<ActionEvent>
        {
            public void handle(ActionEvent e)
            {
                txtFirst.setText("First Button handled by Inner Class!");
            }
        }
        // Now instantiate this class and register it to the Button
       HandleFirstButtonClass firstButtonHandler  = new HandleFirstButtonClass();
       btnFirst.setOnAction(firstButtonHandler);
       
       // Method #2: Anonymous Inner Event Handling Class
       // This is a class "defined" inside the setOnAction() method of an object.
       // Its just "anonymous" aka missing a name.
       btnSecond.setOnAction(new EventHandler<ActionEvent>() {
           public void handle(ActionEvent e)
           {
               txtSecond.setText("Second Button Handled by Anon Inner Class!");
           }
           
       });
       
       
       // Method #3: Lambda Expression
       // A "smart" expression built into Java that can figure out that you 
       // are trying to create a class that implements the EventHandler interface
       // AND it figures out that you are overriding method "handle()" locally. 
       // Shortcut!!
       btnThird.setOnAction(e -> {
           
           txtThird.setText("Third Button Handled by a Lambda Expression: So Simple!");
           
       });
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
