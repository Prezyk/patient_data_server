package com.prezyk.patient_data_server.data.dao;

import com.pixelmed.dicom.DicomException;
import com.pixelmed.dicom.DicomInputStream;
import com.pixelmed.displaywave.DicomSourceECG;
import com.prezyk.patient_data_server.data.ResultData;

import java.io.File;
import java.io.IOException;

public class TimeSeriesResultDAO {
    ResultData resultData;

    public TimeSeriesResultDAO(ResultData ResultData) {
        this.resultData = ResultData;
    }

    public byte[] getData() throws IOException, DicomException {

        String path = resultData.getFilepath();
        DicomSourceECG ecg = new DicomSourceECG(new DicomInputStream(new File(path)));
        StringBuilder sb = new StringBuilder();

        sb.append("t,");
        for(int i=0; i<ecg.getNumberOfChannels(); i++) {
            if(i==ecg.getNumberOfChannels()-1) {
                sb.append(ecg.getChannelNames()[i] + "\n");
            } else {
                sb.append(ecg.getChannelNames()[i] + ",");
            }
        }

        int chNumber = ecg.getNumberOfChannels();
        int samplesNumber = ecg.getSamples()[0].length;
        double sampleTimeInterval = (double)ecg.getSamplingIntervalInMilliSeconds();
        double currSampleTime = 0;

        for(int i=0; i<samplesNumber; i++) {
            sb.append(currSampleTime + ",");
            currSampleTime += sampleTimeInterval;
            for(int j=0; j<chNumber; j++) {
                if (j == chNumber - 1) {
                    sb.append(ecg.getSamples()[j][i] + "\n");
                } else {
                    sb.append(ecg.getSamples()[j][i] + ",");

                }
            }
        }






        return sb.toString().getBytes();
    }

}
