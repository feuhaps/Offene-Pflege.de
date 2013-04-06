/*
 * Created by JFormDesigner on Sat Apr 06 12:02:54 CEST 2013
 */

package op.misc;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import entity.info.ResInfoCategory;
import entity.nursingprocess.Intervention;
import entity.nursingprocess.InterventionTools;
import op.OPDE;
import op.care.nursingprocess.PnlSelectIntervention;
import op.threads.DisplayManager;
import op.threads.DisplayMessage;
import op.tools.GUITools;
import op.tools.MyJDialog;
import op.tools.SYSTools;
import org.jdesktop.swingx.JXSearchField;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.OptimisticLockException;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Arrays;

/**
 * @author Torsten Löhr
 */
public class DlgIntervention extends MyJDialog {
    Intervention intervention2Edit;
    private JToggleButton tbAktiv;
    Number dauer = BigDecimal.TEN;

    public DlgIntervention() {
        super(false);
        initComponents();
        initDialog();
    }

    private void initDialog() {
        tbAktiv = GUITools.getNiceToggleButton(PnlSelectIntervention.internalClassID + ".activeIntervention");
        panel2.add(tbAktiv, CC.xywh(1, 9, 2, 1));
    }

    private void txtSearchActionPerformed(ActionEvent e) {
        if (txtSearch.getText().isEmpty()) return;
        lstInterventions.setModel(SYSTools.list2dlm(InterventionTools.findMassnahmenBy(InterventionTools.TYPE_CARE, txtSearch.getText())));
    }

    private void lstInterventionsMouseClicked(MouseEvent e) {
        // TODO add your code here
    }

