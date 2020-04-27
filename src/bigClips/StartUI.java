package bigClips;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import spaceInvaders.SpaceInvaders;

import java.io.IOException;

public class StartUI extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        try{
            Parent planner = FXMLLoader.load(getClass().getResource("startMenu.fxml"));
            primaryStage.setTitle("Big-Clips");
            primaryStage.setScene(new Scene(planner));
            primaryStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        SpaceInvaders.launch(args);
    }
}
