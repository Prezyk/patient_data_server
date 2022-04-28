//import com.sun.media.jai.codecimpl.JPEGCodec;
//import com.sun.media.jai.codecimpl.JPEGImageEncoder;
//import org.dcm4che3.tool.dcm2jpg.Dcm2Jpg;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.prezyk.patient_data_server.data.dao.xml.LabResult;
import com.prezyk.patient_data_server.data.dao.xml.LabResults;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXB;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
//
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.YEAR, 1940);
//        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
//        System.out.println(format.format(calendar.getTime()));
//        System.out.println(calendar.getTimeInMillis());
//
//
//        Date date = new Date(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
//        System.out.println(date.getTime());

//        Path path = Paths.get(System.getProperty("user.home")).resolve("sample_lab.xml");
//        File file = new File(path.toString());
//        LabResults labResult = JAXB.unmarshal(file, LabResults.class);

//        ObjectMapper mapper = new ObjectMapper();
//        mapper.wr
//        mapper.writeValue(System.out, labResult);

//        StringBuilder sb = new StringBuilder();
//
//        Scanner scan = new Scanner(file);
//
//        while(scan.hasNext()) {
//            sb.append(scan.nextLine());
//        }
//
//        String data = sb.toString();
//        System.out.println(data);
//
//        XmlMapper xmlMapper = new XmlMapper();
//        JsonNode jsonNode = xmlMapper.readTree(data);
//
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        String value = objectMapper.writeValueAsString(jsonNode);
//
//        System.out.println(value);



//        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//        try {
//            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//            Document document = dBuilder.parse(file);
//
//
//            System.out.println("Root: " + document.getDocumentElement().getNodeName());
//
//            NodeList groups = document.getElementsByTagName("tests-group");
//
//            for(int i=0; i<groups.getLength(); i++) {
//
//                Node group = groups.item(i);
//
//
//                System.out.println("Test group: " + ((Element)group).getAttribute("name"));
//
//                NodeList tests = ((Element)group).getElementsByTagName("lab-result");
//                for(int j=0; j<tests.getLength(); j++) {
//                    Node test = tests.item(j);
//                    Element testElement = (Element)test;
//                    System.out.println("Test: "+ testElement.getAttribute("name"));
//                    System.out.println("\tValue: " + testElement.getElementsByTagName("result-value").item(0).getTextContent());
//                    System.out.println("\tUnit: " + testElement.getElementsByTagName("unit").item(0).getTextContent());
//                    System.out.println("\tLower limit: " + testElement.getElementsByTagName("lower-limit").item(0).getTextContent());
//                    System.out.println("\tUpper limit: " + testElement.getElementsByTagName("upper-limit").item(0).getTextContent() + "\n");
//                }
//
//            }
//
//
//
//
//        } catch (ParserConfigurationException e) {
//            e.printStackTrace();
//        } catch (SAXException e) {
//            e.printStackTrace();
//        }


//        Path path = Paths.get(System.getProperty("user.home")).resolve("anonymous_ecg.dcm");
//        DicomSourceECG ecg = new DicomSourceECG(new DicomInputStream(new File(path.toString())));
//        short[][] samples = ecg.getSamples();

//        System.out.println(samples.length + " ; " + samples[0].length);
//
//        StringBuilder sb = new StringBuilder();
//
//        for(int i = 0; i<samples[0].length; i++) {
//            for(int j=0; j<samples.length; j++) {
//                sb.append(samples[j][i] + "\t");
//            }
//            sb.append("\n");
//        }


//        TimeSeriesResult timeSeriesResult = new TimeSeriesResult();
//        timeSeriesResult.setFilepath(path.toString());
//        TimeSeriesResultDAO dao = new TimeSeriesResultDAO(timeSeriesResult);
//
//
//        String string = new String(dao.getData());
//        System.out.println(string);
//
////
//        for(String S: ecg.getChannelNames()) {
//
//            System.out.println(S);
//        }










//        Path path2 = path.resolve("dicom_test.dcm");
//        AttributeList list = new AttributeList();
//        Path path3 = path.resolve("jpg_test.jpg");
//        File file = new File(path3.toString());
////        list.read(path2.toString());
//        BufferedImage image = ConsumerFormatImageMaker.makeEightBitFrame(new SourceImage(path2.toString()), 0);
////        SourceImage sImg = new SourceImage(path2.toString());
////        BufferedImage image = sImg.getBufferedImage();
//
//
//
//
//        ImageIO.write(image, "png", file);

//
//        ImageResultDAO dao = new ImageResultDAO(path2);
//        dao.getImage();

//        Dcm2Jpg dcm2Jpg = new Dcm2Jpg();
//        dcm2Jpg.convert(new File(path2.toString()), new File(path3.toString()));
//        ImageReader imageReader = (ImageReader)ImageIO.getImageReadersByFormatName("DICOM");

//



//        File file2 = new File(path3.toString());
//        try {
//            OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file2));
//            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(outputStream);
//            encoder.encode(myJpegImage);
//            outputStream.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
