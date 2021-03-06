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
@Table(name = "hall")
@XmlRootElement
public class Hall extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "floor")
    private String floor;
    @Column(name = "building")
    private String building;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Column(name = "capacity")
    private Integer capacity;
    @JoinColumn(name = "event", referencedColumnName = "uuid")
    @ManyToOne(optional = false)
    private Event event;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hall")
    private Set<SessionInHall> sessionInHalls;

    public Hall() {
    }

    public Hall(UUID uuid) {
        super(uuid);
    }

    public Hall(UUID uuid, String name) {
        super(uuid);
        this.name = name;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @XmlTransient
    public Set<SessionInHall> getSessionInHalls() {
        return sessionInHalls;
    }

    public void setSessionInHalls(Set<SessionInHall> sessionInHalls) {
        this.sessionInHalls = sessionInHalls;
    }

}
