/*
 * PersonagePanel.java
 *
 * Created on 23 de octubre de 2011, 11:14
 */

package com.asqueados.vpm.view.swing;
import com.asqueados.vpm.entities.Personage;
import com.asqueados.vpm.entities.Trait;

/**
 *
 * @author  Pablo J. Urbano Santos <flamma at member.fsf.org>
 */
public class PersonagePanel extends javax.swing.JPanel {
    private Personage character;
    
    /** Creates new form PersonagePanel */
    public PersonagePanel() {
        initComponents();
    }
    
    public PersonagePanel(Personage character) {
        this.character = character;
        initComponents();

        if(character!=null) {
            Trait nameTrait = character.getTrait("name");
            if(nameTrait != null) {
                String name = (String) nameTrait.getValue();
                nameLabel.setText(name);
            }       
        }
                
        
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nameLabel = new javax.swing.JLabel();
        portraitPanel = new com.asqueados.vpm.view.swing.PersonagePortraitPanel(character);
        attributesPanel = new com.asqueados.vpm.view.swing.PersonageAttributesPanel(character);
        skillsPanel = new com.asqueados.vpm.view.swing.PersonageSkillsPanel(character);

        nameLabel.setFont(new java.awt.Font("Cantarell", 0, 24));
        nameLabel.setText("Name Lastname"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(portraitPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(attributesPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(skillsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)))
                    .addComponent(nameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(portraitPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(attributesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(skillsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)))
                .addContainerGap(137, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.asqueados.vpm.view.swing.PersonageAttributesPanel attributesPanel;
    private javax.swing.JLabel nameLabel;
    private com.asqueados.vpm.view.swing.PersonagePortraitPanel portraitPanel;
    private com.asqueados.vpm.view.swing.PersonageSkillsPanel skillsPanel;
    // End of variables declaration//GEN-END:variables
    
}
