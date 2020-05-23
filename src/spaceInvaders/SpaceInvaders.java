package spaceInvaders;

import bigClips.StartGame;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//Created by Edvin
public class SpaceInvaders extends Application implements Runnable {

    AnimationTimer timer;
    Pane root = new Pane();
    private List<ImageView> invaders = new ArrayList<ImageView>();
    private List<Circle> invaderProjectile = new ArrayList<Circle>();
    private List<Circle> playerProjectile = new ArrayList<Circle>();
    private ImageView player;
    private Circle projectile = new Circle();
    private boolean toRight = true;
    private Text lives;
    private Text points;
    private int playerPoints = 0;
    private int playerLives = 3;
    private static final double W = 600, H = 800;
    boolean goLeft, goRight;


    //Main method for starting the game
    public static void main(String[] args) {
        StartGame.launch(args);
    }

    //JavaFX method for creating the stage
    @Override
    public void start(Stage stage) throws Exception {
        lives = new Text("Lives: 3");
        lives.setFont(Font.loadFont(SpaceInvaders.class.getResource("/resources/JourneyPS3.ttf")
                .toExternalForm(), 20));
        lives.setLayoutX(20);
        lives.setLayoutY(30);
        lives.setFill(Color.WHITE);

        points = new Text("Points: 0");
        points.setFont(Font.font("JourneyPS3", 20));
        points.setLayoutX(450);
        points.setLayoutY(30);
        points.setFill(Color.WHITE);
        root.getChildren().addAll(lives, points);
        projectile.setLayoutX(0);

        //Creating player
        player = player();
        root.getChildren().add(player);

        //Creating invaders
        addInvaders();


        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                try {
                    gameUpdate();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        timer.start();

        //Timeline for the invaders firing
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.7), event -> {
            if (!invaders.isEmpty()) {
                invadersFiring();
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        //Creating the map for the game
        Scene scene = new Scene(root, 600, 800);
        Image img = new Image("https://3.bp.blogspot.com/-6xyJ92jKRjg/XD930UNULaI/AAAAAAAAAuM/jfz5tKgvmxYKj-YAtmhFM-s-JcGC1k7DQCKgBGAs/w1242-h2688-c/abstract-space-8-4k.jpg");
        scene.setFill(new ImagePattern(img));

        //Testing smoother movement
        moveShipTo(W / 2, 0);

        //methods for moving the player
        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case LEFT:
                    goLeft = true;
                    break;
                case RIGHT:
                    goRight = true;
                    break;
                case SPACE:
                    playerFiring(player.getLayoutX());
                    break;
            }
        });

        scene.setOnKeyReleased(e -> {
            switch (e.getCode()) {
                case LEFT:
                    goLeft = false;
                    break;
                case RIGHT:
                    goRight = false;
                    break;
            }
        });
        stage.setScene(scene);
        stage.setTitle("Space Invaders");
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                int dx = 0, dy = 0;

                if (goLeft) dx -= 10;
                if (goRight) dx += 10;

