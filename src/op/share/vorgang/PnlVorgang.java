/*
 * OffenePflege
 * Copyright (C) 2006 - 2009 Torsten Löhr
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

import entity.Bewohner;
import op.OCSec;
import op.OPDE;
import op.care.CleanablePanel;
import op.care.FrmPflege;
import op.share.tools.DlgText;
import op.tools.*;
import tablerenderer.RNDHTML;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;

/**
 * @author tloehr
 */
public class PnlVorgang extends CleanablePanel {

    public static final int MODE_CALLED_FROM_PFLEGE = 0;
    public static final int MODE_CALLED_FROM_VERWALTUNG = 1;
    private String currentBW;
    private Frame parent;
    private long vorgangid = 0;
    private HandleVSelections lslV;
    private int mode;
    private JPopupMenu menu;
    private OCSec ocs;
    private boolean archiv;
    private String besitzer;
    private int wv;
    public JLabel lblbw;

    /**
     * Creates new form PnlVorgang
     */
    public PnlVorgang(Frame parent, int mode, String bwkennung, boolean archiv, String besitzer, int wv, FrmPflege pflege) {
        this.mode = mode;
        this.currentBW = bwkennung;
        this.parent = parent;
        this.archiv = archiv;
        this.besitzer = besitzer;
        this.wv = wv;
        ocs = OPDE.getOCSec();
        initComponents();
        if (pflege != null) {
            if (pflege.bwlabel == null) {
                SYSTools.setBWLabel(lblBW, currentBW);
                pflege.bwlabel = lblBW;
            } else {
                lblBW.setText(pflege.bwlabel.getText());
                lblBW.setToolTipText(pflege.bwlabel.getToolTipText());
            }
        } else {
            lblBW.setText(null);
            lblBW.setToolTipText(null);
        }

        reloadVTable();
        tblElement.setModel(new DefaultTableModel());
        cbSystem.setEnabled(false);
    }

    public void cleanup() {
        SYSTools.unregisterListeners(this);
    }

