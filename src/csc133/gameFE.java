package csc133;
import csc133.spot.*;
import java.util.Scanner;
public class gameFE {

    public boolean promptToStart() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello and welcome to my Tic Tac Toe game!");
        System.out.println("Rules:\n----------------------\nWe here at MovsesyanGamesStudio© Inc pride ourselves by being a 0 index game studio.");
        System.out.println("That means when entering where you wish to mark on the board start counting from 0 instead of 1.");
        System.out.println("You enter 'Q' or 'q' anytime during your turn in the game to end it!");
        System.out.print("Would you like to go first (Y|N): ");
        String user_input = sc.next();
        if (user_input.equalsIgnoreCase("N")) {
            return true;
        }
        else {
            return false;
        }
    }

    public void print_exit_message(int gameStatus) {
//        switch (gameStatus) {
//            case 1:
//                System.out.println("Sorry to see you go; come again!");
//                break;
//            case 2:
//                System.out.println("Congratulations! you have won!");
//                break;
//            case 3:
//                System.out.println("Sorry, you did not win; try again!");
//                break;
//            case 4:
//                System.out.println("Hey, you almost beat me, let's try again!");
//                break;
//            default: // case: game incomplete
//                break;
//        }
    }
}
