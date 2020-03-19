
package Quiz2;

public class Quiz2Prep 
{

    public static void main(String[] args) 
    {
        int[] array = {3, 5, 2, 6};
        printArray(array);
        shiftArray(array, 1);
        printArray(array);
        System.out.println("Even integer counter = " + findEvens(array) + "\n\n");
        
        Pet firstPet = new Pet("AJ", "Cat", 3);
        firstPet.giveShot("Parvo");
        firstPet.giveShot("Rabies");
        Pet secondPet = new Pet("Cashmere", "Dog", 6);
        secondPet.giveShot("Parvo");
        secondPet.giveShot("Rabies");
        secondPet.giveShot("VitaBoost");
        System.out.println(firstPet.status());
        System.out.println(secondPet.status());
        
    }
    public static void shiftArray(int[] array, int moves)
    {
        //shift array numbers one over
        int temp = 0;
        int last = 0;
        int temp2 = 0;
        for (int i = 0; i < moves; i++)
        {
            last = array[array.length - 1];
            temp2 = array[0];
            for (int j = 0; j < array.length; j++)
            {
                if (j == (array.length - 1))
                    break;
                else
                    temp = array[j + 1];
                    array[j + 1] = temp2;
                    temp2 = temp;
            }
            array[0] = last;
        }
    }
    public static void printArray(int[] array)
    {
        for (int i = 0; i < array.length; i++)
            System.out.print("[" + array[i] + "]");
        System.out.println("");
    }
    public static int findEvens(int[] array)
    {
        int large = 0;
        int count = 0;
        for (int i = 0; i < array.length; i++)
        {
            if (array[i] % 2 == 0){
                count++;
            }
            if ((array[i] % 2 == 0) && (array[i] > large))
                large = array[i];
        }
        System.out.println("The largest even number is " + large);
        return count;
    }
    
}
