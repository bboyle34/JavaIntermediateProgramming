
package ch14;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.Stage;

public class ShowCircle extends Application
{
    public void start(Stage primaryStage) 
    {
        Circle circle = new Circle();
        Pane pane = new Pane();
//        circle.setCenterX(100);
//        circle.setCenterY(100);
        circle.centerXProperty().bind(pane.widthProperty().divide(2));
        circle.centerYProperty().bind(pane.widthProperty().divide(2));
      
        circle.setRadius(50);
        circle.setStroke(Color.BLACK);
        circle.setFill(Color.WHITE);
        
        pane.getChildren().add(circle);
        
        Scene scene = new Scene(pane, 200, 200);
        primaryStage.setTitle("ShowCircle");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String args[])
    {
        Application.launch(args);
    }

}
