//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.08.25 at 09:12:45 AM CST 
//


package com.foundation.service.evaluate.xsdEntity.evaluationTemplate;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element ref="{}expParam"/>
 *         &lt;element ref="{}expOper"/>
 *         &lt;element ref="{}expValue"/>
 *       &lt;/all>
 *       &lt;attribute name="rel" type="{}relType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {

})
@XmlRootElement(name = "exp")
public class Exp {

    @XmlElement(required = true)
    protected ExpParam expParam;
    @XmlElement(required = true)
    protected ExpOper expOper;
    @XmlElement(required = true)
    protected ExpValue expValue;
    @XmlAttribute(name = "rel")
    protected RelType rel;

    /**
     * Gets the value of the expParam property.
     *
     * @return
     *     possible object is
     *     {@link com.foundation.service.evaluate.xsdEntity.evaluationTemplate.test.ExpParam }
     *
     */
    public ExpParam getExpParam() {
        return expParam;
    }

    /**
     * Sets the value of the expParam property.
     *
     * @param value
     *     allowed object is
     *     {@link com.foundation.service.evaluate.xsdEntity.evaluationTemplate.test.ExpParam }
     *
     */
    public void setExpParam(ExpParam value) {
        this.expParam = value;
    }

    /**
     * Gets the value of the expOper property.
     *
     * @return
     *     possible object is
     *     {@link com.foundation.service.evaluate.xsdEntity.evaluationTemplate.test.ExpOper }
     *
     */
    public ExpOper getExpOper() {
        return expOper;
    }

    /**
     * Sets the value of the expOper property.
     *
     * @param value
     *     allowed object is
     *     {@link ExpOper }
     *
     */
    public void setExpOper(ExpOper value) {
        this.expOper = value;
    }

    /**
     * Gets the value of the expValue property.
     *
     * @return
     *     possible object is
     *     {@link ExpValue }
     *
     */
    public ExpValue getExpValue() {
        return expValue;
    }

    /**
     * Sets the value of the expValue property.
     *
     * @param value
     *     allowed object is
     *     {@link ExpValue }
     *
     */
    public void setExpValue(ExpValue value) {
        this.expValue = value;
    }

    /**
     * Gets the value of the rel property.
     *
     * @return
     *     possible object is
     *     {@link RelType }
     *
     */
    public RelType getRel() {
        return rel;
    }

    /**
     * Sets the value of the rel property.
     *
     * @param value
     *     allowed object is
     *     {@link RelType }
     *     
     */
    public void setRel(RelType value) {
        this.rel = value;
    }

}
