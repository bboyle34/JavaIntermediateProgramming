
package Quiz2;

public class TwoDArrays 
{

    public static void main(String[] args) 
    {
        int[][][] threeDim = new int[3][3][3];
        for (int i = 0; i < threeDim.length; i++)
        {
            for (int j = 0; j < threeDim[i].length; j++)
            {
                for (int y = 0; y < threeDim[i][j].length; y++)
                    threeDim[i][j][y] = (i + j + y);
            }
        }
        System.out.println(threeDim[0][1][2] + " " + threeDim[1][0][2] + " "
                + threeDim[0][2][1] + " " + threeDim[2][2][2]);
    }
    
}
