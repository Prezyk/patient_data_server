package com.prezyk.patient_data_server;

import com.prezyk.patient_data_server.data.*;
import com.prezyk.patient_data_server.data.Event;
import com.prezyk.patient_data_server.data.dao.ImageResultDAO;
import com.prezyk.patient_data_server.data.dao.LabResultDAO;
//import com.prezyk.patient_data_server.data.dao.TimeSeriesResultDAO;
import com.prezyk.patient_data_server.data.dao.xml.LabResults;
import com.prezyk.patient_data_server.data.jpa_repositories.EventRepository;
import com.prezyk.patient_data_server.data.jpa_repositories.PatientRepository;
import com.prezyk.patient_data_server.data.jpa_repositories.ResultDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/data")
public class MainController {

    @Autowired
    PatientRepository patientRepository;

//    @Autowired
//    AllergyRepository allergyRepository;

    @Autowired
    ResultDataRepository resultDataRepository;

    @Autowired
    EventRepository eventRepository;

    @Autowired
    ServletContext servletContext;

    @GetMapping("/savedata")
    public void saveSampleData() {

        Path photopath = Paths.get(System.getProperty("user.home")).resolve("photo_patient.jpg");

        Patient patient = new Patient(photopath.toString(), "Jan", "Kowalski", "12345673532", LocalDate.of(1990, 12, 13), "MAN", "432432123");
        Patient patient2 = new Patient(photopath.toString(), "Eleonora", "Jankiewicz", "01027728391", LocalDate.of(1974, 8, 15), "WOMAN", "000-000-000");

//        Path path = Paths.get(System.getProperty("user.home")).resolve("anonymous_ecg.dcm");
//        Path path2 = Paths.get(System.getProperty("user.home")).resolve("sample_lab.xml");
//        Path path3 = Paths.get(System.getProperty("user.home")).resolve("dicom_test.dcm");


//        ResultData result1 = new ResultData(patient, ResultData.TestType.IMG, path3.toString(), LocalDate.of(2018, 3, 10), "Description1");
//        ResultData result2 = new ResultData(patient, ResultData.TestType.TS, path.toString(), LocalDate.of(2019, 4, 12), "Description2");
//        ResultData result3 = new ResultData(patient, ResultData.TestType.LAB, path2.toString(), LocalDate.of(2019, 3, 2), "Description2");
//
//        patient.addResult(result1);
//        patient.addResult(result2);
//        patient.addResult(result3);

//        Allergy allergy = new Allergy(patient2, "Ketoprofen");
//        Allergy allergy1 = new Allergy(patient2, "Penicylina");

        patient2.addAllergy("Ketoprofen");
        patient2.addAllergy("Penicylina");

//        resultDataRepository.save(result1);
//        resultDataRepository.save(result2);
//        resultDataRepository.save(result3);
//        allergyRepository.save(allergy);
//        allergyRepository.save(allergy1);
        patientRepository.save(patient);
        patientRepository.save(patient2);
    }


    @GetMapping("/patientinfo")
    @ResponseBody
    public Patient sendPatientInfo(@RequestParam(value="patientID") Long patientID) {
        Patient patient = patientRepository.findPatientById(patientID);
        patient.encodePhotoToString();

        return patient;
    }


    @GetMapping("/patient/results")
    public List<ResultData> sendResultsData(@RequestParam(value="patientID") Long patientID,
                                            @RequestParam(value="startDate", required = false) Long startDate,
                                            @RequestParam(value="endDate", required = false) Long endDate,
                                            @RequestParam(value="testType", required = false) String testType) {
        List<ResultData> results = null;


        if(startDate==null) {
            startDate = 0L;
        }

        if(endDate==null) {
            endDate = Long.MAX_VALUE;
        }

        Patient patient = patientRepository.findPatientById(patientID);
        Calendar startDateCal = Calendar.getInstance();
        Calendar endDateCal = Calendar.getInstance();

        startDateCal.setTimeInMillis(startDate);
        endDateCal.setTimeInMillis(endDate);

        LocalDate startDateL = LocalDate.of(startDateCal.get(Calendar.YEAR), startDateCal.get(Calendar.MONTH)+1, startDateCal.get(Calendar.DAY_OF_MONTH));
        LocalDate endDateL = LocalDate.of(endDateCal.get(Calendar.YEAR), endDateCal.get(Calendar.MONTH)+1, endDateCal.get(Calendar.DAY_OF_MONTH));

//        results = resultDataRepository.findAllByPatient(patient);
//        return results;

        if(testType==null) {
            results = resultDataRepository.findAllByPatientAndDateAfterAndDateBefore(patient, startDateL, endDateL);
            return results;
        }

        ResultData.TestType testTypeEnum;

        switch(testType) {
            case ResultData.TestType.Values.IMG:
                testTypeEnum = ResultData.TestType.IMG;
                break;
            case ResultData.TestType.Values.IMG_S:
                testTypeEnum = ResultData.TestType.IMG_S;
                break;
            case ResultData.TestType.Values.LAB:
                testTypeEnum = ResultData.TestType.LAB;
                break;
            case ResultData.TestType.Values.TS:
                testTypeEnum = ResultData.TestType.TS;
                break;
                default:
                    testTypeEnum = null;
                    break;
        }

        results = resultDataRepository.findAllByPatientAndDateAfterAndDateBeforeAndTestType(patient, startDateL, endDateL, testTypeEnum);

        System.out.println("RESULTS SENT");
        return results;
    }


