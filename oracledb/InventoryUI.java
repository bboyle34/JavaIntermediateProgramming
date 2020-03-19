package oracledb;

import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.collections.*;
import javafx.scene.shape.*;

import java.sql.*;
import oracle.jdbc.pool.*;
import java.util.*;

public class InventoryUI extends Application {
    // Notice that I have placed all the JavaFX controls
    // at the Class/Global level. This is perfectly valid
    // and it lets us use our Navigator window when we are
    // writing our SQL queries later on.
    TextField txtItemName = new TextField();
    TextField txtItemPaid = new TextField();
    TextField txtItemSell = new TextField();
    TextField txtItemQty = new TextField();
    Label lblItemName = new Label("Item: ");
    Label lblItemPaid = new Label("Paid: ");
    Label lblItemSell = new Label("Sell: ");
    Label lblItemQty = new Label("QTY: ");
    Button btnCommand = new Button("Execute ->");
    RadioButton rdoAddItem = new RadioButton("Add Item");
    RadioButton rdoUpdateItem = new RadioButton("Update Item");
    RadioButton rdoDeleteItem = new RadioButton("Delete Item");
    RadioButton rdoDisplayChart = new RadioButton("Display Chart");
    ToggleGroup rdoCRUDGroup = new ToggleGroup();
    
    // TextArea for general output and information.
    // You can use a ListView<> if you wish as well.
    TextArea txtaOutput = new TextArea();
    
    // Our Database Connection method needs these 
    // objects. We declare them here and point them
    // to instance objects below.
    Connection dbConn;
    Statement commStmt;
    ResultSet dbResults;
    
    @Override
    public void start(Stage primaryStage) {
        // We will use three GridPane to layout our UI Form
        GridPane controlsPane = new GridPane();
        GridPane tablePane = new GridPane();
        GridPane overallPane = new GridPane();
    
        // Adding controls to our controls GridPane
        controlsPane.setAlignment(Pos.CENTER);
        controlsPane.add(lblItemName, 0, 0);
        controlsPane.add(lblItemPaid, 0, 1);
        controlsPane.add(lblItemSell, 0, 2);
        controlsPane.add(lblItemQty, 0, 3);
        controlsPane.add(txtItemName, 1, 0);
        controlsPane.add(txtItemPaid, 1, 1);
        controlsPane.add(txtItemSell, 1, 2);
        controlsPane.add(txtItemQty, 1, 3);
        rdoAddItem.setToggleGroup(rdoCRUDGroup);
        rdoUpdateItem.setToggleGroup(rdoCRUDGroup);
        rdoDeleteItem.setToggleGroup(rdoCRUDGroup);
        rdoDisplayChart.setToggleGroup(rdoCRUDGroup);
        controlsPane.add(rdoAddItem, 0, 4);
        controlsPane.add(rdoUpdateItem, 0, 5);
        controlsPane.add(rdoDeleteItem, 0, 6);
        //controlsPane.add(rdoDisplayChart, 0, 7);
        controlsPane.add(btnCommand, 1, 4);
        
        txtaOutput.setMaxWidth(300);
        
        // Place the output on its own pane.
        tablePane.setAlignment(Pos.CENTER);
        tablePane.add(txtaOutput, 0, 0);
        
        // Add the controls GridPane and output 
        // GridPane to the overall GridPane
        overallPane.setAlignment(Pos.CENTER);
        overallPane.add(controlsPane, 0, 0);
        overallPane.add(tablePane, 1, 0);
        
        // Set up the primary window and have it display
        Scene primaryScene = new Scene(overallPane,700,400);
        primaryStage.setTitle("Inventory Application");
        primaryStage.setScene(primaryScene);
        primaryStage.show();
       
        // We will be adding lambda expressions here.
        
        
        //sendDBCommand("Select * from javauser.inventory");       

    } // End of start() method
     
    // We will be adding more methods here.
    
    
    public static void main(String[] args) {
        launch(args);
    }
    
    public void sendDBCommand(String sqlQuery)
    {
        // Set up your connection strings
        // IF YOU ARE IN CIS330 NOW: use YOUR Oracle Username/Password
        String URL = "jdbc:oracle:thin:@localhost:1521:XE";
        String userID = "javauser"; // Change to YOUR Oracle username
        String userPASS = "javapass"; // Change to YOUR Oracle password
        OracleDataSource ds;
        
        // Clear Box Testing - Print each query to check SQL syntax
        //  sent to this method.
        // You can comment this line out when your program is finished
        System.out.println(sqlQuery);
        
        // Lets try to connect
        try
        {
            // instantiate a new data source object
            ds = new OracleDataSource();
            // Where is the database located? Web? Local?
            ds.setURL(URL);
            // Send the user/pass and get an open connection.
            dbConn = ds.getConnection(userID,userPASS);
            // When we get results
            //  -TYPE_SCROLL_SENSITIVE means if the database data changes we
            //   will see our resultset update in real time.
            //  -CONCUR_READ_ONLY means that we cannot accidentally change the
            //   data in our database by using the .update____() methods of
            //   the ResultSet class - TableView controls are impacted by
            //   this setting as well.
            commStmt = dbConn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            // We send the query to the DB. A ResultSet object is instantiated
            //  and we are returned a reference to it, that we point to from
            // dbResults.
            // Because we declared dbResults at the datafield level
            // we can see the results from anywhere in our Class.
            dbResults = commStmt.executeQuery(sqlQuery); // Sends the Query to the DB
            // The results are stored in a ResultSet structure object
            // pointed to by the reference variable dbResults
            // Because we declared this variable globally above, we can use
            // the results in any method in the class.
        }
        catch (SQLException e)
        {
            System.out.println(e.toString());
        }
    }
    
    
    
} // end of Class
