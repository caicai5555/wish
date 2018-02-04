package com.foundation.service.disease.biz;

import com.foundation.dao.entity.disease.DiseaseAppraisalConclusionRel;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: IDiseaseAppraisalConclusionRelBiz
 * @Description:疾病-疾病与评估结论关联业务
 * @author wangsen
 * @date 2016/12/1 11:35
 * @version V1.0
 */
public interface IDiseaseAppraisalConclusionRelBiz {

    /**
     * @Description:查询列表
     * @param params
     * @throws Exception
     */
    public List<DiseaseAppraisalConclusionRel> queryList(Map<String, Object> params) throws Exception;

    /**
     * @Description:根据疾病主键查询关联结论主键
     * @param diseaseId
     * @return
     * @throws Exception
     */
    public List<String> getConclusionIds(String diseaseId) throws Exception;
}
