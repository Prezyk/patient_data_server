package com.prezyk.patient_data_server.data.jpa_repositories;

import com.prezyk.patient_data_server.data.Event;
import com.prezyk.patient_data_server.data.Patient;
import com.prezyk.patient_data_server.data.ResultData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findAllByPatient(Patient patient);
//    List<Event> findAllByPatient_ID(Long patientID);
//    List<Event> findAllByPatient_IDAndDateAfterAndDateBefore(Long patientID, Date startDate, Date endDate);
    List<Event> findAllByPatientAndDateAfterAndDateBefore(Patient patient, Date startDate, Date endDate);
//    List<Event> findAllByPatient_IDAndDateAfterAndDateBeforeAAndEventType(Long patientID, Date startDate, Date endDate, String eventType);
    List<Event> findAllByPatientAndDateAfterAndDateBeforeAndEventType(Patient patient, Date startDate, Date endDate, String eventType);

}
