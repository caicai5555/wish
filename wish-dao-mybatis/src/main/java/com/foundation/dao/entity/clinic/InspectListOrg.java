package com.foundation.dao.entity.clinic;

import com.foundation.dao.util.DataEntity;
/**
 * @ClassName: InspectListOrg
 * @Description: 检验单与授权机构关系实体
 * @Author cuiyaohua
 * @Date 2016/10/13
 *
 */
public class InspectListOrg extends DataEntity<InspectListOrg>{
    private static final long serialVersionUID = 1L;
    /**
     *检验单名称
     */
    private String inspectName;
    /**
     *机构
     */
    private String orgName;
    /**
     *检验单id
     */
    private String listId;
    /**
     *机构id
     */
    private String orgId;

    public String getInspectName() {
        return inspectName;
    }

    public void setInspectName(String inspectName) { this.inspectName = inspectName == null ? null : inspectName.trim(); }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public String getListId() { return listId; }

    public void setListId(String listId) { this.listId = listId; }

    public String getOrgId() { return orgId; }

    public void setOrgId(String orgId) { this.orgId = orgId; }
}