package com.prezyk.patient_data_server.data.dao.xml;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

//@XmlRootElement(name = "lab-results")
@XmlAccessorType(XmlAccessType.FIELD)
public class LabResults {

    @XmlElement(name = "tests-group")
//    @JsonProperty("tests-group")
    private List<TestsGroup> testsGroups;


    public List<TestsGroup> getTestsGroups() {
        return testsGroups;
    }

    public void setTestsGroups(List<TestsGroup> testsGroups) {
        this.testsGroups = testsGroups;
    }

}
