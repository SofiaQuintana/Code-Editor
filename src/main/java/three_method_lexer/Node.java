/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package three_method_lexer;

/**
 *
 * @author zofia
 */
public class Node {
    private Node left;
    private Node right;
    private Leaf leaf;

    public Node(Leaf leaf) {
        this.leaf = leaf;
        this.left = null;
        this.right = null;
    }

    public Node(Node left, Node right, Leaf leaf) {
        this.left = left;
        this.right = right;
        this.leaf = leaf;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Leaf getLeaf() {
        return leaf;
    }

    public void setLeaf(Leaf leaf) {
        this.leaf = leaf;
    }

    @Override
    public String toString() {
        return  "val=" + leaf.getValue() +  "type = " + leaf.getType();
    }
    
}
