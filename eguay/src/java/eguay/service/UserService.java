/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eguay.service;

import eguay.dao.AuctionFacade;
import eguay.dao.CategoryFacade;
import eguay.dao.UsersFacade;
import eguay.entity.Auction;
import eguay.entity.Category;
import eguay.entity.Groups;
import eguay.entity.Mail;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import eguay.entity.Users;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
/**
 *
 * @author parsa
 */
@Stateless
public class UserService {
    @EJB UsersFacade usersFacade;
    @EJB CategoryFacade categoryFacade;
    @EJB AuctionFacade auctionFacade;
    
    // Query
    
    public List<Users> getAllUsers(){
        return this.usersFacade.findAll();
    }
    
    public List<Users> getUsersInterestedIn(Category category){
       List<Users> userList = getAllUsers();
       
       for(Users user : userList){
           if(isInterestedIn(user, category))
               userList.remove(user);
       }
       
       return userList;
    }
    
    public static List<Mail> getMails(Users user){
        List<Mail> mails = new LinkedList<>();
        
        mails.addAll(user.getMailList());
        for(Groups group : user.getGroupsList()){
            mails.addAll(group.getMailList());
        }
        
        return mails;
    }
    
    // Extra functionalities  

    public boolean isInterestedIn(Users user, Category category) {
        return user.getCategoryList().contains(category);
    }
    
    public void addToGroup(Users user, Groups group){
        user.getGroupsList().add(group);
    }
    
    // Logic
    
    public void createUser(Users user , String username ,String name ,String surname , String address ,
    String city , String email , String country , String password , Date birthday , int sex){
        
        user.setName(name);
        user.setAddress(address);
        user.setSurname(surname);
        user.setCity(city);
        user.setEmail(email);
        user.setCountry(country);
        user.setPassword(password);
        user.setUsername(username);
        user.setBirthyear(birthday);
        user.setSex(sex);
        
        usersFacade.create(user);
    }
    
    public void editFavCategories(Users user , Category category ,String check ){
        
        List<Category> categoryFavList = user.getCategoryList(); 
        if(categoryFavList == null) categoryFavList = new ArrayList() ; 
        
        if(check != null )
            {
                if(!categoryFavList.contains(category)){  
                    categoryFavList.add(category);
                    category.getUsersList().add(user);
                    categoryFacade.edit(category);
                }
             }else{
                if(categoryFavList.contains(category)){
                    categoryFavList.remove(category);
                    category.getUsersList().remove(user);
                    categoryFacade.edit(category);
                }
            }
        
    }
    
    public void editFavAuctions(Users user , Auction auction){
        
        List<Auction> auctionFavList = user.getAuctionList(); 
        if(auctionFavList == null) auctionFavList = new ArrayList() ; 
        List<Auction> auctionList = auctionFacade.findAll();
        
        if(auction != null )
            {
                if(!auctionFavList.contains(auction)){  
                    auctionFavList.add(auction);
                    user.setAuctionList(auctionFavList);
                    usersFacade.edit(user);
                    
                    List<Users> auctionUserFav = auction.getUsersList();
                    auctionUserFav.add(user);
                    auction.setUsersList(auctionUserFav);
                    auctionFacade.edit(auction);
                    
                }else {
                    auctionFavList.remove(auction);
                    user.setAuctionList(auctionFavList);
                    usersFacade.edit(user);
                    
                     List<Users> auctionUserFav = auction.getUsersList();
                     auctionUserFav.remove(user) ; 
                     auction.setUsersList(auctionUserFav);
                    auctionFacade.edit(auction);
                    
                }
            }
    }
    
    public void removePurchasedAuction(Auction auction , Users user){
        
        auction.getUsersList1().remove(user);
                auctionFacade.edit(auction);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
