/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphical_user_interface;

import abstract_syntax_tree.Instruction;
import drivers.FileDriver;
import environment.GlobalError;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import lexer.LanguageLexer;
import parser.LanguageParser;

/**
 *
 * @author zofia
 */
public class PrincipalFrame extends javax.swing.JFrame {
    private int unsavedTabCounter;
    private LinkedList<Tab> tabs;
    private LinkedList<GlobalError> errors;
    private Tab selectedTab;
    private int selectedTabIndex;
    private JFileChooser chooser;
    private FileDriver fileDriver;
    private LanguageLexer lexer;
    private LanguageParser parser;
    private Errores erroresDialog;
    private static final FileNameExtensionFilter languageFilter = new FileNameExtensionFilter("LENGUAJE", "len");
  
    
    /**
     * Creates new form PrincipalFrame
     */
    public PrincipalFrame() {
        initComponents();
        this.tabs = new LinkedList<>();
        this.errors = new LinkedList<>();
        this.fileDriver = new FileDriver();
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        this.unsavedTabCounter = 0;
    }

    /*Crea una nueva pestana*/
    private Tab createNewTab() {
        unsavedTabCounter++;
        Tab tab = new Tab(false, "Untitled Document " + unsavedTabCounter, tabs.size(), "");
        return tab;
    }
    
    /*Agrega al tabbed panel una nueva pestana*/
    private void addTab(Tab tab) {
        tabs.add(tab);
        tabbedPanel.addTab(tab.getTitle() , new ImageIcon(getClass().getResource("/close.png")), tab);
        selectedTab = tab;
    }
    
    private void openFile() throws IOException {
        this.chooser = new JFileChooser();
        this.chooser.setFileFilter(languageFilter);
         int option = chooser.showOpenDialog(this);
        if(option == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            String fileName = file.getName();
            String content = fileDriver.readInputFile(file);
            selectedTab = new Tab(true, fileName, tabs.size(), file.getPath());
            selectedTab.setText(content);
            addTab(selectedTab);
        }
    }
    
    private void removeTab(int index) {
        int selected = JOptionPane.showConfirmDialog(this, "Desea guardar los cambios realizados? ", "Guardado", JOptionPane.QUESTION_MESSAGE);
        switch (selected) {
            case JOptionPane.OK_OPTION:
                //guardar
                tabbedPanel.removeTabAt(index);
                tabs.remove(index);
            break;
            case JOptionPane.NO_OPTION:
                tabbedPanel.removeTabAt(index);
                tabs.remove(index);
            break;
        }
    }
    
    private void save() throws IOException {
        if(selectedTab.getPath().equals("")) {
            saveAs();
        } else {
            File file = new File(selectedTab.getPath());
            fileDriver.saveFileContent(selectedTab.getText(), file);
            JOptionPane.showMessageDialog(this, "Se guardo con exito el archivo", "Informacion", JOptionPane.INFORMATION_MESSAGE);
        }
        
    }
    
