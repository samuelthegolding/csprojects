import java.util.Scanner;

public class Quadratic {
    public static void main(String[] args) {

        // Capturing User Input
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a, b, c: ");
        double a = input.nextDouble();
        double b = input.nextDouble();
        double c = input.nextDouble();

        // Calculating Discriminant
        double discriminant = b * b - 4 * a * c;

        // Calculating and Printing Roots
        if (discriminant > 0) {
            System.out.println("There are two roots for the quadratic equation with these coefficients.");
            double r1 = (-b + Math.sqrt(discriminant)) / (2 * a);
            double r2 = (-b - Math.sqrt(discriminant)) / (2 * a);
            System.out.println("r1 = " + r1);
            System.out.println("r2 = " + r2);
        } else if (discriminant == 0) {
            System.out.println("There is one root for the quadratic equation with these coefficients.");
            double r1 = (-b ) / (2 * a);
            System.out.println("r1 = " + r1);
        } else if (discriminant < 0) {
            System.out.println("There are no roots for the quadratic equation with these coefficients.");
        }
    }
}