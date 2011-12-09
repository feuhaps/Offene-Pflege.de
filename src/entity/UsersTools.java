/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import op.OPDE;
import op.tools.SYSTools;

/**
 *
 * @author tloehr
 */
public class UsersTools {

    public static boolean isAdmin(Users user) {
        EntityManager em = OPDE.createEM();
        Query query = em.createNamedQuery("Groups.findByUserAndAdmin");
        query.setParameter("user", user);
        boolean admin = query.getResultList().size() > 0;
        OPDE.getLogger().debug("Benutzer ist " + (admin ? "" : "kein") + " Admin");
        return admin;
    }

    public static boolean isExamen(Users user) {
        EntityManager em = OPDE.createEM();
        Query query = em.createNamedQuery("Groups.findByUserAndExamen");
        query.setParameter("user", user);
        boolean examen = query.getResultList().size() > 0;
        OPDE.getLogger().debug("Benutzer ist " + (examen ? "" : "kein") + " Admin");
        return examen;
    }

    public static Users checkPassword(String username, String password) {
        EntityManager em = OPDE.createEM();
        Query query = em.createNamedQuery("Users.findForLogin");
        query.setParameter("uKennung", username);
        query.setParameter("md5pw", SYSTools.hashword(password));
        Users user = null;
        try {
            user = (Users) query.getSingleResult();
        } catch (Exception e) {
            OPDE.info(e);
        }
        return user;
    }
}
