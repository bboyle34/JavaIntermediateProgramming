
package finalExam;
import java.util.*;

public class alphabet 
{
    public static void main(String[] args) 
    {
        String inputString = "the quick brown fox jumps over the lazy dog";
        String outputString = sortString(inputString);
        
        System.out.println("Input: " + inputString);
        System.out.println("Output: " + outputString);  
        System.out.println(0%2);
    }
    public static String sortString(String inputString)
    {
        char tempArray[] = inputString.toCharArray();
        Arrays.sort(tempArray);
        return new String(tempArray);        
    }
}
