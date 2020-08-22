/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package environment;

/**
 *
 * @author zofia
 */
public class GlobalError {
    private String type;
    private String value;
    private String message;
    private int row;
    private int column;

    public GlobalError(String type, String value, String message, int row, int column) {
        this.type = type;
        this.value = value;
        this.message = message;
        this.row = row;
        this.column = column;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    @Override
    public String toString() {
        return "Error " + type + " en linea: " + row + ", columna: " + column  + ", valor:" + value + ", observacion general: " + message;
    }
}
