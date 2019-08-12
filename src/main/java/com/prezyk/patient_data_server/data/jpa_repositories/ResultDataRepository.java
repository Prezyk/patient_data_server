package com.prezyk.patient_data_server.data.jpa_repositories;

import com.prezyk.patient_data_server.data.Patient;
import com.prezyk.patient_data_server.data.ResultData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

public interface ResultDataRepository extends JpaRepository<ResultData, Long> {

//    List<ResultData> findAllByPatient_ID(Long patientID);
    List<ResultData> findAllByPatient(Patient patient);
//    List<ResultData> findAllByPatient_IDAndDateAfterAndDateBefore(Long patientID, Date startDate, Date endDate);
    List<ResultData> findAllByPatientAndDateAfterAndDateBefore(Patient patient, LocalDate startDate, LocalDate endDate);
//    List<ResultData> findAllByPatient_IDAndDateAfterAndDateBeforeAndTestType(Long patientID, Date startDate, Date endDate, ResultData.TestType testType);
    List<ResultData> findAllByPatientAndDateAfterAndDateBeforeAndTestType(Patient patient, LocalDate startDate, LocalDate endDate, ResultData.TestType testType);

    ResultData findResultDataById(Long ID);


}
