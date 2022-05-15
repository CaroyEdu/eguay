/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eguay.dao;

import eguay.entity.Auction;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author jean-
 */
@Stateless
public class AuctionFacade extends AbstractFacade<Auction> {

    @PersistenceContext(unitName = "eguayPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AuctionFacade() {
        super(Auction.class);
    }
    
    public List<Auction> getAllOrdered() {
        return this.em.createQuery("SELECT a FROM Auction a ORDER BY a.auctionid").getResultList();
    }
    
    public List<Auction> filter(String title) {
        return this.em.createQuery("SELECT a FROM Auction a WHERE a.title LIKE :title ORDER BY a.auctionid")
                .setParameter("title", "%" + title + "%")
                .getResultList();
    }
    
    public List<Auction> findByTitle(String title)
    {
        Query q;
        q = this.em.createQuery("SELECT c FROM Auction c WHERE c.title like :title");
        q.setParameter("title", '%' + title + '%');
        return q.getResultList();
    }
    
    public List<Auction> findByTitleAndUser(String title, int userid)
    {
        Query q;
        q = this.em.createQuery("SELECT c FROM Auction c WHERE c.sellerid.userid = :userid AND c.title LIKE :title");
        q.setParameter("title", '%' + title + '%');
        q.setParameter("userid", userid);
        return q.getResultList();
    }
    
    public List<Auction> findOrderedByUser(int userid)
    {
        Query q;
        q = this.em.createQuery("SELECT c FROM Auction c WHERE c.sellerid.userid = :userid ORDER BY c.startdate DESC");
        q.setParameter("userid", userid);
        return q.getResultList();
    }
    
    public List<Auction> findByActive()
    {
        Query q;
        q = this.em.createQuery("SELECT c FROM Auction c WHERE c.active = TRUE");
        return q.getResultList();
    }
}
