package fi.helsinki.tapio;

import fi.helsinki.tapio.tools.CommonTools;
import fi.helsinki.tapio.tools.ReversePolishNotationCalculator;
import fi.helsinki.tapio.tools.ShuntingYardTool;

import java.util.HashMap;

/**
 * The App class implements a command-line tool for performing mathematical calculations
 * using the Shunting Yard algorithm and Reverse Polish Notation (RPN) calculator. It also
 * provides functionality for defining and updating variables, as well as an interactive
 * user interface.
 */
public class App {

    private static final HashMap<String, Double> variables = new HashMap<>();

    /**
     * The entry point for the application.
     */
    public static void main( String[] args ) {
        String calculationString = null;

        if (args.length < 1) {
            App app = new App();
            app.run();
            return;
        } else {
            calculationString = args[0];
        }
        if (calculationString == null || calculationString.isEmpty()) {
            System.out.println("Invalid input");
            return;
        }
        try {
            calculate(calculationString);
        } catch (RuntimeException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    /**
     * Calculates the result of a mathematical expression provided as a string using the Shunting Yard
     * algorithm and Reverse Polish Notation (RPN) calculator.
     *
     * @param calculationString the mathematical expression to calculate, represented as a string.
     *                          Can include numbers, operators, and variables.
     * @return the calculated result of the mathematical expression as a Double value.
     * @throws RuntimeException if the input is null, empty, invalid, or contains unresolved variables.
     */
    protected static Double calculate(String calculationString) throws RuntimeException {
        if (calculationString == null || calculationString.isEmpty()) {
            throw new RuntimeException("Invalid input");
        }

        // If calculation is just a number, return it
        if (CommonTools.isNumber(calculationString)) {
            double result = Double.parseDouble(calculationString);
            System.out.println("Result: " + result + "\n");
            return result;
        }

        if (!validateUserInputForCommonTypoes(calculationString)) {
            throw new RuntimeException("Invalid input: " + calculationString);
        }

        if (!variables.isEmpty()) {
            String[] tokens = calculationString.split(" ");

            // Replace variables with their values
            for (String var : variables.keySet()) {
                for (int i = 0; i < tokens.length; i++) {
                    if (tokens[i].equals(var)) {
                        tokens[i] = String.valueOf(variables.get(var));
                    }
                }
            }

            // If unset variables are left throw exception
            for (int i = 0; i < tokens.length; i++) {
                if (tokens[i].matches("[a-z]")) {
                    throw new RuntimeException("Variable " + tokens[i] + " is not set");
                }
            }
            // Reconstruct calculation string
            calculationString = String.join(" ", tokens);
        }

        ShuntingYardTool syTool = new ShuntingYardTool();
        ReversePolishNotationCalculator rpnCalc = new ReversePolishNotationCalculator();
        try {
            String syOutput = syTool.calculateOutputStack(calculationString);
            double result = rpnCalc.calculateFromRPNString(syOutput);
            System.out.println("Result: " + result + "\n");
            return result;
        } catch (IllegalArgumentException e) {
            // RPN string is invalid, for user return "Invalid input""
            throw new RuntimeException("Invalid input: " + calculationString + ". " + e.getMessage());
        }
    }

    /**
     * Adds a new variable or updates the value of an existing variable in the collection.
     *
     * @param variableName  the name of the variable to add or update
     * @param variableValue the value to assign to the variable
     */
    protected static void addOrChangeVariable(String variableName, Double variableValue) {
        variables.put(variableName, variableValue);
    }

    protected static boolean validateUserInputForCommonTypoes(String calculationString) {
        if (calculationString == null || calculationString.isEmpty()) {
            return false;
        }
        // Check that there is not two operators in a row
        String noSpacesCalculationString = calculationString.replaceAll(" ", "");
        if (noSpacesCalculationString.contains("++") ||
                noSpacesCalculationString.contains("--") ||
                noSpacesCalculationString.contains("-+") ||
                noSpacesCalculationString.contains("**") ||
                noSpacesCalculationString.contains("^^") ||
                noSpacesCalculationString.contains("^*") ||
                noSpacesCalculationString.contains("*^") ||
                noSpacesCalculationString.contains("-*") ||
                noSpacesCalculationString.contains("+*")) {
            return false;
        }

        return true;
    }

    /**
     * The main program user interface.
     */
    public void run() {
        String calculationString = null;
        boolean running = true;

        while (running) {
            try {
                // Print manu and ask user for input
                System.out.println("Shunting Yard Calculator");
                System.out.println("=========================");
                System.out.println("Variables set so far:");
                for (String var : variables.keySet()) {
                    System.out.println(var + ": " + variables.get(var));
                }
                System.out.println();
                System.out.println("1. Enter calculation");
                System.out.println("2. Add/change variable");
                System.out.println("3. Add/change variable by calculation");
                System.out.println("4. Exit");
                System.out.println("Your wish? ");
                String command = System.console().readLine();

                if (command.equals("1")) {
                    System.out.println("Remember to enter calculation in form of like '1 + 2 + MIN ( 1 , 2 )'");
                    System.out.println("Enter calculation: ");
                    calculationString = System.console().readLine();
                    calculate(calculationString);
                } else if (command.equals("2")) {
                    System.out.println("Enter variable name (a-z): ");
                    String varName = System.console().readLine();
                    System.out.println("Enter variable value: ");
                    String varValue = System.console().readLine();
                    if (varName == null || !varName.matches("[a-z]") || !CommonTools.isNumber(varValue)) {
                        System.out.println("ERROR in input");
                        return;
                    }
                    addOrChangeVariable(varName, Double.parseDouble(varValue));
                } else if (command.equals("3")) {
                    System.out.println("Enter variable name (a-z): ");
                    String varName = System.console().readLine();
                    System.out.println("Remember to enter calculation in form of '1 + 2 + MIN ( 1 , 2 )'");
                    System.out.println("Enter calculation: ");
                    String calculation = System.console().readLine();
                    if (varName == null || calculation == null || !varName.matches("[a-z]")) {
                        System.out.println("ERROR in input");
                        return;
                    }
                    addOrChangeVariable(varName, calculate(calculation));
                } else if (command.equals("4")) {
                    System.out.println("Bye!");
                    running = false;
                }
            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
                System.out.println();
            }
        }
    }

}
