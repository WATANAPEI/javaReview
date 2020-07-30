package calculator;

class AssignNode extends ExprNode {
    VariableNode lhs;
    AssignType assignType;
    UnaryNode rhs;

    AssignNode(VariableNode lhs, AssignType assignType, UnaryNode rhs) {
        super();
        this.lhs = lhs;
        this.assignType = assignType;
        this.rhs = rhs;
    }

    @Override
    Integer accept(NodeVisitor v) {
        return v.visit(this);
    }

    @Override
    public String dump() {
        return String.format("%s = %s", lhs, rhs);
    }
}
