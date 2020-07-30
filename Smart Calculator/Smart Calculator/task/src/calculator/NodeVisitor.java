package calculator;

/**
 * NodeVisitor
 *
 */
interface NodeVisitor {
    Integer visit(BinOpNode node);
    Integer visit(NumNode node);
    Integer visit(AssignNode node);
    Integer visit(UnaryNode node);
    Integer visit(PrimaryNode node);
    Integer visit(VariableNode node);

}
