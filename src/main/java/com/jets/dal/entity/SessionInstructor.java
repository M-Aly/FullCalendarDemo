/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.dal.entity;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ibrahim Yousre (ib.yousre@gmail.com)
 */
@Entity
@Table(name = "session_instructor")
@XmlRootElement
public class SessionInstructor extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @JoinColumn(name = "instructor", referencedColumnName = "uuid")
    @ManyToOne(optional = false)
    private Instructor instructor;
    @JoinColumn(name = "session", referencedColumnName = "uuid")
    @ManyToOne(optional = false)
    private Session session;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sessionInstructor")
    private Set<Material> materials;

    public SessionInstructor() {
    }

    public SessionInstructor(UUID uuid) {
        super(uuid);
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    @XmlTransient
    public Set<Material> getMaterials() {
        return materials;
    }

    public void setMaterials(Set<Material> materials) {
        this.materials = materials;
    }

}
