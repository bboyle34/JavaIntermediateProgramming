
package finalExam;
// Class Definition File (CDF)

public class DataBitPlus extends DataBit
{
    // Data Fields
    public int thirdBit;

    // Constructors
    public DataBitPlus()
    {
        super();
        this.thirdBit = 0;
    }

    // Methods
    public void setBits(boolean firstBitFlag, boolean secondBitFlag)
    {
        super.setBits(firstBitFlag, secondBitFlag);
        if (firstBitFlag)
        {
            this.thirdBit += 2;
        }
        else
        {
            this.thirdBit++;
        }
    }
    public void describeBit()
    {
        super.describeBit();
        System.out.println(" 3rd: " + this.thirdBit + " databitplus");
    }
}
