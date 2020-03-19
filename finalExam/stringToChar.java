
package finalExam;

public class stringToChar 
{
    public static void main(String[] args) 
    {
        char[] test = {'h', 'e', 'l', 'l', 'o'};
        char[][] table = new char[2][5];
        table[0][0] = 'h';
        table[0][1] = 'e';
        table[0][2] = 'l';
        table[0][3] = 'l';
        table[0][4] = 'o'; 
        table[1][0] = 't';
        table[1][1] = 'h';
        table[1][2] = 'e';
        table[1][3] = 'r';
        table[1][4] = 'e';
        
        printArray(table);
        String[] output1 = charToString(table);
        printArray(output1);
        char[][] output2 = stringToChar(output1);
        printArray(output2);
    }
    public static String[] charToString(char[][] table)
    {
        String [] array = new String[table.length];
        for (int i = 0; i < table.length; i++)
        {
            String word = "";
            for (int j = 0; j < table[i].length; j++)
            {
                word += table[i][j];
            }
            array[i] = word;
        }
        return array;
    }
    public static char[][] stringToChar(String[] array)
    {
        char[][] table = new char[array.length][];
        for (int i = 0; i < array.length; i++)
        {
            table[i] = new char[array[i].length()];
            for (int j = 0; j < array[i].length(); j++)
            {
                table[i][j] = array[i].charAt(j);
            }
        }
        return table;
    }
    public static void printArray(String[] array)
    {
        for (int i = 0; i < array.length; i++)
        {
            System.out.println("[" + array[i] + "]");
        }
    }
    public static void printArray(char[][] table)
    {
        for (int i = 0; i < table.length; i++)
        {
            for (int j = 0; j < table[i].length; j++)
            {
                System.out.print("[" + table[i][j] + "]");
            }
            System.out.println();
        }
    }
}
