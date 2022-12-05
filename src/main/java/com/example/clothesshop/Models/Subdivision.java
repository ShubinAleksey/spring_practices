package com.example.clothesshop.Models;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "subdivisions")
public class Subdivision {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long UID;

    @NotEmpty(message= "Данное поле не может быть пустым")
    @Size(min = 4, max = 50, message="Длина значения должна быть в диапозоне от 4 до 50")
    private String subdivisionName;

    @OneToMany(mappedBy = "subdivision", fetch = FetchType.EAGER)
    public Set<User> user = new HashSet<>();

    @OneToMany(mappedBy = "subdivision", fetch = FetchType.EAGER)
    public Set<SubdivisionReporting> subdivisionReporting = new HashSet<>();

    public Subdivision() {

    }

    public Subdivision(String subdivisionName, Set<User> user, Set<SubdivisionReporting> subdivisionReporting) {
        this.subdivisionName = subdivisionName;
        this.user = user;
        this.subdivisionReporting = subdivisionReporting;
    }


    public Long getUID() {
        return UID;
    }

    public void setUID(Long UID) {
        this.UID = UID;
    }

    public String getSubdivisionName() {
        return subdivisionName;
    }

    public void setSubdivisionName(String subdivisionName) {
        this.subdivisionName = subdivisionName;
    }

    public Set<User> getUser() {
        return user;
    }

    public void setUser(Set<User> user) {
        this.user = user;
    }

    public Set<SubdivisionReporting> getSubdivisionReporting() {
        return subdivisionReporting;
    }

    public void setSubdivisionReporting(Set<SubdivisionReporting> subdivisionReporting) {
        this.subdivisionReporting = subdivisionReporting;
    }
}
