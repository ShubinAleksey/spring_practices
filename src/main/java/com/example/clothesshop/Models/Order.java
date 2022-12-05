package com.example.clothesshop.Models;

import javax.persistence.*;
import javax.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long UID;

    @NotEmpty(message= "Данное поле не может быть пустым")
    @Size(min = 4, max = 50, message="Длина значения должна быть в диапозоне от 4 до 50")
    private String orderName;
    @FutureOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date purchaseDate;
    @NotBlank(message="Данное поле не может состоять из пробелов")
    @NotEmpty(message= "Данное поле не может быть пустым")
    @Min(value = 1, message = "Количество товаров должно быть не меньше 1")
    private Integer amount;

    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    public User user;

    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    public Product product;

    @OneToOne(optional = true, mappedBy = "order", orphanRemoval = true)
    public Check check;

    public Order() {

    }

    public Order(String orderName, Date purchaseDate, Integer amount, User user, Product product, Check check) {
        this.orderName = orderName;
        this.purchaseDate = purchaseDate;
        this.amount = amount;
        this.user = user;
        this.product = product;
        this.check = check;
    }

    public Long getUID() {
        return UID;
    }

    public void setUID(Long UID) {
        this.UID = UID;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Check getCheck() {
        return check;
    }

    public void setCheck(Check check) {
        this.check = check;
    }
}
