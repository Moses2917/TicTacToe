package mechanicsBE;
import static csc133.spot.GAME_INCOMPLETE;
import static csc133.spot.GAME_QUIT;
import static csc133.spot.PLAYER_WIN;
import static csc133.spot.MACHINE_WIN;
import static csc133.spot.GAME_DRAW;
import java.util.Scanner;

public class slTTTBoard {
    int[][] game_board = new int[3][3];
    final int GRID_MAX_SIZE = 2; //Adjusted for 0 index board
    static int user_player_num = 1;
    static int machine_player_num = 2;
    static boolean machine_goes_first = false;
    static boolean machine_first_and_first_round = false;
//    public slTTTBoard(){
//        game_board = new int[3][3];
//        System.out.println("Hello and welcome to my Tic Tac Toe game!");
//        System.out.println("Rules:\n----------------------\nWe here at MovsesyanGamesStudio© Inc pride ourselves by being a 0 index game studio.");
//        System.out.println("That means when entering where you wish to mark on the board start counting from 0 instead of 1.");
//        System.out.println("You enter 'Q' or 'q' anytime during your turn in the game to end it!");
//    }

    public void printBoard() {
        // Possibly add a player var, then based on that if player = 2, print 2 as X not O, because machine is 1
        int ct = 0;
        for (int[] ints : game_board) { //switch anint and ints if need be
            for (int anint : ints) {
                if (ct >= 3){
                    System.out.println("\n");

                    ct = 0;
                }
                if (machine_goes_first){
                    if (anint == 1){
                        System.out.print("O    ");
                    }
                } else if (anint == 1){
                    System.out.print("X    ");
                }
                if (machine_goes_first){
                    if (anint == 2){
                        System.out.print("X    ");
                    }
                } else if (anint == 2){
                    System.out.print("O    ");
                }
                if (anint == 0){
                    System.out.print("-    ");
                }

                ct++;
            }
        }
        System.out.println();

    }

    private void announceWinner(int user_player_num){
        if (gameOver() == 1){
            if (user_player_num == 1) {
                System.out.println("Congratulations! you have won!");
            }
            else if (user_player_num == 2) {
                System.out.println("Sorry, you did not win; try again!");
            }
        }
        else if (gameOver() == 2){
            if (user_player_num == 2) {
                System.out.println("Congratulations! you have won!");
            }
            else if (user_player_num == 1) {
                System.out.println("Sorry, you did not win; try again!");
            }
        }
        else if (gameOver() == -1){

            System.out.println("Hey, you almost beat me, let's try again!");
        }
    }

    private void print_exit_message(int game_status){
        gameOver();
    }

    public int[] findNextMove(int[][] game_board, int player) {
        // Check horizontally
        for (int row = 0; row < game_board.length; row++) {
            int col = hasTwoConsecutive(game_board[row], player);
            if (col != -1) return new int[]{row, col};
        }

        // Check vertically
        for (int col = 0; col < game_board[0].length; col++) {
            int[] column = new int[game_board.length];
            for (int row = 0; row < game_board.length; row++) {
                column[row] = game_board[row][col];
            }
            int row = hasTwoConsecutive(column, player);
            if (row != -1) return new int[]{row, col};
        }

        // Check primary diagonal
        int diagIndex = hasTwoConsecutive(diagonalPrimary(game_board), player);
        if (diagIndex != -1) return new int[]{diagIndex, diagIndex};

        // Check secondary diagonal
        diagIndex = hasTwoConsecutive(diagonalSecondary(game_board), player);
        if (diagIndex != -1) return new int[]{diagIndex, game_board.length - 1 - diagIndex};

        // No immediate winning move found
        return null;
    }

    // Checks for two consecutive marks and an empty spot (0) in the array
    private int hasTwoConsecutive(int[] row, int player) {
        int count = 0;
        int emptyIndex = -1;

        for (int i = 0; i < row.length; i++) {
            if (row[i] == player) {
                count++;
            } else if (row[i] == 0) {
                emptyIndex = i; // Possible spot for the next move
            }
        }

        // Return the empty index if exactly two consecutive marks and an empty spot
        return (count == 2 && emptyIndex != -1) ? emptyIndex : -1;
    }

    // Extracts the primary diagonal from the game board
    private int[] diagonalPrimary(int[][] game_board) {
        int[] primaryDiagonal = new int[game_board.length];
        for (int i = 0; i < game_board.length; i++) {
            primaryDiagonal[i] = game_board[i][i];
        }
        return primaryDiagonal;
    }

    // Extracts the secondary diagonal from the game board
    private int[] diagonalSecondary(int[][] game_board) {
        int[] secondaryDiagonal = new int[game_board.length];
        for (int i = 0; i < game_board.length; i++) {
            secondaryDiagonal[i] = game_board[i][game_board.length - 1 - i];
        }
        return secondaryDiagonal;
    }

