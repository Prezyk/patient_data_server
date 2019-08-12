package com.prezyk.patient_data_server.data.dao;

import com.pixelmed.dicom.DicomException;
import com.pixelmed.display.ConsumerFormatImageMaker;
import com.pixelmed.display.SourceImage;
import com.prezyk.patient_data_server.data.ResultData;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ImageResultDAO {

    private ResultData resultData;


    public ImageResultDAO(ResultData ResultData) {
        this.resultData = ResultData;
    }


    public byte[] getImage() throws DicomException, IOException {

        String path = resultData.getFilepath();


        BufferedImage image = ConsumerFormatImageMaker.makeEightBitFrame(new SourceImage(path), 0);
        int height = image.getHeight();
        int width = image.getWidth();



        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        ImageIO.write(image, "png", baos);


        baos.flush();

        byte[] imageBytes = baos.toByteArray();
        System.out.println(imageBytes.length);
        baos.close();
        return imageBytes;
    }


}
