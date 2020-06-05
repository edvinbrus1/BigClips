package bigClips;

import Hangman.Hangman;
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
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;


/**
 * Controller class for the main game, controls the various FXML documents and the launching
 * of the two smaller games that have been implemented
 *
 * @author Edvin
 */
public class Controller {

    private int totalScore = 0; //Amir edit

    /**
     * Method for controlling what happens when the user clicks play on the start menu,
     * loads up the textWindow fxml document and sets the scene to that fxml document
     *
     * @param event mouse clicked
     * @throws IOException IOException
     */
    @FXML
    public void playClicked(MouseEvent event) throws IOException {
        Parent textView = FXMLLoader.load(getClass().getResource("textWindow.fxml"));

        Scene textScene = new Scene(textView);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(textScene);
        window.show();


    }

    /**
     * Controlling what happens when a user presses the play button in the first text window,
     * after the user has read the first piece of the story the play button is expected to
     * be pressed which loads the firstGameWindow scene and starts the Space Invaders mini game.
     *
     * @param mouseEvent mouse clicked
     * @throws IOException IOException
     */
    @FXML
    public void textWindowPlayClicked(MouseEvent mouseEvent) throws IOException {
        Parent firstGameView = FXMLLoader.load(getClass().getResource("firstGameWindow.fxml"));

        Scene firstGameScene = new Scene(firstGameView);

        Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        window.setScene(firstGameScene);
        window.show();

        Platform.runLater(() -> {
            try {
                new SpaceInvaders().start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Controlling what happens when the user presses the continue button on the first game window,
     * the method loads another FXML document and creates a new scene including another piece
     * of the games story
     *
     * @param mouseEvent mouse clicked
     * @throws IOException IOException
     */
    @FXML
    public void fgwContClicked(MouseEvent mouseEvent) throws IOException {
        Parent secondTextWindow = FXMLLoader.load(getClass().getResource("secondTextWindow.fxml"));

        Scene secondTextWindowScene = new Scene(secondTextWindow);

        Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        window.setScene(secondTextWindowScene);
        window.show();
    }

    /**
     * Controlling what happens when the user presses the play button on the second text window,
     * after the second piece of the story has been read the user presses the play button which
     * loads up the secondGameWindow fxml document and creates a scene. It also launches the Tre
     * i rad mini game.
     *
     * @param mouseEvent mouse clicked
     * @throws IOException IOException
     */
    @FXML
    public void secondTWPClicked(MouseEvent mouseEvent) throws IOException {
        Parent secondGameWindow = FXMLLoader.load(getClass().getResource("secondGameWindow.fxml"));

        Scene secondGameWindowScene = new Scene(secondGameWindow);

        Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        window.setScene(secondGameWindowScene);
        window.show();

        Platform.runLater(() -> new Control().run());
    }

    /**
     * Controlling what happens when the user presses the continue button in the second game window,
     * Since we have only managed to implement two smaller games at this moment, the player will be
     * brought to the result window in which the user can see the total score of the two games.
     *
     * @param mouseEvent mouse clicked
     * @throws IOException IOException
     */
    @FXML
    public void sgwContClicked(MouseEvent mouseEvent) throws IOException {
        Parent thirdTextWindow = FXMLLoader.load(getClass().getResource("thirdTextWindow.fxml"));

        Scene thirdTextScene = new Scene(thirdTextWindow);

        Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        window.setScene(thirdTextScene);
        window.show();

    }

    /**
     * Method for controlling what happens when the Exit game button is clicked
     *
     * @param mouseEvent mouse clicked
     * @throws IOException IOException
     */
    @FXML
    public void gameExitClicked(MouseEvent mouseEvent) throws IOException {
        Platform.exit();
    }

    /**
     * Controlling what happens when the user presses return within the settings window,
     * when the user presses return they will be brought back to the start menu
     *
     * @param mouseEvent mouse clicked
     * @throws IOException IOException
     */
    @FXML
    public void settingsReturnClicked(MouseEvent mouseEvent) throws IOException {
        Parent startMenu = FXMLLoader.load(getClass().getResource("startMenu.fxml"));

        Scene startMenuScene = new Scene(startMenu);

        Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        window.setScene(startMenuScene);
        window.show();
    }

    /**
     * Controlling what happens when the settings shape is clicked in the startMenu, which is that
     * they will be taken to the settings scene.
     *
     * @param mouseEvent mouse clicked
     * @throws IOException IOException
     */
    @FXML
    public void settingsClicked(MouseEvent mouseEvent) throws IOException {
        Parent settings = FXMLLoader.load(getClass().getResource("settingsScene.fxml"));

        Scene settingsScene = new Scene(settings);

        Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        window.setScene(settingsScene);
        window.show();
    }

    /**
     * Method for exiting the game when the user presses exit on the start menu
     *
     * @param mouseEvent mouse clicked
     * @throws IOException IOException
     */
    @FXML
    public void startMenuExitClicked(MouseEvent mouseEvent) throws IOException {
        Platform.exit();
    }

    /**
     * Method for allowing the user to try again on the Space Invaders mini game
     *
     * @param mouseEvent mouse clicked
     * @throws IOException IOException
     */
    @FXML
    public void fgwTryAgainClicked(MouseEvent mouseEvent) throws IOException {
        textWindowPlayClicked(mouseEvent);
    }

    /**
     * Method for controlling what happens when the user presses play again on the result screen.
     *
     * @param mouseEvent mouseClicked
     * @throws IOException IOException
     */
    @FXML
    public void victoryPlayedAgain(MouseEvent mouseEvent) throws IOException {
        playClicked(mouseEvent);
    }

    /**
     * Method for controlling what happens when the user presses continue on the third game window
     *
     * @param mouseEvent mouse clicked
     * @throws IOException IOException
     */
    @FXML
    public void tgwContClicked(MouseEvent mouseEvent) throws IOException {
        Parent finalText = FXMLLoader.load(getClass().getResource("finalTextWindow.fxml"));

        Scene finalTextScene = new Scene(finalText);

        Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        window.setScene(finalTextScene);
        window.show();
    }

    /**
     * Method for controlling what happens when the user presses continue in the final text window.
     *
     * @param mouseEvent mouse clicked
     * @throws IOException IOException
     */
    @FXML
    public void finalTWPClicked(MouseEvent mouseEvent) throws IOException {

        DataInputStream input = new DataInputStream(new FileInputStream("src/resources/TreScore.txt"));
        int TreScore = input.readInt();

        input = new DataInputStream(new FileInputStream("src/resources/SpaceScore.txt"));
        int SpaceScore = input.readInt();

        input = new DataInputStream(new FileInputStream("src/resources/HangmanScore.txt"));
        int hangmanScore = input.readInt();

        input.close();
        totalScore = SpaceScore + TreScore + hangmanScore;

        if (totalScore > 2750) {
            Parent resultWindow = FXMLLoader.load(getClass().getResource("resultWindow.fxml"));

            Scene resultWindowScene = new Scene(resultWindow);

            Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            window.setScene(resultWindowScene);
            window.show();

        } else if (totalScore < 2750) {
            Parent altResultWindow = FXMLLoader.load(getClass().getResource("altResultWindow.fxml"));

            Scene altResultWindowScene = new Scene(altResultWindow);

            Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            window.setScene(altResultWindowScene);
            window.show();
        }

        JOptionPane.showMessageDialog(null, "total: " + totalScore + "\nTre i rad: " + TreScore
                + "\nSpace Invaders: " + SpaceScore + "\nHangman: " + hangmanScore);


    }

    /**
     * Method for controlling what happens when the user presses try again for the Hangman game.
     *
     * @param mouseEvent mouse clicked
     * @throws IOException IOException
     */
    @FXML
    public void tgwTryAgainClicked(MouseEvent mouseEvent) throws IOException {
        thirdTWPClicked(mouseEvent);
    }

    /**
     * Method for controlling what happens when the user presses play on the third text window. It will launch the
     * mini-game Hangman in the same manner as Space Invaders.
     *
     * @param mouseEvent mouse clicked
     * @throws IOException IOException
     */
    @FXML
    public void thirdTWPClicked(MouseEvent mouseEvent) throws IOException {
        Parent thirdGameView = FXMLLoader.load(getClass().getResource("thirdGameWindow.fxml"));

        Scene thirdGameScene = new Scene(thirdGameView);

        Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        window.setScene(thirdGameScene);
        window.show();

        Platform.runLater(() -> {
            try {
                new Hangman().start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
