/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eguay.entity;

import eguay.dto.UserDTO;
import eguay.service.AuctionService;
import eguay.service.CategoryService;
import eguay.service.MailService;
import eguay.service.UserService;
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
import javax.persistence.ManyToMany;
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
 * @author parsa - Edu
 */
@Entity
@Table(name = "users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u")
    , @NamedQuery(name = "Users.findByUsername", query = "SELECT u FROM Users u WHERE u.username = :username")
    , @NamedQuery(name = "Users.findByPassword", query = "SELECT u FROM Users u WHERE u.password = :password")
    , @NamedQuery(name = "Users.findByEmail", query = "SELECT u FROM Users u WHERE u.email = :email")
    , @NamedQuery(name = "Users.findByJoineddate", query = "SELECT u FROM Users u WHERE u.joineddate = :joineddate")
    , @NamedQuery(name = "Users.findByName", query = "SELECT u FROM Users u WHERE u.name = :name")
    , @NamedQuery(name = "Users.findBySurname", query = "SELECT u FROM Users u WHERE u.surname = :surname")
    , @NamedQuery(name = "Users.findBySex", query = "SELECT u FROM Users u WHERE u.sex = :sex")
    , @NamedQuery(name = "Users.findByBirthyear", query = "SELECT u FROM Users u WHERE u.birthyear = :birthyear")
    , @NamedQuery(name = "Users.findByCountry", query = "SELECT u FROM Users u WHERE u.country = :country")
    , @NamedQuery(name = "Users.findByCity", query = "SELECT u FROM Users u WHERE u.city = :city")
    , @NamedQuery(name = "Users.findByAddress", query = "SELECT u FROM Users u WHERE u.address = :address")
    , @NamedQuery(name = "Users.findByUserid", query = "SELECT u FROM Users u WHERE u.userid = :userid")})
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "password")
    private String password;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "email")
    private String email;
    @Column(name = "joineddate")
    @Temporal(TemporalType.DATE)
    private Date joineddate;
    @Size(max = 2147483647)
    @Column(name = "name")
    private String name;
    @Size(max = 2147483647)
    @Column(name = "surname")
    private String surname;
    @Column(name = "sex")
    private Integer sex;
    @Basic(optional = false)
    @NotNull
    @Column(name = "birthyear")
    @Temporal(TemporalType.DATE)
    private Date birthyear;
    @Size(max = 2147483647)
    @Column(name = "country")
    private String country;
    @Size(max = 2147483647)
    @Column(name = "city")
    private String city;
    @Size(max = 2147483647)
    @Column(name = "address")
    private String address;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "userid")
    private Integer userid;
    @ManyToMany(mappedBy = "usersList")
    private List<Rol> rolList;
    @ManyToMany(mappedBy = "usersList")
    private List<Mail> mailList;
    @ManyToMany(mappedBy = "usersList")
    private List<Groups> groupsList;
    @ManyToMany(mappedBy = "usersList")
    private List<Category> categoryList;
    @ManyToMany(mappedBy = "usersList")
    private List<Auction> auctionList;
    @ManyToMany(mappedBy = "usersList1")
    private List<Auction> auctionList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "senderid")
    private List<Mail> mailList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "biderid")
    private List<Bid> bidList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sellerid")
    private List<Auction> auctionList2;

    public Users() {
    }

    public Users(Integer userid) {
        this.userid = userid;
    }

    public Users(Integer userid, String username, String password, String email, Date birthyear) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.email = email;
        this.birthyear = birthyear;
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

    public Date getJoineddate() {
        return joineddate;
    }

    public void setJoineddate(Date joineddate) {
        this.joineddate = joineddate;
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

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    @XmlTransient
    public List<Rol> getRolList() {
        return rolList;
    }

    public void setRolList(List<Rol> rolList) {
        this.rolList = rolList;
    }

    @XmlTransient
    public List<Mail> getMailList() {
        return mailList;
    }

    public void setMailList(List<Mail> mailList) {
        this.mailList = mailList;
    }

    @XmlTransient
    public List<Groups> getGroupsList() {
        return groupsList;
    }

    public void setGroupsList(List<Groups> groupsList) {
        this.groupsList = groupsList;
    }

    @XmlTransient
    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    @XmlTransient
    public List<Auction> getAuctionList() {
        return auctionList;
    }

    public void setAuctionList(List<Auction> auctionList) {
        this.auctionList = auctionList;
    }

    @XmlTransient
    public List<Auction> getAuctionList1() {
        return auctionList1;
    }

    public void setAuctionList1(List<Auction> auctionList1) {
        this.auctionList1 = auctionList1;
    }

    @XmlTransient
    public List<Mail> getMailList1() {
        return mailList1;
    }

    public void setMailList1(List<Mail> mailList1) {
        this.mailList1 = mailList1;
    }

    @XmlTransient
    public List<Bid> getBidList() {
        return bidList;
    }

    public void setBidList(List<Bid> bidList) {
        this.bidList = bidList;
    }

    @XmlTransient
    public List<Auction> getAuctionList2() {
        return auctionList2;
    }

    public void setAuctionList2(List<Auction> auctionList2) {
        this.auctionList2 = auctionList2;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userid != null ? userid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.userid == null && other.userid != null) || (this.userid != null && !this.userid.equals(other.userid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eguay.entity.Users[ userid=" + userid + " ]";
    }

    // DTO
    public UserDTO toDTO(){
        UserDTO dto = new UserDTO();
        
        // DB
        dto.setId(userid);
        
        // Conceptual
        dto.setUsername(username);
        dto.setPassword(password);
        dto.setEmail(email);
        dto.setName(name);
        dto.setUsername(username);
        dto.setSex(sex);
        dto.setBirthyear(birthyear);
        dto.setCountry(country);
        dto.setCity(city);
        dto.setAddress(address);
        
        return dto;
    }
}
