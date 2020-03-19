
package ch2;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class tester 
{

    public static void main(String[] args) 
    {
    String s = "2Brendan";
    {
     if (s == null || s.trim().isEmpty()) 
     {
         System.out.println("Incorrect format of string");
     }
     Pattern p = Pattern.compile("[^A-Za-z0-9]");
     Matcher m = p.matcher(s);
    // boolean b = m.matches();
     boolean b = m.find();
     if (b)
        System.out.println("There is a special character in my string ");
     else
         System.out.println("There is no special char.");
 }
}

}
