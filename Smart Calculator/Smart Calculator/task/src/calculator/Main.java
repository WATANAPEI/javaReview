package calculator;

import java.text.ParseException;
import java.util.Scanner;

enum TokenType {
    NUM,
    OP;
}
enum OpType {
    ADD,
    SUB;
}
/**
 * Token
 * Token type should be String(Operator) or Integer(Number)
 */
abstract class Token {
    TokenType type;
}

class NumToken extends Token {
    private Integer value;
    private String image;
    NumToken(Integer value) {
        this.value = value;
        this.type = TokenType.NUM;
        this.image = value.toString();
    }
    public Integer getValue() {
        return this.value;
    }
    public String toString() {
        return this.image;
    }
}

class OpToken extends Token {
    private OpType translatedOp;
    private String image;

    OpToken(String value) {
        this.type = TokenType.OP;
        this.image = value;
        this.translatedOp = translate(value);
    }
    OpType translate(String value) {
        OpType operator = OpType.ADD;
        for(var e: value.split("")) {
            if(e.contentEquals("+")) {
                operator = OpType.ADD;
            } else if (e.contentEquals("-")) {
                if(operator == OpType.SUB) {
                    operator = OpType.ADD;
                }else {
                    operator = OpType.SUB;
                }
            } else {
                new ParseException("Parse Error at OperatorNode", 0);
            }
        }
        return operator;
    }
    OpType getValue() {
        return this.translatedOp;
    }

    public String toString() {
        return this.image;
    }
}
class Lexer {
    private Scanner sc;

    Lexer(String line) {
        sc = new Scanner(line);
    }
    Token nextToken() {
        if(sc.hasNext()) {
            String nextStr = sc.next();
            if(nextStr.matches("\\d+")) {
                return new NumToken(Integer.parseInt(nextStr));
            } else {
                return new OpToken(nextStr);
            }
        } else {
            return  null;
        }
    }

}

abstract class AST {
    abstract Integer accept(NodeVisitor v);
}

class BinOpNode extends AST {
    AST left;
    OpType op; //this node
    AST right;

    BinOpNode(AST left, OpType op, AST right) {
        this.left = left;
        this.op = op;
        this.right = right;
    }

    @Override
    Integer accept(NodeVisitor v) {
        return v.visit(this);
    }
}

class NumNode extends AST {
    Integer value;
    NumNode(Integer value) {
        this.value = value;
    }

    @Override
    Integer accept(NodeVisitor v) {
        return v.visit(this);
    }

    public Integer getValue() {
        return value;
    }
}

// BNF
// <Expr> := <Number> | (<Op> <Number>)*
// <Op> := (+ | -)*

class Parser {
    Lexer lexer;
    Token currentToken;
    Parser(Lexer lexer) {
        this.lexer = lexer;
        this.currentToken = lexer.nextToken();
    }

    /**
     * consume
     * @param tokenType
     * @throws ParseException
     * advance current token point only when token type is right
     */
    void consume(TokenType tokenType) throws ParseException{
        if(currentToken.type == tokenType) {
            currentToken = lexer.nextToken();
        } else {
            throw new ParseException("Invalid expression", 0);
        }
    }

    NumNode parseNum() throws ParseException{
        Token token = currentToken;
        try {
            consume(TokenType.NUM);
        } catch (ParseException e) {
            throw e;
        }
        Integer i = ((NumToken)token).getValue();
        return new NumNode(i);
    }

    AST parseExpr() throws ParseException{
        AST node = parseNum();
        while(currentToken.type == TokenType.OP) {
            Token opToken = currentToken;
            try {
                consume(TokenType.OP);
            } catch (ParseException e) {
                throw e;
            }
            node = new BinOpNode(node, ((OpToken)opToken).getValue(), parseNum());
            if(currentToken == null) {
                break;
            }
        }
        return node;
    }

    AST parse() throws ParseException{
        return parseExpr();
    }
}

/**
 * NodeVisitor
 *
 */
interface NodeVisitor {
    Integer visit(BinOpNode node);
    Integer visit(NumNode node);

}

class Interpreter implements NodeVisitor {
    Parser parser;
    Interpreter(Parser parser) {
        this.parser  = parser;
    }

    public Integer visit(BinOpNode node) {
        if(node.op == OpType.ADD) {
            return node.left.accept(this) + node.right.accept(this);
        } else {
            return node.left.accept(this) - node.right.accept(this);
        }
    }

    public Integer visit(NumNode node) {
        return node.getValue();
    }

    Integer interpret() throws ParseException{
        AST tree = this.parser.parse();
        return tree.accept(this);

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
                break; //terminate program here
            } else if(line.contains("/") && !line.contentEquals("/exit") && !line.contentEquals("/help")) {
                unknown();
            } else {
                Lexer lexer = new Lexer(line); // generate tokens
                Parser parser = new Parser(lexer); // generate AST
                Interpreter interpreter = new Interpreter(parser);
                try {
                    System.out.println(interpreter.interpret());
                } catch(ParseException e) {
                    System.out.println(e.getMessage());
                }
                //System.out.println(node.toString());
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
    public static void unknown() {
        System.out.println("Unknown command");
    }
}
