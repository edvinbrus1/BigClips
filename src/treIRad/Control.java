package treIRad;

import java.util.Random;

import static javafx.application.Application.launch;

//Created by Amir

public class Control implements Runnable{

    private GUI gui;
    private Game game;
    private Ai ai;
    private int turn;
    private Random rand;
    private int maxRandom;


    public static void main(String[]args){
            new Control();
        }

        public Control(){
            rand = new Random();
            gui= new GUI(this);
            resetGame();

        }

        //Resets the board and starts the game over
        public void resetGame(){
            turn=0;
            game = new Game();
            ai = new Ai(game);
            gui.resetGui();
            firstTurn();
        }

        public void randomize(int bound){
            maxRandom = rand.nextInt(bound);
        }

        //Handles logic for first turn in the game. Randomises who starts. If Ai then calls Ai method for making a turn
        public void firstTurn(){
            game.randomiseTurn();
            if (game.getTurn()==Turn.Ai){
                randomize(3);
                aiMove();
            }
            else {
                randomize(2);
            }
        }

        //Checks if someone has won or if the game is a draw
        public boolean checkResult(){
            String str = "";

            if (game.checkWin()==Winner.None) {
                if (game.checkDraw() == Winner.Draw) {
                    str = "An eye for an eye makes the whole world blind...";
                } else return false;  //If no one has won yet, the method is stopped here
            }
            else if (game.checkWin()==Winner.Ai){
                str = "Retreat! Live and fight another day...";
            }
            else if (game.checkWin()==Winner.Player){
                str = "This battle is won! But what about the war...";
            }
            gui.winPopUp(str);
            resetGame();
            return true;

        }

        //Handles the players move.
        public void playerMove(int i){
            gui.setJb(i, game.getTurn()); //Updates the GUI with the players turn
            game.setBoard(i);       //Updates the board with the players turn.
            game.changeTurn();      //Changes the current turn to Ai
            if (!checkResult()){    //Checks if the game is over

                aiMove();               //Prompts the Ai to make its turn

            }
        }


        //Updates the GUI with the Ai's move
        public void aiMove() {

            double[] move ;

            //If its the Ais first turn, calls on the firstmove method to randomise it
            if (turn < maxRandom){
                move=game.aiFirstMove();
                turn++;
            }
            //Calls method which calculates which is the best move to make
            else {
                move = ai.bestMove();
            }

            //Transforms the Ais move to an int which is then sent to the gui
            if (move[0] == 0) {
                if (move[1] == 0) {
                    gui.setJb(1,game.getTurn());
                }
                else if (move[1] == 1) {
                    gui.setJb(2,game.getTurn());
                }
                else if (move[1] == 2) {
                    gui.setJb(3,game.getTurn());
                }
            }
            else if (move[0] == 1) {
                if (move[1] == 0) {
                    gui.setJb(4,game.getTurn());
                }
                else if (move[1] == 1) {
                    gui.setJb(5,game.getTurn());
                }
                else if (move[1] == 2) {
                    gui.setJb(6,game.getTurn());
                }
            }
            else if (move[0] == 2) {
                if (move[1] == 0) {
                    gui.setJb(7,game.getTurn());
                }
                else if (move[1] == 1) {
                    gui.setJb(8,game.getTurn());
                }
                else if (move[1] == 2) {
                    gui.setJb(9,game.getTurn());
                }

            }

            if (!checkResult()){ //Checks if game is over
                game.changeTurn();      //Changes turn to player

            }
        }


    @Override
    public void run() {
        launch();
    }
}


