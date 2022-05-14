/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eguay.dto;

import eguay.entity.Auction;
import eguay.entity.Category;
import eguay.entity.Mail;
import eguay.entity.Rol;
import java.util.Date;
import java.util.List;

/**
 *
 * @author pedro
 */
public class UserDTO {
    // DB 
    private Integer id;
    
    // Conceptual 
    private String username;
    private String password;
    private String email;
    private String name;
    private String surname;
    private Integer sex;
    private Date birthyear;
    private String country;
    private String city;
    private String address;
    
    // Relationships
    private List<RolDTO> rols;
    private List<MailDTO> mails;
    private List<CategoryDTO> favoriteCategories;
    private List<AuctionDTO> followingAuctions;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getBirthyear() {
        return birthyear;
    }

    public void setBirthyear(Date birthyear) {
        this.birthyear = birthyear;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<RolDTO> getRols() {
        return rols;
    }

    public void setRols(List<RolDTO> rols) {
        this.rols = rols;
    }

    public List<MailDTO> getMails() {
        return mails;
    }

    public void setMails(List<MailDTO> mails) {
        this.mails = mails;
    }

    public List<CategoryDTO> getFavoriteCategories() {
        return favoriteCategories;
    }

    public void setFavoriteCategories(List<CategoryDTO> favoriteCategories) {
        this.favoriteCategories = favoriteCategories;
    }

    public List<AuctionDTO> getFollowingAuctions() {
        return followingAuctions;
    }

    public void setFollowingAuctions(List<AuctionDTO> followingAuctions) {
        this.followingAuctions = followingAuctions;
    }
}
