import java.io.*;
import java.util.Scanner;
import java.util.Stack;

public class Q2 {

    public static void main(String[] args) {
        
        String inputFile = "input.txt";
        String outputFile = "output.txt";

        try {
            
            FileReader fileReader = new FileReader(inputFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            FileWriter fileWriter = new FileWriter(outputFile);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                
                String result = evaluateExpression(line);
                bufferedWriter.write(result);
                bufferedWriter.newLine();
            }

            
            bufferedReader.close();
            bufferedWriter.close();

            System.out.println("Expressions solved and results written to output file.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    private static String evaluateExpression(String expression) {
        try {
            
            expression = expression.replaceAll("\\s", "");

        
            Stack<Integer> stack = new Stack<>();
            for (int i = 0; i < expression.length(); i++) {
                char ch = expression.charAt(i);
                if (Character.isDigit(ch)) {
                    
                    stack.push(ch - '0');
                } else {
                    
                    int operand2 = stack.pop();
                    int operand1 = stack.pop();
                    int result = evaluate(operand1, operand2, ch);
                    stack.push(result);
                }
            }

           
            return Integer.toString(stack.pop());

        } catch (Exception e) {
            
            return "Error evaluating expression: " + expression;
        }
    }

    
    private static int evaluate(int operand1, int operand2, char operator) {
        switch (operator) {
            case '+':
                return operand1 + operand2;
            case '-':
                return operand1 - operand2;
            case '*':
                return operand1 * operand2;
            case '/':
                if (operand2 == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                return operand1 / operand2;
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }
}