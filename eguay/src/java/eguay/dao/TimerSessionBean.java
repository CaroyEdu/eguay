/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eguay.dao;

import eguay.entity.Auction;
import eguay.entity.Bid;
import eguay.entity.Users;
import eguay.service.AuctionService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jean-
 */
@Stateless
public class TimerSessionBean {
    
    @EJB AuctionFacade auctionFacade;
    @EJB BidFacade bidFacade;
    @EJB AuctionService auctionService;
    @EJB UsersFacade usersFacade;

    @Schedule(hour = "*", minute = "*", second = "*/30", persistent = false)
    
    public void myTimer() {
        Date now = new Date();
        //System.out.println("Ejecutando comprobación de subastas");
        List<Auction> activeAuctions = this.auctionFacade.findByActive();
        for(Auction a : activeAuctions)
        {
            if(a.getClosedate() != null){
                if(now.compareTo(a.getClosedate()) >= 0)
                {
                    List<Bid> bidList = this.bidFacade.highestBid(a);
                    if(bidList.size()>0)
                    {
                        Bid higherBid = bidList.get(0);
                        Users user = higherBid.getBiderid();
                        List<Users> clientList = new ArrayList();
                        clientList.add(0, user);
                        a.setUsersList1(clientList);
                        a.setActive(Boolean.FALSE);

                        List<Auction> purchasedAuction = user.getAuctionList1();
                        if(purchasedAuction == null){
                            purchasedAuction = new ArrayList() ;
                        }
                        purchasedAuction.add(a);
                        user.setAuctionList1(purchasedAuction);

                        auctionService.editAuction(a);
                        usersFacade.edit(user);
                        System.out.println("La subasta " + a.getAuctionid() + " con titulo:  " + a.getTitle() + " ha sido ganada por " + user.getName() );
                    }else{
                        a.setActive(Boolean.FALSE);
                        auctionService.editAuction(a);
                        System.out.println("La subasta " + a.getAuctionid() + " con titulo:  " + a.getTitle() + " ha sido cerrada sin ganador");
                    }
                }
            }
        }
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
