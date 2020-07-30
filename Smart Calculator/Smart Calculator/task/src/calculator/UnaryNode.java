package calculator;
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
    Integer accept(NodeVisitor v) {
        return v.visit(this);
    }

    @Override
    public String dump() {
        return uniOp + node.dump();
    }

}
