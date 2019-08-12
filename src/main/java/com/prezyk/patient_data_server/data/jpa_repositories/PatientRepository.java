package com.prezyk.patient_data_server.data.jpa_repositories;

import com.prezyk.patient_data_server.data.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    Patient findPatientById(Long Id);
    List<Patient> findAllBy();
    List<Patient> findAllByFirstName(String firstName);
    List<Patient> findAllByLastName(String lastName);
    List<Patient> findAllByPhoneNumber(String phoneNumber);
    List<Patient> findAllByPesel(String PESEL);
    List<Patient> findAllByGender(String gender);



}
