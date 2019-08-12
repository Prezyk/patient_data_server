package com.prezyk.patient_data_server.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.io.FileUtils;

import javax.persistence.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Entity
@Table(name = "PATIENT")
public class Patient {

    public Patient() { }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "PHOTO")
    @JsonIgnore
    private String photoPath;

    @Transient
    private String photoBase64;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "PESEL", length = 11, nullable = false, unique = true)
    private String pesel;

    @Column(name = "BIRTHDATE", nullable = false)
    private LocalDate birthdate;

    @Column(name = "GENDER")
    private String gender;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;


    @JsonIgnore
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<ResultData> results;

    @JsonIgnore
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Event> events;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "USER_ALLERGY", joinColumns = @JoinColumn(name = "PATIENT_ID"))
    @Column(name = "ALLERGY")
    private List<String> allergies;


    public Patient(String photoPath, String firstName, String lastName, String PESEL, LocalDate birthdate, String gender, String phoneNumber, ArrayList<ResultData> results, ArrayList<Event> events, ArrayList<String> allergies) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = PESEL;
        this.birthdate = birthdate;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.results = results;
        this.events = events;
        this.allergies = allergies;
        this.photoPath = photoPath;
        this.encodePhotoToString();
    }

    public Patient(String photoPath, String firstName, String lastName, String PESEL, LocalDate birthdate, String gender, String phoneNumber, ArrayList<String> allergies) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = PESEL;
        this.birthdate = birthdate;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.allergies = allergies;
        this.results = new ArrayList<>();
        this.events = new ArrayList<>();
        this.photoPath = photoPath;
        this.encodePhotoToString();

    }

    public Patient(String photoPath, String firstName, String lastName, String PESEL, LocalDate birthdate, String gender, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = PESEL;
        this.birthdate = birthdate;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.allergies = new ArrayList<>();
        this.results = new ArrayList<>();
        this.events = new ArrayList<>();
        this.photoPath = photoPath;
        this.encodePhotoToString();

    }


    public void encodePhotoToString()  {
        try {
            byte[] fileBytes = FileUtils.readFileToByteArray(new File(this.photoPath));
            this.photoBase64 = Base64.getEncoder().encodeToString(fileBytes);
        } catch(IOException e) {
            this.photoBase64 = "";
        }

    }

    public void addResult(ResultData resultData) {
        this.results.add(resultData);
    }

    public void addAllergy(String allergy) {
        this.allergies.add(allergy);
    }

    public void addEvent(Event event) {
        this.events.add(event);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<ResultData> getResults() {
        return results;
    }

    public void setResults(List<ResultData> results) {
        this.results = results;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<String> getAllergies() {
        return allergies;
    }

    public void setAllergies(List<String> allergies) {
        this.allergies = allergies;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getPhotoBase64() {
        return photoBase64;
    }

    public void setPhotoBase64(String photoBase64) {
        this.photoBase64 = photoBase64;
    }

    public enum Gender{
        MAN, WOMAN
    }


    @Override
    public String toString() {
        return "Patient{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", pesel='" + pesel + '\'' +
                ", birthdate=" + birthdate +
                ", gender=" + gender +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
