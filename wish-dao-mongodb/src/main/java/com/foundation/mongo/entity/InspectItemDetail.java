package com.foundation.mongo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @Title InspectItemDetail
 * @Package com.foundation.mongo.entity
 * @Description 检验项实体，用于mongodb
 * @Author cuiyaohua
 * @date 2016-10-26 23:44
 */
@Document
public class InspectItemDetail implements Serializable{
    private static final long serialVersionUID = 1L;
    /**
     * _id
     */
    @Id
    private String id;
    /**
     * 英文名
     */
    private String englishName;
    /**
     * 项目名称
     */
    private String itemName;
    /**
     * 单位
     */
    private String units;
    /**
     * 正常范围
     */
    private String normalRange;
    /**
     * 最大值
     */
    private Float max;
    /**
     * 最小值
     */
    private Float min;
    /**
     * 指标
     */
    private String indexName;
    /**
     * 换算公式
     */
    private String reduceFormula;
    /**
     * 指标id
     */
    private String indexId;
    /**
     * 值
     */
    private String value;

    public String getIndexId() { return indexId; }

    public void setIndexId(String indexId) { this.indexId = indexId == null ? null : indexId.trim(); }

    public String getEnglishName() { return englishName; }

    public void setEnglishName(String englishName) { this.englishName = englishName == null ? null : englishName.trim(); }

    public String getItemName() { return itemName; }

    public void setItemName(String itemName) { this.itemName = itemName == null ? null : itemName.trim(); }

    public String getUnits() { return units; }

    public void setUnits(String units) { this.units = units == null ? null : units.trim(); }

    public String getNormalRange() { return normalRange; }

    public void setNormalRange(String normalRange) { this.normalRange = normalRange == null ? null : normalRange.trim(); }

    public Float getMax() { return max; }

    public void setMax(Float max) { this.max = max; }

    public Float getMin() { return min; }

    public void setMin(Float min) { this.min = min; }

    public String getIndexName() { return indexName; }

    public void setIndexName(String indexName) { this.indexName = indexName == null ? null : indexName.trim(); }

    public String getReduceFormula() { return reduceFormula; }

    public void setReduceFormula(String reduceFormula) { this.reduceFormula = reduceFormula == null ? null : reduceFormula.trim(); }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getValue() { return value; }

    public void setValue(String value) { this.value = value; }
}
