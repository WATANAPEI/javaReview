package calculator;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

// BNF
// <Expr> := <Number> | (<RHS>)*
// <RHS> := <OperatorList> <Number>
// <OperatorList> := <Operator>*
// <Operator> := + | -


abstract class Node implements Traversable {
    abstract void parse(Context context) throws ParseException;
}

class ExprNode extends Node {
    // BNF
    // <Expr> := <Number> | (<RHS>)*
    public Integer number = 0;
    public List<Node> rhs = new ArrayList<>();
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
        StringBuilder sb = new StringBuilder(number.toString());
        for(var e: rhs) {
            sb.append(" " + e + " ");
        }
        return sb.toString();
    }

    @Override
    public void accept(CASTVisitor v) {
        v.visit(this);
    }
}

class RhsNode extends Node {
    // BNF
    // <RHS> := <OperatorList> <Number>
    public OperatorNode operatorList;
    public Integer number;
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
    @Override
    public void accept(CASTVisitor v) {
        v.visit(this);
    }

}

class OperatorNode extends Node{
    // BNF
    // <Operator> := <Operator>*
    // <OperatorList> := + | -
    private char operator = ' ';
    void parse(Context context) throws ParseException {
        operator = '+';
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

    public char getValue() {
        return operator;
    }

    @Override
    public void accept(CASTVisitor v) {
        v.visit(this);
    }
}
