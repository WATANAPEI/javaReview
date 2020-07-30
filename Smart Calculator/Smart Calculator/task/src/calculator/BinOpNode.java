package calculator;
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
    Integer accept(NodeVisitor v) {
        return v.visit(this);
    }

    @Override
    public String dump() {
        return String.format("%s %s %s\n", left.dump(), op.toString(), right.dump());
    }
}
