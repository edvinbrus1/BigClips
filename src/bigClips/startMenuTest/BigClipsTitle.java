package bigClips.startMenuTest;

import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class BigClipsTitle extends Pane {
    private Text text;

    public BigClipsTitle(String name){
        String spread = "";
        for(char c : name.toCharArray()){
            spread += c + " ";
        }

        text = new Text(spread);
        text.setFont(Font.loadFont(BigClipsMenu.class.getResource("/resources/JourneyPS3.ttf")
                .toExternalForm(), 100));
        text.setFill(Color.WHITE);
        text.setEffect(new DropShadow(30, Color.BLACK));

        getChildren().addAll(text);
    }

    public double getTitleWidth(){
        return text.getLayoutBounds().getWidth();
    }

    public double getTitleHeight(){
        return text.getLayoutBounds().getHeight();
    }
}
