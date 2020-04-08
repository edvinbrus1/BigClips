package treIRad.TreIRad;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

import static java.lang.StrictMath.max;
import static java.lang.StrictMath.min;

public class Game {
    int turn;
    Random rand;
    ImageIcon playerIcon;
    ImageIcon aiIcon;
    int[][] board = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};


<<<<<<< Updated upstream



    public Game(){
        rand = new Random();
        turn = rand.nextInt(2)+1;
=======
    public Game() {
        rand = new Random();
       // turn = rand.nextInt(2) + 1;       // 1 is player, 2 is Ai
        turn=1;
>>>>>>> Stashed changes
        setIcons();

    }

    //Borde kanske flyttas till gui??
    public void setIcons() {
        try {

            Image playerImg = ImageIO.read(new File("images/red.jpg"));
            Image aiImg = ImageIO.read(new File("images/yellow.jpg"));


            playerIcon = new ImageIcon(playerImg);
            aiIcon = new ImageIcon(aiImg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //Byter vems tur det är
<<<<<<< Updated upstream
    public void changeTurn(){
        if (turn==2){
            turn=1;
        }
        else if (turn==1){
            turn=2;
=======
    public void changeTurn() {
        if (turn == 2) {       //Change current turn from Ai to player
            turn = 1;
        } else if (turn == 1) {     //Changes current turn from player to AI
            turn = 2;
>>>>>>> Stashed changes
        }

    }


    //Returns current turn
    public int getTurn() {
        return turn;
    }



    //Returnerar aktuell spelares icon
    public ImageIcon setImage(int i) {
        if (i == 1) {
            return playerIcon;
        }
        return aiIcon;
    }

<<<<<<< Updated upstream

    //Söker genom board arrayen för att se om en spelare har 3 markörer i rad
    public boolean checkWin(){

    Boolean win = false;
    int a=0, b=0;

        for (int i =0;i<board.length;i++){

            a=0;
            b=0;
            for (int j=0;j<board[i].length;j++){
                if (board[i][j]==1){
                    a+=board[i][j];
=======
    //Söker genom board arrayen för att se om en spelare har 3 markörer i rad
    public Winner checkWin() {

        int a = 0, b = 0;

        for (int i = 0; i < 3; i++) {

            a = 0;
            b = 0;
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 1) {
                    a += board[i][j];
>>>>>>> Stashed changes
                }
                if (board[i][j] == 10) {
                    b += board[i][j];
                }
<<<<<<< Updated upstream
             if (a==3 || b==30){
                 win=true;

             }
         }}

        for (int j=0;j<3;j++){
            a=0;
            b=0;
            for (int i=0;i<board.length;i++){
                if (board[i][j]==1){
                    a+=board[i][j];
=======
                if (a == 3) {
                    return Winner.Player;
>>>>>>> Stashed changes
                }
                if (b == 30) {
                    return Winner.Ai;
                }
<<<<<<< Updated upstream
                if (a==3 || b==30){
                    win=true;

=======
            }
        }

        for (int j = 0; j < 3; j++) {
            a = 0;
            b = 0;
            for (int i = 0; i < 3; i++) {
                if (board[i][j] == 1) {
                    a += board[i][j];
                }
                if (board[i][j] == 10) {
                    b += board[i][j];
                }
                if (a == 3) {
                    return Winner.Player;
                }
                if (b == 30) {
                    return Winner.Ai;
>>>>>>> Stashed changes
                }
            }
        }

<<<<<<< Updated upstream
    if (board[0][0]+board[1][1]+board[2][2]==3||board[0][0]+board[1][1]+board[2][2]==30){
        win=true;
    }

    else if (board[2][0]+board[1][1]+board[0][2]==3||board[2][0]+board[1][1]+board[0][2]==30){
            win=true;
        }

    return win;

=======
        if (board[0][0] + board[1][1] + board[2][2] == 3) {
            return Winner.Player;
        }
        if (board[0][0] + board[1][1] + board[2][2] == 30) {
            return Winner.Ai;
        }
        if (board[2][0] + board[1][1] + board[0][2] == 3) {
            return Winner.Player;
        }
        if (board[2][0] + board[1][1] + board[0][2] == 30) {
            return Winner.Ai;
        }

        return Winner.None;
>>>>>>> Stashed changes
    }

    //Ändrar värden i board array beroende på vems tur det är
    public void setBoard(int position) {
        int value = 1;

        if (getTurn() == 2) {
            value = 10;
        }


        if (position == 1) {
            board[0][0] = value;
        } else if (position == 2) {
            board[0][1] = value;
        } else if (position == 3) {
            board[0][2] = value;
        } else if (position == 4) {
            board[1][0] = value;
        } else if (position == 5) {
            board[1][1] = value;
        } else if (position == 6) {
            board[1][2] = value;
        } else if (position == 7) {
            board[2][0] = value;
        } else if (position == 8) {
            board[2][1] = value;
        } else if (position == 9) {
            board[2][2] = value;
        }

    }

    public int[] aiMove(){
        int[] move = bestMove();
        return move;

    }
<<<<<<< Updated upstream

=======
    public void aiFirstMove() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

            }
        }
    }



    public int[] bestMove() {

        int a,b;
        int[] move = new int[2];
        double bestScore = Double.NEGATIVE_INFINITY;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                //Checks if square is empty
                if (board[i][j] == 0) {
                    //Sets square to Ai's value
                    board[i][j] = 10;
                    double score = minimax(board, 0, false);
                    board[i][j] = 0;
                    if (score > bestScore) {
                        bestScore = score;

                        move[0]=i;
                        move[1]=j;
                        a=i;        //move = {i,j};
                        b=j;
                    }
                }
            }
            //behöver åkalla funktion som placerar på brädan åt Ai
        }
        System.out.println(move[0] +" " + move[1]);
        return move;
    }


    public double minimax(int[][] board, int depth, Boolean isMaximizing) {

        if (!(checkWin() == Winner.None)) {

        }
>>>>>>> Stashed changes

        if (isMaximizing) {
            double bestScore = Double.NEGATIVE_INFINITY;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == 0) {
                        board[i][j] = 10;
                        double score = minimax(board, depth + 1, false);
                        board[i][j] = 0;
                        bestScore = max(score, bestScore);
                    }
                }
            }
            return bestScore;
        } else {
            double bestScore = Double.POSITIVE_INFINITY;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == 0) {
                        board[i][j] = 1;
                        double score = minimax(board, depth + 1, true);
                            board[i][j] = 0;
                            bestScore = min(score, bestScore);
                    }
                }
            }
            return bestScore;
        }
    }
}
