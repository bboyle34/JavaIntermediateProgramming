//Description: A class that defines specific guest objects for the hotel. Contains methods
//to describe guests and also a method to check login credentials
//contributed by Brendan Boyle
package GroupProject1;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
// Class Definition File (CDF)

public class Guest 
{
    // Data Fields
    private int guestID;
    private String username;
    private String password;
    private String guestName;
    
    public static int nextID = 0;

    // Constructors
    public Guest(String username, String password, String guestName)
    {
        this.guestID = nextID++;
        this.username = username;
        this.password = password;
        this.guestName = guestName;
    }

    // Methods
    //checks to ensure that credentials sent in as username and password
    //match ones stored in memory upon creation of guest object.
    public boolean checkCredentials(String username, String password)
    {
        if (this.username.equals(username) && this.password.equals(password))
        {
                return true;
        }
        else
        {
            return false;
        }
    }
    
    //getter for guest name
    public String getGuestName()
    {
        return this.guestName;
    }
    
    //getter for username
    public String getUsername()
    {
        return this.username;
    }
    
    public int getGuestID()
    {
        return this.guestID;
    }
    public String getPassword()
    {
        return this.password;
    }
    
    //setter for guest name
    public void setGuestName(String guestName)
    {
        this.guestName = guestName;
    }
    
    //setter for guest password, requires old and new
    //password as parameters
//   public int setPassword(String oldP, String newP)
//    {
//        boolean operator = true;
//        if (newP.substring(0, 1).matches("[0-9]"))
//        {
//            operator = false;
//        }
//        int count = 1;
//        boolean b = false;
//        for (int i = 0; i < newP.length(); i++)
//        {
//            if (newP.substring(i).matches("[^A-Za-z0-9]"))
//            {
//                b = true;
//            }
//            count++;
//        }
//        Pattern p = Pattern.compile("[^A-Za-z0-9]");
//        Matcher m = p.matcher(newP);
//        b = m.find();
//        if (this.password.equalsIgnoreCase(oldP) && b && operator)
//        {
//            System.out.println("it worked");
//            this.password = newP;
//            return 0;
//        }
//        
//        else
//        {
//            return 1;
//        }
//    }
//    
    
        public int setPassword(String oldP, String newP)
    {
     
        int count = 0;
        
        if(Character.isDigit(newP.charAt(0))||oldP.matches(newP))
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
            
    //method which returns as a string a description of a guest
    public String describeGuest()
    {
        String answer = "";
        answer += ("Guest Name: " + this.getGuestName() 
                + ", Guest Username: " + this.getUsername());
        return answer;
    
}
}
