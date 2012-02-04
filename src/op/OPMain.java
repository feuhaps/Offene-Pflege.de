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
 */
package op;

import entity.system.SYSHostsTools;
import entity.system.SYSLoginTools;
import op.share.tools.PnlEditor;

import op.tools.DlgException;
import op.tools.DlgLogin;
import op.tools.SYSTools;
import tablemodels.TMVorgang;
import tablerenderer.RNDHTML;

import javax.swing.*;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * @author __USER__
 */
public class OPMain extends javax.swing.JFrame {


    public static final String internalClassID = "opmain";
    private final Frame parent = this;
    private HashMap frames;
    private ArrayList menuStructure;
    //private String classname;
    private JPanel pnlMainButtons;
    private JPopupMenu menu;

    public OPMain() {
        //this.classname = this.getClass().getName();
        initComponents();


        menuStructure = new ArrayList();
        menuStructure.add(new String[]{"Pflege/Pflegeakte", "op.FrmMain", "pflegeakte.png"});
        menuStructure.add(new String[]{"Pflege/Medikamente", "op.care.med.FrmMed", "agt_virussafe.png"});
        menuStructure.add(new String[]{"Pflege/Massnahmen", "op.care.planung.massnahmen.FrmMassnahmen", "work.png"});
        menuStructure.add(new String[]{"Bewohner/Bewohnerdaten", "op.bw.admin.FrmBWAttr", "groupevent.png"});
//        menuStructure.add(new String[]{"Bewohner/Barbeträge", "op.bw.tg.PnlTG", "coins.png"});
        menuStructure.add(new String[]{"System/Mitarbeiter", "op.ma.admin.FrmUser", "identity.png"});
        menuStructure.add(new String[]{"System/Datei-Manager", "op.sysfiles.FrmFilesManager", "kfm.png"});
        menuStructure.add(new String[]{"Controlling/Controlling", "op.controlling.FrmCtrlMonitor", "kfind.png"});
        menuStructure.add(new String[]{"Controlling/Vorgänge", "op.vorgang.FrmVorgang", "utilities-file-archiver.png"});

        frames = new HashMap();
        //this.setTitle(OPDE.getLocalProps().getProperty("program.PROGNAME") + ", Version " + OPDE.getLocalProps().getProperty("program.VERSION") +
        //        " (Build: " + OPDE.getLocalProps().getProperty("program.BUILDNUM") + ")");
        this.setTitle(SYSTools.getWindowTitle(""));
        this.setVisible(true);

        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        lblServer.setText(OPDE.url);

        ToolTipManager.sharedInstance().setInitialDelay(500);
        ToolTipManager.sharedInstance().setDismissDelay(2500);

        txtGeburtstag.setContentType("text/html");
        txtAlarm.setContentType("text/html");

        initOC();

        //new NewJDialog(this, false).setVisible(true);
    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        jmbMain = new JMenuBar();
        pnlStatus = new JPanel();
        lblServer = new JLabel();
        btnLogout = new JButton();
        jspMainButtons = new JScrollPane();
        jspVorgang = new JScrollPane();
        tblVorgang = new JTable();
        jScrollPane1 = new JScrollPane();
        txtGeburtstag = new JTextPane();
        jScrollPane2 = new JScrollPane();
        txtAlarm = new JTextPane();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        addWindowFocusListener(new WindowAdapter() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
                formWindowGainedFocus(e);
            }
        });
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                formWindowClosing(e);
            }
            @Override
            public void windowDeiconified(WindowEvent e) {
                formWindowDeiconified(e);
            }
        });
        Container contentPane = getContentPane();
        setJMenuBar(jmbMain);

        //======== pnlStatus ========
        {

            //---- lblServer ----
            lblServer.setText("jLabel2");
            lblServer.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));

            GroupLayout pnlStatusLayout = new GroupLayout(pnlStatus);
            pnlStatus.setLayout(pnlStatusLayout);
            pnlStatusLayout.setHorizontalGroup(
                pnlStatusLayout.createParallelGroup()
                    .addComponent(lblServer, GroupLayout.DEFAULT_SIZE, 948, Short.MAX_VALUE)
            );
            pnlStatusLayout.setVerticalGroup(
                pnlStatusLayout.createParallelGroup()
                    .addComponent(lblServer, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
        }

        //---- btnLogout ----
        btnLogout.setIcon(new ImageIcon(getClass().getResource("/artwork/48x48/lock.png")));
        btnLogout.setText("Abmelden");
        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnLogoutActionPerformed(e);
            }
        });

        //======== jspVorgang ========
        {
            jspVorgang.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentResized(ComponentEvent e) {
                    jspVorgangComponentResized(e);
                }
            });

            //---- tblVorgang ----
            tblVorgang.setModel(new DefaultTableModel(
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
            tblVorgang.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    tblVorgangMousePressed(e);
                }
            });
            jspVorgang.setViewportView(tblVorgang);
        }

        //======== jScrollPane1 ========
        {

            //---- txtGeburtstag ----
            txtGeburtstag.setEditable(false);
            jScrollPane1.setViewportView(txtGeburtstag);
        }

        //======== jScrollPane2 ========
        {

            //---- txtAlarm ----
            txtAlarm.setBackground(new Color(255, 204, 204));
            txtAlarm.setEditable(false);
            jScrollPane2.setViewportView(txtAlarm);
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addComponent(pnlStatus, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jspVorgang, GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                    .addGap(10, 10, 10)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(btnLogout, GroupLayout.Alignment.TRAILING)
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addComponent(jScrollPane2, GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)))
                    .addContainerGap())
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jspMainButtons, GroupLayout.DEFAULT_SIZE, 908, Short.MAX_VALUE)
                    .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(btnLogout)
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(jScrollPane2, GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                        .addComponent(jspVorgang, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jspMainButtons, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(pnlStatus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );
        pack();
        setLocationRelativeTo(getOwner());
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (OPDE.getLogin() != null) {
            // OPDE.getDb().doLogout();
            try {
                OPDE.getDb().db.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                System.exit(1);
            }
        }

        SYSHostsTools.shutdown(0);

    }//GEN-LAST:event_formWindowClosing

    public void dispose() {
    }

    private void newFrame(String classname) {
        OPDE.debug("NewFrame with Classname: " + classname);
        // Key für die InnerClass
        final String key = classname;
        if (!frames.containsKey(classname)) {
            Class c = null;
            JFrame frame = null;
            try {
                c = Class.forName(classname);
                frame = (JFrame) c.newInstance();
                if (!frame.isVisible()) {
                    frame.setVisible(true);
                }
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            } catch (InstantiationException ex) {
                ex.printStackTrace();
            }

            frames.put(key, frame);
            SYSTools.iconify(parent);
            ((JFrame) frames.get(classname)).addWindowListener(new java.awt.event.WindowAdapter() {

                public void windowClosed(java.awt.event.WindowEvent evt) {
                    frames.remove(key);
                    if (frames.size() == 0) {
                        SYSTools.deiconify(parent);
                    }
                }
            });
        } else {
            ((JFrame) frames.get(classname)).setVisible(true);
        }
    }

    private void disposeFrame(String key) {
        if (frames.containsKey(key)) {
            ((JFrame) frames.get(key)).dispose();
            frames.remove(key);
        }
    }

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        lockOC();
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void jspVorgangComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jspVorgangComponentResized
        if (tblVorgang.getModel().getColumnCount() > 0) {
            TableColumnModel tcm1 = tblVorgang.getColumnModel();
            tcm1.getColumn(0).setHeaderValue(SYSTools.toHTML("<b>Vorgänge, die Ihnen zugeordnet sind.</b>"));
        }
    }//GEN-LAST:event_jspVorgangComponentResized

    private void formWindowDeiconified(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowDeiconified
        if (OPDE.getLogin() != null && OPDE.getLogin().getLoginID() > 0) {
            reloadVTable();
            reloadAlarm();
        }
    }//GEN-LAST:event_formWindowDeiconified

    private void tblVorgangMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVorgangMousePressed
        TMVorgang tm = (TMVorgang) tblVorgang.getModel();
        if (OPDE.getLogin().getLoginID() < 0 || tm.getRowCount() == 0) {
            return;
        }
        Point p = evt.getPoint();
        //int col = tblVorgang.columnAtPoint(p);
        int row = tblVorgang.rowAtPoint(p);
        ListSelectionModel lsm = tblVorgang.getSelectionModel();
        lsm.setSelectionInterval(row, row);


        if (evt.isPopupTrigger()) {

            SYSTools.unregisterListeners(menu);
            menu = new JPopupMenu();

            JMenuItem itemPopupEnd = new JMenuItem("Vorgänge bearbeiten");
            itemPopupEnd.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    ///newVrgFrame();
                    newFrame("op.share.vorgang.PnlVorgang");
                }
            });
            menu.add(itemPopupEnd);
            itemPopupEnd.setEnabled(true);
            menu.show(evt.getComponent(), (int) p.getX(), (int) p.getY());
        } else {
            if (evt.getClickCount() == 2) {
                newFrame("op.share.vorgang.PnlVorgang");
            }
        }
    }//GEN-LAST:event_tblVorgangMousePressed

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        if (OPDE.getLogin() != null && OPDE.getLogin().getLoginID() > 0) {
            reloadVTable();
        }
    }//GEN-LAST:event_formWindowGainedFocus

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JFrame frm = new JFrame();
        frm.setContentPane(new PnlEditor());
        frm.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                JFrame frm = (JFrame) e.getSource();
                OPDE.debug(((PnlEditor) frm.getContentPane()).getHTML());
            }
        });

        frm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frm.setSize(300, 200);
        frm.pack();
        frm.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JMenuBar jmbMain;
    private JPanel pnlStatus;
    private JLabel lblServer;
    private JButton btnLogout;
    private JScrollPane jspMainButtons;
    private JScrollPane jspVorgang;
    private JTable tblVorgang;
    private JScrollPane jScrollPane1;
    private JTextPane txtGeburtstag;
    private JScrollPane jScrollPane2;
    private JTextPane txtAlarm;
    // End of variables declaration//GEN-END:variables

    public void lockOC() {
        lockOC("");
    }

    private void reloadAlarm() {
        String alarm = "";
        alarm += op.controlling.DBHandling.getAlarmStuhl(1);
        alarm += op.controlling.DBHandling.getAlarmEinfuhr(1);
        if (alarm.equals("")) {
            alarm = "Keine Alarmmeldungen gefunden";
        }
        txtAlarm.setText(SYSTools.toHTML(alarm));
    }

    public void lockOC(String message) {
        disposeFrame("op.care.FrmPflege");
        OPDE.notifyAboutLogout();
        if (frames.size() == 0) {
            // Menüs aufräumen
            SYSTools.unregisterListeners(jmbMain);
            jmbMain.removeAll();
            // MainButtons aufräumen
            jspMainButtons.remove(pnlMainButtons);
            SYSTools.unregisterListeners(pnlMainButtons);
            pnlMainButtons.removeAll();
            pnlMainButtons = null;

            // Vorgangsanzeige leeren sonst kann es zu
            // Exceptions wegen Zugriff auf geschlossener
            // DB kommen.
            tblVorgang.setModel(new DefaultTableModel());
            txtGeburtstag.setText("");
            txtAlarm.setText(SYSTools.toHTML("Keine Alarmmeldungen gefunden"));

            try {
                OPDE.closeDB();
            } catch (SQLException ex) {
                new DlgException(ex);
                ex.printStackTrace();
                System.exit(1);
            }

            SYSLoginTools.logout();

            System.gc();
            OPDE.getEMF().getCache().evictAll();
            this.setVisible(true);
            this.setExtendedState(JFrame.MAXIMIZED_BOTH);

            //this.paintComponents(this.getGraphics());
            initOC(message);
        } else {
            JOptionPane.showMessageDialog(this, "Es sind noch " + frames.size() + " offene Fenster vorhanden. Bitte schließen Sie diese erst.", "Abmeldung nicht möglich", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    public void initOC() {
        initOC("");
    }

    public void initOC(String preMessage) {
        btnLogout.setEnabled(false);
        SYSTools.unregisterListeners(menu);

        tblVorgang.setModel(new DefaultTableModel());
        tblVorgang.setVisible(false);
        new DlgLogin(this, preMessage);
        if (OPDE.getLogin() == null) {
            System.exit(0);
        }
        createMenuAndButtons();
        reloadVTable();
        reloadAlarm();
        txtGeburtstag.setText(op.controlling.DBHandling.getGeburtstage(4));

        btnLogout.setEnabled(true);
    }

    private void reloadVTable() {
        TMVorgang tm = new TMVorgang(null, false, 0, OPDE.getLogin().getUser().getUKennung());
        int rows = tm.getRowCount();
        tblVorgang.setVisible(rows > 0);
        if (rows > 0) {
            tblVorgang.setModel(tm);
            tblVorgang.getColumnModel().getColumn(0).setCellRenderer(new RNDHTML());
            tblVorgang.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
            tblVorgang.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            jspVorgang.dispatchEvent(new ComponentEvent(jspVorgang, ComponentEvent.COMPONENT_RESIZED));
            //tblVorgang.getColumnModel().getColumn(0).setCellRenderer(new RNDHTML());
        } else {
            tm = null;
        }
    }

    private void createMenuAndButtons() {
        // Dieser Teil ist immer dabei.
        JMenu datei = new JMenu("Datei");
        JMenuItem logout = new JMenuItem("Abmelden");
        logout.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                SYSTools.deiconify(parent);
                lockOC();
            }
        });
        JMenuItem quit = new JMenuItem("Beenden");
        quit.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        datei.add(logout);
        datei.add(quit);
        jmbMain.add(datei);
        // Ein neues Panel für die Buttons erstellen.
        pnlMainButtons = new JPanel(new FlowLayout(FlowLayout.LEADING));
        jspMainButtons.setViewportView(pnlMainButtons);
        // Jetzt beginnt der dynamische Teil.
        OCSec ocs = OPDE.getOCSec();
        String classname = this.getClass().getName();
        ocs.init(classname);
        JMenu currentRoot = null;
        for (int item = 0; item
                < menuStructure.size(); item++) {
            String[] menuItem = (String[]) menuStructure.get(item);
            // Nur erstellen, wenn der User auch die passenden Rechte dafür hat.
            if (ocs.isExecutable(classname, menuItem[1])) {
                StringTokenizer st = new StringTokenizer(menuItem[0], "/");
                final String frameName = menuItem[1];
                String rootName = st.nextToken();
                if (currentRoot == null || !currentRoot.getText().equals(rootName)) {
                    if (currentRoot != null) { // Fügt den vorhergehenden Menüstrang hinzu.
                        jmbMain.add(currentRoot);
                    }

                    currentRoot = new JMenu(rootName);
                }

                JMenuItem thisItem = currentRoot;
                while (st.hasMoreTokens()) {
                    String name = st.nextToken();
                    if (thisItem == null || !thisItem.getText().equals(name)) {
                        thisItem = new JMenuItem(name);
                        if (!menuItem[2].equals("")) {
                            thisItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/artwork/16x16/" + menuItem[2])));
                        }
                        currentRoot.add(thisItem);
                    }

                    if (!st.hasMoreTokens()) { // Hier ist das eigentliche Blatt erreicht.
                        ActionListener al = new ActionListener() {

                            public void actionPerformed(ActionEvent e) {
                                newFrame(frameName);
                            }
                        };
                        thisItem.addActionListener(al);
                        JButton btn = new JButton(name);
                        if (!menuItem[2].equals("")) {
                            btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/artwork/48x48/" + menuItem[2])));
                        }

                        btn.addActionListener(al);
                        pnlMainButtons.add(btn);
                    }

                } // while
            }
        }
        jmbMain.add(currentRoot); // Fügt das letzte Menü hinzu.
        this.pack();
//        this.paintComponents(this.getGraphics());
//        this.paintAll(this.getGraphics());
    }
}
