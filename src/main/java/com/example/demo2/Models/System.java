package com.example.demo2.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Entity
public class System {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long UID;
    @NotNull(message = "Поле не должно быть пустым")
    @NotBlank
    private String name;
    @NotNull(message = "Поле не должно быть пустым")
    @Positive(message = "Масса не должна быть меньше 0")
    @Max(value = 999999999, message = "Значение должно быть не выше 999999999")
    private Long system_mass;
    @NotNull(message = "Поле не должно быть пустым")
    @Positive(message = "Звезд не должно быть меньше 0")
    @Max(value = 5, message = "Значение должно быть не выше 5")
    private Integer total_stars;
    @NotNull(message = "Поле не должно быть пустым")
    @Positive(message = "Планет не должно быть меньше 0")
    @Max(value = 9999, message = "Значение должно быть не выше 9999")
    private Integer total_known_planets;
    @NotNull(message = "Поле не должно быть пустым")
    @Min(value = 0, message = "Значение в поле не может быть меньше нуля")
    @Max(value = 99999, message = "Значение должно быть не выше 99999")
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
