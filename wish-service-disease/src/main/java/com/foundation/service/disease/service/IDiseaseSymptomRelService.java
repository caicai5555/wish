package com.foundation.service.disease.service;

import com.foundation.dao.entity.disease.DiseaseSymptomRel;

import java.util.List;

/**
 * @ClassName: IDiseaseSymptomRelService
 * @Description:疾病-疾病与症状信息关系服务
 * @author wangsen
 * @date 2016/12/1 11:35
 * @version V1.0
 */
public interface IDiseaseSymptomRelService {

    /**
     * @Description:保存
     * @param bean
     * @return
     * @throws Exception
     */
    public boolean save(DiseaseSymptomRel bean) throws Exception;

    /**
     * @Description:根据疾病主键，症状主键串保存关联表实体
     *              注意：：由于目前症状分类是枚举存储，所以ids逗号分隔后又以#号分隔分类与症状；
     *              如：症状分类#症状,症状分类#症状...
     * @param diseaseId
     * @param symptomIds
     * @return
     * @throws Exception
     */
    public void save(String diseaseId,String symptomIds) throws Exception;

    /**
     * @Description:批量保存
     * @param beanList
     * @return
     * @throws Exception
     */
    public void batchSave(List<DiseaseSymptomRel> beanList) throws Exception;

    /**
     * @Description:根据疾病信息id删除关联数据
     * @param diseaseId
     * @throws Exception
     */
    public void deleteByDiseaseId(String diseaseId) throws Exception;
}
