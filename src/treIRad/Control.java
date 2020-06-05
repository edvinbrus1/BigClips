package treIRad;

import javax.swing.*;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import static javafx.application.Application.launch;



/**
 * Controller class for tre i rad
 *
 * @author Amir Khodabakhshi
 */

public class Control implements Runnable {

    private final GUI gui;
    private Game game;
    private Ai ai;
    private int turn;
    private final Random rand;
    private int maxRandom;
    private int score;
    private int rounds;
    private final int scoreLoss = -25;
    private final int scoreDraw = 50;
    private final int scoreWin = 100;


    /**
     * Constructor which creates the gui and a new random object. Also initializes score and rounds values.
     */
    public Control() {
        score = 0;
        rand = new Random();
        gui = new GUI(this);
        rounds = 9;
        newRound();

    }


    public static void main(String[] args) {
        new Control();
    }

    /**
     * Method for starting a new round. Ends the game after 10 rounds have been played.
     *
     * @author Amir & Edvin
     */
    private void newRound() {
        if (rounds >= 0) {
            turn = 0;
            game = new Game();
            ai = new Ai(game);
            gui.resetGui();
            firstTurn();
            gui.setRoundsLeft(rounds);
            rounds--;
        } else {
            printScore();
            int input = JOptionPane.showOptionDialog(null, "Game Over!", "Game complete", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

            //Method for closing the game - Edvin
            if (input == JOptionPane.OK_OPTION) {
                closeGame();
            }
        }
    }

    /**
     * Closes the gui for tre i rad
     *
     * @author Edvin
     */
    private void closeGame() {
        gui.dispose();
    }


    /**
     *  Randomly sets how many turns the Ai should make random moves from within a given range
     * @param bound the bound for the nextInt function
     */
    private void randomize(int bound) {
        maxRandom = rand.nextInt(bound);
    }

    /**
     * Handles logic for the first turn in the game. Calls method to randomise who starts, if Ai calls on ai to move.
     */
    private void firstTurn() {

        game.randomiseTurn();
        if (game.getTurn() == Turn.Ai) {
            randomize(3);
            aiMove();
        } else {
            randomize(2);
        }
    }



    /**
     * Adds score from the previous round to the total score for tre i rad.
     * @param score the players score from the finished rund
     */
    private void addScore(int score) {
        this.score += score;
    }

    /**
     * Writes the totalscore of the player to TreScore.txt, overwriting any previous data saved there.
     */
    private void printScore() {
        try {
            //"false" makes sure the data is not appended, thus overwriting previously stored data.
            DataOutputStream out = new DataOutputStream(new FileOutputStream("src/resources/TreScore.txt", false));
            out.writeInt(score);
            out.flush();
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Checks if round is over and if the player won/lost/drew. Sets appropriate message and calls on popup-window method.
     * Calls method to add to player score. Also calls on method to update score on gui.
     * @return true if round is finished, false if its ongoing.
     */
    private boolean checkResult() {
        String str = "";

        if (game.checkWin() == Winner.None) {     //Checks if there is no winner
            if (game.checkDraw() == Winner.Draw) {      //Checks if game's a draw
                str = "Draw! You gain " + scoreDraw + " points";
                addScore(scoreDraw);  //If its a draw, adds points to total score


            } else return false;  //If no one has won yet, the method is stopped here

        } else if (game.checkWin() == Winner.Ai) {       //Checks if Ai won
            str = "Victory! " + scoreLoss + " points";
            addScore(scoreLoss);    //If Ai won, points are reduced

        } else if (game.checkWin() == Winner.Player) {       //Checks if player has won
            str = scoreWin + " points";
            addScore(scoreWin); //Adds points if player won
        }
        gui.setScore(score);
        gui.winPopUp(str);
        newRound();
        return true; //If someone won or it's a draw, returns true

    }

    /**
     * Handles logic for the players move.
     * @param i which button (square on the board) that the player pressed.
     */
    protected void playerMove(int i) {
        gui.setJb(i, game.getTurn()); //Updates the GUI with the players turn
        game.setBoard(i);       //Updates the board with the players turn.
        game.changeTurn();      //Changes the current turn to Ai
        if (!checkResult()) {    //Checks if the game is over

            aiMove();               //Prompts the Ai to make its turn

        }
    }


    /**
     * Updates GUI with ai move. Handles some logic for ai move as well
     */
    private void aiMove() {

        double[] move;

        //If its the Ais first turn, calls on the aiRandomMove method to randomise it
        if (turn < maxRandom) {
            move = game.aiRandomMove();
            turn++;
        }
        //Calls method which calculates which is the best move to make
        else {
            move = ai.bestMove();
        }

        //Transforms the Ais move to an int which is then sent to the gui
        if (move[0] == 0) {
            if (move[1] == 0) {
                gui.setJb(1, game.getTurn());
            } else if (move[1] == 1) {
                gui.setJb(2, game.getTurn());
            } else if (move[1] == 2) {
                gui.setJb(3, game.getTurn());
            }
        } else if (move[0] == 1) {
            if (move[1] == 0) {
                gui.setJb(4, game.getTurn());
            } else if (move[1] == 1) {
                gui.setJb(5, game.getTurn());
            } else if (move[1] == 2) {
                gui.setJb(6, game.getTurn());
            }
        } else if (move[0] == 2) {
            if (move[1] == 0) {
                gui.setJb(7, game.getTurn());
            } else if (move[1] == 1) {
                gui.setJb(8, game.getTurn());
            } else if (move[1] == 2) {
                gui.setJb(9, game.getTurn());
            }

        }

        if (!checkResult()) { //Checks if game is over
            game.changeTurn();      //Changes turn to player

        }
    }


    /**
     * Method for starting the game from Controller in main game.
     * @throws RuntimeException
     *
     * @author Edvin
     */
    @Override
    public void run() throws RuntimeException {
        launch();
    }
}


