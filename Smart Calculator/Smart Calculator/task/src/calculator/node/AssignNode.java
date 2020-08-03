package calculator.node;

import calculator.*;
import calculator.visitor.NodeVisitor;

public class AssignNode extends ExprNode {
    public VariableNode lhs;
    AssignType assignType;
    public UnaryNode rhs;

    public AssignNode(VariableNode lhs, AssignType assignType, UnaryNode rhs) {
        super();
        this.lhs = lhs;
        this.assignType = assignType;
        this.rhs = rhs;
    }

    @Override
    public Integer accept(NodeVisitor v) {
        return v.visit(this);
    }

    @Override
    public String dump() {
        return String.format("%s = %s", lhs, rhs);
    }
}
