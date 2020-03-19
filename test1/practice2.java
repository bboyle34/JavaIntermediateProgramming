
package test1;

public class practice2 
{
    public static void main(String[] args) 
    {
//        for (int i = 0; i < 20; i++)
//        {
//            if ((i % 2) == 0)
//            {
//                System.out.println("This number is even");
//            }
//            else
//            {
//                continue;
//            }
//            if (i == 11)
//            {
//                System.out.println("This is 11");
//            }
//        }
//        System.out.println("This is the end");
        
//        for (int x = 0; x < 10; x++)
//        {
//            for (int y = 0; y < 10;y++)
//            {
//                if (y == 0)
//                {
//                    System.out.print("0 ");
//                }
//                else if (y == 9)
//                {
//                    System.out.print("9 ");
//                }
//                else if (x == 0)
//                {
//                    System.out.print(y + " ");
//                }
//                else if (x == 9)
//                {
//                    System.out.print(y + " ");
//                }
//                else if (x == y)
//                {
//                    System.out.print(y + " ");
//                }
//                else if ((9 - y) == x)
//                {
//                    System.out.print(y + " ");
//                }
//                else
//                {
//                    System.out.print("  ");
//                }
//            }
//            System.out.println("");
//        }
        
//        int number = 5;
//        for (int i = 0; i < 6; i++)
//        {
//            for (int j = 0; j < 12; j++)
//            {
//                if (number == 10)
//                    number = 0;
//                System.out.print(number);
//                number++;
//            }
//            System.out.println(" ");
//        }
//        
//        String phrase = "I am fucked for this test";
//        for (int x = phrase.length(); x > 0; x--)
//        {
//            System.out.print(phrase.charAt(x - 1));
//        }
//        System.out.println(" ");
//        
//        int wordCount = 1;
//        String matt = "Hi world what is up bitches muthafuckin";
//        for (int a = 0; a < matt.length(); a++)
//        {
//            if (matt.charAt(a) == ' ')
//                wordCount++;
//        }
//        System.out.println(wordCount);
        
        int count = 6;
        int num = 0;
        for (int i = 0; i < 12; i++)
        {
            for (int j = 0; j < 6; j++)
            {
                if (count <= j)
                {
                    System.out.print((6 - j) + " ");
                }
                else
                {
                    System.out.print("  ");
                }
            }
            if (count == 0)
            {
                num++;
            }
            if (num == 0)
            {
                count--;
            }
            else
            {
                count++;
            }
            System.out.println("");
        }
        System.out.println(" ");
        int number = 1;
        for (int i = 0; i < 12; i++)
        {
            for (int j = 0; j < 6; j++)
            {
                if ((9 - i) <= j)
                {
                    System.out.print((9 - number++) + " ");
                }
                else
                {
                    System.out.print("  ");
                }
            }
            if (count == 0)
            {
                number++;
            }
            if (number == 0)
            {
                count--;
            }
            else
            {
                count++;
            }
            System.out.println("");
        }
        
    }

}
