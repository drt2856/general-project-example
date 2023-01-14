/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cu.generalprojectexample.model;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Daniel
 */
@Entity
@Table(name = "authority", catalog = "general-project-example", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Authority.findAll", query = "SELECT a FROM Authority a"),
    @NamedQuery(name = "Authority.findByUserid", query = "SELECT a FROM Authority a WHERE a.authorityPK.userid = :userid"),
    @NamedQuery(name = "Authority.findByAuthority", query = "SELECT a FROM Authority a WHERE a.authorityPK.authority = :authority")})
public class Authority implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AuthorityPK authorityPK;
    @JoinColumn(name = "userid", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private User user;

    public Authority() {
    }

    public Authority(AuthorityPK authorityPK) {
        this.authorityPK = authorityPK;
    }

    public Authority(int userid, String authority) {
        this.authorityPK = new AuthorityPK(userid, authority);
    }

    public AuthorityPK getAuthorityPK() {
        return authorityPK;
    }

    public void setAuthorityPK(AuthorityPK authorityPK) {
        this.authorityPK = authorityPK;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (authorityPK != null ? authorityPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Authority)) {
            return false;
        }
        Authority other = (Authority) object;
        if ((this.authorityPK == null && other.authorityPK != null) || (this.authorityPK != null && !this.authorityPK.equals(other.authorityPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Authority{" +
                "authorityPK=" + authorityPK;
    }
}
