import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class WritePoetry {
    private HashTable<String, WordFreqInfo> hashTable;

    /**
     * Method to write a Poem from a file
     *
     * @param file           file used as a word bank
     * @param startWord      word with which to start the poem
     * @param length         length of poem
     * @param printHashtable boolean representing whether to print the table before the poem or not
     * @return the poem
     */
    public String writePoem(String file, String startWord, int length, boolean printHashtable) {
        hashTable = buildHashTable(file);

        if (printHashtable) {
            System.out.println(hashTable.toString(10)); // Print the first 10 entries for debugging
        }

        // Initializers
        StringBuilder poem = new StringBuilder();
        String currentWord = startWord.toLowerCase();
        Random rnd = new Random();

        for (int i = 0; i < length; i++) {
            // Add the current word to the poem
            poem.append(currentWord);

            // Look for the current word in the hashtable
            WordFreqInfo wordInfo = hashTable.find(currentWord);

            // If it doesn't work, end the poem.
            if (wordInfo == null || wordInfo.getOccurCount() == 0) {
                break; // No follow word found
            }

            // Pick a new word
            int count = rnd.nextInt(wordInfo.getOccurCount() - 1);
            String nextWord = wordInfo.getFollowWord(count);

            // If it doesn't work, end the poem.
            if (nextWord == null) {
                break; // No valid follow word
            }

            // If the next word isn't punctuation, add a space
            if (!isPunctuation((nextWord))) {
                poem.append(" ");
            }

            // If the current word is punctuation, add a newline
            if (isPunctuation(currentWord)) {
                poem.append("\n");
            }

            // Reassign currentWord to restart the loop
            currentWord = nextWord;
        }

        // Ensure the poem ends with punctuation
        String output = "";

        // If the last word wasn't already punctuation
        if (!isPunctuation(currentWord)) {
            poem.deleteCharAt(poem.length() - 1); // Delete the last space
            String regex = "([.,!?])\\s+[.,!?]"; // To avoid double punctuation
            poem.append("."); // Add a period
            output = poem.toString().replaceAll(regex, "$1"); // If a period wasn't necessary, delete it
        }

        // Return the output string if it was necessary to create, otherwise, just return the poem/
        return !output.isEmpty() ? output : poem.toString();
    }

    private HashTable<String, WordFreqInfo> buildHashTable(String file) {
        HashTable<String, WordFreqInfo> table = new HashTable<>(100);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            String previousWord = null;

            while ((line = reader.readLine()) != null) {
                String[] words = line.split("[\s+]"); // Split by spaces
                for (String word : words) {
                    word = word.toLowerCase();
                    if (word.isEmpty()) {
                        continue; // Skip empty words
                    }
                    String punctuation = "";

                    if (!Character.isLetter(word.charAt(word.length() - 1))) {
                        // Extract punctuation from the end of the word
                        punctuation = word.substring(word.length() - 1);
                        word = word.substring(0, word.length() - 1);
                    }

                    // Add the word to the hash table
                    WordFreqInfo wordInfo = table.find(word);
                    if (wordInfo == null) {
                        wordInfo = new WordFreqInfo(word, 1);
                        table.insert(word, wordInfo);
                    }

                    // If there's a previous word, update its followList
                    if (previousWord != null) {
                        WordFreqInfo previousInfo = table.find(previousWord);
                        if (previousInfo == null) {
                            previousInfo = new WordFreqInfo(previousWord, 1);
                            table.insert(previousWord, previousInfo);
                        }
                        previousInfo.updateFollows(word);
                    }

                    // Update previousWord to the current word
                    previousWord = word;

                    if (!punctuation.isEmpty()) {
                        WordFreqInfo punctuationInfo = table.find(punctuation);
                        if (punctuationInfo == null) {
                            punctuationInfo = new WordFreqInfo(punctuation, 1);
                            table.insert(punctuation, punctuationInfo);
                        }

                        wordInfo.updateFollows(punctuation);
                        previousWord = punctuation;
                    }
                }


            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + file);
            e.printStackTrace();
        }
        return table;
    }

    private boolean isPunctuation(String word) {
        if (word == null) return false;
        return word.matches("[.,!?]");
    }
}