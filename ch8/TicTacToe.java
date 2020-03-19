
package ch8;
import java.util.Scanner;

public class TicTacToe 
{
    public static void main(String[] args) 
    {
        Scanner input = new Scanner(System.in);
        String[][] matrix = new String[3][3];
        for (int i = 0; i < matrix.length; i++)
        {
            for (int j = 0; j < matrix[i].length; j++)
            {
                matrix[i][j] = " ";
            }
        }
        String winner = "continue";
        do
        {
            String playerX = "X";
            String playerO = "O";
            winner = playerMove(playerX, matrix, winner);
            if (winner.equalsIgnoreCase(playerX))
                break;
            winner = playerMove(playerO, matrix, winner);           

        } while (winner.equalsIgnoreCase("continue"));
        printBoard(matrix);
        if (winner.equalsIgnoreCase("Draw"))
            System.out.println("It's a draw!");
        else
            System.out.println(winner + " player won");
    }
    public static void printBoard(String[][] board)
    {
        System.out.println("-------------");
        System.out.println("| " + board[0][0] +  " | " + board[0][1] + " | " + board[0][2] + " |");
        System.out.println("-------------");
        System.out.println("| " + board[1][0] +  " | " + board[1][1] + " | " + board[1][2] + " |");
        System.out.println("-------------");
        System.out.println("| " + board[2][0] +  " | " + board[2][1] + " | " + board[2][2] + " |");
        System.out.println("-------------");
    }
    public static String gameDecision(String[][] game, String winner, String player)
    {
        if ((game[0][0].equalsIgnoreCase(player)) && (game[0][1].equalsIgnoreCase(player)) && (game[0][2].equalsIgnoreCase(player)))
            winner = player;
        else if ((game[0][0].equalsIgnoreCase(player)) && (game[1][0].equalsIgnoreCase(player)) && (game[2][0].equalsIgnoreCase(player)))
            winner = player;
        else if ((game[1][0].equalsIgnoreCase(player)) && (game[1][1].equalsIgnoreCase(player)) && (game[1][2].equalsIgnoreCase(player)))
            winner = player;
        else if ((game[2][0].equalsIgnoreCase(player)) && (game[2][1].equalsIgnoreCase(player)) && (game[2][2].equalsIgnoreCase(player)))
            winner = player;
        else if ((game[0][1].equalsIgnoreCase(player)) && (game[1][1].equalsIgnoreCase(player)) && (game[2][1].equalsIgnoreCase(player)))
            winner = player;
        else if ((game[0][2].equalsIgnoreCase(player)) && (game[1][2].equalsIgnoreCase(player)) && (game[2][2].equalsIgnoreCase(player)))
            winner = player;
        else if ((game[0][0].equalsIgnoreCase(player)) && (game[1][1].equalsIgnoreCase(player)) && (game[2][2].equalsIgnoreCase(player)))
            winner = player;
        else if ((game[0][2].equalsIgnoreCase(player)) && (game[1][1].equalsIgnoreCase(player)) && (game[2][0].equalsIgnoreCase(player)))
            winner = player;
        else
            winner = "continue";
        int counter = 0;
        for (int i = 0; i < game.length; i++)
        {
            for (int j = 0; j < game[i].length; j++)
            {
                if (game[i][j].equalsIgnoreCase("X") || game[i][j].equalsIgnoreCase("O"));
                {
                    counter++;
                }
            }
        }
        if (counter == 10)
            winner = "Draw";
        return winner;
    }
    public static String playerMove(String player, String[][] matrix, String winner)
    {
        Scanner input = new Scanner(System.in);
        printBoard(matrix);        
        boolean aid = true;
        while (aid)
        {
            System.out.print("Enter a row (0, 1, 2) for player " + player + ": ");
            int move1 = input.nextInt();
            System.out.print("Enter a row (0, 1, 2) for player " + player + ": ");
            int move2 = input.nextInt();
            
            if (matrix[move1][move2].equalsIgnoreCase(" "))
            {
                matrix[move1][move2] = player;
                aid = false;
            }                
            else
                System.out.println("Please make your move in an open space.");                
        }
        winner = gameDecision(matrix, winner, player);
        System.out.println("");
        return winner;
    }

}
