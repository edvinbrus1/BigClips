package treIRad;

import javax.swing.*;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import static javafx.application.Application.launch;

//Created by Amir

public class Control implements Runnable {

    private final GUI gui;
    private Game game;
    private Ai ai;
    private int turn;
    private final Random rand;
    private int maxRandom;
    private int score;
    private int rounds;


    public Control() {
        score = 0;
        rand = new Random();
        gui = new GUI(this);
        rounds = 0;
        resetGame();

    }


    public static void main(String[] args) {
        new Control();
    }

    //Resets the board and starts the game over, if less than 10 rounds have been played
    private void resetGame() {
        if (rounds < 5) {
            turn = 0;
            game = new Game();
            ai = new Ai(game);
            gui.resetGui();
            firstTurn();
            rounds++;
        } else {
            printScore();
            int input = JOptionPane.showOptionDialog(null, "game over", "Game complete", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);  //test syfte

            //Method for closing the game - Edvin
            if (input == JOptionPane.OK_OPTION) {
                closeGame();
            }
        }
    }

    //Method for closing the game - Edvin
    private void closeGame() {
        gui.dispose();
    }

    private void randomize(int bound) {
        maxRandom = rand.nextInt(bound);
    }

    //Handles logic for first turn in the game. Randomises who starts. If Ai then calls Ai method for making a turn
    private void firstTurn() {

        game.randomiseTurn();
        if (game.getTurn() == Turn.Ai) {
            randomize(3);
            aiMove();
        } else {
            randomize(2);
        }
    }

    //adds to the score
    private void addScore(int score) {
        this.score += score;
    }

    private void printScore() {
        try {
            //"false" makes sure the file is overwritten if program if run multiple times
            DataOutputStream out = new DataOutputStream(new FileOutputStream("src/resources/TreScore.txt", false));
            out.writeInt(score);
            out.flush();
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Checks if someone has won or if the game is a draw
    private boolean checkResult() {
        String str = "";

        if (game.checkWin() == Winner.None) {     //Checks if there is no winner
            if (game.checkDraw() == Winner.Draw) {      //Checks if game's a draw
                str = "5 points";
                addScore(5);  //If its a draw, adds points to total score
            } else return false;  //If no one has won yet, the method is stopped here
        } else if (game.checkWin() == Winner.Ai) {       //Checks if Ai won
            str = "-1 points";
            addScore(-1);    //If Ai won, points are reduced
        } else if (game.checkWin() == Winner.Player) {       //Checks if player has won
            str = "10 points";
            addScore(10); //Adds points if player won
        }
        gui.winPopUp(str);
        resetGame();
        return true; //If someone won or it's a draw, returns true

    }

    //Handles the players move.
    protected void playerMove(int i) {
        gui.setJb(i, game.getTurn()); //Updates the GUI with the players turn
        game.setBoard(i);       //Updates the board with the players turn.
        game.changeTurn();      //Changes the current turn to Ai
        if (!checkResult()) {    //Checks if the game is over

            aiMove();               //Prompts the Ai to make its turn

        }
    }


    //Updates the GUI with the Ai's move
    private void aiMove() {

        double[] move;

        //If its the Ais first turn, calls on the firstmove method to randomise it
        if (turn < maxRandom) {
            move = game.aiFirstMove();
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


    @Override
    public void run() {
        launch();
    }
}


