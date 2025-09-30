package ui;
import core.GameLogic;
import core.ComputerPlayer;

import java.io.IOException;
import java.util.Scanner;

public class GameConsoleUI {

    public static void main(String[] args) throws InterruptedException, IOException {
        System.out.println("Welcome To Snakes and Ladders");
        System.out.println("Would you like to play against a computer? Type yes or no");
        System.out.println();

        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        System.out.println();

        if(input.equals("yes")){
            System.out.println("Great! Player 2 is the computer.");
            System.out.println("Press Enter to Start");
            scan.nextLine();
            System.out.println();
            core.GameLogic.computer = true;
            GameLogic gl = new GameLogic();
            gl.startGame();
            gl.play();
        } else if(input.equals("no")){
            System.out.println("Press Enter to Start");
            scan.nextLine();
            core.GameLogic.computer = false;
            GameLogic gl = new GameLogic();
            gl.startGame();
            gl.play();
        } else {
            scan.nextLine();
            throw new IllegalArgumentException("Invalid input");
        }

    }

}
