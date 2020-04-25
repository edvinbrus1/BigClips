package treIRad;

public class Control {

    private GUI gui;
    private Game game;
    private Ai ai;
    private int turn;


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
            if (game.getTurn()==Turn.Ai){  //2 is the value for Ai turn
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

        public void playerMove(int i){
            gui.setJb(i, game.getTurn());
            game.setBoard(i);
            game.changeTurn();
            checkResult();
            aiMove();
        }


        //Updates the GUI with the Ai's move
        public void aiMove() {

            double[] move;

            //If its the Ais first turn, calls on the firstmove method to randomise it
            if (turn<1){
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
                if (move[1] == 1) {
                    gui.setJb(2,game.getTurn());
                }
                if (move[1] == 2) {
                    gui.setJb(3,game.getTurn());
                }
            }
            if (move[0] == 1) {
                if (move[1] == 0) {
                    gui.setJb(4,game.getTurn());
                }
                if (move[1] == 1) {
                    gui.setJb(5,game.getTurn());
                }
                if (move[1] == 2) {
                    gui.setJb(6,game.getTurn());
                }
            }
            if (move[0] == 2) {
                if (move[1] == 0) {
                    gui.setJb(7,game.getTurn());
                }
                if (move[1] == 1) {
                    gui.setJb(8,game.getTurn());
                }
                if (move[1] == 2) {
                    gui.setJb(9,game.getTurn());
                }

            }

            //Calls on methods to check if the game is over and to change current turn
            checkResult();
            game.changeTurn();
        }


}


