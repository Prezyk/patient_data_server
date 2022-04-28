package com.prezyk.patient_data_server.data.dao;

import com.prezyk.patient_data_server.data.ResultData;
import org.dcm4che3.imageio.plugins.dcm.DicomImageReadParam;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class ImageResultDAO {

    private ResultData resultData;


    public ImageResultDAO(ResultData ResultData) {
        this.resultData = ResultData;
    }


    public byte[] getImage() throws IOException {

        String path = resultData.getFilepath();


//        BufferedImage image = ConsumerFormatImageMaker.makeEightBitFrame(new SourceImage(path), 0);
        File dicomImageFile = new File(path);
        BufferedImage jpegImage = null;
        Iterator<ImageReader> readerIterator = ImageIO.getImageReadersByFormatName("DICOM");
        ImageReader imageReader = readerIterator.next();
        DicomImageReadParam param = (DicomImageReadParam) imageReader.getDefaultReadParam();
        try {
            ImageInputStream imageInputStream = ImageIO.createImageInputStream(dicomImageFile);
            imageReader.setInput(imageInputStream, false);
            jpegImage = imageReader.read(0, param);
            imageInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        ImageIO.write(jpegImage, "png", baos);


        baos.flush();

        byte[] imageBytes = baos.toByteArray();
        System.out.println(imageBytes.length);
        baos.close();
        return imageBytes;
    }


}
