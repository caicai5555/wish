package com.foundation.dao.modules.write.sys;

import com.foundation.dao.modules.MybatisBaseDao;
import com.foundation.dao.util.TreeDao;
import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.sys.Office;

/**
 * 机构DAO接口
 * @author fqh
 * @version 2016-08-16
 */
@MyBatisRepository
public interface OfficeDao extends MybatisBaseDao<String, Office> {
    /**
     * 插入数据
     * @param entity
     * @return
     */
    public int insert(Office entity);


    /**
     * 更新所有父节点字段
     * @param entity
     * @return
     */
    public int updateParentIds(Office entity);

    /**
     * 更新数据
     * @param entity
     * @return
     */
    public void update(Office entity);

    /**
     * 删除数据（一般为逻辑删除，更新del_flag字段为1）
     * @param id
     * @see public int delete(T entity)
     * @return
     */
    public void delete(String id);

    /**
     * 删除数据（一般为逻辑删除，更新del_flag字段为1）
     * @param entity
     * @return
     */
    public int delete(Office entity);
}
