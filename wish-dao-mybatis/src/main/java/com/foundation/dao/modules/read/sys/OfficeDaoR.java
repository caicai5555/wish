package com.foundation.dao.modules.read.sys;

import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.sys.Office;
import com.foundation.dao.modules.MybatisBaseDao;
import com.foundation.dao.util.TreeDao;

import java.util.List;
import java.util.Map;

/**
 * @Title: OfficeDaoR.java
 * @Package com.foundation.dao.modules.read.sys
 * @Description: office读的dao
 * @author chunyangli(chunyangli@bioeh.com)
 * @date 2016/11/21 10:07
 */
@MyBatisRepository
public interface OfficeDaoR  extends MybatisBaseDao<String, Office> {

    /**
     * 找到所有子节点
     * @param entity
     * @return
     */
    public List<Office> findByParentIdsLike(Office entity);




    /**
     * 获取单条数据
     * @param id
     * @return
     */
    public Office get(Map<String,Object> params);

    /**
     * 获取单条数据
     * @param entity
     * @return
     */
    public Office get(Office entity);

    /**
     * 查询数据列表，如果需要分页，请设置分页对象，如：entity.setPage(new Page<T>());
     * @param entity
     * @return
     */
    public List<Office> findList(Office entity);

    /**
     * 查询所有数据列表
     * @param entity
     * @return
     */
    public List<Office> findAllList(Office entity);

    /**
     * 查询所有数据列表
     * @see public List<T> findAllList(T entity)
     * @return
     */
    @Deprecated
    public List<Office> findAllList();


	
}
