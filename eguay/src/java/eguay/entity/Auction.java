/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eguay.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jean-
 */
@Entity
@Table(name = "auction")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Auction.findAll", query = "SELECT a FROM Auction a")
    , @NamedQuery(name = "Auction.findByStartDate", query = "SELECT a FROM Auction a WHERE a.startDate = :startDate")
    , @NamedQuery(name = "Auction.findByStartPrice", query = "SELECT a FROM Auction a WHERE a.startPrice = :startPrice")
    , @NamedQuery(name = "Auction.findByMaxBid", query = "SELECT a FROM Auction a WHERE a.maxBid = :maxBid")
    , @NamedQuery(name = "Auction.findByAuctionid", query = "SELECT a FROM Auction a WHERE a.auctionid = :auctionid")
    , @NamedQuery(name = "Auction.findByTitle", query = "SELECT a FROM Auction a WHERE a.title = :title")
    , @NamedQuery(name = "Auction.findByDescription", query = "SELECT a FROM Auction a WHERE a.description = :description")
    , @NamedQuery(name = "Auction.findByFotoUrl", query = "SELECT a FROM Auction a WHERE a.fotoUrl = :fotoUrl")
    , @NamedQuery(name = "Auction.findByClosePrice", query = "SELECT a FROM Auction a WHERE a.closePrice = :closePrice")
    , @NamedQuery(name = "Auction.findByCloseDate", query = "SELECT a FROM Auction a WHERE a.closeDate = :closeDate")
    , @NamedQuery(name = "Auction.findByCloseNumberOfBids", query = "SELECT a FROM Auction a WHERE a.closeNumberOfBids = :closeNumberOfBids")})
public class Auction implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "startDate")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Column(name = "startPrice")
    @Temporal(TemporalType.DATE)
    private Date startPrice;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "maxBid")
    private Float maxBid;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "auctionid")
    private Integer auctionid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "title")
    private String title;
    @Size(max = 2147483647)
    @Column(name = "description")
    private String description;
    @Size(max = 2147483647)
    @Column(name = "fotoUrl")
    private String fotoUrl;
    @Basic(optional = false)
    @NotNull
    @Column(name = "closePrice")
    private float closePrice;
    @Basic(optional = false)
    @NotNull
    @Column(name = "closeDate")
    @Temporal(TemporalType.DATE)
    private Date closeDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "closeNumberOfBids")
    private int closeNumberOfBids;
    @JoinTable(name = "sugestedauction", joinColumns = {
        @JoinColumn(name = "auctionid", referencedColumnName = "auctionid")}, inverseJoinColumns = {
        @JoinColumn(name = "mailid", referencedColumnName = "mailid")})
    @ManyToMany
    private List<Mail> mailList;
    @JoinTable(name = "favoriteauction", joinColumns = {
        @JoinColumn(name = "auctionid", referencedColumnName = "auctionid")}, inverseJoinColumns = {
        @JoinColumn(name = "userid", referencedColumnName = "userid")})
    @ManyToMany
    private List<Users> usersList;
    @JoinTable(name = "auctioncategory", joinColumns = {
        @JoinColumn(name = "auctionid", referencedColumnName = "auctionid")}, inverseJoinColumns = {
        @JoinColumn(name = "categoryid", referencedColumnName = "categoryid")})
    @ManyToMany
    private List<Category> categoryList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auctionid")
    private List<Bid> bidList;
    @JoinColumn(name = "sellerid", referencedColumnName = "userid")
    @ManyToOne(optional = false)
    private Users sellerid;

    public Auction() {
    }

    public Auction(Integer auctionid) {
        this.auctionid = auctionid;
    }

    public Auction(Integer auctionid, String title, float closePrice, Date closeDate, int closeNumberOfBids) {
        this.auctionid = auctionid;
        this.title = title;
        this.closePrice = closePrice;
        this.closeDate = closeDate;
        this.closeNumberOfBids = closeNumberOfBids;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(Date startPrice) {
        this.startPrice = startPrice;
    }

    public Float getMaxBid() {
        return maxBid;
    }

    public void setMaxBid(Float maxBid) {
        this.maxBid = maxBid;
    }

    public Integer getAuctionid() {
        return auctionid;
    }

    public void setAuctionid(Integer auctionid) {
        this.auctionid = auctionid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }

    public float getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(float closePrice) {
        this.closePrice = closePrice;
    }

    public Date getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }

    public int getCloseNumberOfBids() {
        return closeNumberOfBids;
    }

    public void setCloseNumberOfBids(int closeNumberOfBids) {
        this.closeNumberOfBids = closeNumberOfBids;
    }

    @XmlTransient
    public List<Mail> getMailList() {
        return mailList;
    }

    public void setMailList(List<Mail> mailList) {
        this.mailList = mailList;
    }

    @XmlTransient
    public List<Users> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<Users> usersList) {
        this.usersList = usersList;
    }

    @XmlTransient
    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    @XmlTransient
    public List<Bid> getBidList() {
        return bidList;
    }

    public void setBidList(List<Bid> bidList) {
        this.bidList = bidList;
    }

    public Users getSellerid() {
        return sellerid;
    }

    public void setSellerid(Users sellerid) {
        this.sellerid = sellerid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (auctionid != null ? auctionid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Auction)) {
            return false;
        }
        Auction other = (Auction) object;
        if ((this.auctionid == null && other.auctionid != null) || (this.auctionid != null && !this.auctionid.equals(other.auctionid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eguay.entity.Auction[ auctionid=" + auctionid + " ]";
    }
    
}
