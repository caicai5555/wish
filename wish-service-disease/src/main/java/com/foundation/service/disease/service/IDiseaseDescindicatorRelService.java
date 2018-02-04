package com.foundation.service.disease.service;

import com.foundation.dao.entity.disease.DiseaseDescindicatorRel;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: IDiseaseDescindicatorRelService
 * @Description:疾病-描述性指标关联服务
 * @author wangsen
 * @date 2016/12/1 11:35
 * @version V1.0
 */
public interface IDiseaseDescindicatorRelService {

    /**
     * @Description:保存
     * @param bean
     * @return
     * @throws Exception
     */
    public boolean save(DiseaseDescindicatorRel bean) throws Exception;

    /**
     * @Description:根据疾病主键，描述性指标主键串保存关联表实体
     * @param diseaseId
     * @param descindicatorIds
     * @return
     * @throws Exception
     */
    public void save(String diseaseId,String descindicatorIds) throws Exception;

    /**
     * @Description:批量保存
     * @param beanList
     * @return
     * @throws Exception
     */
    public void batchSave(List<DiseaseDescindicatorRel> beanList) throws Exception;

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
    public List<DiseaseDescindicatorRel> queryList(Map<String, Object> params) throws Exception;
}
