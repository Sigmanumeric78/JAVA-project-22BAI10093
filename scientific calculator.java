import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * ScientificCalculator.java
 * A fully interactive command-line scientific calculator.
 * Supports: Arithmetic, Trigonometry, Complex Numbers, Matrix Operations, and Linear Systems.
 */
public class ScientificCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("=================================================");
        System.out.println("       ULTIMATE SCIENTIFIC CALCULATOR");
        System.out.println("=================================================");

        while (running) {
            printMainMenu();
            int choice = getIntInput(scanner, "Enter your choice: ");

            switch (choice) {
                case 1:
                    runArithmeticMode(scanner);
                    break;
                case 2:
                    runComplexMode(scanner);
                    break;
                case 3:
                    runMatrixMode(scanner);
                    break;
                case 4:
                    runLinearSystemMode(scanner);
                    break;
                case 5:
                    running = false;
                    System.out.println("Exiting calculator. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please select 1-5.");
            }
        }
        scanner.close();
    }

    // --- Menus and Modes ---

    private static void printMainMenu() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. Basic & Scientific Arithmetic");
        System.out.println("2. Complex Number Operations");
        System.out.println("3. Matrix Operations");
        System.out.println("4. Solve Linear Equations (2x2)");
        System.out.println("5. Exit");
    }

    private static void runArithmeticMode(Scanner scanner) {
        System.out.println("\n--- Arithmetic & Scientific Mode ---");
        System.out.println("Operations: +, -, *, /, power, sqrt, sin, cos, tan, log, log10");
        System.out.print("Enter operation: ");
        String op = scanner.next().trim().toLowerCase();

        double result = Double.NaN;
        
        try {
            if (isBinaryOp(op)) {
                double a = getDoubleInput(scanner, "Enter first number: ");
                double b = getDoubleInput(scanner, "Enter second number: ");
                switch (op) {
                    case "+": result = a + b; break;
                    case "-": result = a - b; break;
                    case "*": result = a * b; break;
                    case "/": 
                        if (b == 0) System.out.println("Error: Division by zero."); 
                        else result = a / b; 
                        break;
                    case "power": result = Math.pow(a, b); break;
                }
            } else if (isUnaryOp(op)) {
                double a = getDoubleInput(scanner, "Enter number: ");
                switch (op) {
                    case "sqrt": 
                        if (a < 0) System.out.println("Error: Negative input for sqrt.");
                        else result = Math.sqrt(a); 
                        break;
                    case "sin": result = Math.sin(a); break;
                    case "cos": result = Math.cos(a); break;
                    case "tan": result = Math.tan(a); break;
                    case "log": 
                        if (a <= 0) System.out.println("Error: Non-positive input for log.");
                        else result = Math.log(a); 
                        break;
                    case "log10": 
                        if (a <= 0) System.out.println("Error: Non-positive input for log10.");
                        else result = Math.log10(a); 
                        break;
                }
            } else {
                System.out.println("Invalid operation.");
                return;
            }

            if (!Double.isNaN(result)) {
                System.out.printf("Result: %.4f\n", result);
                System.out.println("Fraction: " + Fraction.fromDouble(result));
            }

        } catch (Exception e) {
            System.out.println("Calculation error: " + e.getMessage());
        }
    }

    private static void runComplexMode(Scanner scanner) {
        System.out.println("\n--- Complex Number Mode ---");
        System.out.println("1. Add  2. Subtract  3. Multiply  4. Divide  5. Magnitude");
        int choice = getIntInput(scanner, "Choose operation (1-5): ");

        if (choice == 5) {
            ComplexNumber c = getComplexInput(scanner, "Enter complex number");
            System.out.printf("Magnitude: %.4f\n", c.magnitude());
        } else if (choice >= 1 && choice <= 4) {
            ComplexNumber c1 = getComplexInput(scanner, "Enter first complex number");
            ComplexNumber c2 = getComplexInput(scanner, "Enter second complex number");
            ComplexNumber res = null;

            switch (choice) {
                case 1: res = c1.add(c2); break;
                case 2: res = c1.subtract(c2); break;
                case 3: res = c1.multiply(c2); break;
                case 4: res = c1.divide(c2); break;
            }
            System.out.println("Result: " + res);
        } else {
            System.out.println("Invalid choice.");
        }
    }

    private static void runMatrixMode(Scanner scanner) {
        System.out.println("\n--- Matrix Mode ---");
        System.out.println("1. Add Matrices");
        System.out.println("2. Multiply Matrices");
        int choice = getIntInput(scanner, "Choose operation (1-2): ");

        if (choice == 1) {
            System.out.println("Note: Matrices must have same dimensions.");
            int rows = getIntInput(scanner, "Enter number of rows: ");
            int cols = getIntInput(scanner, "Enter number of columns: ");
            Matrix A = getMatrixInput(scanner, rows, cols, "Matrix A");
            Matrix B = getMatrixInput(scanner, rows, cols, "Matrix B");
            System.out.println("Result:\n" + A.add(B));
        } else if (choice == 2) {
            System.out.println("Note: Cols of A must equal Rows of B.");
            int r1 = getIntInput(scanner, "Rows for Matrix A: ");
            int c1 = getIntInput(scanner, "Cols for Matrix A: ");
            int r2 = getIntInput(scanner, "Rows for Matrix B: ");
            int c2 = getIntInput(scanner, "Cols for Matrix B: ");

            if (c1 != r2) {
                System.out.println("Error: Dimensions mismatch for multiplication.");
                return;
            }
            Matrix A = getMatrixInput(scanner, r1, c1, "Matrix A");
            Matrix B = getMatrixInput(scanner, r2, c2, "Matrix B");
            System.out.println("Result:\n" + A.multiply(B));
        } else {
            System.out.println("Invalid choice.");
        }
    }

    private static void runLinearSystemMode(Scanner scanner) {
        System.out.println("\n--- Solve Linear System (2x2) ---");
        System.out.println("Format: ax + by = e");
        System.out.println("        cx + dy = f");
        
        Matrix coeff = getMatrixInput(scanner, 2, 2, "Coefficient Matrix (a,b,c,d)");
        System.out.println("Enter constants (e, f):");
        double e = getDoubleInput(scanner, "Value for e: ");
        double f = getDoubleInput(scanner, "Value for f: ");
        double[] rhs = {e, f};

        try {
            double[] res = coeff.solve2x2System(rhs);
            System.out.printf("Solution: x = %.2f, y = %.2f\n", res[0], res[1]);
        } catch (IllegalArgumentException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    // --- Input Helpers ---

    private static boolean isBinaryOp(String op) {
        return op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/") || op.equals("power");
    }

    private static boolean isUnaryOp(String op) {
        return op.equals("sqrt") || op.equals("sin") || op.equals("cos") || op.equals("tan") || op.equals("log") || op.equals("log10");
    }

    private static double getDoubleInput(Scanner scanner, String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            System.out.print("Invalid input. " + prompt);
            scanner.next();
        }
        return scanner.nextDouble();
    }

    private static int getIntInput(Scanner scanner, String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid integer. " + prompt);
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static ComplexNumber getComplexInput(Scanner scanner, String name) {
        System.out.println("For " + name + ":");
        double r = getDoubleInput(scanner, "  Real part: ");
        double i = getDoubleInput(scanner, "  Imaginary part: ");
        return new ComplexNumber(r, i);
    }

    private static Matrix getMatrixInput(Scanner scanner, int rows, int cols, String name) {
        System.out.println("Enter data for " + name + " (" + rows + "x" + cols + "):");
        double[][] data = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i][j] = getDoubleInput(scanner, "  Value at [" + i + "][" + j + "]: ");
            }
        }
        return new Matrix(data);
    }
}

