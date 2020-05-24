package bigClips;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import spaceInvaders.SpaceInvaders;
import treIRad.Control;

import javax.swing.*;
import java.io.*;


//Created by Edvin, controls the different javaFX elements and scenes.
public class Controller {

    private int totalScore = 0; //Amir edit

    //Method for controlling what happens when the user presses play within the start menu.
    @FXML
    public void playClicked(MouseEvent event) throws IOException {
        Parent textView = FXMLLoader.load(getClass().getResource("textWindow.fxml"));

        Scene textScene = new Scene(textView);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(textScene);
        window.show();




    }

    //Controlling what happens when a user presses the play button in the first text window
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

    //Controlling what happens when the user presses the continue button on the first game window.
    @FXML
    public void fgwContClicked(MouseEvent mouseEvent) throws IOException{
        Parent secondTextWindow = FXMLLoader.load(getClass().getResource("secondTextWindow.fxml"));

        Scene secondTextWindowScene = new Scene(secondTextWindow);

        Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        window.setScene(secondTextWindowScene);
        window.show();
    }

    //Controlling what happens when the user presses the play button on the second text window.
    @FXML
    public void secondTWPClicked(MouseEvent mouseEvent) throws IOException{
        Parent secondGameWindow = FXMLLoader.load(getClass().getResource("secondGameWindow.fxml"));

        Scene secondGameWindowScene = new Scene(secondGameWindow);

        Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        window.setScene(secondGameWindowScene);
        window.show();

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                new Control().run();
            }
        });
    }

    //Controlling what happens when the user presses the continue button in the second game window.
    @FXML
    public void sgwContClicked(MouseEvent mouseEvent) throws IOException{
        Parent resultWindow = FXMLLoader.load(getClass().getResource("resultWindow.fxml"));

        Scene resultWindowScene = new Scene(resultWindow);

        Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        window.setScene(resultWindowScene);
        window.show();

        //Amir edit under
        //Reads the scores from spaceinvader and tre i rads textfiles
        DataInputStream input = new DataInputStream(new FileInputStream("src/resources/TreScore.txt"));
        int TreScore = input.readInt();

        input = new DataInputStream(new FileInputStream("src/resources/SpaceScore.txt"));
        int SpaceScore = input.readInt();

        input.close();
        totalScore = SpaceScore + TreScore;

        // behövs kanske inte o visa alla poängen separat också men gjorde det för o se att allt funkade

        JOptionPane.showMessageDialog(null,"total: " + totalScore +"\nTre i rad: " + TreScore
        + "\nSpace invader: "+ SpaceScore);     // för o testa poängsystem

    }

    @FXML
    public void gameExitClicked(MouseEvent mouseEvent) throws IOException{
        Platform.exit();
    }

    //Controlling what happens when the user presses return within the settings window
    @FXML
    public void settingsReturnClicked(MouseEvent mouseEvent) throws IOException{
        Parent startMenu = FXMLLoader.load(getClass().getResource("startMenu.fxml"));

        Scene startMenuScene = new Scene(startMenu);

        Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        window.setScene(startMenuScene);
        window.show();
    }

    //Controlling what happens when the settings shape is clicked in the startMenu
    @FXML
    public void settingsClicked(MouseEvent mouseEvent) throws IOException{
        Parent settings = FXMLLoader.load(getClass().getResource("settingsScene.fxml"));

        Scene settingsScene = new Scene(settings);

        Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        window.setScene(settingsScene);
        window.show();
    }

    //Controlling what happens when the user clicks on exit within the start menu.
    @FXML
    public void startMenuExitClicked(MouseEvent mouseEvent) throws IOException {
        Platform.exit();
    }

    //Controlling what happens when the user clicks on the try again button in the first game window.
    @FXML
    public void fgwTryAgainClicked(MouseEvent mouseEvent) throws IOException {
        textWindowPlayClicked(mouseEvent);
    }




}
