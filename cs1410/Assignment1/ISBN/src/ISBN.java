import java.util.Arrays;
import java.util.Scanner;

public class ISBN {
    public static void main(String[] args) {
        // Initializing Scanner
        Scanner input =  new Scanner(System.in);
        System.out.print("Enter the first 9 digits of an ISBN: ");
        int isbn = input.nextInt();

        // Deconstructing Integer into List of Integers
        int[] isbnList = new int[10];
        int number = isbn;
        for (int i = 8; i >= 0; i--) {
            int digit = Math.floorDiv(number, (int) Math.pow(10, (double) i));
            number = number - ((int) Math.pow(10, i)) * digit;
            isbnList[8 - i] = digit;
        }

        // Calculating Checksum
        int checksum = 0;
        for (int j = 1; j<=9; j++){
            checksum += isbnList[j] * (j + 1);
        }
        isbnList[9] = checksum % 11;

        // Recreating string from Array
        StringBuilder isbnString = new StringBuilder();
        for (int j : isbnList) {
            if (j != 10) {
                isbnString.append(j);
            } else {
                isbnString.append("X");
            }
        }

        // Printing String
        System.out.println("The ISBN-10 number is : " + isbnString);
    }
}