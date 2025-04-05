import java.util.ArrayList;

public class LadderGamePriority extends LadderGame {
    public LadderGamePriority(String dictionaryFile) {
        super(dictionaryFile);
    }

    public void play(String start, String end) {
        playChecks(start, end, wordGrids.get(end.length()), wordGrids.get(start.length()));

        AVLTree<WordInfoPriority> priorityQueue = new AVLTree<>();
        int estimatedWork = estimate(start, end);
        WordInfoPriority startNode = new WordInfoPriority(start, 0, estimatedWork);
        priorityQueue.insert(startNode);
        double enqueues = 0;

        System.out.println("Seeking A* Solution from [" + start + " -> " + end + "]:");
        while (!priorityQueue.isEmpty()) {
            WordInfoPriority curr = priorityQueue.deleteMin();
            String currWord = curr.getWord();
            String currHistory = curr.getHistory();
            if (currWord.equals(end)) {
                System.out.printf("[%s], Total Enqueues: %d%n",
                        currHistory, (int) enqueues);

                wordGrids = wordGridsCopy;
                wordGridsCopy = copyGrid(wordGrids);
                return;
            }

            ArrayList<String> oneAwayWords = oneAway(currWord, true);
            for (String oneAwayWord : oneAwayWords) {
                int newEstimatedWork = estimate(oneAwayWord, end);
                priorityQueue.insert(new WordInfoPriority(
                        oneAwayWord,
                        curr.getMoves() + 1,
                        newEstimatedWork,
                        currHistory + " " + oneAwayWord));
                enqueues++;
            }
        }
        System.out.println("No Ladder :(");
        wordGrids = wordGridsCopy;
        wordGridsCopy = copyGrid(wordGrids);

    }

    private int estimate(String word, String target) {
        int diff = 0;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) != target.charAt(i)) {
                diff++;
            }
        }
        return diff;
    }
}