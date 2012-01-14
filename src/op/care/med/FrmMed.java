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


package op.care.med;

import entity.EntityTools;
import entity.verordnungen.*;
import op.OPDE;
import op.care.med.vorrat.DlgBestand;
import op.care.med.vorrat.DlgVorrat;
import op.tools.SYSTools;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.*;
import javax.swing.border.SoftBevelBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * @author tloehr
 */
public class FrmMed extends javax.swing.JFrame {

    private DefaultTreeModel tree;
    private MedProdukte produkt;
    private String template;
    private boolean ignoreCaret;
    private JPopupMenu menu;
    private JDialog parent;
    private JFrame thisFrame;

    /**
     * Creates new form FrmMed
     */
    public FrmMed() {
        this.parent = parent;
        this.template = "";
        initDialog();
    }

    /**
     * Creates new form FrmMed
     */
    public FrmMed(String template) {
        this.template = template;
        thisFrame = this;
        initDialog();
    }

    private void initDialog() {
        initComponents();

        this.setTitle(SYSTools.getWindowTitle("Medikamentenverwaltung"));
        // erstmal alles leer
        produkt = null;
        treeMed.setModel(new DefaultTreeModel(new DefaultMutableTreeNode()));
        treeMed.setVisible(false);
        lstPraep.setModel(new DefaultListModel());
        lstPraep.setCellRenderer(MedProdukteTools.getMedProdukteRenderer());
        btnVorrat.setEnabled(true);

        SYSTools.center(this);
        setVisible(true);
    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        jToolBar1 = new JToolBar();
        btnNew = new JButton();
        btnMed = new JButton();
        btnBuchen = new JButton();
        btnVorrat = new JButton();
        jLabel1 = new JLabel();
        jPanel1 = new JPanel();
        jScrollPane1 = new JScrollPane();
        treeMed = new JTree();
        jPanel2 = new JPanel();
        txtSuche = new JTextField();
        jScrollPane2 = new JScrollPane();
        lstPraep = new JList();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                formWindowOpened(e);
            }
        });
        Container contentPane = getContentPane();

        //======== jToolBar1 ========
        {
            jToolBar1.setFloatable(false);

            //---- btnNew ----
            btnNew.setIcon(new ImageIcon(getClass().getResource("/artwork/22x22/filenew.png")));
            btnNew.setText("Neues Medikament");
            btnNew.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    btnNewActionPerformed(e);
                }
            });
            jToolBar1.add(btnNew);

            //---- btnMed ----
            btnMed.setIcon(new ImageIcon(getClass().getResource("/artwork/22x22/wizard.png")));
            btnMed.setText("Assistent");
            btnMed.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    btnMedActionPerformed(e);
                }
            });
            jToolBar1.add(btnMed);

            //---- btnBuchen ----
            btnBuchen.setIcon(new ImageIcon(getClass().getResource("/artwork/22x22/shetaddrow.png")));
            btnBuchen.setText("Buchen");
            btnBuchen.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    btnBuchenActionPerformed(e);
                }
            });
            jToolBar1.add(btnBuchen);

            //---- btnVorrat ----
            btnVorrat.setIcon(new ImageIcon(getClass().getResource("/artwork/22x22/sheetremocolums.png")));
            btnVorrat.setText("Vorrat");
            btnVorrat.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    btnVorratActionPerformed(e);
                }
            });
            jToolBar1.add(btnVorrat);
        }

        //---- jLabel1 ----
        jLabel1.setFont(new Font("Dialog", Font.BOLD, 24));
        jLabel1.setText("Medikamente");

        //======== jPanel1 ========
        {
            jPanel1.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));

            //======== jScrollPane1 ========
            {

                //---- treeMed ----
                treeMed.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        treeMedMousePressed(e);
                    }
                });
                jScrollPane1.setViewportView(treeMed);
            }

            GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
            jPanel1.setLayout(jPanel1Layout);
            jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup()
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)
                        .addContainerGap())
            );
            jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup()
                    .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                        .addContainerGap())
            );
        }

        //======== jPanel2 ========
        {
            jPanel2.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));

            //---- txtSuche ----
            txtSuche.addCaretListener(new CaretListener() {
                @Override
                public void caretUpdate(CaretEvent e) {
                    txtSucheCaretUpdate(e);
                }
            });
            txtSuche.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    txtSucheActionPerformed(e);
                }
            });

            //======== jScrollPane2 ========
            {

                //---- lstPraep ----
                lstPraep.setModel(new AbstractListModel() {
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
                lstPraep.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                lstPraep.addListSelectionListener(new ListSelectionListener() {
                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        lstPraepValueChanged(e);
                    }
                });
                jScrollPane2.setViewportView(lstPraep);
            }

            GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
            jPanel2.setLayout(jPanel2Layout);
            jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup()
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(txtSuche, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE))
                        .addContainerGap())
            );
            jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup()
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtSuche, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                        .addContainerGap())
            );
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel1)
                    .addContainerGap(462, Short.MAX_VALUE))
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
                .addComponent(jToolBar1, GroupLayout.DEFAULT_SIZE, 638, Short.MAX_VALUE)
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addComponent(jToolBar1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel1)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap())
        );
        pack();
        setLocationRelativeTo(getOwner());
    }// </editor-fold>//GEN-END:initComponents


    private void btnVorratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVorratActionPerformed
        new DlgVorrat(this);
    }//GEN-LAST:event_btnVorratActionPerformed

    private void btnBuchenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuchenActionPerformed
        new DlgBestand(this);
    }//GEN-LAST:event_btnBuchenActionPerformed

    private void btnMedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMedActionPerformed
