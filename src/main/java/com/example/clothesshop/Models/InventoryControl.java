package com.example.clothesshop.Models;

import javax.persistence.*;
import javax.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "inventoryControls")
public class InventoryControl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long UID;

    @NotEmpty(message= "Данное поле не может быть пустым")
    @Size(min = 4, max = 20, message="Длина значения должна быть в диапозоне от 4 до 20")
    private String documentName;
    @NotEmpty(message= "Данное поле не может быть пустым")
    @Size(min = 4, max = 20, message="Длина значения должна быть в диапозоне от 4 до 30")
    private String warehouse;
    @PastOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @Min(value = 10000, message = "Номер накладной должен быть не меньше 10000")
    @Max(value = 999999999, message = "Номер накладной должен быть не больше 999999999")
    private Integer invoiceNumber;
    @NotNull(message= "Данное поле не может быть пустым")
    @Min(value = 200, message = "Укажите цену продукта не ниже 200 рублей")
    @Max(value = 50000, message = "Укажите цену продукта не выше 50000 рублей")
    private double cost;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "deliveries_uid")
    public Delivery delivery;

    public InventoryControl() {

    }

    public InventoryControl(String documentName, String warehouse, Date date, Integer invoiceNumber, double cost, Delivery delivery) {
        this.documentName = documentName;
        this.warehouse = warehouse;
        this.date = date;
        this.invoiceNumber = invoiceNumber;
        this.cost = cost;
        this.delivery = delivery;
    }

    public Long getUID() {
        return UID;
    }

    public void setUID(Long UID) {
        this.UID = UID;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(Integer invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }
}
