/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dummy_classes;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author zofia
 */
public class State implements Serializable {
    private int number;
    private boolean initial;
    private boolean acceptance;
    private boolean checked;
    private ArrayList<Integer> positions;

    public State(int number, boolean initial, boolean acceptance, boolean checked, ArrayList<Integer> values) {
        this.number = number;
        this.initial = initial;
        this.acceptance = acceptance;
        this.checked = checked;
        this.positions = values;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isInitial() {
        return initial;
    }

    public void setInitial(boolean initial) {
        this.initial = initial;
    }

    public boolean isAcceptance() {
        return acceptance;
    }

    public void setAcceptance(boolean acceptance) {
        this.acceptance = acceptance;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public ArrayList<Integer> getValues() {
        return positions;
    }

    public void setValues(ArrayList<Integer> values) {
        this.positions = values;
    }
    
}
