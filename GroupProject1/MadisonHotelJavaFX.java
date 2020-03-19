// WIP
package GroupProject1;
import java.sql.*;
import oracle.jdbc.pool.*;
import java.util.*;
import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.collections.*;
import javafx.scene.text.*;




public class MadisonHotelJavaFX extends Application
{
    static ArrayList<Employee> employees = new ArrayList<Employee>();
    static ArrayList<Guest> guests = new ArrayList<Guest>();
    static ArrayList<ValueGuest> valueGuests = new ArrayList<ValueGuest>();
    static ArrayList<Booking> bookings = new ArrayList<Booking>();
    static ArrayList<Room> rooms = new ArrayList<Room>();
    
    Connection dbConn;
    Statement commStmt;
    ResultSet dbResults;
    
    public void refreshRoom()
    {   
        rooms.clear();
        String tableName = "ROOM";
        String sqlQuery  = "SELECT * FROM JAVAUSER." + tableName;
        sendDBCommand(sqlQuery);
        
        try
        {
            while (dbResults.next())
            {
                int roomid = Integer.parseInt(dbResults.getString(1));
                int bedOption = Integer.parseInt(dbResults.getString(3));
                int kitchOption = Integer.parseInt(dbResults.getString(4));
                int coffeeOption = Integer.parseInt(dbResults.getString(5));
                int accessibleOption = Integer.parseInt(dbResults.getString(6));
                int roomBookQuantity = Integer.parseInt(dbResults.getString(7));
                int roomBooked = Integer.parseInt(dbResults.getString(8));
                int roomNumber = Integer.parseInt(dbResults.getString(2));
                int roomCostPerNight = Integer.parseInt(dbResults.getString(9));
                String roomActivity = dbResults.getString(10);
                rooms.add(new Room(bedOption, kitchOption, coffeeOption, accessibleOption, roomNumber, roomCostPerNight));
                rooms.get(roomid).setRoomActivity(roomActivity);
                rooms.get(roomid).setRoomBookedQuantity(roomBookQuantity);
                if (roomBooked == 1)
                {
                    rooms.get(roomid).setRoomBook(true);
                }
            }
        }
        catch (SQLException sqle)
        {
            System.out.println(sqle.toString());
        }
        
    }
    public void refreshEmployee()
    {
        employees.clear();   
        String tableName = "EMPLOYEE";
        String sqlQuery = "SELECT * FROM JAVAUSER." + tableName;
        sendDBCommand(sqlQuery);
        
        try 
        {
            while (dbResults.next())
            {
                String employeeUserName = (dbResults.getString(2));
                String employeeName = (dbResults.getString(3));                
                String employeePassword = (dbResults.getString(4));
                employees.add(new Employee(employeeUserName, employeePassword, employeeName));
                
            }
        } 
        catch (SQLException sqle)
        {
            System.out.println(sqle.toString());
        }
    }
    public void refreshGuest()
    {
        guests.clear();
        String tableName = "GUEST";
        String sqlQuery = ("SELECT * FROM JAVAUSER." + tableName);
        sendDBCommand(sqlQuery);
        try 
        {
            while (dbResults.next())
            {
                String guestUsername = (dbResults.getString(2));
                String guestName = (dbResults.getString(3));
                String guestPassword = (dbResults.getString(4));
                try
                {
                    if (!(dbResults.getString(5).isEmpty()))
                    {
                        ValueGuest v = new ValueGuest(guestUsername, guestPassword, guestName);
                        valueGuests.add(v);
                        guests.add(v);
                    } 
                }
                catch (Exception f)
                {
                    guests.add(new Guest(guestUsername, guestPassword, guestName));
                }
            }
        } 
        catch (SQLException sqle)
        {
            System.out.println(sqle.toString());
        }        
    }
    public void refreshBooking()
    {
        bookings.clear();
        String tableName = "BOOKING";
        String sqlQuery = ("SELECT * FROM JAVAUSER." + tableName);
        sendDBCommand(sqlQuery);
        try 
        {
            while (dbResults.next())
            {
                int roomID = Integer.parseInt(dbResults.getString(2));
                int room = 0;
                for (int i = 0; i < rooms.size(); i++)
                {
                    if (rooms.get(i).getRoomID() == roomID)
                    {
                        room = i;
                    }
                }
                int guestID = Integer.parseInt(dbResults.getString(3));
                int guest = 0;
                for (int i = 0; i < guests.size(); i++)
                {
                    if (guests.get(i).getGuestID() == guestID)
                    {
                        guest = i;
                    }
                }
                int BookingYear = Integer.parseInt(dbResults.getString(4));
                int CheckInDay = Integer.parseInt(dbResults.getString(5));
                int CheckOutDay = Integer.parseInt(dbResults.getString(6));
                bookings.add(new Booking(guests.get(guest), rooms.get(room), BookingYear, CheckInDay, CheckOutDay));
            }
            
        } 
        catch (SQLException sqle)
        {
            System.out.println(sqle.toString());
        }
    }
    public void refreshRoomService()
    {
        for (int i = 0; i < bookings.size(); i++)
        {
            bookings.get(i).roomServices.clear();
            String tableName = "ROOMSERVICE";
            String sqlQuery = ("SELECT * FROM JAVAUSER." + tableName);
            sendDBCommand(sqlQuery);
            
            try
            {
                while (dbResults.next())
                {
                    int bookingServiceID = Integer.parseInt(dbResults.getString(2));
                    String description = (dbResults.getString(3));
                    int price = Integer.parseInt(dbResults.getString(4));
                    if (bookingServiceID == bookings.get(i).bookingID)
                    {
                        RoomService r = new RoomService(description, price, bookings.get(i));
                        bookings.get(i).addRoomService(r);
                    }
                    else
                    {
                        break;
                    }
                }
            }
            catch (SQLException sqle)
            {
            System.out.println(sqle.toString());
            }
        }
    }
    public void start(Stage loginStage) 
    {
        //read the database
        refreshRoom();
        refreshEmployee();
        refreshGuest();
        refreshBooking();
        refreshRoomService();        
        
        login(loginStage);

    }
    public void login(Stage loginStage)
    {
        // Controls
        Text txtTitle = new Text("Welcome to Hotel Madison");
        txtTitle.setFont(Font.font(20.0));
        Label lblUsername = new Label("Username: ");
        Label lblPassword = new Label("Password: ");
        Button btnLogin = new Button("Login ->");
        TextField txtUsername = new TextField();
        PasswordField userPass = new PasswordField();    
        ComboBox userCombo = new ComboBox();
        userCombo.getItems().add("Guest");
        userCombo.getItems().add("Employee");
        //userCombo.getItems().add("Value Guest");
        userCombo.getSelectionModel().select(0);
        Button btnExit = new Button("Exit Program");
        Label lblError = new Label("");
        
        btnLogin.setOnAction(e ->
        {
            if (!(txtUsername.getText().isEmpty()) && !(userPass.getText().isEmpty()))
            {
                boolean operator = true;
                String choice = (String) userCombo.getValue();
                if (choice.equalsIgnoreCase("Employee"))
                {
                    for (int i = 0; i < employees.size(); i++)
                    {
                        if (employees.get(i).checkCredentials(txtUsername.getText(), 
                                userPass.getText()))
                        {
                            operator = false;
                            loginStage.close();
                            Stage employeeStage = new Stage();
                            employee(employeeStage, employees.get(i));
                            break;
                        }
                    }
                    if (operator)
                    {
                        lblError.setText("Invalid Employee Username or Password");
                    }
                }
                else if (choice.equalsIgnoreCase("Guest"))
                {    
                    for (int i = 0; i < guests.size(); i++)
                    {
                        if (guests.get(i).checkCredentials(txtUsername.getText(), 
                                userPass.getText()))
                        {
                            operator = false;
                            loginStage.close();
                            Stage guestStage = new Stage();
                            guest(guestStage, guests.get(i));
                            break;
                        }
                    }
                    if (operator)
                    {
                        lblError.setText("Invalid Guest Username or Password");
                    }
                }
            }
        });
        btnExit.setOnAction(e ->
        {
           loginStage.close(); 
        });
                
        // Pane
        GridPane loginPane = new GridPane();
        loginPane.setAlignment(Pos.CENTER);
        loginPane.setVgap(10);
        loginPane.setHgap(10);
        loginPane.add(txtTitle, 0, 0, 2, 1);
        loginPane.add(lblUsername, 0, 1);
        loginPane.add(lblPassword, 0, 2);
        loginPane.add(txtUsername, 1, 1);
        loginPane.add(userPass, 1, 2);
        loginPane.add(btnLogin, 1, 4);
        loginPane.add(userCombo, 1, 3);
        loginPane.add(btnExit, 1, 5);
        loginPane.add(lblError, 0, 6, 3, 1);
        
        // Scene
        Scene loginScene = new Scene(loginPane, 500, 300);
        
        // Stage
        loginStage.setTitle("Madison Hotel - Login");
        loginStage.setScene(loginScene);
        loginStage.show();
    }
    public void guest(Stage guestStage, Guest guest)
    {
        String guestName = guest.getGuestName();
        // Controls
        // Book Room Tab
        Label sceneTitle1 = new Label("  Welcome " + guestName);
        Label sceneTitle2 = new Label("  Welcome " + guestName);
        Label sceneTitle3 = new Label("  Welcome " + guestName);
        Label sceneTitle4 = new Label("  Welcome " + guestName);
        Label lblFreeRoom = new Label("Free Rooms: ");
        ListView lvFreeRoom = new ListView();
        for (int i = 0; i < rooms.size(); i++)
        {
            String booked = null;
            if (!(rooms.get(i).isBooked()) 
                    && rooms.get(i).getRoomActivity().equalsIgnoreCase("Active"))
            {
                booked = "[Available]";
                lvFreeRoom.getItems().add("Room " 
                + rooms.get(i).roomNumber 
                + ", " + rooms.get(i).printKitchOption()
                + ", \n" + rooms.get(i).printCoffeeOption()
                + ", " + booked);
            }
        }
        Label lbCheckYear = new Label("Check In Year: ");
        TextField txtCheckYear = new TextField();
        ComboBox comboCheckMonth = new ComboBox();
        comboCheckMonth.getItems().add("January");
        comboCheckMonth.getItems().add("February");
        comboCheckMonth.getItems().add("March");
        comboCheckMonth.getItems().add("April");
        comboCheckMonth.getItems().add("May");
        comboCheckMonth.getItems().add("June");
        comboCheckMonth.getItems().add("July");
        comboCheckMonth.getItems().add("August");
        comboCheckMonth.getItems().add("September");
        comboCheckMonth.getItems().add("October");
        comboCheckMonth.getItems().add("November");
        comboCheckMonth.getItems().add("December");
        comboCheckMonth.getSelectionModel().select(0);
        Label lbCheckDay = new Label("Check In Day: ");
         ComboBox comboCheckDay = new ComboBox();
        comboCheckDay.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", 
                "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19",
                "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", 
                "30", "31");
        comboCheckDay.getSelectionModel().select(0);
        Label lbCheckOut = new Label("Check Out Day: ");
        ComboBox comboCheckOutMonth = new ComboBox();
        comboCheckOutMonth.getItems().add("January");
        comboCheckOutMonth.getItems().add("February");
        comboCheckOutMonth.getItems().add("March");
        comboCheckOutMonth.getItems().add("April");
        comboCheckOutMonth.getItems().add("May");
        comboCheckOutMonth.getItems().add("June");
        comboCheckOutMonth.getItems().add("July");
        comboCheckOutMonth.getItems().add("August");
        comboCheckOutMonth.getItems().add("September");
        comboCheckOutMonth.getItems().add("October");
        comboCheckOutMonth.getItems().add("November");
        comboCheckOutMonth.getItems().add("December");
        comboCheckOutMonth.getSelectionModel().select(0);
        ComboBox comboCheckOutDay = new ComboBox();
        comboCheckOutDay.getItems().addAll("1", "2", "3", "4", "5", "6", "7", 
                "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", 
                "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", 
                "29", "30", "31");
        comboCheckOutDay.getSelectionModel().select(0);
        Button btnBookRoom = new Button("Book Room ^");
        Button btnLogout1 = new Button("<- Logout");       
        btnLogout1.setOnAction(e ->
        {
            guestStage.close();
            Stage loginStage = new Stage();
            login(loginStage);
        });
        btnBookRoom.setOnAction(e ->
        {
            String room = (String) lvFreeRoom.getSelectionModel().getSelectedItem();
            int year = Integer.parseInt(txtCheckYear.getText());
            int tempday = Integer.parseInt((String)comboCheckDay.getValue());
            //how many days have passed total in the year since this month started
            int tempmonth =0;
            int tempdayout = Integer.parseInt((String)comboCheckOutDay.getValue());
            int tempmonthout = 0;

            String result = (String)comboCheckMonth.getValue();
            switch (result) {
                case("January"):
                    tempmonth = 0;
                    break;
                case("February"):
                    tempmonth = 31;
                    break;
                case("March"):
                    tempmonth = 59;
                    break;
                case("April"):
                    tempmonth = 90;
                    break;
                case("May"):
                    tempmonth = 120;
                    break;
                case("June"):
                    tempmonth = 151;
                    break;
                case("July"):
                    tempmonth = 181;
                    break;
                case("August"):
                    tempmonth = 212;
                    break;
                case("September"):
                    tempmonth = 243;
                    break;
                case("October"):
                    tempmonth = 273;
                    break;
                case("November"):
                    tempmonth = 304;
                    break;
                case("December"):
                    tempmonth = 334;
                    break;
            }

            String result1 = (String)comboCheckOutMonth.getValue();
            switch (result1) {
                case("January"):
                    tempmonthout = 0;
                    break;
                case("February"):
                    tempmonthout = 31;
                    break;
                case("March"):
                    tempmonthout = 59;
                    break;
                case("April"):
                    tempmonthout = 90;
                    break;
                case("May"):
                    tempmonthout = 120;
                    break;
                case("June"):
                    tempmonthout = 151;
                    break;
                case("July"):
                    tempmonthout = 181;
                    break;
                case("August"):
                    tempmonthout = 212;
                    break;
                case("September"):
                    tempmonthout = 243;
                    break;
                case("October"):
                    tempmonthout = 273;
                    break;
                case("November"):
                    tempmonthout = 304;
                    break;
                case("December"):
                    tempmonthout = 334;
                    break;
            }
            //checkOut - checkIn will give us total # of days spent at hotel
            //only thing about that that doesnt work is if they stay over the year
            //can probably figure out how to change that if necessary
            int checkIn = tempmonth + tempday;
            int checkOut = tempmonthout + tempdayout;
            int number = 0;
            boolean operator = false;

            if (room != null && checkIn > 0 && checkOut > 0)
            {
                try
                {
                    number = Integer.parseInt(room.substring(5, 8));                        
                }
                catch (Exception f)
                {
                    number = Integer.parseInt(room.substring(5, 7));
                }
                for (int i = 0; i < rooms.size(); i++)
                {
                    if (rooms.get(i).roomNumber == number)
                    {
                        Booking b = new Booking(guest, rooms.get(i), year, 
                                checkIn, checkOut);
                        bookings.add(b);
                        operator = true;
                    }
                }
                if (operator)
                {
                    lvFreeRoom.getItems().clear();
                    for (int i = 0; i < rooms.size(); i++)
                    {
                        String booked = null;
                        if (!(rooms.get(i).isBooked()) 
                                && rooms.get(i).getRoomActivity().equalsIgnoreCase("Active"))
                        {
                            booked = "[Available]";
                            lvFreeRoom.getItems().add("Room " 
                            + rooms.get(i).roomNumber 
                            + ", " + rooms.get(i).printKitchOption()
                            + ", \n" + rooms.get(i).printCoffeeOption()
                            + ", " + booked);
                        }
                    }
                    txtCheckYear.clear();
                    comboCheckMonth.getSelectionModel().select(0);
                    comboCheckOutMonth.getSelectionModel().select(0);
                }
            }
        });
        
