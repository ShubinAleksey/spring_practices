package com.example.demo2.Models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "star")
public class Star {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long UID;
    @NotNull(message = "Поле не должно быть пустым")
    @NotBlank
    @Size(min = 2, max = 30, message = "Размер данного поля должен быть в диапозоне от 2 до 30")
    private  String name;
    @NotNull(message = "Поле не должно быть пустым")
    @NotBlank
    @Size(min = 2, max = 30, message = "Размер данного поля должен быть в диапозоне от 2 до 30")
    private  String class_star;
    @NotNull(message = "Поле не должно быть пустым")
    @Min(value = 0, message = "Значение в поле не может быть меньше нуля")
    private  Integer lumen;

    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    public System system;

    public System getSystem() {
        return system;
    }

    public void setSystem(System system) {
        this.system = system;
    }

    public Star() {

    }

    public Star(String name, String class_star, Integer lumen) {
        this.name = name;
        this.class_star = class_star;
        this.lumen = lumen;
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

    public String getClass_star() {
        return class_star;
    }

    public void setClass_star(String class_star) {
        this.class_star = class_star;
    }

    public Integer getLumen() {
        return lumen;
    }

    public void setLumen(Integer lumen) {
        this.lumen = lumen;
    }
}
