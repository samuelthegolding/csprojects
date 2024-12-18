import java.util.Scanner;

public class Pyramid1 {
    public static void main(String[] args) {
        // User input
        Scanner input =  new Scanner(System.in);
        System.out.print("Enter the number of lines: ");
        int lines = input.nextInt();

        // Formatting
        String lineStr = "" + lines;
        String format = "%" + lineStr.length() + "d ";
        StringBuilder spaces = new StringBuilder(" ");
        spaces.append(" ".repeat(lineStr.length()));

        // Looping/Printing
        for(int i = 0; i <= lines; i++) {
            // Leading spaces
            for (int j = 1; j <= (lines - i); j++) {
                System.out.print(spaces);
            }
            // Left --> Middle
            for (int j = i; j >= 1; j--) {
                System.out.printf(format, j);
            }
            // Middle --> Right
            for (int j = 2; j <= i; j++) {
                System.out.printf(format, j);
            }
            // New Line
            System.out.println();
        }
    }
}
