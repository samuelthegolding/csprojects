public class Recursion {
    public static boolean isWordSymmetric(String[] words, int start, int end) {

        // Win Condition
        if (start >= end) {
            return true;
        }

        // Fail Condition
        if (!(words[start]).equalsIgnoreCase(words[end])) {
            return false;
        }

        return isWordSymmetric(words, start + 1, end - 1); // Recursing while moving inwards by one
    }

    public static long arraySum(int[] data, int position) {

        // End Condition, Exits Recursion and Stops Adding
        if (position >= data.length) {
            return 0;
        }

        // Recursion and moving forward
        return data[position] + arraySum(data, position + 1);
    }

    public static int arrayMin(int[] data, int position) {

        // End Condition, exits recursion and returns the value at the position
        if (position == data.length - 1) {
            return data[position];
        }

        // Recursion, and returns minimum between the recursed value and the data at the position.
        return Math.min(data[position], arrayMin(data, position + 1));
    }

    public static double computePyramidWeights(double[][] masses, int row, int column) {
        double leftSide;
        double rightSide;
        // Out of Bounds Handling
        if (row < 0 || column < 0 || row >= masses.length || column >= masses[row].length) {
            return 0;
        }

        // If top of pyramid, return top of pyramid.
        if (row == 0 && column == 0) {
            return masses[row][column];
        }

        // Computing Left Side if Exists.
        if (column > 0) {
            leftSide = (computePyramidWeights(masses, row - 1, column - 1)) / 2;
        } else {
            leftSide = 0;
        }

        // Computing Right Side if Exists.
        if (column < masses[row - 1].length) {
            rightSide = (computePyramidWeights(masses, row - 1, column)) / 2;
        } else {
            rightSide = 0;
        }

        // Returning sum.
        return masses[row][column] + leftSide + rightSide;
    }

    public static int howManyOrganisms(char[][] image) {
        // Initializers
        int count = 0;
        char label = 'a';

        // Iterate through all the "Image"
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[i].length; j++) {
                if (image[i][j] == '*') {
                    // Trigger recursive method and increment count and label.
                    recurFill(image, i, j, label);
                    count++;
                    label++;
                }
            }
        }
        return count;
    }

    private static void recurFill(char[][] image, int row, int column, char label) {
        if (row < 0 || column < 0 || row >= image.length || column >= image[row].length || image[row][column] != '*') {
            return;
        }

        // Renaming
        image[row][column] = label;

        // Recursively go through the sides.
        recurFill(image, row - 1, column, label); // Up
        recurFill(image, row + 1, column, label); // Down
        recurFill(image, row, column - 1, label); // Left
        recurFill(image, row, column + 1, label); // Right
    }

    public static void main(String[] args) {

        int[] sumMe = {1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89};
        System.out.printf("Array Sum: %d\n", arraySum(sumMe, 0));

        int[] minMe = {0, 1, 1, 2, 3, 5, 8, -42, 13, 21, 34, 55, 89};
        System.out.printf("Array Min: %d\n", arrayMin(minMe, 0));

        String[] amISymmetric = {"You can cage a swallow can't you but you can't swallow a cage can you", "I still say cS 1410 is my favorite class"};
        for (String test : amISymmetric) {
            String[] words = test.toLowerCase().split(" ");
            System.out.println();
            System.out.println(test);
            System.out.printf("Is word symmetric: %b\n", isWordSymmetric(words, 0, words.length - 1));
        }

        double[][] masses = {{51.18}, {55.90, 131.25}, {69.05, 133.66, 132.82}, {53.43, 139.61, 134.06, 121.63}};
        System.out.println();
        System.out.println("--- Weight Pyramid ---");
        for (int row = 0; row < masses.length; row++) {
            for (int column = 0; column < masses[row].length; column++) {
                double weight = computePyramidWeights(masses, row, column);
                System.out.printf("%.2f ", weight);
            }
            System.out.println();
        }

        char[][] image = {{'*', '*', ' ', ' ', ' ', ' ', ' ', ' ', '*', ' '}, {' ', '*', ' ', ' ', ' ', ' ', ' ', ' ', '*', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', '*', '*', ' ', ' '}, {' ', '*', ' ', ' ', '*', '*', '*', ' ', ' ', ' '}, {' ', '*', '*', ' ', '*', ' ', '*', ' ', '*', ' '}, {' ', '*', '*', ' ', '*', '*', '*', '*', '*', '*'}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '*', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '*', ' '}, {' ', ' ', ' ', '*', '*', '*', ' ', ' ', '*', ' '}, {' ', ' ', ' ', ' ', ' ', '*', ' ', ' ', '*', ' '}};
        int howMany = howManyOrganisms(image);
        System.out.println();
        System.out.println("--- Labeled Organism Image ---");
        for (char[] line : image) {
            for (char item : line) {
                System.out.print(item);
            }
            System.out.println();
        }
        System.out.printf("There are %d organisms in the image.\n", howMany);

    }
}
