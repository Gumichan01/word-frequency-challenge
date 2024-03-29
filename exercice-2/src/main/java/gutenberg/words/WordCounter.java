package gutenberg.words;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public final class WordCounter {

    public List<WordFrequency> count(String bookPath) {

        if (bookPath == null) {
            throw new IllegalArgumentException("The book to load is not provided");
        }

        List<String> words = retrieveWordsFrom(Paths.get(bookPath));
        Map<String, Integer> wordsCount = processCounting(words);
        Set<Map.Entry<String, Integer>> entries = wordsCount.entrySet();
        List<Map.Entry<String, Integer>> top100UsedWords = retrieveTop100UsedWords(entries);
        return top100UsedWords.stream().
                map(entry -> new WordFrequency(entry.getKey(), entry.getValue())).
                collect(Collectors.toList());
    }

    private List<String> retrieveWordsFrom(Path book) {
        String text = retrieveTextOf(book);
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
        return words.parallelStream().collect(Collectors.toConcurrentMap(word -> word, word -> 1, Integer::sum));
    }

    private List<Map.Entry<String, Integer>> retrieveTop100UsedWords(Set<Map.Entry<String, Integer>> entries) {
        int numberOfEntriesToTake = 100;
        return entries.stream().
                sorted((o1, o2) -> Integer.compare(o2.getValue(), o1.getValue())).
                limit(numberOfEntriesToTake).
                collect(Collectors.toList());
    }
}
