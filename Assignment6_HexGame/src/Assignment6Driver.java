import java.io.File;
import java.util.Scanner;


public class Assignment6Driver {
    public static void main(String[] args) {
        testGame();
        playGame("moves1.txt");
        System.out.println();
        playGame("moves2.txt");
    }

    private static void playGame(String filename) {
        HexGame game = new HexGame(11);
        File file = new File(filename);
        try (Scanner input = new Scanner(file)) {
            // Start with the blue turn
            boolean blueTurn = true;
            while (input.hasNextInt()) {
                // Play Every Turn until someone wins
                int pos = input.nextInt();
                boolean hasWon = blueTurn ?
                        game.playBlue(pos, false) :
                        game.playRed(pos, false);

                // Print Winning Board
                if (hasWon) {
                    System.out.println("\n" + (blueTurn ? "Blue" : "Red") + " wins with play at position " + pos + "!");
                    printGrid(game);
                    return;
                }
                // Invert Turn to be Red's Turn
                blueTurn = !blueTurn;
            }
            // If no more moves are left, there's no winner.
            System.out.println("No winner!");
            printGrid(game);
        } catch (java.io.IOException ex) {
            System.out.println("An error occurred trying to read the moves file: " + ex);
        }
    }

    //
    // TODO: You can use this to compare with the output show in the assignment while working on your code
    private static void testGame() {
        HexGame game = new HexGame(11);

        System.out.println("--- red ---");
        game.playRed(1, true);
        game.playRed(11, true);
        game.playRed(122 - 12, true);
        game.playRed(122 - 11, true);
        game.playRed(122 - 10, true);
        game.playRed(121, true);
        game.playRed(61, true);

        System.out.println("--- blue ---");
        game.playBlue(1, true);
        game.playBlue(2, true);
        game.playBlue(11, true);
        game.playBlue(12, true);
        game.playBlue(121, true);
        game.playBlue(122 - 11, true);
        game.playBlue(62, true);

        printGrid(game);
    }

    /**
     * Essentially an Override for toString, but a bit fancier
     *
     * @param game HexGame to be Printed
     */
    private static void printGrid(HexGame game) {
        // ANSI color codes
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_BLUE = "\u001B[34m";
        final String ANSI_WHITE = "\u001B[37m";

        // Get Grid and Size from the input Game
        Color[] grid = game.getGrid();
        int size = game.getSize();

        // Adding Top Red Border
        for (int i = 0; i < size; i++) System.out.print(ANSI_RED + "̅̅̅" + ANSI_RESET);
        System.out.println();

        // Loop to Print All Rows
        for (int row = 0; row < size; row++) {
            // Loop to add indentation
            for (int i = 0; i < row; i++) System.out.print("  ");

            // Add Left Blue Border
            System.out.print(ANSI_BLUE + "\\" + ANSI_RESET);

            // Adding Each Row
            for (int col = 0; col < size; col++) {
                int index = row * size + col;
                if (index >= grid.length) continue;

                Color cell = grid[index];

                // Switch statement to Display based on moves played
                String display = switch (cell) {
                    case RED -> ANSI_RED + " R " + ANSI_RESET;
                    case BLUE -> ANSI_BLUE + " B " + ANSI_RESET;
                    default -> ANSI_WHITE + " 0 " + ANSI_RESET;
                };
                System.out.print(display);
            }
            // Adding Right Blue Border
            System.out.println(ANSI_BLUE + "\\" + ANSI_RESET);
        }

        // Adding Bottom Red Borders
        for (int i = 0; i < size; i++) System.out.print("  ");
        for (int i = 0; i < size; i++) System.out.print(ANSI_RED + "̅̅̅" + ANSI_RESET);
    }

}
