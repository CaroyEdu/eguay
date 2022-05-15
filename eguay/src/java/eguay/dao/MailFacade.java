/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eguay.dao;

import eguay.entity.Mail;
import eguay.entity.Users;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jean-
 */
@Stateless
public class MailFacade extends AbstractFacade<Mail> {

    @PersistenceContext(unitName = "eguayPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MailFacade() {
        super(Mail.class);
    }
    
    public List<Mail> findAllMailsToUser(Integer userId){
        return this.em.createQuery("SELECT m FROM Users u, u.groupsList g, g.mailList m WHERE u.userid = :userId")
                .setParameter("userId", userId)
                .getResultList();
    }
}
