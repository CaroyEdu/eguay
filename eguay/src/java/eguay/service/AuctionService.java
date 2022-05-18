/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eguay.service;

import eguay.dao.AuctionFacade;
import eguay.dao.UsersFacade;
import eguay.dto.AuctionDTO;
import eguay.entity.Auction;
import eguay.entity.Category;
import eguay.entity.Users;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Parsa zendehdel nobari 80% Pedro Antonio Benito Rojano
 */
@Stateless
public class AuctionService {
    @EJB AuctionFacade auctionFacade;
    @EJB UsersFacade userFacade;
    
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
    
    public List<AuctionDTO> filterAuctionByActive()
    {
        return toDTO(this.auctionFacade.findByActive());
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
    
    private Auction toDAO(AuctionDTO auction)
    {
        Auction a = new Auction();
        a.setFotourl(auction.getFotourl());
        a.setStartprice(auction.getStartPrice());
        a.setActive(auction.isActive());
        a.setAuctionid(auction.getId());
        a.setTitle(auction.getName());
        a.setDescription(auction.getDescription());
        a.setClosedate(auction.getCloseDate());
        a.setCloseprice(auction.getClosePrice());
        a.setClosenumberofbids(auction.getCloseNumberofBids());
        Users user = this.userFacade.getUserByID(auction.getSellerID());
        a.setSellerid(user);
        a.setStartdate(auction.getStartDate());
        
        // Añadimos la categoría
        List<Category> categoryList = new ArrayList<>();
        Category category = new Category();
        category.setCategoryid(auction.getCategoryList().get(0).getId());
        List<Users> userList = new ArrayList<>();
        userList.add(user);
        category.setUsersList(userList);
        List<Auction> auctionList = new ArrayList<>();
        auctionList.add(a);
        category.setAuctionList(auctionList);
        categoryList.add(category);
        a.setCategoryList(categoryList);
        a.setUsersList(userList);
        
        return a;
    }
    
    public void createAuction(AuctionDTO auction)
    {
        auctionFacade.create(toDAO(auction));
    }
    
    public void editAuction(AuctionDTO auction)
    {
        auctionFacade.edit(toDAO(auction));
    }
    
    public void removeAuction(AuctionDTO auction)
    {
        auctionFacade.remove(toDAO(auction));
    }
}
