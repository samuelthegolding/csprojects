public class WordInfoPriority extends WordInfo implements Comparable<WordInfoPriority> {
    private int priority;

    public WordInfoPriority(String word, int moves, int estimatedWork) {
        super(word, moves, word);
        this.priority = moves + 2 * estimatedWork;
    }

    public WordInfoPriority(String word, int moves, int estimatedWork, String history) {
        super(word, moves, history);
        this.priority = moves + 2 * estimatedWork;
    }

    public int getPriority() {
        return priority;
    }


    @Override
    public String toString() {
        return String.format("Word: %s, Moves: %d, Priority: %d, History: %s",
                getWord(), getMoves(), priority, getHistory());
    }

    @Override
    public int compareTo(WordInfoPriority comparison) {
        return Integer.compare(priority, comparison.priority);
    }
}
