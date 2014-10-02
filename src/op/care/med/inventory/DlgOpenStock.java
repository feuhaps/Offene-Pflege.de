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
package op.care.med.inventory;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import entity.info.Resident;
import entity.prescription.*;
import op.OPDE;
import op.threads.DisplayManager;
import op.tools.MyJDialog;
import op.tools.SYSConst;
import op.tools.SYSTools;
import org.apache.commons.collections.Closure;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.Query;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author tloehr
 */
public class DlgOpenStock extends MyJDialog {

    private MedInventory inventory;
    private Closure actionBlock;
    private MedStock medStock;

    /**
     * Creates new form DlgOpenStock
     */
    public DlgOpenStock(TradeForm darreichung, Resident resident, Closure actionBlock) {
        super(false);
        this.inventory = TradeFormTools.getInventory4TradeForm(resident, darreichung);
        this.medStock = null;
        this.actionBlock = actionBlock;
        initComponents();
        initDialog();
        pack();
        setVisible(true);
    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the PrinterForm Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        lbl1 = new JLabel();
        cmbBestID = new JComboBox<>();
        lbl2 = new JLabel();
        btnClose = new JButton();
        btnOK = new JButton();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        Container contentPane = getContentPane();
        contentPane.setLayout(new FormLayout(
            "default, $lcgap, default:grow, 2*($lcgap, default), $lcgap, default:grow, $lcgap, default",
            "default, $lgap, fill:default, 21dlu, default, $lgap, default"));

        //---- lbl1 ----
        lbl1.setText("Die Packung mit der Nummer:");
        lbl1.setFont(new Font("Arial", Font.PLAIN, 14));
        contentPane.add(lbl1, CC.xy(3, 3));

        //---- cmbBestID ----
        cmbBestID.setModel(new DefaultComboBoxModel<>(new String[] {
            "Item 1",
            "Item 2",
            "Item 3",
            "Item 4"
        }));
        cmbBestID.setFont(new Font("Arial", Font.PLAIN, 14));
        cmbBestID.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                cmbBestIDItemStateChanged(e);
            }
        });
        contentPane.add(cmbBestID, CC.xy(5, 3));

        //---- lbl2 ----
        lbl2.setText("anbrechen.");
        lbl2.setFont(new Font("Arial", Font.PLAIN, 14));
        contentPane.add(lbl2, CC.xywh(7, 3, 3, 1));

        //---- btnClose ----
        btnClose.setIcon(new ImageIcon(getClass().getResource("/artwork/22x22/cancel.png")));
        btnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnCloseActionPerformed(e);
            }
        });
        contentPane.add(btnClose, CC.xy(7, 5));

        //---- btnOK ----
        btnOK.setIcon(new ImageIcon(getClass().getResource("/artwork/22x22/apply.png")));
        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnOKActionPerformed(e);
            }
        });
        contentPane.add(btnOK, CC.xy(9, 5));
        setSize(455, 165);
        setLocationRelativeTo(getOwner());
    }// </editor-fold>//GEN-END:initComponents

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        medStock = null;
        dispose();
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKActionPerformed
        if (cmbBestID.getSelectedIndex() > 0) {
            EntityManager em = OPDE.createEM();
            try {
                em.getTransaction().begin();
                medStock = em.merge((MedStock) cmbBestID.getSelectedItem());
                em.lock(medStock, LockModeType.OPTIMISTIC);
                em.lock(em.merge(medStock.getInventory().getResident()), LockModeType.OPTIMISTIC);

                medStock.setOpened(new Date());

                em.getTransaction().commit();
            } catch (javax.persistence.OptimisticLockException ole) {
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
                if (ole.getMessage().indexOf("Class> entity.info.Resident") > -1) {
                    OPDE.getMainframe().emptyFrame();
                    OPDE.getMainframe().afterLogin();
                }
                OPDE.getDisplayManager().addSubMessage(DisplayManager.getLockMessage());
            } catch (Exception e) {
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
                OPDE.fatal(e);
            } finally {
                em.close();
            }
            dispose();
        }
    }//GEN-LAST:event_btnOKActionPerformed

    @Override
    public void dispose() {
        actionBlock.execute(medStock);
        super.dispose();
    }

    private void initDialog() {
        EntityManager em = OPDE.createEM();
        Query query = em.createQuery(" " +
                " SELECT b FROM MedStock b " +
                " WHERE b.inventory = :vorrat AND b.out = :aus AND b.opened = :anbruch " +
                " ORDER BY b.in, b.id "); // Geht davon aus, dass die PKs immer fortlaufend, automatisch vergeben werden.
        query.setParameter("vorrat", inventory);
        query.setParameter("aus", SYSConst.DATE_UNTIL_FURTHER_NOTICE);
        query.setParameter("anbruch", SYSConst.DATE_UNTIL_FURTHER_NOTICE);
        DefaultComboBoxModel dcbm = new DefaultComboBoxModel(query.getResultList().toArray());
        dcbm.insertElementAt(SYSTools.xx("misc.msg.none"), 0);
        cmbBestID.setModel(dcbm);
        cmbBestID.setRenderer(MedStockTools.getBestandOnlyIDRenderer());

        int index = Math.min(2, cmbBestID.getItemCount());
        cmbBestID.setSelectedIndex(index - 1);
    }

    private void cmbBestIDItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbBestIDItemStateChanged
        if (cmbBestID.getSelectedIndex() == 0) {
            cmbBestID.setToolTipText(null);
        } else {
            cmbBestID.setToolTipText(SYSTools.toHTML(MedStockTools.getTextASHTML((MedStock) cmbBestID.getSelectedItem())));
        }
    }//GEN-LAST:event_cmbBestIDItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JLabel lbl1;
    private JComboBox<String> cmbBestID;
    private JLabel lbl2;
    private JButton btnClose;
    private JButton btnOK;
    // End of variables declaration//GEN-END:variables
}
