package calculator;

import java.text.ParseException;
import java.util.Scanner;

enum TokenType {
    NUM,
    OP,
    ID,
    ASSIGN;
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
    String image;

    public String toString() {
        return image;
    }
}

class NumToken extends Token {
    private Integer value;
    NumToken(Integer value) {
        this.value = value;
        this.type = TokenType.NUM;
        this.image = value.toString();
    }
    public Integer getValue() {
        return this.value;
    }
}

class OpToken extends Token {
    private OpType translatedOp;

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

}

class IdToken extends Token {

    public IdToken(String image) {
        this.image = image;
        this.type = TokenType.ID;
    }

}

class AssignToken extends Token {

    public AssignToken(String image) {
        this.image = image;
        this.type = TokenType.ASSIGN;
    }

}

/**
 * Lexer
 */
class Lexer {
    private Scanner sc;

    Lexer(String line) {
        sc = new Scanner(line);
    }
    Token nextToken() {
        if (sc.hasNext()) {
            String nextStr = sc.next();
            if (nextStr.matches("[\\+-]?\\d+")) {
                return new NumToken(Integer.parseInt(nextStr));
            } else if (nextStr.matches("\\w+")) {
                return new IdToken(nextStr);
            } else if (nextStr.matches("=")) {
                return new AssignToken(nextStr);
            } else {
                return new OpToken(nextStr);
            }
        } else {
            return null;
        }
    }

}

/**
 * Node classes
 */

abstract class Node {
    public Node() {}

    abstract Integer accept(NodeVisitor v);
}

abstract class ExprNode extends Node_ {
    ExprNode() {
        super();
    }

    @Override
    Integer accept(NodeVisitor v) {
        return v.visit(this);
    }
}

class UnaryNode extends ExprNode {
    PrimaryNode node;
    String uniOp;

    UnaryNode(PrimaryNode node, String uniOp) {
        super();
        this.node = node;
        this.uniOp = uniOp;
    }

    UnaryNode(PrimaryNode node) {
        new UnaryNode(node, "");
    }

    @Override
    Integer accept(NodeVisitor v) {
        return v.visit(this);

    }
}

class PrimaryNode extends ExprNode {
    ExprNode node;
    PrimaryNode(ExprNode node) {
        super();
        this.node = node;
    }

    @Override
    Integer accept(NodeVisitor v) {
        return v.visit(this);
    }
}


class BinOpNode extends ExprNode {

    ExprNode left;
    OpType op;
    ExprNode right;

    BinOpNode(ExprNode left, OpType op, ExprNode right) {
        super();
        this.left = left;
        this.op = op;
        this.right = right;
    }

    @Override
    Integer accept(NodeVisitor v) {
        return v.visit(this);
    }
}

class NumNode extends ExprNode {
    Integer value;
    NumNode(Integer value) {
        super();
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

class VariableNode extends ExprNode {
    ExprNode node;

    public VariableNode(ExprNode node) {
        super();
        this.node = node;
    }

    @Override
    Integer accept(NodeVisitor v) {
        return v.visit(this);
    }

}

class AssignNode extends ExprNode {
    ExprNode lhs;
    AssignNode assignNode;
    ExprNode rhs;

    AssignNode(ExprNode lhs, AssignNode assignNode, ExprNode rhs) {
        super();
        this.lhs = lhs;
        this.assignNode = assignNode;
        this.rhs = rhs;
    }

    @Override
    Integer accept(NodeVisitor v) {
        return v.visit(this);
    }
}

/**
 * Parser class
 * BNF
 * <Expr> := <Unary> | <BinOp> | <AssignOp>
 * <BinOP> := <Unary> (<op> <Unary>)+
 * <AssignOp> :=  <Variable> <assign> <Expr>
 * <Unary> := +<Primary> | -<Primary> | <Primary>
 * <Primary> := <number> | <Variable>
 * <Variable> := <number>
 * <op> := (+ | -)*
 * <assign> := "="
 */

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

    VariableNode parseVariable() throws ParseException {
        return new VariableNode()

    }

    AssignNode parseAssignOp() throws ParseException {
        VariableNode node = parseVariable();
    }

    ExprNode parseExpr2() throws ParseException {
        Token token = currentToken;
        if(token.type == TokenType.ID) {
            parseAssignOp();
        }
    }

    ExprNode parseExpr() throws ParseException{
        ExprNode node = parseNum();
        while(currentToken != null) {
            Token opToken;
            if(currentToken.type == TokenType.OP) {
                opToken = currentToken;
                try {
                    consume(TokenType.OP);
                } catch (ParseException e) {
                    throw e;
                }
            } else if(currentToken.type == TokenType.ID) {

            } else {
                throw new ParseException("Invalid expression", 0);
            }
            node = new BinOpNode(node, ((OpToken)opToken).getValue(), parseNum());
        }
        return node;
    }

    ExprNode parse() throws ParseException{
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
        ExprNode tree = this.parser.parse();
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
            } else if(line.contentEquals("")) {
                ;
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
