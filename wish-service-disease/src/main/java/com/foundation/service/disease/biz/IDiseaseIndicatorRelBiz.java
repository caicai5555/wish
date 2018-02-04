package com.foundation.service.disease.biz;

import com.foundation.dao.entity.disease.DiseaseIndicatorRel;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: IDiseaseIndicatorRelBiz
 * @Description:疾病-疾病与指标信息关系业务
 * @author wangsen
 * @date 2016/12/1 11:35
 * @version V1.0
 */
public interface IDiseaseIndicatorRelBiz {

    /**
     * @Description:查询列表
     * @param params
     * @throws Exception
     */
    public List<DiseaseIndicatorRel> queryList(Map<String, Object> params) throws Exception;

    /**
     * @Description:根据疾病主键查询关联结论主键
     * @param diseaseId
     * @return
     * @throws Exception
     */
    public List<String> getIndicatorIds(String diseaseId) throws Exception;

}