// --- Helper Classes ---

class ComplexNumber {
    private final double real, imaginary;

    public ComplexNumber(double r, double i) { this.real = r; this.imaginary = i; }

    public ComplexNumber add(ComplexNumber o) { return new ComplexNumber(real + o.real, imaginary + o.imaginary); }
    public ComplexNumber subtract(ComplexNumber o) { return new ComplexNumber(real - o.real, imaginary - o.imaginary); }
    
    public ComplexNumber multiply(ComplexNumber o) {
        return new ComplexNumber(real * o.real - imaginary * o.imaginary, real * o.imaginary + imaginary * o.real);
    }

    public ComplexNumber divide(ComplexNumber o) {
        double den = o.real * o.real + o.imaginary * o.imaginary;
        if (den == 0) return new ComplexNumber(Double.NaN, Double.NaN);
        return new ComplexNumber((real * o.real + imaginary * o.imaginary) / den, 
                                 (imaginary * o.real - real * o.imaginary) / den);
    }
    public double magnitude() { return Math.sqrt(real * real + imaginary * imaginary); }
    public String toString() { return String.format("%.2f + %.2fi", real, imaginary); }
}

class Matrix {
    private final double[][] data;
    private final int rows, cols;

    public Matrix(double[][] d) { 
        this.data = d; this.rows = d.length; this.cols = d[0].length; 
    }

    public Matrix add(Matrix o) {
        if (rows != o.rows || cols != o.cols) return null;
        double[][] res = new double[rows][cols];
        for (int i = 0; i < rows; i++) 
            for (int j = 0; j < cols; j++) res[i][j] = data[i][j] + o.data[i][j];
        return new Matrix(res);
    }

    public Matrix multiply(Matrix o) {
        if (cols != o.rows) return null;
        double[][] res = new double[rows][o.cols];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < o.cols; j++)
                for (int k = 0; k < cols; k++) res[i][j] += data[i][k] * o.data[k][j];
        return new Matrix(res);
    }

    public double[] solve2x2System(double[] rhs) {
        if (rows != 2 || cols != 2) throw new IllegalArgumentException("Not a 2x2 matrix");
        double det = data[0][0] * data[1][1] - data[0][1] * data[1][0];
        if (Math.abs(det) < 1e-9) throw new IllegalArgumentException("No unique solution (det=0)");
        double detX = rhs[0] * data[1][1] - rhs[1] * data[0][1];
        double detY = data[0][0] * rhs[1] - data[1][0] * rhs[0];
        return new double[]{detX / det, detY / det};
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (double[] row : data) {
            sb.append("| ");
            for (double val : row) sb.append(String.format("%.2f ", val));
            sb.append("|\n");
        }
        return sb.toString();
    }
}

class Fraction {
    private final int num, den;
    public Fraction(int n, int d) {
        if (d == 0) throw new IllegalArgumentException("Denom is 0");
        int g = gcd(Math.abs(n), Math.abs(d));
        this.num = (d < 0 ? -n : n) / g;
        this.den = Math.abs(d) / g;
    }
    private static int gcd(int a, int b) { return b == 0 ? a : gcd(b, a % b); }
    public static Fraction fromDouble(double v) {
        if (Double.isInfinite(v) || Double.isNaN(v)) return new Fraction(0, 1); // Fallback
        int den = 1;
        while (Math.abs(v * den - Math.round(v * den)) > 1e-6 && den < 10000) den++;
        return new Fraction((int)Math.round(v * den), den);
    }
    public String toString() { return den == 1 ? String.valueOf(num) : num + "/" + den; }
}