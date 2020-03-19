
package test1;

public class practice4 
{
    public static void main(String[] args) 
    {
        String st1 = "this is a test";
        String st2 = "this is a test too";
        String st3 = "we are different";
        
        System.out.println(st1.substring(0, 4));
        System.out.println(st2.compareTo(st1));
        
        System.out.println(st3.indexOf("are"));
        System.out.println(st3.charAt(7));
        System.out.println(st2.indexOf("h", 2));
        System.out.println(st2.length());
        System.out.println(st2.indexOf("o", 17));
    }

}
