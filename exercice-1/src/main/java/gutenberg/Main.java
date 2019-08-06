package gutenberg;

import gutenberg.exception.CheckedIllegalArgumentException;
import gutenberg.words.WordCounter;

import java.util.Map;

public class Main {

    public static void main(String[] args) {
        try {
            String bookName = "les3mousquetaires.txt";
            Map<String, Integer> wordStats = new WordCounter().count(bookName);
            System.out.println("Word" + " - " + "Count ");
            System.out.println("--------------");
            wordStats.forEach((s, integer) -> System.out.println(s + " - " + integer));
        } catch (CheckedIllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}
