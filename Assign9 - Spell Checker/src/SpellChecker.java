import java.io.*;
import java.util.*;

public class SpellChecker {
    public static void main(String[] args){

        // Step 1: Demonstrate tree capabilities
        testTree();

        // Step 2: Read the dictionary and report the tree statistics
        BinarySearchTree<String> dictionary = readDictionary();
        reportTreeStats(dictionary);

        // Step 3: Perform spell checking
        spellChecker(dictionary);
    }

    /**
     * This method is used to help develop the BST, also for the grader to
     * evaluate if the BST is performing correctly.
     */
    public static void testTree() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();

        //
        // Add a bunch of values to the tree
        tree.insert("Olga");
        tree.insert("Tomeka");
        tree.insert("Benjamin");
        tree.insert("Ulysses");
        tree.insert("Tanesha");
        tree.insert("Judie");
        tree.insert("Tisa");
        tree.insert("Santiago");
        tree.insert("Chia");
        tree.insert("Arden");

        //
        // Make sure it displays in sorted order
        tree.display("--- Initial Tree State ---");
        reportTreeStats(tree);

        //
        // Try to add a duplicate
        if (tree.insert("Tomeka")) {
            System.out.println("oops, shouldn't have returned true from the insert");
        }
        tree.display("--- After Adding Duplicate ---");
        reportTreeStats(tree);

        //
        // Remove some existing values from the tree
        tree.remove("Olga");    // Root node
        tree.remove("Arden");   // a leaf node
        tree.display("--- Removing Existing Values ---");
        reportTreeStats(tree);

        //
        // Remove a value that was never in the tree, hope it doesn't crash!
        tree.remove("Karl");
        tree.display("--- Removing A Non-Existent Value ---");
        reportTreeStats(tree);
    }

    /**
     * This method is used to report on some stats about the BST
     */
    public static void reportTreeStats(BinarySearchTree<String> tree) {
        System.out.println("-- Tree Stats --");
        System.out.printf("Total Nodes : %d\n", tree.numberNodes());
        System.out.printf("Leaf Nodes  : %d\n", tree.numberLeafNodes());
        System.out.printf("Tree Height : %d\n", tree.height());
    }

    /**
     * This method reads the dictionary and constructs the BST to be
     * used for the spell checking.
     */
    public static BinarySearchTree<String> readDictionary() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        List<String> words = readFile("dictionary.txt");

        Collections.shuffle(words, new Random(System.currentTimeMillis()));
        for (String word : words) {
            tree.insert(word.trim().toLowerCase());
        }

        return tree;
    }

    private static List<String> readFile(String fileName) {
        List<String> words = new ArrayList<>();
        File file = new File(fileName);
        try (Scanner input = new Scanner(file)) {
            while (input.hasNextLine()) {
                String line = input.nextLine();
                words.addAll(Arrays.asList(line.split("\\s+")));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Oops! I couldn't read file: " + fileName);
            System.out.println("I ran into this error: " + e);
        }
        return words;
    }

    private static String cleaner(String string) {
        return string.replaceAll("[^a-zA-Z]", "").toLowerCase();
    }

    public static void spellChecker(BinarySearchTree<String> dictionary){
        List<String> letWords = readFile("letter.txt");
        System.out.println("\n-- Misspelled Words --");
        for (String word : letWords) {
            String cleaned = cleaner(word.trim());
            if (!dictionary.search(cleaned)) {
                if (!cleaned.isEmpty()) {
                    System.out.print("  - ");
                    System.out.println(cleaned);
                }
            }
        }
    }
}
