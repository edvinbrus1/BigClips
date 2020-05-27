package Hangman;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Hangman {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("***********\n\tMenu\n**********\n");
        System.out.println("* Start Game (press s)\n* End Game (press e)");
        String character = scan.nextLine();

        if (character.toLowerCase().equals("e")) {
            System.out.println("Good bye!");
            System.exit(0);

        } else if (character.toLowerCase().equals("s")) {

            Random random = new Random();
            int r = random.nextInt(4);
            selectWord(r);
            String word = selectWord(r);
            word = lowerCaseWord(word);
            ArrayList<Character> characters = wordChar(word);
            int chance = 11;

            boolean check = true;
            while (check) {
                System.out.print("Guess a letter: ");
                String theguess = scan.nextLine();
                theguess = theguess.toLowerCase();

                if (theguess.length() == 1) {
                    char guess = theguess.charAt(0);

                    for (int i = 0; i < characters.size(); i++) {
                        char a = word.charAt(i);
                        if (guess == characters.get(i)) {
                            characters.remove(i);
                            System.out.println("Correct");

                            if (characters.isEmpty()) {
                                check = false;
                                System.out.println("You won!");
                            }
                            break;
                        }
                        if (i == characters.size() - 1) {
                            chance--;
                            System.out.println("Not correct, chances left: " + chance);

                            if (chance == 0) {
                                check = false;
                                System.out.println("You lost!");
                            }

                        }
                    }
                } else
                    System.out.println("Caution! Only one letter at a time!");
            }

        }else
            System.out.println("Press n to end game or s to start game!");
    }

    public static String selectWord(int r) {
        ArrayList<String> listOfWords = new ArrayList<>();
        listOfWords.add("Sweden");
        listOfWords.add("Norway");
        listOfWords.add("Denmark");
        listOfWords.add("Finland");
        return listOfWords.get(r);
    }

    public static String lowerCaseWord(String word) {
        word = word.toLowerCase();
        return word;
    }

    public static ArrayList<Character> wordChar(String theWord) {
        ArrayList<Character> listOfChar = new ArrayList<>();
        for (int i = 0; i < theWord.length(); i++) {
            char a = theWord.charAt(i);
            listOfChar.add(a);
        }
        return listOfChar;
    }
}
