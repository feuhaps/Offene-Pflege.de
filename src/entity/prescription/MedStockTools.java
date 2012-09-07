package entity.prescription;

import entity.info.ResidentTools;
import entity.Stationen;
import op.OPDE;
import op.tools.SYSConst;
import op.tools.SYSTools;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.swing.*;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: tloehr
 * Date: 18.11.11
 * Time: 14:28
 * To change this template use File | Settings | File Templates.
 */
public class MedStockTools {
//    public static boolean apvNeuberechnung = true;

    public static ListCellRenderer getBestandOnlyIDRenderer() {
        return new ListCellRenderer() {
            @Override
            public java.awt.Component getListCellRendererComponent(JList jList, Object o, int i, boolean isSelected, boolean cellHasFocus) {
                String text;
                if (o == null) {
                    text = "<i>Keine Auswahl</i>";//SYSTools.toHTML("<i>Keine Auswahl</i>");
                } else if (o instanceof MedStock) {
                    text = ((MedStock) o).getBestID().toString();
                } else {
                    text = o.toString();
                }
                return new DefaultListCellRenderer().getListCellRendererComponent(jList, text, i, isSelected, cellHasFocus);
            }
        };
    }

//    public static MedBestand findByVerordnungImAnbruch(Verordnung prescription) {
//        EntityManager em = OPDE.createEM();
//        Query query = em.createNamedQuery("MedBestand.findByDarreichungAndBewohnerImAnbruch");
//        query.setParameter("bewohner", prescription.getResident());
//        query.setParameter("darreichung", prescription.getTradeForm());
//
//        MedBestand result = null;
//
//        try {
//            result = (MedBestand) query.getSingleResult();
//        } catch (NoResultException nre) {
//            result = null;
//        } catch (Exception e) {
//            OPDE.fatal(e);
//            System.exit(1);
//        } finally {
//            em.close();
//        }
//
//        return result;
//    }


    /**
     * Sucht aus den den Beständen des Vorrats den angebrochenen heraus.
     *
     * @param inventory
     * @return der angebrochene Bestand. null, wenn es keinen gab.
     */
    public static MedStock getStockInUse(MedInventory inventory) {
        MedStock bestand = null;
        if (inventory != null && inventory.getMedStocks() != null) {
            Iterator<MedStock> itBestand = inventory.getMedStocks().iterator();
            while (itBestand.hasNext()) {
                MedStock b = itBestand.next();
                if (b.isAngebrochen() && !b.isAbgeschlossen()) {
                    bestand = b;
                    break;
                }
            }
        }
        return bestand;
    }

//
//    /**
//     * Bricht einen Bestand an. Berechnet das neue APV selbstständig.
//     *
//     * @param bestand
//     * @return der geänderte Bestand. Direkt persistiert und committed.
//     */
//    public static MedBestand anbrechen(EntityManager em, MedBestand bestand) throws Exception {
//        BigDecimal apv;
//        MedBestand result = null;
//
//        apv = getPassendesAPV(bestand);
//
//        result = anbrechen(em, bestand, apv);
//
//        return result;
//    }

    /**
     * Bricht einen Bestand an.
     *
     * @param bestand
     * @return
     */
    public static void anbrechen(MedStock bestand, BigDecimal apv) throws Exception {
        if (apv == null) {
            throw new NullPointerException("apv darf nicht null sein");
        }

        bestand.setAnbruch(new Date());
        if (apv.equals(BigDecimal.ZERO)) {
            // Das hier verhindert Division by Zero Exceptions.
            apv = BigDecimal.ONE;
        }
        bestand.setApv(apv);
    }

