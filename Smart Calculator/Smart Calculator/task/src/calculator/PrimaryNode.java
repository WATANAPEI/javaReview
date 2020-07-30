package calculator;
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
    Integer accept(NodeVisitor v) {
        return v.visit(this);
    }

    @Override
    public String dump() {
        return node.dump();
    }
}
