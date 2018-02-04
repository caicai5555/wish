package com.foundation.service.disease.biz;

import com.foundation.dao.entity.disease.DiseaseDescindicatorRel;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: IDiseaseDescindicatorRelBiz
 * @Description:疾病-描述性指标关联业务
 * @author wangsen
 * @date 2016/12/1 11:35
 */
public interface IDiseaseDescindicatorRelBiz {

    /**
     * @Description:查询列表
     * @param params
     * @throws Exception
     */
    public List<DiseaseDescindicatorRel> queryList(Map<String, Object> params) throws Exception;

    /**
     * @Description:根据疾病主键查询关联描述性指标主键列表
     * @param diseaseId
     * @return
     * @throws Exception
     */
    public List<String> getDescIndicatorIds(String diseaseId) throws Exception;
}
