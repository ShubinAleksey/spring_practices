package com.example.clothesshop.Models;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "subdivisionreportings")
public class SubdivisionReporting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long UID;

    @NotEmpty(message= "Данное поле не может быть пустым")
    @Size(min = 5, max = 40, message="Длина значения должна быть в диапозоне от 5 до 40 символов")
    private String documentName;
    @NotEmpty(message= "Данное поле не может быть пустым")
    @Size(min = 5, max = 10, message="Длина значения должна быть в диапозоне от 5 до 10 символов")
    private String documentType;
    @NotEmpty(message= "Данное поле не может быть пустым")
    @Size(min = 5, max = 100, message="Длина значения должна быть в диапозоне от 5 до 100 символов")
    private String commentary;

    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    public Subdivision subdivision;

    public SubdivisionReporting() {

    }

    public SubdivisionReporting(String documentName, String documentType, String commentary, Subdivision subdivision) {
        this.documentName = documentName;
        this.documentType = documentType;
        this.commentary = commentary;
        this.subdivision = subdivision;
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

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    public Subdivision getSubdivision() {
        return subdivision;
    }

    public void setSubdivision(Subdivision subdivision) {
        this.subdivision = subdivision;
    }
}
