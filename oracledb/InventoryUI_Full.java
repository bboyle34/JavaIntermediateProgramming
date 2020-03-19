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

public class InventoryUI_Full extends Application {
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
    
    // This variable sets the proper table in the SQL strings
    // below. This helps during Lecture, not needed for your
    // projects. You might create a username/password
    // field at the datafield level as well to make
    // altering credentials easier for sendDBCommand().
    String tableName = "INVENTORY";
    
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
        btnCommand.setOnAction(e -> {
            if (rdoAddItem.isSelected())
            {
                insertItem();
                showInventory();
            }
            
            if (rdoUpdateItem.isSelected())
            {
                updateItem();
                showInventory();
            }
            
            if (rdoDeleteItem.isSelected())
            {
                deleteItem();
                showInventory();
            }
        }); // end of btnCommand's Event Handler
        
        
        

    } // End of start() method
     
    // We will be adding more methods here.
    public void insertItem()
    {
        String sqlQuery = "INSERT INTO JAVAUSER." + tableName + " (ITEMNAME, ITEMPAID, ITEMSELL, ITEMQTY) VALUES (";
        sqlQuery += "\'" + txtItemName.getText() + "\',";
        sqlQuery += txtItemPaid.getText() + ",";
        sqlQuery += txtItemSell.getText() + ",";
        sqlQuery += txtItemQty.getText() + ")";
        
        //System.out.println(sqlQuery);
        
        sendDBCommand(sqlQuery);
    }
    
    public void updateItem()
    {
        String sqlQuery = "UPDATE JAVAUSER." + tableName + " SET ITEMPAID=";
        sqlQuery += txtItemPaid.getText() + ",";
        sqlQuery += "ITEMSELL=" + txtItemSell.getText() + ",";
        sqlQuery += "ITEMQTY=" + txtItemQty.getText() 
                + " WHERE ITEMNAME=\'" 
                + txtItemName.getText() + "\'";
        
        sendDBCommand(sqlQuery);
    }
    
    public void deleteItem()
    {
        String sqlQuery = "DELETE FROM JAVAUSER." + tableName 
                + " WHERE ITEMNAME=\'" + txtItemName.getText() + "\'";
        
        sendDBCommand(sqlQuery);
    }
    
    public void showInventory()
    {
        String sqlQuery = "SELECT * FROM JAVAUSER." + tableName;
        sendDBCommand(sqlQuery);
        
        String outputString = "";
        try
        {
            // While there is more rows of results from the SELECT
            // query, loop on each row (.next() moves to the next
            // row each time its called)
            while (dbResults.next())
            {
                // Clear out the TextArea's previous contents
                txtaOutput.clear();
                // Traverse the current row of the ResultSet object
                // and extract each column, appending to our String
                outputString += dbResults.getString(1) + "\t" 
                        + dbResults.getString(2) + "\t" 
                        + dbResults.getString(3) + "\t"
                        + dbResults.getString(4) + "\n";
                
                // Append the outputString to the TextArea's contents.
                txtaOutput.appendText(outputString);
            }
        }
        catch (SQLException sqle)
        {
            txtaOutput.setText(sqle.toString());
        }
        
    }
    
    
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
    
    /* If you've read this far you are thorough! Here's a joke:
    Why did the chicken cross the road?
    Give up?
    Because that action was a non-static member method 
      in its class definition!
    Ha!
    */
    
} // end of Class
