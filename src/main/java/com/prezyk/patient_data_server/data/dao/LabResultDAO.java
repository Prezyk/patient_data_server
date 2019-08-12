package com.prezyk.patient_data_server.data.dao;

import com.prezyk.patient_data_server.data.ResultData;
import com.prezyk.patient_data_server.data.dao.xml.LabResult;
import com.prezyk.patient_data_server.data.dao.xml.LabResults;

import javax.xml.bind.JAXB;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LabResultDAO {

    private ResultData resultData;

    public LabResultDAO(ResultData resultData) {
        this.resultData = resultData;
    }

    public LabResults getData() {
//        Path path = Paths.get(System.getProperty("user.home")).resolve("sample_lab.xml");
//        File file = new File(path.toString());
        File file = new File(resultData.getFilepath());
        LabResults labResult = JAXB.unmarshal(file, LabResults.class);
        return labResult;
    }


    public ResultData getResultData() {
        return resultData;
    }

    public void setResultData(ResultData resultData) {
        this.resultData = resultData;
    }
}
