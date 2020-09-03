/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package three_method_lexer;

import abstract_syntax_tree.RegexType;
import java.util.ArrayList;

/**
 *
 * @author zofia
 */
public class Leaf {
    private int value;
    RegexType type;
    private int number;
    private boolean anulable;
    private boolean leaf;
    private ArrayList<Integer> first;
    private ArrayList<Integer> last;
    private ArrayList<Integer> next;

    public Leaf(int value, boolean leaf) {
        this.value = value;
        this.leaf = leaf;
        this.first = new ArrayList<>();
        this.last = new ArrayList<>();
        if(leaf) this.next = new ArrayList<>();
    }

    public Leaf(int value, RegexType type, boolean leaf) {
        this.value = value;
        this.type = type;
        this.leaf = leaf;
        this.first = new ArrayList<>();
        this.last = new ArrayList<>();
        if(leaf) this.next = new ArrayList<>();
    }

    public boolean isLeaf() {
        return leaf;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isAnulable() {
        return anulable;
    }

    public void setAnulable(boolean anulable) {
        this.anulable = anulable;
    }

    public ArrayList<Integer> getFirst() {
        return first;
    }

    public void addFirst(int first) {
        this.first.add(first);
    }

    public ArrayList<Integer> getLast() {
        return last;
    }

    public void addLast(int last) {
        this.last.add(last);
    }

    public void setFirst(ArrayList<Integer> first) {
        this.first = first;
    }

    public void setLast(ArrayList<Integer> last) {
        this.last = last;
    }

    public ArrayList<Integer> getNext() {
        return next;
    }

    public void setNext(ArrayList<Integer> next) {
        this.next = next;
    }

    public RegexType getType() {
        return type;
    }

    public void setType(RegexType type) {
        this.type = type;
    }

}
