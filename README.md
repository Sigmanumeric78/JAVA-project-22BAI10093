Ultimate Scientific Calculator

>>Overview of the Project

The Ultimate Scientific Calculator is a robust, Java-based Command Line Interface (CLI) application designed to handle complex mathematical computations. Unlike standard calculators that are limited to basic arithmetic, this project integrates advanced functionalities required by engineering and science students, such as matrix algebra, complex number arithmetic, and solving systems of linear equations. It serves as a lightweight, portable alternative to heavy mathematical software like MATLAB for quick, everyday calculations.

>>Features

:>> Basic & Scientific Arithmetic: Supports standard operators (+, -, *, /) along with scientific functions like Power, Square Root, Logarithms (natural & base 10), and Trigonometry (sin, cos, tan).

:>> Smart Output: Automatically converts decimal results into simplified fractions (e.g., 0.5 becomes 1/2).

:>> Complex Number Operations: Performs addition, subtraction, multiplication, division, and magnitude calculations for complex numbers ($a + bi$).

:>> Matrix Operations: Supports Matrix Addition and Multiplication with dynamic dimension sizing and automatic dimension validation.

:>> Linear System Solver: Solves 2x2 systems of linear equations ($ax+by=e$, $cx+dy=f$) using Cramer's Rule.

:>> Robust Error Handling: gracefully handles errors like division by zero, negative square roots, and matrix dimension mismatches without crashing.

>>Technologies/Tools Used

Language: Java (JDK 8 or higher)

Libraries: Standard Java Library (java.util.Scanner, java.lang.Math)

IDE/Tools: Any Java-compatible IDE (VS Code, IntelliJ, Eclipse) or simple Terminal/Command Prompt.

Version Control: Git

**Steps to Install & Run the Project**

Prerequisites: Ensure you have Java installed. You can check by running java -version in your terminal.

Clone/Download: Download the ScientificCalculator.java file or clone this repository.

>>Compile:
Open your terminal/command prompt, navigate to the folder containing the file, and run:

javac ScientificCalculator.java


**Run:**
Execute the program using:

java ScientificCalculator


**Instructions for Testing**

Once the application is running, follow the on-screen menu. Here are a few test scenarios:

Test Arithmetic: Select Option 1. Enter sin, then 90 (result depends on radian/degree logic, strictly math inputs apply). Try 10 / 2 to see the fraction output.

Test Complex Numbers: Select Option 2, then Multiply. Enter 1 + 1i and 2 + 2i. Expected result: 0.00 + 4.00i.

Test Linear Solver: Select Option 4. Enter coefficients for equations:

$1x + 1y = 5$

$2x - 1y = 1$

Result: x = 2.00, y = 3.00

**Screenshots**

<img width="848" height="717" alt="Screenshot 2025-11-25 032502" src="https://github.com/user-attachments/assets/cbf6eea1-9134-45b7-80cb-77d3cb1749fc" />


