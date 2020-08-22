/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphical_user_interface;

import java.util.LinkedList;

/**
 *
 * @author zofia
 */
public class PrincipalFrame extends javax.swing.JFrame {
    private int unsavedTabCounter;
    private LinkedList<Tab> tabs;
    private Tab selectedTab;
    /**
     * Creates new form PrincipalFrame
     */
    public PrincipalFrame() {
        initComponents();
        this.tabs = new LinkedList<>();
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        this.unsavedTabCounter = 0;
    }

    /*Crea una nueva pestana*/
    private Tab createNewTab() {
        unsavedTabCounter++;
        Tab tab = new Tab(false, "Unsaved Document " + unsavedTabCounter);
        return tab;
    }
    
    private void addTab(Tab tab) {
        tabs.add(tab);
        tabbedPanel.addTab(tab.getTitle() ,tab);
        selectedTab = tab;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        iconBar = new javax.swing.JPanel();
        openButton = new javax.swing.JButton();
        newButton = new javax.swing.JButton();
        newButton1 = new javax.swing.JButton();
        newButton2 = new javax.swing.JButton();
        newButton3 = new javax.swing.JButton();
        centralPanel = new javax.swing.JPanel();
        tabbedPanel = new javax.swing.JTabbedPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        languageMenu = new javax.swing.JMenu();
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

        newButton1.setBackground(new java.awt.Color(102, 102, 102));
        newButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/new16.png"))); // NOI18N
        newButton1.setFocusPainted(false);
        newButton1.setFocusable(false);
        newButton1.setOpaque(true);
        newButton1.setRequestFocusEnabled(false);
        newButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newButton1ActionPerformed(evt);
            }
        });

        newButton2.setBackground(new java.awt.Color(102, 102, 102));
        newButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/new16.png"))); // NOI18N
        newButton2.setFocusPainted(false);
        newButton2.setFocusable(false);
        newButton2.setOpaque(true);
        newButton2.setRequestFocusEnabled(false);
        newButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newButton2ActionPerformed(evt);
            }
        });

        newButton3.setBackground(new java.awt.Color(102, 102, 102));
        newButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/new16.png"))); // NOI18N
        newButton3.setFocusPainted(false);
        newButton3.setFocusable(false);
        newButton3.setOpaque(true);
        newButton3.setRequestFocusEnabled(false);
        newButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newButton3ActionPerformed(evt);
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
                .addComponent(newButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(newButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(newButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(909, Short.MAX_VALUE))
        );
        iconBarLayout.setVerticalGroup(
            iconBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(iconBarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(iconBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(newButton3)
                    .addComponent(newButton2)
                    .addComponent(newButton1)
                    .addComponent(newButton)
                    .addComponent(openButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        centralPanel.setBackground(new java.awt.Color(51, 51, 51));
        centralPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        tabbedPanel.setBackground(new java.awt.Color(102, 102, 102));
        tabbedPanel.setForeground(new java.awt.Color(255, 255, 255));

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
        jMenuBar1.add(fileMenu);

        languageMenu.setForeground(new java.awt.Color(255, 255, 255));
        languageMenu.setText("Languages");
        languageMenu.setFont(new java.awt.Font("URW Gothic L", 0, 13)); // NOI18N
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

    private void newButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_newButton1ActionPerformed

    private void newButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_newButton2ActionPerformed

    private void newButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_newButton3ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel centralPanel;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JPanel iconBar;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu languageMenu;
    private javax.swing.JButton newButton;
    private javax.swing.JButton newButton1;
    private javax.swing.JButton newButton2;
    private javax.swing.JButton newButton3;
    private javax.swing.JButton openButton;
    private javax.swing.JMenu runMenu;
    private javax.swing.JTabbedPane tabbedPanel;
    private javax.swing.JMenu viewMenu;
    // End of variables declaration//GEN-END:variables
}
