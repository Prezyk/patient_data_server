package com.prezyk.patient_data_server;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.prezyk.patient_data_server.data.Patient;
import com.prezyk.patient_data_server.data.ResultData;
import com.prezyk.patient_data_server.data.dto.PatientDTO;
import com.prezyk.patient_data_server.data.dto.ResultDataDTO;
import com.prezyk.patient_data_server.data.jpa_repositories.EventRepository;
import com.prezyk.patient_data_server.data.jpa_repositories.PatientRepository;
import com.prezyk.patient_data_server.data.jpa_repositories.ResultDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class WebAppController {

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


    @GetMapping("/webapp/nav/addpatient")
    public String addPatient() {
        return "add-patient";
    }

    @GetMapping("/webapp/home")
    public String toHome() {
        return "index.html";
    }

    @GetMapping("/webapp/nav/searchpatients")
    public String toSearchPatients() { return "search-patients"; }

    @PostMapping("/webapp/submitpatient")
    public String patientSubmitted(Model model, @ModelAttribute("patient") PatientDTO patient,
                                   @RequestParam MultipartFile file) throws IOException, WriterException {

        Patient p = patient.getPatient();
        p = patientRepository.save(p);

//        Patient rPatient = patientRepository.save(patient);
        System.out.println(patient.toString());
        Path path  = Paths.get(System.getProperty("user.home")).resolve("PatientServerData");
        path = path.resolve(String.valueOf(p.getId()));
        Path pathQR = path.resolve("QR.png");
        path = path.resolve("photo.jpg");

        p.setPhotoPath(path.toString());
        System.out.println(p.getPhotoPath());
        p = patientRepository.save(p);
        System.out.println(p.getId());

        File outputFile = new File(path.toString());
        outputFile.mkdirs();
        ByteArrayInputStream bis = new ByteArrayInputStream(file.getBytes());
        BufferedImage imageTemp = ImageIO.read(bis);
        ImageIO.write(imageTemp, "jpg", outputFile);

        QRCodeWriter qrWriter = new QRCodeWriter();
        BitMatrix bitmatrix = qrWriter.encode(String.valueOf(p.getId()), BarcodeFormat.QR_CODE, 350, 350);

        ByteArrayOutputStream bisQR = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToPath(bitmatrix, "PNG", pathQR);

        ArrayList<ResultDataDTO> resultDTOList = new ArrayList<>();
        List<ResultData> resultList = resultDataRepository.findAllByPatient(p);
        for(ResultData r: resultList) {
            resultDTOList.add(new ResultDataDTO(r));
        }

        model.addAttribute("patient", new PatientDTO(p));
        model.addAttribute("results", resultDTOList);
        model.addAttribute("QRCode", pathQR.toString());

        return "patient-details";
    }


    @PostMapping("/webapp/submitresult")
    public String resultSubmitted(Model model, @ModelAttribute("result") ResultDataDTO resultDTO,
                                  @RequestParam MultipartFile file,
                                  @ModelAttribute("patientId") long patientID) throws IOException {
        Patient patient = patientRepository.findPatientById(patientID);
        ResultData result = resultDTO.getResultData();

        result.setPatient(patient);
        result = resultDataRepository.save(result);

        Path path  = Paths.get(System.getProperty("user.home")).resolve("PatientServerData");
        path = path.resolve(String.valueOf(patient.getId()));
        String fileName;

        switch(resultDTO.getTestType()) {
            case "IMG":
                fileName = String.valueOf(result.getId()) + "IMG.dcm";
                break;
            case "TS":
                fileName = String.valueOf(result.getId()) + "TS.dcm";
                break;
            case "LAB":
                fileName = String.valueOf(result.getId()) + "LAB.xml";
                break;
                default:
                    fileName = "";
                    break;
        }
        Path pathQR = path.resolve("QR.png");
        path = path.resolve(fileName);
        result.setFilepath(path.toString());
        file.transferTo(path);
        resultDataRepository.save(result);

        List<ResultData> results = resultDataRepository.findAllByPatient(patient);
        List<ResultDataDTO> resultsDTO = new ArrayList<>();
        for(ResultData r: results) {
            resultsDTO.add(new ResultDataDTO(r));
        }

        model.addAttribute("patient", new PatientDTO(patient));
        model.addAttribute("results", resultsDTO);
        model.addAttribute("QRCode", pathQR.toString());




//        path = path.resolve("photo.jpg");
        return "patient-details";
    }

    @GetMapping("/webapp/patientinfo")
    public String patientInfo(Model model, @RequestParam long patientId) {
        Patient patient = patientRepository.findPatientById(patientId);
        Path path  = Paths.get(System.getProperty("user.home")).resolve("PatientServerData");
        path = path.resolve(String.valueOf(patient.getId()));
        path = path.resolve("QR.png");

        List<ResultData> results = resultDataRepository.findAllByPatient(patient);
        List<ResultDataDTO> resultsDTO = new ArrayList<>();
        for(ResultData r: results) {
            resultsDTO.add(new ResultDataDTO(r));
        }

        model.addAttribute("patient", new PatientDTO(patient));
        model.addAttribute("results", resultsDTO);
        model.addAttribute("QRCode", path.toString());

        return "patient-details";
    }

    @GetMapping("/webapp/searchpatients")
    public String searchPatients(Model model, @RequestParam(name = "param", required = false) String param,
                                 @RequestParam(name = "paramValue", required = false) String paramValue) {
        List<Patient> patients = null;
        if(paramValue==null) {
            System.out.println("Param value is null");
            return "search-patient";
        }
        if (paramValue.equals("*")) {
            patients = patientRepository.findAll();
        } else {
            switch (param) {
                case "firstName":
                    patients = patientRepository.findAllByFirstName(paramValue);
                    break;
                case "lastName":
                    patients = patientRepository.findAllByLastName(paramValue);
                    break;
                case "PESEL":
                    patients = patientRepository.findAllByPesel(paramValue);
                    break;
                case "phoneNumber":
                    patients = patientRepository.findAllByPhoneNumber(paramValue);
                    break;
                default:
                    System.out.println("Something went wrong");
                    break;
            }
        }

        ArrayList<PatientDTO> patientDTOS = new ArrayList<>();
        for(Patient p: patients) {
            patientDTOS.add(new PatientDTO(p));
        }

        model.addAttribute("patients", patientDTOS);
        return "search-patients";
    }

    @GetMapping("/webapp/qr")
    @ResponseBody
    public byte[] getQRCode(@RequestParam(name = "patientID") Long patientID) throws IOException {
        Path path  = Paths.get(System.getProperty("user.home")).resolve("PatientServerData");
        path = path.resolve(String.valueOf(patientID));
        path = path.resolve("QR.png");
        BufferedImage image = ImageIO.read(new File(path.toString()));

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);
        return baos.toByteArray();
    }

    @GetMapping("/webapp/patient/photo")
    @ResponseBody
    public byte[] getPhoto(@RequestParam(name = "patientID") Long patientID) throws IOException {
        Path path = Paths.get(System.getProperty("user.home")).resolve("PatientServerData");
        path = path.resolve(String.valueOf(patientID));
        path = path.resolve("photo.jpg");
        BufferedImage image = ImageIO.read(new File(path.toString()));


        double scaledWidth = 200;
        double scaledHeight = ((double)image.getHeight())/image.getWidth() * scaledWidth;
//        Image scaledImage = image.getScaledInstance((int)scaledWidth, (int)scaledHeight, Image.SCALE_DEFAULT);
        BufferedImage scaledBImage = new BufferedImage((int)scaledWidth, (int)scaledHeight, image.getType());

        Graphics2D bGr = scaledBImage.createGraphics();
        bGr.drawImage(image, 0, 0, (int)scaledWidth, (int)scaledHeight, null);
        bGr.dispose();


        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(scaledBImage, "jpg", baos);
        return baos.toByteArray();
    }



}
