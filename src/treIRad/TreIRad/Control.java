package treIRad.TreIRad;

import javax.swing.*;

import static javafx.application.Application.launch;

public class Control implements Runnable{

    private GUI gui;
    private Game game;
    private Ai ai;
    int turn;


    public static void main(String[]args){
            new Control();
        }

        public Control(){

            game = new Game();
            ai = new Ai(game);
            gui= new GUI(this);
            turn=0;
            firstTurn();

        }

        //Handles logic for first turn in the game. Randomises who starts. If Ai then calls Ai method for making a turn
        public void firstTurn(){
            game.randomiseTurn();
            if (game.getTurn()==2){  //2 is the value for Ai turn
                aiMove();
            }
        }


        //Checks if someone has won or if the game is a draw
        public void checkResult(){
            String str = "";

            if (game.checkWin()==Winner.None) {
                if (game.checkDraw() == Winner.Draw) {
                    str = "An eye for an eye makes the whole world blind...";
                } else return;
            }
            else if (game.checkWin()==Winner.Ai){
                str = "Retreat! Live and fight another day...";
            }
            else if (game.checkWin()==Winner.Player){
                str = "This battle is won! But what about the war...";
            }
            gui.winPopUp(str);
            System.exit(0);

        }

        public void playerMove(JButton jb, int i){
                gui.deactivateButton(jb);
                game.setBoard(i);
                gui.setButtonImage(jb, game.setImage());
                game.changeTurn();
                checkResult();
                aiMove();
            }


        //Updates the GUI with the Ai's move
        public void aiMove() {

            double[] move;

            if (turn<1){
                move=game.aiFirstMove();
                turn++;
            }
            else {
                move = ai.bestMove();
            }

            if (move[0] == 0) {
                if (move[1] == 0) {
                    gui.setJb1(game.setImage());
                }
                if (move[1] == 1) {
                    gui.setJb2(game.setImage());
                }
                if (move[1] == 2) {
                    gui.setJb3(game.setImage());
                }
            }
            if (move[0] == 1) {
                if (move[1] == 0) {
                    gui.setJb4(game.setImage());
                }
                if (move[1] == 1) {
                    gui.setJb5(game.setImage());
                }
                if (move[1] == 2) {
                    gui.setJb6(game.setImage());
                }
            }
            if (move[0] == 2) {
                if (move[1] == 0) {
                    gui.setJb7(game.setImage());
                }
                if (move[1] == 1) {
                    gui.setJb8(game.setImage());
                }
                if (move[1] == 2) {
                    gui.setJb9(game.setImage());
                }

            }
          //  gui.winPopUp(game.checkWin());
            checkResult();
            game.changeTurn();
        }


    @Override
    public void run() {
        launch();
    }
}


