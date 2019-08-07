package gutenberg;

import gutenberg.words.WordCounter;
import gutenberg.words.WordFrequency;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        String bookName = "les3mousquetaires.txt";
        List<WordFrequency> wordStats = new WordCounter().count(bookName);
        System.out.println("{<Word>" + ", " + "<Count>}");
        System.out.println("-----------------");
        wordStats.forEach(System.out::println);
    }
}
