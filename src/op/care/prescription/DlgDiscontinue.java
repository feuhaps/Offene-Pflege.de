/*
 * OffenePflege
 * Copyright (C) 2006-2012 Torsten Löhr
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

package op.care.prescription;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import entity.prescription.*;
import op.OPDE;
import op.threads.DisplayMessage;
import op.tools.MyJDialog;
import op.tools.SYSTools;
import org.apache.commons.collections.Closure;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @author root
 */
public class DlgDiscontinue extends MyJDialog {
    private Prescription prescription;
    private Closure actionBlock;
    private java.util.List<GP> listAerzte;
    private java.util.List<Hospital> listKH;

    /**
     * Creates new form DlgDiscontinue
     */
    private void cmbArztAbKeyPressed(KeyEvent e) {
        final String searchKey = String.valueOf(e.getKeyChar());
        GP doc = (GP) CollectionUtils.find(listAerzte, new Predicate() {
            @Override
            public boolean evaluate(Object o) {
                return o != null && ((GP) o).getName().toLowerCase().charAt(0) == searchKey.toLowerCase().charAt(0);
            }
        });
        if (doc != null) {
            cmbArztAb.setSelectedItem(doc);
        }
    }

    public DlgDiscontinue(Prescription prescription, Closure actionBlock) {
        super(false);
        this.actionBlock = actionBlock;
        this.prescription = prescription;
        initComponents();
        lblQuestion.setText(SYSTools.xx( "nursingrecords.prescription.dlgDiscontinue.question"));
        fillCMBs();
        setVisible(true);
    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the PrinterForm Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Erzeugter Quelltext ">//GEN-BEGIN:initComponents
    private void initComponents() {
        lblQuestion = new JLabel();
        cmbArztAb = new JComboBox();
        cmbKHAb = new JComboBox();
        panel1 = new JPanel();
        btnCancel = new JButton();
        btnOK = new JButton();

        //======== this ========
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        Container contentPane = getContentPane();
        contentPane.setLayout(new FormLayout(
            "$rgap, 200dlu:grow, $lcgap, $rgap",
            "$rgap, $lgap, default, 2*($lgap, fill:16dlu), $lgap, fill:default, $lgap, $rgap"));

        //---- lblQuestion ----
        lblQuestion.setText("Wer hat die Verordnung abgesetzt ?");
        lblQuestion.setFont(new Font("Arial", Font.PLAIN, 18));
        contentPane.add(lblQuestion, CC.xy(2, 3));

        //---- cmbArztAb ----
        cmbArztAb.setModel(new DefaultComboBoxModel(new String[] {
            "Item 1",
            "Item 2",
            "Item 3",
            "Item 4"
        }));
        cmbArztAb.setFont(new Font("Arial", Font.PLAIN, 14));
        cmbArztAb.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                cmbArztAbKeyPressed(e);
            }
        });
        contentPane.add(cmbArztAb, CC.xy(2, 5));

        //---- cmbKHAb ----
        cmbKHAb.setModel(new DefaultComboBoxModel(new String[] {
            "Item 1",
            "Item 2",
            "Item 3",
            "Item 4"
        }));
        cmbKHAb.setFont(new Font("Arial", Font.PLAIN, 14));
        contentPane.add(cmbKHAb, CC.xy(2, 7));

        //======== panel1 ========
        {
            panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));

            //---- btnCancel ----
            btnCancel.setIcon(new ImageIcon(getClass().getResource("/artwork/22x22/cancel.png")));
            btnCancel.setText(null);
            btnCancel.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    btnCancelActionPerformed(e);
                }
            });
            panel1.add(btnCancel);

            //---- btnOK ----
            btnOK.setIcon(new ImageIcon(getClass().getResource("/artwork/22x22/apply.png")));
            btnOK.setText(null);
            btnOK.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    btnOKActionPerformed(e);
                }
            });
            panel1.add(btnOK);
        }
        contentPane.add(panel1, CC.xy(2, 9, CC.RIGHT, CC.DEFAULT));
        setSize(490, 175);
        setLocationRelativeTo(getOwner());
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        prescription = null;
        dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    @Override
    public void dispose() {
        actionBlock.execute(prescription);
        super.dispose();
    }

    private void btnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKActionPerformed

        GP doc = (GP) cmbArztAb.getSelectedItem();
        Hospital hospital = (Hospital) cmbKHAb.getSelectedItem();

        if (doc == null && hospital == null) {
            OPDE.getDisplayManager().addSubMessage(new DisplayMessage( "nursingrecords.prescription.dlgDiscontinue.docandhospitalempty"));
        } else {
            prescription.setDocOFF(doc);
            prescription.setHospitalOFF(hospital);
            prescription.setUserOFF(OPDE.getLogin().getUser());
            dispose();
        }
    }//GEN-LAST:event_btnOKActionPerformed

    private void fillCMBs() {
        EntityManager em = OPDE.createEM();
        Query queryArzt = em.createQuery("SELECT a FROM GP a ORDER BY a.name, a.vorname");
        listAerzte = queryArzt.getResultList();
        listAerzte.add(0, null);

        Query queryKH = em.createQuery("SELECT k FROM Hospital k ORDER BY k.name");
        listKH = queryKH.getResultList();
        listKH.add(0, null);

        cmbArztAb.setModel(new DefaultComboBoxModel(listAerzte.toArray()));
        cmbArztAb.setRenderer(GPTools.getRenderer());
        cmbArztAb.setSelectedIndex(0);

        cmbKHAb.setModel(new DefaultComboBoxModel(listKH.toArray()));
        cmbKHAb.setRenderer(HospitalTools.getKHRenderer());
        cmbKHAb.setSelectedIndex(0);

        em.close();
    }


    // Variablendeklaration - nicht modifizieren//GEN-BEGIN:variables
    private JLabel lblQuestion;
    private JComboBox cmbArztAb;
    private JComboBox cmbKHAb;
    private JPanel panel1;
    private JButton btnCancel;
    private JButton btnOK;
    // Ende der Variablendeklaration//GEN-END:variables

}
