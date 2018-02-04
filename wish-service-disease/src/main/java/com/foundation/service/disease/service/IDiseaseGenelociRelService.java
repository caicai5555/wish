package com.foundation.service.disease.service;

import com.foundation.dao.entity.disease.DiseaseGenelociRel;

import java.util.List;

/**
 * @ClassName: IDiseaseGenelociRelService
 * @Description:疾病-疾病与基因位点关系服务
 * @author wangsen
 * @date 2016/12/1 11:35
 * @version V1.0
 */
public interface IDiseaseGenelociRelService {

    /**
     * @Description:保存
     * @param bean
     * @return
     * @throws Exception
     */
    public boolean save(DiseaseGenelociRel bean) throws Exception;

    /**
     * @Description:根据疾病主键，基因主键串保存关联表实体
     * @param diseaseId
     * @param genelociIds
     * @return
     * @throws Exception
     */
    public void save(String diseaseId,String genelociIds) throws Exception;


    /**
     * @Description:批量保存
     * @param beanList
     * @return
     * @throws Exception
     */
    public void batchSave(List<DiseaseGenelociRel> beanList) throws Exception;

    /**
     * @Description:根据疾病信息id删除关联数据
     * @param diseaseId
     * @throws Exception
     */
    public void deleteByDiseaseId(String diseaseId) throws Exception;
}
