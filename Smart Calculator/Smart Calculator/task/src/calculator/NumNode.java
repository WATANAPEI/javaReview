package calculator;
/**
 * NumNode supports expression like below:
 * 1, 23, -30
 */
class NumNode extends ExprNode {
    Integer value;
    NumNode(Integer value) {
        super();
        this.value = value;
    }

    @Override
    Integer accept(NodeVisitor v) {
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
