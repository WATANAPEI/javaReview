package calculator;

/**
 * Node classes
 */

abstract class Node {
    public Node() {}
    abstract Integer accept(NodeVisitor v);
    abstract public String dump();
}