    @GetMapping("/patient/labtestresult")
    public LabResults sendLabResult(@RequestParam(value = "resultID") Long resultID) {

        ResultData resultData = resultDataRepository.findResultDataById(resultID);
        LabResultDAO dao = new LabResultDAO(resultData);


        LabResults labResults = dao.getData();

        return labResults;
    }

//    @GetMapping("/patient/timeseriesresult")
//    public byte[] sendTimeSeriesResult(@RequestParam(value = "resultID") Long resultID) throws IOException, DicomException {
//
//        ResultData timeSeriesResult = resultDataRepository.findResultDataById(resultID);
//
//        if(timeSeriesResult==null) {
//            return null;
//        }
//
//        TimeSeriesResultDAO dao = new TimeSeriesResultDAO(timeSeriesResult);
//
//        return dao.getData();
//    }

    @GetMapping("/patient/imageresult")
    @ResponseBody
    public byte[] sendImageResult(@RequestParam(value="resultID") Long resultID) throws IOException {
        ResultData imageResult = resultDataRepository.findResultDataById(resultID);

//        Path path = Paths.get(System.getProperty("user.home")).resolve("dicom_test.dcm");

//        imageResult.setFilepath(path.toString());

        ImageResultDAO dao = new ImageResultDAO(imageResult);


        return dao.getImage();
    }

    @GetMapping(value = "/patient/imageseriesresults")
    public byte[] sendImageSeriesResult(@RequestParam(value="resultID") Long path) throws IOException {
        //TODO chuj wie co

//        long resultID = Long.valueOf(ID);
//        ImageSeriesResult imageSeriesResult = null;
//        servletContext.getMajorVersion();

//        InputStream in = servletContext.getResourceAsStream(path);
//        if(in == null) {
//            System.out.println(servletContext.getRealPath(path));
//        }

//        URL url = new URL("https://i.pinimg.com/originals/f3/2f/b3/f32fb3102ed4ccf3a088e827743f8761.jpg");
//        BufferedImage image = ImageIO.read(url);
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        ImageIO.write(image, "png", out);
//
//        String encoded = Base64.getEncoder().encodeToString(out.toByteArray());
//        Temp temp = new Temp();
//        temp.content = encoded;
        File file = new File("/home/kacper/sample.csv");
        System.out.println(file.getAbsolutePath());
        byte[] fileBytes = null;

        FileInputStream in = null;

        try {
            in = new FileInputStream(file);
            fileBytes = in.readAllBytes();
        } catch (IOException e) {
            System.out.println("IOException");
        }

        return fileBytes;
    }

    @GetMapping("/patient/schedule")
    public List<Event> sendPatientSchedule(@RequestParam(value="patientID") Long patientID,
                                           @RequestParam(value="startDate") Long startDate,
                                           @RequestParam(value = "endDate") Long endDate) {
        List<Event> schedule = null;
        return schedule;
    }

    @GetMapping("/patient/getgqid")
    public byte[] sendPatientQRID(@RequestParam(value = "patientID") Long patientID) {
        byte[] qrCode = null;
        //TODO generacja kodu QR
        return null;
    }


    @PostMapping("/patient/insertpatient")
    @ResponseBody
    public Patient receivePatient(@RequestBody Patient patient) {
        //TODO pakowanie do bazy plus kod QR
        byte[] qrCode = null;

        return patient;
    }



}
