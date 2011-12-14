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
package op.care.verordnung;

import java.beans.*;

import com.toedter.calendar.JDateChooser;
import entity.*;
import entity.verordnungen.*;
import op.OPDE;
import op.care.med.DlgMediAssistent;
import op.tools.DBHandling;
import op.tools.*;
import org.apache.commons.beanutils.BeanUtils;
import tablerenderer.RNDHTML;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.swing.*;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeListener;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author tloehr
 */
public class DlgVerordnung extends javax.swing.JDialog {

    public static final int NEW_MODE = 1; // Neu
    public static final int EDIT_MODE = 2; // Korrigieren
    public static final int CHANGE_MODE = 3; // Ändern

    private java.awt.Frame parent;
    private boolean ignoreSitCaret;
    private boolean ignoreEvent;


    private JPopupMenu menu;
    private PropertyChangeListener myPropertyChangeListener;
    private int editMode;

    Verordnung verordnung = null, oldVerordnung = null;


    /**
     * Creates new form DlgVerordnung
     */
    public DlgVerordnung(java.awt.Frame parent, Verordnung verordnung, int mode) {
        super(parent, true);
        this.parent = parent;

        this.editMode = mode;
        if (editMode == CHANGE_MODE){
            try {
                this.oldVerordnung = verordnung;
                this.verordnung = (Verordnung) BeanUtils.cloneBean(verordnung);
            } catch (IllegalAccessException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (InstantiationException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (InvocationTargetException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (NoSuchMethodException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        } else {
            this.verordnung = verordnung;
        }
        initDialog();
    }

    private void cbStellplanActionPerformed(ActionEvent e) {
        if (ignoreEvent) {
            return;
        }
        verordnung.setStellplan(cbStellplan.isSelected());
    }

    private void jdcANPropertyChange(PropertyChangeEvent e) {
        if (ignoreEvent) {
            return;
        }
        verordnung.setAnDatum(jdcAN.getDate());
        saveOK();
    }

    private void jdcABPropertyChange(PropertyChangeEvent e) {
        if (ignoreEvent) {
            return;
        }
        verordnung.setAbDatum(jdcAB.getDate());
        saveOK();
    }

    public DlgVerordnung(java.awt.Frame parent, Bewohner bewohner) {
        super(parent, true);
        this.parent = parent;
        this.verordnung = new Verordnung(bewohner);
        this.editMode = NEW_MODE;
        initDialog();
    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        lblBW = new JLabel();
        lblTitle = new JLabel();
        jSeparator1 = new JSeparator();
        jPanel1 = new JPanel();
        cmbSit = new JComboBox();
        txtMed = new JTextField();
        cmbMed = new JComboBox();
        cmbMass = new JComboBox();
        txtSit = new JTextField();
        btnBedarf = new JButton();
        btnMed = new JButton();
        jPanel8 = new JPanel();
        jspDosis = new JScrollPane();
        tblDosis = new JTable();
        cbPackEnde = new JCheckBox();
        cbStellplan = new JCheckBox();
        jLabel6 = new JLabel();
        jPanel3 = new JPanel();
        jPanel4 = new JPanel();
        jLabel3 = new JLabel();
        jdcAB = new JDateChooser();
        jLabel4 = new JLabel();
        cmbAB = new JComboBox();
        cbAB = new JCheckBox();
        lblAB = new JLabel();
        cmbKHAb = new JComboBox();
        jScrollPane3 = new JScrollPane();
        txtBemerkung = new JTextPane();
        jLabel5 = new JLabel();
        jPanel2 = new JPanel();
        jLabel1 = new JLabel();
        jdcAN = new JDateChooser();
        cmbAN = new JComboBox();
        jLabel2 = new JLabel();
        lblAN = new JLabel();
        cmbKHAn = new JComboBox();
        jSeparator2 = new JSeparator();
        btnSave = new JButton();
        btnClose = new JButton();
        jPanel5 = new JPanel();
        lblVerordnung = new JLabel();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                formWindowClosing(e);
            }
        });
        Container contentPane = getContentPane();

        //---- lblBW ----
        lblBW.setFont(new Font("Dialog", Font.BOLD, 18));
        lblBW.setForeground(new Color(255, 51, 0));
        lblBW.setText("Nachname, Vorname (*GebDatum, 00 Jahre) [??1]");

        //---- lblTitle ----
        lblTitle.setFont(new Font("Dialog", Font.BOLD, 24));
        lblTitle.setText("\u00c4rztliche Verordnung");

        //======== jPanel1 ========
        {
            jPanel1.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));

            //---- cmbSit ----
            cmbSit.setModel(new DefaultComboBoxModel(new String[] {
                "Item 1",
                "Item 2",
                "Item 3",
                "Item 4"
            }));
            cmbSit.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    cmbSitItemStateChanged(e);
                }
            });

            //---- txtMed ----
            txtMed.setText("jTextField1");
            txtMed.addCaretListener(new CaretListener() {
                @Override
                public void caretUpdate(CaretEvent e) {
                    txtMedCaretUpdate(e);
                }
            });
            txtMed.addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {
                    txtMedFocusGained(e);
                }
            });

            //---- cmbMed ----
            cmbMed.setModel(new DefaultComboBoxModel(new String[] {
                "Item 1",
                "Item 2",
                "Item 3",
                "Item 4"
            }));
            cmbMed.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    cmbMedItemStateChanged(e);
                }
            });

            //---- cmbMass ----
            cmbMass.setModel(new DefaultComboBoxModel(new String[] {
                "Item 1",
                "Item 2",
                "Item 3",
                "Item 4"
            }));
            cmbMass.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    cmbMassItemStateChanged(e);
                }
            });

            //---- txtSit ----
            txtSit.setText("jTextField1");
            txtSit.addCaretListener(new CaretListener() {
                @Override
                public void caretUpdate(CaretEvent e) {
                    txtSitCaretUpdate(e);
                }
            });

            //---- btnBedarf ----
            btnBedarf.setText("Situation");
            btnBedarf.setEnabled(false);
            btnBedarf.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    btnBedarfActionPerformed(e);
                }
            });

            //---- btnMed ----
            btnMed.setText("Medikament");
            btnMed.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    btnMedActionPerformed(e);
                }
            });

            //======== jPanel8 ========
            {
                jPanel8.setBorder(new TitledBorder("Dosis / H\u00e4ufigkeit"));

                //======== jspDosis ========
                {
                    jspDosis.setToolTipText("<html>Dr\u00fccken Sie die <b>rechte</b> Maustaste, wenn Sie neue Dosierungen eintragen wollen.</html>");
                    jspDosis.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            jspDosisMousePressed(e);
                        }
                    });
                    jspDosis.addComponentListener(new ComponentAdapter() {
                        @Override
                        public void componentResized(ComponentEvent e) {
                            jspDosisComponentResized(e);
                        }
                    });

                    //---- tblDosis ----
                    tblDosis.setModel(new DefaultTableModel(
                        new Object[][] {
                            {null, null, null, null},
                            {null, null, null, null},
                            {null, null, null, null},
                            {null, null, null, null},
                        },
                        new String[] {
                            "Title 1", "Title 2", "Title 3", "Title 4"
                        }
                    ));
                    tblDosis.setToolTipText("<html>Dr\u00fccken Sie die <b>rechte</b> Maustaste, wenn Sie Ver\u00e4nderungen vornehmen wollen.</html>");
                    tblDosis.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            tblDosisMousePressed(e);
                        }
                    });
                    jspDosis.setViewportView(tblDosis);
                }

                //---- cbPackEnde ----
                cbPackEnde.setText("Bis Packungsende");
                cbPackEnde.setBorder(BorderFactory.createEmptyBorder());
                cbPackEnde.setEnabled(false);
                cbPackEnde.setMargin(new Insets(0, 0, 0, 0));
                cbPackEnde.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cbPackEndeActionPerformed(e);
                    }
                });

                //---- cbStellplan ----
                cbStellplan.setText("Auf den Stellplan, auch wenn kein Medikament");
                cbStellplan.setBorder(BorderFactory.createEmptyBorder());
                cbStellplan.setMargin(new Insets(0, 0, 0, 0));
                cbStellplan.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cbStellplanActionPerformed(e);
                    }
                });

                GroupLayout jPanel8Layout = new GroupLayout(jPanel8);
                jPanel8.setLayout(jPanel8Layout);
                jPanel8Layout.setHorizontalGroup(
                    jPanel8Layout.createParallelGroup()
                        .addGroup(jPanel8Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel8Layout.createParallelGroup()
                                        .addComponent(jspDosis, GroupLayout.DEFAULT_SIZE, 519, Short.MAX_VALUE)
                                        .addGroup(jPanel8Layout.createSequentialGroup()
                                                .addComponent(cbPackEnde)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cbStellplan)))
                                .addContainerGap())
                );
                jPanel8Layout.setVerticalGroup(
                    jPanel8Layout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                .addComponent(jspDosis, GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(cbPackEnde)
                                        .addComponent(cbStellplan))
                                .addContainerGap())
                );
            }

            //---- jLabel6 ----
            jLabel6.setText("Massnahmen:");

            GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
            jPanel1.setLayout(jPanel1Layout);
            jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup()
                    .addGroup(jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel1Layout.createParallelGroup()
                                    .addComponent(jPanel8, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel6)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(cmbMass, 0, 485, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(btnBedarf, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(btnMed, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(jPanel1Layout.createParallelGroup()
                                                    .addComponent(txtSit, GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                                                    .addComponent(txtMed, GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE))
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(cmbSit, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(cmbMed, GroupLayout.PREFERRED_SIZE, 275, GroupLayout.PREFERRED_SIZE))))
                            .addContainerGap())
            );
            jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup()
                    .addGroup(jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel1Layout.createParallelGroup()
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                    .addComponent(btnMed)
                                                    .addComponent(txtMed, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                    .addComponent(btnBedarf)
                                                    .addComponent(txtSit, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(cmbMed, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(cmbSit, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(cmbMass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jPanel8, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addContainerGap())
            );
        }

        //======== jPanel3 ========
        {
            jPanel3.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));

            //======== jPanel4 ========
            {
                jPanel4.setBorder(new TitledBorder("Absetzung"));

                //---- jLabel3 ----
                jLabel3.setText("Am:");

                //---- jdcAB ----
                jdcAB.setEnabled(false);
                jdcAB.addPropertyChangeListener("date", new PropertyChangeListener() {
                    @Override
                    public void propertyChange(PropertyChangeEvent e) {
                        jdcABPropertyChange(e);
                    }
                });

                //---- jLabel4 ----
                jLabel4.setText("Durch:");

                //---- cmbAB ----
                cmbAB.setModel(new DefaultComboBoxModel(new String[] {
                    "Item 1",
                    "Item 2",
                    "Item 3",
                    "Item 4"
                }));
                cmbAB.setEnabled(false);
                cmbAB.addItemListener(new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        cmbABItemStateChanged(e);
                    }
                });

                //---- cbAB ----
                cbAB.setText("Abgesetzt");
                cbAB.setBorder(BorderFactory.createEmptyBorder());
                cbAB.setMargin(new Insets(0, 0, 0, 0));
                cbAB.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cbABActionPerformed(e);
                    }
                });

                //---- lblAB ----
                lblAB.setText("jLabel13");

                //---- cmbKHAb ----
                cmbKHAb.setModel(new DefaultComboBoxModel(new String[] {
                    "Item 1",
                    "Item 2",
                    "Item 3",
                    "Item 4"
                }));
                cmbKHAb.setEnabled(false);
                cmbKHAb.addItemListener(new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        cmbKHAbItemStateChanged(e);
                    }
                });

                GroupLayout jPanel4Layout = new GroupLayout(jPanel4);
                jPanel4.setLayout(jPanel4Layout);
                jPanel4Layout.setHorizontalGroup(
                    jPanel4Layout.createParallelGroup()
                        .addGroup(jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel4Layout.createParallelGroup()
                                        .addComponent(cbAB)
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addGroup(jPanel4Layout.createParallelGroup()
                                                        .addComponent(jLabel4)
                                                        .addComponent(jLabel3))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel4Layout.createParallelGroup()
                                                        .addComponent(cmbAB, 0, 189, Short.MAX_VALUE)
                                                        .addComponent(cmbKHAb, 0, 189, Short.MAX_VALUE)
                                                        .addComponent(jdcAB, GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                                                        .addComponent(lblAB, GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE))))
                                .addContainerGap())
                );
                jPanel4Layout.setVerticalGroup(
                    jPanel4Layout.createParallelGroup()
                        .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(cbAB)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel3)
                                        .addComponent(jdcAB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel4)
                                        .addComponent(cmbAB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbKHAb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblAB)
                                .addContainerGap())
                );
            }

            //======== jScrollPane3 ========
            {

                //---- txtBemerkung ----
                txtBemerkung.addCaretListener(new CaretListener() {
                    @Override
                    public void caretUpdate(CaretEvent e) {
                        txtBemerkungCaretUpdate(e);
                    }
                });
                jScrollPane3.setViewportView(txtBemerkung);
            }

            //---- jLabel5 ----
            jLabel5.setText("Bemerkung:");

            //======== jPanel2 ========
            {
                jPanel2.setBorder(new TitledBorder("Ansetzung"));

                //---- jLabel1 ----
                jLabel1.setText("Am:");

                //---- jdcAN ----
                jdcAN.addPropertyChangeListener("date", new PropertyChangeListener() {
                    @Override
                    public void propertyChange(PropertyChangeEvent e) {
                        jdcANPropertyChange(e);
                    }
                });

                //---- cmbAN ----
                cmbAN.setModel(new DefaultComboBoxModel(new String[] {
                    "Item 1",
                    "Item 2",
                    "Item 3",
                    "Item 4"
                }));
                cmbAN.addItemListener(new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        cmbANItemStateChanged(e);
                    }
                });

                //---- jLabel2 ----
                jLabel2.setText("Durch:");

                //---- lblAN ----
                lblAN.setText("jLabel11");

                //---- cmbKHAn ----
                cmbKHAn.setModel(new DefaultComboBoxModel(new String[] {
                    "Item 1",
                    "Item 2",
                    "Item 3",
                    "Item 4"
                }));
                cmbKHAn.addItemListener(new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        cmbKHAnItemStateChanged(e);
                    }
                });

                GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                    jPanel2Layout.createParallelGroup()
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup()
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel1))
                                .addGap(5, 5, 5)
                                .addGroup(jPanel2Layout.createParallelGroup()
                                        .addComponent(jdcAN, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                                        .addComponent(cmbKHAn, 0, 192, Short.MAX_VALUE)
                                        .addComponent(cmbAN, 0, 192, Short.MAX_VALUE)
                                        .addComponent(lblAN, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE))
                                .addContainerGap())
                );
                jPanel2Layout.setVerticalGroup(
                    jPanel2Layout.createParallelGroup()
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup()
                                        .addComponent(jLabel1)
                                        .addComponent(jdcAN, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
                                .addGap(9, 9, 9)
                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(cmbAN, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbKHAn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblAN)
                                .addContainerGap(66, Short.MAX_VALUE))
                );
            }

            GroupLayout jPanel3Layout = new GroupLayout(jPanel3);
            jPanel3.setLayout(jPanel3Layout);
            jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup()
                    .addGroup(jPanel3Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel3Layout.createParallelGroup()
                                    .addComponent(jLabel5, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                                    .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jPanel2, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jPanel4, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jScrollPane3, GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE))
                            .addContainerGap())
            );
            jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup()
                    .addGroup(jPanel3Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jPanel4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel5)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jScrollPane3, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                            .addContainerGap())
            );
        }

        //---- btnSave ----
        btnSave.setIcon(new ImageIcon(getClass().getResource("/artwork/22x22/apply.png")));
        btnSave.setText("Speichern");
        btnSave.setEnabled(false);
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnSaveActionPerformed(e);
            }
        });

        //---- btnClose ----
        btnClose.setIcon(new ImageIcon(getClass().getResource("/artwork/22x22/cancel.png")));
        btnClose.setText("Schlie\u00dfen");
        btnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnCloseActionPerformed(e);
            }
        });

        //======== jPanel5 ========
        {
            jPanel5.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));

            //---- lblVerordnung ----
            lblVerordnung.setFont(new Font("Dialog", Font.BOLD, 18));
            lblVerordnung.setForeground(new Color(0, 51, 255));
            lblVerordnung.setText("jLabel11");

            GroupLayout jPanel5Layout = new GroupLayout(jPanel5);
            jPanel5.setLayout(jPanel5Layout);
            jPanel5Layout.setHorizontalGroup(
                jPanel5Layout.createParallelGroup()
                    .addGroup(jPanel5Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(lblVerordnung, GroupLayout.DEFAULT_SIZE, 888, Short.MAX_VALUE)
                            .addContainerGap())
            );
            jPanel5Layout.setVerticalGroup(
                jPanel5Layout.createParallelGroup()
                    .addComponent(lblVerordnung, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(lblTitle, GroupLayout.DEFAULT_SIZE, 916, Short.MAX_VALUE)
                                .addComponent(lblBW, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 916, Short.MAX_VALUE)
                                .addComponent(jSeparator1, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 916, Short.MAX_VALUE)
                                .addComponent(jSeparator2, GroupLayout.DEFAULT_SIZE, 916, Short.MAX_VALUE)
                                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                                        .addComponent(btnSave)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnClose))
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                        .addComponent(jPanel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(jPanel5, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblTitle)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblBW)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel3, GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator2, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(btnClose)
                                .addComponent(btnSave))
                        .addContainerGap())
        );
        pack();
        setLocationRelativeTo(getOwner());
    }// </editor-fold>//GEN-END:initComponents

    private void cmbABItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbABItemStateChanged
        if (ignoreEvent) {
            return;
        }

        verordnung.setAbArzt((Arzt) cmbAB.getSelectedItem());

        saveOK();
    }//GEN-LAST:event_cmbABItemStateChanged

    private void cmbKHAbItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbKHAbItemStateChanged
        if (ignoreEvent) {
            return;
        }

        verordnung.setAbKH((Krankenhaus) cmbKHAb.getSelectedItem());

        saveOK();
    }//GEN-LAST:event_cmbKHAbItemStateChanged

    private void cmbKHAnItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbKHAnItemStateChanged
        if (ignoreEvent) {
            return;
        }
        verordnung.setAnKH((Krankenhaus) cmbKHAn.getSelectedItem());

        saveOK();
    }//GEN-LAST:event_cmbKHAnItemStateChanged

    public void initDialog() {
        initComponents();
        prepareTMPData();
        SYSTools.setBWLabel(lblBW, verordnung.getBewohner());
        setTitle(SYSTools.getWindowTitle("Ärztliche Verordnungen, Detailansicht"));
        fillAerzteUndKHs();

        ignoreSitCaret = true;
        ignoreEvent = true;
        txtSit.setText("");
        txtMed.setText("");
        cmbMass.setModel(new DefaultComboBoxModel(MassnahmenTools.findMassnahmenBy(MassnahmenTools.MODE_NUR_BHP).toArray()));
        cmbMass.setRenderer(MassnahmenTools.getMassnahmenRenderer());
        jdcAN.setMinSelectableDate(new Date());
        jdcAB.setMinSelectableDate(new Date());
        cmbMed.setRenderer(DarreichungTools.getDarreichungRenderer());
        cmbSit.setRenderer(SituationenTools.getSituationenRenderer());
        if (this.editMode == NEW_MODE) { // NewMode
            lblTitle.setText(lblTitle.getText() + " (Neuer Eintrag)");
            jdcAN.setDate(SYSCalendar.today_date());
            cbAB.setSelected(false);
            txtBemerkung.setText("");
            lblAN.setText(OPDE.getLogin().getUser().getUKennung());
            lblAB.setText("");
            cmbMed.setModel(new DefaultComboBoxModel());
            cmbSit.setModel(new DefaultComboBoxModel());
            cmbMass.setSelectedIndex(-1);
            cbStellplan.setEnabled(true);
            cbStellplan.setSelected(false);
            tblDosis.setModel(new DefaultTableModel());
            lblVerordnung.setText(" ");

            cbPackEnde.setEnabled(false);
        } else { // CHANGE oder EDIT
            lblTitle.setText(lblTitle.getText() + (editMode == EDIT_MODE ? " (Korrektur)" : " (Änderung der bestehenden Verordnung)"));
            // Bei einer Änderung muss sich das Fenster am Anfang in einem Zustand befinden,
            // der ein Save ermöglich
            btnSave.setEnabled(true);
            //HashMap verordnung = DBRetrieve.getSingleRecord("BHPVerordnung", new String[]{"AnDatum", "AbDatum", "AnArztID", "AbArztID", "AnKHID", "AbKHID", "AnUKennung", "AbUKennung", "VerKennung", "Bemerkung", "MassID", "DafID", "SitID", "BisPackEnde", "Stellplan"}, "VerID", verid);

            jdcAN.setDate(new Date());
            if (this.editMode == EDIT_MODE) {
                lblAN.setText(verordnung.getAngesetztDurch().getUKennung());
            } else {
                lblAN.setText(OPDE.getLogin().getUser().getUKennung());
            }
            cmbAN.setSelectedItem(verordnung.getAnArzt());
            cmbKHAn.setSelectedItem(verordnung.getAnKH());
            cmbAB.setToolTipText(cmbAB.getSelectedItem().toString());
            cbPackEnde.setSelected(verordnung.isBisPackEnde());
            cmbKHAn.setToolTipText(cmbKHAn.getSelectedItem().toString());
            jdcAN.setEnabled(editMode == EDIT_MODE);
            txtBemerkung.setText(SYSTools.catchNull(verordnung.getBemerkung()));

            cmbMed.setModel(new DefaultComboBoxModel(new MedProdukte[]{verordnung.getDarreichung().getMedProdukt()}));

            cmbMass.setEnabled(cmbMed.getModel().getSize() == 0);
            cbStellplan.setEnabled(cmbMed.getModel().getSize() == 0);
            cbStellplan.setSelected(verordnung.isStellplan());

            cmbSit.setModel(new DefaultComboBoxModel(new Situationen[]{verordnung.getSituation()}));

            cmbMass.setSelectedItem(verordnung.getMassnahme());

            cmbMed.setEnabled(this.editMode != CHANGE_MODE);
            txtMed.setEnabled(this.editMode != CHANGE_MODE);
            txtSit.setEnabled(this.editMode != CHANGE_MODE);
            cmbSit.setEnabled(this.editMode != CHANGE_MODE);

            if (cmbMed.getSelectedItem() != null) {
                lblVerordnung.setText(cmbMed.getSelectedItem().toString());
                cbPackEnde.setEnabled(true);
            } else {
                lblVerordnung.setText(cmbMass.getSelectedItem().toString());
                cbPackEnde.setEnabled(false);
            }
            if (!verordnung.isAbgesetzt()) {
                cbAB.setSelected(false);
                lblAB.setText("");
                cmbAB.setSelectedIndex(-1);
            } else {
                cbAB.setSelected(true);
                jdcAB.setDate(verordnung.getAbDatum());
                lblAB.setText(verordnung.getAbgesetztDurch().getUKennung());
                cmbAB.setSelectedItem(verordnung.getAbArzt());
                cmbKHAb.setSelectedItem(verordnung.getAbKH());
                cmbAB.setToolTipText(cmbAB.getSelectedItem().toString());
                cmbKHAb.setToolTipText(cmbKHAb.getSelectedItem().toString());
            }
        }

        reloadTable();
        ignoreSitCaret = false;
        ignoreEvent = false;
        pack();
        SYSTools.centerOnParent(parent, this);
        txtMed.requestFocus();

        myPropertyChangeListener = new java.beans.PropertyChangeListener() {

            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("value")) {
                    JDateChooser jdcDatum = (JDateChooser) ((JComponent) evt.getSource()).getParent();
                    SYSCalendar.checkJDC(jdcDatum);
                }
            }
        };

        jdcAN.getDateEditor().addPropertyChangeListener(myPropertyChangeListener);
        jdcAB.getDateEditor().addPropertyChangeListener(myPropertyChangeListener);
        setVisible(true);
    }

    private void txtMedFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMedFocusGained
        SYSTools.markAllTxt(txtMed);
    }//GEN-LAST:event_txtMedFocusGained

    private void cmbMassItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbMassItemStateChanged
        if (ignoreEvent) {
            return;
        }
        verordnung.setMassnahme((Massnahmen) cmbMass.getSelectedItem());
        saveOK();
    }//GEN-LAST:event_cmbMassItemStateChanged

    private void btnMedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMedActionPerformed
        //String template = ( txtMed.getText().matches("^ß?\\d{7}") ? "" : txtMed.getText());
        //new DlgMed(this, template);
        ArrayList result = new ArrayList();
        result.add(txtMed.getText());
        new DlgMediAssistent(this, result);
        if (result.size() > 0) {
            ignoreEvent = true;
            txtMed.setText(result.get(0).toString());
            ignoreEvent = false;
            txtMedCaretUpdate(null);
        }
    }//GEN-LAST:event_btnMedActionPerformed

    private void btnBedarfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBedarfActionPerformed
        if (JOptionPane.showConfirmDialog(this, "\"" + txtSit.getText() + "\"", "Situation hinzufügen",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
            HashMap hm = new HashMap();
            hm.put("Text", txtSit.getText());
            long sitid = DBHandling.insertRecord("Situationen", hm);
            hm.clear();
            cmbSit.setModel(op.care.med.DBHandling.getSit(sitid));
            cbPackEnde.setEnabled(false);
            ignoreEvent = true;
            cbPackEnde.setSelected(false);
            ignoreEvent = false;
        }
    }//GEN-LAST:event_btnBedarfActionPerformed

    private void jspDosisMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jspDosisMousePressed
        if (!evt.isPopupTrigger()) {
            return;
        }

        // Wenn die Dosis Tabelle leer ist, dann funktioniert da auch kein MousePressed Event
        // In diesem Fall muss die ScrollPane einspringen.
        TableModel tm = tblDosis.getModel();
        if (tm.getRowCount() > 0) {
            return;
        }

        SYSTools.unregisterListeners(menu);
        menu = new JPopupMenu();

        JMenuItem itemPopupNew = new JMenuItem("Neue Dosis eingeben");
        itemPopupNew.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Date ldatum = null;
                DlgVerabreichung dlg = new DlgVerabreichung(parent, 0, verordnung, cmbSit.getSelectedIndex() >= 0);
                dlg = null;
                reloadTable();
            }
        });
        menu.add(itemPopupNew);
        Point p = evt.getPoint();
        menu.show(evt.getComponent(), (int) p.getX(), (int) p.getY());
    }//GEN-LAST:event_jspDosisMousePressed

    private void cmbMedItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbMedItemStateChanged
        if (ignoreEvent) {
            return;
        }
        verordnung.setDarreichung((Darreichung) cmbMed.getSelectedItem());
        cmbMass.setSelectedItem(verordnung.getDarreichung().getMedForm().getMassnahme());
        cmbMass.setEnabled(false);
        cbStellplan.setEnabled(false);
        cbStellplan.setSelected(false);
        lblVerordnung.setText(cmbMed.getSelectedItem().toString());
        // Bestand prüfen
        saveOK();
    }//GEN-LAST:event_cmbMedItemStateChanged

    private void cbPackEndeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPackEndeActionPerformed
        if (ignoreEvent) {
            return;
        }
        verordnung.setBisPackEnde(cbPackEnde.isSelected());
        saveOK();
    }//GEN-LAST:event_cbPackEndeActionPerformed

    private void saveOK() {
        boolean ansetzungOK = jdcAN.getDate() != null && (cmbAN.getSelectedIndex() > 0 || cmbKHAn.getSelectedIndex() > 0);
        boolean absetzungOK = !cbAB.isSelected() || (jdcAB.getDate() != null && (cmbAB.getSelectedIndex() > 0 || cmbKHAb.getSelectedIndex() > 0));
        boolean medOK = cmbMed.getModel().getSize() == 0 || cmbMed.getSelectedItem() != null;
        boolean massOK = cmbMass.getSelectedItem() != null;
        boolean dosisVorhanden = tblDosis.getModel().getRowCount() > 0;
        btnSave.setEnabled(ansetzungOK && absetzungOK && medOK && massOK && dosisVorhanden);
        cbPackEnde.setEnabled(!verordnung.isBedarf() && cmbMed.getModel().getSize() > 0);

        if (!btnSave.isEnabled()) {
            String ursache = "<html><body>Es fehlen noch Angaben, bevor Sie speichern können.<ul>";
            ursache += (ansetzungOK ? "" : "<li>Die Informationen zum <b>an</b>setzenden Arzt oder KH sind unvollständig.</li>");
            ursache += (absetzungOK ? "" : "<li>Die Informationen zum <b>ab</b>setzenden Arzt oder KH sind unvollständig.</li>");
            ursache += (medOK ? "" : "<li>Die Medikamentenangabe ist falsch.</li>");
            ursache += (massOK ? "" : "<li>Die Angaben über die Massnahmen sind falsch.</li>");
            ursache += (dosisVorhanden ? "" : "<li>Sie müssen mindestens eine Dosierung angegeben.</li>");
            ursache += "</ul></body></html>";
            btnSave.setToolTipText(ursache);
        } else {
            btnSave.setToolTipText(null);
        }

    }

    private void prepareTMPData() {
    }

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        save();
        syncBHP();
        dispose();
    }//GEN-LAST:event_btnSaveActionPerformed

    public void dispose() {
        jdcAB.removePropertyChangeListener(myPropertyChangeListener);
        jdcAN.removePropertyChangeListener(myPropertyChangeListener);
        jdcAB.cleanup();
        jdcAN.cleanup();
        SYSTools.unregisterListeners(this);
        super.dispose();
    }

    private void cmbSitItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbSitItemStateChanged
        if (ignoreEvent) {
            return;
        }
        verordnung.setSituation((Situationen) cmbSit.getSelectedItem());
        saveOK();
    }//GEN-LAST:event_cmbSitItemStateChanged

    private void txtBemerkungCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBemerkungCaretUpdate
        if (ignoreEvent) {
            return;
        }
        verordnung.setBemerkung(txtBemerkung.getText());
        saveOK();
    }//GEN-LAST:event_txtBemerkungCaretUpdate

    private void txtSitCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtSitCaretUpdate
        if (!txtSit.isEnabled() || ignoreEvent || ignoreSitCaret) {
            return;
        }
        if (txtSit.getText().equals("")) {
            cmbSit.setModel(new DefaultComboBoxModel());
            btnBedarf.setEnabled(false);
        } else {
            cmbSit.setModel(new DefaultComboBoxModel(SituationenTools.findSituationByText(txtSit.getText()).toArray()));
            btnBedarf.setEnabled(cmbSit.getModel().getSize() == 0);
        }
        saveOK();
    }//GEN-LAST:event_txtSitCaretUpdate

    private void save() {
        try {

        } catch (Exception e){

        } finally {

        }

//            if (cbAB.isSelected()) {
//                abarztid = ((ListElement) cmbAB.getSelectedItem()).getPk();
//                abkhid = ((ListElement) cmbKHAb.getSelectedItem()).getPk();
//                if (SYSCalendar.sameDay(jdcAB.getDate(), new Date()) == 0) {
//                    OPDE.debug("jdcAB steht auf HEUTE");
//                    if (SYSCalendar.sameDay(jdcAB.getDate(), jdcAN.getDate()) == 0) {
//                        OPDE.debug("jdcAB und jdcAN sind gleich");
//                        hm.put("AnDatum", new Timestamp(SYSCalendar.startOfDay()));
//                        hm.put("AbDatum", new Timestamp(SYSCalendar.endOfDay()));
//                    } else {
//                        hm.put("AbDatum", "!NOW!");
//                    }
//                } else {
//                    OPDE.debug("jdcAB steht nicht auf HEUTE");
//                    hm.put("AbDatum", new Timestamp(SYSCalendar.endOfDay(jdcAB.getDate())));
//                }
//                hm.put("AbUKennung", lblAB.getText());
//            } else {
//                //abdatum = new Date(SYSConst.BIS_AUF_WEITERES.getTimeInMillis());
//                abarztid = 0l;
//                abkhid = 0l;
//                hm.put("AbDatum", "!BAW!");
//            }
//
//            hm.put("AnArztID", ((ListElement) cmbAN.getSelectedItem()).getPk());
//            hm.put("AnKHID", ((ListElement) cmbKHAn.getSelectedItem()).getPk());
//            hm.put("AbArztID", abarztid);
//            hm.put("AbKHID", abkhid);
//            hm.put("AnUKennung", lblAN.getText());
//            hm.put("Stellplan", cbStellplan.isSelected());
//            hm.put("BisPackEnde", cbPackEnde.isSelected());
//            hm.put("Bemerkung", txtBemerkung.getText());
//            hm.put("MassID", ((ListElement) cmbMass.getSelectedItem()).getPk());
//            hm.put("DafID", dafid);
//            long sitid = 0;
//            if (cmbSit.getSelectedIndex() > -1) {
//                sitid = ((ListElement) cmbSit.getSelectedItem()).getPk();
//            }
//            hm.put("SitID", sitid);
//
//            // Sicherung
//            if (editMode == NEW_MODE) { // =================== NEU ====================
//                // Bei einer neuen Verordnung kann einfach eingetragen werden. Die BHP spielt hier keine Rolle.
//                hm.put("VerKennung", OPDE.getDb().getUID("__verkenn"));
//                verid = DBHandling.insertRecord("BHPVerordnung", hm);
//                if (verid < 0) {
//                    throw new SQLException();
//                }
//            } else if (editMode == EDIT_MODE) { // =================== KORREKTUR ====================
//                // Bei einer Korrektur werden alle bisherigen Einträge aus der BHP zuerst wieder entfernt.
//                if (!DBHandling.updateRecord("BHPVerordnung", hm, "VerID", verid)) {
//                    throw new SQLException();
//                }
//                op.care.verordnung.DBHandling.deleteBHP(verid);
//            } else if (editMode == CHANGE_MODE) { // =================== VERÄNDERUNG ====================
//                // Bei einer Veränderung, wird erst die alte Verordnung durch den ANsetzenden Arzt ABgesetzt.
//                // Dann werden die nicht mehr benötigten BHPs entfernt.
//                // Dann wird die neue Verordnung angesetzt.
//                hm.put("VerKennung", verkennung);
//                if (!op.care.verordnung.DBHandling.absetzen(verid, ((ListElement) cmbAN.getSelectedItem()).getPk(), ((ListElement) cmbKHAn.getSelectedItem()).getPk())) {
//                    throw new SQLException("Fehler beim Absetzen");
//                }
//                //op.care.verordnung.DBHandling.cleanBHP(verid, SYSCalendar.nowDB());
//                hm.put("AnDatum", "!NOW+1!");
//                verid = DBHandling.insertRecord("BHPVerordnung", hm);
//            } else {
//                throw new SQLException();
//            }
//            // Die bisher nur als TMP markierten Einträge werden in GÜLTIG geändert.
//            op.care.verordnung.DBHandling.tmp2real(verid);
//
//            OPDE.getDb().db.commit();
//            OPDE.getDb().db.setAutoCommit(true);
//
//        } catch (SQLException ex) {
//            try {
//                OPDE.debug(ex.getMessage());
//                OPDE.getDb().db.rollback();
//            } catch (SQLException ex1) {
//                new DlgException(ex1);
//                ex1.printStackTrace();
//                System.exit(1);
//            }
//            new DlgException(ex);
//        }

        // Wenn bisher noch keine Dosierungen eingetragen wurden, dann fragt das System jetzt nach.
//        if (tblDosis.getModel().getRowCount() == 0){
//            DlgVerabreichung dlg = new DlgVerabreichung(parent, 0, verid, dafid, cmbSit.getSelectedIndex() >= 0);
//            dlg = null;
//            //reloadTable();
//        }
    }

    private void syncBHP() {
        // Wenn jetzt Dosierungen da sind, ein Medikament vorhanden ist und keine Bedarfsmedikation,
        // dann können wir auch direkt die BHP anpassen.
        if (tblDosis.getModel().getRowCount() > 0 && cmbSit.getSelectedIndex() < 0) {
            try {
                if (editMode == CHANGE_MODE) {
                    BHPTools.importBHP(verordnung, new Date());
                } else {
                    BHPTools.importBHP(verordnung);
                }
            } catch (Exception e) {
                new DlgException(e);
            }
        }
    }

    private void tblDosisMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDosisMousePressed
        if (!evt.isPopupTrigger()) {
            return;
        }
