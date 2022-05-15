/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eguay.dto;

/**
 *
 * @author jean-
 */
public class BidDTO {
    // DB
    private Long id;
    
    // Conceptual
    private Long auction;
    private double bid;
    
    // Relationships
    private Long bider;

    public Long getAuction() {
        return auction;
    }

    public void setAuction(Long auction) {
        this.auction = auction;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getBider() {
        return bider;
    }

    public void setBider(Long bider) {
        this.bider = bider;
    }
    
    public double getBid()
    {
        return bid;
    }
    
    public void setBid(Double bid){
        this.bid = bid;
    }
}
