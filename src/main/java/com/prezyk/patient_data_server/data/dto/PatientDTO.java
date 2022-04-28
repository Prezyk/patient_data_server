package com.prezyk.patient_data_server.data.dto;

import com.prezyk.patient_data_server.data.Patient;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;

public class PatientDTO {
    private String firstName;
    private String lastName;
    private String pesel;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthdate;
    private String gender;
    private String phoneNumber;
    private String allergies;
    private Long id;

    public PatientDTO() {

    }

    public PatientDTO(Patient patient) {
        this.firstName = patient.getFirstName();
        this.lastName = patient.getLastName();
        this.pesel = patient.getPesel();
        this.birthdate = patient.getBirthdate();
        this.id = patient.getId();
        switch(patient.getGender()) {
            case "MAN":
                this.gender = "Mężczyzna";
                break;
            case "WOMAN":
                this.gender = "Kobieta";
                break;
                default:
                    this.gender = "";
                    break;
        }
        this.phoneNumber = patient.getPhoneNumber();
        if(patient.getAllergies()!=null && patient.getAllergies().size()>0) {
            StringBuilder sb = new StringBuilder();
            for (String s : patient.getAllergies()) {
                sb.append(s + "\n");
            }
            sb.deleteCharAt(sb.length()-1);
            this.allergies = sb.toString();
        } else {
            this.allergies = "";
        }
    }


    @Override
    public String toString() {
        return "PatientDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", PESEL='" + pesel + '\'' +
                ", birthdate=" + birthdate +
                ", gender='" + gender + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", allergies='" + allergies + '\'' +
                '}';
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

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String getPesel() {
        return pesel;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setId(String id) {
        try {
            this.id = Long.valueOf(id);
        } catch (NumberFormatException e) {
            this.id = null;
        }
    }

    public Patient getPatient() {

        String[] allergyArray = allergies.split("\n");
        ArrayList<String> allergyList = new ArrayList<>();
        for(String s: allergyArray) {
            allergyList.add(s);
        }
        System.out.println(this.toString());
        return new Patient("", firstName, lastName, pesel, birthdate, gender, phoneNumber, allergyList);
    }
}
