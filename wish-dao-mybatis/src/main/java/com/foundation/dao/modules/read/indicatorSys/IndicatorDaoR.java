package com.foundation.dao.modules.read.indicatorSys;

import com.foundation.common.persistence.Page;
import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.indicatorSys.Indicator;
import com.foundation.dao.entity.indicatorSys.IndicatorTreeDO;
import com.foundation.dao.modules.MybatisBaseDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: IndicatorDaoR
 * @Description: 指标体系-指标信息持久持久类->R
 * @author wangsen
 * @date 2016/11/24 11:33
 * @version V1.0
 */
@MyBatisRepository
public interface IndicatorDaoR extends MybatisBaseDao<String, Indicator> {
    /**
     * @Description:查询父子级联信息并根据父节点分页,分页为手动分页，不走mybatis分页拦截器
     * @param params 查询参数
     *               1.mybatisMapper中通过pageNo,pageSize获取分页信息
     *               2.普通查询参数直接获取，name
     * @return
     */
    public  List<IndicatorTreeDO> queryTree(Map<String, Object> params);

    /**
     * @Description:根据查询条件统计父节点总数
     * @param parasm
     * @return
     */
    public Integer getCountByParams(Map<String,Object> parasm);

    /**
     * @Description:根据疾病id查询列表
     * @param diseaseId
     * @return
     */
    public List<Indicator> queryByDiseaseId(String diseaseId);

    /**
     * @Description:根据查询条件分页查询指标分类
     * @param params
     * @param pageBounds
     * @return
     */
    public  List<Indicator> queryCategoryPage(@Param("map")Map params, @Param("page") Page<Indicator> pageBounds);
}