    private void lstInterventionsValueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            intervention2Edit = (Intervention) lstInterventions.getSelectedValue();
            txtText.setText(intervention2Edit.getBezeichnung());
            txtLength.setText(intervention2Edit.getDauer().toBigInteger().toString());
            tbAktiv.setSelected(intervention2Edit.isActive());
            cmbType.setSelectedIndex(intervention2Edit.getInterventionType() - 1);
        }
    }

    private boolean saveok() {
        if (txtText.getText().trim().isEmpty()) {
            OPDE.getDisplayManager().addSubMessage(new DisplayMessage(OPDE.lang.getString(PnlSelectIntervention.internalClassID + ".textempty"), DisplayMessage.WARNING));
            return false;
        }
        return true;
    }

    private void txtDauerFocusLost(FocusEvent e) {
        try {
            dauer = NumberFormat.getNumberInstance().parse(txtLength.getText());
        } catch (ParseException e1) {
            dauer = BigDecimal.TEN;
            txtLength.setText("10");
        }
    }

    private void btnEditActionPerformed(ActionEvent e) {
        set2EditMode(true);
    }

    private void set2EditMode(boolean editMode) {
        txtText.setEnabled(editMode);
        txtLength.setEnabled(editMode);
        tbAktiv.setEnabled(editMode);
        cmbType.setEnabled(editMode);
        cmbCat.setEnabled(editMode);
        txtSearch.setEnabled(!editMode);
        lstInterventions.setEnabled(!editMode);
    }

    private void btnCancelActionPerformed(ActionEvent e) {
        set2EditMode(false);
    }

    private void btnSaveActionPerformed(ActionEvent evt) {
        if (saveok()) {
            if (intervention2Edit == null) {
                Intervention intervention = new Intervention(txtText.getText().trim(), new BigDecimal(dauer.doubleValue()), cmbType.getSelectedIndex() + 1, (ResInfoCategory) cmbCat.getSelectedItem());
                lstInterventions.setModel(SYSTools.list2dlm(Arrays.asList(intervention)));
                btnEdit.setEnabled(false);
            } else {
                EntityManager em = OPDE.createEM();
                try {
                    em.getTransaction().begin();
                    Intervention myIntervention = em.merge(intervention2Edit);
                    em.lock(myIntervention, LockModeType.OPTIMISTIC);
                    myIntervention.setBezeichnung(txtText.getText().trim());
                    myIntervention.setDauer(new BigDecimal(dauer.doubleValue()));
                    myIntervention.setCategory(em.merge((ResInfoCategory) cmbCat.getSelectedItem()));
                    myIntervention.setInterventionType(cmbType.getSelectedIndex() + 1);
                    myIntervention.setActive(tbAktiv.isSelected());
                    em.getTransaction().commit();
                } catch (OptimisticLockException ole) {
                    if (em.getTransaction().isActive()) {
                        em.getTransaction().rollback();
                    }
                    if (ole.getMessage().indexOf("Class> entity.info.Bewohner") > -1) {
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

            }
        }
    }

    private void btnEjectActionPerformed(ActionEvent e) {
        dispose();
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panel1 = new JPanel();
        txtSearch = new JXSearchField();
        panel2 = new JPanel();
        lblText = new JLabel();
        txtText = new JTextField();
        lblLength = new JLabel();
        txtLength = new JTextField();
        lblCat = new JLabel();
        cmbCat = new JComboBox();
        lblType = new JLabel();
        cmbType = new JComboBox();
        panel3 = new JPanel();
        btnEdit = new JButton();
        hSpacer1 = new JPanel(null);
        btnCancel = new JButton();
        btnSave = new JButton();
        hSpacer2 = new JPanel(null);
        btnEject = new JButton();
        panel4 = new JScrollPane();
        lstInterventions = new JList();
        btnAdd = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));

        //======== panel1 ========
        {
            panel1.setLayout(new FormLayout(
                "default, $lcgap, 87dlu, $lcgap, default:grow, $lcgap, default",
                "2*(default, $lgap), default:grow, 2*($lgap, default)"));

            //---- txtSearch ----
            txtSearch.setFont(new Font("Arial", Font.PLAIN, 14));
            txtSearch.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    txtSearchActionPerformed(e);
                }
            });
            panel1.add(txtSearch, CC.xy(3, 3));

            //======== panel2 ========
            {
                panel2.setLayout(new FormLayout(
                    "default, $lcgap, 63dlu:grow",
                    "5*(default, $lgap), fill:default:grow"));

                //---- lblText ----
                lblText.setText("Bezeichnung");
                lblText.setFont(new Font("Arial", Font.PLAIN, 14));
                panel2.add(lblText, CC.xy(1, 1));

                //---- txtText ----
                txtText.setFont(new Font("Arial", Font.PLAIN, 14));
                txtText.setEnabled(false);
                panel2.add(txtText, CC.xy(3, 1));

                //---- lblLength ----
                lblLength.setText("Dauer");
                lblLength.setFont(new Font("Arial", Font.PLAIN, 14));
                panel2.add(lblLength, CC.xy(1, 3));

                //---- txtLength ----
                txtLength.setHorizontalAlignment(SwingConstants.RIGHT);
                txtLength.setText("10");
                txtLength.setToolTipText(null);
                txtLength.setFont(new Font("Arial", Font.PLAIN, 14));
                txtLength.setEnabled(false);
                txtLength.addFocusListener(new FocusAdapter() {
                    @Override
                    public void focusLost(FocusEvent e) {
                        txtDauerFocusLost(e);
                    }
                });
                panel2.add(txtLength, CC.xy(3, 3));

                //---- lblCat ----
                lblCat.setText("Kategorie");
                lblCat.setFont(new Font("Arial", Font.PLAIN, 14));
                panel2.add(lblCat, CC.xy(1, 5));

                //---- cmbCat ----
                cmbCat.setModel(new DefaultComboBoxModel(new String[] {
                    "Item 1",
                    "Item 2",
                    "Item 3",
                    "Item 4"
                }));
                cmbCat.setFont(new Font("Arial", Font.PLAIN, 14));
                cmbCat.setEnabled(false);
                panel2.add(cmbCat, CC.xy(3, 5));

                //---- lblType ----
                lblType.setText("Art");
                lblType.setFont(new Font("Arial", Font.PLAIN, 14));
                panel2.add(lblType, CC.xy(1, 7));

                //---- cmbType ----
                cmbType.setModel(new DefaultComboBoxModel(new String[] {
                    "Item 1",
                    "Item 2",
                    "Item 3",
                    "Item 4"
                }));
                cmbType.setFont(new Font("Arial", Font.PLAIN, 14));
                cmbType.setEnabled(false);
                panel2.add(cmbType, CC.xy(3, 7));

                //======== panel3 ========
                {
                    panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));

                    //---- btnEdit ----
                    btnEdit.setText(null);
                    btnEdit.setIcon(new ImageIcon(getClass().getResource("/artwork/22x22/edit1.png")));
                    btnEdit.setContentAreaFilled(false);
                    btnEdit.setBorder(null);
                    btnEdit.setPressedIcon(new ImageIcon(getClass().getResource("/artwork/22x22/bw/pressed.png")));
                    btnEdit.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            btnEditActionPerformed(e);
                        }
                    });
                    panel3.add(btnEdit);
                    panel3.add(hSpacer1);

                    //---- btnCancel ----
                    btnCancel.setText(null);
                    btnCancel.setIcon(new ImageIcon(getClass().getResource("/artwork/22x22/cancel.png")));
                    btnCancel.setContentAreaFilled(false);
                    btnCancel.setBorder(null);
                    btnCancel.setPressedIcon(new ImageIcon(getClass().getResource("/artwork/22x22/bw/pressed.png")));
                    btnCancel.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            btnCancelActionPerformed(e);
                        }
                    });
                    panel3.add(btnCancel);

                    //---- btnSave ----
                    btnSave.setText(null);
                    btnSave.setIcon(new ImageIcon(getClass().getResource("/artwork/22x22/apply.png")));
                    btnSave.setContentAreaFilled(false);
                    btnSave.setBorder(null);
                    btnSave.setPressedIcon(new ImageIcon(getClass().getResource("/artwork/22x22/bw/pressed.png")));
                    btnSave.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            btnSaveActionPerformed(e);
                        }
                    });
                    panel3.add(btnSave);
                    panel3.add(hSpacer2);

                    //---- btnEject ----
                    btnEject.setText(null);
                    btnEject.setIcon(new ImageIcon(getClass().getResource("/artwork/22x22/bw/player_eject.png")));
                    btnEject.setContentAreaFilled(false);
                    btnEject.setBorder(null);
                    btnEject.setPressedIcon(new ImageIcon(getClass().getResource("/artwork/22x22/bw/pressed.png")));
                    btnEject.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            btnEjectActionPerformed(e);
                        }
                    });
                    panel3.add(btnEject);
                }
                panel2.add(panel3, CC.xywh(1, 11, 3, 1, CC.DEFAULT, CC.BOTTOM));
            }
            panel1.add(panel2, CC.xywh(5, 3, 1, 5));

            //======== panel4 ========
            {

                //---- lstInterventions ----
                lstInterventions.setFont(new Font("Arial", Font.PLAIN, 14));
                lstInterventions.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        lstInterventionsMouseClicked(e);
                    }
                });
                lstInterventions.addListSelectionListener(new ListSelectionListener() {
                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        lstInterventionsValueChanged(e);
                    }
                });
                panel4.setViewportView(lstInterventions);
            }
            panel1.add(panel4, CC.xy(3, 5, CC.DEFAULT, CC.FILL));

            //---- btnAdd ----
            btnAdd.setText(null);
            btnAdd.setIcon(new ImageIcon(getClass().getResource("/artwork/22x22/bw/add.png")));
            btnAdd.setBorderPainted(false);
            btnAdd.setContentAreaFilled(false);
            btnAdd.setBorder(null);
            btnAdd.setPressedIcon(new ImageIcon(getClass().getResource("/artwork/22x22/bw/pressed.png")));
            panel1.add(btnAdd, CC.xy(3, 7, CC.LEFT, CC.DEFAULT));
        }
        contentPane.add(panel1);
        setSize(735, 495);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panel1;
    private JXSearchField txtSearch;
    private JPanel panel2;
    private JLabel lblText;
    private JTextField txtText;
    private JLabel lblLength;
    private JTextField txtLength;
    private JLabel lblCat;
    private JComboBox cmbCat;
    private JLabel lblType;
    private JComboBox cmbType;
    private JPanel panel3;
    private JButton btnEdit;
    private JPanel hSpacer1;
    private JButton btnCancel;
    private JButton btnSave;
    private JPanel hSpacer2;
    private JButton btnEject;
    private JScrollPane panel4;
    private JList lstInterventions;
    private JButton btnAdd;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}