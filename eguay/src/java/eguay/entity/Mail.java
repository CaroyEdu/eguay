/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eguay.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jean-
 */
@Entity
@Table(name = "mail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mail.findAll", query = "SELECT m FROM Mail m")
    , @NamedQuery(name = "Mail.findBySubject", query = "SELECT m FROM Mail m WHERE m.subject = :subject")
    , @NamedQuery(name = "Mail.findByBody", query = "SELECT m FROM Mail m WHERE m.body = :body")
    , @NamedQuery(name = "Mail.findByMailid", query = "SELECT m FROM Mail m WHERE m.mailid = :mailid")})
public class Mail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 2147483647)
    @Column(name = "subject")
    private String subject;
    @Size(max = 2147483647)
    @Column(name = "body")
    private String body;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "mailid")
    private Integer mailid;
    @ManyToMany(mappedBy = "mailList")
    private List<Auction> auctionList;
    @ManyToMany(mappedBy = "mailList")
    private List<Groups> groupsList;
    @JoinTable(name = "usersmail", joinColumns = {
        @JoinColumn(name = "mailid", referencedColumnName = "mailid")}, inverseJoinColumns = {
        @JoinColumn(name = "userid", referencedColumnName = "userid")})
    @ManyToMany
    private List<Users> usersList;
    @JoinColumn(name = "senderid", referencedColumnName = "userid")
    @ManyToOne(optional = false)
    private Users senderid;

    public Mail() {
    }

    public Mail(Integer mailid) {
        this.mailid = mailid;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Integer getMailid() {
        return mailid;
    }

    public void setMailid(Integer mailid) {
        this.mailid = mailid;
    }

    @XmlTransient
    public List<Auction> getAuctionList() {
        return auctionList;
    }

    public void setAuctionList(List<Auction> auctionList) {
        this.auctionList = auctionList;
    }

    @XmlTransient
    public List<Groups> getGroupsList() {
        return groupsList;
    }

    public void setGroupsList(List<Groups> groupsList) {
        this.groupsList = groupsList;
    }

    @XmlTransient
    public List<Users> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<Users> usersList) {
        this.usersList = usersList;
    }

    public Users getSenderid() {
        return senderid;
    }

    public void setSenderid(Users senderid) {
        this.senderid = senderid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mailid != null ? mailid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mail)) {
            return false;
        }
        Mail other = (Mail) object;
        if ((this.mailid == null && other.mailid != null) || (this.mailid != null && !this.mailid.equals(other.mailid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eguay.entity.Mail[ mailid=" + mailid + " ]";
    }
    
}
