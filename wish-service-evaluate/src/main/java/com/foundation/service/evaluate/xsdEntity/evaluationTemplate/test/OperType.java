//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.08.25 at 09:12:45 AM CST 
//


package com.foundation.service.evaluate.xsdEntity.evaluationTemplate.test;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for operType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="operType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="等于"/>
 *     &lt;enumeration value="不等于"/>
 *     &lt;enumeration value="大于"/>
 *     &lt;enumeration value="大于等于"/>
 *     &lt;enumeration value="小于"/>
 *     &lt;enumeration value="小于等于"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "operType")
@XmlEnum
public enum OperType {

    等于,
    不等于,
    大于,
    大于等于,
    小于,
    小于等于;

    public String value() {
        return name();
    }

    public static OperType fromValue(String v) {
        return valueOf(v);
    }

}
