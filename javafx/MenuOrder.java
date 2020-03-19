// Author: Jeremy D. Ezell
// Date: 11.20.2019
// Purpose: This is a simple Restaurant order system that allows for
//   the creation of Menu Items and their addition to a single customer's
//   order.

package javafx;

import javafx.application.Application;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

// For ArrayList
import java.util.*;

// These enable ComboBoxes
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;


public class MenuOrder extends Application {
    
    // Declare controls and data objects at the class level so that they are accessible
    // via our Event Handling Lambda Expressions.
    public static ArrayList<MenuItem> menu = new ArrayList<>();
    public static ArrayList<MenuItem> currentOrder = new ArrayList<>();
    public static ObservableList olMenuCombo = FXCollections.observableArrayList();
    public static ObservableList olListMenu = FXCollections.observableArrayList();
    
    public double orderTotal; // Non-static "data field" of this application class
    public static double taxRate = 9.5; // Local Tax Rate
    
    public Label lblPrice = new Label("Price:");
    public Label lblDesc = new Label("Description:");
    public Label lblItems = new Label("Menu Items: ");
    public TextField txtPrice = new TextField();
    public TextField txtDesc = new TextField();
    public Button btnAddNewItem = new Button("Add ->");
    public Button btnAddToOrder = new Button("Add ->");
    public Button btnClearOrder = new Button("Clear Order:");
    
    // Declare a ComboBox and pass the ObservableList object through
    // to its Constructor.
    public ComboBox cmboMenuItems = new ComboBox(olMenuCombo);
    
    // Declare a ListView control and have it use its ObservableList
    public ListView lstItemsList = new ListView(olListMenu);
    
    // TextArea to display the order so far.
    public TextArea taOrderDisplay = new TextArea();
    
    // Declare a primaryPane to hold everything, and
    // then two separate GridPanes that will be placed
    // one on each Tab to organize our controls there.
    public GridPane primaryPane = new GridPane();
    public GridPane addItemsPane = new GridPane();
    public GridPane buildOrderPane = new GridPane();
    
    // A TabPane object holds Tab objects, and each Tab
    // acts like a little "pane" to hold its own controls.
    public TabPane tpTabs = new TabPane();
    public Tab tabAddItems = new Tab("Add Menu Items");
    public Tab tabBuildOrder = new Tab("Build Order");
    
