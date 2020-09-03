/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package three_method_lexer;

import dummy_classes.DFA;
import dummy_classes.State;
import dummy_classes.Transition;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 *
 * @author zofia
 */
public class DFAGenerator {
    private LinkedList<HashMap<Integer, State>> transitions;
    private LinkedList<State> dStates; //Estados deterministicos D = {}
    private LinkedList<Integer> characters;
    private HashMap<Integer, Node> leaves;
    private State current;
    private int counter;
    private static final int STARTER_STATE = 0;
    private static final int UNDEFINED = -202;
    private static final boolean CHECKED_STATE = true;

    public DFAGenerator(LinkedList<Integer> characters, HashMap<Integer, Node> leaves, int counter) {
        this.transitions = new LinkedList<>();
        this.dStates = new LinkedList<>();
        this.characters = characters;
        this.leaves = leaves;
        this.counter = counter;
    }
    
    /** Algoritmo de generacion de AFD*/
    public DFA generate(Node root) {
        int enumerator = 1;
        State auxiliar;
        ArrayList<Integer> temporal;
        //1. Creacion de estado inicial
        dStates.add(createStarterState(root.getLeaf()));
        //2. Recorrer mientras haya un 'estado E' en el conjunto de estados 'D' sin chequear
        while((current = getUncheckedState()) != null) {
            //3. Marcar estado E
            current.setChecked(CHECKED_STATE);
            transitions.add(new HashMap());
            //4. Para cada simbolo de entrada 'a' hacer
            for (int i = 0; i < characters.size(); i++) {
                //5. Sea 'U' el conjunto de posiciones que estan en SIGUIENTES para alguna posicion 'p' en E, tal que el simbolo en la posicion 'p' es 'a'.
                temporal = union(positions(characters.get(i), current.getValues()));
                //6. Si 'U' no esta vacio 
                if(!temporal.isEmpty()) {
                    auxiliar = verifyIfStateExists(temporal);
                    if(auxiliar == null) {
                        auxiliar = new State(enumerator, false, false, false,temporal);
                        enumerator++;
                    }
                    //7. Si el estado generado no esta en Estados 'D' agregar a estados D y agregar transicion
                    if(isStateRepeated(temporal)) {
                        dStates.add(auxiliar);
                    } //8. De lo contrario agregar un estado indefinido.
                        transitions.get(current.getNumber()).put(i, auxiliar);
                } else {
                    transitions.get(current.getNumber()).put(i, new State(UNDEFINED, false, false, false, null));
                }
            }
        }
        checkAcceptanceStates();
        return new DFA(dStates.get(STARTER_STATE), characters, transitions);
    }
    
    private State createStarterState(Leaf root) {
        return new State(STARTER_STATE, true, false, false, root.getFirst());
    }
    
    /** Sea 'U' el conjunto de posiciones que estan en SIGUIENTES para alguna posicion 'p' en E,
     * tal que el simbolo en la posicion 'p' es 'a'.     */
    private ArrayList<Integer> positions(int character, ArrayList<Integer> nexts) {
        ArrayList<Integer> matched = new ArrayList<>();
        nexts.stream().filter(value -> leaves.get(value).getLeaf().getValue() == character).forEachOrdered(value -> {
            matched.add(value);
        });
        return matched;
    }
    
    private ArrayList<Integer> union(ArrayList<Integer> matched) {
        ArrayList<Integer> temporal = new ArrayList<>();
        matched.forEach(value -> { 
            leaves.get(value).getLeaf().getNext().stream().filter(charac -> (!temporal.contains(charac))).forEachOrdered(val -> { 
                temporal.add(val);
            });
        });
        return temporal;
    }
    
    private void checkAcceptanceStates() {
        for(State state : dStates) {
            if(state.getValues().contains(counter)) state.setAcceptance(true);
        }
    } 
    
    private boolean isStateRepeated(ArrayList<Integer> positions) {
        return dStates.stream().noneMatch(val -> (val.getValues().equals(positions)));
    }
    
    private State verifyIfStateExists(ArrayList<Integer> matched) {
        for(State state : dStates) {
            if(state.getValues().equals(matched)) return state;
        }
        return null;
    }
    private State getUncheckedState() {
        for(State state : dStates) {
            if(state.isChecked() == false)  return state;
        }
        return null;
    }
    
}
