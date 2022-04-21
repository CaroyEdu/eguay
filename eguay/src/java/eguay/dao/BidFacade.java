/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eguay.dao;

import eguay.entity.Auction;
import eguay.entity.Bid;
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
public class BidFacade extends AbstractFacade<Bid> {

    @PersistenceContext(unitName = "eguayPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BidFacade() {
        super(Bid.class);
    }
    
     public List<Bid> highestBid(Auction auction)
    {
        Query q;
        q = this.em.createQuery("SELECT bi FROM Bid bi where bi.bid = ( SELECT MAX(bii.bid) from Bid bii WHERE bii.auctionid = :id)");
        q.setParameter("id",auction );
        List<Bid> results = (List<Bid>) q.getResultList();
        return results; 
    }
}
