/*
 * OffenePflege
 * Copyright (C) 2008 Torsten Löhr
 * This program is free software; you can redistribute it and/or modify it under the terms of the 
 * GNU General Public License V2 as published by the Free Software Foundation
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even 
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General 
 * Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with this program; if not, write to 
 * the Free Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110, USA
 * www.offene-pflege.de
 * ------------------------ 
 * Auf deutsch (freie Übersetzung. Rechtlich gilt die englische Version)
 * Dieses Programm ist freie Software. Sie können es unter den Bedingungen der GNU General Public License, 
 * wie von der Free Software Foundation veröffentlicht, weitergeben und/oder modifizieren, gemäß Version 2 der Lizenz.
 *
 * Die Veröffentlichung dieses Programms erfolgt in der Hoffnung, daß es Ihnen von Nutzen sein wird, aber 
 * OHNE IRGENDEINE GARANTIE, sogar ohne die implizite Garantie der MARKTREIFE oder der VERWENDBARKEIT FÜR EINEN 
 * BESTIMMTEN ZWECK. Details finden Sie in der GNU General Public License.
 *
 * Sie sollten ein Exemplar der GNU General Public License zusammen mit diesem Programm erhalten haben. Falls nicht, 
 * schreiben Sie an die Free Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110, USA.
 * 
 */
package op.care.nursingprocess;

import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.event.*;

import op.tools.SYSTools;

import javax.swing.*;

/**
 * @author root
 */
public class DlgMassSelect extends javax.swing.JDialog {

    /**
     * Creates new form DlgMassSelect
     */
    private DlgMassSelect(java.awt.Frame parent, boolean multiselect) {
        super(parent, true);
        initComponents();
        initDialog(multiselect);
        SYSTools.centerOnParent(parent, this);
    }

    private DlgMassSelect(JDialog parent, boolean multiselect) {
        super(parent, true);
        initComponents();
        initDialog(multiselect);
        SYSTools.centerOnParent(parent, this);
    }

    private void initDialog(boolean multiselect) {
        if (multiselect) {
            lstMass.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            lblTitel.setText("Massnahmen auswählen");
        } else {
            lstMass.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            lblTitel.setText("Wählen sie eine Massnahme aus");
        }
        setTitle(SYSTools.getWindowTitle("Massnahmen-Auswahl"));
        reloadMassnahmen();
    }

//    public static Object[] showDialog(JDialog parent, boolean multiselect) {
//        DlgMassSelect dlg = new DlgMassSelect(parent, multiselect);
//        dlg.setVisible(true);
//        Object[] sel = dlg.getSelection();
//        dlg.dispose();
//        return sel;
//    }

    public static Object[] showDialog(JFrame parent, boolean multiselect) {
        DlgMassSelect dlg = new DlgMassSelect(parent, multiselect);
        dlg.setVisible(true);
        Object[] sel = dlg.getSelection();
        dlg.dispose();
        return sel;
    }

    private Object[] getSelection() {
        return lstMass.getSelectedValues();
    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        lblTitel = new JLabel();
        jPanel1 = new JPanel();
        txtSuche = new JTextField();
        jLabel1 = new JLabel();
        jScrollPane1 = new JScrollPane();
        lstMass = new JList();
        btnCancel = new JButton();
        btnOK = new JButton();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Container contentPane = getContentPane();

        //---- lblTitel ----
        lblTitel.setFont(new Font("Dialog", Font.BOLD, 18));
        lblTitel.setText("Massnahmen ausw\u00e4hlen");

        //======== jPanel1 ========
        {
            jPanel1.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));

            //---- txtSuche ----
            txtSuche.addCaretListener(new CaretListener() {
                @Override
                public void caretUpdate(CaretEvent e) {
                    txtSucheCaretUpdate(e);
                }
            });

            //---- jLabel1 ----
            jLabel1.setText("Suchtext:");

            //======== jScrollPane1 ========
            {

                //---- lstMass ----
                lstMass.setModel(new AbstractListModel() {
                    String[] values = {
                        "Item 1",
                        "Item 2",
                        "Item 3",
                        "Item 4",
                        "Item 5"
                    };
                    @Override
                    public int getSize() { return values.length; }
                    @Override
                    public Object getElementAt(int i) { return values[i]; }
                });
                jScrollPane1.setViewportView(lstMass);
            }

            GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
            jPanel1.setLayout(jPanel1Layout);
            jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup()
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup()
                            .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 569, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSuche, GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)))
                        .addContainerGap())
            );
            jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup()
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtSuche, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                        .addContainerGap())
            );
        }

        //---- btnCancel ----
        btnCancel.setIcon(new ImageIcon(getClass().getResource("/artwork/22x22/cancel.png")));
        btnCancel.setText("Abbrechen");
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnCancelActionPerformed(e);
            }
        });

        //---- btnOK ----
        btnOK.setIcon(new ImageIcon(getClass().getResource("/artwork/22x22/apply.png")));
        btnOK.setText("Hinzuf\u00fcgen");
        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnOKActionPerformed(e);
            }
        });

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblTitel, GroupLayout.DEFAULT_SIZE, 599, Short.MAX_VALUE)
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addComponent(btnOK)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnCancel)))
                    .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblTitel)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(btnCancel)
                        .addComponent(btnOK))
                    .addContainerGap())
        );
        pack();
        setLocationRelativeTo(getOwner());
    }// </editor-fold>//GEN-END:initComponents

    private void btnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKActionPerformed
        setVisible(false);
    }//GEN-LAST:event_btnOKActionPerformed

    private void txtSucheCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtSucheCaretUpdate
        reloadMassnahmen();
    }//GEN-LAST:event_txtSucheCaretUpdate

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        lstMass.clearSelection();
        setVisible(false);
    }//GEN-LAST:event_btnCancelActionPerformed

    private void reloadMassnahmen() {
//        lstMass.setModel(SYSTools.cmb2lst(new DefaultComboBoxModel(InterventionTools.findMassnahmenBy(InterventionTools.MODE_NUR_PFLEGE, txtSuche.getText()).toArray())));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JLabel lblTitel;
    private JPanel jPanel1;
    private JTextField txtSuche;
    private JLabel jLabel1;
    private JScrollPane jScrollPane1;
    private JList lstMass;
    private JButton btnCancel;
    private JButton btnOK;
    // End of variables declaration//GEN-END:variables
}