package MyHangman;

import java.util.ArrayList;
import java.util.Random;

public class FindWord {

    public static String selectWord(int r) {
        ArrayList<String> listOfWords = new ArrayList<>();
        listOfWords.add("Sweden");
        listOfWords.add("Norway");
        listOfWords.add("Denmark");
        listOfWords.add("Finland");
        return listOfWords.get(r);
    }

    public static String lowerCaseWord(String word) {
        word = word.toUpperCase();
        return word;
    }
    public static ArrayList<String> wordChar(String theWord) {
        ArrayList<String> listOfChar = new ArrayList<>();
        for (int i = 0; i < theWord.length(); i++) {
            char a = theWord.charAt(i);
            listOfChar.add(a+"");
        }
        return listOfChar;
    }


}
