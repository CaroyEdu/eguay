/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package eguay.service;

import eguay.dao.AuctionFacade;
import eguay.dao.GroupsFacade;
import eguay.dao.MailFacade;
import eguay.dao.UsersFacade;
import eguay.dto.MailDTO;
import eguay.dto.UserDTO;
import eguay.entity.Auction;
import eguay.entity.Groups;
import eguay.entity.Mail;
import eguay.entity.Users;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author pedro
 */
@Stateless
public class MailService {
    @EJB MailFacade mailFacade;
    @EJB AuctionFacade auctionFacade;
    @EJB GroupsFacade groupsFacade;
    @EJB UsersFacade usersFacade;
    
    public List<MailDTO> getAllMails(Integer userId){
        return Mail.toDTO(mailFacade.findAllMailsToUser(userId));
    }

    public List<MailDTO> getAllMails() {
        return Mail.toDTO(mailFacade.findAll());
    }

    public void sendMailToGroup(UserDTO sender, String asunto, List<Long> auctionIds, List<Long> groupIds) {
        Mail mail = new Mail();
        
        List<Auction> auctions = auctionFacade.findAll(auctionIds);
        List<Groups> groups = groupsFacade.findAll(groupIds);
        
        
        mail.setSenderid(usersFacade.find(sender.getId()));
        mail.setSubject(asunto);
        mail.setBody(asunto);
        mail.setSentDate(new Date());
        mail.setAuctionList(auctions);
        mail.setGroupsList(groups);
        
        mailFacade.create(mail);
        
        addMailToAuctions(mail, auctions);
        addMailToGroups(mail, groups);
    }
    
    public void sendMailToUsers(UserDTO sender, String asunto, List<Long> auctionIds, List<Integer> userIds) {
        Mail mail = new Mail();
        
        List<Auction> auctions = auctionFacade.findAll(auctionIds);
        List<Users> users = usersFacade.findAll(userIds);
        
        if(sender != null){
            mail.setSenderid(usersFacade.find(sender.getId()));
        }
        mail.setSubject(asunto);
        mail.setBody(asunto);
        mail.setSentDate(new Date());
        mail.setAuctionList(auctions);
        mail.setUsersList(users);
        
        mailFacade.create(mail);
        
        addMailToAuctions(mail, auctions);
        addMailToUsers(mail, users);
    }
    
    public void sendMailToAuctionWinner(String asunto, Long auctionId, Integer userId) {
        List<Long> auctionIdAsList = new ArrayList<>(1);
        auctionIdAsList.add(auctionId);
        
    }

    private void addMailToAuctions(Mail mail, List<Auction> auctions) {
        for(Auction auction : auctions){
            List<Mail> auctionMails = auction.getMailList();
            auctionMails.add(mail);
            auction.setMailList(auctionMails);
            auctionFacade.edit(auction);
        }
    }

    private void addMailToGroups(Mail mail, List<Groups> groups) {
        for(Groups group : groups){
            List<Mail> groupMails = group.getMailList();
            groupMails.add(mail);
            group.setMailList(groupMails);
            groupsFacade.edit(group);
        }
    }

    private void addMailToUsers(Mail mail, List<Users> users) {
        for(Users user : users){
            List<Mail> userMails = user.getMailList();
            userMails.add(mail);
            user.setMailList(userMails);
            usersFacade.edit(user);
        }
    }
}