    public static HashMap getBestand4Printing(MedStock bestand) {
        OPDE.debug("BestandID: " + bestand.getBestID());

        HashMap hm = new HashMap();
        hm.put("bestand.darreichung", TradeFormTools.toPrettyString(bestand.getTradeForm()));

        String pzn = bestand.getaPackage().getPzn() == null ? "??" : bestand.getaPackage().getPzn();
        hm.put("bestand.packung.pzn", pzn);
        hm.put("bestand.bestid", bestand.getBestID());
        hm.put("bestand.eingang", bestand.getEin());
        hm.put("bestand.userkurz", bestand.getUser().getUID());
        hm.put("bestand.userlang", bestand.getUser().getFullname());
        hm.put("bestand.vorrat.bewohnername", ResidentTools.getBWLabel1(bestand.getInventory().getResident()));
        hm.put("bestand.vorrat.bewohnergebdatum", bestand.getInventory().getResident().getGebDatum());
        hm.put("bestand.vorrat.bewohnerkennung", bestand.getInventory().getResident().getRID());

        return hm;
    }


//    public static String getBestandText4Print(MedBestand bestand) {
//        String result = "";
//
//        result = SYSPrint.EPL2_CLEAR_IMAGE_BUFFER;
//        result += SYSPrint.EPL2_labelformat(57, 19, 3);
//        result += SYSPrint.EPL2_print_ascii(5, 5, 0, SYSPrint.EPL2_FONT_7pt, 1, 1, false, DarreichungTools.toPrettyString(bestand.getTradeForm())); // bestand.getTradeForm().getMedProdukt().getBezeichnung() + " " + bestand.getTradeForm().getZusatz())
//        if (!SYSTools.catchNull(bestand.getaPackage().getPzn()).equals("")) {
//            result += SYSPrint.EPL2_print_ascii(5, 30, 0, SYSPrint.EPL2_FONT_6pt, 1, 1, false, "PZN:" + bestand.getaPackage().getPzn() + "  Datum:" + DateFormat.getDateInstance().format(bestand.getEin()) + " (" + bestand.getUser().getUID() + ")");
//        }
//
//        result += SYSPrint.EPL2_print_ascii(5, 55, 0, SYSPrint.EPL2_FONT_12pt, 2, 2, true, Long.toString(bestand.getBestID()));
//        result += SYSPrint.EPL2_print_ascii(5, 107, 0, SYSPrint.EPL2_FONT_6pt, 1, 1, false, BewohnerTools.getBWLabel1(bestand.getInventory().getResident()));
//        result += SYSPrint.EPL2_print_ascii(5, 122, 0, SYSPrint.EPL2_FONT_6pt, 1, 1, false, BewohnerTools.getBWLabel2(bestand.getInventory().getResident()));
//
//        result += SYSPrint.EPL2_PRINT;
//
//
//        // Konvertierung auf PC850 weg. Umlauten.
//        try {
//            byte[] conv = result.getBytes("Cp850");
//            result = new String(conv);
//        } catch (UnsupportedEncodingException ex) {
//            //ex.printStackTrace();
//            new DlgException(ex);
//        }
//        return result;
//    }


    public static BigDecimal getBestandSumme(MedStock bestand) {
        BigDecimal result = BigDecimal.ZERO;

        for (MedStockTransaction buchung : bestand.getStockTransaction()) {
            result = result.add(buchung.getMenge());
        }
        return result;
    }

    /**
     * Ermittelt die Menge, die in einer Packung noch enthalten ist.
     *
     * @param stock die entsprechende Packung
     * @return die Summe in der Packungs Einheit.
     */
    public static BigDecimal getBestandSumme(EntityManager em, MedStock stock) throws Exception {
        BigDecimal result;

        Query query = em.createQuery(" " +
                " SELECT SUM(tx.menge) " +
                " FROM MedStock st " +
                " JOIN st.stockTransaction tx " +
                " WHERE st = :stock ");

        try {
            query.setParameter("stock", stock);
            result = (BigDecimal) query.getSingleResult();
        } catch (NoResultException nre) {
            result = BigDecimal.ZERO;
        }

        return result;
    }

