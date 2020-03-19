
package ch10;
// Class Definition File (CDF)

public class Owner 
{
    // Data Fields
    public String ownerName;
    public int ownerYear;

    // Constructors
    public Owner()
    {
        this.ownerName = "";
        this.ownerYear = 0;
    }
    public Owner(String ownerName, int ownerYear)
    {
        this.ownerName = ownerName;
        this.ownerYear = ownerYear;
    }

    // Methods
    public String displayInformation()
    {
        String answer = "";
        answer += ("Name: " + this.ownerName + ", Year: " + this.ownerYear);
        return answer;
    }
}
