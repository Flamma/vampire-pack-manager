/*
 * PackPanel.java
 *
 * Created on 12 de noviembre de 2011, 13:53
 */

package com.asqueados.vpm.view.swing;

import com.asqueados.vpm.entities.Pack;
import com.asqueados.vpm.entities.Personage;
import java.util.List;

/**
 *
 * @author  Pablo J. Urbano Santos <flamma at member.fsf.org>
 */
public class PackPanel extends javax.swing.JPanel {
    private Pack pack;
    
    /** Creates new form PackPanel */
    public PackPanel() {
        initComponents();
    }
    
    public PackPanel(Pack pack) {
        this.pack = pack;
        initComponents();
        loadPack();
    }
    
    public void setPack(Pack pack) {
        this.pack = pack;
        loadPack();
    }
    
    public void loadPack() {
        if(pack!=null) {
            String name = (String) pack.getValue("name");
            if(name!=null)
                setName(name);
            
            charsPanel.removeAll();
            
            List<Personage> characters = pack.getCharacters();
            
            for(Personage character: characters) {
                SimplePersonagePanel charPanel = new SimplePersonagePanel(character);
                charsPanel.add(charPanel);
            }      
        }
    }
    
    public void setName(String name) {
        nameLabel.setText(name);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nameLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        charsPanel = new javax.swing.JPanel();

        setLayout(new java.awt.BorderLayout());

        nameLabel.setText("Pack name");
        add(nameLabel, java.awt.BorderLayout.PAGE_START);

        charsPanel.setLayout(new java.awt.GridLayout(0, 1));
        jScrollPane1.setViewportView(charsPanel);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel charsPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel nameLabel;
    // End of variables declaration//GEN-END:variables
    
}