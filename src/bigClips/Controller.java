package bigClips;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


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
}
