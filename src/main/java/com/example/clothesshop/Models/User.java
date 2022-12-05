package com.example.clothesshop.Models;

import javax.persistence.*;
import javax.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long UID;

    @NotBlank(message="Данное поле не может состоять из пробелов")
    @NotEmpty(message= "Данное поле не может быть пустым")
    @Size(min = 4, max = 50, message="Длина значения должна быть в диапозоне от 4 до 50")
    private String username;

    @NotEmpty(message= "Данное поле не может быть пустым")
    private String password;

    private boolean active;

    @NotBlank(message="Данное поле не может состоять из пробелов")
    @NotEmpty(message= "Данное поле не может быть пустым")
    @Size(min = 4, max = 50, message="Длина значения должна быть в диапозоне от 4 до 50")
    private String surname;

    @NotBlank(message="Данное поле не может состоять из пробелов")
    @NotEmpty(message= "Данное поле не может быть пустым")
    @Size(min = 4, max = 50, message="Длина значения должна быть в диапозоне от 4 до 50")
    private String name;

    @NotBlank(message="Данное поле не может состоять из пробелов")
    @Size(min = 4, max = 50, message="Длина значения должна быть в диапозоне от 4 до 50")
    private String middleName;

    @Past
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirthday;

    @NotBlank(message="Данное поле не может состоять из пробелов")
    @NotEmpty(message= "Данное поле не может быть пустым")
    @Size(min = 11, max = 11, message="Длина значения должна быть в 11 символах")
    private String telephone;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private java.util.Set<Role> roles;

    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    public Subdivision subdivision;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    public Set<Order> order = new HashSet<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    public Set<Check> check = new HashSet<>();

    public User() {

    }

    public User(String username, String password, boolean active, String surname, String name, String middleName, Date dateOfBirthday, String telephone, Set<Role> roles, Subdivision subdivision, Set<Order> order, Set<Check> check) {
        this.username = username;
        this.password = password;
        this.active = active;
        this.surname = surname;
        this.name = name;
        this.middleName = middleName;
        this.dateOfBirthday = dateOfBirthday;
        this.telephone = telephone;
        this.roles = roles;
        this.subdivision = subdivision;
        this.order = order;
        this.check = check;
    }

    public Set<Order> getOrder() {
        return order;
    }

    public void setOrder(Set<Order> order) {
        this.order = order;
    }

    public Set<Check> getCheck() {
        return check;
    }

    public void setCheck(Set<Check> check) {
        this.check = check;
    }

    public Long getUID() {
        return UID;
    }

    public void setUID(Long UID) {
        this.UID = UID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Date getDateOfBirthday() {
        return dateOfBirthday;
    }

    public void setDateOfBirthday(Date dateOfBirthday) {
        this.dateOfBirthday = dateOfBirthday;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Subdivision getSubdivision() {
        return subdivision;
    }

    public void setSubdivision(Subdivision subdivision) {
        this.subdivision = subdivision;
    }
}
