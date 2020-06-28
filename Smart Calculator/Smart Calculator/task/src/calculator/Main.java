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
enum AssignType {
    ASSIGN;
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
    private PeekableScanner sc;

    Lexer(String line) {
        sc = new PeekableScanner(line);
    }

    Token nextToken() {
        if (sc.hasNext()) {
            String nextStr = sc.next();
            return dispatchToken(nextStr);
        } else {
            return null;
        }
    }

    Token peekNextToken() {
        String nextTokenString = sc.peek();
        if(nextTokenString != null) {
            return dispatchToken(nextTokenString);
        } else {
            return null;
        }
    }

    Token dispatchToken(String tokenString) {
        if (tokenString.matches("[\\+-]?\\d+")) {
            return new NumToken(Integer.parseInt(tokenString));
        } else if (tokenString.matches("\\w+")) {
            return new IdToken(tokenString);
        } else if (tokenString.matches("=")) {
            return new AssignToken(tokenString);
        } else {
            return new OpToken(tokenString);
        }
    }
}

class PeekableScanner {
    private Scanner sc;
    private String next;

    public PeekableScanner(String line) {
        this.sc = new Scanner(line);
        this.next = (sc.hasNext()? sc.next(): null);
    }

    public boolean hasNext() {
        return next != null;
    }

    public String next() {
        String result = next;
        this.next = (sc.hasNext()? sc.next(): null);
        return result;
    }

    public String peek() {
        return next;
    }
}

/**
 * Node classes
 */

abstract class Node {
    public Node() {}
    abstract void accept(NodeVisitor v);
    abstract public String dump();
}

/**
 * ExprNode
 */
abstract class ExprNode extends Node {
    ExprNode() {
        super();
    }
}

/**
 * BinOpNode supports expression below:
 * a + 2, 1 - 2, a + b
 */
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
    void accept(NodeVisitor v) {
        v.visit(this);
    }

    @Override
    public String dump() {
        return String.format("%s %s %s\n", left.dump(), op.toString(), right.dump());
    }
}

class AssignNode extends ExprNode {
    ExprNode lhs;
    AssignType assignType;
    ExprNode rhs;

    AssignNode(ExprNode lhs, AssignType assignType, ExprNode rhs) {
        super();
        this.lhs = lhs;
        this.assignType = assignType;
        this.rhs = rhs;
    }

    @Override
    void accept(NodeVisitor v) {
        v.visit(this);
    }

    @Override
    public String dump() {
        return String.format("%s = %s", lhs, rhs);
    }
}

/**
 * UnaryNode supports expression below:
 * a, -2, +b
 */
class UnaryNode extends ExprNode {
    ExprNode node;
    String uniOp;

    UnaryNode(ExprNode node, String uniOp) {
        super();
        this.node = node;
        this.uniOp = uniOp;
    }

    UnaryNode(ExprNode node) {
        new UnaryNode(node, "");
    }

    @Override
    void accept(NodeVisitor v) {
        v.visit(this);
    }

    @Override
    public String dump() {
        return uniOp + node.dump();
    }
}

/**
 * PrimaryNode supports expression below:
 * 22, 1
 * NumNode only for now
 */

class PrimaryNode extends ExprNode {
    NumNode node;
    PrimaryNode(NumNode node) {
        super();
        this.node = node;
    }

    @Override
    void accept(NodeVisitor v) {
        v.visit(this);
    }

    @Override
    public String dump() {
        return node.dump();
    }
}


/**
 * NumNode supports expression like below:
 * 1, 23, -30
 */
class NumNode extends ExprNode {
    Integer value;
    NumNode(Integer value) {
        super();
        this.value = value;
    }

    @Override
    void accept(NodeVisitor v) {
        v.visit(this);
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public String dump() {
        return value.toString();
    }
}

/**
 * VariableNode supports expression as below:
 * a, bb
 * evaluate the value of reference node during construction
 */

class VariableNode extends ExprNode {
    NumNode node;
    String image;

    public VariableNode(NumNode node, String image) {
        this.node = node;
        this.image = image;
    }
    public VariableNode(String image) {
        this.image = image;
    }
    public void setNode(NumNode node) {
        this.node = node;
    }
    public Integer evaluate() {
        return ((NumNode)node).getValue();
    }

    @Override
    void accept(NodeVisitor v) {
        v.visit(this);
    }

}


/**
 * Parser class
 * EBNF
 * Expr = Unary | BinOp | AssignOp
 * BinOP = Unary, {Op, Primary}
 * AssignOp = Variable , "=", Unary
 * Unary = ["+" | "-" ], Primary | Variable
 * Primary = \\d+
 * Variable = \\w+
 * Op = "+" | "-" , {"+"}, {"-"}
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
        Token token = currentToken;
        try {
            consume(TokenType.ID);
        } catch(ParseException e) {
            throw e;
        }
        return new VariableNode(token.image);

    }

    PrimaryNode parsePrimary() throws ParseException {
        return new PrimaryNode(parseNum());
    }

    UnaryNode parseUnary() throws ParseException {
        Token token = currentToken;
        String sign = String.valueOf(currentToken.image.charAt(0));
        if(!sign.contentEquals("+") && !sign.contentEquals("-")) {
            sign = "";
        }
        if(currentToken.type == TokenType.NUM) {
            PrimaryNode node = parsePrimary();
            return new UnaryNode(node, sign);
        } else if (currentToken.type == TokenType.ID) {
            VariableNode node = parseVariable();
            return new UnaryNode(node, sign);
        } else {
            throw new ParseException("Invalid expression", 0);
        }
    }

    AssignNode parseAssignOp() throws ParseException {
        VariableNode lhs;
        try {
            lhs = parseVariable();
            consume(TokenType.ASSIGN);
        } catch (ParseException e) {
            throw e;
        }
        return new AssignNode(lhs, AssignType.ASSIGN, parseUnary());
    }

    BinOpNode parseBinOp() throws ParseException {
        UnaryNode lhs;
        OpToken opToken;
        try {
            lhs = parseUnary();
            opToken = (OpToken)currentToken;
            consume(TokenType.OP);
        } catch (ParseException e) {
            throw e;
        }
        return new BinOpNode(lhs, opToken.getValue(), parsePrimary());
    }

    ExprNode parseExpr() throws ParseException{
        if(lexer.peekNextToken() == null) {
            return parseUnary();
        } else if(lexer.peekNextToken().type == TokenType.ASSIGN) {
            return parseAssignOp();
        } else if(lexer.peekNextToken().type == TokenType.OP) {
            return parseBinOp();
        } else {
            throw new ParseException("Invalid expression", 0);
        }
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
    void visit(BinOpNode node);
    void visit(NumNode node);
    void visit(AssignNode node);

}

class Interpreter implements NodeVisitor {
    Parser parser;
    Interpreter(Parser parser) {
        this.parser  = parser;
    }

    public void visit(BinOpNode node) {
        if(node.op == OpType.ADD) {
            System.out.println(node.left.accept(this) + node.right.accept(this));
        } else {
            System.out.println(node.left.accept(this) - node.right.accept(this));
        }
    }

    public void visit(AssignNode node) {

    }

    public void visit(NumNode node) {
        System.out.println(node.getValue());
    }

    void interpret() throws ParseException{
        ExprNode tree = this.parser.parse();
        tree.accept(this);
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
                        interpreter.interpret();
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
