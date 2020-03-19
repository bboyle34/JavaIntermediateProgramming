
package finalExam;
// Class Definition File (CDF)

public class DataBit 
{
    // Data Fields
    public int firstBit;
    public int secondBit;

    // Constructors
    public DataBit()
    {
        this.firstBit = 0;
        this.secondBit = 0;
    }

    // Methods
    public void setBits(boolean firstBitFlag, boolean secondBitFlag)
    {
        if (firstBitFlag)
        {
            this.firstBit += 2;
        }
        else
        {
            this.firstBit++;
        }
        if (secondBitFlag)
        {
            this.secondBit += 2;
        }
        else
        {
            this.secondBit++;
        }
    }
    public void describeBit()
    {
        System.out.println("1st: " + this.firstBit + " 2nd: " + this.secondBit);
    }
}
