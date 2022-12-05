package com.example.clothesshop.Models;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.util.List;

@Entity
@Table(name = "colorsProduct")
public class ColorProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long UID;

    @NotBlank(message="Данное поле не может состоять из пробелов")
    @NotEmpty(message= "Данное поле не может быть пустым")
    @Size(min = 1, max = 50, message="Длина значения должна быть в диапозоне от 4 до 50")
    private String colorName;

    @OneToMany(mappedBy = "colorProduct", fetch = FetchType.EAGER)
    public List<Product> product;

    public ColorProduct() {

    }

    public ColorProduct(String colorName, List<Product> product) {
        this.colorName = colorName;
        this.product = product;
    }

    public Long getUID() {
        return UID;
    }

    public void setUID(Long UID) {
        this.UID = UID;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }
}
