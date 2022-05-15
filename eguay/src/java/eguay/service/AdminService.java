/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eguay.service;

import eguay.dao.AuctionFacade;
import eguay.dao.CategoryFacade;
import eguay.dao.RolFacade;
import eguay.dao.UsersFacade;
import eguay.dto.AuctionDTO;
import eguay.dto.UserDTO;
import eguay.entity.Auction;
import eguay.entity.Category;
import eguay.entity.Rol;
import eguay.entity.Users;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author carlos
 */
@Stateless
public class AdminService {
    @EJB UsersFacade userFacade;
    @EJB RolFacade rolFacade;
    @EJB AuctionFacade auctionFacade;
    @EJB CategoryFacade categoryFacade;
    
    
    public List<UserDTO> getAllUsers(){
        return Users.toDTO(userFacade.getAllOrdered());
    }
    
    public List<UserDTO> filterUsers(String username) {
        return Users.toDTO(userFacade.filter(username));
    }
    
    public void createUser(String username, String name, String surname,
            String address, String city, String email, String country,
            String password, Date birthday, int sex, List<Integer> roleIds){
        List<Rol> roles = roleIds.stream()
                .map((rId) -> rolFacade.find(rId))
                .collect(Collectors.toList());
        
        Users user = new Users();
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
        user.setRolList(roles);
        
        userFacade.create(user);
    }

    public UserDTO getUser(int id) {
        return userFacade.find(id).toDTO();
    }

    public void saveUser(UserDTO user) {
        List<Rol> roles = user.getRoleIds().stream()
            .map((rId) -> rolFacade.find(rId))
            .collect(Collectors.toList());
        Users u = userFacade.find(user.getId());
        u.setUsername(user.getUsername());
        u.setName(user.getName());
        u.setSurname(user.getSurname());
        u.setAddress(user.getAddress());
        u.setCity(user.getCity());
        u.setEmail(user.getEmail());
        u.setCountry(user.getCountry());
        u.setPassword(user.getPassword());
        u.setBirthyear(user.getBirthyear());
        u.setSex(user.getSex());
        u.setRolList(roles);
        userFacade.edit(u);
    }

    public void deleteUser(Integer id) {
        Users u = userFacade.find(id);
        userFacade.remove(u);
    }

    public void createUser(UserDTO user) {
        List<Rol> roles = user.getRoleIds().stream()
            .map((rId) -> rolFacade.find(rId))
            .collect(Collectors.toList());
        Users u = new Users();
        u.setUsername(user.getUsername());
        u.setName(user.getName());
        u.setSurname(user.getSurname());
        u.setAddress(user.getAddress());
        u.setCity(user.getCity());
        u.setEmail(user.getEmail());
        u.setCountry(user.getCountry());
        u.setPassword(user.getPassword());
        u.setBirthyear(user.getBirthyear());
        u.setSex(user.getSex());
        u.setRolList(roles);
        userFacade.create(u);
    }

    public List<AuctionDTO> filterProducts(String product) {
        return Auction.toDTO(auctionFacade.filter(product));
    }

    public List<AuctionDTO> getAllProducts() {
        return Auction.toDTO(auctionFacade.getAllOrdered());
    }

    public AuctionDTO getProduct(long id) {
        return auctionFacade.find(id).toDTO();
    }

    public void saveProduct(AuctionDTO auction) {
        Auction a = auctionFacade.find(auction.getId());
        Category c = categoryFacade.find(auction.getCategoryId());
        List<Category> cl = new ArrayList<>();
        cl.add(c);
        a.setCategoryList(cl);
        a.setStartprice(auction.getStartPrice());
        a.setFotourl(auction.getUrlFoto());
        a.setTitle(auction.getName());
        a.setDescription(auction.getDescripcion());
        a.setCloseprice(auction.getClosePrice());
        a.setClosenumberofbids(auction.getCloseNumberofBids());
        a.setClosedate(auction.getCloseDate());
        auctionFacade.edit(a);
    }

    public void deleteAuction(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
