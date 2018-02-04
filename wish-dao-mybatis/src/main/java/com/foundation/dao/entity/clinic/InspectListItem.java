package com.foundation.dao.entity.clinic;

import com.foundation.dao.util.DataEntity;
/**
 * @ClassName: InspectListItem
 * @Description: 检验单与检验项关系实体
 * @Author cuiyaohua
 * @Date 2016/10/13
 *
 */
public class InspectListItem extends DataEntity<InspectListItem>{
    private static final long serialVersionUID = 1L;
    /**
     *检验单名称
     */
    private String inspectName;
    /**
     *检验项名称
     */
    private String itemName;
    /**
     *检验项id
     */
    private String itemId;
    /**
     *检验单id
     */
    private String listId;

    public String getInspectName() {
        return inspectName;
    }

    public void setInspectName(String inspectName) { this.inspectName = inspectName == null ? null : inspectName.trim(); }

    public String getItemName() { return itemName; }

    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
    }

    public String getItemId() { return itemId; }

    public void setItemId(String itemId) { this.itemId = itemId; }

    public String getListId() { return listId; }

    public void setListId(String listId) { this.listId = listId; }
}