        // Room Report Tab
        Label lblRooms = new Label("Rooms: ");
        ListView lvRooms = new ListView();
        for (int i = 0; i < rooms.size(); i++)
        {
            lvRooms.getItems().add("Room " + rooms.get(i).roomNumber);
        }
        TextArea txtRoomReport = new TextArea();
        Button btnRoomReport = new Button("Room Report ^");
        Button btnLogout2 = new Button("<- Logout");
        btnLogout2.setOnAction(e ->
        {
            guestStage.hide();
            Stage loginStage = new Stage();
            login(loginStage);
        });
        btnRoomReport.setOnAction(e ->
        {
            int choice = lvRooms.getSelectionModel().getSelectedIndex();
            txtRoomReport.setText(rooms.get(choice).roomDescription());
        });
        
        // Personal Info Tab
        Label lblGuestNameInfo1 = new Label("Guest Name: ");
        Label lblGuestNameInfo2 = new Label(guest.getGuestName());
        Label lblGuestUsernameInfo1 = new Label("Guest Username: ");
        Label lblGuestUsernameInfo2 = new Label(guest.getUsername());
        Label lblGuestBookings = new Label("Guest Bookings: ");
        ListView lvGuestBookings = new ListView();
        int count = 0;
        for (int i = 0; i < bookings.size(); i++)
        {
            if (bookings.get(i).bookingGuest.equals(guest))
            {
                    lvGuestBookings.getItems().add("Room " 
                        + bookings.get(i).bookedRoom.roomNumber 
                        + "\nCheck in Year " + bookings.get(i).bookingYear 
                        + " \nCheck in Day: " + bookings.get(i).getDate(bookings.get(i).checkInDay) 
                        + " \nCheck out Day: " + bookings.get(i).getDate(bookings.get(i).checkOutDay));
                count++;
            }
        }
        if (count == 0)
        {
            lvGuestBookings.getItems().add("You currently have no Bookings");
        }
        Button btnChangeGuestName = new Button("Change Guest Name");
        //Button btnChangeGuestPassword = new Button("Change Guest Password");
        Button btnLogout3 = new Button("<- Logout");
        btnLogout3.setOnAction(e ->
        {
            guestStage.hide();
            Stage loginStage = new Stage();
            login(loginStage);
        });
        btnChangeGuestName.setOnAction(e ->
        {
            Stage changeGuestNameStage = new Stage();
            changeGuestName(changeGuestNameStage, guest);
            guestStage.close();
        });
        
//         btnChangeGuestPassword.setOnAction(e ->
//        {
//            Stage changeGuestPasswordStage = new Stage();
//            changeGuestPassword(changeGuestPasswordStage, guest);
//            guestStage.close();
//        });        
        
        // Room Service Tab
        Label lblGuestNameInfo3 = new Label("Guest Name: ");
        Label lblGuestNameInfo4 = new Label(guest.getGuestName());
        Label lblGuestUsernameInfo3 = new Label("Guest Username: ");
        Label lblGuestUsernameInfo4 = new Label(guest.getUsername());
        Label lblGuestCosts = new Label("Room Service Charges: ");
        ListView lvGuestCosts = new ListView();
        Label lblTotalCost = new Label();
        int count1 = 0;
        for (int i = 0; i < bookings.size(); i++)
        {
            if (bookings.get(i).bookingGuest.equals(guest))
            {
                lblTotalCost.setText("Total Cost: $" + bookings.get(i).getTotalCost());
                for (int j = 0; j < bookings.get(i).roomServices.size(); j++)
                {
                    lvGuestCosts.getItems().add("Room Number: " + bookings.get(i).bookedRoom.roomNumber + "\n" + bookings.get(i).roomServices.get(j).description
                        + ": $" + bookings.get(i).roomServices.get(j).price);
                        count1++;
                }
            }
        }
        if (count1 == 0)
        {
            lvGuestCosts.getItems().add("You currently have no Room Service Charges");
        }
        Button btnLogout4 = new Button("<- Logout");
        btnLogout4.setOnAction(e ->
        {
            guestStage.hide();
            Stage loginStage = new Stage();
            login(loginStage);
        });
        
        // Pane
        TabPane guestTabs = new TabPane();
        
        GridPane bookRoomPane = new GridPane();
        bookRoomPane.setAlignment(Pos.CENTER);
        bookRoomPane.setVgap(10);
        bookRoomPane.setHgap(10);
        bookRoomPane.add(sceneTitle1, 0, 0, 2, 1);
        bookRoomPane.add(lblFreeRoom, 0, 1);
        bookRoomPane.add(lvFreeRoom, 1, 1, 2, 1);
        bookRoomPane.add(lbCheckYear, 0, 2);
        bookRoomPane.add(txtCheckYear, 1, 2);
        bookRoomPane.add(comboCheckMonth,1,3);
        bookRoomPane.add(comboCheckDay,2,3);
        bookRoomPane.add(lbCheckDay, 0, 3);
        //bookRoomPane.add(txtCheckDay, 1, 3);
        bookRoomPane.add(comboCheckOutMonth,1,4);
        bookRoomPane.add(comboCheckOutDay,2,4);
        bookRoomPane.add(lbCheckOut, 0, 4);
        //bookRoomPane.add(txtCheckOut, 1, 4);
        bookRoomPane.add(btnBookRoom, 1, 5);
        bookRoomPane.add(btnLogout1,3, 5);
        
        GridPane roomReportPane = new GridPane();
        roomReportPane.setAlignment(Pos.CENTER);
        roomReportPane.setVgap(10);
        roomReportPane.setHgap(10);
        roomReportPane.add(sceneTitle2, 0, 0, 2, 1);
        roomReportPane.add(lblRooms, 2, 0);
        roomReportPane.add(lvRooms, 1, 1, 1, 15);
        roomReportPane.add(txtRoomReport, 2, 1, 1, 15);
        roomReportPane.add(btnRoomReport, 1, 16);
        roomReportPane.add(btnLogout2, 2, 16);
        
        GridPane personalInfoPane = new GridPane();
        personalInfoPane.setOnMouseEntered(e ->
        {
            lvGuestBookings.getItems().clear();
            int thisCount = 0;
            for (int i = 0; i < bookings.size(); i++)
            {
                if (guest.equals(bookings.get(i).bookingGuest))
                {
                    lvGuestBookings.getItems().add("Room " 
                        + bookings.get(i).bookedRoom.roomNumber 
                        + "\nCheck in Year " + bookings.get(i).bookingYear 
                        + " \nCheck in Day: " + bookings.get(i).getDate(bookings.get(i).checkInDay) 
                        + " \nCheck out Day: " + bookings.get(i).getDate(bookings.get(i).checkOutDay));
                    thisCount++;
                }
            }
            if (thisCount == 0)
            {
                lvGuestBookings.getItems().add("You currently have no Bookings");
            }
        });
        personalInfoPane.setAlignment(Pos.CENTER);
        personalInfoPane.setVgap(10);
        personalInfoPane.setHgap(10);
        personalInfoPane.add(sceneTitle3, 0, 0, 2, 1);
        personalInfoPane.add(lblGuestNameInfo1, 0, 1);
        personalInfoPane.add(lblGuestNameInfo2, 1, 1);
        personalInfoPane.add(lblGuestUsernameInfo1, 0, 2);
        personalInfoPane.add(lblGuestUsernameInfo2, 1, 2);
        personalInfoPane.add(lblGuestBookings, 0, 3);
        personalInfoPane.add(lvGuestBookings, 1, 3, 1, 15);
        personalInfoPane.add(btnChangeGuestName, 0, 19);
        //personalInfoPane.add(btnChangeGuestPassword, 1, 19);
        personalInfoPane.add(btnLogout3, 3, 19);
        
