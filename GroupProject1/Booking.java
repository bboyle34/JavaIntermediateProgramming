
package GroupProject1;
import java.util.*;
// Class Definition File (CDF)


//Description: A class which defines Booking objects. A booking is instantiated with several details about each specific booking,
//as well as an object from the room class assigning a specific Room object to each booking object.
//Contributed by Brendan Boyle and Andrew Taylor
public class Booking 
{
    // Data Fields
    public int bookingID;
    public Room bookedRoom;
    public Guest bookingGuest;
    public int bookingYear;
    public int checkInDay;
    public int checkOutDay;
    public ArrayList<RoomService> roomServices = new ArrayList<RoomService>();
    
    public static int nextID = 0;
    public static int nextListID = 0;
    // Constructors
    public Booking(Guest bookingGuest, Room bookedRoom, int year, int checkIn, 
            int checkOut)
    {
        this.bookingID = nextID++;
        this.bookedRoom = bookedRoom;
        this.bookingGuest = bookingGuest;
        this.bookingYear = year;
        this.checkInDay = checkIn;
        this.checkOutDay = checkOut;
        this.bookedRoom.bookRoom();
    }

    // Methods
    public void addRoomService(RoomService r)
    {
        this.roomServices.add(r);       
    }
    
    //Returns a string with a description of a specific booking
    public String describeBooking()
    {
        String answer = "";
        answer += ("Booking Guest: " + this.bookingGuest.describeGuest() + "\nBooked Room: " 
                + this.bookedRoom.roomDescription());
        if (!(this.roomServices.isEmpty())) {
            answer += "\nRoom Service Charges: \n";
        for (int i=0; i< roomServices.size(); i++) {
            answer += "Desc: " + roomServices.get(i).description + ", Price: $" + roomServices.get(i).price + "\n";
        } 
        } else {
            answer += "\n";
        }
        answer += " " + "\nCheck in: " + this.getDate(this.checkInDay) 
                + "\nCheck Out: " + this.getDate(this.checkOutDay) + "\nTotal Booking Cost: $" + this.getTotalCost();
        return answer;
    }
    //ends booking - which frees the room associated with the booking
    public void endBooking()
    {
        this.bookedRoom.freeThisRoom();
    }
    
    public String shortBookingDescription() {
        String returnString = "";
        
        returnString += "Room #" + this.bookedRoom.roomNumber + " booked by: " + this.bookingGuest.getGuestName()
                + "Check out: " + this.getDate(this.checkOutDay);
        return returnString;
    }
    
    public String checkMonth(int dayInOrOut) {
        //returns string with month name
        String returnString = "";
        
        if (dayInOrOut >=0 && dayInOrOut <=31) {
            returnString += "January";
        } else if (dayInOrOut >31 && dayInOrOut <=59) {
            returnString += "February";
        } else if (dayInOrOut > 59 && dayInOrOut <=90) {
            returnString += "March";
        } else if (dayInOrOut > 90 && dayInOrOut <= 120) {
            returnString += "April";
        } else if (dayInOrOut > 120 && dayInOrOut <= 151) {
            returnString += "May";
        } else if (dayInOrOut > 151 && dayInOrOut <= 181) {
            returnString += "June";
        } else if (dayInOrOut > 181 && dayInOrOut <= 212) {
            returnString += "July";
        } else if (dayInOrOut > 212 && dayInOrOut <= 243) {
            returnString += "August";
        } else if (dayInOrOut > 243 && dayInOrOut <= 273) {
            returnString += "September";
        } else if (dayInOrOut > 273 && dayInOrOut <= 304) {
            returnString += "October";
        } else if (dayInOrOut > 304 && dayInOrOut <= 334) {
            returnString += "November";
        } else if (dayInOrOut > 334) {
            returnString += "December";
        }
        
        return returnString;
    }
    
    public int checkDay(int day) {
        String month = checkMonth(day);
        //day-monthnum = day
        int monthnum = 0;
        switch(month) {
            case("January"): monthnum = 0; break;
            case("February"): monthnum = 31; break;
            case("March"): monthnum = 59; break;
            case("April"): monthnum = 90; break;
            case("May"): monthnum = 120; break;
            case("June"): monthnum = 151; break;
            case("July"): monthnum = 181; break;
            case("August"): monthnum = 212; break;
            case("September"): monthnum = 243; break;
            case("October"): monthnum = 273; break;
            case("November"): monthnum = 304; break;
            case("December"): monthnum = 334; break;
        }
        return (day - monthnum);
    }
    
    public String getDate(int day) {
        String returnString = "";
        
        returnString += this.checkMonth(day) + " ";
        returnString += this.checkDay(day) + ", ";
        if (checkOutNextYear(this.checkInDay, this.checkOutDay) && (day < this.checkInDay)) {
            returnString += (this.bookingYear + 1);
        } else {
        returnString += this.bookingYear;
        }
        
        return returnString;
    }
    
    public boolean checkOutNextYear(int dayin, int dayOut) {
        boolean a = false;
        if (checkMonth(dayin).equals("December") && (!(checkMonth(dayOut).equals("December")))) {
            a = true;
        }
        return a;
    }
    
    public int getDaysStayed() {
        int daysstayed = 0;
        if (checkMonth(this.checkInDay).equals("December") && (!(checkMonth(this.checkOutDay).equals("December")))) {
            daysstayed = (365-this.checkInDay) + this.checkOutDay;
        } else {
            daysstayed = this.checkOutDay-this.checkInDay;
        }
        return daysstayed;
    }
    
    //returns as double the total cost (amt spent) on a booking
    public double getTotalCost() {
        double amt = 0;
        amt += (this.getDaysStayed() * this.bookedRoom.roomCostPerNight);
        for (int i = 0; i<roomServices.size(); i++) {
        amt += roomServices.get(i).price;
        }
        return amt;
    }
}
