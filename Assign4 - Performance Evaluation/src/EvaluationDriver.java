/**
 * Assignment 4 for CS 1410
 * This program evaluates the linear and binary searching, along
 * with comparing performance difference between the selection sort
 * and the built-in java.util.Arrays.sort.
 *
 * @author James Dean Mathias
 */
public class EvaluationDriver {
    static final int MAX_VALUE = 1_000_000;
    static final int MAX_ARRAY_SIZE = 100_000;
    static final int ARRAY_SIZE_START = 20_000;
    static final int ARRAY_SIZE_INCREMENT = 20_000;
    static final int NUMBER_SEARCHES = 50_000;

    public static int[] generateNumbers(int howMany, int maxValue) {
        if (howMany < 0 || howMany > MAX_ARRAY_SIZE) {
            return null;
        }
        int[] numbers = new int[howMany];
        for (int i = 0; i < howMany; i++) {
            numbers[i] = (int) (Math.random() * maxValue);
        }
        return numbers;
    }

    public static boolean linearSearch(int[] data, int search) {
        for (int datum : data) {
            if (datum == search) {
                return true;
            }
        }
        return false;
    }

    public static boolean binarySearch(int[] data, int search) {
        selectionSort(data);
        int low = 0;
        int high = data.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (data[mid] == search) {
                return true;
            } else if (data[mid] < search) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return false;
    }

    ;

    public static void selectionSort(int[] data) {
        int dataLength = data.length;
        for (int i = 0; i < dataLength - 1; i++) {
            // Find Minimum
            int minIndex = i;
            for (int j = i + 1; j < dataLength; j++) {
                if (data[j] < data[minIndex]) {
                    minIndex = j;
                }
            }

            //Reassigning
            int placeholder = data[i];
            data[i] = data[minIndex];
            data[minIndex] = placeholder;
        }

    }

    public static void main(String[] args) {

//        demoLinearSearchUnsorted();
//        demoLinearSearchSorted();
//        demoBinarySearchSelectionSort();
//        demoBinarySearchFastSort();
    }

}
