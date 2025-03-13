import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class QuadraticEquationSolver {
    public static void main(String[] args) {
        if (args.length == 0) {
            interactiveMode();
        }
    }

    private static void interactiveMode() {
        Scanner scanner = new Scanner(System.in);
        double a = readCoefficient("a", scanner);


        if (a == 0) {
            System.out.println("Error. a cannot be 0");
            System.exit(1);
        }

        double b = readCoefficient("b", scanner);
        double c = readCoefficient("c", scanner);

        solveAndPrintEquation(a, b, c);
    }

    private static double readCoefficient(String name, Scanner scanner) {
        while (true) {
            System.out.print(name + " = ");
            String input = scanner.nextLine().trim();

            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Error. Expected a valid real number, got " + input + " instead");
            }
        }
    }



    private static void solveAndPrintEquation(double a, double b, double c) {
        System.out.println("Equation is: (" + a + ") x^2 + (" + b + ") x + (" + c + ") = 0");

        double discriminant = b * b - 4 * a * c;

        if (discriminant > 0) {
            // Two distinct real roots
            double x1 = (-b - Math.sqrt(discriminant)) / (2 * a);
            double x2 = (-b + Math.sqrt(discriminant)) / (2 * a);
            System.out.println("There are 2 roots");
            System.out.println("x1 = " + x1);
            System.out.println("x2 = " + x2);
        } else if (discriminant == 0) {
            // One real root (repeated)
            double x = -b / (2 * a);
            System.out.println("There are 1 roots");
            System.out.println("x1 = " + x);
        } else {
            // No real roots
            System.out.println("There are 0 roots");
        }
    }
}