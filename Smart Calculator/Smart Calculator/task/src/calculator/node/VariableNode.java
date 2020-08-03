package calculator.node;

import calculator.visitor.NodeVisitor;

/**
 * VariableNode supports expression as below:
 * a, bb
 * evaluate the value of reference node during construction
 */

public abstract class VariableNode extends ExprNode {
    public UnaryNode node;
    public String image;

    public VariableNode(UnaryNode node, String image) {
        this.node = node;
        this.image = image;
    }

    public VariableNode(String image) {
        this.image = image;
    }

    public void setNode(UnaryNode node) {
        this.node = node;
    }

    @Override
    public String dump() {
        return this.image;
    }

    @Override
    public Integer accept(NodeVisitor v) {
        return v.visit(this);
    }

}
