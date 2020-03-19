
package finalExam;

public class DataBitApp 
{
    public static void main(String[] args) 
    {
        DataBit[] bitArray = new DataBit[3]; 
        boolean f = true; 
        boolean t = false; 
        for (int i=0;i<3;i++) 
        { 
            if (i%2 == 0) 
            { 
                bitArray[i] = new DataBitPlus(); 
            } 
            else 
                bitArray[i] = new DataBit(); 
        } 
        for (int i=0;i<3;i++) 
        { 
            bitArray[i].setBits(t, f); 
            t = !t; 
            f = !f; 
        } 
        System.out.println("Done!"); 
        for (int i = 0; i < 3; i++)
        {
            bitArray[i].describeBit();
            System.out.println("");
        }
        bitArray[0].describeBit();
        DataBit d = new DataBitPlus();
        d.describeBit();
        DataBitPlus b = new DataBitPlus();
        
    }

}
