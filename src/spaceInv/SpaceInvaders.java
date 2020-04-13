package spaceInv;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

//Created by Edvin
public class SpaceInvaders extends Application{

    AnimationTimer timer;
    Pane root = new Pane();
    List<ImageView> invaders = new ArrayList<ImageView>();
    List<Circle> iShot = new ArrayList<Circle>();
    List<Circle> pShot = new ArrayList<Circle>();
    List<Shield> shields = new ArrayList<Shield>();
    ImageView player;
    Circle dotR = new Circle();
    boolean toRight = true;
    Text lives;
    Text points;
    int numPoints = 0;
    int numLives = 3;
    private int SPACE = 40;
    private int rectangleSize = 8;
    private Group shieldGroup = new Group();
    private Group secondShield = new Group();
    private Group thirdShield = new Group();
    private Group fourthShield = new Group();

    //Main method for starting the game
    public static void main(String[] args){
        launch();
    }

    //JavaFX method for creating the stage
    @Override
    public void start(Stage stage) throws Exception {
        lives = new Text("Lives: 3");
        lives.setLayoutX(20);
        lives.setLayoutY(30);
        lives.setFill(Color.WHITE);

        points = new Text("Points: 0");
        points.setLayoutX(350);
        points.setLayoutY(30);
        points.setFill(Color.WHITE);
        root.getChildren().addAll(lives, points, shieldGroup, secondShield,
                thirdShield);
        dotR.setLayoutX(0);

        //Creating player
        player = player();
        root.getChildren().add(player);

        //Creating invaders
        addInvaders();
        

        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                gameUpdate();
            }
        };
        timer.start();

        //Timeline for the invaders firing
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), event ->{
            if(!invaders.isEmpty()){
                invadersFiring();
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        //Creating the map for the game
        Scene scene = new Scene(root, 500,700);
        scene.setFill(Color.BLACK);

        //methods for moving the player
        scene.setOnKeyPressed(e ->{
            if(e.getCode() == KeyCode.RIGHT){
                player.setLayoutX(player.getLayoutX() + 5);
            }
            if(e.getCode() == KeyCode.LEFT){
                player.setLayoutX(player.getLayoutX() - 5);
            }
            if(e.getCode() == KeyCode.SPACE){
                playerFiring(player.getLayoutX());
            }
        });
        stage.setScene(scene);
        stage.setTitle("Space Invaders Test");
        stage.show();
    }

    //Method for controlling the game updates
    public void gameUpdate(){
        invaderFiringUpdate();
        playerFiringUpdate();
        playerKilled();
        invaderKilled();
        invadersMovement();
        gameWon();
        gameLost();
    }

    //One of the methods for controlling when the monsters will fire
    public int rand(int min, int max){
        return (int)(Math.random() * max + min);
    }

    //Method for the controlling the invaders projectiles
    public void invadersFiring(){
        int getInvaderFiringIndex = rand(0, invaders.size() - 1);
        iShot.add(projectile(invaders.get(getInvaderFiringIndex).getLayoutX() + 25, invaders.get(getInvaderFiringIndex).getLayoutY() + 25));
        root.getChildren().add(iShot.get(iShot.size() -1));
    }

    //Method for adding invaders
    public void addInvaders(){
        for(int i = 0, w = 40; i < 6; i++, w += 70){
            invaders.add(invader(w, 50));
            root.getChildren().add((Node)invaders.get(i));
        }
        for (int i = 0, w = 40; i < 6; i++, w += 70){
            invaders.add(invader(w, 120));
            root.getChildren().add((Node)invaders.get(i + 6));
        }
        for(int i = 0, w = 40; i < 6; i++, w+= 70){
            invaders.add(invader(w, 190));
            root.getChildren().add((Node)invaders.get(i + 12));
        }
    }

    //Method for controlling the movement of invaders
    public void invadersMovement(){

        double velocity;

        if(toRight)
            velocity = 0.6;
        else
            velocity = -0.6;

        if(dotR.getLayoutX() >= 40){
            toRight = false;
            for(int i = 0; i < invaders.size(); i++){
                invaders.get(i).setLayoutY(invaders.get(i).getLayoutY() + 8);
            }
        }

        if(dotR.getLayoutX() <= -20){
            toRight = true;
            for(int i = 0; i < invaders.size(); i++){
                invaders.get(i).setLayoutY(invaders.get(i).getLayoutY() + 8);
            }
        }

        for(int i = 0; i < invaders.size(); i++){
            invaders.get(i).setLayoutX(invaders.get(i).getLayoutX() + velocity);
        }

        dotR.setLayoutX(dotR.getLayoutX() + velocity);
    }

    //Method for selecting the image of the players ship
    public ImageView player(){
        ImageView i = new ImageView(new Image(getClass().getResourceAsStream("/resources/ship.png")));
        i.setLayoutX(225);
        i.setLayoutY(650);
        i.setFitHeight(50);
        i.setFitWidth(50);
        return i;
    }

    
    //Method for selecting the image to be used for the invaders
    public ImageView invader(double x, double y){
        ImageView i = new ImageView(new Image(getClass().getResourceAsStream("/resources/inv.png")));
        i.setLayoutX(x);
        i.setLayoutY(y);
        i.setFitHeight(50);
        i.setFitWidth(50);
        return i;
    }

    //Method for creating the projectiles
    public Circle
    projectile(double x, double y) {
        Circle c = new Circle();
        c.setFill(Color.AQUAMARINE);
        c.setLayoutX(x);
        c.setLayoutY(y);
        c.setRadius(3);
        return c;
    }

    //Method for firing projectiles
    public void playerFiring(double x){
        pShot.add(projectile((x + 25), 650));
        root.getChildren().add(pShot.get(pShot.size() - 1));
    }

    //Method for updating the game when the player fires
    private void playerFiringUpdate(){
        if(!pShot.isEmpty()){
            for(int i = 0; i < pShot.size(); i++){
                pShot.get(i).setLayoutY(pShot.get(i).getLayoutY() - 3);
                if(pShot.get(i).getLayoutY()<=0){
                    root.getChildren().remove(pShot.get(i));
                    pShot.remove(i);
                }
            }
        }
    }

    //Method for updating the game when an invader fires
    private void invaderFiringUpdate(){
        if(!iShot.isEmpty()){
            for(int i = 0; i < iShot.size(); i++){
                iShot.get(i).setLayoutY(iShot.get(i).getLayoutY() + 3);
                if(iShot.get(i).getLayoutY() <= 0){
                    root.getChildren().remove(iShot.get(i));
                    iShot.remove(i);
                }
            }
        }
    }

    //Method for what happens when a invader is killed
    private void invaderKilled(){
        for(int i = 0; i < pShot.size(); i++){
            for(int j = 0; j < invaders.size(); j++){
                if(((pShot.get(i).getLayoutX() > invaders.get(j).getLayoutX())
                &&((pShot.get(i).getLayoutX() < invaders.get(j).getLayoutX() + 50))
                &&((pShot.get(i).getLayoutY() > invaders.get(j).getLayoutY())
                &&((pShot.get(i).getLayoutY() < invaders.get(j).getLayoutY() + 50))))){
                    root.getChildren().remove(invaders.get(j));
                    invaders.remove(j);
                    root.getChildren().remove(pShot.get(i));
                    pShot.remove(i);
                    numPoints += 100;
                    points.setText("Points: " + numPoints);
                }
            }
        }
    }

    //Method for what happens when the player is killed
    private void playerKilled(){
        for(int i = 0; i < iShot.size(); i++){
            if(((iShot.get(i).getLayoutX() > player.getLayoutX())
            &&((iShot.get(i).getLayoutX() < player.getLayoutX() + 50))
            &&((iShot.get(i).getLayoutY() > player.getLayoutY())
            &&((iShot.get(i).getLayoutY() < player.getLayoutY() + 50))))){
                player.setLayoutX(225);
                numLives -= 1;
                lives.setText("Lives: " + numLives);
            }
        }
    }

    //Method for what happens when a player wins the game
    public void gameWon(){
        if(invaders.isEmpty()){
            Text text = new Text();
            //TODO find a good font to use here
            text.setX(180);
            text.setY(300);
            text.setFill(Color.YELLOWGREEN);
            text.setStrokeWidth(3);
            text.setStroke(Color.GOLD);
            text.setText("WIN");
            root.getChildren().add(text);
            timer.stop();
        }
    }

    //Method for what happens when a game is lost
    public void gameLost(){
        if(numLives <= 0){
            Text text = new Text();
            //TODO find a good font to use here
            text.setX(180);
            text.setY(300);
            text.setFill(Color.RED);
            text.setStrokeWidth(3);
            text.setStroke(Color.MAROON);
            text.setText("LOST");
            root.getChildren().add(text);
            timer.stop();
        }
    }
}
