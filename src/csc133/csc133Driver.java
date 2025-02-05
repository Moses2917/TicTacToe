package csc133;
import mechanicsBE.slTTTBoard;
import static csc133.gameFE.*;
import static csc133.spot.GAME_INCOMPLETE;
import static csc133.spot.GAME_QUIT;

public class csc133Driver {
    private final slTTTBoard my_board = new slTTTBoard();
    private final csc133.gameFE my_fe = new csc133.gameFE();
    public static void main(String[] args) {
        (new csc133Driver()).startGame();
    } // public static void main(String] args)
    private void startGame() {
        my_board.printBoard();
        int game_status = GAME_INCOMPLETE;
        while (GAME_QUIT != game_status) {
            my_fe.print_exit_message(game_status);
            my_board.resetBoard();
            if (my_fe.promptToStart()) { // Would this not prompt continually. Unless I hardcoded it not too.
                my_board.playRandom();
                my_board.printBoard();
            } // if (my_fe.promptToStart())
            game_status = my_board.play(); // Return the game status/update the game status, Is the actual game play loop
        } // while (...)
//        my_fe.promptToStart(); // I believe this to be a big therefore it must be commented out
    } // public static void startGame()
} // public class csc133Driver

