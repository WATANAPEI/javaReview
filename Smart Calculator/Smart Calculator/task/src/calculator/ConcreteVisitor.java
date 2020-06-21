package calculator;

class CalcVisitor implements CASTVisitor {
    int result;
    char currentOp;

    CalcVisitor() {
        this.result = 0;
    }

    public int visit(ExprNode node) {
        result = node.number;
        for(var e: node.rhs) {
            visit((RhsNode)e);
        }
        return result;
    }
    public void visit(RhsNode node) {
        if(visit(node.operatorList) == '+') {
            result += node.number;
        } else {
            result -= node.number;
        }
    }
    public char visit(OperatorNode node) {
        return node.getValue();
    }
}
