/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abstract_syntax_tree;

import three_method_lexer.Leaf;
import three_method_lexer.Node;

/**
 *
 * @author zofia
 */
public class Operation implements Instruction {
    private int line;
    private int column;
    private RegexType type;
    private Leaf value;
    private Node left;
    private Node right;
    private Node parent;
    private Node connection;
    private String macro;
    
    public Operation(RegexType type, Leaf value, Node left, Node right, Node parent, Node connection, int line, int column) {
        this.type = type;
        this.right = right;
        this.left = left;
        this.line = line;
        this.column = column;
        this.value = value;
        this.parent = parent;
        this.connection = connection;
    }

    public Operation(RegexType type, Node left, Node right, Node parent, Node connection, String macro) {
        this.type = type;
        this.left = left;
        this.right = right;
        this.parent = parent;
        this.connection = connection;
        this.macro = macro;
    }

    @Override
    public Object execute() {
        Node operation = null;
        switch (type) {
            case CONCAT:   operation = concatOperation();   break;
            case LETTER_MACRO: case NUMBER_MACRO: operation = macro(); break;
            case UNI_C: operation = uniConnection(); break;
            case UNI_N: operation = uniCreation(); break;
            case BI_C:  operation = biConnection();  break;
            case BI_N:   operation = biCreation();   break;
            case MULTI_C:  operation = multiConnection();  break;
            case MULTI_N:  operation = multiCreation();   break;
        }
        return operation;
    }
    
    private Node concatOperation() {
        right = new Node(value);
        return new Node(left, right, new Leaf( (int) '.', false));
    }

    private Node uniConnection() {
        parent.setLeft(left);
        return parent;
    }
    
    private Node uniCreation() {
        left = new Node(value);
        parent.setLeft(left);
        return parent;
    }
    
    private Node biConnection() {
        parent.setLeft(left);
        parent.setRight(right);
        return parent;
    }
    
    private Node biCreation() {
        right = new Node(value);
        parent.setLeft(left);
        parent.setRight(right);
        return parent;
    }
    
    private Node multiCreation() {
        connection = new Node(value);
        right.setLeft(connection);
        parent.setLeft(left);
        parent.setRight(right);
        return parent;
    }
    
    private Node multiConnection() {
        right.setLeft(connection);
        parent.setLeft(left);
        parent.setRight(right);
        return parent;
    }
    
     private Node macro() {
         char start = macro.charAt(1);
         char end = macro.charAt(3);
         for (int i = (int) start; i <= (int) end; i++) {
             if(i == (int) start) {
                left = new Node(new Leaf((char) i, true));
                parent = new Node(left, left, new Leaf( (int) '|', RegexType.OR, false));
             } else if(i == (int) start + 1) {
                 right = new Node(new Leaf((char) i, true));
                 parent.setRight(right);
             } else {
                 right = new Node(new Leaf((char) i, true));
                 connection = new Node(parent, right, new Leaf( (int) '|', RegexType.OR, false));
                 parent = connection;
             }
         }
         return parent;
     }
     
    @Override
    public int getLine() {
        return line;
    }

    @Override
    public int getColumn() {
        return column;
    }
    
}
