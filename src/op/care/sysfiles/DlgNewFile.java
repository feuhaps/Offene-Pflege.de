/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DlgNewFile.java
 *
 * Created on 05.04.2011, 17:03:15
 */
package op.care.sysfiles;

import java.awt.*;
import java.awt.event.*;
import javax.persistence.EntityManager;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;
import javax.swing.event.*;
import entity.*;
import entity.files.SYSFiles;
import entity.files.SYSFilesTools;
import entity.files.Sysbw2file;
import entity.files.Syspb2file;
import entity.system.SYSPropsTools;
import op.OPDE;
import op.tools.SYSTools;

import java.io.File;
import java.util.Date;

/**
 * @author tloehr
 */
public class DlgNewFile extends javax.swing.JDialog {

    File selectedFile;
    Object[] entities;
    private EntityManager em = OPDE.createEM();

    /**
     * Creates new form DlgNewFile
     */
    public DlgNewFile(java.awt.Frame parent) {
        this(parent, null);
    }

    /**
     * Creates new form DlgNewFile
     */
    public DlgNewFile(java.awt.Frame parent, Object entity) {
        this(parent, new Object[]{entity});
    }

    /**
     * Creates new form DlgNewFile
     */
    public DlgNewFile(java.awt.Frame parent, Object[] entities) {
        super(parent, false);
        this.entities = entities;
        initComponents();
        setTitle(SYSTools.getWindowTitle("Neue Datei anhängen"));
        SYSPropsTools.restoreState(this.getClass().getName() + "::cbBeleg", cbBeleg);
        SYSTools.centerOnParent(parent, this);
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
        txtFile = new JTextField();
        btnSelectFile = new JButton();
        lblFileExist = new JLabel();
        jScrollPane1 = new JScrollPane();
        txtBemerkung = new JTextPane();
        jLabel1 = new JLabel();
        cbBeleg = new JCheckBox();
        btnOk = new JButton();
        btnCancel = new JButton();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Container contentPane = getContentPane();

        //---- txtFile ----
        txtFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtFileActionPerformed(e);
            }
        });
        txtFile.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                txtFileFocusLost(e);
            }
        });

        //---- btnSelectFile ----
        btnSelectFile.setIcon(new ImageIcon(getClass().getResource("/artwork/22x22/fileopen.png")));
        btnSelectFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnSelectFileActionPerformed(e);
            }
        });

        //---- lblFileExist ----
        lblFileExist.setIcon(new ImageIcon(getClass().getResource("/artwork/22x22/ballred.png")));
        lblFileExist.setToolTipText("Datei nicht gefunden.");

        //======== jScrollPane1 ========
        {

            //---- txtBemerkung ----
            txtBemerkung.addCaretListener(new CaretListener() {
                @Override
                public void caretUpdate(CaretEvent e) {
                    txtBemerkungCaretUpdate(e);
                }
            });
            jScrollPane1.setViewportView(txtBemerkung);
        }

        //---- jLabel1 ----
        jLabel1.setText("Bemerkung:");

        //---- cbBeleg ----
        cbBeleg.setText("Einbuchungsbeleg drucken");
        cbBeleg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cbBelegActionPerformed(e);
            }
        });

        //---- btnOk ----
        btnOk.setIcon(new ImageIcon(getClass().getResource("/artwork/22x22/apply.png")));
        btnOk.setText("Ok");
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnOkActionPerformed(e);
            }
        });

        //---- btnCancel ----
        btnCancel.setIcon(new ImageIcon(getClass().getResource("/artwork/22x22/cancel.png")));
        btnCancel.setText("Abbrechen");
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnCancelActionPerformed(e);
            }
        });

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(cbBeleg)
                        .addComponent(jLabel1)
                        .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 653, Short.MAX_VALUE)
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addComponent(txtFile, GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblFileExist)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnSelectFile))
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addComponent(btnOk)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnCancel)))
                    .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtFile)
                        .addComponent(lblFileExist, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSelectFile, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE))
                    .addGap(18, 18, 18)
                    .addComponent(jLabel1)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                    .addGap(18, 18, 18)
                    .addComponent(cbBeleg)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(btnCancel)
                        .addComponent(btnOk))
                    .addContainerGap())
        );
        pack();
        setLocationRelativeTo(getOwner());
    }// </editor-fold>//GEN-END:initComponents

    private void cbBelegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbBelegActionPerformed
        SYSPropsTools.storeState(this.getClass().getName() + "::cbBeleg", cbBeleg);
    }//GEN-LAST:event_cbBelegActionPerformed

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        SYSFiles sysfile = SYSFilesTools.putFile(selectedFile);
        Object relationEntity;

        em.getTransaction().begin();
        try {
            for (int i = 0; i < entities.length; i++) {
                if (entities[i] instanceof Bewohner) {
                    relationEntity = new Sysbw2file(txtBemerkung.getText().trim(), sysfile, (Bewohner) entities[i], OPDE.getLogin().getUser());
                } else if (entities[i] instanceof Pflegeberichte) {
                    relationEntity = new Syspb2file(txtBemerkung.getText().trim(), sysfile, (Pflegeberichte) entities[i], OPDE.getLogin().getUser(), new Date());
                } else {
                    relationEntity = null;
                }

                if (relationEntity != null) {
                    em.persist(relationEntity);

                }
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            OPDE.getLogger().error(e.getMessage(), e);
            em.getTransaction().rollback();
        }
        dispose();
    }//GEN-LAST:event_btnOkActionPerformed

    private void setSaveButton() {
        btnOk.setEnabled(!txtBemerkung.getText().trim().equals("") && selectedFile != null && selectedFile.exists());
    }

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnSelectFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectFileActionPerformed
        File[] files = SYSTools.chooseFile(this, false);
        if (files != null && files.length > 0) {
            selectedFile = files[0];
            txtFile.setText(selectedFile.getAbsolutePath());
            lblFileExist.setIcon(new javax.swing.ImageIcon(getClass().getResource("/artwork/22x22/ballgreen.png")));
            lblFileExist.setToolTipText(null);
        } else {
            selectedFile = null;
            txtFile.setText("");
            lblFileExist.setIcon(new javax.swing.ImageIcon(getClass().getResource("/artwork/22x22/ballred.png")));
            lblFileExist.setToolTipText("Datei nicht gefunden.");
        }
        setSaveButton();
    }//GEN-LAST:event_btnSelectFileActionPerformed

    private void txtFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFileActionPerformed
        txtBemerkung.requestFocus();
    }//GEN-LAST:event_txtFileActionPerformed

    private void txtFileFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFileFocusLost
        selectedFile = new File(txtFile.getText().trim());
        if (selectedFile != null && selectedFile.exists()) {
            lblFileExist.setIcon(new javax.swing.ImageIcon(getClass().getResource("/artwork/22x22/ballgreen.png")));
            lblFileExist.setToolTipText(null);
        } else {
            lblFileExist.setIcon(new javax.swing.ImageIcon(getClass().getResource("/artwork/22x22/ballred.png")));
            lblFileExist.setToolTipText("Datei nicht gefunden.");
        }
        setSaveButton();
    }//GEN-LAST:event_txtFileFocusLost

    private void txtBemerkungCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBemerkungCaretUpdate
        setSaveButton();
    }//GEN-LAST:event_txtBemerkungCaretUpdate

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JTextField txtFile;
    private JButton btnSelectFile;
    private JLabel lblFileExist;
    private JScrollPane jScrollPane1;
    private JTextPane txtBemerkung;
    private JLabel jLabel1;
    private JCheckBox cbBeleg;
    private JButton btnOk;
    private JButton btnCancel;
    // End of variables declaration//GEN-END:variables
}
