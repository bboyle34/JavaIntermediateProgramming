package GroupProject1;
// Class Definition File (CDF)

//Description: A class that has details and member methods for specific rooms in the hotel. Objects are instantiated with 
//a variety of options and details about the specific room, as well as whether or not the room is booked.
//Contributed by Brendan Boyle, Andrew Taylor, and George Moya
public class Room 
{
    // Data Fields
    public int bedOption;
    public int kitchenOption;
    public int coffeeOption;
    public int accessibleOption;
    public int roomNumber;
    private int roomID;
    private int roomBookedQuantity;
    private boolean roomBooked;
    public double roomCostPerNight;
    private String roomStatus;
    
    public static int nextID = 0;

    // Constructors
    public Room(int bed, int kitch, int coffee, int accessibility, 
            int roomNumber, double roomCostPerNight)
    {
        this.roomID = nextID++;
        this.bedOption = bed;
        this.kitchenOption = kitch;
        this.coffeeOption = coffee;
        this.accessibleOption = accessibility;
        this.roomNumber = roomNumber;
        this.roomBooked = false;
        this.roomStatus = "Active";
        this.roomBookedQuantity = 0;
        this.roomCostPerNight = roomCostPerNight;
    }

    // Methods
    //setter for room activity ("Active" or "Inactive")
    public void setRoomActivity(String status)
    {
        this.roomStatus = status;
    } 
    
    //getter for room activity ("Active" or "Inactive")
    public String getRoomActivity()
    {
        return this.roomStatus;
    }
    
    //member method which returns a boolean true if the room is currently booked,
    //false if it is not booked
    public boolean isBooked()
    {
        if (this.roomBooked)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    //method that sets roomBooked data field as true and increments the roomBookedQuantity.
    //returns false and does neither if room is already booked. Returns roomBooked boolean
    //data field
    public boolean bookRoom()
    {
        if (this.roomBooked == true)
        {
            return false;
        }
        else
        {
            this.roomBooked = true;
            this.roomBookedQuantity++;
            return this.roomBooked;
        }
    }
    public void setRoomBook(boolean set)
    {
        this.roomBooked = set;
    }
    public void setRoomBookedQuantity(int count)
    {
        this.roomBookedQuantity = count;
    }
    
    //unbooks room/sets room to unbooked
    public void freeThisRoom()
    {
        this.roomBooked = false;
    }
    
    //getter for amount of times a specific room instance object has been booked
    public int getRoomBookedQuantity()
    {
        return this.roomBookedQuantity;
    }
    //getter for room ID
    public int getRoomID()
    {
        return this.roomID;
    }
    
    //getter for room number
    public int getRoomNumber()
    {
        return this.roomNumber;
    }
    
    //returns a string containing the total number of times a specific room object
    //has been booked as well as whether or not the room is currently booked
    public String roomAnalytics()
    {
        String answer = "";
        String booked = "";
        if (this.roomBooked)
        {
            booked = "Yes";
        }
        else
        {
            booked = "No";
        }
        answer += ("Number of times booked: " + this.getRoomBookedQuantity()
                + "\nIs Room currently booked? " + booked); 
        return answer;
    }
    
    //returns a string containing details about room specifications as well as information from
    //room analytics method above
    //Contributed by George Moya
    public String roomDescription()
    {
        String answer = "";
        answer += ("Room Number: " + this.getRoomNumber() 
                + "\n" + "Bed is: " + this.printBedOption() +"\n"
                + "Kitchen is: " + this.printKitchOption() + "\n"
                + "Coffee machine is: " + this.printCoffeeOption() + "\n"
                + "Accessibility option is: " + this.printAccessOption() + "\n"
                + "Room booked status: " + this.printRoomBooked() + "\n"
                + "Room cost per night is: " + this.roomCostPerNight + "\n"); 
          answer += this.roomAnalytics();
        return answer;
    }
    
    //returns a string containing the bed option for a specific room object
    //Contributed by George Moya
    public String printBedOption()
    {
        String bed = "";
        if(this.bedOption == 0)
            bed += "1x Queen bed";
        
        else if(this.bedOption == 1)
            bed+= "2x Queen beds";
        
        else if(this.bedOption == 2)
            bed+= "1x King bed";
        
        return bed;
    }
    
    //returns a string containing the kitchen option for a specific room object
    //Conributed by George Moya
    public String printKitchOption()
    {
        String kitch = "";
        if(this.kitchenOption == 0)
            kitch += "Microwave";
        else if(this.kitchenOption == 1)
            kitch += "Fridge + Microwave";
        return kitch;
    }
    
    //returns a string containing the coffee option for a specific room object
    //Contributed by George Moya
    public String printCoffeeOption()
    {
        String coffee = "";
        if(this.coffeeOption == 0)
            coffee += "1-Cup Std. Coffee Machine";
        else if(this.coffeeOption == 1)
            coffee += "Keurig Hot K200 Machine";
        return coffee;
    }
    
    //returns a string containing the accessibility option for a specific room object
    //Contributed by George Moya
    public String printAccessOption()
    {
        String access = "";
        if(this.accessibleOption == 0)
            access += "Standard Room";
        else if(this.accessibleOption == 1)
            access += "Enhanced Accessibility Room";
        return access;
    }
    
    //returns a string containing whether or not the room is booked
    //Contributed by Dylan Vetter
    public String printRoomBooked()
    {
        String check = "";
        if(roomBooked == false)
            check += "Room not booked";
        else if(roomBooked == true)
            check += "Room is booked";
        return check;
    }
    
    //setter for room number
    //Contributed by Dylan Vetter
    public boolean setRoomNumber(int roomNumber)
    {
        this.roomNumber = roomNumber;
        return false;
    }
}
