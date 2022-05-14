/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eguay.entity;

import eguay.dto.MailDTO;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author parsa
 */
@Entity
@Table(name = "mail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mail.findAll", query = "SELECT m FROM Mail m")
    , @NamedQuery(name = "Mail.findBySubject", query = "SELECT m FROM Mail m WHERE m.subject = :subject")
    , @NamedQuery(name = "Mail.findByBody", query = "SELECT m FROM Mail m WHERE m.body = :body")
    , @NamedQuery(name = "Mail.findByMailid", query = "SELECT m FROM Mail m WHERE m.mailid = :mailid")
    , @NamedQuery(name = "Mail.findBySentDate", query = "SELECT m FROM Mail m WHERE m.sentDate = :sentDate")})
public class Mail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 2147483647)
    @Column(name = "subject")
    private String subject;
    @Size(max = 2147483647)
    @Column(name = "body")
    private String body;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "mailid")
    private Long mailid;
    @Column(name = "sentDate")
    @Temporal(TemporalType.DATE)
    private Date sentDate;
    @ManyToMany(mappedBy = "mailList")
    private List<Groups> groupsList;
    @JoinTable(name = "usersmail", joinColumns = {
        @JoinColumn(name = "mailid", referencedColumnName = "mailid")}, inverseJoinColumns = {
        @JoinColumn(name = "userid", referencedColumnName = "userid")})
    @ManyToMany
    private List<Users> usersList;
    @JoinTable(name = "suggestedauction", joinColumns = {
        @JoinColumn(name = "mailid", referencedColumnName = "mailid")}, inverseJoinColumns = {
        @JoinColumn(name = "auctionid", referencedColumnName = "auctionid")})
    @ManyToMany
    private List<Auction> auctionList;
    @JoinColumn(name = "senderid", referencedColumnName = "userid")
    @ManyToOne(optional = false)
    private Users senderid;

    public Mail() {
    }

    public Mail(Long mailid) {
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

    public Long getMailid() {
        return mailid;
    }

    public void setMailid(Long mailid) {
        this.mailid = mailid;
    }

    public Date getSentDate() {
        return sentDate;
    }

    public void setSentDate(Date sentDate) {
        this.sentDate = sentDate;
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

    @XmlTransient
    public List<Auction> getAuctionList() {
        return auctionList;
    }

    public void setAuctionList(List<Auction> auctionList) {
        this.auctionList = auctionList;
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
    
    public MailDTO toDTO(){
        MailDTO dto = new MailDTO();
        
        dto.setId(mailid);
        dto.setSubject(subject);
        dto.setBody(body);
        dto.setSentDate(sentDate);
        
        return dto;
    }
}
