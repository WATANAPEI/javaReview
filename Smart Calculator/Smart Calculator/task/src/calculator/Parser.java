package calculator;

import java.text.ParseException;

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
    void consume(TokenType tokenType) throws ParseException {
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