        GridPane roomServicePane = new GridPane();
        roomServicePane.setAlignment(Pos.CENTER);
        roomServicePane.setVgap(10);
        roomServicePane.setHgap(10); 
        roomServicePane.add(sceneTitle4, 0, 0, 2, 1);
        roomServicePane.add(lblGuestNameInfo3, 0, 1);
        roomServicePane.add(lblGuestNameInfo4, 1, 1);
        roomServicePane.add(lblGuestUsernameInfo3, 0, 2);
        roomServicePane.add(lblGuestUsernameInfo4, 1, 2);
        roomServicePane.add(lvGuestCosts, 1, 3, 1, 15);
        roomServicePane.add(lblGuestCosts, 0, 4);
        roomServicePane.add(lblTotalCost, 1, 19);
        roomServicePane.add(btnLogout4, 3, 19);
        
        for (int i = 0; i < valueGuests.size(); i++)
        {
            if (valueGuests.get(i).equals(guest))
            {
                Tab roomServiceChargeTab = new Tab("Room Service Charge");
                roomServiceChargeTab.setContent(roomServicePane);
                guestTabs.getTabs().add(roomServiceChargeTab);
            }
        }
        
        Tab bookRoomTab = new Tab("Book Room");
        bookRoomTab.setContent(bookRoomPane);
        guestTabs.getTabs().add(bookRoomTab);
        
        Tab roomReportTab = new Tab("Room Report");
        roomReportTab.setContent(roomReportPane);
        guestTabs.getTabs().add(roomReportTab);
        
        Tab personalInfoTab = new Tab("Personal Information");
        personalInfoTab.setContent(personalInfoPane);
        guestTabs.getTabs().add(personalInfoTab);
        
        // Scene
        Scene guestScene = new Scene(guestTabs, 550, 600);
        
