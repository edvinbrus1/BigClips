package bigClips;

import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Pair;

import java.util.Arrays;
import java.util.List;

public class BigClipsMenu extends Application {

    private static final int WIDTH = 1920;
    private static final int HEIGHT = 1080;

    private List<Pair<String, Runnable>> menuData = Arrays.asList(
            new Pair<String, Runnable>("Play", () -> {}),
            new Pair<String, Runnable>("Settings", () -> {}),
            new Pair<String, Runnable>("Exit", Platform::exit)
    );

    private Pane root = new Pane();
    private VBox menuBox = new VBox(-3);
    private Line line;

    private Parent createContent(){
        addBackground();
        addTitle();

        double lineX = WIDTH / 2 - 100;
        double lineY = HEIGHT / 3 + 50;

        addLine(lineX, lineY);
        addMenu(lineX + 5, lineY + 5);

        startAnimation();

        return root;
    }

    private void addBackground(){
        ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("/resources/BCBackground.png")));
        imageView.setFitWidth(WIDTH);
        imageView.setFitHeight(HEIGHT);

        root.getChildren().add(imageView);
    }

    private void addTitle(){
        BigClipsTitle title = new BigClipsTitle("BIG-CLIPS");
        title.setTranslateX(WIDTH / 2 - title.getTitleWidth() / 2);
        title.setTranslateY(HEIGHT / 3);

        root.getChildren().add(title);
    }

    private void addLine(double x, double y){
        line = new Line(x,y,x,y + 300);
        line.setStrokeWidth(3);
        line.setStroke(Color.color(1,1,1,0.70));
        line.setEffect(new DropShadow(5, Color.BLACK));
        line.setScaleY(0);

        root.getChildren().add(line);
    }

    private void startAnimation(){
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(1), line);
        scaleTransition.setToY(1);
        scaleTransition.setOnFinished(e -> {
            for(int i = 0; i < menuBox.getChildren().size(); i++){
                Node n = menuBox.getChildren().get(i);

                TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1 + i * 0.10), n);
                translateTransition.setToX(0);
                translateTransition.setOnFinished(f -> n.setClip(null));
                translateTransition.play();
            }
        });
        scaleTransition.play();
    }

    private void addMenu(double x, double y){
        menuBox.setTranslateX(x);
        menuBox.setTranslateY(y);
        menuData.forEach(data -> {
            BigClipsItem item = new BigClipsItem(data.getKey());
            item.setOnAction(data.getValue());
            item.setTranslateX(-300);

            Rectangle clip = new Rectangle(300,30);
            clip.translateXProperty().bind(item.translateXProperty().negate());

            item.setClip(clip);

            menuBox.getChildren().addAll(item);
        });
        root.getChildren().add(menuBox);
    }


    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(createContent());
        stage.setTitle("Big-Clips Start Menu Test");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[]args){
        launch(args);
    }
}
