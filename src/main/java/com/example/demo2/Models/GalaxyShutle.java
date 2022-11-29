package com.example.demo2.Models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class GalaxyShutle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long UID;

    @NotNull(message = "Поле не должно быть пустым")
    @NotBlank
    @Size(min = 1, max = 70, message = "Размер данного поля должен быть в диапозоне от 1 до 70")
    private String name;

    @ManyToMany()
    @JoinTable(name = "galaxyshutles_system",
            joinColumns = @JoinColumn(name = "system_uid"),
            inverseJoinColumns = @JoinColumn(name = "galaxyshutle_uid"))
    public List<System> system;

    public GalaxyShutle() {
    }

    public GalaxyShutle(String name, List<System> system) {
        this.name = name;
        this.system = system;
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

    public List<System> getSystem() {
        return system;
    }

    public void setSystem(List<System> system) {
        this.system = system;
    }
}
