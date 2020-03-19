
package Ch2and3;
import java.util.Scanner;

public class SwitchStatements {

    public static void main(String[] args) 
    {
     Scanner input = new Scanner(System.in);
     int hour = 0;
     int day = 0;
     int substanceML = 0;
     System.out.print("Please enthe hour and day: ");
     hour = input.nextInt();
     day = input.nextInt();
     
     //allow for any given hour 1-12 an day 1-7
     
     switch(hour)
     {
         case 1:
         case 4:
         case 5:
         case 8:
         case 10: substanceML = 2;
            break;
         case 2:
         case 6:
         case 9: substanceML = 4;
            break;
         case 3:
         case 7: substanceML = 6;
            break;
         default: substanceML = 7;
            break;
     }
     if ((day == 1) && ((hour == 1) || (hour == 5)))
     {
         substanceML = 10;
     }
     if (day == 5 && hour == 9)
     {
         substanceML = 5;
     }
     System.out.println("Substance addition " + substanceML);
    }
    
}
