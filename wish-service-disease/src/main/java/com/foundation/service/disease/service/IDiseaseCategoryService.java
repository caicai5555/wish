package com.foundation.service.disease.service;

import com.foundation.dao.entity.disease.DiseaseCategory;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: IDiseaseCategoryService
 * @Description:疾病-疾病类别服务
 * @author wangsen
 * @date 2016/12/1 11:35
 * @version V1.0
 */
public interface IDiseaseCategoryService {

    /**
     * @Description:保存
     * @param diseaseCategory
     * @return
     * @throws Exception
     */
    public boolean save(DiseaseCategory diseaseCategory) throws Exception;

    /**
     * @Description:更新
     * @param diseaseCategory
     * @throws Exception
     */
    public void update(DiseaseCategory diseaseCategory) throws Exception;

    /**
     * @Description:根据id查询
     * @param id
     * @return
     * @throws Exception
     */
    public DiseaseCategory queryById(String id) throws Exception;

    /**
     * @Description:查询列表
     * @param params
     * @return
     * @throws Exception
     */
    public List<DiseaseCategory> queryList(Map<String, Object> params) throws Exception;

}