    /**
     * Schliesst einen Bestand ab. Erzeugt dazu direkt eine passende Abschlussbuchung, die den Bestand auf null bringt.
     * <p/>
     * <b>tested:</b> Test0002
     *
     * @param bestand, der abzuschliessen ist.
     * @param text,    evtl. gewünschter Text für die Abschlussbuchung
     * @param status,  für die Abschlussbuchung
     * @return Falls die Neuberechung gewünscht war, steht hier das geänderte, bzw. neu erstelle APV Objekt. null andernfalls.
     * @throws Exception
     */
    public static void abschliessen(EntityManager em, MedStock bestand, String text, short status) throws Exception {
        BigDecimal bestandsumme = getBestandSumme(bestand);
        MedStockTransaction abschlussBuchung = new MedStockTransaction(bestand, bestandsumme.negate(), status);
        abschlussBuchung.setText(text);
        bestand.getStockTransaction().add(abschlussBuchung);
        bestand.setAus(new Date());
        bestand.setNaechsterBestand(null);

        if (bestand.hasNextBestand()) {
            MedStockTools.anbrechen(bestand.getNaechsterBestand(), berechneAPV(bestand));
            OPDE.info("Nächste Packung mit Bestands Nr.: " + bestand.getNaechsterBestand().getBestID() + " wird nun angebrochen.");
        } else {

            // es wurde kein nächster angebrochen ?
            // könnte es sein, dass dieser Vorrat keine Packungen mehr hat ?
            if (MedInventoryTools.getNextToOpen(bestand.getInventory()) == null) {
                // Dann prüfen, ob dieser Vorrat zu Verordnungen gehört, die nur bis Packungs Ende laufen sollen
                // Die müssen dann jetzt nämlich abgeschlossen werden.
                for (Prescriptions verordnung : PrescriptionsTools.getPrescriptionsByInventory(bestand.getInventory())) {
                    if (verordnung.isTillEndOfPackage()) {
                        verordnung = em.merge(verordnung);
                        em.lock(verordnung, LockModeType.OPTIMISTIC);
                        verordnung.setTo(new Date());
                        verordnung.setAbgesetztDurch(em.merge(OPDE.getLogin().getUser()));
                        verordnung.setAbArzt(verordnung.getAnArzt());
                        verordnung.setAbKH(verordnung.getAnKH());
                        BHPTools.cleanup(em, verordnung);
                    }
                }
            }
        }
    }

    private static MedStockTransaction getAnfangsBuchung(MedStock bestand) {
        MedStockTransaction result = null;
        for (MedStockTransaction buchung : bestand.getStockTransaction()) {
            if (buchung.getStatus() == MedStockTransactionTools.STATUS_EINBUCHEN_ANFANGSBESTAND) {
                result = buchung;
                break;
            }
        }
        return result;
    }

    /**
     * @param bestand, für den das Verhältnis neu berechnet werden soll.
     */
    public static BigDecimal berechneAPV(MedStock bestand) throws Exception {

        BigDecimal apvNeu = BigDecimal.ONE;

        if (bestand.getTradeForm().getDosageForm().getStatus() != DosageFormTools.APV1) {

            // Menge in der Packung (in der Packungseinheit). Also das, was wirklich in der Packung am Anfang
            // drin war. Meist das, was auf der Packung steht.
            BigDecimal inhaltZuBeginn = getAnfangsBuchung(bestand).getMenge();

            // Das APV, das bei diesem Bestand angenommen wurde.
            BigDecimal apvAlt = bestand.getApv();

            // Zur Verhinderung von Division durch 0
            if (apvAlt.equals(BigDecimal.ZERO)) {
                apvAlt = BigDecimal.ONE;
            }

            BigDecimal summeBHPDosis = getBestandSumme(bestand);

            // Die Gaben aus der BHP sind immer in der Anwendungseinheit. Teilt man diese durch das
            // verwendete APV, erhält man das was rechnerisch in der Packung drin gewesen
            // sein soll. Das kann natürlich von dem realen Inhalt abweichen. Klebt noch was an
            // der Flaschenwand oder wurde was verworfen. Das APV steht ja für Anzahl der Anwendung im
            // Verhaltnis zur Packungseinheit 1. Wurden 100 Tropfen gegeben, bei einem APV von 20(:1)
            // Dann ergibt das einen rechnerischen Flascheninhalt von 5 ml.
            BigDecimal inhaltRechnerisch = summeBHPDosis.divide(apvAlt, 4, BigDecimal.ROUND_UP);
            // Zur Verhinderung von Division durch 0
            if (inhaltRechnerisch.equals(BigDecimal.ZERO)) {
                inhaltRechnerisch = inhaltZuBeginn;
            }

            // Nimmt man den realen Inhalt und teil ihn durch den rechnerischen, dann gibt es drei Möglichkeiten
            // 1. Es wurde mehr gegeben als in der Packung drin war. Dann muss das ursprüngliche APV zu gross gewesen
            // sein. Die Division von realem Inhalt durch rechnerischem Inhalt ist kleiner 1 und somit wird auch
            // das apvNeu kleiner als das apvAlt.
            // 2. Es wurde genau so viel gegeben wie drin war. Dann war das apvAlt genau richtig. Der Divisor ist
            // dann 1 und apvNeu ist gleich apvAlt.
            // 3. Es wurde weniger gegeben als drin war. Dann war apvAlt zu klein und der Divisor (real durch rechnerisch) wird größer 0 und
            // der apvNeu wird größer als der apvAlt.
            apvNeu = inhaltZuBeginn.divide(inhaltRechnerisch, 4, BigDecimal.ROUND_UP).multiply(apvAlt);


            // Zu große APV Abweichungen verhindern. Alle außerhalb eines 20% Korridors wird ignoriert.
            BigDecimal apvkorridor = new BigDecimal(Double.parseDouble(OPDE.getProps().getProperty("apv_korridor"))).divide(BigDecimal.valueOf(100), 4, BigDecimal.ROUND_UP);
            BigDecimal halbeBreite = apvAlt.multiply(apvkorridor);
            BigDecimal korridorUnten = apvAlt.subtract(halbeBreite);
            BigDecimal korridorOben = apvAlt.add(halbeBreite);

            // Liegt der neue apv AUSSERHALB des maximalen Korridors, so wird er verworfen
            if (apvAlt.compareTo(korridorUnten) < 0 || korridorOben.compareTo(apvNeu) < 0) {
                apvNeu = apvAlt;
            }

        }
        return apvNeu;
    }

