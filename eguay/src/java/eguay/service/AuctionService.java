/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eguay.service;

import eguay.dao.AuctionFacade;
import eguay.entity.Auction;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author parsa
 */
@Stateless
public class AuctionService {
@EJB AuctionFacade auctionFacade;

public Auction findById(Long id){
    Auction auction = auctionFacade.find(id);
    
    return auction;
}

public List<Auction> filterAuction(String filter){
    List<Auction> auctions ;
    if(filter == null || filter.isEmpty())
        {
            auctions = this.auctionFacade.findAll();
        }
        else
        {
            auctions = this.auctionFacade.findByTitle(filter);
        }
    return auctions;
}

public void editAuction(Auction auction){
    auctionFacade.edit(auction);
}


    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
