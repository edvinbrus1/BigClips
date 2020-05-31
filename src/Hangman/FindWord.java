package Hangman;

//Created by Nikola

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Stream;

public class FindWord {


    protected static String lowerCaseWord(String word) {
        word = word.toUpperCase();
        return word;
    }

    protected static ArrayList<String> wordChar(String theWord) {
        ArrayList<String> listOfChar = new ArrayList<>();
        for (int i = 0; i < theWord.length(); i++) {
            char a = theWord.charAt(i);
            listOfChar.add(a + "");
        }
        return listOfChar;
    }

    protected static String importWords(int line) {


        try {
            //reads in the textfile
            BufferedReader br = new BufferedReader(new FileReader("src/resources/wordList.txt"));

            //skips as many lines as int line decides
            for (int i = 0; i < line; i++) {
                br.readLine();
            }

            return br.readLine();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "error";

    }


}
