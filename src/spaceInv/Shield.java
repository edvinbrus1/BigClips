package spaceInv;

import javafx.scene.paint.Color;

//Class for testing the addition of a shield.
//Created by Edvin.
public class Shield {

    private int[][] shield;
    private double locationX;
    private double locationY;
    private Color color;

    //Declaring how the shield will look
    public Shield(){
        this.shield = new int[][]{
                {0, 0, 1, 1, 1, 1, 1, 1, 0, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 0, 0, 0, 0, 1, 1, 1},
                {1, 1, 0, 0, 0, 0, 0, 0, 1, 1}
        };
        this.color = Color.AQUAMARINE;
    }

    //Getter and setter methods for the shields
    public int[][] getShield(){
        return shield;
    }

    public Color getColor(){
        return color;
    }

    public double getLocationX(){
        return locationX;
    }

    public double getLocationY(){
        return locationY;
    }

    //Method for deleting the destroyed parts of the shield.
    public void deleteDestroyedParts(int row, int col){
        shield[row][col] = 0;
        if(row < shield.length - 1){
            shield[row + 1][col] = 0;
            if(col < shield[0].length - 1){
                shield[row][col + 1 ] = 0;
            }
            if(col > 0){
                shield[row][col - 1] = 0;
            }
        }
    }

    public void setLocationX(double locationX){
        this.locationX = locationX;
    }

    public void setLocationY(double locationY){
        this.locationY = locationY;
    }
}