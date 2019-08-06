package gutenberg;

import gutenberg.words.WordCounter;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        String bookName = "main/resources/les3mousquetaires.txt";
        new WordCounter().count(bookName);
    }
}
