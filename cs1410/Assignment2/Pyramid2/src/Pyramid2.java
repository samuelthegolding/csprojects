import java.util.Scanner;

public class Pyramid2 {
    public static void main(String[] args) {
        // User Input & Creation of List
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the number of rows: ");
        int rows = input.nextInt();
        long[] longest = new long[rows];
        for (int i = 0; i < rows; i++) {
            longest[i] = (long) Math.pow(2, i);
        }

        // Formatting
        String digits = "" + longest[rows-1];
        String format = "%" + digits.length() + "d ";
        StringBuilder spaces = new StringBuilder(" ");
        spaces.append(" ".repeat(digits.length()));

        // Printing
        for(int i = 0; i <= rows; i++){
            // Leading spaces
            for (int j = 1; j <= (rows - i); j++) {
                System.out.print(spaces);
            }
            // Middle --> Right
            for (int j = 0; j <= i - 1; j++) {
                System.out.printf(format, longest[j]);
            }
            // Left --> Middle
            for (int j = i-1; j >= 1; j--) {
                System.out.printf(format, longest[j - 1]);
            }
            // New Line
            System.out.println();

        }
    }
}