
package test2;

public class DataBit 
{
    private int bit;
    public DataBit coBit;
    
    public static int bitCount = 0;
    
    public DataBit(int bit)
    {
        this.bit = bit;
        this.coBit = null;
        bitCount++;
    }
    
    public int getBit()
    {
        return this.bit;
    }
    public void attachCoBit(DataBit coBit)
    {
        this.coBit = coBit;
    }
}
