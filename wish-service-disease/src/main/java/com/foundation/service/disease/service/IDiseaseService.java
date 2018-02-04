package com.foundation.service.disease.service;

import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.disease.Disease;
import com.foundation.dao.entity.disease.DiseaseRelations;

import java.util.List;
import java.util.Map;

/**
 * @Description:疾病-疾病信息服务
 * @author wangsen
 * @date 2016/12/1 11:35
 * @version V1.0
 */
public interface IDiseaseService {

    /**
     * @Description:保存
     * @param disease
     * @return
     * @throws Exception
     */
    public boolean save(Disease disease) throws Exception;

    /**
     * @Description:更新
     * @param disease
     * @throws Exception
     */
    public void update(Disease disease) throws Exception;

    /**
     * @Description:根据id查询
     * @param id
     * @return
     * @throws Exception
     */
    public Disease queryById(String id) throws Exception;

    /**
     * @Description:查询列表
     * @param params
     * @return
     * @throws Exception
     */
    public List<Disease> queryList(Map<String, Object> params) throws Exception;

    /**
     * @Description:分页查询
     * @param params
     * @param pageBounds
     * @return
     * @throws Exception
     */
    public Page<Disease> queryPage(Map<String, Object> params, Page<Disease> pageBounds) throws Exception;


    /**
     * @Description:更新检测名字是否存在，判定条件name==,id or pid !=
     * @param params
     * @return
     * @throws Exception
     */
    public List<Disease> queryNameExist(Map<String,Object> params) throws Exception;

    /**
     * @Description 根据id级联查询关联指标
     * @param id
     * @return
     * @throws Exception
     */
    public DiseaseRelations queryIndicatorById(String id) throws Exception;

}
