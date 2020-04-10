package spaceInv;
/*
import java.util.ArrayList;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class SpaceInv2 extends Application{

    AnimationTimer timer;
    Pane root = new Pane();
    List<ImageView> monsters = new ArrayList<ImageView>();
    List<Circle> iShot = new ArrayList<Circle>();
    List<Circle> pShot = new ArrayList<Circle>();
    ImageView player;
    Circle dotR = new Circle();
    boolean toRight = true;
    Text lives;
    Text points;
    int numPoints = 0;
    int numLives = 3;

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
        root.getChildren().addAll(lives, points);

        dotR.setLayoutX(0);

        //Creating player
        player = player();
        root.getChildren().add(player);

        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                gameUpdate();
            }
        };
        timer.start();


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

    //Method for selecting the image of the players ship
    public ImageView player(){
        ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("/resources/ship.png")));
        imageView.setLayoutX(225);
        imageView.setLayoutY(650);
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);
        return imageView;
    }

    //Method for controlling the game updates
    public void gameUpdate(){
        playerFiringUpdate();
    }

    //Method for creating the projectiles
    public Circle projectile(double x, double y) {
        Circle c = new Circle();
        c.setFill(Color.AQUAMARINE);
        c.setLayoutX(x);
        c.setLayoutY(y);c.setRadius(3);
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
}


 */