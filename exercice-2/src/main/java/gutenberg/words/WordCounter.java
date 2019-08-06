package gutenberg.words;

import gutenberg.exception.CheckedIllegalArgumentException;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public final class WordCounter {

    public List<WordFrequency> count(String bookPath) throws CheckedIllegalArgumentException {

        if (bookPath == null) {
            throw new CheckedIllegalArgumentException("The book to load is not provided");
        }

        List<String> words = retrieveWordsFrom(Paths.get(bookPath));
        List<String> distinctWords = Collections.unmodifiableList(words).stream().distinct().collect(Collectors.toList());
        System.out.println(words.size());
        System.out.println(distinctWords.size());
        Map<String, Integer> wordsCount = processCounting(words);
        Set<Map.Entry<String, Integer>> entries = wordsCount.entrySet();
        return retrieveTop100UsedWords(entries);
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
        Map<String, Integer> map = new HashMap<>();
        words.forEach(s -> map.put(s, map.getOrDefault(s, 0) + 1));
        return map;
    }

    private List<WordFrequency> retrieveTop100UsedWords(Set<Map.Entry<String, Integer>> entries) {
        int numberOfEntriesToTake = 100;
        Comparator<Map.Entry<String, Integer>> entryComparator = (o1, o2) -> Integer.compare(o2.getValue(), o1.getValue());
        return entries.stream().sorted(entryComparator).limit(numberOfEntriesToTake).
                map(entry -> new WordFrequency(entry.getKey(), entry.getValue())).collect(Collectors.toList());
    }
}
