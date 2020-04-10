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
                gui.deactivateButton(jb);
                game.changeTurn();
            //    int turn = game.getTurn();
                game.setBoard(i);
                gui.winPopUp(game.checkWin());
                gui.setButtonImage(jb, game.setImage());
                aiMove();
            }




        public void aiMove(){
           double move[] = game.bestMove();

           if (move[0]==0){
               if (move[1]==0){
                gui.setJb1(game.setImage());
               }
               if (move[1]==1){
                   gui.setJb2(game.setImage());

               }
               if (move[1]==2){
                   gui.setJb3(game.setImage());

               }
           }
            if (move[0]==1) {
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
                if (move[0]==2){
                    if (move[1]==0){
                        gui.setJb7(game.setImage());

                    }
                    if (move[1]==1){
                        gui.setJb8(game.setImage());

                    }
                    if (move[1]==2){
                        gui.setJb9(game.setImage());

                    }
                }
                gui.winPopUp(game.checkWin());

        }

}


