package treIRad;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Game {

    private Random rand;
    private int[][] board = {{0,0,0},{0,0,0},{0,0,0}};
    private Turn currentTurn;

    public Game(){
        rand = new Random();

    }

    //Randomises which player starts first
    public void randomiseTurn(){
        int turn = rand.nextInt(2)+1;       // 1 is player, 2 is Ai
        if (turn==1){
            currentTurn=Turn.Player;
        }
        else
            currentTurn=Turn.Ai;
    }



    //Changes turns
    public void changeTurn(){
        if (currentTurn==Turn.Ai){       //Change current turn from Ai to player
            currentTurn=Turn.Player;
        }
        else if (currentTurn==Turn.Player){     //Changes current turn from player to AI
            currentTurn=Turn.Ai;
        }
    }

    //Returns current turn
    public Turn getTurn(){
        return currentTurn;
    }




    //Searches through the board to see if anyone has won by having 3 pieces in a row either vertically, horizontally or diagonally.
    public Winner checkWin(){

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

    //Ändrar värden i board array beroende på vems tur det är
    public void setBoard(int position){

            int value=1;
        //If its AI's turn
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

    public void setBoard(int row, int col, int value){
        board[row][col]=value;
    }


    public double[] aiFirstMove(){
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

    //Checks if game is a draw
    public Winner checkDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if ( board[i][j]==0){
                    return Winner.None;
                }
            }
        }
        if (checkWin()==Winner.None){
            return Winner.Draw;
        }
        return Winner.None;
    }

    public int[][] getBoard(){
        return board;
    }


}


