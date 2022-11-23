package com.example.demo2.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class System {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long UID;
    private String name;
    private Long system_mass;
    private Integer total_stars;
    private Integer total_known_planets;
    private Integer total_moons;
    public System() {

    }

    public System(String name, Long system_mass, Integer total_stars, Integer total_known_planets, Integer total_moons) {
        this.name = name;
        this.system_mass = system_mass;
        this.total_stars = total_stars;
        this.total_known_planets = total_known_planets;
        this.total_moons = total_moons;
    }

    public Long getUID() {
        return UID;
    }

    public void setUID(Long UID) {
        this.UID = UID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSystem_mass() {
        return system_mass;
    }

    public void setSystem_mass(Long system_mass) {
        this.system_mass = system_mass;
    }

    public Integer getTotal_stars() {
        return total_stars;
    }

    public void setTotal_stars(Integer total_stars) {
        this.total_stars = total_stars;
    }

    public Integer getTotal_known_planets() {
        return total_known_planets;
    }

    public void setTotal_known_planets(Integer total_known_planets) {
        this.total_known_planets = total_known_planets;
    }

    public Integer getTotal_moons() {
        return total_moons;
    }

    public void setTotal_moons(Integer total_moons) {
        this.total_moons = total_moons;
    }
}
