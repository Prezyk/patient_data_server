package com.prezyk.patient_data_server.data.dto;

import com.prezyk.patient_data_server.data.ResultData;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;

public class ResultDataDTO {
    private String testType;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private String description;
    private Long id;

    public ResultDataDTO() {

    }

    public ResultDataDTO(ResultData resultData) {
        this.id = resultData.getId();
        switch(resultData.getTestType()) {
            case TS:
                this.testType = "Przebieg czasowy";
                break;
            case IMG:
                this.testType = "Obraz";
                break;
            case LAB:
                this.testType = "Laboratoryjny";
                break;
                default: this.testType = "";
        }
        this.description = resultData.getDescription();
        this.date = resultData.getDate();
    }

    public ResultData getResultData() {
        ResultData result = new ResultData();
        result.setDate(date);
        result.setDescription(description);
        switch (testType) {
            case "TS":
                result.setTestType(ResultData.TestType.TS);
                break;
            case "IMG":
                result.setTestType(ResultData.TestType.IMG);
                break;
            case "LAB":
                result.setTestType(ResultData.TestType.LAB);
                break;
                default:
                    result.setTestType(null);
                    break;
        }
        return result;
    }


    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    public LocalDate getDate() {
        return date;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
