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
    public int hashCode() {
        return 31 * word.hashCode() + frequency;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof WordFrequency)) {
            return false;
        }

        WordFrequency wf = (WordFrequency) obj;
        return word.equals(wf.word) && frequency == wf.frequency;
    }

    @Override
    public String toString() {
        return "{" + word + ", " + frequency + "}";
    }
}
