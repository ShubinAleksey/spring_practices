package com.example.clothesshop.Models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.*;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
@Table(name = "checks")
public class Check {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long UID;

    @NotNull(message= "Данное поле не может быть пустым")
    @Min(value = 1, message = "Номер чека не должен быть меньше 1")
    private Integer checkNumber;
    @NotBlank(message="Данное поле не может состоять из пробелов")
    @NotEmpty(message= "Данное поле не может быть пустым")
    @Size(min = 10, max = 10, message="Длина значения должна быть в диапозоне от 10 символов")
    private String ITN;
    @FutureOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfSeal;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_uid")
    public Order order;

    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    public User user;

    public Check() {

    }

    public Check(Integer checkNumber, String ITN, Date dateOfSeal, Order order, User user) {
        this.checkNumber = checkNumber;
        this.ITN = ITN;
        this.dateOfSeal = dateOfSeal;
        this.order = order;
        this.user = user;
    }

    public Long getUID() {
        return UID;
    }

    public void setUID(Long UID) {
        this.UID = UID;
    }

    public Integer getCheckNumber() {
        return checkNumber;
    }

    public void setCheckNumber(Integer checkNumber) {
        this.checkNumber = checkNumber;
    }

    public String getITN() {
        return ITN;
    }

    public void setITN(String ITN) {
        this.ITN = ITN;
    }

    public Date getDateOfSeal() {
        return dateOfSeal;
    }

    public void setDateOfSeal(Date dateOfSeal) {
        this.dateOfSeal = dateOfSeal;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
