package spaceInv;

import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Sprite {

    private double positionX;
    private double positionY;
    private double velocityX;
    private double velocityY;
    private double width;
    private double height;
    private Image image;

    public Sprite(){
        this.positionX = 0;
        this.positionY = 0;
        this.velocityX = 0;
        this.velocityY = 0;
    }

    public void setImage(Image image){
        this.image = image;
        this.width = image.getWidth();
        this.height = image.getHeight();
    }

    public void setImage(String filename){
        Image image = new Image(getClass().getResource(filename).toExternalForm());
        Image toReturn = new Image(filename, image.getWidth()/3, image.getHeight()/3, true, false);
        setImage(toReturn);
    }

    public void setImage(boolean gif, String filename){
        ImageView image = new ImageView(new Image(getClass().getResource(filename).toExternalForm()));
        Image toReturn = new Image(filename);
        setImage(toReturn);
    }

    public Image getImage(){
        return image;
    }

    public double getPositionX(){
        return positionX;
    }

    public double getPositionY(){
        return positionY;
    }

    public double getWidth(){
        return width;
    }

    public double getHeight(){
        return height;
    }

    public void setPosition(double x, double y){
        this.positionX = x;
        this.positionY = y;
    }

    public void setVelocity(double x, double y){
        this.velocityY = y;
        this.velocityX = x;
    }

    public void increaseVelocity(double x, double y){
        velocityX += x;
    }

    public void update(double time){
        positionY += velocityY * time;
        positionX += velocityX * time;
    }

    public void render(GraphicsContext gc){
        gc.drawImage(image, positionX, positionY);
    }

    public Rectangle2D getBoundary(){
        return new Rectangle2D(positionX, positionY, width, height);
    }

    public boolean intersects(Sprite s){
        return s.getBoundary().intersects(this.getBoundary());
    }
}
