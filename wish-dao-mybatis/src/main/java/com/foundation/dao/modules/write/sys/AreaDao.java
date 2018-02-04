package com.foundation.dao.modules.write.sys;

import com.foundation.dao.modules.MybatisBaseDao;
import com.foundation.dao.util.TreeDao;
import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.sys.Area;

/**
 * 区域DAO接口
 * @author fqh
 * @version 2016-08-16
 */
@MyBatisRepository
public interface AreaDao extends MybatisBaseDao<String, Area> {

    /**
     * 更新所有父节点字段
     * @param entity
     * @return
     */
    public int updateParentIds(Area entity);
    /**
     * 插入数据
     * @param entity
     * @return
     */
    public int insert(Area entity);
	
}
