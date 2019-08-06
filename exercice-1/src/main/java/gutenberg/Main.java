package gutenberg;

import gutenberg.exception.CheckedIllegalArgumentException;
import gutenberg.words.WordCounter;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        String bookName = "les3mousquetaires.txt";
        try {
            new WordCounter().count(bookName);
        } catch (CheckedIllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}
