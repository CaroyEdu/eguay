/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eguay.service;

import eguay.dao.AuctionFacade;
import eguay.dao.CategoryFacade;
import eguay.dao.UsersFacade;
import eguay.dto.UserDTO;
import eguay.entity.Auction;
import eguay.entity.Category;
import eguay.entity.Groups;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import eguay.entity.Users;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
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
    
    Users getUser(Integer id) {
        return usersFacade.find(id);
    }
    
    public UserDTO loginUser(String username, String password)
    {
        return this.usersFacade.userLogin(username, password).toDTO();
    }
    
    public List<UserDTO> getAllUsersDTO(){
        return toDTO(getAllUsers());
    }
    
    public List<Users> getAllUsers(){
        return this.usersFacade.findAll();
    }
    
    public List<Users> getUsersInterestedIn(Category category){
       List<Users> userList = this.usersFacade.findAll();
       
       for(Users user : userList){
           if(isInterestedIn(user, category))
               userList.remove(user);
       }
       
       return userList;
    }
    
    // Extra functionalities  

    public boolean isInterestedIn(Users user, Category category) {
        return user.getCategoryList().contains(category);
    }
    
    public void addToGroup(Users user, Groups group){
        user.getGroupsList().add(group);
    }
    
    public static List<UserDTO> toDTO(List<Users> users){
        List<UserDTO> dtos = new ArrayList<>(users.size());
        
        for(Users user : users){
            dtos.add(user.toDTO());
        }
        
        return dtos;
    }
    
    // Logic
    
    public void createUser(Users user , String username ,String name ,String surname , String address ,
    String city , String email , String country , String password , Date birthday , int sex, List<Auction> auctionList1){
        
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
        user.setAuctionList1(auctionList1);
        
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
       List<Auction> userPurchased = user.getAuctionList1();
       userPurchased.remove(auction);
       user.setAuctionList1(userPurchased);
       
        List <Users> auctions = auction.getUsersList1();
        auctions.remove(user);
        auction.setUsersList1(auctions);
        
       usersFacade.edit(user);
        auctionFacade.edit(auction);
    }
    
    public void finilizeBuyingAuction(Users user , Auction auction){
        
        List<Users> clientList = new ArrayList();
        clientList.add(0, user);
        auction.setUsersList1(clientList);
        auction.setActive(Boolean.FALSE);
        
        List<Auction> purchasedAuction = user.getAuctionList1() ;
        if(purchasedAuction == null) purchasedAuction = new ArrayList() ;
        purchasedAuction.add(auction);
        user.setAuctionList1(purchasedAuction);
        
        
        
        auctionFacade.edit(auction);
        usersFacade.edit(user);
        System.out.println("success \n");
    }
    
        public List<Auction> filterPurchasedAuctionByUser(String filter, Users userid){
        List<Auction> auctions ;
        if(filter == null || filter.isEmpty())
            {
                auctions = this.usersFacade.findPurchasedAuctionsByTitleAndUser("", userid);
            }
            else
            {
                auctions = this.usersFacade.findPurchasedAuctionsByTitleAndUser(filter, userid);
            }
        return auctions;
    }
        
        public List<Auction> filterFavAuctionByUser(String filter, Users userid){
        List<Auction> auctions ;
        if(filter == null || filter.isEmpty())
            {
                auctions = this.usersFacade.findFavAuctionsByTitleAndUser("", userid);
            }
            else
            {
                auctions = this.usersFacade.findFavAuctionsByTitleAndUser(filter, userid);
            }
        return auctions;
    }
        
        

    public List<UserDTO> getUsersDTO(List<Integer> userIds) {
        return toDTO(getUsersByIds(userIds));
    }

    public List<Users> getUsersByIds(List<Integer> userIds) {
        return usersFacade.findAll(userIds);
    }

    public UserDTO getSessionUser(HttpSession session) {
        return ((Users) session.getAttribute("user")).toDTO();
    }
}
