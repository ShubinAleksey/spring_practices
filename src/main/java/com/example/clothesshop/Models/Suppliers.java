package com.example.clothesshop.Models;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.util.List;

@Entity
@Table(name = "supplierses")
public class Suppliers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long UID;

    @NotEmpty(message= "Данное поле не может быть пустым")
    @Size(min = 1, max = 50, message="Длина значения должна быть в диапозоне от 1 до 50")
    private String suppliersName;
    @NotBlank(message="Данное поле не может состоять из пробелов")
    @NotEmpty(message= "Данное поле не может быть пустым")
    @Size(min = 10, max = 10, message="Длина значения должна быть в диапозоне от 10 символов")
    private String ITN;
    @NotEmpty(message= "Данное поле не может быть пустым")
    @Size(min = 1, max = 100, message="Длина значения должна быть в диапозоне от 1 до 100")
    private String legalAddress;
    @NotBlank(message="Данное поле не может состоять из пробелов")
    @NotEmpty(message= "Данное поле не может быть пустым")
    @Size(min = 13, max = 13, message="Длина значения должна быть в диапозоне от 13 символов")
    private String PSRN;
    @NotBlank(message="Данное поле не может состоять из пробелов")
    @NotEmpty(message= "Данное поле не может быть пустым")
    @Size(min = 11, max = 11, message="Длина значения должна быть в 11 символах")
    private String telephone;
    @NotBlank(message="Данное поле не может состоять из пробелов")
    @NotEmpty(message= "Данное поле не может быть пустым")
    @Email
    @Size(min = 4, max = 45, message="Длина значения должна быть в диапозоне от 4 до 45")
    private String email;

    @OneToMany(mappedBy = "suppliers", fetch = FetchType.EAGER)
    public List<Delivery> delivery;

    public Suppliers() {

    }

    public Suppliers(String suppliersName, String ITN, String legalAddress, String PSRN, String telephone, String email, List<Delivery> delivery) {
        this.suppliersName = suppliersName;
        this.ITN = ITN;
        this.legalAddress = legalAddress;
        this.PSRN = PSRN;
        this.telephone = telephone;
        this.email = email;
        this.delivery = delivery;
    }

    public Long getUID() {
        return UID;
    }

    public void setUID(Long UID) {
        this.UID = UID;
    }

    public String getSuppliersName() {
        return suppliersName;
    }

    public void setSuppliersName(String suppliersName) {
        this.suppliersName = suppliersName;
    }

    public String getITN() {
        return ITN;
    }

    public void setITN(String ITN) {
        this.ITN = ITN;
    }

    public String getLegalAddress() {
        return legalAddress;
    }

    public void setLegalAddress(String legalAddress) {
        this.legalAddress = legalAddress;
    }

    public String getPSRN() {
        return PSRN;
    }

    public void setPSRN(String PSRN) {
        this.PSRN = PSRN;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Delivery> getDelivery() {
        return delivery;
    }

    public void setDelivery(List<Delivery> delivery) {
        this.delivery = delivery;
    }
}
