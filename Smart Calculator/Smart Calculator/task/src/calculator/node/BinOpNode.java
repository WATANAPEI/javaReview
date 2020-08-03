package calculator.node;

import calculator.node.ExprNode;
import calculator.visitor.NodeVisitor;
import calculator.OpType;

/**
 * BinOpNode supports expression below:
 * a + 2, 1 - 2, a + b
 */
public class BinOpNode extends ExprNode {
    public ExprNode left;
    public OpType op;
    public ExprNode right;

    public BinOpNode(ExprNode left, OpType op, ExprNode right) {
        super();
        this.left = left;
        this.op = op;
        this.right = right;
    }

    @Override
    Integer accept(NodeVisitor v) {
        return v.visit(this);
    }

    @Override
    public String dump() {
        return String.format("%s %s %s\n", left.dump(), op.toString(), right.dump());
    }
}
