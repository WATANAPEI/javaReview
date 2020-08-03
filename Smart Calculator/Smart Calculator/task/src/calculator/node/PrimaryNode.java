package calculator.node;

import calculator.visitor.NodeVisitor;
import calculator.node.ExprNode;
import calculator.node.NumNode;

/**
 * PrimaryNode supports expression below:
 * 22, 1
 * NumNode only for now
 */

public class PrimaryNode extends ExprNode {
    public NumNode node;
    public PrimaryNode(NumNode node) {
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
