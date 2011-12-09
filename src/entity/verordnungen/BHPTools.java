package entity.verordnungen;

import op.OPDE;
import op.tools.DlgException;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: tloehr
 * Date: 01.12.11
 * Time: 15:49
 * To change this template use File | Settings | File Templates.
 */
public class BHPTools {

    public static final int STATUS_OFFEN = 0;
    public static final int STATUS_ERLEDIGT = 1;
    public static final int STATUS_VERWEIGERT = 2;
    public static final int STATUS_VERWEIGERT_VERWORFEN = 3;

    public static long getNumBHPs(Verordnung verordnung) {
        EntityManager em = OPDE.createEM();
        Query query = em.createNamedQuery("BHP.numByNOTStatusAndVerordnung");
        query.setParameter("verordnung", verordnung);
        query.setParameter("status", STATUS_OFFEN);

        return (Long) query.getSingleResult();
    }


}
