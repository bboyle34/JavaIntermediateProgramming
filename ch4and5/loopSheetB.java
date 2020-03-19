
package ch4and5;

public class loopSheetB 
{
    public static void main(String[] args) 
    {
//       for (int i = 10; i > 0; i--)
//       {
//           System.out.print(i + " ");
//           if (i % 2 == 0)
//           {
//               System.out.print("\b\b");
//           }
//       }
       
       for (int a = 0; a < 10; a++)
       {
           for (int b = 0; b < 10; b++)
           {
               if (a == b)
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

       for (int x = 0; x < 10; x++)
       {
           for (int y = 0; y < 10; y++)
           {
               if (y == x)
               {
                   System.out.print("0 ");
               }
               else if (y < x)
               {
                   System.out.print("1 ");
               }
           }
           System.out.println("");
       }
       
        int num = 11;
        for (int m = 0; m < 4; m++)
       {
           for (int n = 0; n < 10; n++)
           {
              if (n < 4)
              {
                  System.out.print(num++ + " ");
              }
              else
              {
                  System.out.print(num-- + " ");
              }
           }
           num += 9;
           System.out.println("");
       }
    }

}
