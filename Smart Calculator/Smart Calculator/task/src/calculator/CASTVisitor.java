package calculator;

/**
 * CASTVisitor
 * Calculator AST Visitor
 *
 */

public interface CASTVisitor {
    public int visit(ExprNode_ node);
    public void visit(RhsNode node);
    public char visit(OperatorNode node);
}