//        if (formDirty){
//            JOptionPane.showMessageDialog(tblDosis, "Verordnung wurde geändert. Bitte zuerst speichern.");
//            return;
//        }
        if (verordnung.isAbgesetzt() && !SYSCalendar.isInFuture(jdcAB.getDate().getTime())) {
            JOptionPane.showMessageDialog(tblDosis, "Verordnung wurde bereits abgesetzt. Sie können diese nicht mehr ändern.");
            return;
        }
//        if (bhpVorhanden){
//            JOptionPane.showMessageDialog(tblDosis, "Verordnung ist schon in Betrieb. Sie können diese nicht mehr ändern.");
//            return;
//        }
        final TMDosis tm = (TMDosis) tblDosis.getModel();
        if (tm.getRowCount() == 0) {
            return;
        }
        Point p = evt.getPoint();
        //final int col = tblDosis.columnAtPoint(p);
        final int row = tblDosis.rowAtPoint(p);
        ListSelectionModel lsm = tblDosis.getSelectionModel();
        lsm.setSelectionInterval(row, row);
        final long bhppid = ((Long) tm.getValueAt(row, TMDosis.COL_BHPPID)).longValue();

        // Menüeinträge
        SYSTools.unregisterListeners(menu);
        menu = new JPopupMenu();

        JMenuItem itemPopupNew = new JMenuItem("Neue Dosis eingeben");
        itemPopupNew.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //Date ldatum = null;
                DlgVerabreichung dlg = new DlgVerabreichung(parent, 0, verid, dafid, cmbSit.getSelectedIndex() >= 0);
                dlg = null;
                reloadTable();
            }
        });
        menu.add(itemPopupNew);
        // Bei Bedarfsmedikation kann immer nur eine Dosis eingegeben werden.
        itemPopupNew.setEnabled(!verordnung.isBedarf() || tm.getRowCount() == 0);

        JMenuItem itemPopupEditText = new JMenuItem("Bearbeiten");
        itemPopupEditText.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DlgVerabreichung dlg = new DlgVerabreichung(parent, bhppid, verid, dafid, cmbSit.getSelectedIndex() >= 0);
                dlg = null;
                reloadTable();
            }
        });
        menu.add(itemPopupEditText);
        //ocs.setEnabled(classname, "itemPopupEditText", itemPopupEditText, status > 0 && changeable);

        //-----------------------------------------
        JMenuItem itemPopupDelete = new JMenuItem("löschen");
        itemPopupDelete.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DBHandling.deleteRecords("BHPPlanung", "BHPPID", bhppid);
                reloadTable();
            }
        });
        menu.add(itemPopupDelete);
        //ocs.setEnabled(classname, "itemPopupEditVer", itemPopupEditVer, true);
        menu.show(evt.getComponent(), (int) p.getX(), (int) p.getY());

    }//GEN-LAST:event_tblDosisMousePressed

    private void jspDosisComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jspDosisComponentResized
    }//GEN-LAST:event_jspDosisComponentResized

    private void txtMedCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtMedCaretUpdate
        if (ignoreEvent) {
            return;
        }
        if (!txtMed.isEnabled() || ignoreEvent) {
            return;
        }
        if (txtMed.getText().equals("")) {
            cmbMed.setModel(new DefaultComboBoxModel());

            cmbMass.setEnabled(true);
            cbStellplan.setEnabled(true);
            cbStellplan.setSelected(false);
            lblVerordnung.setText(" ");
            cmbMed.setToolTipText("");
            ignoreEvent = true;
            cbPackEnde.setSelected(false);
            ignoreEvent = false;
            cbPackEnde.setEnabled(false);
        } else {
            if (txtMed.getText().matches("^ß?\\d{7}")) { // Hier sucht man nach einer PZN. Im Barcode ist das führende 'ß' enthalten.
                String pzn = txtMed.getText();

                EntityManager em = OPDE.createEM();
                pzn = (pzn.startsWith("ß") ? pzn.substring(1) : pzn);
                Query pznQuery = em.createNamedQuery("MedPackung.findByPzn");
                pznQuery.setParameter("pzn", pzn);

                try {
                    MedPackung medPackung = (MedPackung) pznQuery.getSingleResult();
                    cmbMed.setModel(new DefaultComboBoxModel(new Darreichung[]{medPackung.getDarreichung()}));
                } catch (NoResultException nre) {
                    OPDE.debug("Nichts passendes zu dieser PZN gefunden");
                } catch (Exception e) {
                    OPDE.fatal(e);
                } finally {
                    em.close();
                }

            } else { // Falls die Suche NICHT nur aus Zahlen besteht, dann nach Namen suchen.
                cmbMed.setModel(new DefaultComboBoxModel(DarreichungTools.findDarreichungByMedProduktText(txtMed.getText()).toArray()));
            }

            if (cmbMed.getModel().getSize() > 0) {
                cmbMedItemStateChanged(null);
            } else {
                lblVerordnung.setText(" ");
                cmbMed.setToolTipText("");
                cmbMass.setSelectedIndex(-1);
                cmbMass.setEnabled(true);
                cbStellplan.setEnabled(true);
                cbStellplan.setSelected(false);
                ignoreEvent = true;
                cbPackEnde.setSelected(false);
                ignoreEvent = false;
            }
            cbPackEnde.setEnabled(!verordnung.isBedarf() && cmbMed.getModel().getSize() > 0);
        }
    }//GEN-LAST:event_txtMedCaretUpdate

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed

        dispose();
    }//GEN-LAST:event_btnCloseActionPerformed

    private void cmbANItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbANItemStateChanged
        if (ignoreEvent) {
            return;
        }
        verordnung.setAnArzt((Arzt) cmbAN.getSelectedItem());
        saveOK();
    }//GEN-LAST:event_cmbANItemStateChanged

    private void cbABActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbABActionPerformed
        if (ignoreEvent) {
            return;
        }
        jdcAB.setEnabled(cbAB.isSelected());
        cmbAB.setEnabled(cbAB.isSelected());
        cmbKHAb.setEnabled(cbAB.isSelected());
        cmbAB.setSelectedIndex(0);
        cmbKHAb.setSelectedIndex(0);
        jdcAB.setDate(new Date());
        jdcAB.setMinSelectableDate(jdcAN.getDate());
        lblAB.setText(cbAB.isSelected() ? OPDE.getLogin().getUser().getUKennung() : "");
        saveOK();
    }//GEN-LAST:event_cbABActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        dispose();
    }//GEN-LAST:event_formWindowClosing

    private void fillAerzteUndKHs() {
        EntityManager em = OPDE.createEM();
        Query queryArzt = em.createNamedQuery("Arzt.findAll");
        List<Arzt> listAerzte = queryArzt.getResultList();
        listAerzte.add(0, null);

        Query queryKH = em.createNamedQuery("Krankenhaus.findAll");
        List<Krankenhaus> listKH = queryKH.getResultList();
        listKH.add(0, null);

        cmbAN.setModel(new DefaultComboBoxModel(listAerzte.toArray()));
        cmbAB.setModel(new DefaultComboBoxModel(listAerzte.toArray()));
        cmbAN.setRenderer(ArztTools.getArztRenderer());
        cmbAB.setRenderer(ArztTools.getArztRenderer());
        cmbAN.setSelectedIndex(0);
        cmbAB.setSelectedIndex(0);

        cmbKHAn.setModel(new DefaultComboBoxModel(listKH.toArray()));
        cmbKHAb.setModel(new DefaultComboBoxModel(listKH.toArray()));
        cmbKHAn.setRenderer(KrankenhausTools.getKHRenderer());
        cmbKHAb.setRenderer(KrankenhausTools.getKHRenderer());
        cmbKHAn.setSelectedIndex(0);
        cmbKHAb.setSelectedIndex(0);

        em.close();
    }

    private void reloadTable() {
        String zubereitung = "x";
        if (verordnung.getDarreichung() != null) {
            zubereitung = verordnung.getDarreichung().getMedForm().getZubereitung();
        }

        tblDosis.setModel(new TMDosis(zubereitung));
        tblDosis.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jspDosis.dispatchEvent(new ComponentEvent(jspDosis, ComponentEvent.COMPONENT_RESIZED));
        tblDosis.getColumnModel().getColumn(TMDosis.COL_Dosis).setCellRenderer(new RNDHTML());
        tblDosis.getColumnModel().getColumn(TMDosis.COL_Dosis).setHeaderValue("Anwendung");
        //tblDosis.getColumnModel().getColumn(1).setCellRenderer(new RNDStandard());

        if (tblDosis.getModel().getRowCount() > 0) { // Sobald etwas in der Tabelle steht, darf man die Situation nicht mehr verändern.
            txtSit.setEnabled(false);
            txtSit.setText("");
        } else {
            txtSit.setEnabled(true);
        }
        saveOK();

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JLabel lblBW;
    private JLabel lblTitle;
    private JSeparator jSeparator1;
    private JPanel jPanel1;
    private JComboBox cmbSit;
    private JTextField txtMed;
    private JComboBox cmbMed;
    private JComboBox cmbMass;
    private JTextField txtSit;
    private JButton btnBedarf;
    private JButton btnMed;
    private JPanel jPanel8;
    private JScrollPane jspDosis;
    private JTable tblDosis;
    private JCheckBox cbPackEnde;
    private JCheckBox cbStellplan;
    private JLabel jLabel6;
    private JPanel jPanel3;
    private JPanel jPanel4;
    private JLabel jLabel3;
    private JDateChooser jdcAB;
    private JLabel jLabel4;
    private JComboBox cmbAB;
    private JCheckBox cbAB;
    private JLabel lblAB;
    private JComboBox cmbKHAb;
    private JScrollPane jScrollPane3;
    private JTextPane txtBemerkung;
    private JLabel jLabel5;
    private JPanel jPanel2;
    private JLabel jLabel1;
    private JDateChooser jdcAN;
    private JComboBox cmbAN;
    private JLabel jLabel2;
    private JLabel lblAN;
    private JComboBox cmbKHAn;
    private JSeparator jSeparator2;
    private JButton btnSave;
    private JButton btnClose;
    private JPanel jPanel5;
    private JLabel lblVerordnung;
    // End of variables declaration//GEN-END:variables
//    class HandleSelections implements ListSelectionListener {
//        public void valueChanged(ListSelectionEvent lse) {
//            // Erst reagieren wenn der Auswahl-Vorgang abgeschlossen ist.
//            TableModel tm = tblDosis.getModel();
//            if (tm.getRowCount() <= 0) {
//                return;
//            }
//
//            if (!lse.getValueIsAdjusting()){
//                DefaultListSelectionModel lsm = (DefaultListSelectionModel) lse.getSource();
//
//                if (lsm.isSelectionEmpty()) {
//                    currentVerID = 0;
//                } else {
//                    currentVerID = ((Long) tm.getValueAt(lsm.getLeadSelectionIndex(), TMVerordnung.COL_VERID)).longValue();
//                }
//            }
//        }
//    }
}
