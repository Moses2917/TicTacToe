package csc133;
import csc133.spot.*;
import java.util.Scanner;
public class gameFE {
    private final int[][] game_board;
    Scanner sc = new Scanner(System.in);

    public gameFE() {
        game_board = new int[3][3];

    }

    public boolean promptToStart() {
        System.out.println("Hello and welcome to my Tic Tac Toe game!");
        System.out.println("Rules:\n----------------------\nWe here at MovsesyanGamesStudio© Inc pride ourselves by being a 0 index game studio.");
        System.out.println("That means when entering where you wish to mark on the board start counting from 0 instead of 1.");
        System.out.println("You enter 'Q' or 'q' anytime during your turn in the game to end it!");
        System.out.print("Would you like to go first (Y|N): ");
        String user_input = sc.next();
        if (user_input.equalsIgnoreCase("N")) {
            return true;
        }
        return true;
    }
}
