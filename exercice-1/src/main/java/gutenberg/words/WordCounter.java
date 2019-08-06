package gutenberg.words;

import gutenberg.exception.CheckedIllegalArgumentException;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class WordCounter {

    public Map<String, Integer> count(String bookName) throws CheckedIllegalArgumentException {

        if (bookName == null) {
            throw new CheckedIllegalArgumentException("The document to load is not provided");
        }

        Path filePath = Paths.get(bookName);
        List<String> words = retrieveWordsFrom(filePath);
        Map<String, Integer> wordsCount = processCounting(words);
        // TODO sort in order to to get the 100 top most used word
        return wordsCount;
    }

    private List<String> retrieveWordsFrom(Path filePath) {
        String text = retrieveTextOf(filePath);
        String[] words = text.split("\\s+");
        return Arrays.asList(words);
    }

    private String retrieveTextOf(Path filePath) {
        try {
            byte[] bytes = Files.readAllBytes(filePath);
            return new String(bytes);

        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private Map<String, Integer> processCounting(List<String> words) {
        Map<String, Integer> map = new HashMap<>();
        words.forEach(s -> map.put(s, map.getOrDefault(s,0) + 1));
        return map;
    }
}
