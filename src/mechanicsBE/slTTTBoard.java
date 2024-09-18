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
                if (anInt == 2){
                    System.out.print("O    ");
                }
                else if (anInt == 0){
                    System.out.print("-    ");
                }

                ct++;
            }
        }
        System.out.println();

    }

    private void announceWinner(int user_player_num){
        if (gameOver(game_board) == 1){
            if (user_player_num == 1) {
                System.out.println("You win!");
            }
            else if (user_player_num == 2) {
                System.out.println("You lost!");
            }
        }
        else if (gameOver(game_board) == 2){
            if (user_player_num == 2) {
                System.out.println("You win!");
            }
            else if (user_player_num == 1) {
                System.out.println("You lost!");
            }
        }
    }

    // TODO: Calculate a draw
    // if board full and func returns 0 -> Draw
    public int gameOver(int[][] game_board) {
        // returns the player num that won or 0 if no win yet
        final int GRID_MAX_SIZE = 2;
        final int player_1_wins = 1;
        final int player_2_wins = 2;
//        final int
        // checks the rows for a win
        for(int row = 0; row <= GRID_MAX_SIZE; row++){
            int player_1_ticks = 0;
            int player_2_ticks = 0;
            for(int col = 0; col <= GRID_MAX_SIZE; col++){
                if (game_board[row][col] == 1){
                    player_1_ticks++;
                }
                if (game_board[row][col] == 2){
                    player_2_ticks++;
                }
            }
            if (player_1_ticks == 3) {
                return player_1_wins;
            }
            if (player_2_ticks == 3) {
                return player_2_wins;
            }
        }
        // checks the columns for a win
        for(int col = 0; col <= GRID_MAX_SIZE; col++){
            int player_1_ticks = 0;
            int player_2_ticks = 0;
            for(int row = 0; row <= GRID_MAX_SIZE; row++){
                if (game_board[row][col] == 1){
                    player_1_ticks++;
                }
                if (game_board[row][col] == 2){
                    player_2_ticks++;
                }
            }
            if (player_1_ticks == 3) {
                return player_1_wins;
            }
            if (player_2_ticks == 3) {
                return player_2_wins;
            }
        }
        // checks the diagonals for a win
        if(game_board[0][0] == 1 && game_board[1][1] == 1 && game_board[2][2] == 1){
            return player_1_wins;
        }
        if(game_board[0][2] == 1 && game_board[1][1] == 1 && game_board[2][0] == 1){
            return player_1_wins;
        }
        if (game_board[0][0] == 2 && game_board[1][1] == 2 && game_board[2][2] == 2){
            return player_2_wins;
        }
        if(game_board[0][2] == 2 && game_board[1][1] == 2 && game_board[2][0] == 2){
            return player_2_wins;
        }
        return 0;
    }

    private boolean valid_mark(int[][] game_board, int row, int col){
        if (game_board[row][col] != 0){
            return false;
        }
        return true;
    }

    /**
     * @param game_board The current ticktacktoe board
     * @param row The row which the player chooses to place a tick in
     * @param col The column which the player chooses to place a tick in
     * @return The ticktacktoe board after the chosen modifications have been applied to it
     */
    private int[][] mark_tic(int[][] game_board, int row, int col, int player){
        final int GRID_MAX_SIZE = 2;
        if (row > GRID_MAX_SIZE || col > GRID_MAX_SIZE){
            System.out.println("Invalid row or col number!");
        }
        else{
            if (game_board[row][col] == 0){
                if (player == 1){
                    game_board[row][col] = 1;
                }
                if (player == 2){
                    game_board[row][col] = 2;
                }
            }
            else{
                System.out.println("Please try again that space is already occupied.");
            }
        }
        return game_board;
    }

    /**
     * @param game_bord The current ticktacktoe board
     */
    private void minimx_algo(int[][] game_bord){
    }

    private void machineMove(int[][] game_bord, int player){
        int row = 0;
        double randRow = Math.random();
        if (randRow > .6){
            row = 2;
        }
        else if (randRow > .3){
            row = 1;
        }
        double randCol = Math.random();
        int col = 0;
        if (randCol > .6){
            col = 2;
        }
        else if (randCol > .3){
            col = 1;
        }
        if (valid_mark(game_board, row, col)){
            mark_tic(game_bord, row, col, player);
        }
        else{
            machineMove(game_bord, player);
        }
    }

    public void play(){
        boolean first = true;
        // Default user and machine player values
        int user_player_num = 1;
        int machine_player_num = 2;
        while(true) {
            if (first) {
                Scanner sc = new Scanner(System.in);
                System.out.print("Would you like to go first (Y|N): ");
                first = false;
                String user_input = sc.next();
                if (user_input.equalsIgnoreCase("Y")){
//                    user_player_num = 1;
//                    machine_player_num = 2;
                    System.out.print("Please enter the coords where you would like to place your mark (Separate by a space):\nPlayer Move: ");
                    try {
                        int row = sc.nextInt();
                        int col = sc.nextInt();

                        mark_tic(game_board, row, col, user_player_num);

                        machineMove(game_board, machine_player_num);

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
                if (user_input.equalsIgnoreCase("N")) {
                    //Call a function to have machine take first move.
                    user_player_num = 2;
                    machine_player_num = 1;
                    System.out.println("Machine Move: ");
                    machineMove(game_board, 1);
//                    System.out.println(row + " " + col);
                }
//                else{
//                    System.out.println("You have entered an invalid number, Try again.");
//                }


            }
            else{
                printBoard();
                System.out.print("Please enter the coords where you would like to place your mark (Seperate by a space):\nPlayer Move: ");
                Scanner sc = new Scanner(System.in);
                try {
                    int row = sc.nextInt();
                    int col = sc.nextInt();

                    if (valid_mark(game_board, row, col)){
                        mark_tic(game_board, row, col, user_player_num);
                        announceWinner(user_player_num);
                        machineMove(game_board, machine_player_num);
                        announceWinner(user_player_num);
                    }
                    else{
                        System.out.println("You have entered an invalid number, Try again.");
                    }

                    if (gameOver(game_board) != 0){
                        System.out.print("Do you wish to play again (Y|N): ");
                        String play_again = sc.next();
                        if (play_again.equalsIgnoreCase("Y")){
                            // Sets up game to play again
                            first = true;
                            game_board = new int[3][3];
                            printBoard();
                            play();
                        } else if (play_again.equalsIgnoreCase("N")){
                            System.out.println("Have a good day!");
                        }
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
