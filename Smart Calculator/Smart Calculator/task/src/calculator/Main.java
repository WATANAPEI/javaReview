package calculator;

import java.io.StringReader;
import java.text.ParseException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

// BNF
// <Expr> := <Number> | (<RHS>)*
// <RHS> := <OperatorList> <Number>
// <OperatorList> := <Operator>*
// <Operator> := + | -

abstract class Node {
    abstract void parse(Context context) throws ParseException;
}

class ExprNode extends Node {
    // BNF
    // <Expr> := <Number> | (<RHS>)*
    Integer number = 0;
    List<Node> rhs = new ArrayList<>();
    public void parse(Context context) throws ParseException {
        try {
            number = context.getCurrentNumber();
        } catch (ParseException e) {
            throw new ParseException("Error: " + e, 0);
        }
        while(context.nextToken() != null) {
            Node rhsNode = new RhsNode();
            rhsNode.parse(context);
            rhs.add(rhsNode);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(number);
        for(var e: rhs) {
            sb.append(" " + e + " ");
        }
        return sb.toString();

    }
}

class RhsNode extends Node {
    // BNF
    // <RHS> := <OperatorList> <Number>
    Node operatorList;
    Integer number;
    void parse(Context context) {
        operatorList = new OperatorNode();
        try {
            operatorList.parse(context);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        context.nextToken();
        try {
            number = context.getCurrentNumber();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        return operatorList.toString() + " " + number.toString();
    }

}

class OperatorNode extends Node{
    // BNF
    // <Operator> := <Operator>*
    // <OperatorList> := + | -
    char operator = ' ';
    void parse(Context context) throws ParseException {
        char operator = '+';
        String token = context.getCurrentToken();
        for(var e: token.split("")) {
            if(e.contentEquals("+")) {
                operator = '+';
            } else if (e.contentEquals("-")) {
                if(operator == '-') {
                    operator = '+';
                }else {
                    operator = '-';
                }
            } else {
                new ParseException("Parse Error at OperatorNode", 0);
            }
        }
    }

    public String toString() {
        return String.valueOf(operator);
    }

}

class Context {
    private Scanner sc;
    private String currentToken;

    public Context(String text) {
        sc = new Scanner(text);
        currentToken = nextToken();
    }

    public String nextToken() {
        if (sc.hasNext()) {
            return sc.next();
        } else {
            return null;
        }
    }

    public void skipToken(String expectedToken) throws ParseException {
        if (!currentToken.contentEquals(expectedToken)) {
            throw new ParseException("Unexpected Token: " + currentToken, 0);
        }
        nextToken();
    }

    public String getCurrentToken() {
        return this.currentToken;
    }

    public Integer getCurrentNumber() throws  ParseException{
        Integer number = 0;
        try {
            number = Integer.parseInt(this.currentToken);
        } catch(NumberFormatException e) {
            throw new ParseException("Error: " + e, 0);
        }
        return number;
    }
}

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //read(scan) separate numbers with operators(by space)
        //aggregate operators to one operator use if statement for -
        while(scanner.hasNext()) {
            String line = scanner.nextLine();
            if(line.contentEquals("/help")) {
                help();
            } else if (line.contentEquals("/exit")) {
                exit();
                return; //terminate program here
            } else {
                Node node = new ExprNode();
                try {
                    node.parse(new Context(line));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void help() {
        System.out.println(
                        "The program must calculate expressions like these: 4 + 6 - 8, 2 - 3 - 4," +
                        " and so on. It must support both unary and binary minus operators." +
                        " If the user has entered several operators following each other," +
                                " the program still should work (like Java or Python REPL).\n" +
                        "\n" +
                        "Consider that the even number of minuses gives a plus," +
                                " and the odd number of minuses gives a minus!" +
                                " Look at it this way: 2 -- 2 equals 2 - (-2) equals 2 + 2."
        );
    }

    public static void exit() {
        System.out.println("Bye!");
    }
}
