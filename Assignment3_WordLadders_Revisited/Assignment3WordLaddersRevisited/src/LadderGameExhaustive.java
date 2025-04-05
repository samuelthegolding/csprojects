public class LadderGameExhaustive extends LadderGame {

    public LadderGameExhaustive(String dictionaryFile) {
        super(dictionaryFile);
    }

    public void play(String start, String end) {
        playChecks(start, end, wordGrids.get(end.length()), wordGrids.get(start.length()));

        Queue<WordInfo> queue = new Queue<>();
        queue.enqueue(new WordInfo(start, 0));
        double enqueues = 0;

        System.out.println("Seeking exhaustive solution from [" + start + " -> " + end + "]:");
        while (!queue.isEmpty()) {
            WordInfo active = queue.dequeue();
            String activeWord = active.getWord();
            int activeMoves = active.getMoves();
            String activeHistory = active.getHistory();
            for (String oneLetterAway : oneAway(activeWord, true)) {
                if (oneLetterAway.equals(end)) {
                    System.out.printf("[%s], Total Enqueues: %d%n",
                            activeHistory + " " + oneLetterAway, (int) enqueues);

                    wordGrids = wordGridsCopy;
                    wordGridsCopy = copyGrid(wordGrids);
                    return;
                }
                if (!activeHistory.contains(oneLetterAway)) {
                    queue.enqueue(
                            new WordInfo(
                                    oneLetterAway,
                                    activeMoves + 1,
                                    activeHistory + " " + oneLetterAway
                            )
                    );
                    enqueues++;
                }

            }
        }
        System.out.println(" No Ladder was found :(");
        wordGrids = wordGridsCopy;
        wordGridsCopy = copyGrid(wordGrids);

    }

}
