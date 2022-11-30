package com.example.demo2.Models;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "galaxys")
public class Galaxy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long UID;
    @NotNull(message = "Поле не должно быть пустым")
    @NotBlank
    @Size(min = 2, max = 30, message = "Размер данного поля должен быть в диапозоне от 2 до 30")
    private String name;
    @NotNull(message = "Поле не должно быть пустым")
    @Max(value = 999999999, message = "Значение должно быть не выше 999999999")
    private Long galaxy_mass;
    @NotNull(message = "Поле не должно быть пустым")
    @NotBlank
    @Size(min = 2, max = 10, message = "Размер данного поля должен быть в диапозоне от 2 до 10")
    private String galaxy_radius;
    @NotNull(message = "Поле не должно быть пустым")
    @NotBlank
    @Size(min = 1, max = 3, message = "Размер данного поля должен быть в диапозоне от 1 до 3")
    private String absolute_star;
    @NotNull(message = "Поле не должно быть пустым")
    @Positive(message = "Звезд не должно быть меньше 0")
    @Max(value = 9999, message = "Значение должно быть не выше 9999")
    private Integer total_stars;

    @OneToOne(optional = true, mappedBy = "galaxy", orphanRemoval = true)
    public System system;

    public System getSystem() {
        return system;
    }

    public void setSystem(System system) {
        this.system = system;
    }

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
