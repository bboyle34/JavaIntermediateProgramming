
package ch4and5;

public class forLoops 
{
    public static void main(String[] args) 
    {
       int num = 2;
       int count = 1;
       
       for (int i = 0; i < 6; i++)
       {
           for (int j = count; j > 0; j--)
           {
               System.out.print(num);
               System.out.print(" ");
           }
           System.out.println("");
           num += 2;
           count++;
       }
       
       String comp = "Hello Everyone";
       
       for (int z = comp.length(); z > 0; z--)
       {
           System.out.print(comp.charAt(z - 1));
       }
    }

}
