package entity.planung;

import entity.Bewohner;
import entity.info.BWInfoKat;
import op.OPDE;
import op.care.planung.PnlPlanung;
import op.tools.SYSTools;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.text.DateFormat;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: tloehr
 * Date: 19.07.12
 * Time: 15:50
 * To change this template use File | Settings | File Templates.
 */
public class PlanungTools {


    public static List<Planung> findByKategorieAndBewohner(Bewohner bewohner, BWInfoKat kat) {
        EntityManager em = OPDE.createEM();
        Query query = em.createQuery("SELECT p FROM Planung p WHERE p.bewohner = :bewohner AND p.kategorie = :kat ORDER BY p.stichwort, p.von");
        query.setParameter("kat", kat);
        query.setParameter("bewohner", bewohner);
        List<Planung> planungen = query.getResultList();
        em.close();
        return planungen;
    }


    /**
     * Gibt einen String zurück, der eine HTML Darstellung einer Pflegeplanung enthält.
     *
     * @param planung
     * @return
     */
    public static String getAsHTML(Planung planung, boolean withHeader) {

        String html = "";
        html += "<h2 id=\"fonth2\" >";
        html += withHeader ? "Pflegeplanung &raquo;" + planung.getStichwort() + "&laquo;" : "";
        html += "</h2>";

        html += "<div id=\"fonttext\">";

        html += withHeader ? "<b>Kategorie:</b> " + planung.getKategorie().getBezeichnung() + "<br/>" : "";

        DateFormat df = DateFormat.getDateInstance();
        html += "<b>Prüfungstermin:</b> " + df.format(planung.getNKontrolle()) + "<br/>";
        html += "<b>Erstellt von:</b> " + planung.getAngesetztDurch().getNameUndVorname() + "  ";
        html += "<b>Am:</b> " + df.format(planung.getVon()) + "<br/>";
        if (planung.isAbgesetzt()) {
            html += "<b>Abgesetzt von:</b> " + planung.getAbgesetztDurch().getNameUndVorname() + "  ";
            html += "<b>Am:</b> " + df.format(planung.getBis()) + "<br/>";
        }

        html += "<h3 id=\"fonth3\">Situation</h3>" + SYSTools.replace(planung.getSituation(), "\n", "<br/>");
        html += "<h3 id=\"fonth3\">Ziel(e):</h3>" + SYSTools.replace(planung.getZiel(), "\n", "<br/>");

        html += "<h3 id=\"fonth3\">"+OPDE.lang.getString(PnlPlanung.internalClassID+".interventions")+"</h3>";

        if (planung.getMassnahmen().isEmpty()) {
            html += "<ul><li><b>Massnahmen fehlen !!!</b></li></ul>";
        } else {
            html += "<ul>";
            html += "<li><b>" + OPDE.lang.getString(PnlPlanung.internalClassID + ".interventions") + "</b></li><ul>";
            for (MassTermin massTermin : planung.getMassnahmen()) {
                html += "<li>";
                html += "";
                html = "<div id=\"fonttext\"><b>" + massTermin.getMassnahme().getBezeichnung() + "</b> (" + massTermin.getDauer().toPlainString() + " " + OPDE.lang.getString("misc.msg.Minutes") + ")</div><br/>";
                html += MassTerminTools.getTerminAsHTML(massTermin);
                html += "";
                html += "</li>";
            }
            html += "</ul>";
        }


        if (!planung.getKontrollen().isEmpty()) {
            html += "<h3 id=\"fonth3\">Kontrolltermine</h3>";
            html += "<ul>";
            for (PlanKontrolle kontrolle : planung.getKontrollen()) {
                html += "<li><div id=\"fonttext\">" + PlanKontrolleTools.getAsHTML(kontrolle) + "</div></li>";
            }
            html += "</ul>";
        }

        html += "</ul></ul></div>";
        return html;
    }
}