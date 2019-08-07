package gutenberg.words;

public final class WordFrequency {

    final String word;
    final int frequency;

    public WordFrequency(String word, int frequency) {
        this.word = word;
        this.frequency = frequency;
    }

    public String getWord() {
        return word;
    }

    public int getFrequency() {
        return frequency;
    }

    @Override
    public String toString() {
        return "{" + word + ", " + frequency + "}";
    }
}
