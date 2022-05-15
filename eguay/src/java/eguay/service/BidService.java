/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eguay.service;

import eguay.dao.BidFacade;
import eguay.dto.AuctionDTO;
import eguay.dto.BidDTO;
import eguay.entity.Auction;
import eguay.entity.Bid;
import eguay.entity.Users;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author parsa
 */
@Stateless
public class BidService {
@EJB BidFacade bidFacade ; 
    public List<BidDTO> getHighestBid(Auction auction){
     List<Bid> highest = bidFacade.highestBid(auction);
     return BidService.toDTO(highest) ; 
    }
    
    public void createBid(Bid newBid , Double BidAmount , Auction auction , Users user){
                
        newBid.setAuctionid(auction);
        newBid.setBid(BidAmount);
        newBid.setBiderid(user);
        bidFacade.create(newBid);        
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    // Logic
    public static List<BidDTO> toDTO(List<Bid> bids){
        List<BidDTO> dtos = new ArrayList<>(bids.size());
        
        for(Bid bid : bids){
            dtos.add(bid.toDTO());
        }
        
        return dtos;
    }
}
