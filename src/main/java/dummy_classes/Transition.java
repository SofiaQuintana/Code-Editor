/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dummy_classes;

import java.io.Serializable;

/**
 *
 * @author zofia
 */
public class Transition implements Serializable {
    private int asciiValue;
    private char character;

    public Transition(int asciiValue, char character) {
        this.asciiValue = asciiValue;
        this.character = character;
    }

    public void setCharacter(char character) {
        this.character = character;
    }
    
    public int getAsciiValue() {
        return asciiValue;
    }

    public void setAsciiValue(int asciiValue) {
        this.asciiValue = asciiValue;
    }
    
}
