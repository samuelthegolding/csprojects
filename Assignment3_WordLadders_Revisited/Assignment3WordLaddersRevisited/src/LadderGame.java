import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

abstract class LadderGame {
    ArrayList<ArrayList<String>> wordGrids;
    ArrayList<ArrayList<String>> wordGridsCopy;

    public LadderGame(String dictionaryFile) {
        this.wordGrids = readDictionary(dictionaryFile);
        assert wordGrids != null;
        this.wordGridsCopy = copyGrid(wordGrids);
    }

    static void playChecks(String start, String end, ArrayList<String> strings, ArrayList<String> strings2) {
        if (start.length() != end.length()) {
            System.out.println("Start and end Words need to be the same length");
            return;
        }

        if (!strings.contains(end) || !strings2.contains(start)) {
            System.out.println("Start and end Words need to be in the dictionary");
        }
    }

    abstract void play(String start, String end);

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

    public ArrayList<ArrayList<String>> copyGrid(ArrayList<ArrayList<String>> grid) {
        ArrayList<ArrayList<String>> copy = new ArrayList<>();
        for (ArrayList<String> list : grid) {
            copy.add(new ArrayList<>(list));
        }
        return copy;
    }
}