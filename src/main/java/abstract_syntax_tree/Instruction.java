/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abstract_syntax_tree;

/**
 *
 * @author zofia
 */
public interface Instruction {
    
    public Object execute();
    public int getLine();
    public int getColumn();
    
}
