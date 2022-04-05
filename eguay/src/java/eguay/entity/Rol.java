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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author parsa
 */
@Entity
@Table(name = "rol")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rol.findAll", query = "SELECT r FROM Rol r")
    , @NamedQuery(name = "Rol.findByRolid", query = "SELECT r FROM Rol r WHERE r.rolid = :rolid")
    , @NamedQuery(name = "Rol.findByType", query = "SELECT r FROM Rol r WHERE r.type = :type")
    , @NamedQuery(name = "Rol.findByName", query = "SELECT r FROM Rol r WHERE r.name = :name")})
public class Rol implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "rolid")
    private Long rolid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "type")
    private int type;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "name")
    private String name;
    @JoinTable(name = "usersrol", joinColumns = {
        @JoinColumn(name = "rolid", referencedColumnName = "rolid")}, inverseJoinColumns = {
        @JoinColumn(name = "userid", referencedColumnName = "userid")})
    @ManyToMany
    private List<Users> usersList;

    public Rol() {
    }

    public Rol(Long rolid) {
        this.rolid = rolid;
    }

    public Rol(Long rolid, int type, String name) {
        this.rolid = rolid;
        this.type = type;
        this.name = name;
    }

    public Long getRolid() {
        return rolid;
    }

    public void setRolid(Long rolid) {
        this.rolid = rolid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        hash += (rolid != null ? rolid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rol)) {
            return false;
        }
        Rol other = (Rol) object;
        if ((this.rolid == null && other.rolid != null) || (this.rolid != null && !this.rolid.equals(other.rolid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eguay.entity.Rol[ rolid=" + rolid + " ]";
    }
    
}
