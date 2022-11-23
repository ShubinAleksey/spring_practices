package com.example.demo2.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Galaxy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long UID;
    private String name;
    private Long galaxy_mass;
    private String galaxy_radius;
    private String absolute_star;
    private Integer total_stars;

    public Galaxy() {

    }

    public Galaxy(String name, Long galaxy_mass, String galaxy_radius, String absolute_star, Integer total_stars) {
        this.name = name;
        this.galaxy_mass = galaxy_mass;
        this.galaxy_radius = galaxy_radius;
        this.absolute_star = absolute_star;
        this.total_stars = total_stars;
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

    public Long getGalaxy_mass() {
        return galaxy_mass;
    }

    public void setGalaxy_mass(Long galaxy_mass) {
        this.galaxy_mass = galaxy_mass;
    }

    public String getGalaxy_radius() {
        return galaxy_radius;
    }

    public void setGalaxy_radius(String galaxy_radius) {
        this.galaxy_radius = galaxy_radius;
    }

    public String getAbsolute_star() {
        return absolute_star;
    }

    public void setAbsolute_star(String absolute_star) {
        this.absolute_star = absolute_star;
    }

    public Integer getTotal_stars() {
        return total_stars;
    }

    public void setTotal_stars(Integer total_stars) {
        this.total_stars = total_stars;
    }
}
