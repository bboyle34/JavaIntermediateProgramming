
package test1;

public class practice3 
{
    public static void main(String[] args) 
    {
        for (int i = 0; i < 10; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                if (j <= i)
                {
                    System.out.print(j);
                }
                else
                {
                    System.out.print(" ");
                }
            }
            System.out.println("");
        }
        System.out.println(" ");
        for (int a = 0; a < 10; a++)
        {
            for (int b = 0; b < 10; b++)
            {
                if ((9 - a) <= b)
                {
                    System.out.print(9 - b);
                }
                else
                {
                    System.out.print(" ");
                }
            }
            System.out.println("");
        }
        System.out.println(" ");
        for (int x = 0; x < 10; x++)
        {
            for (int y = 0; y < 10; y++)
            {
                if (x == y)
                {
                    System.out.print("1 ");
                }
                else
                {
                    System.out.print("0 ");
                }
            }
            System.out.println("");
        }
        System.out.println(" ");
        for (int c = 0; c < 10; c++)
        {
            for (int d = 0; d < 10; d++)
            {
                if ((9 - c) == d)
                {
                    System.out.print("1 ");
                }
                else
                {
                    System.out.print("0 ");
                }
            }
            System.out.println("");
        }
        System.out.println(" ");
        int stop = 0;
        boolean go = false;
        for (int m = 0; m < 9; m++)
        {
            for (int n = 0; n < 9; n++)
            {
                if ((4 - stop) <= n && n <= (4 + stop)) 
                {
                    System.out.print("1 ");
                }
                else
                {
                    System.out.print("0 ");
                }
            }
            if (m == 4)
            {
                go = true;
            }
            if (go)
            {
                stop--;
            }
            else
            {
                stop++;   
            }
            System.out.println(" ");
        }
        System.out.println(" ");
        int num = 8;
        for (int f = 0; f < 6; f++)
        {
            for (int g = 0; g < 12; g++)
            {
                if (num == 10)
                {
                    System.out.print("J");
                    num = 1;
                }
                else
                {
                    System.out.print(num++);
                }
            }
            System.out.println("");
        }
        System.out.println(" ");
        
        System.out.println(averageThree(45.7, 4.5, 90.3));
        System.out.println(" ");
        System.out.println(customPow(5.5, 3));
        System.out.println(" ");
        System.out.println(emailBuilder("Bob Marty Mickelson"));
        System.out.println(" ");
        System.out.println(stringManipulation("abcdefghijklmnop"));
        System.out.println(" ");
        System.out.println(vowelCount("Example output"));
        System.out.println(" ");
        System.out.println(emailGen("Brendan", "Boyle"));
        System.out.println(" ");
        System.out.println(minValue(4, 2));
        System.out.println(minValue(21, 100));
        System.out.println(minValue(50, 50));
        System.out.println(" ");
        mirrorImage("Hello There!");
        
    }
    public static double averageThree(double num1, double num2, double num3)
    {
        double average = ((num1 + num2 + num3) / 3);
        
        return average;
    }
    public static double customPow(double number, int power)
    {
        double output = Math.pow(number, power);
        
        return output;
    }
    public static String emailBuilder(String email)
    {
        String firstInitial = email.substring(0, 1);
        String middleInitial = email.substring(email.indexOf(" ") + 1, email.indexOf(" ") + 2);
        String lastName = email.substring((email.indexOf(" ", email.indexOf(" ") + 1)));
        lastName = lastName.toLowerCase();
        String finalEmail = (lastName.substring(1, 6) + middleInitial.toLowerCase() + firstInitial.toLowerCase() + "@dukes.jmu.edu");
        
        return finalEmail;        
    }
    public static String stringManipulation(String alpha)
    {

        for (int i = alpha.length(); i > 0; i--)
        {
            if (i == 3)
            {
                break;
            }
            System.out.println(alpha.substring((i - 4), i));
        }
        
        return alpha;
    }
    public static String vowelCount(String sentence)
    {
        sentence = sentence.toLowerCase();
        int a = 0, e = 0, i = 0, o = 0, u = 0;
        for (int x = 0; x < sentence.length(); x++)
        {
            char holder = sentence.charAt(x);
            switch (holder)
            {
                case 'a':
                    a++; break;
                case 'e':
                    e++; break;
                case 'i':
                    i++; break;
                case 'o':
                    o++; break;
                case 'u':
                    u++; break;
            }
        }
        return "a: " + a + " e: " + e + " i: " + i + " o: " + o + " u: " + u;
    }
    public static String emailGen(String firstName, String lastName)
    {
        return lastName + firstName.substring(0, 2) + "@dukes.jmu.edu";
    }
    public static int minValue(int value1, int value2)
    {
        if (value1 < value2)
        {
            return value1;
        }
        else if (value2 < value1)
        {
            return value2;
        }
        else
        {
            return -1;
        }
    }
    public static void mirrorImage(String phrase)
    {
        for (int q = phrase.length(); q > 0; q--)
        {
            System.out.print(phrase.charAt(q - 1));
        }
        System.out.print(phrase);
        System.out.println("");
    }
}