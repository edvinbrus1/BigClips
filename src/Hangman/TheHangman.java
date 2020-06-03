package Hangman;

//Created by Nikola

import bigClips.StartGame;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;


public class TheHangman extends Application implements Runnable {

    public static void main(String[] args) {
        StartGame.launch(args);
    }

    private Label guessingDocks; //Edit by Edvin, used to get access to the stage

    @Override
    public void start(Stage primaryStage) throws Exception {



        //Getting the word and new game
        Random random = new Random();
        int skip = random.nextInt(84091);

        String wordFromTxtfile = (FindWord.importWords(skip));
        String word = FindWord.lowerCaseWord(wordFromTxtfile);
        ArrayList<String> wordList = FindWord.wordChar(word);




        BorderPane root = new BorderPane();
        //This is used for the box user writes letter in and a error text underneath
        VBox vb = new VBox();
        TextField answer = new TextField();//Box to write in
        answer.setMaxWidth(30);
        Button button = new Button("Guess");
        Label errorMessage = new Label();//Error message
        vb.getChildren().addAll(answer, button, errorMessage);
        vb.setAlignment(Pos.CENTER);
        root.setBottom(vb);

        //Hangman! All pictures are iterations of the hangman game process
        Image stage0 = new Image("resources/HackInit.png");
        ImageView v0 = new ImageView();
        v0.setImage(stage0);
        v0.setFitHeight(300);
        v0.setFitWidth(600);
        root.setCenter(v0);

        Image stage1 = new Image("resources/earth1.jpg");
        ImageView v1 = new ImageView();
        v1.setImage(stage1);
        v1.setFitHeight(300);
        v1.setFitWidth(600);

        Image stage2 = new Image("resources/earth2.jpg");
        ImageView v2 = new ImageView();
        v2.setImage(stage2);
        v2.setFitHeight(300);
        v2.setFitWidth(600);

        Image stage3 = new Image("resources/earth3.jpg");
        ImageView v3 = new ImageView();
        v3.setImage(stage3);
        v3.setFitHeight(300);
        v3.setFitWidth(600);

        Image stage4 = new Image("resources/earth4.jpg");
        ImageView v4 = new ImageView();
        v4.setImage(stage4);
        v4.setFitHeight(300);
        v4.setFitWidth(600);

        Image stage5 = new Image("resources/earth5.jpg");
        ImageView v5 = new ImageView();
        v5.setImage(stage5);
        v5.setFitHeight(300);
        v5.setFitWidth(600);

        Image stage6 = new Image("resources/earth6.jpg");
        ImageView v6 = new ImageView();
        v6.setImage(stage6);
        v6.setFitHeight(300);
        v6.setFitWidth(600);

        Image stageLoss = new Image("resources/earth7.jpg");
        ImageView Lose = new ImageView();
        Lose.setImage(stageLoss);
        Lose.setFitHeight(300);
        Lose.setFitWidth(600);

        Image stageWin = new Image("resources/HackComplete.png");
        ImageView Win = new ImageView();
        Win.setImage(stageWin);
        Win.setFitHeight(300);
        Win.setFitWidth(600);

        //this is what presents at the top of the screen. "___" or letters if user guessed right.
        HBox hb = new HBox();
        guessingDocks = new Label();
        ArrayList list = new ArrayList(); //for displaying the letters and "___".
        ArrayList<String> carrier = new ArrayList<>(); //for adding the correctly guessed letters
                                                        // so that we can compare two ArrayLists to see if the user won.
        for (int i = 0; i < wordList.size(); i++) {
            list.add("___\t"); //adding for correct index input later
            carrier.add("");//adding for correct index  input later
        }

        AtomicInteger chance = new AtomicInteger(7); //Chances before user loses
        //Action
        button.setOnAction(e -> {

            if (chance.get() == 1) {//Losing message
                saveResult(0); //score 0 if player loses
                button.setDisable(true);
                root.setCenter(Lose);
                errorMessage.setText("You lost! Better luck next time!");
                closeGame(); //Edit by Edvin
            }
            else if (answer.getText().isEmpty()) {
                errorMessage.setText("Please enter a letter!");
            }
            else if (answer.getText().length() > 1) {
                errorMessage.setText("Only enter one letter at a time please!");
            }
            else if (answer.getText().matches("[0-9,\"[!@#$%&*()_+=|<>?{}\\\\[\\\\]~-]\"]+")) {
                errorMessage.setText("No numbers/special characters are allowed only letters from A-Z/a-z!");
            }
            else {
                boolean pass = false; //Used so we know when player guesses wrong or right.
                for (int i = 0; i < wordList.size(); i++) {
                    String temp = wordList.get(i); //Letter from the wordList
                    String answerString = answer.getText(); //letter from the User
                    answerString = answerString.toUpperCase();
                    char wordChar = temp.charAt(0);
                    char answerChar = answerString.charAt(0);

                    if (answerChar == wordChar) { //When user is correct
                        list.remove(i); //remove index that is getting a letter instead
                        list.add(i, answerString + "\t"); //adding letter to index
                        carrier.remove(i);
                        carrier.add(i, answerString);
                        errorMessage.setText("Good Job!");
                        pass = true;
                    }
                }
                if (carrier.equals(wordList)) {//Winning message
                    saveResult(1200);
                    button.setDisable(true);
                    root.setCenter(Win);
                    errorMessage.setText("Congratulations! You won! You had only " + chance + " chances left!");
                    closeGame();
                }
                if (pass == false) { //When user guess is incorrect
                    chance.getAndDecrement(); //Chance--
                    errorMessage.setText("Better luck next time! Chances left: " + chance);

                    switch (chance.get()) { //For showing hangman status
                        case 1:
                            root.setCenter(v6);
                            break;
                        case 2:
                            root.setCenter(v5);
                            break;
                        case 3:
                            root.setCenter(v4);
                            break;
                        case 4:
                            root.setCenter(v3);
                            break;
                        case 5:
                            root.setCenter(v2);
                            break;
                        case 6:
                            root.setCenter(v1);
                            break;
                    }
                }
            }
            String temp = "";
            for (Object a : list) {

                temp += a;
            }
            guessingDocks.setText(temp);
        });

        hb.getChildren().add(guessingDocks);
        hb.setAlignment(Pos.BASELINE_CENTER);
        root.setTop(hb);


        Scene scene = new Scene(root, 600, 800);
        primaryStage.setTitle("Hangman");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    //Puts player result in textfile.
    private void saveResult(int score) {
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream("src/resources/HangmanScore.txt", false));
            dataOutputStream.writeInt(score);
            dataOutputStream.flush();
            dataOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for closing the game automatically after a loss
     * @author Edvin - edit
     *
     */
    public void closeGame(){
        new java.util.Timer().schedule(
                new java.util.TimerTask(){
                    @Override
                    public void run(){
                        Platform.runLater(()->{
                            Stage stage = (Stage) guessingDocks.getScene().getWindow();
                            stage.close();
                        });
                    }
                },
                4000
        );
    }

    /**
     * @author Edvin - edit
     * Used for launching the Hangman through the main game.
     */
    @Override
    public void run(){
        launch();
    }

}

