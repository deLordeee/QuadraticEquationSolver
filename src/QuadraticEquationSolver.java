import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class QuadraticEquationSolver {
    public static void main(String[] args) {
        if (args.length == 0) {
            interactiveMode();
        } else if (args.length == 1) {
            fileMode(args[0]);
        } else {
            System.out.println("Usage: java QuadraticEquationSolver [file_path]");
            System.exit(1);
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

    private static void fileMode(String filePath) {
        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println("file " + filePath + " does not exist");
            System.exit(1);
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();

            
            if (reader.readLine() != null) {
                System.out.println("invalid file format");
                System.exit(1);
            }

            if (line == null) {
                System.out.println("invalid file format");
                System.exit(1);
            }

            String[] parts = line.trim().split("\\s+");
            if (parts.length != 3) {
                System.out.println("invalid file format");
                System.exit(1);
            }

            double a, b, c;
            try {
                a = Double.parseDouble(parts[0]);
                b = Double.parseDouble(parts[1]);
                c = Double.parseDouble(parts[2]);
            } catch (NumberFormatException e) {
                System.out.println("invalid file format");
                System.exit(1);
                return; 
            }

            if (a == 0) {
                System.out.println("Error. a cannot be 0");
                System.exit(1);
            }

            solveAndPrintEquation(a, b, c);

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            System.exit(1);
        }
    }

    private static void solveAndPrintEquation(double a, double b, double c) {
        System.out.println("Equation is: (" + a + ") x^2 + (" + b + ") x + (" + c + ") = 0");

        double discriminant = b * b - 4 * a * c;

        if (discriminant > 0) {
           
            double x1 = (-b - Math.sqrt(discriminant)) / (2 * a);
            double x2 = (-b + Math.sqrt(discriminant)) / (2 * a);
            System.out.println("There are 2 roots");
            System.out.println("x1 = " + x1);
            System.out.println("x2 = " + x2);
        } else if (discriminant == 0) {
           
            double x = -b / (2 * a);
            System.out.println("There are 1 roots");
            System.out.println("x1 = " + x);
        } else {
          
            System.out.println("There are 0 roots");
        }
    }
}
