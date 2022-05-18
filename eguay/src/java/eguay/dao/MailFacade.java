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
 * @author Pedro Antonio Benito Rojano
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
        List<Mail> mails = findAllMailsToUserThroughtGroup(userId);
        mails.addAll(findAllMailsToDirectlyToUser(userId));
        return mails;
    }
    
    public List<Mail> findAllMailsToUserThroughtGroup(Integer userId){
        return this.em.createQuery("SELECT m FROM Mail m JOIN m.groupsList g JOIN g.usersList u WHERE :userId IN (u.userid)")
                .setParameter("userId", userId)
                .getResultList();
    }
    
    public List<Mail> findAllMailsToDirectlyToUser(Integer userId){
        return this.em.createQuery("SELECT m FROM Mail m JOIN m.usersList u WHERE :userId IN (u.userid)")
                .setParameter("userId", userId)
                .getResultList();
    }
}
