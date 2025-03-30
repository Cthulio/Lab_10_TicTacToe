public class TicTacToe {
    //declaring row and col counts is somewhat unnecessary, but it would allow us to change the game later more easily if we decided to.
    private static final int ROWS= 3;
    private static final int COLS = 3;
    private static String[][] board = new String[ROWS][COLS];

    public static void main(String[] args) {
clearBoard();
        display();
    }

    //sets all the board elements to a space
    private static void clearBoard()
    {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                board[r][c]="x";
            }
        }
    }

    //shows the Tic Tac Toe game used as part of the prompt for the users move choice.
    private static void display()
    {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                System.out.print(board[r][c]);
            }
            System.out.println();
        }
    }

}
