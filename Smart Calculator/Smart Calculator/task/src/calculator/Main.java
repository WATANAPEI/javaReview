package calculator;

import java.text.ParseException;
import java.util.Scanner;

// BNF
// <Expr> := <Number> ( <Operator> <Number> )*
// <Operator> := <Operator>* | + | -

interface Node {
    public void parse(Context context) throws ParseException;
}

class ExprNode implements Node {
    // BNF
    // <Expr> := <Number> ( <Operator> <Number> )*
    public void parse(Context context) throws ParseException {
        Integer number;
        try {
            number = context.getCurrentNumber();
        } catch (ParseException e) {
            throw new ParseException("Error: " + e, 0);
        }


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
                Node node = new CalculateNode();
                node.parse(new Context(line));
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
