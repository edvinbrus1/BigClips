package Hangman;

/**
 * Class for getting the word for hangman
 *
 * @author Nikola
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FindWord {


    /**
     * Changes the word to uppercase letters
     *
     * @param word the selected secret word for hangman
     * @return the same word but in all caps
     */
    protected static String lowerCaseWord(String word) {
        word = word.toUpperCase();
        return word;
    }

    /**
     * Method for splitting up the word into its chars and making an arraylist out of them.
     *
     * @param theWord the chosen secret word for hangman
     * @return arraylist with the word split into its chars
     */
    protected static ArrayList<String> wordChar(String theWord) {
        ArrayList<String> listOfChar = new ArrayList<>();
        for (int i = 0; i < theWord.length(); i++) {
            char a = theWord.charAt(i);
            listOfChar.add(a + "");
        }
        return listOfChar;
    }

    /**
     * Method for retrieving a word from a textfile
     * Words in the textfile downloaded from https://github.com/first20hours/google-10000-english/blob/master/google-10000-english-no-swears.txt
     * No further vetting done.
     *
     * @param line decides at which line the word should be taken from
     * @return the retrieved word
     */
    protected static String importWords(int line) {


        try {
            //Words downloaded from https://github.com/first20hours/google-10000-english/blob/master/google-10000-english-no-swears.txt

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
