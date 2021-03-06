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
import entity.prescription.MedStockTools;
import entity.prescription.MedStockTransaction;
import entity.prescription.MedStockTransactionTools;
import entity.prescription.TradeFormTools;
import op.OPDE;
import op.tools.MyJDialog;
import op.tools.SYSTools;
import org.apache.commons.collections.Closure;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.math.BigDecimal;
import java.text.NumberFormat;

/**
 *
 */
public class DlgTX extends MyJDialog {
    private BigDecimal amount, weight;
    private Closure actionBlock;
    private BigDecimal bestandsumme;
    private BigDecimal packgroesse;
    private MedStockTransaction tx;

    public DlgTX(MedStockTransaction tx, Closure actionBlock) {
        super(false);
        this.tx = tx;
        this.actionBlock = actionBlock;
        initDialog();
    }

    private void txtMengeFocusGained(FocusEvent e) {
        txtValue.selectAll();
    }

//    @Override
//    public void dispose() {
//        super.dispose();
//    }

    private void txtTextActionPerformed(ActionEvent e) {
        txtValue.requestFocus();
    }

    private void txtValueActionPerformed(ActionEvent e) {
        txtText.requestFocus();
    }

    boolean isAmountOk() {
        boolean amountOK = false;
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            amountOK = amount.negate().compareTo(bestandsumme) <= 0;
        } else if (amount.compareTo(BigDecimal.ZERO) > 0) {
            amountOK = amount.compareTo(packgroesse.subtract(bestandsumme)) <= 0;
        }
        return amountOK;
    }


    boolean isWeightOk() {
        if (!tx.getStock().getTradeForm().isWeightControlled()) return true;
        boolean weightOK = weight.compareTo(BigDecimal.ZERO) > 0;
        return weightOK;
    }

    private void txtWeightControlledFocusGained(FocusEvent e) {
        txtWeightControlled.selectAll();
    }

    private void txtWeightControlledCaretUpdate(CaretEvent evt) {
        weight = SYSTools.checkBigDecimal(evt, false);
        OPDE.debug("weight = " + weight.toString());
        btnBuchung.setEnabled(isAmountOk() && isWeightOk());
    }

    private void initDialog() {
        initComponents();
        lblText.setText(SYSTools.xx("opde.medication.tx.text"));
        lblValue.setText(SYSTools.xx("misc.msg.amount"));
        lblWeightControl.setText(SYSTools.xx("opde.medication.tx.controlWeight"));
        lblUnit2.setText("g");
        bestandsumme = MedStockTools.getSum(tx.getStock());
        weight = null;
        txtWeightControlled.setVisible(tx.getStock().getTradeForm().isWeightControlled());
        lblWeightControl.setVisible(tx.getStock().getTradeForm().isWeightControlled());

        lblUnit.setText(TradeFormTools.getPackUnit(tx.getStock().getTradeForm()));

        if (tx.getStock().hasPackage()) {
            packgroesse = tx.getStock().getPackage().getContent();
        } else {
            packgroesse = BigDecimal.valueOf(Double.MAX_VALUE);
        }

        txtValue.setText(NumberFormat.getNumberInstance().format(tx.getAmount()));

        if (txtWeightControlled.isVisible()) {
            txtWeightControlled.setToolTipText(SYSTools.xx("opde.medication.controlWeight.only.after.change"));
            txtWeightControlled.setText(NumberFormat.getNumberInstance().format(tx.getWeight() != null ? tx.getWeight() : BigDecimal.ZERO));
        }
        setVisible(true);
    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the PrinterForm Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        lblText = new JLabel();
        txtText = new JTextField();
        txtValue = new JTextField();
        lblValue = new JLabel();
        lblUnit = new JLabel();
        lblWeightControl = new JLabel();
        txtWeightControlled = new JTextField();
        lblUnit2 = new JLabel();
        panel1 = new JPanel();
        btnCancel = new JButton();
        btnBuchung = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(new FormLayout(
                "default, $lcgap, default, $ugap, 141dlu:grow, $rgap, default, $lcgap, default",
                "2*(default, $lgap), fill:default, $lgap, default, $lgap, fill:default"));

        //---- lblText ----
        lblText.setText("Buchungstext");
        lblText.setFont(new Font("Arial", Font.PLAIN, 14));
        contentPane.add(lblText, CC.xy(3, 3, CC.DEFAULT, CC.TOP));

        //---- txtText ----
        txtText.setColumns(100);
        txtText.addActionListener(e -> txtTextActionPerformed(e));
        contentPane.add(txtText, CC.xywh(5, 3, 3, 1));

        //---- txtValue ----
        txtValue.setHorizontalAlignment(SwingConstants.RIGHT);
        txtValue.setText("jTextField1");
        txtValue.setFont(new Font("Arial", Font.PLAIN, 14));
        txtValue.addCaretListener(e -> txtMengeCaretUpdate(e));
        txtValue.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                txtMengeFocusGained(e);
            }
        });
        txtValue.addActionListener(e -> txtValueActionPerformed(e));
        contentPane.add(txtValue, CC.xy(5, 5));

        //---- lblValue ----
        lblValue.setText("Menge");
        lblValue.setFont(new Font("Arial", Font.PLAIN, 14));
        contentPane.add(lblValue, CC.xy(3, 5));

        //---- lblUnit ----
        lblUnit.setHorizontalAlignment(SwingConstants.TRAILING);
        lblUnit.setText("jLabel4");
        lblUnit.setFont(new Font("Arial", Font.PLAIN, 14));
        contentPane.add(lblUnit, CC.xy(7, 5));

        //---- lblWeightControl ----
        lblWeightControl.setText("Menge");
        lblWeightControl.setFont(new Font("Arial", Font.PLAIN, 14));
        contentPane.add(lblWeightControl, CC.xy(3, 7));

        //---- txtWeightControlled ----
        txtWeightControlled.setHorizontalAlignment(SwingConstants.RIGHT);
        txtWeightControlled.setText("jTextField1");
        txtWeightControlled.setFont(new Font("Arial", Font.PLAIN, 14));
        txtWeightControlled.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                txtWeightControlledFocusGained(e);
            }
        });
        txtWeightControlled.addCaretListener(e -> txtWeightControlledCaretUpdate(e));
        contentPane.add(txtWeightControlled, CC.xy(5, 7));

        //---- lblUnit2 ----
        lblUnit2.setHorizontalAlignment(SwingConstants.TRAILING);
        lblUnit2.setText("g");
        lblUnit2.setFont(new Font("Arial", Font.PLAIN, 14));
        contentPane.add(lblUnit2, CC.xy(7, 7, CC.LEFT, CC.DEFAULT));

        //======== panel1 ========
        {
            panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));

            //---- btnCancel ----
            btnCancel.setIcon(new ImageIcon(getClass().getResource("/artwork/22x22/cancel.png")));
            btnCancel.addActionListener(e -> btnCancelActionPerformed(e));
            panel1.add(btnCancel);

            //---- btnBuchung ----
            btnBuchung.setIcon(new ImageIcon(getClass().getResource("/artwork/22x22/apply.png")));
            btnBuchung.addActionListener(e -> btnBuchungActionPerformed(e));
            panel1.add(btnBuchung);
        }
        contentPane.add(panel1, CC.xywh(5, 9, 3, 1, CC.RIGHT, CC.DEFAULT));
        setSize(600, 165);
        setLocationRelativeTo(getOwner());
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        tx = null;
        dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnBuchungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuchungActionPerformed
        tx.setAmount(amount);
        tx.setWeight(weight);
        tx.setState(MedStockTransactionTools.STATE_EDIT_MANUAL);
        tx.setText(txtText.getText().trim());
        actionBlock.execute(tx);
        dispose();
    }//GEN-LAST:event_btnBuchungActionPerformed


    private void txtMengeCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtMengeCaretUpdate
        amount = SYSTools.checkBigDecimal(evt, false);
        btnBuchung.setEnabled(isAmountOk() && isWeightOk());
        OPDE.debug("amount = " + amount.toString());
//        if (amount.compareTo(BigDecimal.ZERO) < 0) {
//            btnBuchung.setEnabled(amount.negate().compareTo(bestandsumme) <= 0);
//        } else if (amount.compareTo(BigDecimal.ZERO) > 0) {
//            btnBuchung.setEnabled(amount.compareTo(packgroesse.subtract(bestandsumme)) <= 0);
//        } else {
//            btnBuchung.setEnabled(false);
//        }
    }//GEN-LAST:event_txtMengeCaretUpdate


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JLabel lblText;
    private JTextField txtText;
    private JTextField txtValue;
    private JLabel lblValue;
    private JLabel lblUnit;
    private JLabel lblWeightControl;
    private JTextField txtWeightControlled;
    private JLabel lblUnit2;
    private JPanel panel1;
    private JButton btnCancel;
    private JButton btnBuchung;
    // End of variables declaration//GEN-END:variables

}
