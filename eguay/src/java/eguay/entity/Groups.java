/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eguay.entity;

import java.io.Serializable;
import java.util.LinkedList;
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author parsa
 */
@Entity
@Table(name = "groups")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Groups.findAll", query = "SELECT g FROM Groups g")
    , @NamedQuery(name = "Groups.findByGroupid", query = "SELECT g FROM Groups g WHERE g.groupid = :groupid")
    , @NamedQuery(name = "Groups.findByName", query = "SELECT g FROM Groups g WHERE g.name = :name")})
public class Groups implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "groupid")
    private Long groupid;
    @Size(max = 2147483647)
    @Column(name = "name")
    private String name;
    @JoinTable(name = "groupsmail", joinColumns = {
        @JoinColumn(name = "groupid", referencedColumnName = "groupid")}, inverseJoinColumns = {
        @JoinColumn(name = "mailid", referencedColumnName = "mailid")})
    @ManyToMany
    private List<Mail> mailList;
    @JoinTable(name = "usersgroups", joinColumns = {
        @JoinColumn(name = "groupid", referencedColumnName = "groupid")}, inverseJoinColumns = {
        @JoinColumn(name = "userid", referencedColumnName = "userid")})
    @ManyToMany
    private List<Users> usersList;

    public Groups() {
    }

    public Groups(Long groupid) {
        this.groupid = groupid;
    }

    public Long getGroupid() {
        return groupid;
    }

    public void setGroupid(Long groupid) {
        this.groupid = groupid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (groupid != null ? groupid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Groups)) {
            return false;
        }
        Groups other = (Groups) object;
        if ((this.groupid == null && other.groupid != null) || (this.groupid != null && !this.groupid.equals(other.groupid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eguay.entity.Groups[ groupid=" + groupid + " ]";
    }
    
    // Auxiliary functions
    
    public void add(Users user){
        if(this.usersList == null)
            this.usersList = new LinkedList<>();
        this.usersList.add(user);
    }
     
    public void addAll(List<Users> users){
        if(users != null && !users.isEmpty()){
            if(this.usersList == null)
                this.usersList = new LinkedList<>();
            this.usersList.addAll(users);
        }
    }
    
    public void addAllGroups(List<Groups> groups){
        if(groups!= null && !groups.isEmpty()){
            if(this.usersList == null)
                this.usersList = new LinkedList<>();
            for(Groups group : groups){
                this.usersList.addAll(group.getUsersList());
            }
        }
    }
    
    public boolean contains(Users user){
        return this.usersList.contains(user);
    }
}
