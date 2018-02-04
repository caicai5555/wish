package com.foundation.dao.modules.read.sys;

import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.sys.Area;
import com.foundation.dao.modules.MybatisBaseDao;

import java.util.List;
import java.util.Map;

/**
 * @Title: AreaDaoR.java
 * @Package com.foundation.dao.modules.read.sys
 * @Description: 区域读类
 * @author chunyangli(chunyangli@bioeh.com)
 * @date 2016/11/21 10:06 
 */
@MyBatisRepository
public interface AreaDaoR extends MybatisBaseDao<String, Area> {

    /**
     * @Title:
     * @Description: TODO 通过参数查询区域
     * @author chunyangli
     * @date 2016/11/2 9:49
     * @param
     * @return
     * @throws
     */
    public Area get(Map<String, Object> params);
    /**
     * @Title:
     * @Description: TODO 根据参数查询区域list
     * @author chunyangli
     * @date 2016/11/2 9:50
     * @param
     * @return
     * @throws
     */
    public List<Area> findList(Map<String, Object> params);

    /**
     * @Title:
     * @Description: TODO 查询所有的区域
     * @author chunyangli
     * @date 2016/11/2 9:53
     * @param
     * @return
     * @throws
     */
    public List<Area> findAllList();

    /**
     * @Title:
     * @Description: TODO 查询所有的区域
     * @author chunyangli
     * @date 2016/11/2 9:53
     * @param
     * @return
     * @throws
     */
    public List<Area> findAllList(Area area);
    /**
     * @Title:
     * @Description: TODO 根据参数查询区域
     * @author chunyangli
     * @date 2016/11/2 9:55
     * @param     
     * @return    
     * @throws 
     */
    public List<Area> findByParentIdsLike(Map<String, Object> params);

}
