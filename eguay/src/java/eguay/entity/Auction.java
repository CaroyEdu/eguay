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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
    , @NamedQuery(name = "Auction.findByStartdate", query = "SELECT a FROM Auction a WHERE a.startdate = :startdate")
    , @NamedQuery(name = "Auction.findByMaxbid", query = "SELECT a FROM Auction a WHERE a.maxbid = :maxbid")
    , @NamedQuery(name = "Auction.findByTitle", query = "SELECT a FROM Auction a WHERE a.title = :title")
    , @NamedQuery(name = "Auction.findByDescription", query = "SELECT a FROM Auction a WHERE a.description = :description")
    , @NamedQuery(name = "Auction.findByFotourl", query = "SELECT a FROM Auction a WHERE a.fotourl = :fotourl")
    , @NamedQuery(name = "Auction.findByCloseprice", query = "SELECT a FROM Auction a WHERE a.closeprice = :closeprice")
    , @NamedQuery(name = "Auction.findByClosedate", query = "SELECT a FROM Auction a WHERE a.closedate = :closedate")
    , @NamedQuery(name = "Auction.findByClosenumberofbids", query = "SELECT a FROM Auction a WHERE a.closenumberofbids = :closenumberofbids")
    , @NamedQuery(name = "Auction.findByStartprice", query = "SELECT a FROM Auction a WHERE a.startprice = :startprice")
    , @NamedQuery(name = "Auction.findByAuctionid", query = "SELECT a FROM Auction a WHERE a.auctionid = :auctionid")})
public class Auction implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "startdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startdate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "maxbid")
    private Float maxbid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "title")
    private String title;
    @Size(max = 2147483647)
    @Column(name = "description")
    private String description;
    @Size(max = 2147483647)
    @Column(name = "fotourl")
    private String fotourl;
    @Column(name = "closeprice")
    private Float closeprice;
    @Column(name = "closedate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date closedate;
    @Column(name = "closenumberofbids")
    private Integer closenumberofbids;
    @Column(name = "startprice")
    private Float startprice;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "auctionid")
    private Long auctionid;
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
    @ManyToMany(mappedBy = "auctionList")
    private List<Mail> mailList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auctionid")
    private List<Bid> bidList;
    @JoinColumn(name = "sellerid", referencedColumnName = "userid")
    @ManyToOne(optional = false)
    private Users sellerid;

    public Auction() {
    }

    public Auction(Long auctionid) {
        this.auctionid = auctionid;
    }

    public Auction(Long auctionid, Date startdate, String title) {
        this.auctionid = auctionid;
        this.startdate = startdate;
        this.title = title;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Float getMaxbid() {
        return maxbid;
    }

    public void setMaxbid(Float maxbid) {
        this.maxbid = maxbid;
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

    public String getFotourl() {
        return fotourl;
    }

    public void setFotourl(String fotourl) {
        this.fotourl = fotourl;
    }

    public Float getCloseprice() {
        return closeprice;
    }

    public void setCloseprice(Float closeprice) {
        this.closeprice = closeprice;
    }

    public Date getClosedate() {
        return closedate;
    }

    public void setClosedate(Date closedate) {
        this.closedate = closedate;
    }

    public Integer getClosenumberofbids() {
        return closenumberofbids;
    }

    public void setClosenumberofbids(Integer closenumberofbids) {
        this.closenumberofbids = closenumberofbids;
    }

    public Float getStartprice() {
        return startprice;
    }

    public void setStartprice(Float startprice) {
        this.startprice = startprice;
    }

    public Long getAuctionid() {
        return auctionid;
    }

    public void setAuctionid(Long auctionid) {
        this.auctionid = auctionid;
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
    public List<Mail> getMailList() {
        return mailList;
    }

    public void setMailList(List<Mail> mailList) {
        this.mailList = mailList;
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
