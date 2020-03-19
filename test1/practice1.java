
package test1;

public class practice1 
{
    public static void main(String[] args) 
    {
//        for (int i = 0; i < 10; i++)
//        {
//            int num = 1;
//            for (int j = 0; j < 10; j++)
//            {
//                if ((9 - j) == i)
//                {
//                    System.out.print((9 - j) + " ");
//                }
//                else if (j == i)
//                {
//                    System.out.print(j + " ");
//                }
//                else
//                {
//                    System.out.print("  ");
//                }
//            }
//            System.out.println("");
//        }
        
//        int counter = 0;
//        boolean swapIncrement = false;
//        for(int i = 0; i < 12; i++) {
//            if(counter > 6) {
//                swapIncrement = true;
//            }
//            if(swapIncrement == true) {
//                counter--;
//            }
//            else {
//                counter++;
//            }
//            for(int j = 7; j > 0; j--) {
//                if(j >= counter) {
//                    System.out.print(" ");
//                }
//                else {
//                    System.out.print(j);
//                }
//            }
//            System.out.println("");
//
//        }
//        int j = 4;
//        int r = 2;
//        while (r < ++j)
//        {
//            for (int i = 2; i > 0; i--)
//            {
//                if (j % r == 0)
//                {
//                    r += --r;
//                }
//                j++;
//                r++;
//            }
//        }
//        System.out.println("r is equal to " + r + " and j is equal to " + j);
        
//        int count = 0;
//        int num = 0;
//        for (int i = 0; i < 13; i++)
//        {
//            for (int j = 0; j < 7; j++)
//            {
//                if (count >= j)
//                {
//                    System.out.print(j + " ");
//                }
//                else
//                {
//                    System.out.print("  ");
//                }
//            }
//            if (count == 6)
//            {
//                num++;
//            }
//            if (num == 0)
//            {
//                count++;
//            }
//            else
//            {
//                count--;
//            }
//            System.out.println("");
//        }
        
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

    }

}
