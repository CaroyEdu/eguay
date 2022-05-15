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
import eguay.service.MailService;
import eguay.service.UserService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Roy Caro Jean Edouard 50% Parsa zendehdel nobari 50%
 */
@Stateless
public class TimerSessionBean {
    
    @EJB AuctionFacade auctionFacade;
    @EJB BidFacade bidFacade;
    @EJB AuctionService auctionService;
    @EJB UsersFacade usersFacade;
    @EJB UserService userService;
    @EJB MailService mailService; 

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
                        userService.finilizeBuyingAuction(user, a);
                        //mailService.sendMailToAuctionWinner(String.format("Has ganado la subasta %s", a.getTitle()), a.getAuctionid(), user.getUserid());
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
