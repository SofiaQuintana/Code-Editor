/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package three_method_lexer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author zofia
 */
public class Tree {
    private static final char SPECIAL_CHAR = 202;
    private static final boolean NULLABLE_OP = true;
    private Node root;
    private HashMap<Integer, Node> leaves;
    private LinkedList<Integer> characters;
    private ArrayList[] nexts;
    private int enumerator;

    public Tree() {
        this.root = null;
    }

    public Tree(Node root) {
        this.root = root;
        this.leaves = new HashMap<>();
        this.characters = new LinkedList<>();
        this.enumerator = 0;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }
    
    private void initNextPositionByLeaf() {
        this.nexts = new ArrayList[enumerator];
        for (int i = 0; i < nexts.length; i++) {
            nexts[i] = new ArrayList<>();
        }
    }
    
    /** Inicio del metodo del arbol, *EXPANSION*
     * 1. Se crea un nuevo nodo al cual se le envia como valor el caracter especial 202 (Ascii code).
     * 2. Se crea un nuevo nodo concatenacion, al cual se le coloca como nodo izq la raiz anterior, y como nodo der el nodo con el caracter especial.
     * 3. Se manda el nodo concatenacion como nueva raiz del arbol.
     */
    public void expandTree() {
        Node right = new Node(new Leaf(SPECIAL_CHAR, true));
        Node concat = new Node(root, right, new Leaf((int)'.', false));
        setRoot(concat);
    }
    
    /** Agrega un caracter a la lista de caracteres permitidos por la expresion regular.
     * @param character */
    public void addCharacter(int character) {
        if(character != SPECIAL_CHAR && !characters.contains(character)) characters.add(character);
    }
    
    /** 1. Enumeracion de los nodos hoja en recorrido preorden (todos los nodos hoja que se encuentran a la altura mas alta).
     *   2. Se coloca el siguiente y el anterior (el cual es el mismo valor que el nodo hoja correspondiente)
     *   3. Se agrega a la lista de caracteres de la expresion regular.
     *   4. Se agrega la hoja al hashmap de hojas.
     */
    public void enumLeaves(Node node) {
        if(node != null) {
            Leaf leaf = node.getLeaf();
            if(leaf != null && leaf.isLeaf()) {
                leaf.setAnulable(false); //los nodos hojas no son anulables
                leaf.setNumber(enumerator); //numero de hoja
                leaf.addFirst(enumerator); //Primero
                leaf.addLast(enumerator); //Ultimo
                addCharacter(leaf.getValue());
                leaves.put(enumerator, node);
                enumerator++;
            }
            enumLeaves(node.getLeft());
            enumLeaves(node.getRight());
        }
        initNextPositionByLeaf();
    }
    
    /** Recorrido en post orden, con el objetivo de asignar primeros,
     * siguientes y ultimos segun el caso que corresponda. */
    public void enumParents(Node node) {
        if(node != null) {
            enumParents(node.getLeft());
            enumParents(node.getRight());
            Leaf leaf = node.getLeaf();
            if(leaf != null && !leaf.isLeaf()) {
                if(node.getRight() != null) {
                    leaf = cases(leaf.getValue(), leaf, node.getRight().getLeaf(), node.getLeft().getLeaf());
                } else {
                    leaf = cases(leaf.getValue(), leaf, null, node.getLeft().getLeaf());
                }
            }
        }
        setNextPosition();
    }
    
    /** Casos de asignacion de primeros, ultimos y siguientes (si corresponde)
     * segun el tipo de operacion que traiga el caracter asignado al nodo enviado.
     */
    private Leaf cases(int character, Leaf actual, Leaf right, Leaf left) {
        boolean nullableValue;
        switch ((char) character) {
            case '.': //Concatenacion
                nullableValue = (right.isAnulable() && left.isAnulable());
                actual.setAnulable(nullableValue);
                actual = concatLeftRules(actual, right, left);
                actual = concatRightRules(actual, right, left);
                //calcular siguientes
                findNexts(right.getFirst(), left.getLast());
            break;
            case '|': //Or
                nullableValue = (right.isAnulable() || left.isAnulable());
                actual.setAnulable(nullableValue);
                actual.setFirst(concatFirsts(left, right));
                actual.setLast(concatLasts(left, right));
            break;
            case '+': // 1 - N veces
                actual.setAnulable(left.isAnulable());
                actual = uniOperationsRules(actual, left);
                //calcular siguientes
                findNexts(actual.getFirst(), actual.getLast());
            break;
            case '*': // 0 - N veces (Kleene)
                actual.setAnulable(NULLABLE_OP);
                actual = uniOperationsRules(actual, left);
                //calcular siguientes
                findNexts(actual.getFirst(), actual.getLast());
            break;
            case '?': // 0 - 1 vez
                actual.setAnulable(NULLABLE_OP);
                actual = uniOperationsRules(actual, left);
            break;
        }
        return actual;
    }
    
    private Leaf concatLeftRules(Leaf actual, Leaf right, Leaf left) {
        if(left.isAnulable()) {
            actual.setFirst(concatFirsts(left, right));
        } else {
            actual.setFirst(left.getFirst());
        }
        return actual;      
    }
    
    private Leaf concatRightRules(Leaf actual, Leaf right, Leaf left) {
        if(right.isAnulable()) {
            actual.setLast(concatLasts(left, right));
        } else {
            actual.setLast(right.getLast());
        }
        return actual;
    }
    
    private ArrayList concatFirsts(Leaf left,Leaf right) {
        return (ArrayList) Stream.concat(left.getFirst().stream(), right.getFirst().stream()).collect(Collectors.toList());
    } 
    
    private ArrayList concatLasts(Leaf left, Leaf right) {
        return (ArrayList) Stream.concat(left.getLast().stream(), right.getLast().stream()).collect(Collectors.toList());
    }
    
    private Leaf uniOperationsRules(Leaf actual, Leaf left) {
        actual.setFirst(left.getFirst());
        actual.setLast(left.getLast());
        return actual;
    }
    
    private void findNexts(ArrayList<Integer> firsts, ArrayList<Integer> lasts) {
        for (int i = 0; i < firsts.size(); i++) {
            for (int j = 0; j < lasts.size(); j++) {
                System.out.println(lasts.get(j));
                if(!nexts[lasts.get(j)].contains(firsts.get(i))) {
                    nexts[lasts.get(j)].add(firsts.get(i));
                }
            }
        }
    }
    
    private void setNextPosition() {
        for (int i = 0; i < nexts.length; i++) {
            leaves.get(i).getLeaf().setNext(nexts[i]);         
        }
    }

    public int getEnumerator() {
        return enumerator;
    }

    public HashMap<Integer, Node> getLeaves() {
        return leaves;
    }

    public LinkedList<Integer> getCharacters() {
        return characters;
    }
    
}
