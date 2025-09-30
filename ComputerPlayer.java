package core;
import ui.GameConsoleUI;

/**
 * @author Emmie Auffant
 * @version 1.0
 */

public class ComputerPlayer {

    public static int randNum;

    // Runs computers turn with different prints and actions than having player 2
    public static void computerTurn(){

    System.out.println(GameLogic.BLUE + "My Turn!" + GameLogic.RESET);
        waitTurn();
        randNum = core.GameLogic.roll();
        System.out.println("I move " + randNum + " spaces!");
        GameLogic.playerPos[1] += randNum;
        GameLogic.checkSOL();

        System.out.println();
        GameLogic.printBoard();
        System.out.println();

        // Check Win
        if(GameLogic.playerPos[1] >= 100){
            GameLogic.printBoard();
            System.out.println();
            System.out.println("Ha I win!!!");
            GameLogic.gameStatus = true;
        }

        waitTurn();

    }

    // Adds delay so code is readable before computer takes turn (1.5 seconds)
    public static void waitTurn(){

        try {Thread.sleep(1500);} catch (InterruptedException waiting) {}

    }

}