//String template = ( txtMed.getText().matches("^ß?\\d{7}") ? "" : txtMed.getText());
        //new DlgMed(this, template);
        ArrayList result = new ArrayList();
        result.add(txtSuche.getText());

        JDialog myDialog = new JDialog(this, "test", true);
       myDialog.setSize(1280, 800);
        PnlProdAssistant myPnl = new PnlProdAssistant();
        myDialog.setContentPane(myPnl);
        SYSTools.centerOnParent(this, myDialog);
        myDialog.setVisible(true);

//        new DlgMediAssistent(this, result);
//        if (result.size() > 0) {
//            ignoreCaret = true;
//            txtSuche.setText(result.get(0).toString());
//            ignoreCaret = false;
//            txtSucheCaretUpdate(null);
//        }
    }//GEN-LAST:event_btnMedActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        if (!template.equals("")) {
            btnNew.doClick();
        }
    }//GEN-LAST:event_formWindowOpened

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        new DlgProdukt(this, produkt, template);
        createTree();
    }//GEN-LAST:event_btnNewActionPerformed

    private void lstPraepValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstPraepValueChanged
        if (!evt.getValueIsAdjusting() && lstPraep.getSelectedValue() != null) {
            produkt = (MedProdukte) lstPraep.getSelectedValue();
            createTree();
        }
    }//GEN-LAST:event_lstPraepValueChanged

    private void txtSucheCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtSucheCaretUpdate
        if (ignoreCaret) return;
        treeMed.setCellRenderer(new DefaultTreeCellRenderer());
        treeMed.setModel(new DefaultTreeModel(new DefaultMutableTreeNode()));
        treeMed.setVisible(false);
        if (txtSuche.getText().equals("")) {
            lstPraep.setModel(new DefaultListModel());
        } else {
            EntityManager em = OPDE.createEM();
            Query query = em.createNamedQuery("MedProdukte.findByBezeichnungLike");
            query.setParameter("bezeichnung", "%" + txtSuche.getText() + "%");
            lstPraep.setModel(SYSTools.list2dlm(query.getResultList()));
            em.close();
        }
    }//GEN-LAST:event_txtSucheCaretUpdate

    private void txtSucheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSucheActionPerformed
