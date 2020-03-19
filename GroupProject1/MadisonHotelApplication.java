package GroupProject1;
import java.util.*;

public class MadisonHotelApplication 
{
    static ArrayList<Employee> employees = new ArrayList<Employee>();
    static ArrayList<Guest> guests = new ArrayList<Guest>();
    static ArrayList<ValueGuest> valueGuests = new ArrayList<ValueGuest>();
    static ArrayList<Booking> bookings = new ArrayList<Booking>();
    static ArrayList<Room> rooms = new ArrayList<Room>();
    
    public static void main(String[] args) 
    {
        Employee employee1 = new Employee("Adam", "pass", "adam hitchings");
        employees.add(employee1);
        Guest guest1 = new Guest("Brendan", "pass", "brendan boyle");
        guests.add(guest1);
        Guest guest2 = new Guest("John", "pass", "johnny guo");
        guests.add(guest2);
        Employee employee2 = new Employee("Isaac", "pass", "isaac gamble");
        employees.add(employee2);
        Room room1 = new Room(0, 0, 0, 0, 100, 200.0);
        rooms.add(room1);
        Room room2 = new Room(1, 1, 1, 1, 201, 200.0);
        rooms.add(room2);
        Room room3 = new Room(2, 1, 0, 1, 302, 300.0);
        rooms.add(room3);
        ValueGuest valueGuest1 = new ValueGuest("George", "pass", "george moy");
        valueGuests.add(valueGuest1);
        Room room4 = new Room(2, 0, 0, 0, 308, 150.0);
        rooms.add(room4);
        Room room5 = new Room(1, 1, 1, 1, 400, 250.0);
        rooms.add(room5);
        Booking book1 = new Booking(guest1, room3, 2005, 1, 4);
        bookings.add(book1);
        Booking book2 = new Booking(guest1, room4, 2005, 1, 4);
        bookings.add(book2);

        
        login();
    }
    public static void guestMenu(int guest)
    {
        // give guests all options for their login and use switch case to 
        //direct them towards their choice
        // display information about they account they logged into
        //Contributed by George Moya
        System.out.println("---------------------------------------------");
        System.out.println("\tGUEST MENU");
        System.out.println("---------------------------------------------");
        print();
        System.out.println(guests.get(guest).describeGuest());
        Scanner in = new Scanner(System.in);
        print();
        System.out.println("(1) Book a room");
        System.out.println("(2) Room Report");
        System.out.println("(3) Edit personal information");
        System.out.println("(0) Back to Login");
        print();
        System.out.print("Enter your choice: ");
        int guestChoice = in.nextInt();
        print();
        while (guestChoice < 0 || guestChoice > 3)
        {
            System.out.print("Please select a valid option: ");
            guestChoice = in.nextInt();
            print();
        }
        switch(guestChoice)
        {
            case 0: login();
                break;
            case 1: bookRoom(guest); 
                break;
            case 2: roomReport(guest) ;
                break;
            case 3: editGuestInfo(guest);
                break;
            default: System.out.println("Please enter a valid option.");                
        }
    }
    public static void employeeMenu(int employee)
    {
        // give employees all options for their login and use switch case
        //to direct them towards their choice
        // display informaiton about the account they logged into
        //Contributed by George Moya
        System.out.println("---------------------------------------------");
        System.out.println("\tEMPLOYEE MENU");
        System.out.println("---------------------------------------------");
        print();
        System.out.println(employees.get(employee).describeEmployee());
        Scanner in = new Scanner(System.in);
        print();
        System.out.println("(1) Run booking report");
        System.out.println("(2) Check guest out");
        System.out.println("(3) Create a guest account");
        System.out.println("(4) Create new employee account");
        System.out.println("(5) Create or edit a room");
        System.out.println("(0) Back to Login");
        print();
        System.out.print("Enter your choice: ");
        int employeeChoice = in.nextInt();
        print();
        while (employeeChoice < 0 || employeeChoice > 5)
        {
            System.out.print("Please select a valid option: ");
            employeeChoice = in.nextInt();
            print();
        }
        switch(employeeChoice)
        {
            case 0: login();
                break;
            case 1: bookingReport(employee); 
                break;
            case 2: checkGuestOut(employee);
                break;
            case 3: createGuestAccount(employee);
                break;
            case 4: createEmployeeAccount(employee);
                break;
            case 5: createOrEditRoom(employee);
                break;
            default: System.out.println("Please enter a valid option.");                
        }
    }
    public static void login()
    {
        // ask if they want to login
        // use data validation to make sure they either put y or n
        //contributed by Dylan Vetter
        Scanner in = new Scanner(System.in);
        boolean operator = true;
        while (operator)
        {
            System.out.print("Login? (y/n): ");
            String login  = in.next();
            print();
            if (login.equalsIgnoreCase("y"))
            {
                operator = true;
                break;
            }
            else if (login.equalsIgnoreCase("n"))
            {
                operator = false;
                System.out.println("System exiting . . . ");
            }
            else
            {
                System.out.println("Please enter a valid option");
            }
        }
        // user will input a username and password and the program must loop
        //through the entire employee and guest list using check credentials
        //to see if the username and password they input works with any of the
        //accounts
        while(operator)
        {
            System.out.println("---------------------------------------------");
            System.out.println("\tGUEST AND EMPLOYEE LOGIN");
            System.out.println("---------------------------------------------");
            print();
            System.out.print("Please enter username: ");
            String username = in.next();
            System.out.print("Please enter password: ");
            String password = in.next();
            print();
            for (int i = 0; i < employees.size(); i++)
            {
                if (employees.get(i).checkCredentials(username, password))
                {
                    employeeMenu(i);
                    operator = false;
                    break;
                }
            }
            for (int i = 0; i < guests.size(); i++)
            {
                if (guests.get(i).checkCredentials(username, password))
                {
                    guestMenu(i);
                    operator = false;
                    break;
                }
            }
            for (int i = 0; i < valueGuests.size(); i++)
            {
                if (valueGuests.get(i).checkCredentials(username, password))
                {
                    guestMenu(i);
                    operator = false;
                    break;
                }
            }
            if (operator)
            {
                print();
                System.out.println("Please enter a valid guest "
                        + "or employee username and password.");
            }
        }
    }
    
