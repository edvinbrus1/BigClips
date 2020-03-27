package treIRad.TreIRad;

import javax.swing.*;

public class Control {

    private GUI gui;
    private Game game;

        public static void main(String[]args){
            new Control();
        }

        public Control(){

            gui= new GUI(this);
            game = new Game();
        }



        public void logic(JButton jb, int i){
            gui.deactivateButton(jb);
            game.changeTurn();
            int turn = game.getTurn();
            game.setBoard(i);
            boolean win = game.checkWin();
            gui.winPopUp(win);
            gui.setButtonImage(jb, game.setImage(turn));



        }


}
