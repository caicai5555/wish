package com.foundation.dao.entity.clinic;

import com.foundation.dao.util.DataEntity;

import java.util.List;

/**
 * @ClassName: InspectList
 * @Description: 检验单实体
 * @Author cuiyaohua
 * @Date 2016/10/13
 *
 */
public class InspectList extends DataEntity<InspectList>{
    private static final long serialVersionUID = 1L;
    /**
     *检验单名称
     */
    private String inspectName;
    /**
     *英文名
     */
    private String englishName;
    /**
     *检验方法
     */
    private String inspectMethod;

    private List<InspectItem> inspectItems;

    public String getInspectName() {
        return inspectName;
    }

    public void setInspectName(String inspectName) { this.inspectName = inspectName == null ? null : inspectName.trim(); }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) { this.englishName = englishName == null ? null : englishName.trim(); }

    public String getInspectMethod() {
        return inspectMethod;
    }

    public void setInspectMethod(String inspectMethod) { this.inspectMethod = inspectMethod == null ? null : inspectMethod.trim(); }

    public List<InspectItem> getInspectItems() { return inspectItems; }

    public void setInspectItems(List<InspectItem> inspectItems) { this.inspectItems = inspectItems; }
}