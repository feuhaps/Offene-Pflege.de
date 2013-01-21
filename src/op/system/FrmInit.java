/*
 * Created by JFormDesigner on Tue Nov 08 12:46:11 CET 2011
 */

package op.system;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import entity.system.SYSProps;
import op.OPDE;
import op.tools.DesEncrypter;
import op.tools.SortedProperties;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.UUID;

/**
 *
 * Diese Klasse erzeugt einen Frame, der immer dann aufgerufen wird, wenn die Datei opde.cfg nicht gefunden wird.
 * In dieser Datei stehen eine ganze Reihe von Konfigurationsinformationen, besonders die Datenbank Verbindungsinformationen.
 *
 * Dieses Formular fragt die Grunddaten ab, testet die Datenbankverbindung und erzeugt die opde.cfg inkl. der notwendigen
 * Verzeichnisse. Dass Datenbank Benutzer Passwort wird mittels des erzeugten, eindeutigen Hostkeys verschlüsselt gespeichert.
 *
 * Gleichzeitig erstellt die Speicheroutine einen neuen Eintrag in der Tabelle Syshosts für den aktuellen Client.
 *
 * @author Torsten Löhr
 */
public class FrmInit extends JFrame {

    private SortedProperties defaultProps;
    private EntityManager em;

    public FrmInit() {
        initComponents();
        setSize(660, 390);
        txtServer.requestFocus();
    }

    private void btnCheckDBActionPerformed(ActionEvent e) {
        String server = txtServer.getText().trim();
        String user = txtUser.getText().trim();
        String pw = new String(txtPW.getPassword());
        String port = txtPort.getText().trim();
        String catalog = txtCatalog.getText().trim();


        defaultProps = OPDE.getAppInfo().getDefaultProperties();
        defaultProps.put("javax.persistence.jdbc.user", user);
        defaultProps.put("javax.persistence.jdbc.password", pw);

        String url = "jdbc:mysql://" + server + ":" + port + "/" + catalog;
        defaultProps.put("javax.persistence.jdbc.url", url);

        try {
            em = Persistence.createEntityManagerFactory("OPDEPU", defaultProps).createEntityManager();

            if (schemaKorrekt()) {
                lblTest.setIcon(new ImageIcon(getClass().getResource("/artwork/16x16/apply.png")));
                txtTest.setText("Verbindung erfolgreich hergestellt");
                btnSave.setEnabled(true);
                btnCheckDB.setEnabled(false);
                txtServer.setEnabled(false);
                txtUser.setEnabled(false);
                txtPW.setEnabled(false);
                txtPort.setEnabled(false);
                txtCatalog.setEnabled(false);
            } else {
                lblTest.setIcon(new ImageIcon(getClass().getResource("/artwork/16x16/cancel.png")));
                txtTest.setText("Datenbank Schema passt nicht zur Programm-Version");
            }

        } catch (Exception e1) {
            txtTest.setText(e1.getMessage());
            lblTest.setIcon(new ImageIcon(getClass().getResource("/artwork/16x16/cancel.png")));
        }
    }

    /**
     * Ermitelt das aktuelle Datenbankschema, das in der Tabelle SYSProps unter dem Schlüssel DBSCHEMA gespeichert ist
     * und prüft, ob dieses Schema (dargestellt durch einen Integer) sich in der Liste der unterstützten Schema dieser
     * Programmversion befindet. Diese Liste wird anhand der appinfo.xml aufgebaut.
     *
     * @return ja oder nein
     */
    private boolean schemaKorrekt() {
        Query query = em.createQuery("SELECT s FROM SYSProps s WHERE s.key = :key");
        query.setParameter("key", "dbstructure");
        SYSProps dbschema = (SYSProps) query.getSingleResult();

        return OPDE.getAppInfo().getDBschema().contains(Integer.parseInt(dbschema.getValue()));
    }

    private void btnSaveActionPerformed(ActionEvent e) {
        // Legt bei Bedarf die nötigen Arbeitsverzeichnisse an
        if (!new File(OPDE.getOPWD()).exists()) {
            new File(OPDE.getOPWD()).mkdir();
        }
        if (!new File(OPDE.getOPCache()).exists()) {
            new File(OPDE.getOPCache()).mkdir();
        }


        saveLocalProps();
        dispose();
    }

