/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eguay.service;

import eguay.dao.AuctionFacade;
import eguay.dto.AuctionDTO;
import eguay.entity.Auction;
import java.util.ArrayList;
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
    
    // Query
    public AuctionDTO findById(Long id){
        Auction auction = auctionFacade.find(id);

        return auction.toDTO();
    }

    // Auxiliary functions
    public List<AuctionDTO> filterAuction(String filter){
        List<Auction> auctions ;
        if(filter == null || filter.isEmpty())
            {
                auctions = this.auctionFacade.findAll();
            }
            else
            {
                auctions = this.auctionFacade.findByTitle(filter);
            }
        return AuctionService.toDTO(auctions);
    }
    
    public List<AuctionDTO> filterAuctionByUser(String filter, int userid){
        List<Auction> auctions ;
        if(filter == null || filter.isEmpty())
            {
                auctions = this.auctionFacade.findByTitleAndUser("", userid);
            }
            else
            {
                auctions = this.auctionFacade.findByTitleAndUser(filter, userid);
            }
        return AuctionService.toDTO(auctions);
    }
    
    public List<AuctionDTO> filterAuctionOrederedByUser(int userid){
        List<Auction> auctions ;
        auctions = this.auctionFacade.findOrderedByUser(userid);
        return AuctionService.toDTO(auctions);
    }

    public void editAuction(Auction auction){
        auctionFacade.edit(auction);
    }
    
    // Logic
    public static List<AuctionDTO> toDTO(List<Auction> auctions){
        List<AuctionDTO> dtos = new ArrayList<>(auctions.size());
        
        for(Auction auction : auctions){
            dtos.add(auction.toDTO());
        }
        
        return dtos;
    }
    
    public List<AuctionDTO> getAllAuctions() {
        return Auction.toDTO(auctionFacade.findAll());
    }
}
