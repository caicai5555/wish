package com.foundation.service.disease.service;

import com.foundation.dao.entity.disease.Symptom;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: ISymptomService
 * @Description:疾病-症状信息服务
 * @author wangsen
 * @date 2016/12/1 11:35
 * @version V1.0
 */
public interface ISymptomService {

    /**
     * @Description:保存
     * @param symptom
     * @return
     * @throws Exception
     */
    public boolean save(Symptom symptom) throws Exception;

    /**
     * @Description:更新
     * @param symptom
     * @throws Exception
     */
    public void update(Symptom symptom) throws Exception;

    /**
     * @Description:根据id查询
     * @param id
     * @return
     * @throws Exception
     */
    public Symptom queryById(String id) throws Exception;

    /**
     * @Description:查询列表
     * @param params
     * @return
     * @throws Exception
     */
    public List<Symptom> queryList(Map<String, Object> params) throws Exception;

    /**
     * @Description:物理删除
     * @param id
     * @throws Exception
     */
    public void delete(String id) throws Exception;

    /**
     * @Descrption:根据指标信息Id查询关联实体
     * @param diseaseId
     * @return
     * @throws Exception
     */
    public List<Symptom> queryByDiseaseId(String diseaseId) throws Exception;
}