//        if (lstPraep.getModel().getSize() > 0) {
//            ListElement le = (ListElement) lstPraep.getModel().getElementAt(0);
//            if (le != null) {
//                medpid = le.getPk();
//                createTree();
//            }
//        }
    }//GEN-LAST:event_txtSucheActionPerformed

    private void treeMedMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_treeMedMousePressed
        if (produkt == null) return;
        if (evt.isPopupTrigger()) {
            // Dieses Popupmenu für den Table
            SYSTools.unregisterListeners(menu);
            menu = new JPopupMenu();
            JMenuItem itemdaf = new JMenuItem("Neue Darreichungsform");
            itemdaf.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnNeuDAF(evt);
                }
            });
            menu.add(itemdaf);

            if (treeMed.getRowForLocation(evt.getX(), evt.getY()) != -1) {
                JMenuItem itemedit = null;
                JMenuItem itemdelete = null;
                JMenuItem itemnew = null;
                JMenuItem itempack = null;
                TreePath curPath = treeMed.getPathForLocation(evt.getX(), evt.getY());
                DefaultMutableTreeNode dmtn = (DefaultMutableTreeNode) curPath.getLastPathComponent();
                treeMed.setSelectionPath(curPath);
//                final ListElement le = (ListElement) dmtn.getUserObject();
//                int nodetype = ((Integer) le.getObject()).intValue();


                if (dmtn.getUserObject() instanceof Darreichung) {
                    final Darreichung darreichung = (Darreichung) dmtn.getUserObject();
                    itemedit = new JMenuItem("Bearbeiten");
                    itemedit.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            new DlgDAF(thisFrame, "", darreichung);
                            createTree();
                        }
                    });
                    itemdelete = new JMenuItem("Entfernen");
                    itemdelete.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            btnDeleteDAF(darreichung);
                        }
                    });
                    itempack = new JMenuItem("Neue Verpackung");
                    itempack.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {

                            new DlgPack(parent, "Neu", new MedPackung(darreichung));

                            createTree();
                        }
                    });
                } else if (dmtn.getUserObject() instanceof MedPackung) {
                    final MedPackung packung = (MedPackung) dmtn.getUserObject();
                    itemedit = new JMenuItem("Bearbeiten");
                    itemedit.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            new DlgPack(parent, "Bearbeiten", packung);
                            createTree();
                        }
                    });
                }

                if (itemedit != null || itemdelete != null || itemnew != null)
                    menu.add(new JSeparator(JSeparator.HORIZONTAL));
                if (itemnew != null) menu.add(itemnew);
                if (itempack != null) menu.add(itempack);
                if (itemedit != null) menu.add(itemedit);
                if (itemdelete != null) menu.add(itemdelete);
            }
            menu.show(evt.getComponent(), evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_treeMedMousePressed

    private void btnNeuDAF(java.awt.event.ActionEvent evt) {

        Darreichung darreichung = new Darreichung(produkt);

        new DlgDAF(this, "", darreichung);
        createTree();
    }


    private void btnEditDAF(Darreichung darreichung) {
        new DlgDAF(this, "", darreichung);
        createTree();
    }

    private void btnDeleteDAF(Darreichung darreichung) {
        if (JOptionPane.showConfirmDialog(this, "Damit werden auch alle Zuordnungen und Packungen gelöscht.\n\nSind Sie sicher ?", DarreichungTools.toPrettyString(darreichung) + " entfernen", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
            return;
        }

        EntityTools.delete(darreichung);
        createTree();
    }

    public void dispose() {
        SYSTools.unregisterListeners(menu);
        menu = null;
        SYSTools.unregisterListeners(this);
        super.dispose();
    }

    private void createTree() {
        if (produkt == null) return;
        treeMed.setVisible(true);
        tree = new DefaultTreeModel(getRoot());
        treeMed.setModel(tree);
        treeMed.setCellRenderer(new TreeRenderer());
        SYSTools.expandAll(treeMed);
    }

    private DefaultMutableTreeNode getRoot() {
        DefaultMutableTreeNode root;
        root = new DefaultMutableTreeNode(produkt);
        SYSTools.addAllNodes(root, getDAF());


        return root;
    }

    private java.util.List getDAF() {
        java.util.List result = new ArrayList();

        EntityManager em = OPDE.createEM();
        Query query = em.createNamedQuery("Darreichung.findByMedProdukt");
        query.setParameter("medProdukt", produkt);

        java.util.List<Darreichung> listDAF = query.getResultList();
        em.close();


        for (Darreichung daf : listDAF) {
            DefaultMutableTreeNode nodeDAF = new DefaultMutableTreeNode(daf);
            SYSTools.addAllNodes(nodeDAF, getPackung(daf));
            result.add(nodeDAF);
        }


        return result;
    }

    private java.util.List getPackung(Darreichung darreichung) {
        java.util.List result = new ArrayList();
        for (MedPackung packung : darreichung.getPackungen()) {
            result.add(new DefaultMutableTreeNode(packung));
        }


        return result;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JToolBar jToolBar1;
    private JButton btnNew;
    private JButton btnMed;
    private JButton btnBuchen;
    private JButton btnVorrat;
    private JLabel jLabel1;
    private JPanel jPanel1;
    private JScrollPane jScrollPane1;
    private JTree treeMed;
    private JPanel jPanel2;
    private JTextField txtSuche;
    private JScrollPane jScrollPane2;
    private JList lstPraep;
    // End of variables declaration//GEN-END:variables

    private class TreeRenderer extends JLabel implements TreeCellRenderer {


        TreeRenderer() {
            super();
        }

        @Override
        public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
            if (node.getUserObject() instanceof MedProdukte) {
                setIcon(new ImageIcon(getClass().getResource("/artwork/16x16/info.png")));
                setText(((MedProdukte) node.getUserObject()).getBezeichnung());
            } else if (node.getUserObject() instanceof Darreichung) {
                setIcon(new ImageIcon(getClass().getResource("/artwork/16x16/medical.png")));
                setText(DarreichungTools.toPrettyString((Darreichung) node.getUserObject()));
            } else if (node.getUserObject() instanceof MedPackung) {
                setIcon(new ImageIcon(getClass().getResource("/artwork/16x16/package.png")));
                setText(MedPackungTools.toPrettyString((MedPackung) node.getUserObject()));
            } else {
                setIcon(new ImageIcon(getClass().getResource("/artwork/16x16/filenew.png")));
                setText("");
            }


            return this;
        }

    }
}
