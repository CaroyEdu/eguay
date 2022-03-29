/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eguay.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jean-
 */
@Entity
@Table(name = "bid")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bid.findAll", query = "SELECT b FROM Bid b")
    , @NamedQuery(name = "Bid.findByBid", query = "SELECT b FROM Bid b WHERE b.bid = :bid")
    , @NamedQuery(name = "Bid.findByBidid", query = "SELECT b FROM Bid b WHERE b.bidid = :bidid")})
public class Bid implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "bid")
    private Integer bid;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "bidid")
    private Long bidid;
    @JoinColumn(name = "auctionid", referencedColumnName = "auctionid")
    @ManyToOne(optional = false)
    private Auction auctionid;
    @JoinColumn(name = "biderid", referencedColumnName = "userid")
    @ManyToOne(optional = false)
    private Users biderid;

    public Bid() {
    }

    public Bid(Long bidid) {
        this.bidid = bidid;
    }

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public Long getBidid() {
        return bidid;
    }

    public void setBidid(Long bidid) {
        this.bidid = bidid;
    }

    public Auction getAuctionid() {
        return auctionid;
    }

    public void setAuctionid(Auction auctionid) {
        this.auctionid = auctionid;
    }

    public Users getBiderid() {
        return biderid;
    }

    public void setBiderid(Users biderid) {
        this.biderid = biderid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bidid != null ? bidid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bid)) {
            return false;
        }
        Bid other = (Bid) object;
        if ((this.bidid == null && other.bidid != null) || (this.bidid != null && !this.bidid.equals(other.bidid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eguay.entity.Bid[ bidid=" + bidid + " ]";
    }
    
}
