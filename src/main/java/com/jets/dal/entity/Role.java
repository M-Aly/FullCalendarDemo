/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.dal.entity;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ibrahim Yousre (ib.yousre@gmail.com)
 */
@Entity
@Table(name = "role")
@XmlRootElement
public class Role extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "role")
    private Set<RolePrivilege> rolePrivileges;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "role")
    private Set<SystemUserHasRole> systemUserHasRoles;

    public Role() {
    }

    public Role(UUID uuid) {
        super(uuid);
    }

    public Role(UUID uuid, String name) {
        super(uuid);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public Set<RolePrivilege> getRolePrivileges() {
        return rolePrivileges;
    }

    public void setRolePrivileges(Set<RolePrivilege> rolePrivileges) {
        this.rolePrivileges = rolePrivileges;
    }

    @XmlTransient
    public Set<SystemUserHasRole> getSystemUserHasRoles() {
        return systemUserHasRoles;
    }

    public void setSystemUserHasRoles(Set<SystemUserHasRole> systemUserHasRoles) {
        this.systemUserHasRoles = systemUserHasRoles;
    }

}
