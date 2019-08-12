//package com.prezyk.patient_data_server.data;
//
//import org.hibernate.annotations.Cascade;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "ALLERGY")
//public class Allergy {
//
//    public Allergy(){}
//
//    public Allergy(String name) {
//        this.allergyName = name;
//    }
//
//    public Allergy(Patient patient, String allergyName) {
//        this.patient = patient;
//        this.allergyName = allergyName;
//    }
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "ID")
//    private long id;
//
//    @ManyToOne
//    @JoinColumn
//    @Cascade(org.hibernate.annotations.CascadeType.ALL)
//    private Patient patient;
//
//    @Column(name = "NAME", nullable = false)
//    private String allergyName;
//
//    public String getAllergyName() {
//        return allergyName;
//    }
//
//    public void setAllergyName(String allergyName) {
//        this.allergyName = allergyName;
//    }
//}
