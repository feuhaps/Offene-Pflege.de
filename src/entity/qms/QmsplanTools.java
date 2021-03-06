package entity.qms;

import entity.system.Commontags;
import entity.system.CommontagsTools;
import op.OPDE;
import op.tools.SYSConst;
import op.tools.SYSTools;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;

/**
 * Created by tloehr on 17.06.14.
 */
public class QmsplanTools {

    public static final byte STATE_ACTIVE = 0;
    public static final byte STATE_INACTIVE = 1;
    public static final byte STATE_ARCHIVE = 2;

    /**
     * returns a list with all active Qmsplan
     *
     * @return
     */
    public static ArrayList<Qmsplan> getAllActive() {
        EntityManager em = OPDE.createEM();
        ArrayList<Qmsplan> list = null;

        try {

            String jpql = " SELECT q " +
                    " FROM Qmsplan q" +
                    " WHERE q.state = :state " +
                    " ORDER BY q.title DESC ";

            Query query = em.createQuery(jpql);
            query.setParameter("state", QmsplanTools.STATE_ACTIVE);
            list = new ArrayList<Qmsplan>(query.getResultList());

        } catch (Exception se) {
            OPDE.fatal(se);
        } finally {
            em.close();
        }
        return list;
    }


    public static ArrayList<Qmsplan> getAll(Commontags commontag) {
        EntityManager em = OPDE.createEM();
        ArrayList<Qmsplan> list = null;

        try {
            String jpql = " SELECT q " +
                    " FROM Qmsplan q " +
                    " WHERE q.state = :state " +
                    " AND :commontag MEMBER OF q.commontags " +
                    " ORDER BY q.title DESC ";

            Query query = em.createQuery(jpql);
            query.setParameter("commontag", commontag);

            list = new ArrayList<Qmsplan>(query.getResultList());
        } catch (Exception se) {
            OPDE.fatal(se);
        } finally {
            em.close();
        }
        return list;
    }


    public static ArrayList<Qmsplan> getAll() {
        EntityManager em = OPDE.createEM();
        ArrayList<Qmsplan> list = null;
        try {

            String jpql = " SELECT q " +
                    " FROM Qmsplan q" +
                    " ORDER BY q.title DESC ";

            Query query = em.createQuery(jpql);
            list = new ArrayList<Qmsplan>(query.getResultList());

        } catch (Exception se) {
            OPDE.fatal(se);
        } finally {
            em.close();
        }
        return list;
    }

    /**
     * returns a HTML representation of the given Qmsplan.
     *
     * @param qmsplan
     * @return
     */
    public static String getAsHTML(Qmsplan qmsplan) {
        String html = "";


//        DateFormat df = DateFormat.getDateInstance();


//        html += SYSConst.html_bold("misc.msg.createdby") + ": " + qmsplan.getUserON().getFullname() + " ";
//        html += SYSConst.html_bold("misc.msg.atchrono") + ": " + df.format(qmsplan.getFrom());
//        if (qmsplan.isClosed()) {
//            html += "<br/>";
//            html += SYSConst.html_bold("misc.msg.closedBy") + ": " + qmsplan.getUserOFF().getFullname() + " ";
//            html += SYSConst.html_bold("misc.msg.atchrono") + ": " + df.format(qmsplan.getTo());
//        }

        html += !SYSTools.catchNull(qmsplan.getDescription()).isEmpty() ? SYSConst.html_h3("misc.msg.description") +
                SYSConst.html_paragraph(SYSTools.replace(qmsplan.getDescription(), "\n", "<br/>", false)) : "";

        html += qmsplan.getCommontags().isEmpty() ? "" : CommontagsTools.getAsHTML(qmsplan.getCommontags(), SYSConst.html_16x16_tagPurple_internal);


//        html += SYSConst.html_h3("misc.msg.measures");
//
//        if (qmsplan.getQmsschedules().isEmpty()) {
//            html += "<ul><li><b>" + SYSTools.xx("misc.msg.MissingInterventions") + " !!!</b></li></ul>";
//        }
//         else {
//            html += "<ul>";
//            for (Qmssched qmssched : qmsplan.getQmsschedules()) {
//                html += "<li>";
//                html += SYSConst.html_div(SYSConst.html_bold(qmssched.getMeasure()));
//                html += QmsschedTools.getAsHTML(qmssched);
//                html += "</li>";
//            }
//            html += "</ul>";
//        }

        //        if (np.getFlag() > 0) {
        //            html += "<br/><b>" + SYSTools.xx("nursingrecords.nursingprocess.dlgplanung.lblFlag") + ":</b> " + FLAGS[np.getFlag()];
        //        }


//        html += "</div>";
        return SYSConst.html_color(qmsplan.getColor(), html);
    }

}
