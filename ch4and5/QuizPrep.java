
package ch4and5;
import java.util.Scanner;

public class QuizPrep 
{

    public static void main(String[] args) 
    {

        Scanner input = new Scanner(System.in);
//       int num = 0;
//       for (int i = 0; i < 5; i++)
//       {
//           for (int j = 0; j < 10; j++)
//           {
//               if (num < 10)
//               {
//                   System.out.print(num++);
//               }
//               else
//               {
//                   num = 0;
//                   System.out.print(num++);
//               }
//           }
//           System.out.println("");
//       }
       
       System.out.print("Input your string: ");
       String phrase = input.nextLine();
       
       for (int i = phrase.length(); i > 0; i--) 
       {
           System.out.print(phrase.charAt(i - 1));
       }
       System.out.println("");
//       
//       
       String line = "Hello Everyone";
       System.out.println(line.charAt(6));
       System.out.println(line.indexOf('E'));
       System.out.println(line.substring(6, (line.length())));
       System.out.println(line.length());
//       
//       
//       System.out.print("Enter a line: ");
//       String cocaine = input.nextLine();
//       
//       for (int i = cocaine.length(); i > 0; i--)
//       {
//           System.out.print(cocaine.charAt(i - 1));
//       }
//       System.out.println("");
//       
//
//        String index = "Hello";
//        System.out.println(index.length());

//        int a = 5;
//        int b = 4;
//        
//        a += ++a + a + --b + b-- + a++;
//
//        System.out.println(a + " " + b);
        
        int num = 7;
        
        for (int a = 0; a < 7; a++)
        {
            for (int b = num; b > 0; b--)
            {
                System.out.print(num);
            }
            System.out.println("");
            num--;
        }
        
        int com = 2;
        
        for (int i = 0; i < 6; i++)
        {
            for (int j = com; j > 0; j--);
            {
              System.out.print(com);
            }
            System.out.println(" ");
            com = com + 2;
        }
//        
//        for (int test = 10; test > 0; test--)
//        {
//            System.out.print("10");
//        }


//        boolean t1 = true;
//        boolean t2 = false;
//        if (t1 ^ t2)
//        {
//            System.out.println("Yes");
//        }
//        System.out.println("no");

        String b = "93";
        System.out.printf("%s - %d\n\ndone!", b, b.length());
        
    }

}
