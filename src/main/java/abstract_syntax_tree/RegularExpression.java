/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abstract_syntax_tree;

import three_method_lexer.DFAGenerator;
import three_method_lexer.Node;
import three_method_lexer.Tree;

/**
 *
 * @author zofia
 */
public class RegularExpression implements Instruction {

    private int line, column;
    private String id;
    private Tree tree;
    private boolean ignored;
    
    public RegularExpression(String id, Node regEx, int line, int column, boolean ignored) {
        this.id = id;
        this.tree = new Tree(regEx);
        this.line = line;
        this.column = column;
        this.ignored = ignored;
    }
    
    @Override
    public Object execute() {
        this.tree.expandTree();
        this.tree.enumLeaves(tree.getRoot());
        this.tree.enumParents(tree.getRoot());
        DFAGenerator generator = new DFAGenerator(tree.getCharacters(), tree.getLeaves(), tree.getEnumerator());
        return generator.generate(tree.getRoot());
    }

    @Override
    public int getLine() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getColumn() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