    /**
     * Diese Methode bucht auf einen Bestand immer genau soviel drauf oder runter, damit er auf
     * dem gewünschten soll landet.
     *
     * @param bestand um die es geht.
     * @param soll    gewünschter Endbestand
     * @param em      EntityManager in dessen Kontext das ganze abläuft
     * @param text    für die Buchung
     * @param status  für die Buchung
     * @return die Korrektur Buchung
     * @throws Exception
     */
    public static MedStockTransaction setzeBestandAuf(EntityManager em, MedStock bestand, BigDecimal soll, String text, short status) throws Exception {
        MedStockTransaction result = null;
        bestand = em.merge(bestand);

        BigDecimal bestandSumme = getBestandSumme(em, bestand);

        if (!bestandSumme.equals(soll)) {
            BigDecimal korrektur;
            if (bestandSumme.compareTo(BigDecimal.ZERO) <= 0) {
                korrektur = bestandSumme.abs().add(soll);
            } else {
                korrektur = bestandSumme.negate().add(soll);
            }

            // passende Buchung anlegen.
            result = em.merge(new MedStockTransaction(bestand, korrektur, status));
            result.setText(text);
        }
        return result;
    }

    public static String getBestandTextAsHTML(MedStock bestand) {
        String result = "";
        result += "<font color=\"blue\"><b>" + bestand.getTradeForm().getMedProdukt().getBezeichnung() + " " + bestand.getTradeForm().getZusatz() + ", ";

        if (!SYSTools.catchNull(bestand.getaPackage().getPzn()).equals("")) {
            result += "PZN: " + bestand.getaPackage().getPzn() + ", ";
            result += MedPackageTools.GROESSE[bestand.getaPackage().getGroesse()] + ", " + bestand.getaPackage().getInhalt() + " " + DosageFormTools.EINHEIT[bestand.getTradeForm().getDosageForm().getPackEinheit()] + " ";
            String zubereitung = SYSTools.catchNull(bestand.getTradeForm().getDosageForm().getZubereitung());
            String anwtext = SYSTools.catchNull(bestand.getTradeForm().getDosageForm().getAnwText());
            result += zubereitung.equals("") ? anwtext : (anwtext.equals("") ? zubereitung : zubereitung + ", " + anwtext);
            result += "</b></font>";
        }

        return result;
    }

