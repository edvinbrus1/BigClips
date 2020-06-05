package treIRad;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * Model class for tre i rad
 *
 * @author Amir
 */

public class Game {

    private Random rand;
    private int[][] board = {{0,0,0},{0,0,0},{0,0,0}};
    private Turn currentTurn;

    /**
     * Constructor that initializes the random class variable.
     */
    protected Game(){
        rand = new Random();
    }


    /**
     * Method that randomises which player starts
     */
    protected void randomiseTurn(){
        int turn = rand.nextInt(2)+1;       // 1 is player, 2 is Ai
        if (turn==1){
            currentTurn=Turn.Player;
        }
        else
            currentTurn=Turn.Ai;
    }



    /**
     * Method for changing whos turn it is currently
     */
    protected void changeTurn(){
        if (currentTurn==Turn.Ai){       //Change current turn from Ai to player
            currentTurn=Turn.Player;
        }
        else if (currentTurn==Turn.Player){     //Changes current turn from player to AI
            currentTurn=Turn.Ai;
        }
    }



    /**
     * Method that returns whos turn it currently is
     * @return enum that says whos turn it currently is
     */
    protected Turn getTurn(){
        return currentTurn;
    }


    /**
     * Method that searches the board to see if anyone got three in a row vertically/horizontally/diagonally.
     * @return tells if play or ai won, or no one.
     */
    protected Winner checkWin(){

    int a=0, b=0;

        for (int i =0;i<3;i++){
            a=0;
            b=0;
            for (int j=0;j<3;j++){
                if (board[i][j]==1){
                    a+=board[i][j];
                }
                if (board[i][j]==10){
                    b+=board[i][j];
                }
                if (a==3) {
                 return Winner.Player;
             }
                if (b==30){
                 return Winner.Ai;
             }
             }
        }

        for (int j=0;j<3;j++){
            a=0;
            b=0;
            for (int i=0;i<3;i++){
                if (board[i][j]==1){
                    a+=board[i][j];
                }
                if (board[i][j]==10){
                    b+=board[i][j];
                }
                if (a==3) {
                    return Winner.Player;
                }
                if (b==30){
                    return Winner.Ai;
                }
            }
        }

    if (board[0][0]+board[1][1]+board[2][2]==3){
        return Winner.Player;
    }
    if(board[0][0]+board[1][1]+board[2][2]==30){
        return Winner.Ai;
    }
    if (board[2][0]+board[1][1]+board[0][2]==3){
        return Winner.Player;
    }
    if (board[2][0]+board[1][1]+board[0][2]==30){
        return Winner.Ai;
    }

    return Winner.None;
    }



    /**
     * Changes the value in the board array for players move.
     * @param position represents the square that the player pressed to place his marker in.
     */
    protected void setBoard(int position){

        int value=1;
        //If its AI's turn
        //Can probably be deleted.
        if (getTurn()==Turn.Ai){
            value=10;
        }

        if (position==1){
            board[0][0]=value;
        }
        else if (position==2){
            board[0][1]=value;
        }
        else if (position==3){
            board[0][2]=value;
        }
        else if (position==4){
            board[1][0]=value;
        }
        else if (position==5){
            board[1][1]=value;
        }
        else if (position==6){
            board[1][2]=value;
        }
        else if (position==7){
            board[2][0]=value;
        }
       else if (position==8){
            board[2][1]=value;
        }
        else if (position==9){
            board[2][2]=value;
        }
    }

    /**
     *
     * @param row which row the ai wants to place his marker in
     * @param col  which column the ai wants to place his marker in
     * @param value the value that represents the AI's markers
     */
    protected void setBoard(int row, int col, int value){
        board[row][col]=value;
    }


    //Randomises AI's turn

    /**
     * Method for making the ai place his marker randomly on an empty square.
     * @return an array with row and column position for ai move.
     */
    protected double[] aiRandomMove(){
        if (currentTurn==Turn.Player){
            return null;
        }
        double[] move = new double[2];
        int row, col;
        boolean bool=true;
            while (bool) {
                row = rand.nextInt(3);
                col = rand.nextInt(3);

                if(board[row][col]==0){
                    move[0]=row;
                    move[1]=col;
                    board[row][col]=10;
                    bool=false;
                }
            }
        return move;
    }

    /**
     * Method for checking if the game has ended in a draw.
     * @return Draw if its a draw, None if game is not over.
     */
    protected Winner checkDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                //Checks for empty squares. If at least one is empty then the game can't be over yet
                if ( board[i][j]==0){
                    return Winner.None;
                }
            }
        }
        //If there is no winner and according to above no empty squares, then the game must be a draw.
        if (checkWin()==Winner.None){
            return Winner.Draw;
        }
        return Winner.None;
    }


    /**
     * Method for getting the board array
     * @return the board array
     */
    protected int[][] getBoard(){
        return board;
    }


}


