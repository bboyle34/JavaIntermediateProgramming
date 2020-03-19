
package ch4and5;

public class loopSheetA 
{
    public static void main(String[] args) 
    {
        for (int i = 5; i >= 0; i--)
        {
            for (int j = 0; j < i; j++)
            {
                System.out.print(j + " ");
            }
            System.out.println("");
        }
        
        int num = 1;
        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                if (j < 4)
                {
                    System.out.print(num + " ");
                    num++;
                }
                else
                {
                    System.out.print(num + " ");
                    num--;
                }
            }
            System.out.println("");
            num += 12;
        }
        System.out.println("");
        for (int a = 5; a > 0; a--)
        {
            System.out.print("Row #" + a + ": ");
            for (int b = 10; b < 20; b++)
            {
                System.out.print(b + " ");
            }
            System.out.println("");
        }
    }

}