    public static String getBestandAsHTML(MedStock bestand) {
        String result = "";

        String htmlcolor = bestand.isAbgeschlossen() ? "gray" : "red";

        result += "<font face =\"" + SYSConst.ARIAL14.getFamily() + "\">";
        result += "<font color=\"" + htmlcolor + "\"><b><u>" + bestand.getBestID() + "</u></b></font>&nbsp; ";
        result += TradeFormTools.toPrettyString(bestand.getTradeForm());

        if (bestand.getaPackage() != null) {
            result += ", " + MedPackageTools.toPrettyString(bestand.getaPackage());
        }

        result += ", APV: " + bestand.getApv().setScale(2, BigDecimal.ROUND_HALF_UP);

        if (bestand.hasNextBestand()) {
            result += ", <b>nächster Bestand: " + bestand.getNaechsterBestand().getBestID() + "</b>";
        }

        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);

        result += "<br/><font color=\"blue\">Eingang: " + df.format(bestand.getEin()) + "</font>";
        if (bestand.isAngebrochen()) {
            result += "<br/><font color=\"green\">Anbruch: " + df.format(bestand.getAnbruch()) + "</font>";
            if (bestand.isAbgeschlossen()) {
                result += "<br/><font color=\"black\">Ausgebucht: " + df.format(bestand.getAus()) + "</font>";
            }
        }
        result += "</font>";

        return result;

    }

//    /**
//     * Setzt für einen Bestand <b>alle</b> Buchungen zurück, bis auf die Anfangsbuchung.
//     *
//     * @param bestand
//     */
//    public static void zuruecksetzen(MedBestand bestand) {
//        EntityManager em = OPDE.createEM();
//        try {
//            em.getTransaction().begin();
//            bestand = em.merge(bestand);
//            em.lock(bestand, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
//            Query query = em.createQuery("DELETE FROM MedStockTransaction b WHERE b.bestand = :bestand AND b.status <> :notStatus ");
//            query.setParameter("bestand", bestand);
//            query.setParameter("notStatus", MedStockTransactionTools.STATUS_EINBUCHEN_ANFANGSBESTAND);
//            query.executeUpdate();
//            em.getTransaction().commit();
//        } catch (OptimisticLockException ole) {
//            OPDE.getDisplayManager().addSubMessage(new DisplayMessage("Wurde zwischenzeitlich von jemand anderem geändert.", DisplayMessage.IMMEDIATELY, OPDE.getErrorMessageTime()));
//            em.getTransaction().rollback();
//        } catch (Exception ex) {
//            if (em.getTransaction().isActive()) {
//                em.getTransaction().rollback();
//            }
//            OPDE.fatal(ex);
//        } finally {
//            em.close();
//        }
//    }

