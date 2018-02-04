package com.foundation.service.indicator.service;

import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.indicatorSys.Indicator;
import com.foundation.dao.entity.indicatorSys.IndicatorTreeDO;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: IIndicatorService
 * @Description:指标体系-指标信息服务
 * @author wangsen
 * @date 2016/11/24 11:35
 * @version V1.0
 */
public interface IIndicatorService {

    /**
     * @Description:保存指标信息
     * @param indicator
     * @return
     * @throws Exception
     */
    public boolean save(Indicator indicator) throws Exception;

    /**
     * @Description 更新指标
     * @param indicator
     * @throws Exception
     */
    public void update(Indicator indicator) throws Exception;

    /**
     * @Description 根据id查询
     * @param id
     * @return
     * @throws Exception
     */
    public Indicator queryById(String id) throws Exception;

    /**
     * @Description 分页查询
     * @param params
     * @param pageBounds
     * @return
     * @throws Exception
     */
    public Page<Indicator> queryPage(Map<String, Object> params, Page<Indicator> pageBounds) throws Exception;
    /**
     * @Description:根据查询条件分页查询指标分类
     * @param params
     * @param pageBounds
     * @return
     */
    public Page<Indicator>  queryCategoryPage(Map<String, Object> params, Page<Indicator> pageBounds) throws Exception;

    /**
     * @Description 查询列表
     * @param params
     * @return
     * @throws Exception
     */
    public List<Indicator> queryList(Map<String, Object> params) throws Exception;

    /**
     * @Description 查询数量
     * @param params
     * @return
     * @throws Exception
     */
    public Integer getCount(Map<String, Object> params) throws Exception;
    /**
     * @Description 查询父子级联信息并根据父节点分页,分页为手动分页，不走mybatis分页拦截器
     * @param params 查询参数
     * @return
     */
    public List<IndicatorTreeDO> queryTree(Map<String, Object> params)throws Exception;

    /**
     * @Description 根据查询条件统计父节点总数
     * @param params
     * @return
     */
    public Integer getCountByParams(Map<String, Object> params)throws Exception;

    /**
     * @Description 查询所有
     * @return
     * @throws Exception
     */
    public List<Indicator> queryChilds()throws Exception;

    /**
     * @Description 根据疾病id查询列表
     * @param diseaseId
     * @return
     * @throws Exception
     */
    public List<Indicator> queryByDiseaseId(String diseaseId) throws Exception;
}
