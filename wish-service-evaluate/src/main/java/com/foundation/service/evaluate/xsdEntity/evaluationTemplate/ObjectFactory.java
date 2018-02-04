//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.08.25 at 09:12:45 AM CST 
//


package com.foundation.service.evaluate.xsdEntity.evaluationTemplate;


import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the evaluationTempleate package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: evaluationTempleate
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RuleModel }
     * 
     */
    public RuleModel createRuleModel() {
        return new RuleModel();
    }

    /**
     * Create an instance of {@link Params }
     *
     */
    public Params createParams() {
        return new Params();
    }

    /**
     * Create an instance of {@link Param }
     *
     */
    public Param createParam() {
        return new Param();
    }

    /**
     * Create an instance of {@link Rules }
     *
     */
    public Rules createRules() {
        return new Rules();
    }

    /**
     * Create an instance of {@link RuleGroup }
     *
     */
    public RuleGroup createRuleGroup() {
        return new RuleGroup();
    }

    /**
     * Create an instance of {@link Rule }
     *
     */
    public Rule createRule() {
        return new Rule();
    }

    /**
     * Create an instance of {@link Exps }
     *
     */
    public Exps createExps() {
        return new Exps();
    }

    /**
     * Create an instance of {@link ExpGroup }
     *
     */
    public ExpGroup createExpGroup() {
        return new ExpGroup();
    }

    /**
     * Create an instance of {@link Exp }
     *
     */
    public Exp createExp() {
        return new Exp();
    }

    /**
     * Create an instance of {@link ExpParam }
     *
     */
    public ExpParam createExpParam() {
        return new ExpParam();
    }

    /**
     * Create an instance of {@link ExpOper }
     *
     */
    public ExpOper createExpOper() {
        return new ExpOper();
    }

    /**
     * Create an instance of {@link ExpValue }
     *
     */
    public ExpValue createExpValue() {
        return new ExpValue();
    }

    /**
     * Create an instance of {@link Result }
     *
     */
    public Result createResult() {
        return new Result();
    }

}