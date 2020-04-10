package treIRad.TreIRad;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

public class Game {
    int turn;
    Random rand;
    ImageIcon playerIcon;
    ImageIcon aiIcon;
    int[][] board = {{0,0,0},{0,0,0},{0,0,0}};




    public Game(){
        rand = new Random();
        turn=2;
        //  turn = rand.nextInt(2)+1;       // 1 is player, 2 is Ai
        setIcons();

    }

    //Borde kanske flyttas till gui??
    public void setIcons() {
        try {

            Image playerImg = ImageIO.read(new File("images/red.jpg"));
            Image aiImg = ImageIO.read(new File("images/yellow.jpg"));


            playerIcon = new ImageIcon(playerImg);
            aiIcon = new ImageIcon(aiImg);
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    //Byter vems tur det är
    public void changeTurn(){
        if (turn==2){       //Change current turn from Ai to player
            turn=1;
        }
        else if (turn==1){     //Changes current turn from player to AI
            turn=2;
        }

    }

    //Returns current turn
    public int getTurn(){
        return turn;
    }


    //Returnerar aktuell spelares icon
    public ImageIcon setImage(){
        if (getTurn()==1){
            return playerIcon;
        }
        return aiIcon;
    }

    public void playerWon(){
    //    checkWin(1,3);
    }
    public void aiWon(){
   //     checkWin(10,30);
    }

    //Söker genom board arrayen för att se om en spelare har 3 markörer i rad
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
         }}

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

        if (getTurn()==2){
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

    public void aiFirstMove(){
        for (int i=0;i<board.length;i++){
            for (int j=0;j<board[i].length;j++){

            }
        }
    }

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
    public double[] bestMove(){

        double bestScore = Double.NEGATIVE_INFINITY;
        double move[]= new double[2];
        int row=0, col=0;

        for(int i=0; i<3 ;i++) {
            for (int j = 0; j < 3; j++) {

                //Checks if square is empty
                if (board[i][j] == 0) {
                    //Sets square to Ai's value
                    board[i][j] = 10;
                    double score = minimax(board, 0, false);
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
        board[row][col]=10;
        changeTurn();
        return move;
    }



    public double minimax(int[][]board, int depth, Boolean isMaximizing){
        Winner result = checkWin();
        if ((result==Winner.Player)){
            System.out.println("player");
            return -10;
        }

        if ((result==Winner.Ai)){
            System.out.println("Ai");
            return 10;
        }

        if (checkDraw()==Winner.Draw){
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


