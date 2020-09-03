/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphical_user_interface;

import java.awt.Color;
import java.awt.GridLayout;
import java.io.File;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author zofia
 */
public class Tab extends JPanel{
    private LineCounter lineCounter;
    private JTextArea textArea;
    private JScrollPane scroll;
    private boolean saved;
    private String title;
    private String path;
    private int index;

    public Tab(boolean saved, String title, int index, String path) {
        super(new GridLayout());
        this.saved = saved;
        this.title = title;
        this.textArea = new JTextArea();
        this.textArea.setBackground(new Color(51, 51, 51));
        this.textArea.setForeground(Color.WHITE);
        this.lineCounter = new LineCounter(textArea);
        this.lineCounter.setBackground(new Color(51, 51, 51));
        this.scroll = new JScrollPane(textArea);
        this.scroll.setRowHeaderView(lineCounter);
        this.add(scroll);
        this.index = index;
        this.path = path;
    }

    public String getText() {
        return textArea.getText();
    }

    public void setText(String text) {
        this.textArea.setText(text);
    }
    
    public boolean isSaved() {
        return saved;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    
}
