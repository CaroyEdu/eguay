/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eguay.dao;

import eguay.dto.AuctionDTO;
import eguay.dto.BidDTO;
import eguay.dto.UserDTO;
import eguay.entity.Auction;
import eguay.entity.Users;
import eguay.service.AuctionService;
import eguay.service.BidService;
import eguay.service.MailService;
import eguay.service.UserService;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;

/**
 *
 * @author Roy Caro Jean Edouard 50% Parsa zendehdel nobari 50%
 */
@Stateless
public class TimerSessionBean {
    
    @EJB AuctionService auctionService;
    @EJB UserService userService;
    @EJB MailService mailService;
    @EJB BidService bidService;
    @EJB AuctionFacade auctionFacade ; 
    @EJB UsersFacade userFacade;

    @Schedule(hour = "*", minute = "*", second = "*/30", persistent = false)
    
    public void myTimer() {
        Date now = new Date();
        //System.out.println("Ejecutando comprobación de subastas");
        List<AuctionDTO> activeAuctions = auctionService.filterAuctionByActive();
        for(AuctionDTO aDTO : activeAuctions)
        {
            Auction a = auctionFacade.find(aDTO.getId());
            if(a.getClosedate()!= null){
                if(now.compareTo(a.getClosedate()) >= 0)
                {
                    List<BidDTO> bidList = this.bidService.getHighestBid(aDTO);
                    if(bidList.size()>0)
                    {
                        BidDTO higherBid = bidList.get(0);
                        Long id = higherBid.getBider().longValue();
                        Users UserDao  = userFacade.find(id.intValue());
                        UserDTO user = UserDao.toDTO();
                        userService.finilizeBuyingAuction(user, aDTO);
                     //   mailService.sendMailToAuctionWinner(String.format("Has ganado la subasta %s", a.getName()), a.getId(), user.getId());
                        System.out.println("La subasta " + a.getAuctionid()+ " con titulo:  " + a.getTitle()+ " ha sido ganada por " + user.getName() );
                    }else{
                        a.setActive(Boolean.FALSE);
                        auctionFacade.edit(a);
                        System.out.println("La subasta " + a.getAuctionid()+ " con titulo:  " + a.getAuctionid()+ " ha sido cerrada sin ganador");
                    }
                }
            }
        }
        
        
        
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
