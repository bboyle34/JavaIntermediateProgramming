
package GroupProject1;

public class test1 
{
    public static void main(String[] args) 
    {
        String name = "Pass!?@#$";
        System.out.println(name.substring(0));
        int count = 1;
        for (int i = 0; i < name.length(); i++)
        {
            if (name.substring(i, count).matches("[^A-Za-z0-9]"))
            {
                System.out.println("it worked");
            }
            else
            {
                System.out.println("nope");            
            }
            count++;
        }
    }
}