        // Stage
        guestStage.setTitle("Madison Hotel - Guest Menu");
        guestStage.setScene(guestScene);
        guestStage.show();
    }
    public void employee(Stage employeeStage, Employee emp)
    {
        String employeeName = emp.getEmployeeName();
        // Controls        
        // Booking Report Tab
        ListView lvBookings = new ListView();
        for (int i = 0; i < bookings.size(); i++)
        {
            lvBookings.getItems().add("Room " 
                    + bookings.get(i).bookedRoom.roomNumber + " booked by " 
                    + bookings.get(i).bookingGuest.getGuestName()); 
        }
        TextArea txtBookingReport = new TextArea();
        Button btnViewBooking = new Button("Booking Report ^");
        Button btnLogout2 = new Button("Logout ->");
        Button btnAllReports = new Button("All booked rooms report ->");
        btnLogout2.setOnAction(e ->
        {
            employeeStage.close();
            Stage loginStage = new Stage();
            login(loginStage);
        });
        btnViewBooking.setOnAction(e ->
        {
            if (!(lvBookings.getSelectionModel().isEmpty()))
            {
                int choice = lvBookings.getSelectionModel().getSelectedIndex();
                txtBookingReport.setText(bookings.get(choice).describeBooking());
            }
        });
        
        btnAllReports.setOnAction(e -> 
        {
            int count = 0;
            txtBookingReport.clear();
            for (int i=0; i<bookings.size(); i++) {
            if (bookings.get(i).bookedRoom.isBooked()) {
                if (count == 0) {
                    txtBookingReport.appendText("All Booked Rooms:\n");
                }
                txtBookingReport.appendText(bookings.get(i).shortBookingDescription() + "\n");
                count++;
            }
        }
        if (count == 0) {
            txtBookingReport.appendText("There are currently no booked rooms!");
        }
        });
        
        // Check Out Tab
        Label lblSelectGuest = new Label("Select a Guest: ");
        ListView lvGuests = new ListView();
        for (int i = 0; i < guests.size(); i++)
        {
            for (int j = 0; j < valueGuests.size(); j++)
            {
                if (guests.get(i).getGuestID() == valueGuests.get(j).getGuestID())
                {
                    lvGuests.getItems().add(guests.get(i).getGuestName() + " [Value]");
                    break;
                }
                else
                {
                    lvGuests.getItems().add(guests.get(i).getGuestName() + " [Regular]");
                    break;
                }
            }
        }
        Label lblSelectRoom = new Label("Select a Booked Room: ");
        ListView lvRooms = new ListView();
        lvGuests.setOnMouseReleased(e ->
        {
            boolean operator = false;
            int regular = 0;
            int value = 0;
            lvRooms.getItems().clear();
            String name = (String) lvGuests.getSelectionModel().getSelectedItem();
            if (name.substring(name.length() - 9, name.length()).equalsIgnoreCase("[Regular]"))
            {
                for (int i = 0; i < guests.size(); i++)
                {
                    if (name.substring(0, name.length() - 10).equalsIgnoreCase(guests.get(i).getGuestName()))
                    {
                        operator = true;
                        regular = i;
                    }
                }
            }
            else if (name.substring(name.length() - 7, name.length()).equalsIgnoreCase("[Value]"))
            {
                for (int i = 0; i < valueGuests.size(); i++)
                {
                    if (name.substring(0, name.length() - 8).equalsIgnoreCase(valueGuests.get(i).getGuestName()))
                    {
                        operator = false;
                        value = i;
                    }
                }
            }
            if (operator)
            {
                for (int i = 0; i < bookings.size(); i++)
                {
                    if (bookings.get(i).bookingGuest.equals(guests.get(regular))
                            && bookings.get(i).bookedRoom.isBooked())
                    {
                        lvRooms.getItems().add(bookings.get(i).bookedRoom.getRoomNumber());
                    }
                }
            }
            else
            {
                for (int i = 0; i < bookings.size(); i++)
                {
                    if (bookings.get(i).bookingGuest.equals(valueGuests.get(value))
                            && bookings.get(i).bookedRoom.isBooked())
                    {
                        lvRooms.getItems().add(bookings.get(i).bookedRoom.getRoomNumber());
                    }
                }
            }
        });
        Button btnCheckOut = new Button("Check Guest out of Room");
        Button btnLogout5 = new Button("Logout ->");
        btnLogout5.setOnAction(e ->
        {
            employeeStage.hide();
            Stage loginStage = new Stage();
            login(loginStage);
        });
        btnCheckOut.setOnAction(e ->
        {
            if (!(lvRooms.getSelectionModel().isEmpty()) 
                    && !(lvGuests.getSelectionModel().isEmpty()))
            {
                int roomNumber = (int) lvRooms.getSelectionModel().getSelectedItem();
                for (int i = 0; i < bookings.size(); i++)
                {
                    if (roomNumber == bookings.get(i).bookedRoom.getRoomNumber())
                    {
                        bookings.get(i).endBooking();
                    }
                }
                boolean operator = false;
                int regular = 0;
                int value = 0;
                lvRooms.getItems().clear();
                String name = (String) lvGuests.getSelectionModel().getSelectedItem();
                if (name.substring(name.length() - 9, name.length()).equalsIgnoreCase("[Regular]"))
                {
                    for (int i = 0; i < guests.size(); i++)
                    {
                        if (name.substring(0, name.length() - 10).equalsIgnoreCase(guests.get(i).getGuestName()))
                        {
                            operator = true;
                            regular = i;
                        }
                    }
                }
                else if (name.substring(name.length() - 7, name.length()).equalsIgnoreCase("[Value]"))
                {
                    for (int i = 0; i < valueGuests.size(); i++)
                    {
                        if (name.substring(0, name.length() - 8).equalsIgnoreCase(valueGuests.get(i).getGuestName()))
                        {
                            operator = false;
                            value = i;
                        }
                    }
                }
                if (operator)
                {
                    for (int i = 0; i < bookings.size(); i++)
                    {
                        if (bookings.get(i).bookingGuest.equals(guests.get(regular))
                                && bookings.get(i).bookedRoom.isBooked())
                        {
                            lvRooms.getItems().add(bookings.get(i).bookedRoom.getRoomNumber());
                        }
                    }
                }
                else
                {
                    for (int i = 0; i < bookings.size(); i++)
                    {
                        if (bookings.get(i).bookingGuest.equals(valueGuests.get(value))
                                && bookings.get(i).bookedRoom.isBooked())
                        {
                            lvRooms.getItems().add(bookings.get(i).bookedRoom.getRoomNumber());
                        }
                    }
                }
            }
        });
        
        //Room Service Tab
        Label lblSelectValueGuest = new Label("Select a Value Guest:");
        ListView lvGuests2 = new ListView();
        for (int i = 0; i < valueGuests.size(); i++)
        {
            lvGuests2.getItems().add(valueGuests.get(i).getGuestName() + " [Value]");
        }
        Label lblSelectValueRoom = new Label("Select a booked room:");
        ListView lvBookedRooms = new ListView();
        lvGuests2.setOnMousePressed(e -> 
        {
            int guestChoice = lvGuests2.getSelectionModel().getSelectedIndex();
            for (int i = 0; i < bookings.size(); i ++) 
            {
                if (bookings.get(i).bookingGuest.equals(valueGuests.get(guestChoice)) 
                        && bookings.get(i).bookedRoom.isBooked()) 
                {
                    lvBookedRooms.getItems().add(bookings.get(i).bookedRoom.getRoomNumber());
                }
            }   
        });
        TextField txtPrice = new TextField();
        Label lblPrice = new Label("Room Service Charge($$): ");
        Label lblDesc = new Label("Description: ");
        TextField txtDescription = new TextField();
        Label lblMessage = new Label("");
        Label lblPriceMessage = new Label("");
        Label lblDescMessage = new Label("");
        Button btnCreateService = new Button("Create Room Service Charge ->");
        btnCreateService.setOnAction(e -> 
        {
            if (!(lvGuests2.getSelectionModel().isEmpty() && !(lvBookedRooms.getSelectionModel().isEmpty()
                    && !(txtPrice.getText().isEmpty()) && !(txtDescription.getText().isEmpty()))))
            {
                int selectedRoomNumber = (int)lvBookedRooms.getSelectionModel().getSelectedItem();
                double serviceCharge = 0.0;
                if (validRoomPrice(txtPrice.getText())) 
                {
                    serviceCharge = Double.parseDouble(txtPrice.getText());
                    lblPriceMessage.setText("");
                }
                if (validRoomPrice(txtPrice.getText()) == false || txtPrice.getText().length() == 0) 
                {
                    lblPriceMessage.setText("Please enter valid service price!");
                }

                String desc = "";
                if (txtDescription.getText().length() != 0) 
                {
                    desc += (String)txtDescription.getText();
                    lblDescMessage.setText("");
                } 
                else 
                {
                    lblDescMessage.setText("Please enter valid description!");
                }
                if (validRoomPrice(txtPrice.getText()) && txtPrice.getText().length() != 0 && txtDescription.getText().length() != 0) 
                {
                    for (int i= 0; i<bookings.size(); i++) 
                    {
                        if (bookings.get(i).bookedRoom.roomNumber == selectedRoomNumber) 
                        {
                            //bookings.get(i).roomServices.add(new RoomService(desc, serviceCharge, bookings.get(i)));
                            bookings.get(i).addRoomService(new RoomService(desc, serviceCharge, bookings.get(i)));
                            lblMessage.setText("Successfully added room service to room " 
                                    + bookings.get(i).bookedRoom.roomNumber);
                            txtPrice.clear();
                            txtDescription.clear();
                            lvBookedRooms.getItems().clear();
                        }
                    }
                }
            }
        });
        Button btnLogout12 = new Button("Logout ->");
        btnLogout12.setOnAction(e ->
        {
            employeeStage.close();
            Stage loginStage = new Stage();
            login(loginStage);
        });
        
        // Create Guest Tab
        Label lblSelectType = new Label("Type of Guest: ");
        ComboBox guestTypeCombo = new ComboBox();
        guestTypeCombo.getItems().add("Guest");
        guestTypeCombo.getItems().add("Value Guest");
        guestTypeCombo.getSelectionModel().select(0);
        Label lblGuestName = new Label("Guest's Name: ");
        TextField txtGuestName = new TextField();
        Label lblGuestUsername = new Label("Guest's Username: ");
        TextField txtGuestUsername = new TextField();
        Label lblGuestPassword = new Label("Guest's Password: ");
        TextField txtGuestPassword = new TextField();
        Label lblGuestList = new Label("All Guests: ");
        ListView lvGuestList = new ListView();
        Label lblError1 = new Label("");
        for (int i = 0; i < guests.size(); i++)
        {
            for (int j = 0; j < valueGuests.size(); j++)
            {
                if (guests.get(i).getGuestID() == valueGuests.get(j).getGuestID())
                {
                    lvGuestList.getItems().add(guests.get(i).getGuestName() + " [Value]");
                    break;
                }
                else
                {
                    lvGuestList.getItems().add(guests.get(i).getGuestName() + " [Regular]");
                    break;
                }
            }
        }
        Button btnCreateGuest = new Button("Create Guest");
        Button btnLogout3 = new Button("Logout ->");
        btnLogout3.setOnAction(e ->
        {
            employeeStage.close();
            Stage loginStage = new Stage();
            login(loginStage);
        });
        btnCreateGuest.setOnAction(e ->
        {
            String type = (String) guestTypeCombo.getValue();
            if (!(txtGuestName.getText().equalsIgnoreCase("")) 
                    && !(txtGuestUsername.getText().equalsIgnoreCase("")) 
                    && !(txtGuestPassword.getText().equalsIgnoreCase(""))
                    && type.equalsIgnoreCase("Guest")
                    && createPassword(txtGuestPassword.getText()) == 0)
            {
                Guest g = new Guest(txtGuestUsername.getText(), 
                        txtGuestPassword.getText(), txtGuestName.getText());
                guests.add(g);
                lvGuestList.getItems().add(g.getGuestName() + " [Regular]");
                txtGuestName.clear();
                txtGuestUsername.clear();
                txtGuestPassword.clear();
                lblError1.setText("");                
            }
            else if (!(txtGuestName.getText().equalsIgnoreCase("")) 
                    && !(txtGuestUsername.getText().equalsIgnoreCase("")) 
                    && !(txtGuestPassword.getText().equalsIgnoreCase(""))
                    && type.equalsIgnoreCase("Value Guest")
                    && createPassword(txtGuestPassword.getText()) == 0)
            {
                ValueGuest v = new ValueGuest(txtGuestUsername.getText(), 
                        txtGuestPassword.getText(), txtGuestName.getText());
                valueGuests.add(v);
                guests.add(v);
                lvGuestList.getItems().add(v.getGuestName() + " [Value]");
                txtGuestName.clear();
                txtGuestUsername.clear();
                txtGuestPassword.clear();
                lblError1.setText("");                
            }
            else
            {
                lblError1.setText("The new password must be different from the old password \n"
                       + "The new password cannot start with a number \n"
                       + "The new password must contain one number \n"
                       + "The new password must contain one capital letter");
            }
        });
        
        // Create Employee Tab
        Label lblEmployeeName = new Label("Employee's Name: ");
        TextField txtEmployeeName = new TextField();
        Label lblEmployeeUsername = new Label("Employee's Username: ");
        TextField txtEmployeeUsername = new TextField();
        Label lblEmployeePassword = new Label("Employee's Password: ");
        TextField txtEmployeePassword = new TextField();
        Label lblEmployeeList = new Label("Employees: ");
        ListView lvEmployeeList = new ListView();
        for (int i = 0; i < employees.size(); i++)
        {
            lvEmployeeList.getItems().add(employees.get(i).getEmployeeName());
        }
        Button btnCreateEmployee = new Button("Create Employee");
        Button btnLogout4 = new Button("Logout ->");
        Label lblError = new Label("");
        btnLogout4.setOnAction(e ->
        {
            employeeStage.close();
            Stage loginStage = new Stage();
            login(loginStage);
        });
        btnCreateEmployee.setOnAction(e ->
        {
            if (!(txtEmployeeName.getText().isEmpty()) 
                    && !(txtEmployeeUsername.getText().isEmpty())
                    && !(txtEmployeePassword.getText().isEmpty())
                    && createPassword(txtEmployeePassword.getText()) == 0)
            {
                Employee z = new Employee(txtEmployeeUsername.getText(), 
                        txtEmployeePassword.getText(), 
                        txtEmployeeName.getText());
                employees.add(z);
                lvEmployeeList.getItems().add(z.getEmployeeName());
                txtEmployeeName.clear();
                txtEmployeePassword.clear();
                txtEmployeeUsername.clear();
                lblError.setText("");
            }
            else
            {
                lblError.setText("The new password must be different from the old password \n"
                       + "The new password cannot start with a number \n"
                       + "The new password must contain one number \n"
                       + "The new password must contain one capital letter");
            }
        });
        
        // Edit Room Tab
        Label sceneTitle1 = new Label("  Welcome " + employeeName);
        Label sceneTitle2 = new Label("  Welcome " + employeeName);
        Label sceneTitle3 = new Label("  Welcome " + employeeName);
        Label sceneTitle4 = new Label("  Welcome " + employeeName);
        Label sceneTitle5 = new Label("  Welcome " + employeeName);
        Label lblBookings = new Label("Booking Report: ");
        Button btnCreateRoom = new Button("Create a new Room");
        Button btnEditRoom = new Button("Edit a Room");
        Button btnLogout1 = new Button("Logout ->");
        
        // Emp PII Tab
        Label lblEmNameInfo1 = new Label("Employee Name: ");
        Label lblEmNameInfo2 = new Label(employeeName);
        Label lblEmUsernameInfo1 = new Label("Employee Username: ");
        Label lblEmUsernameInfo2 = new Label(emp.getUsername());
        Button btnChangeEmpName = new Button("Change Employee Name");
        Button btnChangeEmpPassword = new Button("Change Employee Password");
        Button btnLogout6 = new Button("Logout ->");
        btnLogout6.setOnAction(e ->
        {
            employeeStage.close();
            Stage loginStage = new Stage();
            login(loginStage);
        });
        btnChangeEmpName.setOnAction(e ->
        {
            Stage changeGuestNameStage = new Stage();
            changeGuestName(changeGuestNameStage, emp);
            employeeStage.close();
        });
        btnChangeEmpPassword.setOnAction(e ->
        {
            Stage changeGuestPasswordStage = new Stage();
            changeGuestPassword(changeGuestPasswordStage, emp);
            employeeStage.close();
        });
        
        
        // Pane
        TabPane employeeTabs = new TabPane();       
        
        GridPane bookReportPane = new GridPane();
        bookReportPane.setOnMouseEntered(e ->
        {
            lvBookings.getItems().clear();
            txtBookingReport.clear();
            for (int i = 0; i < bookings.size(); i++)
            {
                lvBookings.getItems().add("Room " 
                        + bookings.get(i).bookedRoom.roomNumber + " booked by " 
                        + bookings.get(i).bookingGuest.getGuestName()); 
            }
        });
        bookReportPane.setAlignment(Pos.CENTER);
        bookReportPane.setVgap(10);
        bookReportPane.setHgap(10);
        bookReportPane.add(sceneTitle1, 0, 0, 2, 1);
        bookReportPane.add(lblBookings, 2, 0);
        bookReportPane.add(lvBookings, 1, 1, 1, 15);
        bookReportPane.add(txtBookingReport, 2, 1, 1, 15);
        bookReportPane.add(btnViewBooking, 1, 16);
        bookReportPane.add(btnLogout2, 2, 16);
        bookReportPane.add(btnAllReports, 1, 17);
        
        GridPane checkOutPane = new GridPane();
        checkOutPane.setOnMouseEntered(e ->
        {
            lvRooms.getItems().clear();
            lvGuests.getItems().clear();
            for (int i = 0; i < guests.size(); i++)
            {
                for (int j = 0; j < valueGuests.size(); j++)
                {
                    if (guests.get(i).getGuestID() == valueGuests.get(j).getGuestID())
                    {
                        lvGuests.getItems().add(guests.get(i).getGuestName() + " [Value]");
                        break;
                    }
                    else
                    {
                        lvGuests.getItems().add(guests.get(i).getGuestName() + " [Regular]");
                        break;
                    }
                }
            }
        });
        checkOutPane.setAlignment(Pos.CENTER);
        checkOutPane.setVgap(10);
        checkOutPane.setHgap(10);
        checkOutPane.add(sceneTitle2, 0, 0);
        checkOutPane.add(lblSelectGuest, 0, 1);
        checkOutPane.add(lblSelectRoom, 1, 1);
        checkOutPane.add(lvGuests, 0, 2, 1, 15);
        checkOutPane.add(lvRooms, 1, 2, 1, 15);
        checkOutPane.add(btnCheckOut, 1, 17);
        checkOutPane.add(btnLogout5, 2, 17);
        
        GridPane roomServicePane = new GridPane();
        roomServicePane.setOnMouseEntered(e -> 
        {
            lblMessage.setText("");
            lvGuests2.getItems().clear();
            for (int i = 0; i < valueGuests.size(); i++)
            {
                lvGuests2.getItems().add(valueGuests.get(i).getGuestName() + " [Value]");
            }
            //valuebookings.clear();
            lvBookedRooms.getItems().clear();
            //comboSelectRoom.getItems().clear();
            txtPrice.clear();
            txtDescription.clear();
        });
        roomServicePane.setVgap(10);
        roomServicePane.setHgap(10);
        roomServicePane.add(lblSelectValueGuest,0,1);
        roomServicePane.add(lvGuests2,0,2,1,15);
        roomServicePane.add(lblSelectValueRoom,3,1);
        roomServicePane.add(lvBookedRooms, 3, 2, 1, 15);
        //roomServicePane.add(comboSelectRoom,3,2);
        roomServicePane.add(lblPrice, 4, 3);
        roomServicePane.add(txtPrice,4,4);
        roomServicePane.add(lblPriceMessage, 4, 5);
        roomServicePane.add(lblDesc, 4, 6);
        roomServicePane.add(txtDescription, 4, 7);
        roomServicePane.add(lblDescMessage, 4, 8);
        roomServicePane.add(btnCreateService, 4, 10);
        roomServicePane.add(lblMessage, 3, 18, 2, 1);
        roomServicePane.add(btnLogout12, 4, 13);
        
        GridPane createGuestPane = new GridPane();
        createGuestPane.setAlignment(Pos.CENTER);
        createGuestPane.setVgap(10);
        createGuestPane.setHgap(10);
        createGuestPane.add(sceneTitle3, 0, 0);
        createGuestPane.add(lblSelectType, 0, 1);
        createGuestPane.add(guestTypeCombo, 1, 1);
        createGuestPane.add(lblGuestList, 2, 0);
        createGuestPane.add(lvGuestList, 2, 1, 1, 15);
        createGuestPane.add(lblGuestName, 0, 2);
        createGuestPane.add(txtGuestName, 1, 2);
        createGuestPane.add(lblGuestUsername, 0, 3);
        createGuestPane.add(txtGuestUsername, 1, 3);
        createGuestPane.add(lblGuestPassword, 0, 4);
        createGuestPane.add(txtGuestPassword, 1, 4);
        createGuestPane.add(btnCreateGuest, 0, 6);
        createGuestPane.add(lblError1, 0, 7, 2, 1);
        createGuestPane.add(btnLogout3, 1, 6);
        
        GridPane createEmployeePane = new GridPane();
        createEmployeePane.setAlignment(Pos.CENTER);
        createEmployeePane.setVgap(10);
        createEmployeePane.setHgap(10);
        createEmployeePane.add(sceneTitle4, 0, 0);
        createEmployeePane.add(lblEmployeeName, 0, 1);
        createEmployeePane.add(txtEmployeeName, 1, 1);
        createEmployeePane.add(lblEmployeeList, 2, 0);
        createEmployeePane.add(lvEmployeeList, 2, 1, 1, 15);
        createEmployeePane.add(lblEmployeeUsername, 0, 2);
        createEmployeePane.add(txtEmployeeUsername, 1, 2);
        createEmployeePane.add(lblEmployeePassword, 0, 3);
        createEmployeePane.add(txtEmployeePassword, 1, 3);
        createEmployeePane.add(btnCreateEmployee, 0, 5);
        createEmployeePane.add(lblError, 0, 6, 2, 1);
        createEmployeePane.add(btnLogout4, 1, 5);
        
        GridPane editRoomPane = new GridPane();
        editRoomPane.setAlignment(Pos.CENTER);
        editRoomPane.setVgap(10);
        editRoomPane.setHgap(10);
        editRoomPane.add(sceneTitle5, 0, 0);
        editRoomPane.add(btnCreateRoom, 0, 1);
        editRoomPane.add(btnEditRoom, 0, 2);
        editRoomPane.add(btnLogout1, 0, 5);
       
        btnLogout1.setOnAction(e ->
        {
            employeeStage.close();
            Stage loginStage = new Stage();
            login(loginStage);
        });
        btnCreateRoom.setOnAction(e ->
        {
            employeeStage.hide();
            Stage createRoomStage = new Stage();
            createRoom(createRoomStage, emp);
        });
        btnEditRoom.setOnAction(e -> 
        {
            employeeStage.hide();
            Stage editRoomStage = new Stage();
            selectRoom(editRoomStage, emp);
        });
        
        // Emp PII controls
        GridPane personalInfoPane = new GridPane();        
        personalInfoPane.setAlignment(Pos.CENTER);
        personalInfoPane.setVgap(10);
        personalInfoPane.setHgap(10);
        personalInfoPane.add(sceneTitle3, 0, 0, 2, 1);
        personalInfoPane.add(lblEmNameInfo1, 0, 1);
        personalInfoPane.add(lblEmNameInfo2, 1, 1);
        personalInfoPane.add(lblEmUsernameInfo1, 0, 2);
        personalInfoPane.add(lblEmUsernameInfo2, 1, 2);
        personalInfoPane.add(btnChangeEmpName, 0, 5);
        personalInfoPane.add(btnChangeEmpPassword, 1, 5);
        personalInfoPane.add(btnLogout6, 3, 5);

        
        Tab bookReportTab = new Tab("Booking Report");
        bookReportTab.setContent(bookReportPane);
        employeeTabs.getTabs().add(bookReportTab);
        
        Tab roomServiceTab = new Tab("Room Service");
        roomServiceTab.setContent(roomServicePane);
        employeeTabs.getTabs().add(roomServiceTab);
        
        Tab checkOutTab = new Tab("Check Guest Out");
        checkOutTab.setContent(checkOutPane);
        employeeTabs.getTabs().add(checkOutTab);
        
        Tab createGuestTab = new Tab("Create Guest Account");
        createGuestTab.setContent(createGuestPane);
        employeeTabs.getTabs().add(createGuestTab);
        
        Tab createEmployeeTab = new Tab("Create Employee Account");
        createEmployeeTab.setContent(createEmployeePane);
        employeeTabs.getTabs().add(createEmployeeTab);
        
        Tab editRoomTab = new Tab("Create/Edit Room");
        editRoomTab.setContent(editRoomPane);
        employeeTabs.getTabs().add(editRoomTab);
        
        Tab PII = new Tab("P.I.I");
        PII.setContent(personalInfoPane);
        employeeTabs.getTabs().add(PII);
        
        // Scene
        Scene employeeScene = new Scene(employeeTabs, 750, 600);
        
        // Stage
        employeeStage.setTitle("Madison Hotel - Employee Menu");
        employeeStage.setScene(employeeScene);
        employeeStage.show();
    }
    public void createRoom(Stage createRoomStage, Employee emp)
    {
        // Controls
        Label lblBed = new Label("  Bed: ");
        ComboBox bedCombo = new ComboBox();
        bedCombo.getItems().add("1x Queen Bed");
        bedCombo.getItems().add("2x Queen Bed");
        bedCombo.getItems().add("1x King Bed");
        bedCombo.getSelectionModel().select(0);
        Label lblKitch = new Label("  Kitchen: ");
        ComboBox kitchCombo = new ComboBox();
        kitchCombo.getItems().add("Microwave");
        kitchCombo.getItems().add("Fridge + Microwave");
        kitchCombo.getSelectionModel().select(0);
        Label lblCoffee = new Label("  Coffee: ");
        ComboBox coffeeCombo = new ComboBox();
        coffeeCombo.getItems().add("1-Cup Std. Coffee Machine");
        coffeeCombo.getItems().add("Keurig Hot K200 Machine");
        coffeeCombo.getSelectionModel().select(0);
        Label lblAccess = new Label("  Accessibility: ");
        ComboBox accessCombo = new ComboBox();
        accessCombo.getItems().add("Standard Room");
        accessCombo.getItems().add("Enhanced Accessibility Room");
        accessCombo.getSelectionModel().select(0);
        Label lblRoomNumber = new Label("  Room Number: ");
        TextField txtRoomNumber = new TextField();
        Label lblRoomPrice = new Label("  Price per Night");
        TextField txtRoomPrice = new TextField();
        Button btnAddRoom = new Button("Add Room ->");
        Button btnEmpMenu = new Button("Employee Menu ->");
        Label lblOutput = new Label("");
        Label lblRoomNumberNot = new Label();
        Label lblRoomPriceNot = new Label();
        ListView lvOutput = new ListView();
        for (int i = 0; i < rooms.size(); i++)
        {
            String booked = null;
            if (rooms.get(i).isBooked())
            {
                booked = "[Booked]";
            }
            else
            {
                booked = "[Available]";
            }
            lvOutput.getItems().add("Room " 
                    + rooms.get(i).roomNumber 
                    + " Booking Qty: " 
                    + rooms.get(i).getRoomBookedQuantity() 
                    + " " + booked);
        }
        
        btnEmpMenu.setOnAction(e ->
        {
            createRoomStage.hide();
            Stage employeeStage = new Stage();
            employee(employeeStage, emp);
        });
        
        btnAddRoom.setOnAction(e ->
        {
            String bed = (String) bedCombo.getValue();
            int bedChoice = 0;
            if (bed.equalsIgnoreCase("1x Queen Bed"))
            {
                bedChoice = 0;
            }
            else if (bed.equalsIgnoreCase("2x Queen Bed"))
            {
                bedChoice = 1;
            }
            else if (bed.equalsIgnoreCase("1x King Bed"))
            {
                bedChoice = 2;
            }
            String kitch = (String) kitchCombo.getValue();
            int kitchChoice = 0;
            if (kitch.equalsIgnoreCase("Microwave"))
            {
                kitchChoice = 0;
            }
            else if (kitch.equalsIgnoreCase("Fridge + Microwave"))
            {
                kitchChoice = 1;
            }
            String coffee = (String) coffeeCombo.getValue();
            int coffeeChoice = 0;
            if (coffee.equalsIgnoreCase("1-Cup Std. Coffee Machine"))
            {
                coffeeChoice = 0;
            }
            else if (coffee.equalsIgnoreCase("Keurig Hot K200 Machine"))
            {
                coffeeChoice = 1;
            }
            String access = (String) accessCombo.getValue();
            int accessChoice = 0;
            if (access.equalsIgnoreCase("Standard Room"))
            {
                accessChoice = 0;
            }
            else if (access.equalsIgnoreCase("Enhanced Accessibility Room"))
            {
                accessChoice = 1;
            }
            boolean proceed = true;
            int roomNumber = 0;
            for (int i = 0; i < rooms.size(); i++)
            {
                if (validRoomNumber(txtRoomNumber.getText())) 
                {
                    if (Integer.parseInt(txtRoomNumber.getText()) == rooms.get(i).roomNumber)
                    {
                        proceed=false;
                    }
                }
            }
            if (proceed == false) 
            {
                lblRoomNumberNot.setText("Sorry, that room number is taken!");
            }
            if (proceed && (validRoomNumber(txtRoomNumber.getText()) == false || txtRoomNumber.getText().length() == 0))
            {
                lblRoomNumberNot.setText("Please enter valid room #");
            }
            if (proceed && (validRoomNumber(txtRoomNumber.getText())) && txtRoomNumber.getText().length() != 0) 
            {
                roomNumber = Integer.parseInt(txtRoomNumber.getText());
                lblRoomNumberNot.setText("");
            }
            double roomPrice = 0;
            if (validRoomPrice(txtRoomPrice.getText()) && txtRoomPrice.getText().length() != 0) 
            {
                roomPrice = Double.parseDouble(txtRoomPrice.getText());
                lblRoomPriceNot.setText("");
            } 
            else 
            {
                lblRoomPriceNot.setText("Please enter valid room price");
            }
            if (validRoomNumber(txtRoomNumber.getText()) && validRoomPrice(txtRoomPrice.getText())) 
            {
                Room r = new Room(bedChoice, kitchChoice, coffeeChoice, 
                        accessChoice, roomNumber, roomPrice);
                rooms.add(r);
                for (int i = 0; i < rooms.size(); i++)
                {
                    if (rooms.get(i).equals(r))
                    {
                        String booked = null;
                        if (rooms.get(i).isBooked())
                        {
                            booked = "[Booked]";
                        }
                        else
                        {
                            booked = "[Available]";
                        }
                        lvOutput.getItems().add("Room " 
                                + rooms.get(i).roomNumber 
                                + " Booking Qty: " 
                                + rooms.get(i).getRoomBookedQuantity() 
                                + " " + booked);
                    }
                }
                lblOutput.setText("Room successfully created!");
                txtRoomNumber.clear();
                txtRoomPrice.clear();
                lblRoomNumberNot.setText("");
                lblRoomPriceNot.setText("");
            }
            else 
            {
                lblOutput.setText("Room not created, fix errors!");
            }
        });        
        
        // Pane        
        GridPane createRoomPane = new GridPane();
        createRoomPane.setVgap(20);
        createRoomPane.setHgap(20);
        createRoomPane.add(lblBed, 0, 1);
        createRoomPane.add(bedCombo, 1, 1);
        createRoomPane.add(lblKitch, 0, 2);
        createRoomPane.add(kitchCombo, 1, 2);
        createRoomPane.add(lblCoffee, 0, 3);
        createRoomPane.add(coffeeCombo, 1, 3);
        createRoomPane.add(lblAccess, 0, 4);
        createRoomPane.add(accessCombo, 1, 4);
        createRoomPane.add(lblRoomNumber, 0, 7);
        createRoomPane.add(txtRoomNumber, 1, 7);
        createRoomPane.add(lblRoomNumberNot,1,8);
        createRoomPane.add(btnAddRoom, 0, 12);
        createRoomPane.add(btnEmpMenu, 1, 12);
        createRoomPane.add(lvOutput, 3, 1, 1, 13);
        createRoomPane.add(lblRoomPrice, 0, 9);
        createRoomPane.add(txtRoomPrice, 1, 9);
        createRoomPane.add(lblRoomPriceNot,1,10);
        createRoomPane.add(lblOutput,1,13);
        
        // Scene
        Scene createRoomScene = new Scene(createRoomPane, 700, 590);
        
        // Stage
        createRoomStage.setTitle("Madison Hotel - Create a Room");
        createRoomStage.setScene(createRoomScene);
        createRoomStage.show();
    }
    public void selectRoom(Stage editRoomStage, Employee emp) 
    {
        //Controls
        Button btnEmpMenu = new Button("Back to Employee Menu ->");
        Label lblRoomSelection = new Label("Select room to edit: ");
        ComboBox roomSelectionCombo = new ComboBox();
        Button btnEditThisRoom = new Button("Edit this room ->");
        ListView lvOutput = new ListView();
        
        String[] roomNumbers = new String[rooms.size()];
        for (int i=0; i<rooms.size(); i++) {
            roomNumbers[i] = Integer.toString(rooms.get(i).roomNumber);
            roomSelectionCombo.getItems().add(roomNumbers[i]);
        }
        roomSelectionCombo.getSelectionModel().select(0);

        btnEditThisRoom.setOnAction(e -> 
        {
            String roomSelection = (String)roomSelectionCombo.getValue();
            int room = 0;
            for (int i=0; i<rooms.size(); i++) 
            {
                if (roomSelection.equals(String.valueOf(rooms.get(i).roomNumber))) 
                {
                    room = i;
                }
            }
            editRoomStage.hide();
            Stage editSelectedRoomStage = new Stage();
            editRoom(editSelectedRoomStage, emp, room);
        });
        
        btnEmpMenu.setOnAction(e ->
        {
            editRoomStage.hide();
            Stage employeeStage = new Stage();
            employee(employeeStage, emp);
        });       
        for (int i = 0; i < rooms.size(); i++)
        {
            String booked = null;
            if (rooms.get(i).isBooked())
            {
                booked = "[Booked]";
            }
            else
            {
                booked = "[Available]";
            }
            lvOutput.getItems().add("Room " 
                    + rooms.get(i).roomNumber 
                    + " Booking Qty: " 
                    + rooms.get(i).getRoomBookedQuantity() 
                    + " " + booked);
        }        
        btnEmpMenu.setOnAction(e ->
        {
            editRoomStage.hide();
            Stage employeeStage = new Stage();
            employee(employeeStage, emp);
        });
        
        //Pane
        GridPane editRoomPane = new GridPane();
        editRoomPane.setVgap(20);
        editRoomPane.setHgap(20);
        editRoomPane.add(lblRoomSelection, 2, 1);
        editRoomPane.add(roomSelectionCombo, 2, 2);
        editRoomPane.add(btnEditThisRoom,2,3);
        editRoomPane.add(btnEmpMenu,2,5);
        editRoomPane.add(lvOutput, 3, 1, 1, 15);
        
        // Scene
        Scene editRoomScene = new Scene(editRoomPane, 630, 520);
        
        // Stage
        editRoomStage.setTitle("Madison Hotel - Select Room to Edit");
        editRoomStage.setScene(editRoomScene);
        editRoomStage.show();
    }
    
    public void editRoom(Stage editSelectedRoomStage, Employee emp, int room) {
        //Controls
        //combobox.getSelectionModel().select(int) --> default value
        //clear text boxes and set back to default with selection model 
        Label lblCurrentRoom = new Label("Editing room #" + rooms.get(room).roomNumber);
        Label lblBed = new Label("  New Bed Option: ");
        ComboBox bedCombo = new ComboBox();
        bedCombo.getItems().add("1x Queen Bed");
        bedCombo.getItems().add("2x Queen Beds");
        bedCombo.getItems().add("1x King Bed");
        bedCombo.getSelectionModel().select(rooms.get(room).printBedOption());
        Label lblKitch = new Label("  New Kitchen Option: ");
        ComboBox kitchCombo = new ComboBox();
        kitchCombo.getItems().add("Microwave");
        kitchCombo.getItems().add("Fridge + Microwave");
        kitchCombo.getSelectionModel().select(rooms.get(room).printKitchOption());
        Label lblCoffee = new Label("  New Coffee Option: ");
        ComboBox coffeeCombo = new ComboBox();
        coffeeCombo.getItems().add("1-Cup Std. Coffee Machine");
        coffeeCombo.getItems().add("Keurig Hot K200 Machine");
        coffeeCombo.getSelectionModel().select(rooms.get(room).printCoffeeOption());
        Label lblAccess = new Label("  New Accessibility Option: ");
        ComboBox accessCombo = new ComboBox();
        accessCombo.getItems().add("Standard Room");
        accessCombo.getItems().add("Enhanced Accessibility Room");
        accessCombo.getSelectionModel().select(rooms.get(room).printAccessOption());
        Label lblActivity = new Label("  Room Status:  ");
        Label txtRoom = new Label("");
        ComboBox activityCombo = new ComboBox();
        activityCombo.getItems().add("Active");
        activityCombo.getItems().add("Inactive");
        activityCombo.getSelectionModel().select(rooms.get(room).getRoomActivity());
        Label lblRoomNumber = new Label(" New Room Number: ");
        Label txtPrice = new Label("");
        TextField txtRoomNumber = new TextField();
        Label lblRoomPrice = new Label("  New Price per Night: ");
        TextField txtRoomPrice = new TextField();
        Button btnApplyChanges = new Button("Apply selected changes ->");
        Button btnEmpMenu = new Button("Return to Employee Menu");
        Label txtConfirm = new Label();
        ListView lvOutput = new ListView();
        lvOutput.getItems().add("Current Room Specifications:\n\nBed: " + rooms.get(room).printBedOption()
        + "\n\nKitchen: " + rooms.get(room).printKitchOption() + "\n\nCoffee: " + rooms.get(room).printCoffeeOption() 
        + "\n\nAccessibility: " + rooms.get(room).printAccessOption() + "\n\nRoom Number: " + rooms.get(room).roomNumber 
        + "\n\nRoom Cost Per Night: " + rooms.get(room).roomCostPerNight + "\n\nRoom Status: " + rooms.get(room).getRoomActivity());
        //Label lblroomSpecs = new Label("Current Room Specifications:\n\nBed: " + rooms.get(room).printBedOption()
        //+ "\n\nCoffee: " + rooms.get(room).printCoffeeOption() + "\n\nKitchen: " + rooms.get(room).printCoffeeOption() 
        //+ "\n\nAccessibility: " + rooms.get(room).printAccessOption() + "\n\nRoom Number: " + rooms.get(room).roomNumber 
        //+ "\n\nRoom Cost Per Night: " + rooms.get(room).roomCostPerNight);
        
        btnApplyChanges.setOnAction(e -> 
        {
            String bed = (String) bedCombo.getValue();
            int bedChoice = 0;
            if (bed.equalsIgnoreCase("1x Queen Bed"))
            {
                bedChoice = 0;
            }
            else if (bed.equalsIgnoreCase("2x Queen Beds"))
            {
                bedChoice = 1;
            }
            else if (bed.equalsIgnoreCase("1x King Bed"))
            {
                bedChoice = 2;
            }
            String kitch = (String) kitchCombo.getValue();
            int kitchChoice = 0;
            if (kitch.equalsIgnoreCase("Microwave"))
            {
                kitchChoice = 0;
            }
            else if (kitch.equalsIgnoreCase("Fridge + Microwave"))
            {
                kitchChoice = 1;
            }
            String coffee = (String) coffeeCombo.getValue();
            int coffeeChoice = 0;
            if (coffee.equalsIgnoreCase("1-Cup Std. Coffee Machine"))
            {
                coffeeChoice = 0;
            }
            else if (coffee.equalsIgnoreCase("Keurig Hot K200 Machine"))
            {
                coffeeChoice = 1;
            }
            String access = (String) accessCombo.getValue();
            int accessChoice = 0;
            if (access.equalsIgnoreCase("Standard Room"))
            {
                accessChoice = 0;
            }
            else if (access.equalsIgnoreCase("Enhanced Accessibility Room"))
            {
                accessChoice = 1;
            }
            String activity = (String)activityCombo.getValue();
            int activityChoice = 0;
            if (activity.equalsIgnoreCase("Active")) 
            {
                rooms.get(room).setRoomActivity("Active");
            } 
            else if (activity.equalsIgnoreCase("Inactive")) 
            {
                rooms.get(room).setRoomActivity("Inactive");
            }
            int count = 0;
            int roomNumber = 0;
            for (int i = 0; i < rooms.size(); i++)
            {
                if (!(validRoomNumber(txtRoomNumber.getText()))) 
                {
                    break;
                }
                if (Integer.parseInt(txtRoomNumber.getText()) == rooms.get(i).roomNumber)
                {
                    if (Integer.parseInt(txtRoomNumber.getText()) == rooms.get(room).roomNumber) 
                    {
                        break;
                    }
                    count++;
                }
            }
            if (!(count > 0))
            {
                if (validRoomNumber(txtRoomNumber.getText())) 
                {
                roomNumber = Integer.parseInt(txtRoomNumber.getText());
                }
            }
            if (count > 0) 
            {
                txtRoom.setText("Room number already taken, try another!\n");
                roomNumber = rooms.get(room).roomNumber;
            //} //else if (txtRoomNumber.getText().length() ==0 || txtRoomPrice.getText().length() ==0)  {
                //txtConfirm.setText("Sorry, invalid option(s)! Try again!"
            } 
            else if (txtRoomNumber.getText().length() == 0) 
            {
                //txtRoom.setText("Keeping same room #");
                roomNumber = rooms.get(room).roomNumber;
            } 
            else if (validRoomNumber(txtRoomNumber.getText()) == false) 
            {
                txtRoom.setText("Please enter valid room #");
                roomNumber = rooms.get(room).roomNumber;
                
            }
            double roomPrice = 0;
            if (validRoomPrice(txtRoomPrice.getText())) 
            {
                roomPrice = Double.parseDouble(txtRoomPrice.getText());
            } 
            else if(txtRoomPrice.getText().length() == 0) 
            {
               // txtPrice.setText("Keeping same price");
                roomPrice = rooms.get(room).roomCostPerNight;
            } 
            else 
            {
                txtPrice.setText("Please enter valid price!");
                roomPrice = rooms.get(room).roomCostPerNight;
            }
            
            if (count == 0) 
            {
                if (txtRoomPrice.getText().length() + txtRoomNumber.getText().length() == 0) 
                {
                    rooms.get(room).bedOption = bedChoice;
                    rooms.get(room).coffeeOption = coffeeChoice;
                    rooms.get(room).kitchenOption = kitchChoice;
                    rooms.get(room).accessibleOption = accessChoice;
                    rooms.get(room).roomNumber = roomNumber;
                    rooms.get(room).roomCostPerNight = roomPrice;
                    txtConfirm.setText("Changes successfully made!");
                    txtRoom.setText("Keeping same room #");
                    txtPrice.setText("Keeping same room price");
                } 
                else if (txtRoomPrice.getText().length() == 0) 
                {
                    rooms.get(room).bedOption = bedChoice;
                    rooms.get(room).coffeeOption = coffeeChoice;
                    rooms.get(room).kitchenOption = kitchChoice;
                    rooms.get(room).accessibleOption = accessChoice;
                    rooms.get(room).roomNumber = roomNumber;
                    rooms.get(room).roomCostPerNight = roomPrice;
                    txtConfirm.setText("Changes successfully made!");
                    txtPrice.setText("Keeping same room price");
                    txtRoom.setText("");
                } 
                else if (txtRoomNumber.getText().length() == 0) 
                {
                    rooms.get(room).bedOption = bedChoice;
                    rooms.get(room).coffeeOption = coffeeChoice;
                    rooms.get(room).kitchenOption = kitchChoice;
                    rooms.get(room).accessibleOption = accessChoice;
                    rooms.get(room).roomNumber = roomNumber;
                    rooms.get(room).roomCostPerNight = roomPrice;
                    txtConfirm.setText("Changes successfully made!");
                    txtRoom.setText("Keeping same room #");
                }
                else if (validRoomPrice(txtRoomPrice.getText()) && validRoomNumber(txtRoomNumber.getText())) 
                {
                    rooms.get(room).bedOption = bedChoice;
                    rooms.get(room).coffeeOption = coffeeChoice;
                    rooms.get(room).kitchenOption = kitchChoice;
                    rooms.get(room).accessibleOption = accessChoice;
                    rooms.get(room).roomNumber = roomNumber;
                    rooms.get(room).roomCostPerNight = roomPrice;
                    txtConfirm.setText("Changes successfully made!");
                    txtRoom.setText("");
                    txtPrice.setText("");
                } 
                else 
                {
                    txtConfirm.setText("Changes not made, fix errors!");
                }
           }
        });
        
        btnApplyChanges.setOnMouseReleased(e -> 
        {
            lvOutput.getItems().clear();
            lvOutput.getItems().add("Current Room Specifications:\n\nBed: " + rooms.get(room).printBedOption()
            + "\n\nKitchen: " + rooms.get(room).printKitchOption() + "\n\nCoffee: " + rooms.get(room).printCoffeeOption() 
            + "\n\nAccessibility: " + rooms.get(room).printAccessOption() + "\n\nRoom Number: " + rooms.get(room).roomNumber 
            + "\n\nRoom Cost Per Night: " + rooms.get(room).roomCostPerNight + "\n\nRoom Status: " + rooms.get(room).getRoomActivity());
        });
        
        btnEmpMenu.setOnAction(e -> 
        {
            editSelectedRoomStage.hide();
            Stage employeeStage = new Stage();
            employee(employeeStage, emp);
        });
        
        //Pane
        GridPane editSelectedRoomPane = new GridPane();
        editSelectedRoomPane.setVgap(10);
        editSelectedRoomPane.setHgap(10);
        editSelectedRoomPane.add(lblCurrentRoom,1,1);
        editSelectedRoomPane.add(lblKitch, 1, 5);
        editSelectedRoomPane.add(kitchCombo,1,6);
        editSelectedRoomPane.add(lblBed,1,3);
        editSelectedRoomPane.add(bedCombo,1,4);
        editSelectedRoomPane.add(lblCoffee,1,7);
        editSelectedRoomPane.add(coffeeCombo,1,8);
        editSelectedRoomPane.add(lblAccess,1,9);
        editSelectedRoomPane.add(accessCombo,1,10);
        editSelectedRoomPane.add(lblRoomNumber,1,11);
        editSelectedRoomPane.add(txtRoomNumber,1,12);
        editSelectedRoomPane.add(txtRoom,1,13);
        editSelectedRoomPane.add(lblRoomPrice,1,14);
        editSelectedRoomPane.add(txtRoomPrice,1,15);
        if (!(rooms.get(room).isBooked()))
        {
            editSelectedRoomPane.add(lblActivity,2,3);
            editSelectedRoomPane.add(activityCombo,2,4);
        }
        editSelectedRoomPane.add(lvOutput, 8, 1,1,13);
        editSelectedRoomPane.add(txtConfirm, 1, 18);
        editSelectedRoomPane.add(btnApplyChanges, 1, 17);
        editSelectedRoomPane.add(txtPrice,1,16);
        editSelectedRoomPane.add(btnEmpMenu,8, 15);
        
        //Scene
        Scene editSelectedRoomScene = new Scene(editSelectedRoomPane,700,590);
        
        //Stage
        editSelectedRoomStage.setTitle("Madison Hotel - Edit a Room");
        editSelectedRoomStage.setScene(editSelectedRoomScene);
        editSelectedRoomStage.show();
        
    }
    
    public boolean validRoomNumber(String input) 
    {
        boolean result = false;
        try 
        {
            int num = Integer.parseInt(input);
            if (num >=1 && num <=9999) 
            {
                result = true;
            }
        } 
        catch (NumberFormatException e) 
        {
            result = false;
        }
        return result;
    }
    
    public boolean validRoomPrice(String input) 
    {
        boolean result = false;
        try 
        {
            double num = Double.parseDouble(input);
            if (num >0 && num <= 9999) 
            {
                result = true;
            }
            
        }
        catch (NumberFormatException e) 
        {
            result = false;
        }
        return result;
    }

    public void changeGuestName(Stage changeGuestNameStage, Guest guest)
    {
        // Controls
        Label lblCurrentName1 = new Label("Current Name: ");
        Label lblCurrentName2 = new Label(guest.getGuestName());
        Label lblNewName = new Label("New Name: ");
        TextField txtNewName = new TextField();
        Button btnChangeName = new Button("Change Name");
        btnChangeName.setOnAction(e ->
        {
            String name = txtNewName.getText();
            if (name != null && !(name.equalsIgnoreCase("") 
                    && !(name.equalsIgnoreCase(" "))))
            {
                guest.setGuestName(name);
                changeGuestNameStage.close();
                Stage guestStage = new Stage();
                guest(guestStage, guest);                
            }
        });
        
        // Pane
        GridPane changeNamePane = new GridPane();
        changeNamePane.setAlignment(Pos.CENTER);
        changeNamePane.setVgap(10);
        changeNamePane.setHgap(10);
        changeNamePane.add(lblCurrentName1, 0, 0);
        changeNamePane.add(lblCurrentName2, 1, 0);
        changeNamePane.add(lblNewName, 0, 1);
        changeNamePane.add(txtNewName, 1, 1);
        changeNamePane.add(btnChangeName, 1, 3);
        
        
        // Scene
        Scene changeNameScene = new Scene(changeNamePane, 500, 300);
        
        // Stage
        changeGuestNameStage.setTitle("Change Guest Name");
        changeGuestNameStage.setScene(changeNameScene);
        changeGuestNameStage.show();
    }
    
    public void changeGuestName(Stage changeGuestNameStage, Employee emp)
    {
        // Controls
        Label lblCurrentName1 = new Label("Current Name: ");
        Label lblCurrentName2 = new Label(emp.getEmployeeName());
        Label lblNewName = new Label("New Name: ");
        TextField txtNewName = new TextField();
        Button btnChangeName = new Button("Change Name");
        btnChangeName.setOnAction(e ->
        {
            String name = txtNewName.getText();
            if (name != null && !(name.equalsIgnoreCase("") 
                    && !(name.equalsIgnoreCase(" "))))
            {
                emp.setEmployeeName(name);
                changeGuestNameStage.close();
                Stage employeeStage = new Stage();
                employee(employeeStage, emp);                
            }
        });
        
        // Pane
        GridPane changeNamePane = new GridPane();
        changeNamePane.setAlignment(Pos.CENTER);
        changeNamePane.setVgap(10);
        changeNamePane.setHgap(10);
        changeNamePane.add(lblCurrentName1, 0, 0);
        changeNamePane.add(lblCurrentName2, 1, 0);
        changeNamePane.add(lblNewName, 0, 1);
        changeNamePane.add(txtNewName, 1, 1);
        changeNamePane.add(btnChangeName, 1, 3);
        
        
        // Scene
        Scene changeNameScene = new Scene(changeNamePane, 500, 300);
        
        // Stage
        changeGuestNameStage.setTitle("Change Employee Name");
        changeGuestNameStage.setScene(changeNameScene);
        changeGuestNameStage.show();
    }
      
    //THIS IS OVERLOADED AND IS USED FOR EMPLOYEES TO CHANGE PASSWORDS  
    public void changeGuestPassword(Stage changeGuestPasswordStage, Employee employee)
    {
         Label lblError = new Label("");
        // Controls
        Label lblOldPassword = new Label("Enter your current password: ");
        TextField txtOldPassword = new TextField();
        Label lblNewPassword = new Label("New Password: ");
        TextField txtNewPassword = new TextField();
        Button btnChangePassword = new Button("Change Password");
        btnChangePassword.setOnAction(e ->
        {
            String oldPassword = txtOldPassword.getText();
            String newPassword = txtNewPassword.getText();

            if (employee.setPassword(oldPassword, newPassword) == 0)
            {
                employee.setPassword(oldPassword,newPassword);
                changeGuestPasswordStage.close();
                Stage employeeStage = new Stage();
                employee(employeeStage, employee);                
            }

            else if (employee.setPassword(oldPassword, newPassword) == 1)
            {
               lblError.setText("The new password must be different from the old password \n"
                       + "The new password cannot start with a number \n"
                       + "The new password must contain one number \n"
                       + "The new password must contain one capital letter");
            }
        });

        // Pane
        GridPane changePasswordPane = new GridPane();
        changePasswordPane.setAlignment(Pos.CENTER);
        changePasswordPane.setVgap(10);
        changePasswordPane.setHgap(10);
        changePasswordPane.add(lblOldPassword, 0, 0);
        changePasswordPane.add(txtOldPassword, 1, 0);
        changePasswordPane.add(lblNewPassword, 0, 1);
        changePasswordPane.add(txtNewPassword, 1, 1);
        changePasswordPane.add(btnChangePassword, 1, 3);
        changePasswordPane.add(lblError, 0, 6, 2, 1);

        // Scene
        Scene changePasswordScene = new Scene(changePasswordPane, 500, 300);

         // Stage
        changeGuestPasswordStage.setTitle("Change Employee Password");
        changeGuestPasswordStage.setScene(changePasswordScene);
        changeGuestPasswordStage.show();
    }
    public static int createPassword(String newP)
    {
        int count = 0;
        if(Character.isDigit(newP.charAt(0)))
        {
            count = 1; 
            return count;
        }
        for(int i = 0; i < newP.length(); i++)
        {
            if(Character.isUpperCase(newP.charAt(i)))
            {
                count = 0;
                break;
            } 
            else{
                count = 1;
                return count;

            }     
        }
        for(int i = 1; i < newP.length(); i++)
        {
            if(Character.isDigit(newP.charAt(i)))
            {
                count = 0;
                return count;
            }
            else{
                count = 1;
               // return count;
            }
        }
        return count;
    }
    public void stop()
    {
        insertRoom();
        insertEmployee();
        insertGuest();
        insertBooking();
        insertRoomService();
    }
    public static void main(String args[])
    {                
        Application.launch(args);
    }
    public static void print()
    {
        System.out.println(" ");
    }
    
    // ROOM
    public void insertRoom()
    {
        for (int i = 0; i < rooms.size(); i++)
        {
            int bookRoom = 0;
            if (rooms.get(i).isBooked())
            {
                bookRoom = 1;
            }
            else
            {
                bookRoom = 0;
            }
            String tableName = "ROOM";
            String sqlQuery = "INSERT INTO JAVAUSER." + tableName 
                    + " (ROOMID, ROOMNUMBER, BEDOPTION, KITCHOPTION, COFFEEOPTION, "
                    + "ACCESSIBLEOPTION, ROOMBOOKEDQUANTITY, "
                    + "ROOMCOSTPERNIGHT, ROOMBOOKED, ROOMSTATUS) VALUES (";
            sqlQuery += rooms.get(i).getRoomID() + ",";
            sqlQuery += rooms.get(i).getRoomNumber() + ",";
            sqlQuery += rooms.get(i).bedOption + ",";
            sqlQuery += rooms.get(i).kitchenOption + ",";
            sqlQuery += rooms.get(i).coffeeOption + ",";
            sqlQuery += rooms.get(i).accessibleOption + ",";
            sqlQuery += rooms.get(i).getRoomBookedQuantity() + ",";
            sqlQuery += rooms.get(i).roomCostPerNight + ",";
            sqlQuery += bookRoom + ",";
            sqlQuery += "\'" + rooms.get(i).getRoomActivity() + "\')";

            sendDBCommand(sqlQuery, tableName, i);
        }
    }
    public void updateRoom(int i)
    {
        int bookRoom = 0;
        if (rooms.get(i).isBooked())
        {
            bookRoom = 1;
        }
        else
        {
            bookRoom = 0;
        }
        String tableName = "ROOM";
        String sqlQuery = "UPDATE JAVAUSER." + tableName + " SET ROOMID=";
        sqlQuery += rooms.get(i).getRoomID() + ",";
        sqlQuery += "ROOMNUMBER=" + rooms.get(i).getRoomNumber() + ",";
        sqlQuery += "BEDOPTION=" + rooms.get(i).bedOption + ",";
        sqlQuery += "KITCHOPTION=" + rooms.get(i).kitchenOption + ",";
        sqlQuery += "COFFEEOPTION=" + rooms.get(i).coffeeOption + ",";
        sqlQuery += "ACCESSIBLEOPTION=" + rooms.get(i).accessibleOption + ",";
        sqlQuery += "ROOMBOOKEDQUANTITY=" + rooms.get(i).getRoomBookedQuantity() + ",";
        sqlQuery += "ROOMBOOKED=" + bookRoom + ",";
        sqlQuery += "ROOMCOSTPERNIGHT=" + rooms.get(i).roomCostPerNight + ",";
        sqlQuery += "ROOMSTATUS=\'" + rooms.get(i).getRoomActivity() + "\'";
        sqlQuery += " WHERE ROOMID=" + i;

        sendDBCommand(sqlQuery, tableName, i);
    }
    
    // EMPLOYEE
    public void insertEmployee()
    {
        for (int i = 0; i < employees.size(); i++)
        {
            String tableName = "EMPLOYEE";
            String sqlQuery = "INSERT INTO JAVAUSER." + tableName
                    + " (EMPLOYEEID, EMPLOYEEUSERNAME, EMPLOYEENAME, "
                    + "EMPLOYEEPASSWORD) VALUES (";
            sqlQuery += employees.get(i).getEmployeeID() + ",";
            sqlQuery += "\'" + employees.get(i).getUsername() + "\',";
            sqlQuery += "\'" + employees.get(i).getEmployeeName() + "\',";
            sqlQuery += "\'" + employees.get(i).getPassword() + "\')";
            
            sendDBCommand(sqlQuery, tableName, i);
        }
    }
    public void updateEmployee(int i)
    {
        String tableName = "EMPLOYEE";
        String sqlQuery = "UPDATE JAVAUSER." + tableName + " SET EMPLOYEEID=";
        sqlQuery += employees.get(i).getEmployeeID() + ",";
        sqlQuery += "EMPLOYEEUSERNAME=\'" + employees.get(i).getUsername() + "\',";
        sqlQuery += "EMPLOYEENAME=\'" + employees.get(i).getEmployeeName() + "\',";
        sqlQuery += "EMPLOYEEPASSWORD=\'" + employees.get(i).getPassword() + "\'";
        sqlQuery += " WHERE EMPLOYEEID=" + i;

        sendDBCommand(sqlQuery, tableName, i);
    }

    // ROOMSERVICE
    public void insertRoomService()
    {
        for (int i = 0; i < bookings.size(); i++)
        {
            for (int j = 0; j < bookings.get(i).roomServices.size(); j++)
            {
                String tableName = "ROOMSERVICE";
                String sqlQuery = "INSERT INTO JAVAUSER." + tableName
                        + " (ROOMSERVICEID, SERVICEBOOKINGID, DESCRIPTION, PRICE) VALUES (";
                sqlQuery += bookings.get(i).roomServices.get(j).ID + ",";
                sqlQuery += bookings.get(i).roomServices.get(j).booking.bookingID + ",";
                sqlQuery += "\'" + bookings.get(i).roomServices.get(j).description + "\',";
                sqlQuery += bookings.get(i).roomServices.get(j).price + ")";
                
                sendDBCommand(sqlQuery, tableName, i, j);
            }
        }
    }
    public void updateRoomService(int i, int j)
    {
        String tableName = "ROOMSERVICE";
        String sqlQuery = "UPDATE JAVAUSER." + tableName + " SET ROOMSERVICEID="; 
        sqlQuery += bookings.get(i).roomServices.get(j).ID + ",";
        sqlQuery += " SERVICEBOOKINGID=" + bookings.get(i).roomServices.get(j).booking.bookingID + ",";
        sqlQuery += "DESCRIPTION=\'" + bookings.get(i).roomServices.get(j).description + "\',";
        sqlQuery += "PRICE=" + bookings.get(i).roomServices.get(j).price;
        sqlQuery += " WHERE ROOMSERVICEID=" + j;


        sendDBCommand(sqlQuery, tableName, i, j);
    }

    // GUEST
    public void insertGuest()
    {
        for (int i = 0; i < guests.size(); i++)
        {
            String tableName = "GUEST";
            String sqlQuery = "INSERT INTO JAVAUSER." + tableName
                    + " (GUESTID, GUESTUSERNAME, GUESTNAME, GUESTPASSWORD, VALUECLUBID) VALUES (";
            sqlQuery += guests.get(i).getGuestID() + ",";
            sqlQuery += "\'" + guests.get(i).getUsername() + "\',";
            sqlQuery += "\'" + guests.get(i).getGuestName() + "\',";
            sqlQuery += "\'" + guests.get(i).getPassword() + "\',";
            boolean operator = true;
            for (int j = 0; j < valueGuests.size(); j++)
            {
                if (guests.get(i).getGuestID() == valueGuests.get(j).getGuestID())
                {
                    sqlQuery += valueGuests.get(j).getValueClubID() + ")";
                    operator = false;
                }
            }
            if (operator)
            {
                sqlQuery += null + ")";
            }
            
            sendDBCommand(sqlQuery, tableName, i);
        }
    }
    public void updateGuest(int i)
    {
        String tableName = "GUEST";
        String sqlQuery = "UPDATE JAVAUSER." + tableName + " SET GUESTID=";
        sqlQuery += guests.get(i).getGuestID() + ",";
        sqlQuery += "GUESTUSERNAME=\'" + guests.get(i).getUsername() + "\',";
        sqlQuery += "GUESTNAME=\'" + guests.get(i).getGuestName() + "\',";
        sqlQuery += "GUESTPASSWORD=\'" + guests.get(i).getPassword() + "\',";
        boolean operator = true;
        for (int j = 0; j < valueGuests.size(); j++)
        {
            if (guests.get(i).getGuestID() == valueGuests.get(j).getGuestID())
            {
                sqlQuery += "VALUECLUBID=" + valueGuests.get(j).getValueClubID();
                operator = false;
            }
        }
        if (operator)
        {
            sqlQuery += "VALUECLUBID=" + null;
        }
        sqlQuery += " WHERE GUESTID=" + i;

        sendDBCommand(sqlQuery, tableName, i);
    }

    // BOOKING
    public void insertBooking()
    {
        for (int i = 0; i < bookings.size(); i++)
        {
            String tableName = "BOOKING";
            String sqlQuery = "INSERT INTO JAVAUSER." + tableName
                    + " (BOOKINGID, BOOKEDROOM, BOOKINGGUEST,"
                    + " BOOKINGYEAR, CHECKINDAY, CHECKOUTDAY) VALUES (";
            sqlQuery += bookings.get(i).bookingID + ",";
            sqlQuery += bookings.get(i).bookedRoom.getRoomID() + ",";
            sqlQuery += bookings.get(i).bookingGuest.getGuestID() + ",";
            sqlQuery += bookings.get(i).bookingYear + ",";
            sqlQuery += bookings.get(i).checkInDay + ",";
            sqlQuery += bookings.get(i).checkOutDay + ")";
            
            sendDBCommand(sqlQuery, tableName, i);
        }
    }
    public void updateBooking(int i)
    {
        String tableName = "BOOKING";
        String sqlQuery = "UPDATE JAVAUSER." + tableName + " SET BOOKINGID=";
        sqlQuery += bookings.get(i).bookingID + ",";
        sqlQuery += "BOOKEDROOM=" + bookings.get(i).bookedRoom.getRoomID() + ",";
        sqlQuery += "BOOKINGGUEST=" + bookings.get(i).bookingGuest.getGuestID() + ",";
        sqlQuery += "BOOKINGYEAR=" + bookings.get(i).bookingYear + ",";
        sqlQuery += "CHECKINDAY=" + bookings.get(i).checkInDay + ",";
        sqlQuery += "CHECKOUTDAY=" + bookings.get(i).checkOutDay;
        sqlQuery += " WHERE BOOKINGID=" + i;

        sendDBCommand(sqlQuery, tableName, i);
    }

    
    //DBCOMMANDS
    public void sendDBCommand(String sqlQuery, String tableName, int i)
    {
        String URL = "jdbc:oracle:thin:@localhost:1521:XE";
        String userID = "javauser"; 
        String userPASS = "javapass"; 
        OracleDataSource ds;

        System.out.println("\n" + sqlQuery);
        
        try
        {
            ds = new OracleDataSource();
            ds.setURL(URL);
            dbConn = ds.getConnection(userID,userPASS);
            commStmt = dbConn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            dbResults = commStmt.executeQuery(sqlQuery);
            
        }
        catch (SQLException e)
        {
            System.out.println(e.toString());
            String error = (String) e.getMessage();
            if (error.contains("unique constraint"))
            {
                switch(tableName)
                {
                    case ("ROOM"): 
                        updateRoom(i);
                        break;
                    case ("GUEST"):
                        updateGuest(i);
                        break;
                    case ("EMPLOYEE"):
                        updateEmployee(i);
                        break;
                    case ("ROOMSERVICE"):
                        break;
                    case ("BOOKING"):
                        updateBooking(i);
                        break;
                }
            }
        }
    }
    public void sendDBCommand(String sqlQuery, String tableName, int i, int j)
    {
        String URL = "jdbc:oracle:thin:@localhost:1521:XE";
        String userID = "javauser"; 
        String userPASS = "javapass"; 
        OracleDataSource ds;

        System.out.println("\n" + sqlQuery);
        
        try
        {
            ds = new OracleDataSource();
            ds.setURL(URL);
            dbConn = ds.getConnection(userID,userPASS);
            commStmt = dbConn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            dbResults = commStmt.executeQuery(sqlQuery);
            
        }
        catch (SQLException e)
        {
            System.out.println(e.toString());
            String error = (String) e.getMessage();
            if (error.contains("unique constraint"))
            {
                switch(tableName)
                {
                    case ("ROOM"): 
                        break;
                    case ("GUEST"):
                        break;
                    case ("EMPLOYEE"):
                        break;
                    case ("ROOMSERVICE"):
                        updateRoomService(i, j);
                        break;
                    case ("BOOKING"):
                        break;
                }
            }
        }
    }
    public void sendDBCommand(String sqlQuery)
    {
        String URL = "jdbc:oracle:thin:@localhost:1521:XE";
        String userID = "javauser"; 
        String userPASS = "javapass"; 
        OracleDataSource ds;

        System.out.println("\n" + sqlQuery);
        
        try
        {
            ds = new OracleDataSource();
            ds.setURL(URL);
            dbConn = ds.getConnection(userID,userPASS);
            commStmt = dbConn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            dbResults = commStmt.executeQuery(sqlQuery);
            
        }
        catch (SQLException e)
        {
            System.out.println(e.toString());
        }
    }
}