    // Guest Menu Choices
    //Allows a guest to book a room. Asks for a variety of specifications 
    //for the room and checks to find rooms with those properties by calling 
    //the availableRooms method below
    //contributed by Brendan Boyle
    public static void bookRoom(int guest)
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Here are your room options:"
                + "\n" + "\n" + " - Bed Options" + "\n"
                + "\n\t(0) 1 Queen Bed"
                + "\n\t(1) 2 Queen Beds"
                + "\n\t(2) 1 King Bed"
                + "\n" + "\n" + "What is your Bed Choice? ");
        int bed = in.nextInt();
        print();
        while (bed < 0 || bed > 2)
        {
            System.out.print("Please select a valid option: ");
            bed = in.nextInt();
            print();
        }
        System.out.print(" - Kitchen Options"+ "\n"
                + "\n\t(0) Microwave"
                + "\n\t(1) Fridge and Microwave"
                + "\n" + "\n" + "What is your Kitchen Choice? ");
        int kitch = in.nextInt();
        print();
        while (kitch < 0 || kitch > 1)
        {
            System.out.print("Please select a valid option: ");
            kitch = in.nextInt();
            print();
        }
        System.out.print(" - Coffee Options" + "\n"
                + "\n\t(0) 1-Cup Std. Coffee Machine"
                + "\n\t(1) Keurig Hot K200 Machine"
                + "\n" + "\n" + "What is your Coffee Choice? ");
        int coffee = in.nextInt();
        print();
        while (coffee < 0 || coffee > 1)
        {
            System.out.print("Please select a valid option: ");
            coffee = in.nextInt();
            print();
        }
        System.out.print(" - Accessibility Options" + "\n"
                + "\n\t(0) Standard Room"
                + "\n\t(1) Enhanced Accessibilty Room"
                + "\n" + "\n" + "What is your Accessibility Choice? ");
        int accessibility = in.nextInt();
        print();
        while (accessibility < 0 || accessibility > 1)
        {
            System.out.print("Please select a valid option: ");
            accessibility = in.nextInt();
            print();
        }
        System.out.println("Here are the available rooms with your specifications: ");
        print();
        boolean roomSaver = availableRooms(bed, kitch, coffee, accessibility);
        print();
        if (roomSaver)
        {
            System.out.println("Which room number would you like? ");
            int roomNumber = in.nextInt();
            for (int i = 0; i < rooms.size(); i++)
            {
                int temp = rooms.get(i).getRoomNumber();
                if (temp == roomNumber)
                {
                    rooms.get(i).bookRoom();
                    Booking b = new Booking(guests.get(guest), 
                            rooms.get(i), 2019, i + 10, i + 20);
                    bookings.add(b);
                }
            }
            System.out.println("Room " + roomNumber 
                    + " has succesfully been booked");
        }
        guestMenu(guest);
    }
    //checks specific room specifications sent in from method above 
    //to find rooms that have those specific characteristics. 
    //Prints which rooms have all matching specifications.
    //Contributed by Brendan Boyle
    public static boolean availableRooms(int bed, int kitch, int coffee, 
            int accessibility)
    {
        boolean real = true;
        for (int i = 0; i < rooms.size(); i++)
        {
            if ((rooms.get(i).bedOption == bed) 
                    && (rooms.get(i).kitchenOption == kitch) 
                    && (rooms.get(i).coffeeOption == coffee) 
                    && (rooms.get(i).accessibleOption == accessibility)
                    && (rooms.get(i).isBooked() == false)
                    && (rooms.get(i).getRoomActivity().equalsIgnoreCase("Active")))
            {
                System.out.println("Room " + rooms.get(i).getRoomNumber() +
                " for $" + rooms.get(i).roomCostPerNight + " per night");
                real = false;            
            }          
        }
        if (real)
        {
            System.out.println("There are currently no rooms with your "
                    + "specifications");
            return false;
        }
        else
        {
            return true;
        }
    }
    
    //a method that checks whether or not a room number (Taken in as parameter)
    //is already taken by a specific room object
    //Contributed by Andrew Taylor
    public static boolean availableRoomNumber(int roomnumber) 
    {
        boolean available = true;
        for (int i=0; i< rooms.size(); i++) 
        {
            if (rooms.get(i).roomNumber == roomnumber) 
            {
                available = false;
            }
        }
        return available;
    }    
    public static void roomReport(int guest)
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Select a Room:");
        print();
        print();
        for (int i = 0; i < rooms.size(); i++)
        {
            System.out.println("[" + i +"] " + rooms.get(i).roomNumber);
        }
        print();
        System.out.println("Enter Room Number Selection: ");
        int room = in.nextInt();
        print();
        System.out.println("---------------------------------------------");
        System.out.println("ROOM DESCRIPTION FOR ROOM NUMBER: " 
                + rooms.get(room).getRoomNumber());
        System.out.println("---------------------------------------------");
        print();
        System.out.println(rooms.get(room).roomDescription()); 
        print();
        guestMenu(guest);
    }
    //allows information of a guest at a specified index 
    //(sent in as a parameter) to be edited.
    //allows guest to change their name and view their information 
    //using the describeGuest method from the Guest class
    //Contributed by Brendan Boyle
    public static void editGuestInfo(int guest)
    {
        Scanner in = new Scanner(System.in);
        System.out.println("---------------------------------------------");
        System.out.println("\tEDIT GUEST INFORMATION");
        System.out.println("---------------------------------------------");
        print();
        System.out.println("Please select what you would like to edit: ");
        print();
        System.out.println("(1) View guest information");
        System.out.println("(2) Change guest Name");
        System.out.println("(0) Back to Guest Menu");
        print();
        System.out.print("Enter your choice: ");
        int infoChoice = in.nextInt();
        print();
        while (infoChoice < 0 || infoChoice > 2)
        {
            System.out.print("Please select a valid option: ");
            infoChoice = in.nextInt();
            print();
        }
        switch(infoChoice)
        {
            case 0: guestMenu(guest);
                break;
            case 1: System.out.println(guests.get(guest).describeGuest());
                editGuestInfo(guest);
                break;
            case 2: System.out.println("Old Guest Name: " 
                    + guests.get(guest).getGuestName());
                System.out.print("New Guest Name: ");
                String firstName = in.next();
                String lastName = in.next();
                String name = firstName + " " + lastName;
                guests.get(guest).setGuestName(name);
                print();
                System.out.println("Change confirmed.");
                print();
                editGuestInfo(guest);
                break;
            default: System.out.println("Please enter a valid option.");
            print();
        }
    }
    // Employee Menu Choices
    //Gives employee choice of printing report for 1 room or all reports for all rooms.
    //calls describe booking method from booking class to print details about the booking for 
    //a room.
    //Contributed by Brendan Boyle
    public static void bookingReport(int employee)
    {
        //WE SHOULD BE HAVE THE CHOICE TO RUN A BOOKING REPORT ON ALL BOOKINGS
        Scanner in = new Scanner(System.in);
        System.out.println("---------------------------------------------");
        System.out.println("RUN A BOOKING REPORT");
        System.out.println("---------------------------------------------");
        print();
        System.out.println("For a specific Guest's Booking report, Enter 1.");
        System.out.println("For all booking reports, Enter 2.");
        int choice = in.nextInt();
        if (choice == 1)
        {
            System.out.println("Please select one of the Guest's usernames below: ");
            for (int i = 0; i < guests.size(); i++)
            {
                System.out.println("[" + i + "] " + guests.get(i).getUsername());
            }
            int username = in.nextInt();
            print();
            int room = 0;
            System.out.println("Please select one of the Guest's rooms "
                    + "to run a booking report on: ");
            for (int i = 0; i < bookings.size(); i++)
            {
                if (bookings.get(i).bookingGuest.equals(guests.get(username)))
                {
                    System.out.println("[" + i + "] " 
                            + bookings.get(i).bookedRoom.getRoomNumber());
                }
            }
            room = in.nextInt();
            print();
            System.out.println(bookings.get(room).describeBooking());
        }
        else if (choice == 2)
        {
            for (int i = 0; i < bookings.size(); i++)
            {
                System.out.println("---------------------------------------------");
                System.out.println(bookings.get(i).describeBooking());
            }
            print();
        }
        employeeMenu(employee);
    }
    //Method used to check a guest out. Asks employee to select a guest, prints all that guests'
    //rooms they have, and has them select a room to check out. 
    //Contributed by Brendan Boyle
    public static void checkGuestOut(int employee)
    {
        Scanner in = new Scanner(System.in);
        System.out.println("---------------------------------------------");
        System.out.println("CHECK GUEST OUT");
        System.out.println("---------------------------------------------");
        print();
        int guest = 0;
        System.out.println("Please select one of the Guest's usernames below: ");
        for (int i = 0; i < guests.size(); i++)
        {
            System.out.println("[" + i + "] " + guests.get(i).getUsername());
        }             
        guest = in.nextInt();
        int book = 0;
        System.out.println("Please select one of the Guest's rooms to check out:  ");
        int var = 0;
        for (int i = 0; i < bookings.size(); i++)
        {
            if (bookings.get(i).bookingGuest.equals(guests.get(guest)))
            {
                if (bookings.get(i).bookedRoom.isBooked())
                {
                    System.out.println("[" + i + "] " 
                            + bookings.get(i).bookedRoom.getRoomNumber());
                    var++;
                }
            }
        }
        if ( var < 1)
        {
            System.out.println("This guest has no bookings");
            print();
            employeeMenu(employee);
        }
        else
        {
            book = in.nextInt();
            bookings.get(book).endBooking();


            System.out.println(bookings.get(book).bookingGuest.getUsername()
                    + " has been checked out of " 
                    + bookings.get(book).bookedRoom.getRoomNumber());
            employeeMenu(employee);
        }
    }
    public static void createGuestAccount(int employee)
    //Mark Kilgore - This method creates a new guest account by
    //accepting user input for both the first and/or last name of the 
    //guest along with the username and password, then concatening 
    //them both to be created as an object.
    {
        Scanner in = new Scanner(System.in);
        System.out.println("---------------------------------------------");
        System.out.println("CREATE A GUEST ACCOUNT");
        System.out.println("---------------------------------------------");
        print();
        System.out.print("Enter 1 if you are a Guest or 2 if you are a ValueGuest: ");
        int value = in.nextInt();
        print();
        while (value < 1 || value > 2)
        {
            System.out.print("Please select a valid option: ");
            value = in.nextInt();
            print();
        }
        if(value == 1)
        {
            //For regular guest
            System.out.print("What is the guest's name? ");
            String firstname = in.next();
            String lastname = in.next();
            String guestName = firstname + " " + lastname;
            System.out.print("What is the guest's username? ");
            String username = in.next();
            System.out.print("What is the guest's password? ");
            String password = in.next();
            Guest g = new Guest(username, password, guestName);
            guests.add(g);
            print();
            System.out.println("Guest Account has been created.");
            employeeMenu(employee);
        }       
        else if(value == 2)
        {
            //For Valueguest creation
            System.out.print("What is the guest's name? ");
            String firstname = in.next();
            String lastname = in.next();
            String guestName = firstname + " " + lastname;
            System.out.print("What is the guest's username? ");
            String username = in.next();
            System.out.print("What is the guest's password? ");
            String password = in.next();
            ValueGuest v = new ValueGuest(username, password, guestName);
            valueGuests.add(v);
            print();
            System.out.println("ValueGuest Account has been created.");
            employeeMenu(employee);
        }        
    }
    
    //Mark Kilgore - This method creates a new employee account by
    //accepting user input for both the first and/or last name of the 
    //employee along with the username and password, then concatening 
    //them both to be created as an object.
    public static void createEmployeeAccount(int employee)
    {
        Scanner in = new Scanner(System.in);
        System.out.println("---------------------------------------------");
        System.out.println("CREATE A NEW EMPLOYEE ACCOUNT");
        System.out.println("---------------------------------------------");
        print();
        System.out.print("What is the employee's name? ");
        String firstname = in.next();
        String lastname = in.next();
        String employeeName = firstname + " " + lastname;
        System.out.print("What is the employee's username? ");
        String username = in.next();
        System.out.print("What is the employee's password? ");
        String password = in.next();
        Employee e = new Employee(username, password, employeeName);
        employees.add(e);
        print();
        System.out.println("Employee Account has been created.");
        employeeMenu(employee);
    }
    //method which allows employees to create or edit a room, asks for various specifications
    //for the room upon creation and gives the same options for editing. Also allows making the room
    //active or inactive. parameter employee specifies which employee is editing/creating room
    //Contributed by Andrew Taylor
    public static void createOrEditRoom(int employee)
    {
        Scanner in = new Scanner(System.in);
        System.out.println("---------------------------------------------");
        System.out.println("CREATE OR EDIT A ROOM");
        System.out.println("---------------------------------------------");
        print();
        System.out.println("Please choose an option: ");
        print();
        System.out.print("(1) Create Room\n");
        System.out.print("(2) Edit Room\n");
        System.out.print("(3) Set Room Active or Inactive\n");
        System.out.print("(0) Return to menu\n");
        print();
        System.out.print("Enter your choice: ");
        int choice;
        choice = in.nextInt();
        print();
        switch(choice) 
        {
            case 1: 
                System.out.print(" - Enter bed size: \n(0) 1 Queen Bed\n(1) "
                        + "2 Queen Beds"
                        + "\n(2) 1 King Bed");
                print();
                int bedchoice = in.nextInt();
                while (bedchoice > 2 || bedchoice <0) 
                {
                    System.out.print("Sorry, invalid choice! "
                            + "Enter valid option: ");
                    bedchoice = in.nextInt();
                }
                System.out.print(" - Enter Kitchen Option: \n(0) Microwave \n"
                        + "(1) Fridge + Microwave");
                print();
                int kitchenchoice = in.nextInt();
                while (kitchenchoice > 1 || kitchenchoice < 0)
                {
                    System.out.print("Sorry, invalid choice! Enter valid option: ");
                    kitchenchoice = in.nextInt();
                }
                System.out.print(" - Enter coffee option: "
                        + "\n(0) 1-Cup Std. Coffee Machine"
                        + "\n(1) Keurig Hot K200 Machine");
                print();
                int coffeechoice = in.nextInt();
                while (coffeechoice > 1 || coffeechoice < 0) 
                {
                    System.out.print("Sorry, invalid choice! Enter valid option: ");
                    coffeechoice = in.nextInt();
                }
                System.out.print(" - Enter Accessibility Option: \n(0) Standard Room"
                        + "\n(1) Enhanced Accessibility Room");
                print();
                int accesschoice = in.nextInt();
                while (accesschoice > 1 || accesschoice < 0) 
                {
                    System.out.print("Sorry, invalid choice! Enter valid option: ");
                    accesschoice = in.nextInt();
                }
                System.out.print(" - Enter room number: ");
                int roomnumberchoice = in.nextInt();
                //check to ensure entered room # is available
                while (!(availableRoomNumber(roomnumberchoice))) 
                {
                    print();
                    System.out.print("Sorry, that room number is taken! "
                            + "Enter valid room #: ");
                    roomnumberchoice = in.nextInt();
                }
                System.out.print(" - Enter room cost per night (e.g. 125.99): ");
                double roomCostEntry = in.nextDouble();
                while (roomCostEntry < 0) 
                {
                    System.out.print("Sorry, room cannot have negative cost! "
                            + "Enter valid cost: ");
                    roomCostEntry = in.nextInt();
                }
                Room newRoom = new Room(bedchoice, kitchenchoice, coffeechoice,
                accesschoice, roomnumberchoice, roomCostEntry);
                rooms.add(newRoom);
                System.out.println("Success, room created!");
                createOrEditRoom(employee);
                break;
        
        case 2: 
            System.out.println("Please select which room you would like to edit: ");
            print();
            for (int i=0; i< rooms.size(); i++) 
            {
                System.out.println("("+rooms.get(i).getRoomID()+") " 
                        + rooms.get(i).roomNumber);
            }
            print();
            System.out.print("Enter choice: ");
            int editChoice = in.nextInt();
            while (!(editChoice <=rooms.size() - 1)) 
            {
                System.out.print("Sorry, invalid room selected! "
                        + "Please enter valid choice : ");
                editChoice = in.nextInt();
            }
            for (int i=0; i<rooms.size(); i++) 
            {
                if (editChoice == rooms.get(i).getRoomID()) 
                {
                    editRoom(rooms.get(i), employee);
                }
            }
            break;
        case 3:
            //Make room inactive -- add check for if room is booked before allowing activation
            System.out.print("Activate or Deactivate a room?" + "\n" 
                    + "\n" + " (1) Deactivate\n (2) Activate"
                    + "\n (0) Return to previous menu\n");
            print();
            System.out.print("Enter your choice: ");
            int activateOrDeactivate = in.nextInt();
            switch (activateOrDeactivate) 
            {
                case 1:
                    print();
                    System.out.print("Choose room to set inactive:\n");
                    print();
                    for (int i=0; i<rooms.size(); i++) 
                    {
                        System.out.println("(" +rooms.get(i).getRoomID() 
                                + ") " + rooms.get(i).roomNumber);
                    }
                    print();
                    System.out.print("Enter your choice: ");
                    int inactiveChoice = in.nextInt();
                    while (!(inactiveChoice <= rooms.size() - 1)) 
                    {
                        System.out.print("Sorry, invalid room selected! "
                                + "Please select valid room: ");
                        inactiveChoice = in.nextInt();
                    }
                    for (int i=0; i<rooms.size(); i++) 
                    {
                        if (inactiveChoice == rooms.get(i).getRoomID()) 
                        {
                            rooms.get(i).setRoomActivity("Inactive");
                        }
                    }
                    System.out.println("Room successfully set to Inactive!");
                    break;
                case 2:
                    print();
                    System.out.print("Choose room to activate:\n");
                    print();
                    for (int i=0; i<rooms.size(); i++) 
                    {
                        System.out.println("(" +rooms.get(i).getRoomID() 
                                + ") " + rooms.get(i).roomNumber);
                    }
                    print();
                    System.out.print("Enter your choice: ");
                    int activateChoice = in.nextInt();
                    while (!(activateChoice <= rooms.size() - 1)) 
                    {
                        System.out.print("Sorry, invalid room selected! "
                                + "Please select valid room: ");
                        activateChoice = in.nextInt();
                    }
                    for (int i=0; i<rooms.size(); i++) 
                    {
                        if (activateChoice == rooms.get(i).getRoomID()) 
                        {
                            rooms.get(i).setRoomActivity("Active");
                        }
                    }
                    System.out.println("Room successfully set to Active!");
                    break;
                case 0:
                    createOrEditRoom(employee);
                    break;
                default:
                    System.out.print("Sorry, invalid choice! "
                            + "Returning to previous menu.");
                    createOrEditRoom(employee);
            }
            createOrEditRoom(employee);
            break;
        case 0:
            employeeMenu(employee);
            break;
        default:
            System.out.print("Sorry, invalid choice!");
            createOrEditRoom(employee);
        } 
    }    
    
    //method to simplify room editing code, called from within createOrEditRoom method above.
    //takes in the employee that is editing the room as well as the specific room they are editing
    //Contributed by Andrew Taylor
    public static void editRoom(Room room, int employee) 
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Editing room #" + room.roomNumber 
                + " . What would you like to change?");
        int choice;
        print();
        System.out.print(" (1) Bed \n (2) Kitchen\n (3) Coffee"
                + "\n (4) Accessibility\n "
                + "(5) Room Number \n (6) Room cost per night "
                + "\n (0) Done editing, return to previous menu\n");
        choice = in.nextInt();
        print();
        switch (choice) 
        {
            case 1: 
                System.out.print("Enter new bed size: " + "\n" 
                        + "\n" + "(0) 1 Queen Bed\n(1) 2 Queen Beds"
                        + "\n(2) 1 King Bed");
                print();
                int bedchoice = in.nextInt();
                while (bedchoice > 2 || bedchoice < 0) 
                {
                    System.out.print("Sorry, invalid option! "
                            + "Enter valid choice: ");
                    bedchoice = in.nextInt();
                }
                room.bedOption = bedchoice;
                System.out.println("Successfully updated bed option!");
                editRoom(room, employee);
                break;
            case 2:
                System.out.print("Enter new Kitchen Option: " 
                        + "\n" + "\n" + "(0) Microwave \n"
                        + "(1) Fridge + Microwave");
                print();
                int kitchenchoice = in.nextInt();
                while (kitchenchoice > 1 || kitchenchoice < 0) 
                {
                    System.out.print("Sorry, invalid option! "
                            + "Enter valid choice: ");
                    kitchenchoice = in.nextInt();
                }
                room.kitchenOption = kitchenchoice;
                System.out.println("Successfully updated kitchen option!");
                editRoom(room, employee);
                break;
            case 3: 
                System.out.print("Enter new coffee option: " 
                        + "\n" + "\n" + "(0) 1-Cup Std. Coffee Machine"
                        + "\n(1) Keurig Hot K200 Machine");
                print();
                int coffeechoice = in.nextInt();
                while (coffeechoice > 1 || coffeechoice < 0) 
                {
                    System.out.println("Sorry, invalid option! "
                            + "Enter valid choice: ");
                    coffeechoice = in.nextInt();
                }
                room.coffeeOption = coffeechoice;
                System.out.println("Successfully updated coffee option!");
                editRoom(room, employee);
                break;
            case 4: 
                System.out.print("Enter new Accessibility Option: " 
                        + "\n" + "\n" + "(0) Standard Room"
                        + "\n(1) Enhanced Accessibility Room");
                print();
                int accesschoice = in.nextInt();
                while (accesschoice > 1 || accesschoice < 0) 
                {
                    System.out.print("Sorry, invalid option! "
                            + "Enter valid choice: ");
                    accesschoice = in.nextInt();
                }
                room.accessibleOption = accesschoice;
                System.out.println("Successfully updated Accessibility option!");
                editRoom(room, employee);
                break;
            case 5:
                System.out.print("Enter new room number: ");
                int roomnumberchoice = in.nextInt();
                //check to ensure entered room # is available
                while (!(availableRoomNumber(roomnumberchoice))) 
                {
                    print();
                    System.out.print("Sorry, that room number is taken! "
                            + "Enter valid room #: ");
                    roomnumberchoice = in.nextInt();
                }
                room.roomNumber = roomnumberchoice;
                System.out.println("Successfully updated room number!");
                editRoom(room, employee);
                break;
            case 6:
                System.out.print("Enter new cost per night (e.g. 125.99): ");
                double newCost = in.nextDouble();
                while (newCost < 0) 
                {
                    System.out.print("Sorry, room cannot have negative cost! "
                            + "Enter valid cost: ");
                    newCost = in.nextDouble();
                }
                room.roomCostPerNight = newCost;
                System.out.println("Successfully updated room cost per night!");
                editRoom(room, employee);
                break;
            case 0:
                createOrEditRoom(employee);
                break;
            default:
                System.out.print("Sorry, invalid option entered!");
                editRoom(room, employee);
                break;
        }       
    }
    public static void print()
    {
        System.out.println(" ");
    }
}
