package gutenberg.words;

import java.util.List;
import java.util.Map;

public final class WordCounter {

    public Map<String, Integer> count(String filePath) {
        List<String> words = retrieveWordsFrom(filePath);
        Map<String, Integer> wordsCount = processCounting(words);
        // TODO sort in order to to get the 100 top most used word
        return wordsCount;
    }

    private List<String> retrieveWordsFrom(String filePath) {
        return null;
    }

    private Map<String, Integer> processCounting(List<String> words) {
        return null;
    }
}
