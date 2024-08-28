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
        System.out.println("");

    }

    public void play(){
        boolean first = true;
        while(true) {
            if (first) {
//                printBoard();

//                displayBoard();
                System.out.print("Please enter the coords where you would like to place your mark (Seperate by a space): ");
                try {
                    Scanner sc = new Scanner(System.in);
                    String response = sc.nextLine();
                    if (response.equalsIgnoreCase("q")){
                        break;
                    }
                    int row = sc.nextInt();
                    int col = sc.nextInt();
                    System.out.println(row + " :row, col: " + col);
                    first = false;
                    if (game_board[row][col] == 0){
                        game_board[row][col] = 1;
                    }

                } catch (Exception e) {
                    if (e.toString().equals("java.util.InputMismatchException")) {
                        System.out.println("You have entered an invalid number, Try again.");
                    }
                }

            }
            else{
                printBoard();
                System.out.print("Please enter the coords where you would like to place your mark (Seperate by a space): ");
                try {
                    Scanner sc = new Scanner(System.in);
                    String response = sc.nextLine();
                    if (response.equalsIgnoreCase("q")){
                        break;
                    }
                    int row = sc.nextInt();
                    int col = sc.nextInt();
//                    System.out.println(row + " :row, col: " + col);
                    first = false;
                    if (game_board[row][col] == 0){
                        game_board[row][col] = 1;
                    }
                    else {
                        System.out.println("Please try again that space is already occupied.");
                    }

                } catch (Exception e) {
                    if (e.toString().equals("java.util.InputMismatchException")) {
                        System.out.println("You have entered an invalid number, Try again.");
                    }
                }
            }


//            String row, col = response.split(" ");
        }
        System.out.println("Thank you for playing Tic Tac Toe game!");
    }

    private void displayBoard() {
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
        System.out.println("");
    }
}
