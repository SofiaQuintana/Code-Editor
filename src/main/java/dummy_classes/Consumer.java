/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dummy_classes;

import java.io.Serializable;
import java.util.LinkedList;

/**
 *
 * @author zofia
 */
public class Consumer implements Serializable {
    private LinkedList<DFA> dfas;
    private static final char EOF = 03; //Fin de texto, codigo ascii.
    private int textLine;
    private int textRow;

    public Consumer(LinkedList<DFA> dfas) {
        this.dfas = dfas;
        textLine = 0;
        textRow = 0;
    }
    
    
}
