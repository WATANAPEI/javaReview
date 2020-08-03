package calculator.node;

import calculator.visitor.NodeVisitor;

/**
 * UnaryNode supports expression below:
 * a, -2, +b
 */
public class UnaryNode extends ExprNode {
    public ExprNode node;
    public String uniOp;

    public UnaryNode(ExprNode node, String uniOp) {
        super();
        this.node = node;
        this.uniOp = uniOp;
    }

    UnaryNode(ExprNode node) {
        new UnaryNode(node, "");
    }

    @Override
    public Integer accept(NodeVisitor v) {
        return v.visit(this);
    }

    @Override
    public String dump() {
        return uniOp + node.dump();
    }

}
