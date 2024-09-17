package mechanicsBE;
import java.util.*;

public class slTTTBoard {
    int[][] game_board;

    public slTTTBoard(){
        game_board = new int[3][3];
        System.out.println("Hello and welcome to my Tic Tac Toe game!");
        System.out.println("Rules:\n----------------------\nWe here at MovsesyanGamesStudio© Inc pride ourselves by being a 0 index game studio.");
        System.out.println("That means when entering where you wish to mark on the board start counting from 0 instead of 1.");
        System.out.println("You enter 'Q' or 'q' anytime during your turn in the game to end it!");
    }

    public void printBoard() {

        int ct = 0;
        for (int[] ints : game_board) {
            for (int anInt : ints) {
                if (ct >= 3){
                    System.out.println("\n");

                    ct = 0;
                }
                if (anInt == 1){
                    System.out.print("X    ");
                }
                else if (anInt == 0){
                    System.out.print("-    ");
                }

                ct++;
            }
        }
        System.out.println();

    }

    public boolean gameOver(int[][] game_Board) {
        final int GRID_MAX_SIZE = 2;
        // checks the rows for a win
        for(int row = 0; row <= GRID_MAX_SIZE; row++){
            int ticks = 0;
            for(int col = 0; col <= GRID_MAX_SIZE; col++){
                if (game_Board[row][col] == 1){
                    ticks++;
                }
            }
            if (ticks == 3) {
                return true;
            }
        }
        // checks the columns for a win
        for(int col = 0; col <= GRID_MAX_SIZE; col++){
            int ticks = 0;
            for(int row = 0; row <= GRID_MAX_SIZE; row++){
                if (game_Board[row][col] == 1){
                    ticks++;
                }
            }
            if (ticks == 3) {
                return true;
            }
        }
        // checks the diagonals for a win
        if(game_board[0][0] == 1 && game_board[1][1] == 1 && game_board[2][2] == 1){
            return true;
        }
        if(game_board[0][2] == 1 && game_board[1][1] == 1 && game_board[2][0] == 1){
            return true;
        }
        return false;
    }

    /**
     * @param game_board The current ticktacktoe board
     * @param row The row which the player chooses to place a tick in
     * @param col The column which the player chooses to place a tick in
     * @return The ticktacktoe board after the chosen modifications have been applied to it
     */
    private int[][] mark_tic(int[][] game_board, int row, int col){
        final int GRID_MAX_SIZE = 2;
        if (row > GRID_MAX_SIZE || col > GRID_MAX_SIZE){
            System.out.println("Invalid row or col number!");
        }
        else{
            if (game_board[row][col] == 0){
                game_board[row][col] = 1;
            }
            else{
                System.out.println("Please try again that space is already occupied.");
            }
        }
        return game_board;
    }



    public void play(){
        boolean first = true;
        while(true) {
            if (first) {
                Scanner sc = new Scanner(System.in);
                System.out.println("Would you like to go first (Y|N): ");
                if (sc.next().equalsIgnoreCase("Y")){
                    System.out.print("Please enter the coords where you would like to place your mark (Separate by a space): ");

                    try {
                        int row = sc.nextInt();
                        int col = sc.nextInt();
                        first = false;
                        mark_tic(game_board, row, col);

                    } catch (Exception e) {
                        String response = sc.next();
                        if (response.equalsIgnoreCase("q")){
                            break;
                        }
                        if (e.toString().equals("java.util.InputMismatchException")) {
                            System.out.println("You have entered an invalid number, Try again.");
                        }
                    }
                } else if (sc.next().equalsIgnoreCase("N")) {
                    //Call a function to have machine take first move.
                    System.out.println();
                }
                else{
                    System.out.println("You have entered an invalid number, Try again.");
                }


            }
            else{
                printBoard();
                System.out.print("Please enter the coords where you would like to place your mark (Seperate by a space): ");
                Scanner sc = new Scanner(System.in);
                try {
                    int row = sc.nextInt();
                    int col = sc.nextInt();
                    mark_tic(game_board, row, col);
                    if (gameOver(game_board)){
                        System.out.println("Game over :(");
                    }

                } catch (Exception e) {
                    String response = sc.next();
                    if (response.equalsIgnoreCase("q")){
                        break;
                    }
                    if (e.toString().equals("java.util.InputMismatchException")) {
                        System.out.println("You have entered an invalid number, Try again.");
                    }
                }
            }
        }
//        System.out.println("Thank you for playing Tic Tac Toe game!");
    }
}
