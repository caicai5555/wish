package com.foundation.service.disease.biz;

import com.foundation.dao.entity.disease.GeneLoci;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: IIndicatorService
 * @Description:疾病-基因位点业务
 * @author wangsen
 * @date 2016/12/1 11:35
 * @version V1.0
 */
public interface IGeneLociBiz {

    /**
     * @Description:保存
     * @param geneLoci
     * @return
     * @throws Exception
     */
    public boolean save(GeneLoci geneLoci) throws Exception;

    /**
     * @Description:更新
     * @param geneLoci
     * @throws Exception
     */
    public void update(GeneLoci geneLoci) throws Exception;

    /**
     * @Description:根据id查询
     * @param id
     * @return
     * @throws Exception
     */
    public GeneLoci queryById(String id) throws Exception;

    /**
     * @Description:查询列表
     * @param params
     * @return
     * @throws Exception
     */
    public List<GeneLoci> queryList(Map<String, Object> params) throws Exception;

    /**
     * @Description:物理删除
     * @param id
     * @throws Exception
     */
    public void delete(String id) throws Exception;

    /**
     * @Description:根据疾病信息关联查询实体
     * @param diseaseId
     * @throws Exception
     */
    public List<GeneLoci> queryByDiseaseId(String diseaseId) throws Exception;
}
