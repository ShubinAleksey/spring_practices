package com.example.clothesshop.Models;

import javax.persistence.*;
import javax.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "delivers")
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long UID;

    @PastOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateDelivery;
    @NotNull(message = "Данное поле не может быть пустым")
    @Min(value = 200, message = "Укажите цену продукта не ниже 200 рублей")
    @Max(value = 50000, message = "Укажите цену продукта не выше 50000 рублей")
    private Integer priceDelivery;

    @NotNull(message = "Данное поле не может быть пустым")
    @Min(value = 1, message = "Количество товаров должно быть не меньше 1")
    private Integer amount;

    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    public Suppliers suppliers;

    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    public Product products;

    @OneToOne(optional = true, mappedBy = "delivery")
    public InventoryControl inventoryControl;

    public Delivery() {

    }

    public Delivery(Date dateDelivery, Integer priceDelivery, Integer amount, Suppliers suppliers, Product products, InventoryControl inventoryControl) {
        this.dateDelivery = dateDelivery;
        this.priceDelivery = priceDelivery;
        this.amount = amount;
        this.suppliers = suppliers;
        this.products = products;
        this.inventoryControl = inventoryControl;
    }

    public Long getUID() {
        return UID;
    }

    public void setUID(Long UID) {
        this.UID = UID;
    }

    public Date getDateDelivery() {
        return dateDelivery;
    }

    public void setDateDelivery(Date dateDelivery) {
        this.dateDelivery = dateDelivery;
    }

    public Integer getPriceDelivery() {
        return priceDelivery;
    }

    public void setPriceDelivery(Integer priceDelivery) {
        this.priceDelivery = priceDelivery;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Suppliers getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(Suppliers suppliers) {
        this.suppliers = suppliers;
    }

    public Product getProduct() {
        return products;
    }

    public void setProduct(Product product) {
        this.products = product;
    }

    public InventoryControl getInventoryControl() {
        return inventoryControl;
    }

    public void setInventoryControl(InventoryControl inventoryControl) {
        this.inventoryControl = inventoryControl;
    }
}