//    public static boolean hasAbgesetzteBestaende(BHP bhp) {
//        boolean result = false;
//
//        EntityManager em = OPDE.createEM();
//
//        try {
//            Query query = em.createQuery(" " +
//                    " SELECT b FROM MedBestand b " +
//                    " JOIN b.buchungen bu " +
//                    " WHERE bu.bhp = :bhp " +
//                    " AND b.aus < '9999-12-31 23:59:59'");
//            query.setParameter("bhp", bhp);
//            result = !query.getResultList().isEmpty();
//        } catch (Exception ex) {
//            OPDE.fatal(ex);
//        } finally {
//            em.close();
//        }
//        return result;
//    }

    /**
     * Ermittelt für einen bestimmten Bestand ein passendes APV.
     */
    public static BigDecimal getPassendesAPV(MedStock bestand) {

        BigDecimal apv = BigDecimal.ONE;

        if (bestand.getTradeForm().getDosageForm().getStatus() == DosageFormTools.APV_PER_BW) {
            apv = getAPVperBW(bestand.getInventory());
        } else if (bestand.getTradeForm().getDosageForm().getStatus() == DosageFormTools.APV_PER_DAF) {
            apv = getAPVperDAF(bestand.getTradeForm());
        }

        return apv;
    }

    public static BigDecimal getAPVperBW(MedInventory inventory) {
        BigDecimal apv;

        if (!inventory.getMedStocks().isEmpty()) {
            apv = BigDecimal.ZERO;
            for (MedStock bestand : inventory.getMedStocks()) {
                apv.add(bestand.getApv());
            }
            // Arithmetisches Mittel
            apv = apv.divide(new BigDecimal(inventory.getMedStocks().size()), BigDecimal.ROUND_UP);
        } else {
            apv = BigDecimal.ONE;
        }

//
//        EntityManager em = OPDE.createEM();
//        BigDecimal result = null;
//
//        try {
//            Query query = em.createQuery("SELECT AVG(b.apv) FROM MedBestand b WHERE b.inventory = :inventory");
//            query.setParameter("inventory", inventory);
//            result = (BigDecimal) query.getSingleResult();
//        } catch (NoResultException nre) {
//            result = BigDecimal.ONE; // Im Zweifel ist das 1
//        } catch (Exception e) {
//            OPDE.fatal(e);
//        } finally {
//            em.close();
//        }
        return apv;
    }

    public static BigDecimal getAPVperDAF(TradeForm darreichung) {

        BigDecimal apv = null;

        if (!darreichung.getMedStocks().isEmpty()) {
            apv = BigDecimal.ZERO;
            for (MedStock bestand : darreichung.getMedStocks()) {
                apv.add(bestand.getApv());
            }
            // Arithmetisches Mittel
            apv = apv.divide(new BigDecimal(darreichung.getMedStocks().size()), BigDecimal.ROUND_UP);
        } else {
            // Im Zweifel ist das 1
            apv = BigDecimal.ONE;
        }

//        EntityManager em = OPDE.createEM();
//        BigDecimal result = null;
//
//
//
//        try {
//            Query query = em.createQuery("SELECT AVG(b.apv) FROM MedBestand b WHERE b.darreichung = :darreichung");
//            query.setParameter("darreichung", darreichung);
//            result = (BigDecimal) query.getSingleResult();
//        } catch (NoResultException nre) {
//            result = BigDecimal.ONE; // Im Zweifel ist das 1
//        } catch (Exception e) {
//            OPDE.fatal(e);
//        } finally {
//            em.close();
//        }
        return apv;
    }

    public static String getMediKontrolle(EntityManager em, Stationen station, int headertiefe) {
        StringBuilder html = new StringBuilder(1000);

        String jpql = " " +
                " SELECT b FROM MedStock b " +
                " WHERE b.inventory.resident.station = :station AND b.inventory.resident.adminonly <> 2 " +
                " AND b.anbruch < :anbruch AND b.aus = " + SYSConst.MYSQL_DATETIME_BIS_AUF_WEITERES +
                " ORDER BY b.inventory.resident.nachname, b.inventory.resident.vorname, b.inventory.text, b.anbruch ";

        Query query = em.createQuery(jpql);
        query.setParameter("anbruch", new Date());
        query.setParameter("station", station);
        List<MedStock> list = query.getResultList();

        DateFormat df = DateFormat.getDateInstance();
        if (!list.isEmpty())
            html.append("<h" + headertiefe + ">");
        html.append("Liste zur Medikamentenkontrolle");
        html.append("</h" + headertiefe + ">");
        html.append("<h" + (headertiefe + 1) + ">");
        html.append("Legende");
        html.append("</h" + (headertiefe + 1) + ">");
        html.append("#1 - Medikament abgelaufen<br/>");
        html.append("#2 - Packung nicht beschriftet<br/>");
        html.append("#3 - Packung beschädigt<br/>");
        html.append("#4 - Anbruchsdatum nicht vermerkt<br/>");
        html.append("#5 - Medikament ist nicht verordnet<br/>");
        html.append("#6 - Mehr als 1 Blister im Anbruch<br/>");
        html.append("#7 - Mehr als 1 Tablette geteilt<br/>");
        html.append("#8 - falsche Bestandsnummer im Anbruch<br/><br/>");

        html.append("<table border=\"1\"><tr>" +
                "<th>BewohnerIn</th><th>BestNr</th><th>Präparat</th><th>Anbruch</th><th>#1</th><th>#2</th><th>#3</th><th>#4</th><th>#5</th><th>#6</th><th>#7</th><th>#8</th></tr>");

        for (MedStock bestand : list) {
            html.append("<tr>");
            html.append("<td>" + ResidentTools.getBWLabelTextKompakt(bestand.getInventory().getResident()) + "</td>");
            html.append("<td>" + bestand.getBestID() + "</td>");
            html.append("<td>" + TradeFormTools.toPrettyString(bestand.getTradeForm()) + "</td>");
            html.append("<td>" + df.format(bestand.getAnbruch()) + "</td>");
            html.append("<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>");
            html.append("</tr>");
        }
        html.append("</table>");
        return html.toString();
    }
}
