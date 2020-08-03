package calculator.node;

import calculator.visitor.NodeVisitor;

/**
 * Node classes
 */

abstract class Node {
    public Node() {}
    public abstract Integer accept(NodeVisitor v);
    abstract public String dump();
}
