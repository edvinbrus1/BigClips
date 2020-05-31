package Hangman;

//Created by Nikola

import javafx.application.Application;
import javafx.geometry.Pos;
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


public class TheHangman extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        //Getting the word and new game
        Random random = new Random();
        int r = random.nextInt(4);
        String word = FindWord.lowerCaseWord(FindWord.selectWord(r));
        ArrayList<String> wordList = FindWord.wordChar(word);
        System.out.println(wordList); //ta bort sen

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
        Image stage0 = new Image("resources/Logo.png");
        ImageView v0 = new ImageView();
        v0.setImage(stage0);
        v0.setFitHeight(200);
        v0.setFitWidth(200);
        root.setCenter(v0);

        Image stage1 = new Image("resources/1.png");
        ImageView v1 = new ImageView();
        v1.setImage(stage1);
        v1.setFitHeight(200);
        v1.setFitWidth(400);

        Image stage2 = new Image("resources/2.png");
        ImageView v2 = new ImageView();
        v2.setImage(stage2);
        v2.setFitHeight(200);
        v2.setFitWidth(400);

        Image stage3 = new Image("resources/3.png");
        ImageView v3 = new ImageView();
        v3.setImage(stage3);
        v3.setFitHeight(200);
        v3.setFitWidth(400);

        Image stage4 = new Image("resources/4.png");
        ImageView v4 = new ImageView();
        v4.setImage(stage4);
        v4.setFitHeight(200);
        v4.setFitWidth(400);

        Image stage5 = new Image("resources/5.png");
        ImageView v5 = new ImageView();
        v5.setImage(stage5);
        v5.setFitHeight(200);
        v5.setFitWidth(400);

        Image stage6 = new Image("resources/6.png");
        ImageView v6 = new ImageView();
        v6.setImage(stage6);
        v6.setFitHeight(200);
        v6.setFitWidth(400);

        Image stageLoss = new Image("resources/Lose.png");
        ImageView Lose = new ImageView();
        Lose.setImage(stageLoss);
        Lose.setFitHeight(200);
        Lose.setFitWidth(400);

        Image stageWin = new Image("resources/WinHangman.png");
        ImageView Win = new ImageView();
        Win.setImage(stageWin);
        Win.setFitHeight(200);
        Win.setFitWidth(400);

        //this is what presents at the top of the screen. "___" or letters if user guessed right.
        HBox hb = new HBox();
        Label guessingDocks = new Label();
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
                button.setDisable(true);
                root.setCenter(Lose);
                errorMessage.setText("You lost! Better luck next time!");
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
                    saveResult();
                    button.setDisable(true);
                    root.setCenter(Win);
                    errorMessage.setText("Congratulations You won! You had only chances " + chance + " left!");
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
            System.out.println(list + "  ,  " + wordList + "   ,  " + carrier); //Ta bort senare
            guessingDocks.setText(temp);
        });

        hb.getChildren().add(guessingDocks);
        hb.setAlignment(Pos.BASELINE_CENTER);
        root.setTop(hb);


        Scene scene = new Scene(root, 400, 400);
        primaryStage.setTitle("Hangman");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private void saveResult() {
        try {
            //"false" makes sure the file is overwritten if program if run multiple times
            DataOutputStream out = new DataOutputStream(new FileOutputStream("src/resources/HangmanScore.txt", false));
            out.writeInt(1200);
            out.flush();
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {
        launch(args);
    }

}

