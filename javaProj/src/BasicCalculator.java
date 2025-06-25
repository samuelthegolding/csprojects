import java.util.Scanner;

public class BasicCalculator {

    double result;

    BasicCalculator() {
        this.result = 0;
    }

    public void driver(double num1, double num2, String operation) {
        result = switch (operation) {
            case "+" -> addition(num1, num2);
            case "-" -> subtraction(num1, num2);
            case "*" -> multiplication(num1, num2);
            case "/" -> division(num1, num2);
            case "^" -> power(num1, num2);
            case "log" -> log(num1, num2);
            default -> {
                System.out.println("Invalid Operation");
                yield 0;
            }
        };
    }

    private static double addition(double num1, double num2) {
        return num1 + num2;
    }

    private static double subtraction(double num1, double num2) {
        return num1 - num2;
    }

    private static double multiplication(double num1, double num2) {
        return num1 * num2;
    }

    private static double division(double num1, double num2) {
        return num2 == 0 ? Double.NaN : (num1 / num2);
    }

    private static double power(double num1, double num2) {
        return Math.pow(num1, num2);
    }

    private static double log(double base, double value) {
        if (base <= 0 || base == 1 || value <= 0) {
            System.out.println("Error: Invalid input for logarithm.");
            return Double.NaN;
        }
        return Math.log(value) / Math.log(base);
    }
}