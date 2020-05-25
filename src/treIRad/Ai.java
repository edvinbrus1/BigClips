package treIRad;

public class Ai {

    //The below algorithms are taken from https://editor.p5js.org/codingtrain/sketches/0zyUhZdJD
    //Adjustements have been made in both methods to ensure that they work properly with this version
    //of tic tac toe / tre i rad.
    //Adjustements by Amir


    private Game game;
    private int[][] board;

    protected Ai(Game game){
        this.game=game;
    }

    protected double[] bestMove(){

        double bestScore = Double.NEGATIVE_INFINITY;
        double move[]= new double[2];
        int row=0, col=0;
        board=game.getBoard();

        for(int i=0; i<3 ;i++) {
            for (int j = 0; j < 3; j++) {

                //Checks if square is empty
                if (board[i][j] == 0) {
                    //Sets square to Ai's value
                    board[i][j] = 10;
                    double score = minimax(board, -1, false); //-1 seemingly makes it harder
                    board[i][j] = 0;
                    if (score > bestScore) {
                        bestScore = score;
                        row=i;
                        col=j;
                        move[0]=i;
                        move[1]=j;
                    }
                }
            }
        }
        game.setBoard(row,col,10);
        return move;
    }


    private double minimax(int[][]board, int depth, Boolean isMaximizing){

        Winner result = game.checkWin();

        if ((result==Winner.Player)){
            return -10;
        }

        if ((result==Winner.Ai)){
            return 10;
        }

        if (game.checkDraw()==Winner.Draw){
            return 0;
        }

        if (isMaximizing){
            double bestScore= Double.NEGATIVE_INFINITY;
            for (int i=0;i<3;i++){
                for (int j=0;j<3;j++){
                    if (board[i][j]==0){
                        board[i][j]=10;
                        double score = minimax(board, depth+1, false );
                        board[i][j]=0;
                        bestScore= Math.max(score,bestScore);
                    }
                }
            }
            return bestScore;
        }else{
            double bestScore = Double.POSITIVE_INFINITY;
            for (int i=0; i<3; i++){
                for (int j=0; j<3; j++){
                    if (board[i][j]==0){

                        board[i][j]=1;
                        double score = minimax(board, depth+1, true);
                        board[i][j] = 0;
                        bestScore= Math.min(score,bestScore);
                    }
                }
            }
            return bestScore;
        }
    }



}
