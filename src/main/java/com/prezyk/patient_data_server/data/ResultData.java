package com.prezyk.patient_data_server.data;

import org.apache.tomcat.jni.Local;
import org.hibernate.annotations.Cascade;
import org.hibernate.mapping.Value;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "RESULT")
//@Inheritance
//@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING)
public class ResultData {

    public ResultData(){}

    public ResultData(Patient patient, TestType testType, String filepath, LocalDate date, String description) {
        this.patient = patient;
        this.testType = testType;
        this.filepath = filepath;
        this.date = date;
        this.description = description;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @ManyToOne
    @JoinColumn
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Patient patient;

    @Column(name = "TEST_TYPE")
    @Enumerated(EnumType.STRING)
    private TestType testType;

    @Column(name = "FILEPATH")
    private String filepath;

    @Column(name = "TEST_DATE")
//    @Basic
//    @Temporal(TemporalType.DATE)
    private LocalDate date;

    @Column(name = "DESCRIPTION")
    @Lob
    private String description;

    public LocalDate getDate() {
        return this.date;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TestType getTestType() {
        return testType;
    }

    public void setTestType(TestType testType) {
        this.testType = testType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public enum TestType {
        LAB(Values.LAB),
        IMG(Values.IMG),
        IMG_S(Values.IMG_S),
        TS(Values.TS);
        private String value;

        TestType(String value) {
            this.value = value;
        }

        public String getType() {
            return value;
        }

        public static class Values {
            public static final String LAB = "LAB";
            public static final String IMG = "IMG";
            public static final String IMG_S = "IMG_S";
            public static final String TS = "TS";


        }


    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
