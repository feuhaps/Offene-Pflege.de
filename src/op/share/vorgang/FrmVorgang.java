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
package op.share.vorgang;

import op.OPDE;
import op.tools.ListElement;
import op.tools.SYSTools;

import javax.swing.*;

/**
 * @author tloehr
 */
public class FrmVorgang extends javax.swing.JFrame {

    private JPanel pnlMain;
    String currentBW = "";
    String besitzer = "";

    public FrmVorgang() {
        this(null, (OPDE.isAdmin() ? null : OPDE.getLogin().getUser().getUKennung()));
    }

    /**
     * Creates new form PnlVorgang
     */
    public FrmVorgang(String bwkennung, String besitzer) {
        initComponents();
        this.currentBW = bwkennung;
        this.besitzer = besitzer;
        int wv = (Integer) spinWV.getValue();
        pnlMain = new PnlVorgang(this, PnlVorgang.MODE_CALLED_FROM_VERWALTUNG, currentBW, cbArchiv.isSelected(), besitzer, wv, null);
        jspMain.setViewportView(pnlMain);
        spinWV.setEnabled(false);
        spinWV.setModel(new SpinnerNumberModel(0, 0, 30, 1));

        if (besitzer != null) {
            cmbBesitzer.setModel(new DefaultComboBoxModel());
            cmbBesitzer.setEnabled(false);
        } else {
            cmbBesitzer.setModel(SYSTools.getUserList(1));
            cmbBesitzer.setSelectedIndex(0);
            this.besitzer = ((ListElement) cmbBesitzer.getSelectedItem()).getData();
            cmbBesitzer.setEnabled(true);
        }

        setTitle(SYSTools.getWindowTitle("Vorgangsverwaltung"));
        setVisible(true);
    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        btnNew = new javax.swing.JButton();
        btnPrint = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        txtBW = new javax.swing.JTextField();
        btnFind = new javax.swing.JButton();
        cbArchiv = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        cmbBesitzer = new javax.swing.JComboBox();
        cbWV = new javax.swing.JCheckBox();
        spinWV = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        jspMain = new javax.swing.JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        btnNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/artwork/22x22/filenew.png"))); // NOI18N
        btnNew.setText("Neu");
        btnNew.setFocusable(false);
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });
        jToolBar1.add(btnNew);

        btnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/artwork/22x22/printer.png"))); // NOI18N
        btnPrint.setText("Drucken");
        btnPrint.setFocusable(false);
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });
        jToolBar1.add(btnPrint);

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txtBW.setToolTipText("Sie können hier Teile des Bewohner Nachnamens oder dessen Kennung eingeben. \"-\" heisst alle Bewohner. \"*\" heisst nur Vorgänge ohne BW Zuordnung.");
        txtBW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBWActionPerformed(evt);
            }
        });
        txtBW.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtBWFocusGained(evt);
            }
        });

        btnFind.setIcon(new javax.swing.ImageIcon(getClass().getResource("/artwork/16x16/search.png"))); // NOI18N
        btnFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindActionPerformed(evt);
            }
        });

        cbArchiv.setText("auch abgeschlossene Vorgänge anzeigen");
        cbArchiv.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbArchivItemStateChanged(evt);
            }
        });

        jLabel1.setText("BewohnerIn suchen:");

        cmbBesitzer.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Item 1", "Item 2", "Item 3", "Item 4"}));
        cmbBesitzer.setToolTipText("Nur für bestimmte Besitzer anzeigen");
        cmbBesitzer.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbBesitzerItemStateChanged(evt);
            }
        });

        cbWV.setText("Wiedervorlage in:");
        cbWV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbWVActionPerformed(evt);
            }
        });

        spinWV.setToolTipText("Tagen");
        spinWV.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinWVStateChanged(evt);
            }
        });

        jLabel2.setText("Eigentümer:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(cbArchiv)
                                                .addGap(18, 18, 18)
                                                .addComponent(cbWV)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(spinWV, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtBW, javax.swing.GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnFind))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cmbBesitzer, 0, 442, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnFind)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(txtBW, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel1)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(cbWV)
                                                .addComponent(spinWV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(cbArchiv))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(cmbBesitzer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jspMain, javax.swing.GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jspMain, javax.swing.GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
                                .addContainerGap())
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width - 602) / 2, (screenSize.height - 557) / 2, 602, 557);
    }// </editor-fold>//GEN-END:initComponents

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        ((PnlVorgang) pnlMain).neu();
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        ((PnlVorgang) pnlMain).print();
    }//GEN-LAST:event_btnPrintActionPerformed

    private void txtBWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBWActionPerformed
        String prevBW = currentBW;
        ((PnlVorgang) pnlMain).setVorgangID(0); // Wenn er hier reingeht, dann wollen wir den VorgangID Modus nicht mehr.
        if (txtBW.getText().equals("-")) {
            currentBW = null;
            reloadDisplay();
        } else if (txtBW.getText().equals("*")) {
            currentBW = "";
            reloadDisplay();
        } else {
            currentBW = SYSTools.findeBW(this, txtBW.getText(), OPDE.isAdmin());
            if (currentBW.equals("")) {
                JOptionPane.showMessageDialog(this, "Keine(n) passende(n) Bewohner(in) gefunden.", "Hinweis", JOptionPane.INFORMATION_MESSAGE);
                //tblTG.setModel(new DefaultTableModel());
                currentBW = prevBW;
            }
            if (!currentBW.equals("") || !prevBW.equals("")) { // Sonst kommt die Meldung "keine passenden BW..." zweimal bei nicht vorhandenen Bewohnern
                reloadDisplay();
            }
        }
    }//GEN-LAST:event_txtBWActionPerformed

    private void reloadDisplay() {
        int wv = (Integer) spinWV.getValue();
        ((PnlVorgang) pnlMain).reload(cbArchiv.isSelected(), currentBW, besitzer, wv);
        //currentBW = "";
    }

    public void showVorgangID(long vorgangid) {
        int wv = (Integer) spinWV.getValue();
        ((PnlVorgang) pnlMain).setVorgangID(vorgangid);
        ((PnlVorgang) pnlMain).reload(cbArchiv.isSelected(), currentBW, besitzer, wv);
    }

    private void txtBWFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBWFocusGained
        txtBW.setSelectionStart(0);
        txtBW.setSelectionEnd(txtBW.getText().length());
    }//GEN-LAST:event_txtBWFocusGained

    private void btnFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindActionPerformed
        txtBWActionPerformed(evt);
    }//GEN-LAST:event_btnFindActionPerformed

    private void cbArchivItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbArchivItemStateChanged
        ((PnlVorgang) pnlMain).reload(cbArchiv.isSelected());
    }//GEN-LAST:event_cbArchivItemStateChanged

    private void cmbBesitzerItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbBesitzerItemStateChanged
        ListElement el = (ListElement) cmbBesitzer.getSelectedItem();
        //OPDE.debug(el.getData());
        this.besitzer = el.getData();

        reloadDisplay();
    }//GEN-LAST:event_cmbBesitzerItemStateChanged

    private void cbWVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbWVActionPerformed
        if (cbWV.isSelected()) {
            spinWV.setEnabled(true);
            spinWV.setValue(4);
        } else {
            spinWV.setEnabled(false);
            spinWV.setValue(0);
        }
        reloadDisplay();
    }//GEN-LAST:event_cbWVActionPerformed

    private void spinWVStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinWVStateChanged
        reloadDisplay();
    }//GEN-LAST:event_spinWVStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFind;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnPrint;
    private javax.swing.JCheckBox cbArchiv;
    private javax.swing.JCheckBox cbWV;
    private javax.swing.JComboBox cmbBesitzer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JScrollPane jspMain;
    private javax.swing.JSpinner spinWV;
    private javax.swing.JTextField txtBW;
    // End of variables declaration//GEN-END:variables
}