    // Finds the first available corner
    public int[] findOpenCorner(int[][] game_board, int player) {
        // First check and see if center is taken
        if (valid_mark(1,1)){
            return new int[]{1,1};
        }
        //Now checking all four corners
        if(valid_mark(0,0)){
            return new int[]{0,0};
        }
        if(valid_mark(2,0)){
            return new int[]{2,0};
        }
        if(valid_mark(0,2)){
            return new int[]{0,2};
        }
        if(valid_mark(2,2)){
            return new int[]{2,2};
        }
        return null;
    }

    public int gameOver() {
        // returns the player num that won or 0 if no win yet
//        final int GRID_MAX_SIZE = 2;
        final int player_1_wins = PLAYER_WIN;
        final int player_2_wins = MACHINE_WIN;

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
            return GAME_DRAW;
        }
        return GAME_INCOMPLETE;
    }

    private boolean valid_mark(int row, int col){
        if (game_board[row][col] != 0){
            return false;
        }
        return true;
    }

    /**
     * @param row The row which the player chooses to place a tick in
     * @param col The column which the player chooses to place a tick in
     */
    private void tick_mark( int row, int col, int player){
//        final int GRID_MAX_SIZE = 2;

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

    }

//    private boolean block(int player_num){
//
//    }

    private void machineMove( int player_num){
        // First: Check if any almost wins exist then block those
        // Second: If no blocks available and center taken, find open corners and take one
        // 2.5: Min-Max based on position of player
        //
        player_num = 1;//player_num != machine num
//        int[] move = findNextMove(game_board, player_num);
//        System.out.println("Near Win:"+ move[0]+" "+ move[1]);
        int[] nextMove = findNextMove(game_board, 1); // Find the next move for player 1
        int[] machineNextMove = findNextMove(game_board, 2);
        if (machineNextMove != null) {
            tick_mark(machineNextMove[0], machineNextMove[1],2);
        }
        else if (nextMove != null) {
            System.out.println("Next move: (" + nextMove[0] + ", " + nextMove[1] + ")");
            System.out.println("Placing a mark to prevent loss....");
            tick_mark(nextMove[0], nextMove[1], 2);
        } else {
            System.out.println("No immediate winning move found.");
//            playRandom();
            int[] openCorner = findOpenCorner(game_board, 2);
            System.out.println("Going back to playing corners");
//            System.out.println("Next move: (" + corner[0] + ", " + corner[1] + ")");
            tick_mark(openCorner[0], openCorner[1], 2);

        }
    }

    private boolean isEmpty(){
        for (int row = 0; row <= GRID_MAX_SIZE; row++){
            for (int col = 0; col <= GRID_MAX_SIZE; col++){
                if (game_board[row][col] != 0){
                    return false;
                }
            }
        }
        return true;
    }

    public void playRandom() {
//        int[][] game_bord int player
        if (isEmpty()){
            // if empty then first time, and since machine is being called to move for the first time, then that means that machine is player 1
//            user_player_num = 2;
//            machine_player_num = 1;
            machine_goes_first = true;
            machine_first_and_first_round = true;
        }

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
//        int[][] game_bord = game_board;

        if (valid_mark(row, col)){
            tick_mark(row, col, machine_player_num);
        }
        else{
            playRandom();
        }
    }

    public int play(){
        boolean first = true;
        boolean again = false;
        // Default user and machine player values

//        minimx_algo(game_board, machine_player_num);
        while(true) {
            if (!machine_first_and_first_round){
                printBoard();
            } else{
                machine_first_and_first_round = false;
            }

            System.out.print("Please enter the coords where you would like to place your mark (Seperate by a space):\nPlayer Move: ");
            Scanner sc = new Scanner(System.in);
            try {
                int row = sc.nextInt();
                int col = sc.nextInt();

                if (valid_mark(row, col)){
                    tick_mark(row, col, user_player_num); // player move
                    announceWinner(user_player_num);
                    if(gameOver() == GAME_INCOMPLETE){
                        machineMove(machine_player_num); // machine move
                        announceWinner(user_player_num);
                    }
                }
                else{
                    System.out.println("You have entered an invalid number, Try again.");
                }

                if (gameOver() != GAME_INCOMPLETE){
                    System.out.print("Do you wish to play again (Y|N): ");
                    String play_again = sc.next();
                    if (play_again.equalsIgnoreCase("Y")){
                        // Sets up game to play again
//                        first = true;
//                        again = true;
                        return GAME_INCOMPLETE;

//                            play();
                    } else if (play_again.equalsIgnoreCase("N")){
                        System.out.println("Have a good day!");
                        return GAME_QUIT;
                    }
                }
            } catch (Exception e) {
                String response = sc.next();
                if (response.equalsIgnoreCase("q")){
                    System.out.println("Sorry to see you go; come again!");
                    return GAME_QUIT;
//                    break;
                }
                if (e.toString().equals("java.util.InputMismatchException")) {
                    System.out.println("You have entered an invalid number, Try again.");
                }
            }
//            }
        }
//        return GAME_INCOMPLETE; //game_status;
    }

    public void resetBoard() {
        game_board = new int[3][3];
    }
}
