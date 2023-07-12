package composite;

import java.util.ArrayList;
import java.util.List;

public class Expression extends Parser
{

    String input;
    Number numLeft = null;
    Number numRight = null;
    Operation op = null;
    Expression expLeft = null;
    Expression expRight = null;
    double runningTotal = 0;
 
    Expression(String input) {

        this.input = input;
        parse();

    }

    public void parse() {

        String leftSide = "";
        String rightSide = "";
        boolean leftIsExp = false;
        boolean rightIsExp = false;
        List<String> rightReturn;

        // + or - in String
        for (int i = 0; i < input.length(); i++) {

            char nextChar = input.charAt(i);
 
            // Stop scanning if addition or subtraction
            if (nextChar == '+' || nextChar == '-') {

                // Store operation in Operation class
                op = new Operation(nextChar);

                // Get remaining string
                rightReturn = getRightParse(input, i+1);
                rightSide = rightReturn.get(0);
                rightIsExp = rightReturn.get(1).equals("true");

                // Both sides are expressions
                if (leftIsExp && rightIsExp) {
                    expLeft = new Expression(leftSide);
                    expRight = new Expression(rightSide);
                }
                // Left side expression, right side number
                else if (leftIsExp) {
                    expLeft = new Expression(leftSide);
                    numRight = new Number(rightSide);
                }
                // Right side expression, left side number
                else if (rightIsExp) {
                    expRight = new Expression(rightSide);
                    numLeft = new Number(leftSide);
                }
                // Both sides numbers
                else {
                    numLeft = new Number(leftSide);
                    numRight = new Number(rightSide);
                }

                return;

            }
            else if(nextChar == '*' || nextChar == '/') {
                leftIsExp = true;
                leftSide += nextChar;
            }
            else {leftSide += nextChar;}
        }

        leftSide = "";
        rightSide = "";
        int lastPos = 0;
        int opCount = 0;

        // Find last * or / in String
        for (int i = 0; i < input.length(); i++) {
            char nextChar = input.charAt(i);

            if (nextChar == '*' || nextChar == '/') {
                lastPos = i;
                opCount += 1;
            }
        }

        // Loop until lastPos
        for (int i = 0; i < input.length(); i++) {

            char nextChar = input.charAt(i);

            if (i == lastPos) {

                // Store operation in Operation class
                op = new Operation(nextChar);

                // Get remaining string
                rightReturn = getRightParse(input, i+1);
                rightSide = rightReturn.get(0);

                // Right is always number
                numRight = new Number(rightSide);

                // Left is expression
                if (opCount > 1) {
                    expLeft = new Expression(leftSide);
                }
                else {
                    numLeft = new Number(leftSide);
                }

                return;

            }
            else {
                leftSide += nextChar;
            }
        }

        leftSide = "";

        // Only number to parse
        for (int i = 0; i < input.length(); i++) {

            char nextChar = input.charAt(i);

            leftSide += nextChar;
        }

        numLeft = new Number(leftSide);

    }

    public List<String> getRightParse(String remaining, int pos) {

        String rightSide = "";
        String rightIsExp = "false";
        List<String> returnList = new ArrayList<String>();
        
        for (int i = pos; i < remaining.length(); i++) {

            char nextChar = remaining.charAt(i);
 
            if (nextChar == '+' || nextChar == '-' || nextChar == '*' || nextChar == '/') {

                rightIsExp = "true";
                rightSide += nextChar;

            }
            else {rightSide += nextChar;}
        }

        returnList.add(rightSide);
        returnList.add(rightIsExp);

        return returnList;
        
    }

    public double eval() {

        double leftTotal = 0.0;
        double rightTotal = 0.0;

        if (this.numLeft != null) {
            leftTotal = this.numLeft.value;
        }

        if (this.numRight != null) {
            rightTotal = this.numRight.value;
        }

        if (this.expLeft != null) {
            leftTotal = expLeft.eval();
        }

        if (this.expRight != null) {
            rightTotal = expRight.eval();
        }

        if (this.op.type == '+') {
            runningTotal = leftTotal + rightTotal;
            System.out.println(leftTotal + " + " + rightTotal);
        }
        else if (this.op.type == '-') {
            runningTotal = leftTotal - rightTotal;
            System.out.println(leftTotal + " - " + rightTotal);
        }
        else if (this.op.type == '*') {
            runningTotal = leftTotal * rightTotal;
            System.out.println(leftTotal + " * " + rightTotal);
        }
        else {
            runningTotal = leftTotal / rightTotal;
            System.out.println(leftTotal + " / " + rightTotal);
        }

        return runningTotal;

    }
}
