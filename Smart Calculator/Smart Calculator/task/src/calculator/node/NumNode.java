package calculator.node;

import calculator.visitor.NodeVisitor;

/**
 * NumNode supports expression like below:
 * 1, 23, -30
 */
public class NumNode extends ExprNode {
    Integer value;
    public NumNode(Integer value) {
        super();
        this.value = value;
    }

    @Override
    public Integer accept(NodeVisitor v) {
        return v.visit(this);
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public String dump() {
        return value.toString();
    }
}