    private void saveLocalProps() {
        try {

            String hostkey = UUID.randomUUID().toString();
            defaultProps.put("hostkey", hostkey);

            // Passwort verschlüsselt speichern
            DesEncrypter desEncrypter = new DesEncrypter(hostkey);

            String clearpw = defaultProps.getProperty("javax.persistence.jdbc.password");
            String cryptpw = desEncrypter.encrypt(clearpw);

            defaultProps.put("javax.persistence.jdbc.password", cryptpw);

            FileOutputStream out = new FileOutputStream(new File(OPDE.getOPWD() + System.getProperty("file.separator") + AppInfo.fileConfig));
            defaultProps.store(out, "Lokale Einstellungen für Offene-Pflege.de");
            out.close();

            em.close();

        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }


    private void btnCancelActionPerformed(ActionEvent e) {
        dispose();
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        lblMessage = new JLabel();
        panel1 = new JPanel();
        label4 = new JLabel();
        txtServer = new JTextField();
        label2 = new JLabel();
        txtUser = new JTextField();
        label3 = new JLabel();
        txtPW = new JPasswordField();
        label5 = new JLabel();
        txtPort = new JTextField();
        label7 = new JLabel();
        txtCatalog = new JTextField();
        panel4 = new JPanel();
        btnCheckDB = new JButton();
        lblTest = new JLabel();
        scrollPane1 = new JScrollPane();
        txtTest = new JTextArea();
        panel2 = new JPanel();
        btnSave = new JButton();
        btnCancel = new JButton();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Container contentPane = getContentPane();
        contentPane.setLayout(new FormLayout(
            "$rgap, $lcgap, 250dlu:grow, $lcgap, $rgap",
            "2*(default, $lgap), fill:default:grow, $lgap, default, $lgap, $rgap"));

        //---- label1 ----
        label1.setText("Client Setup");
        label1.setFont(new Font("Lucida Grande", Font.BOLD, 18));
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(label1, CC.xywh(1, 1, 5, 1));

        //---- lblMessage ----
        lblMessage.setText("Erstmalige Einrichtung von OPDE");
        lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblMessage, CC.xywh(1, 3, 5, 1));

        //======== panel1 ========
        {
            panel1.setBorder(new TitledBorder("Datenbank"));
            panel1.setBackground(new Color(238, 238, 238));
            panel1.setLayout(new FormLayout(
                "default, $lcgap, default:grow, $lcgap, $rgap",
                "4*(default, $lgap), default, $ugap, fill:default:grow"));

            //---- label4 ----
            label4.setText("Server");
            label4.setHorizontalAlignment(SwingConstants.RIGHT);
            panel1.add(label4, CC.xy(1, 1));
            panel1.add(txtServer, CC.xy(3, 1));

            //---- label2 ----
            label2.setText("Benutzer");
            label2.setHorizontalAlignment(SwingConstants.RIGHT);
            panel1.add(label2, CC.xy(1, 3));
            panel1.add(txtUser, CC.xy(3, 3));

            //---- label3 ----
            label3.setText("Passwort");
            label3.setHorizontalAlignment(SwingConstants.RIGHT);
            panel1.add(label3, CC.xy(1, 5));
            panel1.add(txtPW, CC.xy(3, 5));

            //---- label5 ----
            label5.setText("Port");
            label5.setHorizontalAlignment(SwingConstants.RIGHT);
            panel1.add(label5, CC.xy(1, 7));

            //---- txtPort ----
            txtPort.setText("3306");
            panel1.add(txtPort, CC.xy(3, 7));

            //---- label7 ----
            label7.setText("Katalog");
            label7.setHorizontalAlignment(SwingConstants.RIGHT);
            panel1.add(label7, CC.xy(1, 9));

            //---- txtCatalog ----
            txtCatalog.setText("opde");
            panel1.add(txtCatalog, CC.xy(3, 9));

            //======== panel4 ========
            {
                panel4.setBackground(new Color(238, 238, 238));
                panel4.setLayout(new FormLayout(
                    "default, $lcgap, default",
                    "default"));

                //---- btnCheckDB ----
                btnCheckDB.setText("Verbindungstest");
                btnCheckDB.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        btnCheckDBActionPerformed(e);
                    }
                });
                panel4.add(btnCheckDB, CC.xy(1, 1, CC.LEFT, CC.TOP));

                //---- lblTest ----
                lblTest.setIcon(new ImageIcon(getClass().getResource("/artwork/16x16/cancel.png")));
                panel4.add(lblTest, CC.xy(3, 1));
            }
            panel1.add(panel4, CC.xy(1, 11));

            //======== scrollPane1 ========
            {

                //---- txtTest ----
                txtTest.setLineWrap(true);
                txtTest.setBackground(UIManager.getColor("Button.background"));
                txtTest.setEditable(false);
                txtTest.setWrapStyleWord(true);
                scrollPane1.setViewportView(txtTest);
            }
            panel1.add(scrollPane1, new CellConstraints(3, 11, 1, 1, CC.DEFAULT, CC.DEFAULT, new Insets(5, 5, 5, 5)));
        }
        contentPane.add(panel1, CC.xy(3, 5, CC.FILL, CC.FILL));

        //======== panel2 ========
        {
            panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));

            //---- btnSave ----
            btnSave.setText("\u00dcbernehmen");
            btnSave.setEnabled(false);
            btnSave.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    btnSaveActionPerformed(e);
                }
            });
            panel2.add(btnSave);

            //---- btnCancel ----
            btnCancel.setText("Abbrechen");
            btnCancel.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    btnCancelActionPerformed(e);
                }
            });
            panel2.add(btnCancel);
        }
        contentPane.add(panel2, CC.xy(3, 7, CC.RIGHT, CC.DEFAULT));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JLabel lblMessage;
    private JPanel panel1;
    private JLabel label4;
    private JTextField txtServer;
    private JLabel label2;
    private JTextField txtUser;
    private JLabel label3;
    private JPasswordField txtPW;
    private JLabel label5;
    private JTextField txtPort;
    private JLabel label7;
    private JTextField txtCatalog;
    private JPanel panel4;
    private JButton btnCheckDB;
    private JLabel lblTest;
    private JScrollPane scrollPane1;
    private JTextArea txtTest;
    private JPanel panel2;
    private JButton btnSave;
    private JButton btnCancel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
