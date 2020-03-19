
package ch3;

public class QuizPrep 
{

    public static void main(String[] args) 
    {
//        int x = 4;
//        int y = 8;
//        x = x++ + ++y - y--;
//        System.out.println(x + " " + y);
//        
//        System.out.println((25%6) - (25/6));
//        
//        boolean isFalse = true;
//        boolean isTrue = true;
//        boolean isTrueToo = false;
//        
//        if (!isFalse)
//        {
//            isTrueToo = !isTrue;
//        }
//        else
//        {
//            isTrue = !(!isTrueToo);
//        }
//        System.out.println(isTrue + " " + isTrueToo + " " + isFalse);
        
//        int x = 5;
//        int y = 4;
//        x += ++x + ++y + y++;
//        System.out.println(x + " " + y);
//        
//        System.out.println((22%7) + (26/5));
        
        System.out.println("Post and pre incremental and decremental operators: \n");
        int temp = 2;
        System.out.println("Post incremental");
        System.out.println(temp);
        System.out.println(temp++ + " Here I increment the variable after using it");
        System.out.println(temp);
        
        System.out.println("Pre incremental");
        System.out.println(temp);
        System.out.println(++temp + " Here I increment the variable before using it");
        System.out.println(temp);
        
        System.out.println("Post decremental");
        System.out.println(temp);
        System.out.println(temp-- + " Here I decrement the variable after using it");
        System.out.println(temp);
        
        System.out.println("Pre decremental");
        System.out.println(temp);
        System.out.println(--temp + " Here I decrement the variable before using it");
        System.out.println(temp);
         
    }

}
