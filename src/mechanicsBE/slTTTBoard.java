package mechanicsBE;

import java.util.Scanner;

public class slTTTBoard {
    int[][] game_board;

//    public slTTTBoard(){
//        game_board = new int[3][3];
//        System.out.println("Hello and welcome to my Tic Tac Toe game!");
//        System.out.println("Rules:\n----------------------\nWe here at MovsesyanGamesStudio© Inc pride ourselves by being a 0 index game studio.");
//        System.out.println("That means when entering where you wish to mark on the board start counting from 0 instead of 1.");
//        System.out.println("You enter 'Q' or 'q' anytime during your turn in the game to end it!");
//    }

    public void printBoard() {

        int ct = 0;
        for (int[] ints : game_board) { //switch anint and ints if need be
            for (int anint : ints) {
                if (ct >= 3){
                    System.out.println("\n");

                    ct = 0;
                }
                if (anint == 1){
                    System.out.print("X    ");
                }
                if (anint == 2){
                    System.out.print("O    ");
                }
                else if (anint == 0){
                    System.out.print("-    ");
                }

                ct++;
            }
        }
        System.out.println();

    }

    public void printBoard(int[][] game_board) {

        int ct = 0;
        for (int[] ints : game_board) { //switch anint and ints if need be
            for (int anint : ints) {
                if (ct >= 3){
                    System.out.println("\n");

                    ct = 0;
                }
                if (anint == 1){
                    System.out.print("X    ");
                }
                if (anint == 2){
                    System.out.print("O    ");
                }
                else if (anint == 0){
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
        else if (gameOver(game_board) == -1){
            System.out.println("Its a draw!");
        }
    }

    public int gameOver(int[][] game_board) {
        // returns the player num that won or 0 if no win yet
        final int GRID_MAX_SIZE = 2;
        final int player_1_wins = 1;
        final int player_2_wins = 2;
        final int draw = -1;

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
        // At this point all possible combos have been tried for a win
        // if no win yet and all pieces are full it's a draw
        int tick_mark = 0;
        for (int row = 0; row <= GRID_MAX_SIZE; row++){
            for (int col = 0; col <= GRID_MAX_SIZE; col++){
                if (game_board[row][col] != 0){
                    tick_mark++;
                }
            }
        }
        if (tick_mark == 9){ // 9 because square count is equal to 9
            return draw;
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
    private int[][] tick_mark(int[][] game_board, int row, int col, int player){
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

    private boolean block(int[][] game_board, int player_num){
        int opponent_num = 1;
        if (player_num == 1){
            opponent_num = 2;
        }
        int row_mark = 0;
        int col_mark = 0;
        for (int i = 0; i < 3; i++) {
            if (game_board[i][0] == opponent_num && game_board[i][1] == opponent_num && game_board[i][2] == opponent_num) row_mark++;
            if (row_mark == 2){
                return true;
            }
            if (game_board[0][i] == opponent_num && game_board[1][i] == opponent_num && game_board[2][i] == opponent_num) col_mark++;
            if (col_mark == 2){
                return true;
            }
        }
        int diagonal_mark = 0;
        if (game_board[0][0] == opponent_num && game_board[1][1] == opponent_num && game_board[2][2] == opponent_num);
        if (game_board[0][2] == opponent_num && game_board[1][1] == opponent_num && game_board[2][0] == opponent_num);
        return false;
    }

    public int score(int[][] game_board, int player_num) {
        int opponent_num = 1;
        if (player_num == 1){
            opponent_num = 2;
        }
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if (game_board[i][0] == player_num && game_board[i][1] == player_num && game_board[i][2] == player_num) return Integer.MAX_VALUE;
            if (game_board[0][i] == player_num && game_board[1][i] == player_num && game_board[2][i] == player_num) return Integer.MAX_VALUE;
        }

        // Check diagonals
        if (game_board[0][0] == player_num && game_board[1][1] == player_num && game_board[2][2] == player_num) return Integer.MAX_VALUE;
        if (game_board[0][2] == player_num && game_board[1][1] == player_num && game_board[2][0] == player_num) return Integer.MAX_VALUE;

        // Check for opponent's win
        for (int i = 0; i < 3; i++) {
            if (game_board[i][0] == opponent_num && game_board[i][1] == opponent_num && game_board[i][2] == opponent_num) return Integer.MIN_VALUE;
            if (game_board[0][i] == opponent_num && game_board[1][i] == opponent_num && game_board[2][i] == opponent_num) return Integer.MIN_VALUE;
        }

        // Check diagonals for opponent's win
        if (game_board[0][0] == opponent_num && game_board[1][1] == opponent_num && game_board[2][2] == opponent_num) return Integer.MIN_VALUE;
        if (game_board[0][2] == opponent_num && game_board[1][1] == opponent_num && game_board[2][0] == opponent_num) return Integer.MIN_VALUE;

        // If no winner yet, score is neutral
        return 0;
    }



    private int[][] possible_marks(int[][] game_board, int player_num) {
        final int GRID_MAX_SIZE = 2;
        int[][] possible_marks = new int[3][3];
        for (int row = 0; row <= GRID_MAX_SIZE; row++) {
            for (int col = 0; col <= GRID_MAX_SIZE; col++) {
                if (game_board[row][col] == 0) { // Check for empty cells
                    possible_marks[row][col] = 1;
                }
            }
        }
        return possible_marks;
    }


    private int[][] future_marks(int[][] game_board, int player_num) {
        final int GRID_MAX_SIZE = 2;


        if (player_num == 1) {
            int bestScore = Integer.MIN_VALUE;
            int bestRow = -1;
            int bestCol = -1;


            for (int row = 0; row <= GRID_MAX_SIZE; row++) {
                for (int col = 0; col <= GRID_MAX_SIZE; col++) {
                    if (game_board[row][col] == 0) {
                        game_board[row][col] = player_num;
                        int score = score(game_board, player_num);
                        game_board[row][col] = 0;

                        if (score > bestScore) {
                            bestScore = score;
                            bestRow = row;
                            bestCol = col;
                        }
                    }
                }
            }

            if (bestRow != -1 && bestCol != -1) {
                game_board[bestRow][bestCol] = player_num;

            }
        } else {
            int worstScore = Integer.MAX_VALUE;
            int bestRow = -1;
            int bestCol = -1;
            int PLAYER_2 = 2;

            for (int row = 0; row <= GRID_MAX_SIZE; row++) {
                for (int col = 0; col <= GRID_MAX_SIZE; col++) {
                    if (game_board[row][col] == 0) {
                        game_board[row][col] = PLAYER_2;
                        int score = score(game_board, player_num);
                        game_board[row][col] = 0;

                        if (score < worstScore) {
                            worstScore = score;
                            bestRow = row;
                            bestCol = col;
                        }
                    }
                }
            }

            if (bestRow != -1 && bestCol != -1) {
                game_board[bestRow][bestCol] = PLAYER_2;

            }
        }

//        printBoard(game_board);
        return game_board;
    }

    /**
     * @param game_bord The current ticktacktoe board
     */
    public int[][] minimx_algo(int[][] game_bord, int player_num){
        // Start by analyzing the board, and how close either of you is to a win
        // Looks at all open slots, and recursively places an X or O
        // calculating a score along the way, at the end it goes with the lowest score
        // make it always want more than less, so reward it for taking
//        player_num = 2; // default num for a machine, can always be switched for a player pov as well
//        game_bord[0][0] = 2;
//        game_bord[0][1] = 1;
//        game_bord[2][0] = 1;
//        game_bord[2][1] = 2;
        return future_marks(game_bord, player_num);
//        printBoard();
//        game_bord[2][0] = 1;
//        pos_paths(game_bord, player_num);
//        held_coordinates(game_bord, player_num);
        // func to see what possible open path any player has

    }

    private int[][] machineMove(int[][] game_bord, int player_num){
//        boolean first =
        return minimx_algo(game_bord, player_num);
    }

    public void playRandom(int[][] game_bord, int player) {
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
            tick_mark(game_bord, row, col, player);
        }
        else{
            playRandom(game_bord, player);
        }
    }

    public void play(){
        boolean first = true;
        boolean again = false;
        // Default user and machine player values
        int user_player_num = 1;
        int machine_player_num = 2;
//        minimx_algo(game_board, machine_player_num);
        while(true) {
            if (again) {
                game_board = new int[3][3];
                printBoard();
                again = false;
            }
//            if (first) {
//                Scanner sc = new Scanner(System.in);
//                System.out.print("Would you like to go first (Y|N): ");
//                first = false;
//                String user_input = sc.next();
//                if (user_input.equalsIgnoreCase("Y")){
////                    user_player_num = 1;
////                    machine_player_num = 2;
//                    System.out.print("Please enter the coords where you would like to place your mark (Separate by a space):\nPlayer Move: ");
//                    try {
//                        int row = sc.nextInt();
//                        int col = sc.nextInt();
//
//                        tick_mark(game_board, row, col, user_player_num);
//
//                        game_board = machineMove(game_board, machine_player_num);
//
//                    } catch (Exception e) {
//                        String response = sc.next();
//                        if (response.equalsIgnoreCase("q")){
//                            break;
//                        }
//                        if (e.toString().equals("java.util.InputMismatchException")) {
//                            System.out.println("You have entered an invalid number, Try again.");
//                        }
//                    }
//                }else if (user_input.equalsIgnoreCase("N")) {
//                    //Call a function to have machine take first move.
//                    user_player_num = 2;
//                    machine_player_num = 1;
//                    System.out.println("Machine Move: ");
//                    playRandom(game_board, machine_player_num);
////                    System.out.println(row + " " + col);
//                }
//                else {
//                    System.out.println("You have entered an invalid response, Try again.");
//                    first = true;
////                    printBoard();
////                    play();
//                }
//
//                //could also set first = false here
//                // so that I can use it in machineMove
//
//            }
//            else{
            printBoard();
            System.out.print("Please enter the coords where you would like to place your mark (Seperate by a space):\nPlayer Move: ");
            Scanner sc = new Scanner(System.in);
            try {
                int row = sc.nextInt();
                int col = sc.nextInt();

                if (valid_mark(game_board, row, col)){
                    tick_mark(game_board, row, col, user_player_num);
                    announceWinner(user_player_num);
                    if(game_board[row][col] == 0){
                        game_board = machineMove(game_board, machine_player_num);
                        announceWinner(user_player_num);
                    }
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
                        again = true;

//                            play();
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
//            }
        }
    }

    public void resetBoard() {
        game_board = new int[3][3];
    }
}
