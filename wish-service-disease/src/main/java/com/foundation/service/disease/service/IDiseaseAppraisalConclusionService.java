package com.foundation.service.disease.service;

import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.disease.DiseaseAppraisalConclusion;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: IDiseaseAppraisalConclusionService
 * @Description:疾病-评估结论（新描述性指标）服务
 * @author wangsen
 * @date 2016/12/1 11:35
 * @version V1.0
 */
public interface IDiseaseAppraisalConclusionService {

    /**
     * @Description:保存
     * @param diseaseAppraisalConclusion
     * @return
     * @throws Exception
     */
    public boolean save(DiseaseAppraisalConclusion diseaseAppraisalConclusion) throws Exception;

    /**
     * @Description:更新
     * @param diseaseAppraisalConclusion
     * @throws Exception
     */
    public void update(DiseaseAppraisalConclusion diseaseAppraisalConclusion) throws Exception;

    /**
     * @Description:根据id查询
     * @param id
     * @return
     * @throws Exception
     */
    public DiseaseAppraisalConclusion queryById(String id) throws Exception;

    /**
     * @Description:查询列表
     * @param params
     * @return
     * @throws Exception
     */
    public List<DiseaseAppraisalConclusion> queryList(Map<String, Object> params) throws Exception;

    /**
     * @Description:分页查询
     * @param params
     * @param pageBounds
     * @return
     * @throws Exception
     */
    public Page<DiseaseAppraisalConclusion> queryPage(Map<String, Object> params, Page<DiseaseAppraisalConclusion> pageBounds) throws Exception;

    /**
     * @Description:根据指标信息Id查询列表
     * @param diseaseId
     * @return
     * @throws Exception
     */
    public List<DiseaseAppraisalConclusion> queryByDeseaseId(String diseaseId)throws Exception;
}
