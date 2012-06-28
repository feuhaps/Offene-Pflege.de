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

package op.care.schichtleitung;

import com.toedter.calendar.JDateChooser;
import entity.Bewohner;
import entity.EinrichtungenTools;
import entity.files.SYSFilesTools;
import op.tools.CleanablePanel;
import op.tools.*;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * @author tloehr
 */
public class PnlSchichtleitung extends CleanablePanel {
    private javax.swing.JFrame parent;
    private boolean initPhase;

    /**
     * Creates new form PnlSchichtleitung
     */
    public PnlSchichtleitung(javax.swing.JFrame parent) {
        this.parent = parent;
        initPhase = true;
        initComponents();
        initDialog();
        initPhase = false;
    }

    public void initDialog() {

        EinrichtungenTools.setComboBox(cmbEinrichtung);

        // Stationsauswahl auf die richtige Station setzen, wenn vorhanden.
//        String station = OPDE.getProps().getProperty("station");
//        if (station != null && !station.equals("")){
//            SYSTools.selectInComboBox(cmbEinrichtung, DBRetrieve.getEinrichtung2Station(station));
//        }
        cmbSchicht.setSelectedIndex(SYSCalendar.ermittleSchicht() + 1);
        jdcDatum.setDate(SYSCalendar.today_date());
        txtHTML.setContentType("text/html");
        reloadDisplay();
    }

     @Override
    public void reload() {
         reloadDisplay();
    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jToolBar1 = new javax.swing.JToolBar();
        btnPrint = new javax.swing.JButton();
        jdcDatum = new com.toedter.calendar.JDateChooser();
        cmbEinrichtung = new javax.swing.JComboBox();
        cmbSchicht = new javax.swing.JComboBox();
        jspHTML = new javax.swing.JScrollPane();
        txtHTML = new javax.swing.JTextPane();
        btnReload = new javax.swing.JButton();

        jToolBar1.setFloatable(false);

        btnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/artwork/22x22/printer.png"))); // NOI18N
        btnPrint.setText("Drucken");
        btnPrint.setEnabled(false);
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });
        jToolBar1.add(btnPrint);

        jdcDatum.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jdcDatumPropertyChange(evt);
            }
        });

        cmbEinrichtung.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Item 1", "Item 2", "Item 3", "Item 4"}));
        cmbEinrichtung.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbEinrichtungItemStateChanged(evt);
            }
        });

        cmbSchicht.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Alles", "Nacht, morgens", "Früh", "Spät", "Nacht, abends"}));
        cmbSchicht.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbSchichtItemStateChanged(evt);
            }
        });

        jspHTML.setViewportView(txtHTML);

        btnReload.setBackground(new java.awt.Color(255, 255, 255));
        btnReload.setIcon(new javax.swing.ImageIcon(getClass().getResource("/artwork/16x16/reload.png"))); // NOI18N
        btnReload.setBorderPainted(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jdcDatum, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbEinrichtung, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbSchicht, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnReload, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(97, Short.MAX_VALUE))
                        .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 787, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jspHTML, javax.swing.GroupLayout.DEFAULT_SIZE, 747, Short.MAX_VALUE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(19, 19, 19)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(cmbEinrichtung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(cmbSchicht, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(btnReload))
                                        .addComponent(jdcDatum, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jspHTML, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        try {
            // Create temp file.
            File temp = File.createTempFile("schichtleitung", ".html");

            // Delete temp file when program exits.
            temp.deleteOnExit();

            // Write to temp file
            BufferedWriter out = new BufferedWriter(new FileWriter(temp));
            out.write(txtHTML.getText());
            out.close();
            SYSFilesTools.handleFile(temp, Desktop.Action.OPEN);
        } catch (IOException e) {
            new DlgException(e);
        }
    }//GEN-LAST:event_btnPrintActionPerformed

    private void cmbSchichtItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbSchichtItemStateChanged
        if (initPhase) return;
        reloadDisplay();
    }//GEN-LAST:event_cmbSchichtItemStateChanged

    private void cmbEinrichtungItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbEinrichtungItemStateChanged
        if (initPhase) return;
        reloadDisplay();
    }//GEN-LAST:event_cmbEinrichtungItemStateChanged

    private void jdcDatumPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jdcDatumPropertyChange
        if (initPhase) return;
        if (!evt.getPropertyName().equals("date")) return;
        SYSCalendar.checkJDC((JDateChooser) evt.getSource());
        reloadDisplay();
    }//GEN-LAST:event_jdcDatumPropertyChange

    @Override
    public void cleanup() {
        SYSTools.unregisterListeners(this);
    }

    private String getAuswertung() {
        ListElement le = (ListElement) cmbEinrichtung.getSelectedItem();
        String sSchicht;
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        int schicht = cmbSchicht.getSelectedIndex() - 1;
        if (schicht != SYSConst.ZEIT_ALLES) {
            sSchicht = SYSConst.ZEIT[schicht + 1];
        } else {
            sSchicht = "Alles";
        }
        String s = "<html><body>";
        s += "<h1>Auswertung für die Schichtleitung: " + sdf.format(jdcDatum.getDate()) + ", Schicht: " + sSchicht + "</h1>";
        s += DBHandling.leereBHPs(schicht, jdcDatum.getDate(), le.getData());
        s += DBHandling.keineTBs(jdcDatum.getDate(), le.getData());
        s += DBHandling.geringeVorraete(le.getData());
        s += "</body></html>";
        return s;
    }

    private void reloadDisplay() {
        txtHTML.setText(getAuswertung());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnReload;
    private javax.swing.JComboBox cmbEinrichtung;
    private javax.swing.JComboBox cmbSchicht;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JToolBar jToolBar1;
    private com.toedter.calendar.JDateChooser jdcDatum;
    private javax.swing.JScrollPane jspHTML;
    private javax.swing.JTextPane txtHTML;
    // End of variables declaration//GEN-END:variables

}