    private void saveAs() throws IOException {
        chooser = new JFileChooser();
        int option = chooser.showSaveDialog(this);
        if(option == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            selectedTab.setPath(file.getPath());
            selectedTab.setName(file.getName());
            selectedTab.setTitle(file.getName());
            fileDriver.saveFileContent(selectedTab.getText(), file);
            JOptionPane.showMessageDialog(this, "Se guardo con exito el archivo", "Informacion", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void analyzeLanguage() throws Exception {
       this.lexer = new LanguageLexer(new StringReader(selectedTab.getText()), errors);
       this.parser = new LanguageParser(lexer, errors);
       this.parser.parse();
       if(errors.isEmpty()) {
           executeInstructions(parser.AST);
       } else {
           erroresDialog = new Errores(this, true);
           erroresDialog.setText(errors);
           erroresDialog.setVisible(true);
       }
    }
    
    private void executeInstructions(LinkedList<Instruction> instructions) {
        for(Instruction instruction : instructions) {
            instruction.execute();
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        iconBar = new javax.swing.JPanel();
        openButton = new javax.swing.JButton();
        newButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        centralPanel = new javax.swing.JPanel();
        tabbedPanel = new javax.swing.JTabbedPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        newItem = new javax.swing.JMenuItem();
        openItem = new javax.swing.JMenuItem();
        saveItem = new javax.swing.JMenuItem();
        saveAsItem = new javax.swing.JMenuItem();
        exit = new javax.swing.JMenuItem();
        languageMenu = new javax.swing.JMenu();
        compileItem = new javax.swing.JMenuItem();
        addLanguageItem = new javax.swing.JMenuItem();
        deleteLanguageItem = new javax.swing.JMenuItem();
        runMenu = new javax.swing.JMenu();
        viewMenu = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        iconBar.setBackground(new java.awt.Color(51, 51, 51));

        openButton.setBackground(new java.awt.Color(102, 102, 102));
        openButton.setForeground(new java.awt.Color(7, 86, 100));
        openButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/open16.png"))); // NOI18N
        openButton.setFocusPainted(false);
        openButton.setFocusable(false);
        openButton.setOpaque(true);
        openButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openButtonActionPerformed(evt);
            }
        });

        newButton.setBackground(new java.awt.Color(102, 102, 102));
        newButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/new16.png"))); // NOI18N
        newButton.setFocusPainted(false);
        newButton.setFocusable(false);
        newButton.setOpaque(true);
        newButton.setRequestFocusEnabled(false);
        newButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newButtonActionPerformed(evt);
            }
        });

        saveButton.setBackground(new java.awt.Color(102, 102, 102));
        saveButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/save16.png"))); // NOI18N
        saveButton.setFocusPainted(false);
        saveButton.setFocusable(false);
        saveButton.setOpaque(true);
        saveButton.setRequestFocusEnabled(false);
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout iconBarLayout = new javax.swing.GroupLayout(iconBar);
        iconBar.setLayout(iconBarLayout);
        iconBarLayout.setHorizontalGroup(
            iconBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(iconBarLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(openButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(newButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1009, Short.MAX_VALUE))
        );
        iconBarLayout.setVerticalGroup(
            iconBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(iconBarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(iconBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(saveButton)
                    .addComponent(newButton)
                    .addComponent(openButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        centralPanel.setBackground(new java.awt.Color(51, 51, 51));
        centralPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        tabbedPanel.setBackground(new java.awt.Color(102, 102, 102));
        tabbedPanel.setForeground(new java.awt.Color(255, 255, 255));
        tabbedPanel.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tabbedPanelStateChanged(evt);
            }
        });
        tabbedPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabbedPanelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout centralPanelLayout = new javax.swing.GroupLayout(centralPanel);
        centralPanel.setLayout(centralPanelLayout);
        centralPanelLayout.setHorizontalGroup(
            centralPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(centralPanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(tabbedPanel)
                .addGap(0, 0, 0))
        );
        centralPanelLayout.setVerticalGroup(
            centralPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(centralPanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(tabbedPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        jMenuBar1.setBackground(new java.awt.Color(51, 51, 51));
        jMenuBar1.setForeground(new java.awt.Color(255, 255, 255));

        fileMenu.setForeground(new java.awt.Color(255, 255, 255));
        fileMenu.setText("File");
        fileMenu.setFont(new java.awt.Font("URW Gothic L", 0, 13)); // NOI18N

        newItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        newItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/new16.png"))); // NOI18N
        newItem.setText("New");
        newItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newItemActionPerformed(evt);
            }
        });
        fileMenu.add(newItem);

        openItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        openItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/open16.png"))); // NOI18N
        openItem.setText("Open");
        openItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openItemActionPerformed(evt);
            }
        });
        fileMenu.add(openItem);

        saveItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        saveItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/save16.png"))); // NOI18N
        saveItem.setText("Save");
        saveItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveItemActionPerformed(evt);
            }
        });
        fileMenu.add(saveItem);

        saveAsItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        saveAsItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/saveAs.png"))); // NOI18N
        saveAsItem.setText("Save As");
        saveAsItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAsItemActionPerformed(evt);
            }
        });
        fileMenu.add(saveAsItem);

        exit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/close.png"))); // NOI18N
        exit.setText("Exit");
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });
        fileMenu.add(exit);

        jMenuBar1.add(fileMenu);

        languageMenu.setForeground(new java.awt.Color(255, 255, 255));
        languageMenu.setText("Languages");
        languageMenu.setFont(new java.awt.Font("URW Gothic L", 0, 13)); // NOI18N

        compileItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/compile.png"))); // NOI18N
        compileItem.setText("Compile");
        languageMenu.add(compileItem);

        addLanguageItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/add.png"))); // NOI18N
        addLanguageItem.setText("Add Language");
        addLanguageItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addLanguageItemActionPerformed(evt);
            }
        });
        languageMenu.add(addLanguageItem);

        deleteLanguageItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/delete.png"))); // NOI18N
        deleteLanguageItem.setText("Delete Language");
        languageMenu.add(deleteLanguageItem);

        jMenuBar1.add(languageMenu);

        runMenu.setForeground(new java.awt.Color(255, 255, 255));
        runMenu.setText("Run");
        runMenu.setFont(new java.awt.Font("URW Gothic L", 0, 13)); // NOI18N
        jMenuBar1.add(runMenu);

        viewMenu.setForeground(new java.awt.Color(255, 255, 255));
        viewMenu.setText("View");
        viewMenu.setFont(new java.awt.Font("URW Gothic L", 0, 13)); // NOI18N
        jMenuBar1.add(viewMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(iconBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(centralPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(iconBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(centralPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void newButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newButtonActionPerformed
        // TODO add your handling code here:
        addTab(createNewTab());
    }//GEN-LAST:event_newButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        try {
            save();
            tabs.remove(selectedTab);
            addTab(selectedTab);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar el archivo", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_saveButtonActionPerformed

    private void newItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newItemActionPerformed
        // TODO add your handling code here:
        addTab(createNewTab());
    }//GEN-LAST:event_newItemActionPerformed

    private void openItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openItemActionPerformed
        try {
            openFile();
        } catch (IOException ex) {
            Logger.getLogger(PrincipalFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_openItemActionPerformed

    private void openButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openButtonActionPerformed
        try {
            openFile();
        } catch (IOException ex) {
            Logger.getLogger(PrincipalFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_openButtonActionPerformed

    private void tabbedPanelStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tabbedPanelStateChanged
        // TODO add your handling code here:
        selectedTabIndex = tabbedPanel.getSelectedIndex();
        System.out.println(selectedTabIndex);
        selectedTab = tabs.get(selectedTabIndex);
    }//GEN-LAST:event_tabbedPanelStateChanged

    private void tabbedPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabbedPanelMouseClicked
        if(evt.getClickCount() == 2) { 
            removeTab(tabbedPanel.getSelectedIndex());
        }
    }//GEN-LAST:event_tabbedPanelMouseClicked

    private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitActionPerformed

    private void addLanguageItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addLanguageItemActionPerformed
        try {
            analyzeLanguage();
        } catch (Exception ex) {
            Logger.getLogger(PrincipalFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_addLanguageItemActionPerformed

    private void saveAsItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAsItemActionPerformed
        try {
            // TODO add your handling code here:
            saveAs();
            tabs.remove(selectedTab);
            addTab(selectedTab);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar el archivo", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_saveAsItemActionPerformed

    private void saveItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveItemActionPerformed
        // TODO add your handling code here:
         try {
            save();
            tabs.remove(selectedTab);
            addTab(selectedTab);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar el archivo", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_saveItemActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem addLanguageItem;
    private javax.swing.JPanel centralPanel;
    private javax.swing.JMenuItem compileItem;
    private javax.swing.JMenuItem deleteLanguageItem;
    private javax.swing.JMenuItem exit;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JPanel iconBar;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu languageMenu;
    private javax.swing.JButton newButton;
    private javax.swing.JMenuItem newItem;
    private javax.swing.JButton openButton;
    private javax.swing.JMenuItem openItem;
    private javax.swing.JMenu runMenu;
    private javax.swing.JMenuItem saveAsItem;
    private javax.swing.JButton saveButton;
    private javax.swing.JMenuItem saveItem;
    private javax.swing.JTabbedPane tabbedPanel;
    private javax.swing.JMenu viewMenu;
    // End of variables declaration//GEN-END:variables
}
