//package csc133;
import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) {

        int[][] game_board = new int[3][3];
        System.out.println("Hello and welcome to my Tic Tac Toe game!");
        System.out.println("Rules:\n----------------------\nWe here at MovsesyanGamesStudio© Inc pride ourselves by being a 0 index game studio.");
        System.out.println("That means when entering where you wish to mark on the board start counting from 0 instead of 1.");
        System.out.println("Inorder to begin either type 'Y' to begin or 'N' to stop the game.");
        System.out.println("You enter 'N' anytime during your turn in the game to end it!");
        System.out.print("Do you wish to continue: ");
        String response = new Scanner(System.in).next();
        if (response.equalsIgnoreCase("y")){
            System.out.println("Lets begin, here's your board");
            printBoard(game_board);
        }

    }

//    private static void printBoard(int[][] gameBoard) {
//    }
    public static void printBoard(int[][] board){
        int ct = 0;
        for (int[] ints : board) {
            for (int anInt : ints) {
                if (ct >= 3){
                    System.out.println("");

                    ct = 0;
                }
                if (anInt == 1){
                    System.out.print("X ");
                }
                else if (anInt == 0){
                    System.out.print("- ");
                }

                ct++;
            }
        }
        System.out.println("");
    }
}