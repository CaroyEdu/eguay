/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eguay.dto;

import eguay.entity.Users;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Pedro Antonio Benito Rojano
 */
public class AuctionDTO {
    // DB
    private Long id;
    
    // Conceptual
    private String name;
    private String category;
    private Float startPrice;
    private Date startDate;
    private Date closeDate;
    private Integer closeNumberofBids;
    private Float closePrice;
    private Float maxBid;
    private Boolean active;
    private String fotourl;
    private List<CategoryDTO> categoryList;
    private String description;
    private Float startPrice;
    private List<UserDTO> userList;
    private UserDTO sellerID;
    private String descripcion;
    private String urlFoto;
    private Long categoryId;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
    
    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }
    
    // Relationships
    private String seller;
    
    public UserDTO getSellerID()
    {
        return sellerID;
    }
    
    public void setSellerID(UserDTO sellerID)
    {
        this.sellerID = sellerID;
    }
    
    public List<UserDTO> getUserList()
    {
        return this.userList;
    }
    
    public void setUserList(List<UserDTO> userList)
    {
        this.userList = userList;
    }
    
    public Float getStartPrice()
    {
        return startPrice;
    }
    
    public void setStartPrice(Float startPrice)
    {
        this.startPrice = startPrice;
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public void setDescription(String description)
    {
        this.description = description;
    }
    
    public void setCategoryList(List<CategoryDTO> categoryList){
        this.categoryList = categoryList;
    }
    
    public List<CategoryDTO> getCategoryList()
    {
        return this.categoryList;
    }
    
    public String getFotourl()
    {
        return fotourl;
    }
    
    public void setFotourl(String fotourl){
        this.fotourl = fotourl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    
    public Float getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(Float startprice) {
        this.startPrice = startprice;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }

    public Integer getCloseNumberofBids() {
        return closeNumberofBids;
    }

    public void setCloseNumberofBids(Integer closeNumberofBids) {
        this.closeNumberofBids = closeNumberofBids;
    }

    public Float getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(Float closePrice) {
        this.closePrice = closePrice;
    }

    public Float getMaxBid() {
        return maxBid;
    }

    public void setMaxBid(Float maxBid) {
        this.maxBid = maxBid;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
    
    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }
    
    public void setDescripcion(String description) {
        this.descripcion = description;
    }
    
    public String getDescripcion() {
        return this.descripcion;
    }
}
