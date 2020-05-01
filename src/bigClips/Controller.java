package bigClips;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import spaceInvaders.SpaceInvaders;


import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Controller {

    @FXML
    public void playClicked(MouseEvent event) throws IOException {
        Parent textView = FXMLLoader.load(getClass().getResource("textWindow.fxml"));

        Scene textScene = new Scene(textView);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(textScene);
        window.show();
    }

    @FXML
    public void textWindowPlayClicked(MouseEvent mouseEvent) throws IOException {
        Parent firstGameView = FXMLLoader.load(getClass().getResource("firstGameWindow.fxml"));

        Scene firstGameScene = new Scene(firstGameView);

        Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        window.setScene(firstGameScene);
        window.show();

       Platform.runLater(new Runnable() {
           @Override
           public void run() {
               try {
                   new SpaceInvaders().start(new Stage());
               } catch (Exception e) {
                   e.printStackTrace();
               }
           }
       });
    }
}
