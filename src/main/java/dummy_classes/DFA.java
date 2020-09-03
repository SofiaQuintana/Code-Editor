/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dummy_classes;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;

/**
 *
 * @author zofia
 */
public class DFA implements Serializable {
    private String identifier;
    private String token;
    private State initial;
    private LinkedList<Integer> acceptedCharacters;
    private LinkedList<HashMap<Integer, State>> transitions;
    private boolean ignored;
    private boolean noneMatch;

    public DFA(State initial, LinkedList<Integer> acceptedCharacters, LinkedList<HashMap<Integer, State>> transitions) {
        this.initial = initial;
        this.acceptedCharacters = acceptedCharacters;
        this.transitions = transitions;
    }
    
    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public State getInitial() {
        return initial;
    }

    public void setInitial(State initial) {
        this.initial = initial;
    }

    public LinkedList<Integer> getAcceptedCharacters() {
        return acceptedCharacters;
    }

    public void setAcceptedCharacters(LinkedList<Integer> acceptedCharacters) {
        this.acceptedCharacters = acceptedCharacters;
    }

    public LinkedList<HashMap<Integer, State>> getTransitions() {
        return transitions;
    }

    public void setTransitions(LinkedList<HashMap<Integer, State>> transitions) {
        this.transitions = transitions;
    }

    public boolean isIgnored() {
        return ignored;
    }

    public void setIgnored(boolean ignored) {
        this.ignored = ignored;
    }

    public boolean isNoneMatch() {
        return noneMatch;
    }

    public void setNoneMatch(boolean noneMatch) {
        this.noneMatch = noneMatch;
    }   
}
