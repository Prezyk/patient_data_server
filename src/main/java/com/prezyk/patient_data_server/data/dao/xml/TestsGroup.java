package com.prezyk.patient_data_server.data.dao.xml;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
//@XmlRootElement(name = "tests-group")
public class TestsGroup {

    @XmlAttribute
    @JsonProperty("name")
    private String name;

    @XmlElement(name = "lab-result")
    @JsonProperty
    private List<LabResult> results;


    public List<LabResult> getResults() {
        return results;
    }

    public void setResults(List<LabResult> results) {
        this.results = results;
    }
}
