package calculator;

import java.text.ParseException;

class Interpreter implements NodeVisitor {
    Parser parser;

    Interpreter(Parser parser) {
        this.parser  = parser;
    }

    public Integer visit(BinOpNode node) {
        if(node.op == OpType.ADD) {
            return node.left.accept(this) + node.right.accept(this);
        } else {
            return node.left.accept(this) - node.right.accept(this);
        }
    }

    public Integer visit(AssignNode node) {
        node.lhs.setNode(node.rhs);
        Main.nodeMap.put(node.lhs.image, node.rhs.accept(this));
        return null;
    }

    public Integer visit(UnaryNode node) {
        if(node.uniOp.contentEquals("+") || node.uniOp.contentEquals("")) {
            return node.node.accept(this);
        } else {
            return -1 * node.node.accept(this);
        }
    }

    public Integer visit(VariableNode node) {
        return node.node.accept(this);
    }

    public Integer visit(NumNode node) {
        return node.getValue();
    }

    public Integer visit(PrimaryNode node) {
        return node.node.getValue();
    }

    void interpret() throws ParseException {
        ExprNode tree = this.parser.parse();
        tree.accept(this);
    }

}
