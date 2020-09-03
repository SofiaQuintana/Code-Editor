/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dummy_classes;

import abstract_syntax_tree.SymType;

/**
 *
 * @author zofia
 */
public class Terminal {
    private String id;
    private SymType type;
    private boolean terminal;

    public Terminal(String id, SymType type, boolean terminal) {
        this.id = id;
        this.type = type;
        this.terminal = terminal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SymType getType() {
        return type;
    }

    public void setType(SymType type) {
        this.type = type;
    }

    public boolean isTerminal() {
        return terminal;
    }

    public void setTerminal(boolean terminal) {
        this.terminal = terminal;
    }
    
}
