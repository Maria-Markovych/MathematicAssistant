package org.example;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.Scanner;
import java.sql.*;

public class Assistant {
    private static final double EPSILON = 1e-9;
    private final String url = "jdbc:mysql://localhost:3307/assistant";
    private final String user = "root";
    private final String password = "password";

    public static void main(String[] args) {
        Assistant main = new Assistant();
        double xValue;
        String equation;
        while (true) {
            equation = main.inputEquation();
            boolean validEquation = verifyValidEquation(equation);
            if (validEquation) {
                xValue = main.inputXValue();
                break;
            }
        }
        while(true){
            if (checkIfRoot(equation, xValue) != 1){
                main.AddDataIntoMysql(equation, xValue);
                break;
            }
            System.out.println("Wrong root of the equation.");
            xValue = main.inputXValue();
        }
    }

        static double checkIfRoot(String equation, double xValue) {
        String substitutedEquation = equation.replaceAll("x", Double.toString(xValue));
        String[] s = substitutedEquation.split("=");
        String resultEquation = s[0] + "-" + "(" + s[1] + ")";
        double endResult = 1;
        try {
            double result = evaluateExpression(resultEquation);
            if (Math.abs(result) <= EPSILON) {
                endResult = result;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return endResult;
    }

    private static double evaluateExpression(String expression) {
        Expression e = new ExpressionBuilder(expression).build();
        return e.evaluate();
    }

    private String inputEquation() {
        System.out.println("You can type char 'x' and any mathematics symbols");
        System.out.println("Please type Mathematical equation:");
        Scanner equation = new Scanner(System.in);
        return equation.nextLine();
    }

    private double inputXValue() {
        System.out.println("Please type root of the equation");
        Scanner xValue = new Scanner(System.in);
        return xValue.nextDouble();
    }

    static boolean verifyValidEquation(String equation) {
        if (!isValidInput(equation) || !twoMathSignsClose(equation) || !invalidBrackets(equation)) {
            System.out.println("Invalid data. Please type the correct data");
            return false;
        } else {
            System.out.println("Equation is accepted");
            return true;
        }
    }

    static boolean invalidBrackets(String equation) {
        boolean res = true;
        int countOpenBracket = 0;
        int countCloseBracket = 0;
        for (int i = 0; i < equation.length(); i++) {
            char currentChar = equation.charAt(i);
            if (currentChar == '(') {
                countOpenBracket++;
            }
            if (currentChar == ')') {
                countCloseBracket++;
            }
        }
        if (countCloseBracket != countOpenBracket) {
            res = false;
        }
        return res;
    }

    static boolean twoMathSignsClose(String equation) {
        boolean res = true;
        for (int i = 0; i < equation.length() - 1; i++) {
            char currentChar = equation.charAt(i);
            char nextChar = equation.charAt(i + 1);
            if (currentChar == '+' && nextChar == '-' || currentChar == '+' && nextChar == '/' || currentChar == '-' && nextChar == '/'
                    || currentChar == '+' && nextChar == '*' || currentChar == '-' && nextChar == '*' || currentChar == '-' && nextChar == '+'
                    || currentChar == '/' && nextChar == '*' || currentChar == '*' && nextChar == '/' || currentChar == '(' && nextChar == ')'
                    || currentChar == '+' && nextChar == '+' || currentChar == '-' && nextChar == '-' || currentChar == '/' && nextChar == '/'
                    || currentChar == '*' && nextChar == '*') {
                res = false;
                break;
            }
        }
        return res;
    }

    static boolean isValidInput(String inputEquation) {

        return inputEquation.matches("[x0-9+=.\\-*/\\s()]+");
    }

    private void AddDataIntoMysql(String equation, double xValue) {
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            Statement stmt = conn.createStatement();
            String insertQuery = "INSERT INTO mathemetical_data(equation, xValue) VALUES ('" + equation + "'," + xValue + ");";
            stmt.executeUpdate(insertQuery);
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void SelectDataFromMysql(double xValue) {
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            Statement stmt = conn.createStatement();
            String selectQuery = "SELECT * FROM mathemetical_data WHERE xValue = " + xValue + ";";
            ResultSet rs = stmt.executeQuery(selectQuery);
            while (rs.next()) {
                System.out.println(rs.getString("equation") + rs.getDouble("xValue"));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}