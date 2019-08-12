package com.prezyk.patient_data_server.data.dao.xml;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.SafeHtml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class LabResult {

    @XmlAttribute
    @JsonProperty("name")
    private String name;

    @XmlElement(name = "value")
    private double value;

    @XmlElement(name = "unit")
    private String unit;

    @XmlElement(name = "lower-limit")
    private double lowerLimit;

    @XmlElement(name = "upper-limit")
    private double upperLimit;


    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getLowerLimit() {
        return lowerLimit;
    }

    public void setLowerLimit(double lowerLimit) {
        this.lowerLimit = lowerLimit;
    }

    public double getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(double upperLimit) {
        this.upperLimit = upperLimit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