                moveShipBy(dx, dy);
            }
        };
        timer.start();
    }

    //Methods used for smoother movement of the ship
    private void moveShipBy(int dx, int dy) {
        if (dx == 0 && dy == 0) return;

        final double cx = player.getBoundsInLocal().getWidth() / 2;
        final double cy = player.getBoundsInLocal().getHeight() / 2;

        double x = cx + player.getLayoutX() + dx;
        double y = cy + player.getLayoutY() + dy;

        moveShipTo(x, y);
    }

    //Methods used for smoother movement of the ship
    private void moveShipTo(double x, double y) {
        final double cx = player.getBoundsInLocal().getWidth() / 2;
        final double cy = player.getBoundsInLocal().getHeight() / 2;

        if (x - cx >= 0 &&
                x + cx <= W &&
                y - cy >= 0 &&
                y + cy <= H) {
            player.relocate(x - cx, y - cy);
        }
    }

    //Method for controlling the game updates
    public void gameUpdate() throws InterruptedException {
        invaderFiringUpdate();
        playerFiringUpdate();
        playerKilled();
        invaderKilled();
        invadersMovement();
        gameWon();
        gameLost();
    }

    //One of the methods for controlling when the monsters will fire
    public int rand(int min, int max) {
        return (int) (Math.random() * max + min);
    }

    //Method for the controlling the invaders projectiles
    public void invadersFiring() {
        int getInvaderFiringIndex = rand(0, invaders.size() - 1);
        invaderProjectile.add(projectile(invaders.get(getInvaderFiringIndex).getLayoutX() + 25, invaders.get(getInvaderFiringIndex).getLayoutY() + 25));
        root.getChildren().add(invaderProjectile.get(invaderProjectile.size() - 1));
    }

    //Method for adding invaders each for loop represents one line of invaders.
    public void addInvaders() {
        for (int i = 0, w = 40; i < 6; i++, w += 90) {
            invaders.add(invader(w, 60));
            root.getChildren().add((Node) invaders.get(i));
        }
        for (int i = 0, w = 40; i < 6; i++, w += 90) {
            invaders.add(invader(w, 140));
            root.getChildren().add((Node) invaders.get(i + 6));
        }
        for (int i = 0, w = 40; i < 6; i++, w += 90) {
            invaders.add(invader(w, 200));
            root.getChildren().add((Node) invaders.get(i + 12));
        }
        for (int i = 0, w = 40; i < 6; i++, w += 90) {
            invaders.add(invader(w, 280));
            root.getChildren().add((Node) invaders.get(i + 18));
        }
    }

    //Method for controlling the movement of invaders
    public void invadersMovement() throws InterruptedException {

        double velocity;

        if (toRight)
            velocity = 0.75;
        else
            velocity = -0.75;
        if (projectile.getLayoutX() >= 40) {
            toRight = false;
            for (int i = 0; i < invaders.size(); i++) {
                invaders.get(i).setLayoutY(invaders.get(i).getLayoutY() + 10);
            }
        }
        if (projectile.getLayoutX() <= -20) {
            toRight = true;
            for (int i = 0; i < invaders.size(); i++) {
                invaders.get(i).setLayoutY(invaders.get(i).getLayoutY() + 10);
            }
        }
        for (int i = 0; i < invaders.size(); i++) {
            invaders.get(i).setLayoutX(invaders.get(i).getLayoutX() + velocity);

            //ends the game game if the invaders gets too close to the player
            if (invaders.get(i).getLayoutY() == 660) {
                invadersWon();
            }
        }

        projectile.setLayoutX(projectile.getLayoutX() + velocity);
    }

    //Method for selecting the image of the players ship
    public ImageView player() {
        ImageView playerImg = new ImageView(new Image(getClass().getResourceAsStream("/resources/ship2.png")));
        playerImg.setLayoutX(300);
        playerImg.setLayoutY(750);
        playerImg.setFitHeight(50);
        playerImg.setFitWidth(50);
        return playerImg;
    }


    //Method for selecting the image to be used for the invaders
    public ImageView invader(double x, double y) {
        ImageView invaderImg = new ImageView(new Image(getClass().getResourceAsStream("/resources/inva.png")));
        invaderImg.setLayoutX(x);
        invaderImg.setLayoutY(y);
        invaderImg.setFitHeight(50);
        invaderImg.setFitWidth(50);
        return invaderImg;
    }

    //Method for creating the projectiles
    public Circle projectile(double x, double y) {
        Circle c = new Circle();
        c.setFill(Color.LIGHTYELLOW);
        c.setLayoutX(x);
        c.setLayoutY(y);
        c.setRadius(4);
        return c;
    }

    //Method for firing projectiles
    public void playerFiring(double x) {
        playerProjectile.add(projectile((x + 25), 750));
        root.getChildren().add(playerProjectile.get(playerProjectile.size() - 1));
    }

    //Method for updating the game when the player fires
    private void playerFiringUpdate() {
        if (!playerProjectile.isEmpty()) {
            for (int i = 0; i < playerProjectile.size(); i++) {
                playerProjectile.get(i).setLayoutY(playerProjectile.get(i).getLayoutY() - 4);
                if (playerProjectile.get(i).getLayoutY() <= 0) {
                    root.getChildren().remove(playerProjectile.get(i));
                    playerProjectile.remove(i);
                }
            }
        }
    }

    //Method for updating the game when an invader fires
    private void invaderFiringUpdate() {
        if (!invaderProjectile.isEmpty()) {
            for (int i = 0; i < invaderProjectile.size(); i++) {
                invaderProjectile.get(i).setLayoutY(invaderProjectile.get(i).getLayoutY() + 4);
                if (invaderProjectile.get(i).getLayoutY() <= 0) {
                    root.getChildren().remove(invaderProjectile.get(i));
                    invaderProjectile.remove(i);
                }
            }
        }
    }

    //Method for what happens when a invader is killed
    private void invaderKilled() {
        for (int i = 0; i < playerProjectile.size(); i++) {
            for (int j = 0; j < invaders.size(); j++) {
                if (((playerProjectile.get(i).getLayoutX() > invaders.get(j).getLayoutX())
                        && ((playerProjectile.get(i).getLayoutX() < invaders.get(j).getLayoutX() + 50))
                        && ((playerProjectile.get(i).getLayoutY() > invaders.get(j).getLayoutY())
                        && ((playerProjectile.get(i).getLayoutY() < invaders.get(j).getLayoutY() + 50))))) {
                    root.getChildren().remove(invaders.get(j));
                    invaders.remove(j);
                    root.getChildren().remove(playerProjectile.get(i));
                    playerProjectile.remove(i);
                    playerPoints += 50;
                    points.setText("Points: " + playerPoints);
                }
            }
        }
    }

    //Method for what happens when the player is killed
    private void playerKilled() {
        for (int i = 0; i < invaderProjectile.size(); i++) {
            if (((invaderProjectile.get(i).getLayoutX() > player.getLayoutX())
                    && ((invaderProjectile.get(i).getLayoutX() < player.getLayoutX() + 50))
                    && ((invaderProjectile.get(i).getLayoutY() > player.getLayoutY())
                    && ((invaderProjectile.get(i).getLayoutY() < player.getLayoutY() + 50))))) {
                lifeLost();
            }
        }
    }

    private void lifeLost(){
        player.setLayoutX(300);
        playerLives--;
        lives.setText("Lives: " + playerLives);
    }

    //Method for what happens when a player wins the game
    public void gameWon() {
        if (invaders.isEmpty()) {
            Text textWon = new Text();
            textWon.setFont(Font.font("JourneyPS3", FontWeight.BOLD, 100));
            textWon.setX(300);
            textWon.setY(400);
            textWon.setFill(Color.YELLOW);
            textWon.setText("WIN");
            root.getChildren().add(textWon);
            timer.stop();
            closeGame();
            printPoints();//Amir edit
        }
    }

    //Method for what happens when a game is lost
    public void gameLost() {
        if (playerLives <= 0) {
            Text textLost = new Text();
            textLost.setFont(Font.font("JourneyPS3", FontWeight.BOLD, 100));
            textLost.setX(300);
            textLost.setY(400);
            textLost.setFill(Color.INDIANRED);
            textLost.setText("LOST");
            root.getChildren().add(textLost);
            timer.stop();
            closeGame();
            printPoints();//Amir edit
        }
    }

    //Method for closing the game when the invaders gets too close to the ship
    public void invadersWon() {
        Text textInvaders = new Text();
        textInvaders.setFont(Font.font("JourneyPS3", FontWeight.BOLD, 100));
        textInvaders.setX(300);
        textInvaders.setY(400);
        textInvaders.setFill(Color.INDIANRED);
        textInvaders.setText("LOST");
        root.getChildren().add(textInvaders);
        timer.stop();
        closeGame();
        printPoints(); //Amir edit
    }

    //Method for closing down the game automatically after two seconds
    public void closeGame() {
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        Platform.runLater(() -> {
                            Stage stage = (Stage) lives.getScene().getWindow();
                            stage.close();
                        });
                    }
                },
                2000
        );
    }

    //Amir edit
    public void printPoints() {

        try {
            //"False" makes sure that the file is overwritten if program run multiple times
            DataOutputStream out = new DataOutputStream(new FileOutputStream("src/resources/SpaceScore.txt", false));
            out.writeInt(playerPoints);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //Method for running the game through the main class
    @Override
    public void run() {
        launch();
    }
}
