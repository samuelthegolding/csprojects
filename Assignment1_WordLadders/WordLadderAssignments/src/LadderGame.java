import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

public class LadderGame {
    ArrayList<ArrayList<String>> wordGrids;
    ArrayList<ArrayList<String>> wordGridsCopy;

    public LadderGame(String dictionaryFile) {
        this.wordGrids = readDictionary(dictionaryFile);
        this.wordGridsCopy = copyGrid(wordGrids);
    }

    public void play(String start, String end) {
        if (start.length() != end.length()) {
            System.out.println("Start and end Words need to be the same length");
            return;
        }
        
        if (!wordGrids.get(end.length()).contains(end) || !wordGrids.get(start.length()).contains(start)) {
            System.out.println("Start and end Words need to be in the dictionary");
            return;
        }

        Queue<WordInfo> queue = new Queue<>();
        queue.enqueue(new WordInfo(start, 0));
        double enqueues = 0;

        System.out.print(start + " -> " + end);
        while (!queue.isEmpty()) {
            WordInfo active = queue.dequeue();
            String activeWord = active.getWord();
            int activeMoves = active.getMoves();
            String activeHistory = active.getHistory();


            for (String oneLetterAway : oneAway(activeWord, true)) {

                if (oneLetterAway.equals(end)) {
                    System.out.printf(" : Moves %d : [%s], Total Enqueues: %d%n",
                            ++activeMoves, activeHistory + " " + oneLetterAway, (int) enqueues);

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

    public ArrayList<String> oneAway(String word, boolean withRemoval) {
        ArrayList<String> oneAwayWords = new ArrayList<>();
        int wordLen = word.length();
        ArrayList<String> sameLength = wordGrids.get(wordLen);

        for (String sameLengthWord : sameLength) {
            if (diff(word, sameLengthWord) == 1) {
                oneAwayWords.add(sameLengthWord);
            }
        }
        if (withRemoval) {
            sameLength.removeAll(oneAwayWords);
        }

        return oneAwayWords;
    }

    public void listWords(int length, int howMany) {
        for (int i = 0; i < howMany; i++) {
            String word = wordGrids.get(length).get(i);
            System.out.println(word);
        }
    }

    /*
        Reads a list of words from a file, putting all words of the same length into the same array.
     */
    private ArrayList<ArrayList<String>> readDictionary(String dictionaryFile) {
        File file = new File(dictionaryFile);
        ArrayList<String> allWords = new ArrayList<>();

        // Track the longest word, because that tells us how big to make the array.
        int longestWord = 0;
        try (Scanner input = new Scanner(file)) {
            //
            // Start by reading all the words into memory.
            while (input.hasNextLine()) {
                String word = input.nextLine().toLowerCase();
                allWords.add(word);
                longestWord = Math.max(longestWord, word.length());
            }

            ArrayList<ArrayList<String>> wordGrids = new ArrayList<>(longestWord);
            for (int i = 0; i <= longestWord + 1; i++) {
                wordGrids.add(new ArrayList<>());
            }
            for (String currentWord : allWords) {
                wordGrids.get(currentWord.length()).add(currentWord);
                
            }

            return wordGrids;
        } catch (java.io.IOException ex) {
            System.out.println("An error occurred trying to read the dictionary: " + ex);
        }
        return null;
    }

    private int diff(String word1, String word2) {
        int numDiff = 0;
        if (word1.equalsIgnoreCase(word2)) {
            return 0;
        } else if (word1.length() == word2.length()) {
            for (int i = 0; i < word1.length(); i++) {
                if (word1.charAt(i) != word2.charAt(i)) {
                    numDiff++;
                }
            }
        }
        return numDiff;
    }

    private ArrayList<ArrayList<String>> copyGrid(ArrayList<ArrayList<String>> grid) {
        ArrayList<ArrayList<String>> copy = new ArrayList<>();
        for (ArrayList<String> list : grid) {
            copy.add(new ArrayList<>(list));
        }
        return copy;
    }
}