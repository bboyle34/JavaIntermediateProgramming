
package GroupProject1;
// Class Definition File (CDF)

public class RoomService 
{
    // Data Fields
    public int ID;
    public int valueGuestID;
    public String description;
    public double price;
    public Booking booking;
    
    public static int nextID = 0;

    // Constructors
    public RoomService(String description, double price, Booking booking)
    {
        this.ID = nextID++;
        this.description = description;
        this.price = price;
        this.valueGuestID = -1;
        this.booking = booking;
    }

    // Methods
}
