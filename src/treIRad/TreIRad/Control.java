package treIRad.TreIRad;

import javax.swing.*;

public class Control {

    private GUI gui;
    private Game game;

        public static void main(String[]args){
            new Control();
        }

        public Control(){

            game = new Game();
            gui= new GUI(this);


        }


        public void playerMove(JButton jb, int i){
        //    if (game.getTurn()==1) {

                gui.deactivateButton(jb);
                game.changeTurn();
                int turn = game.getTurn();
                game.setBoard(i);
                gui.winPopUp(game.checkWin());
                gui.setButtonImage(jb, game.setImage(turn));
            }

     //       aiMove();
 //       }

        public void aiMove(){
     //       game.aiMove();

        }

}
