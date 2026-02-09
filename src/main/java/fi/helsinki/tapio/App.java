package fi.helsinki.tapio;

import fi.helsinki.tapio.tools.ReversePolishNotationCalculator;
import fi.helsinki.tapio.tools.ShuntingYardTool;

import java.util.HashMap;

/**
 * A simple app to take either argument or user input and calculate result
 *
 */
public class App {

    private static final HashMap<String, Double> variables = new HashMap<>();

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
            System.out.println("ERROR in input");
            return;
        }
        try {
            calculate(calculationString);
        } catch (RuntimeException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private static Double calculate(String calculationString) throws RuntimeException {
        if (calculationString == null || calculationString.isEmpty()) {
            throw new RuntimeException("ERROR in input");
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
            System.out.println("Result: " + result);
            System.out.println();
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

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
                    System.out.println("Remember to enter calculation in form of RPN (Reverse Polish Notation) like '1 + 2 + MIN ( 1 , 2 )'");
                    System.out.println("Enter calculation: ");
                    calculationString = System.console().readLine();
                    calculate(calculationString);
                } else if (command.equals("2")) {
                    System.out.println("Enter variable name (a-z): ");
                    String varName = System.console().readLine();
                    System.out.println("Enter variable value: ");
                    String varValue = System.console().readLine();
                    if (varName == null || varValue == null || !varName.matches("[a-z]")) {
                        System.out.println("ERROR in input");
                        return;
                    }
                    variables.put(varName, Double.parseDouble(varValue));
                } else if (command.equals("3")) {
                    System.out.println("Enter variable name (a-z): ");
                    String varName = System.console().readLine();
                    System.out.println("Remember to enter calculation in form of RPN (Reverse Polish Notation) like '1 + 2 + MIN ( 1 , 2 )'");
                    System.out.println("Enter calculation: ");
                    String calculation = System.console().readLine();
                    if (varName == null || calculation == null || !varName.matches("[a-z]")) {
                        System.out.println("ERROR in input");
                        return;
                    }
                    variables.put(varName, calculate(calculation));
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
