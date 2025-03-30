import java.util.Scanner;
public class TicTacToe {
    //declaring row and col counts is somewhat unnecessary, but it would allow us to change the game later more easily if we decided to.
    private static final int ROWS= 3;
    private static final int COLS = 3;
    private static String[][] board = new String[ROWS][COLS];
    private static Scanner in = new Scanner(System.in);
    private static int turn = 1;
    private  static String currentPlayer = "x";//default/first is x

    //main class
    public static void main(String[] args) {

        int tempRow;
        int tempCol;
        do {
            clearBoard();

            while (!(isWin(currentPlayer) || isTie())) {//if noone has won yet, change the player.
                if (turn > 1)// we always want to start with 'x', this would change it to 'o' if we ran it turn 1.
                    if (currentPlayer == "o")
                    {
                        currentPlayer = "x";
                    }
                    else
                        currentPlayer = "o";
                display();
                tempCol = -1;
                tempRow = -1;

                do {
                    System.out.print("Turn: "+turn);
                    tempRow = SafeInput.getRangedInt(in, "Row", 1, ROWS) - 1;
                    tempCol = SafeInput.getRangedInt(in, "Column", 1, COLS) - 1;
                } while (!isValidMove(tempRow, tempCol));
                board[tempCol][tempRow] = currentPlayer;
                turn++;

                if (turn > 4) {
                    if (isWin(currentPlayer)) {
                        display();
                        System.out.print(currentPlayer + " wins!");
                    }
                }

            }
        }while(SafeInput.getYNConfirm(in,"Play Again?"));
    }

    //sets all the board elements to a space
    private static void clearBoard()
    {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                board[r][c]=" ";
            }
        }
        currentPlayer = "x";
        turn = 1;
    }

    //shows the Tic Tac Toe game used as part of the prompt for the users move choice.
    private static void display()
    {
        for (int c = 0; c < 3; c++) {
            for (int r = 0; r < 3; r++) {
                System.out.print(board[r][c]);
            }
            System.out.println();
        }
    }

    // returns true if there is a space at the given proposed move coordinates which means it is a legal move.
    private static boolean isValidMove(int row, int col)
    {
        boolean moveValidated = false;
        if((row>=0 & row < ROWS & col >= 0 & col < COLS)
        & (board[col][row] == "" || board[col][row] == " "))
            moveValidated = true;
        else
            System.out.println("invalid move!");
        return moveValidated;
    }

    // checks to see if there is a win state on the current board for the specified player (X or O) This method in
    // turn calls three additional methods that break down the 3 kinds of wins that are possible.
    private static boolean isWin(String player)
    {
        boolean winFound = false;
        //we can check if the values match three in a row anywhere, agnostic of which value it is until we find a win.
        if (isColWin(player)||isRowWin(player)||isDiagonalWin(player))
        {
            winFound = true;
        }
        return winFound;
    }

    // checks for a col win for specified player
    private static boolean isColWin(String player)
    {
        boolean winFound = false;
        if(     (board[0][0].equals(player) & board[1][0].equals(player) & board[2][0].equals(player)) ||
                (board[0][1].equals(player) & board[1][1].equals(player) & board[2][1].equals(player)) ||
                (board[0][2].equals(player) & board[1][2].equals(player) & board[2][2].equals(player))
        )
        {
            winFound = true;
        }
        return winFound;
    }

    // checks for a row win for the specified player
    private static boolean isRowWin(String player)
    {
        boolean winFound = false;
        if(     (board[0][0].equals(player) & board[0][1].equals(player) & board[0][2].equals(player)) ||
                (board[1][0].equals(player) & board[1][1].equals(player) & board[1][2].equals(player)) ||
                (board[2][0].equals(player) & board[2][1].equals(player) & board[2][2].equals(player))
        )
        {
            winFound = true;
        }
        return winFound;
    }

    // checks for a diagonal win for the specified player
    private static boolean isDiagonalWin(String player)
    {
        boolean winFound = false;
        if(     (board[0][0].equals(player) & board[1][1].equals(player) & board[2][2].equals(player)) ||
                (board[0][2].equals(player) & board[1][1].equals(player) & board[2][0].equals(player))
        )
        {
            winFound = true;
        }
        return winFound;
    }

    // checks for a tie condition: all spaces on the board are filled OR there is an X and an O in every win vector
    private static boolean isTie()
    {
        boolean tieFound = false;
        //if it's the last turn and there were no wins found, it is a tie.
        if(turn == 10 && !(isDiagonalWin(currentPlayer)||isColWin(currentPlayer)||isRowWin(currentPlayer)))
        {
            tieFound = true;
            System.out.print("It's a Tie!");

        }
        return tieFound;
    }
}