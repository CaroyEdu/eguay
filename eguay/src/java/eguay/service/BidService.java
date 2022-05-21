/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eguay.service;

import eguay.dao.AuctionFacade;
import eguay.dao.BidFacade;
import eguay.dao.UsersFacade;
import eguay.dto.AuctionDTO;
import eguay.dto.BidDTO;
import eguay.dto.UserDTO;
import eguay.entity.Auction;
import eguay.entity.Bid;
import eguay.entity.Users;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Parsa zendehdel nobari
 */
@Stateless
public class BidService {
@EJB BidFacade bidFacade ; 
@EJB AuctionFacade auctionFacade;
@EJB UsersFacade userFacade ;
@EJB UserService userService;
@EJB AuctionService auctionService;

    public List<BidDTO> getHighestBid(AuctionDTO auction){
     List<Bid> highest = bidFacade.highestBid(auction);
     return BidService.toDTO(highest) ; 
    }
    
    public void createBid( Double BidAmount , AuctionDTO auction , UserDTO user){
        Bid newBid = new Bid(); 
        
        newBid.setAuctionid(auctionFacade.find(auction.getId()));
        newBid.setBid(BidAmount);
        newBid.setBiderid(userFacade.find(user.getId()));
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