    @Override
    public void start(Stage primaryStage) {

        // Set the order total. This application only handles 1 order.
        // If you wanted to create and store multiple orders, you 
        // could create an Order class and store its objects in
        // an ArrayList as well. You would have to add
        // elements to the UI to handle the display of all the existing
        // orders in the system.
        
        orderTotal = 0.0;
        
        // Since the TabPane is the only thing in primaryPane,
        // I'll set the alignment to Top Left.
        primaryPane.setAlignment(Pos.TOP_LEFT);
        
        // Add each individual Tab to the TabPane
        tpTabs.getTabs().add(tabAddItems);
        tpTabs.getTabs().add(tabBuildOrder);
        
        // Now add the TabPane to primaryPane
        primaryPane.add(tpTabs, 0, 0);
        
        // Lets set up the Add Items tab
        
        // Add the addItemsPane to tabAddItems by setting the tab's content.
        tabAddItems.setContent(addItemsPane);
        
        // Now add items to addItemsPane
        addItemsPane.setAlignment(Pos.CENTER);
        addItemsPane.add(new Label("Add Items to our Menu:"), 0, 0);
        addItemsPane.add(lblDesc, 0, 1);
        addItemsPane.add(txtDesc, 1, 1);
        addItemsPane.add(lblPrice, 0, 2);
        addItemsPane.add(txtPrice, 1, 2);
        addItemsPane.add(btnAddNewItem, 1, 3);
        addItemsPane.add(lstItemsList, 2, 1, 1, 3);
        addItemsPane.setVgap(20);
        addItemsPane.setHgap(20);
        
        // Lets set up the Build Order Tab
        
        // Add the buildOrderPane to tabBuildOrder by setting the tab's content:
        tabBuildOrder.setContent(buildOrderPane);
        
        // Now add items to buildOrderPane
        buildOrderPane.setAlignment(Pos.CENTER);
        buildOrderPane.add(new Label("Build The Customer's Order: "), 0, 0);
        buildOrderPane.add(lblItems, 0, 1);
        buildOrderPane.add(cmboMenuItems, 1, 1, 2, 1);
        buildOrderPane.add(btnAddToOrder, 0, 2);
        buildOrderPane.add(btnClearOrder, 0, 3);
        buildOrderPane.add(taOrderDisplay, 2, 2, 1, 3);
        
        
        Scene primaryScene = new Scene(primaryPane, 700, 500);
        
        // Set the width of the TabPane to the width of the scene
        // If the window is resized, you'll see this TabPane NOT change
        // with it.
        tpTabs.setMinWidth(primaryScene.getWidth());
        
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("Restaurant Order System v. 1.0");
        primaryStage.show();
        
        // Lets set up the Lambda Event Handler for adding new Menu Items
        btnAddNewItem.setOnAction(e -> {
            // Create new MenuItem and store it in a temp reference.
            MenuItem tempItem = new MenuItem(Double.valueOf(txtPrice.getText()), txtDesc.getText());
            // Add the MenuItem via its reference to the array list.
            menu.add(tempItem);
            // Now, add the new MenuItem to the ListView control. ListView likes to display
            // a String (typically), so by adding the MenuItem to the ListView's Observable list,
            // the MenuItem's toString() method will automatically be called and its returned
            // String will be displayed in the ListView Box.
            olListMenu.add(tempItem);
            // Also, add the new MenuItem to the ObservableList for the Menu ComboBox on 
            // the second tab. Remember we are storing a copy of the reference to the new
            // MenuItem object in the ArrayLists.
            olMenuCombo.add(tempItem);
            
            // Now, clear out the TextFields on the first tab to ready it for
            // the next entry
            txtPrice.clear();
            txtDesc.clear();
            
        });
        
        btnAddToOrder.setOnAction(e -> {
            
            // Get the index number of the item that is selected in the ComboBox
            int selectedIndex = cmboMenuItems.getSelectionModel().getSelectedIndex();
            // Now, get a temp reference to that same index position's item in 
            // the ArrayList menu
            MenuItem tempItemRef = menu.get(selectedIndex);
            
            // Increment the Order Total by the cost of this new item
            orderTotal += tempItemRef.getPrice();
            
            // Clear out whatever contents of the TextArea Exist
            taOrderDisplay.clear();
            
            // Add the selected item to the currentOrder ArrayList
            currentOrder.add(tempItemRef);
            
            // Build the Order Display TextArea
            for (MenuItem mi: currentOrder)
            {
                taOrderDisplay.appendText(mi.toString() + "\n");
            }
            taOrderDisplay.appendText("===================\n");
            taOrderDisplay.appendText("Tax: " + String.format("$%.2f\n", (orderTotal * (taxRate / 100))));
            taOrderDisplay.appendText("===================\n");
            taOrderDisplay.appendText("Total: " 
                    + String.format(
                            "$%.2f",
                            (orderTotal + (orderTotal * (taxRate / 100)))));
            

            
        });
        
        btnClearOrder.setOnAction(e -> {
            // Empty the entire ArrayList
            currentOrder.clear();
            // Set the orderTotal to zero
            orderTotal = 0.0;
            // Clear the TextArea and reset it to 0.0;
            taOrderDisplay.clear();
            taOrderDisplay.appendText("===================\n");
            taOrderDisplay.appendText("Tax: " + String.format("$%.2f\n", (orderTotal * (taxRate / 100))));
            taOrderDisplay.appendText("===================\n");
            taOrderDisplay.appendText("Total: " 
                    + String.format(
                            "$%.2f",
                            (orderTotal + (orderTotal * (taxRate / 100)))));
        });
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
