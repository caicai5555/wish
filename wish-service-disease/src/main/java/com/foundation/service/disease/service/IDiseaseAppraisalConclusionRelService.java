package com.foundation.service.disease.service;

import com.foundation.dao.entity.disease.DiseaseAppraisalConclusionRel;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: IDiseaseAppraisalConclusionRelService
 * @Description:疾病-疾病与评估结论关联服务
 * @author wangsen
 * @date 2016/12/1 11:35
 * @version V1.0
 */
public interface IDiseaseAppraisalConclusionRelService {

    /**
     * @Description:保存
     * @param bean
     * @return
     * @throws Exception
     */
    public boolean save(DiseaseAppraisalConclusionRel bean) throws Exception;

    /**
     * @Description:批量保存
     * @param beanList
     * @return
     * @throws Exception
     */
    public void batchSave(List<DiseaseAppraisalConclusionRel> beanList) throws Exception;

    /**
     * @Description:根据疾病信息id删除关联数据
     * @param diseaseId
     * @throws Exception
     */
    public void deleteByDiseaseId(String diseaseId) throws Exception;

    /**
     * @Description:查询列表
     * @param params
     * @throws Exception
     */
    public List<DiseaseAppraisalConclusionRel> queryList(Map<String,Object> params) throws Exception;
}
