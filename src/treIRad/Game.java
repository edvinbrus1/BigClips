package treIRad;

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
        turn = rand.nextInt(2)+1;
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
        if (turn==2){
            turn=1;
        }
        else if (turn==1){
            turn=2;
        }

    }


    //Returns current turn
    public int getTurn(){
        return turn;
    }



    //Returnerar aktuell spelares icon
    public ImageIcon setImage(int i){
        if (i==1){
            return playerIcon;
        }
        return aiIcon;
    }


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
                }
                if (board[i][j]==10){
                    b+=board[i][j];
                }
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
                }
                if (board[i][j]==10){
                    b+=board[i][j];
                }
                if (a==3 || b==30){
                    win=true;

                }
            }
        }

    if (board[0][0]+board[1][1]+board[2][2]==3||board[0][0]+board[1][1]+board[2][2]==30){
        win=true;
    }

    else if (board[2][0]+board[1][1]+board[0][2]==3||board[2][0]+board[1][1]+board[0][2]==30){
            win=true;
        }

    return win;

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


}
