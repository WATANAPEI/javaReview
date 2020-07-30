package calculator;
/**
 * VariableNode supports expression as below:
 * a, bb
 * evaluate the value of reference node during construction
 */

class VariableNode extends ExprNode {
    UnaryNode node;
    String image;

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
    Integer accept(NodeVisitor v) {
        return v.visit(this);
    }

}