    @Override
    public void change2Bewohner(Bewohner bewohner) {
        //To change body of implemented methods use File | Settings | File Templates.
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

        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        lblBW = new javax.swing.JLabel();
        jspVorgang = new javax.swing.JScrollPane();
        tblVorgang = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jspElement = new javax.swing.JScrollPane();
        tblElement = new javax.swing.JTable();
        cbSystem = new javax.swing.JCheckBox();

        jSplitPane1.setDividerLocation(300);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        lblBW.setFont(new java.awt.Font("Dialog", 1, 18));
        lblBW.setForeground(new java.awt.Color(255, 51, 0));
        lblBW.setText(" ");

        tblVorgang.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null}
                },
                new String[]{
                        "Title 1", "Title 2", "Title 3", "Title 4"
                }
        ));
        tblVorgang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblVorgangMousePressed(evt);
            }
        });
        jspVorgang.setViewportView(tblVorgang);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jspVorgang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 738, Short.MAX_VALUE)
                                        .addComponent(lblBW, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 738, Short.MAX_VALUE))
                                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblBW)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jspVorgang, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                                .addContainerGap())
        );

        jSplitPane1.setLeftComponent(jPanel1);

        jspElement.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jspElementMousePressed(evt);
            }
        });
        jspElement.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                jspElementComponentResized(evt);
            }
        });

        tblElement.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null}
                },
                new String[]{
                        "Title 1", "Title 2", "Title 3", "Title 4"
                }
        ));
        tblElement.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblElementMousePressed(evt);
            }
        });
        jspElement.setViewportView(tblElement);

        cbSystem.setText("System-Berichte anzeigen");
        cbSystem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSystemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jspElement, javax.swing.GroupLayout.DEFAULT_SIZE, 738, Short.MAX_VALUE)
                                        .addComponent(cbSystem))
                                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jspElement, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbSystem))
        );

        jSplitPane1.setRightComponent(jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 764, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jSplitPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    public void neu() {
        Object[] template = new Object[]{currentBW};
        new DlgVorgang(parent, template);
        this.vorgangid = 0; // Nach einer Neueingabe brauchen wir das nicht mehr. Jetzt gibts ja mehr, die er sehen will.
        reloadVTable();
    }

    public void setVorgangID(long vor) {
        this.vorgangid = vor;
    }

    public void print() {
        if (vorgangid > 0) {
            String content = "<h1>Vorgang</h1>" + tblVorgang.getModel().getValueAt(tblVorgang.getSelectedRow(), TMVorgang.COL_TITEL);
            content += ((TMElement) tblElement.getModel()).getTableAsHTML();
            SYSPrint.print(parent, content, true);
        }
    }

    public void reload(boolean archiv) {
        this.archiv = archiv;
        reloadVTable();
    }

    public void reload(boolean archiv, String bwkennung, String besitzer, int wv) {
        this.currentBW = bwkennung;
        this.archiv = archiv;
        this.besitzer = besitzer;
        this.wv = wv;
        reloadVTable();
    }

    private void jspElementComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jspElementComponentResized
        JScrollPane jsp = (JScrollPane) evt.getComponent();
        Dimension dim = jsp.getSize();
        // Größe der Text Spalten im DFN ändern.
        // Summe der fixen Spalten + ein bisschen)
        int textWidth = dim.width - 200;
        TableColumnModel tcm = tblElement.getColumnModel();
        if (tcm.getColumnCount() == 2) {
            tcm.getColumn(TMElement.COL_PIT).setPreferredWidth(180);
            tcm.getColumn(TMElement.COL_CONTENT).setPreferredWidth(textWidth);
        }
    }//GEN-LAST:event_jspElementComponentResized

    private void cbSystemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSystemActionPerformed
        reloadETable();
    }//GEN-LAST:event_cbSystemActionPerformed

    private JMenu getUserAssignMenu() {
        JMenu result = new JMenu("Vorgang zuweisen");

        HashMap where = new HashMap();
        where.put("Status", new Object[]{1, "="});
        ResultSet rs = op.tools.DBHandling.getResultSet("OCUsers", new String[]{"UKennung", "Nachname", "Vorname"}, where, new String[]{"Nachname", "Vorname"});

        try {
            rs.beforeFirst();
            while (rs.next()) {
                final String s = rs.getString("Nachname") + ", " + rs.getString("Vorname") + " (" + rs.getString("UKennung") + ")";

                JMenuItem mi = new JMenuItem(s);
                final String ukennung = rs.getString("UKennung");
                mi.addActionListener(new java.awt.event.ActionListener() {

                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        HashMap hm = new HashMap();
                        hm.put("Besitzer", ukennung);
                        if (op.tools.DBHandling.updateRecord("Vorgaenge", hm, "VorgangID", vorgangid)) {
                            DBHandling.newVBericht(vorgangid, "Vorgang wurde zugewiesen an: " + s, DBHandling.VBERICHT_ART_SET_OWNERSHIP);
                        }
                        // Jetzt ist nämlich nichts mehr ausgewählt.
                        vorgangid = 0;
                        reloadVTable();
                    }
                });
                OPDE.debug(mi.getText());
                result.add(mi);
            }

        } catch (SQLException se) {
            new DlgException(se);
        }
        return result;
    }

    private void tblVorgangMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVorgangMousePressed
        TMVorgang tm = (TMVorgang) tblVorgang.getModel();
        if (tm.getRowCount() == 0) {
            return;
        }
        Point p = evt.getPoint();
        //int col = tblVorgang.columnAtPoint(p);
        int row = tblVorgang.rowAtPoint(p);
        ListSelectionModel lsm = tblVorgang.getSelectionModel();
        lsm.setSelectionInterval(row, row);

        boolean beendet = (Boolean) tm.getValueAt(row, TMVorgang.COL_BEENDET);
        boolean isBesitzer = tm.getValueAt(row, TMVorgang.COL_BESITZER).toString().equalsIgnoreCase(OPDE.getLogin().getUser().getUKennung());

        /*
         * Vorgänge können übernommen werden
         * * von jedem aktiven Benutzer.
         * * wenn sie nicht abgeschlossen sind
         * * wenn sie mir nicht schon gehören
         *
         * Vorgänge können zugewiesen werden (jedem aktiven Benutzer)
         * * von den Gruppen die auf itemPopupAssign zugelassen sind
         * * admin sowieso
         * * vom Besitzer immer
         * * wenn sie nicht abgeschlossen sind
         *
         * Vorgänge können gelöscht werden
         * * vom admin
         *
         * Vorgänge können beendet werden
         * * von den Gruppen die auf itemPopupEnd zugelassen sind
         * * admin sowieso
         * * vom Besitzer immer
         * * wenn sie nicht abgeschlossen sind
         *
         * Vorgänge können wieder eröffnet werden von
         * * von den Gruppen die auf itemPopupReopen zugelassen sind
         * * admin sowieso
         * * vom Besitzer immer
         * * nur wenn sie abgeschlossen sind
         *
         * Vorgänge können erstellt werden
         * * von allen aktiven Benutzern
         *
         * Elemente können zu Vorgängen hinzugefügt werden
         * * von allen aktiven Benutzern
         *
         * Das Wiedervorlagedatum kann verändert werden
         * * von den Gruppen die auf itemPopupWV zugelassen sind
         * * admin sowieso
         * * vom Besitzer immer
         * * nur wenn sie nicht abgeschlossen sind
         *
         */

        if (evt.isPopupTrigger()) {

            boolean takeAllowed = !beendet && !isBesitzer;
            boolean assignAllowed = !beendet && (ocs.isAccessible(this, "menuAssign") || OPDE.isAdmin() || isBesitzer);
            boolean endAllowed = !beendet && (ocs.isAccessible(this, "itemPopupEnd") || OPDE.isAdmin() || isBesitzer);
            boolean reopenAllowed = beendet && (ocs.isAccessible(this, "itemPopupReopen") || OPDE.isAdmin() || isBesitzer);
            boolean wvAllowed = !beendet && (ocs.isAccessible(this, "itemPopupWV") || OPDE.isAdmin() || isBesitzer);
            boolean delAllowed = OPDE.isAdmin();

            SYSTools.unregisterListeners(menu);
            menu = new JPopupMenu();


            JMenuItem itemPopupEnd = new JMenuItem("Vorgang abschließen");
            itemPopupEnd.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    DBHandling.endVorgang(vorgangid);
                    reloadVTable();
                }
            });
            menu.add(itemPopupEnd);
            itemPopupEnd.setEnabled(endAllowed);

            JMenuItem itemPopupReopen = new JMenuItem("Vorgang wieder aktivieren");
            itemPopupReopen.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    DBHandling.reopenVorgang(vorgangid);
                    reloadVTable();
                }
            });
            menu.add(itemPopupReopen);
            itemPopupReopen.setEnabled(reopenAllowed);

            JMenuItem itemPopupWV = new JMenuItem("Wiedervorlage setzen");
            itemPopupWV.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    DlgZeitpunkt dlg = new DlgZeitpunkt(parent, "Wiedervorlage setzen");
                    Date wv = dlg.showDialog(SYSCalendar.today_date(), SYSCalendar.today_date(), SYSConst.DATE_BIS_AUF_WEITERES, false);
                    if (wv != null) {
                        DBHandling.setWVVorgang(vorgangid, wv);
                        reloadVTable();
                    }
                }
            });
            menu.add(itemPopupWV);
            itemPopupWV.setEnabled(wvAllowed);

            JMenuItem itemPopupTake = new JMenuItem("Vorgang übernehmen");
            itemPopupTake.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    DBHandling.takeVorgang(vorgangid);
                    reloadVTable();
                }
            });
            menu.add(itemPopupTake);
            itemPopupTake.setEnabled(takeAllowed);

            JMenuItem itemPopupDelete = new JMenuItem("Vorgang löschen");
            itemPopupDelete.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    if (JOptionPane.showConfirmDialog(parent, "Soll der Vorgang wirklich gelöscht werden.",
                            "Vorgang löschen", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        DBHandling.deleteVorgang(vorgangid);
                        reloadVTable();
                    }
                }
            });
            menu.add(itemPopupDelete);
            itemPopupDelete.setEnabled(delAllowed);

            JMenu menuAssign = getUserAssignMenu();
            menu.add(menuAssign);
            menuAssign.setEnabled(assignAllowed);

            menu.show(evt.getComponent(), (int) p.getX(), (int) p.getY());
        }
    }//GEN-LAST:event_tblVorgangMousePressed

    private void tblElementMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblElementMousePressed
        if (vorgangid > 0 && evt.isPopupTrigger()) {
            SYSTools.unregisterListeners(menu);
            menu = new JPopupMenu();
            Point p = evt.getPoint();
            int row = tblElement.rowAtPoint(p);
            OPDE.debug(row);

            JMenuItem itemPopupText = new JMenuItem("Neuen Vorgangsbericht erstellen");
            itemPopupText.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    DlgText dlg = new DlgText(parent, "Neuer Vorgangsbericht", "");
                    dlg.showDialog();
                    DBHandling.newVBericht(vorgangid, dlg.getText(), DBHandling.VBERICHT_ART_USER);
                    reloadETable();
                }
            });
            menu.add(itemPopupText);

            TableModel tm = (TableModel) tblElement.getModel();
            if (tm.getRowCount() > 0 && row > -1) {

                //int col = tblVorgang.columnAtPoint(p);

                ListSelectionModel lsm = tblElement.getSelectionModel();
                lsm.setSelectionInterval(row, row);

                final long elementid = ((Long) tm.getValueAt(lsm.getLeadSelectionIndex(), TMElement.COL_ELEMENTID)).longValue();
                final int tblidx = ((Integer) tm.getValueAt(lsm.getLeadSelectionIndex(), TMElement.COL_TBLIDX)).intValue();
                final boolean sys = ((Boolean) tm.getValueAt(lsm.getLeadSelectionIndex(), TMElement.COL_SYSBERICHT)).booleanValue();
                final JPanel thisPanel = this;

                JMenuItem itemPopupDel = new JMenuItem("Element entfernen");
                itemPopupDel.addActionListener(new java.awt.event.ActionListener() {

                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        if (JOptionPane.showConfirmDialog(thisPanel, "Möchten Sie dieses Element wirklich aus dem Vorgang entfernen ?", "Element entfernen",
                                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                            DBHandling.removeElement(tblidx, elementid);
                            reloadETable();
                        }
                    }
                });
                menu.add(itemPopupDel);
                itemPopupDel.setEnabled(!sys); // Systemberichte können nicht gelöscht werden.
            }
            menu.show(evt.getComponent(), (int) p.getX(), (int) p.getY());
        }
    }//GEN-LAST:event_tblElementMousePressed

    private void jspElementMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jspElementMousePressed
        tblElementMousePressed(evt);
    }//GEN-LAST:event_jspElementMousePressed

    private void reloadETable() {
        cbSystem.setEnabled(true);
        //ListSelectionModel lsm = tblVorgang.getSelectionModel();

        tblElement.setModel(new TMElement(vorgangid, cbSystem.isSelected()));
        tblElement.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jspElement.dispatchEvent(new ComponentEvent(jspElement, ComponentEvent.COMPONENT_RESIZED));

        tblElement.getColumnModel().getColumn(0).setCellRenderer(new RNDHTML());
        tblElement.getColumnModel().getColumn(1).setCellRenderer(new RNDHTML());
        tblElement.getColumnModel().getColumn(0).setHeaderValue("Datum / MA");
        tblElement.getColumnModel().getColumn(1).setHeaderValue("Inhalt");
    }

    private void reloadVTable() {
        if (!SYSTools.catchNull(currentBW).equals("")) {
            if (lblbw == null) {
                SYSTools.setBWLabel(lblBW, currentBW);
                lblbw = lblBW;
            } else {
                lblBW = lblbw;
            }
        } else if (!SYSTools.catchNull(besitzer).equals("")) {
            OPDE.debug(besitzer);
            lblBW.setText("Vorgänge zeigen für: " + DBRetrieve.getUsername(besitzer));
            lblbw = null;
        } else {
            lblBW.setText(" ");
            lblbw = null;
        }

        ListSelectionModel lsm = tblVorgang.getSelectionModel();
        if (lslV != null) {
            lsm.removeListSelectionListener(lslV);
        }
        lslV = new HandleVSelections();

        tblVorgang.setModel(new TMVorgang(currentBW, archiv, wv, besitzer));
//        if (this.vorgangid == 0) {
//            tblVorgang.setModel(new TMVorgang(currentBW, archiv, wv, besitzer));
//        } else {
//            tblVorgang.setModel(new TMVorgang(vorgangid));
//        }
        lsm.addListSelectionListener(lslV);
        tblVorgang.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblVorgang.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        tblVorgang.getColumnModel().getColumn(0).setCellRenderer(new RNDHTML());
        tblVorgang.getColumnModel().getColumn(0).setHeaderValue("Vorgang");
        tblElement.setModel(new DefaultTableModel());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox cbSystem;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JScrollPane jspElement;
    private javax.swing.JScrollPane jspVorgang;
    private javax.swing.JLabel lblBW;
    private javax.swing.JTable tblElement;
    private javax.swing.JTable tblVorgang;
    // End of variables declaration//GEN-END:variables

    class HandleVSelections implements ListSelectionListener {

        public void valueChanged(ListSelectionEvent lse) {
            // Erst reagieren wenn der Auswahl-Vorgang abgeschlossen ist.
            TMVorgang tm = (TMVorgang) tblVorgang.getModel();
            if (tm.getRowCount() <= 0) {
                return;
            }

            if (!lse.getValueIsAdjusting()) {
                DefaultListSelectionModel lsm = (DefaultListSelectionModel) lse.getSource();
                if (lsm.isSelectionEmpty()) {
                    vorgangid = 0;
                    cbSystem.setEnabled(false);
                    tblElement.setModel(new DefaultTableModel());
                    if (mode == MODE_CALLED_FROM_VERWALTUNG) {
                        lblBW.setText(" ");
                    }
                } else {
                    vorgangid = ((Long) tm.getValueAt(lsm.getLeadSelectionIndex(), TMVorgang.COL_VORGANGID)).longValue();
                    if (mode == MODE_CALLED_FROM_VERWALTUNG) {
                        currentBW = (tm.getValueAt(lsm.getLeadSelectionIndex(), TMVorgang.COL_BWKENNUNG)).toString();
                        if (SYSTools.catchNull(currentBW).equals("")) {
                            lblBW.setText("Allgemeiner Vorgang");
                            lblBW.setToolTipText("Das bedeutet, dass er keinem/r bestimmten BewohnerIn zugeordnet ist.");
                        } else {
                            SYSTools.setBWLabel(lblBW, currentBW);
                        }
                    }
                    reloadETable();
                }
            }
        }
    }
}
