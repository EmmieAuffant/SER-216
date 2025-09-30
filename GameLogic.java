package core;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * This class includes all necessary game logic to make the game work and display in the console
 *
 * @author  Emmie Auffant
 * @version 3.0
 *
 * */

public class GameLogic {

    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static boolean computer;
    static int currentPlayer;
    static int[] playerPos = {0,0};
    static int[][] board = new int[10][10];
    static int num = 1;
    static boolean gameStatus = false;
    static int randNum;
    Scanner scan = new Scanner(System.in);


    // Sets all game variables to default 0 and resets board
    public void startGame(){

        currentPlayer = 0;
        playerPos = new int[]{0,0};
        gameStatus = false;
        createBoard();

    }

    // Entire game functionality where it loops until the game is won
    public void play(){

        while(!gameStatus){

        // Player 1 turn
          if (currentPlayer == 0) {

              System.out.print(YELLOW);
              printPlayerTurn();

              playerPos[0] += randNum;
              checkSOL();
              System.out.println();
              printBoard();
              System.out.println();

              if(playerPos[0] >= 100){
                System.out.println(YELLOW + "Player 1 " + RESET + "Wins!");
                System.out.println("Final Board:");
                System.out.println();
                gameStatus = true;
              }
          }

          // Player 2 turn + computer turn if applicable
          if (currentPlayer == 1 && computer) {
                core.ComputerPlayer.computerTurn();
          }
          else if (currentPlayer == 1) {

              System.out.print(BLUE);
              printPlayerTurn();

              playerPos[1] += randNum;
              checkSOL();
              System.out.println();
              printBoard();
              System.out.println();

              if(playerPos[1] >= 100){
                System.out.println(BLUE + "Player 2 " + RESET + "Wins!");
                System.out.println("Final Board:");
                System.out.println();
                gameStatus = true;
              }
          }
          swapPlayer();
        }
    }

    //Helper method for printing the game onto the console and prompting the player
    public void printPlayerTurn(){

      System.out.println("Player " + (currentPlayer + 1) + RESET + ": Press Enter to Roll");
      scan.nextLine();
      randNum = roll();
      System.out.println("Player " + (currentPlayer + 1) + " moves " + randNum + " spaces");
      System.out.println();
      ComputerPlayer.waitTurn();

    }

    // quickly swaps player
    public void swapPlayer(){
        if(currentPlayer == 0){
          currentPlayer = 1;
        } else if (currentPlayer == 1){
          currentPlayer = 0;
        }
    }

    // Generates random number for each turn (1-6)
    public static int roll(){
        Random roll = new Random();
        randNum = roll.nextInt(6) + 1;
        return randNum;
    }

    // Generates the numbers for the board and adds to an array
    public void createBoard(){
        for(int row = 0; row < 10; row++){
          for(int col = 0; col < 10; col++){
            board[row][col] = num++;
          }
        }
    }

    // Snakes and Ladders check
    public static void checkSOL(){
        if(playerPos[currentPlayer] == 3){
            System.out.println("Player " + (currentPlayer + 1) + " Climbs up the Ladder!");
            playerPos[currentPlayer] = 25;
        }
        else if(playerPos[currentPlayer] == 27){
            System.out.println("Player " + (currentPlayer + 1) + " Climbs up the Ladder!");
            playerPos[currentPlayer] = 57;
        }
        else if(playerPos[currentPlayer] == 97){
            System.out.println("Player " + (currentPlayer + 1) + " The snake brings you back down!");
            playerPos[currentPlayer] = 19;
        }
        else if(playerPos[currentPlayer] == 74){
            System.out.println("Player " + (currentPlayer + 1) + " The snake brings you back down!");
            playerPos[currentPlayer] = 36;
        }
    }

    // Prints the board, checks each number to see if player is there or a snake or ladder
    public static void printBoard(){

        for (int i = 9; i >= 0; i--){
          for(int j = 9; j >= 0; j--){
            if (playerPos[1] == board[i][j] && playerPos[0] == board[i][j]) {
                System.out.print(PURPLE + "TIE" + RESET + "|");
            }
            else if (playerPos[0] == board[i][j]) {
                System.out.print(YELLOW + "P1" + RESET + " |");
            }
            else if (playerPos[1] == board[i][j]) {
                System.out.print(BLUE + "P2" + RESET + " |");
            }
            else if (board[i][j] == 100 && playerPos[0] >= 100) {
                System.out.print(YELLOW + "P1" + RESET + " |");
            }
            else if (board[i][j] == 100 && playerPos[1] >= 100) {
                System.out.print(YELLOW + "P1" + RESET + " |");
            }
            else if(board[i][j] == 3){
                System.out.print(GREEN + "L1" + RESET + " |");
            }
            else if(board[i][j] == 27){
                System.out.print(GREEN + "L2" + RESET + " |");
            }
            else if(board[i][j] == 25){
                System.out.print(GREEN + "H1" + RESET + " |");
            }
            else if(board[i][j] == 57){
                System.out.print(GREEN + "H2" + RESET + " |");
            }
            else if(board[i][j] == 19){
                System.out.print(RED + "T1" + RESET + " |");
            }
            else if(board[i][j] == 36){
                System.out.print(RED + "T2" + RESET + " |");
            }
            else if(board[i][j] == 97){
                System.out.print(RED + "S1" + RESET + " |");
            }
            else if(board[i][j] == 74){
                System.out.print(RED + "S2" + RESET + " |");
            }
            else if(board[i][j] < 10){
              System.out.print(board[i][j] + "  |");
            }
            else if(board[i][j] == 100){
              System.out.print(board[i][j] + "|");
            }
            else{
              System.out.print(board[i][j] + " |");
            }
          }
        System.out.println();
        }
    }
}