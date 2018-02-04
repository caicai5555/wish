package com.foundation.dao.modules.write.commonDict;

import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.commonDict.SysArea;
import com.foundation.dao.modules.MybatisBaseDao;

/**
 * @ClassName: DictAreaDao
 * @Description: 字典-区域->CUD
 * @author wangsen
 * @date 2016/12/14 11:39
 */
@MyBatisRepository
public interface SysAreaDao extends MybatisBaseDao<String, SysArea> {

    /**
     * 更新所有父节点字段
     * @param entity
     * @return
     */
    public int updateParentIds(SysArea entity);
    /**
     * 插入数据
     * @param entity
     * @return
     */
    public int insert(SysArea entity);
}