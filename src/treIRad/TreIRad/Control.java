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


<<<<<<< Updated upstream
        public void logic(JButton jb, int i){
            gui.deactivateButton(jb);
            game.changeTurn();
            int turn = game.getTurn();
            game.setBoard(i);
            boolean win = game.checkWin();
            gui.winPopUp(win);
            gui.setButtonImage(jb, game.setImage(turn));
            
        }


=======
        public void playerMove(JButton jb, int i){
        //    if (game.getTurn()==1) {

                gui.deactivateButton(jb);
                game.changeTurn();
                int turn = game.getTurn();
                game.setBoard(i);
                gui.winPopUp(game.checkWin());
                gui.setButtonImage(jb, game.setImage(turn));
                aiMove();
        }


        public void aiMove(){
           int[] move = game.aiMove();
           int position=-1;
            if (move[0]==0 && move[1]==0){
                position=1;
            }
            else if (move[0]==0 && move[1]==1){
                position=2;
            }
            else if (move[0]==0 && move[1]==2){
                position=3;
            }
            else if (move[0]==1 && move[1]==0){
                position=4;
            }
            else if (move[0]==1 && move[1]==1){
                position=5;
            }
            else if (move[0]==1 && move[1]==2){
                position=6;
            }
            else if (move[0]==2 && move[1]==0){
                position=7;
            }
            else if (move[0]==2 && move[1]==1){
                position=8;
            }
            else if (move[0]==2 && move[1]==2){
                position=9;
            }

        }
>>>>>>> Stashed changes
}
