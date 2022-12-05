package com.example.clothesshop.Models;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.util.List;

@Entity
@Table(name = "typesProduct")
public class TypeProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long UID;

    @NotBlank(message="Данное поле не может состоять из пробелов")
    @NotEmpty(message= "Данное поле не может быть пустым")
    @Size(min = 1, max = 50, message="Длина значения должна быть в диапозоне от 4 до 50")
    private String typeName;

    @OneToMany(mappedBy = "typeProduct", fetch = FetchType.EAGER)
    public List<Product> product;

    public TypeProduct() {

    }

    public TypeProduct(String typeName, List<Product> product) {
        this.typeName = typeName;
        this.product = product;
    }

    public Long getUID() {
        return UID;
    }

    public void setUID(Long UID) {
        this.UID = UID;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }
}
