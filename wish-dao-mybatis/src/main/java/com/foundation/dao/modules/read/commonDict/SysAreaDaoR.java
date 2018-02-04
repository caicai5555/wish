package com.foundation.dao.modules.read.commonDict;

import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.commonDict.SysArea;
import com.foundation.dao.modules.MybatisBaseDao;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: DictAreaDaoR
 * @Description: 字典-区域->R
 * @author fqh
 * @date 2016/12/14 11:39
 */
@MyBatisRepository
public interface SysAreaDaoR extends MybatisBaseDao<String, SysArea> {

    /**
     * @Title:
     * @Description: TODO 通过参数查询区域
     * @author chunyangli
     * @date 2016/11/2 9:49
     * @param
     * @return
     * @throws
     */
    public SysArea get(Map<String, Object> params);
    /**
     * @Title:
     * @Description: TODO 根据参数查询区域list
     * @author chunyangli
     * @date 2016/11/2 9:50
     * @param
     * @return
     * @throws
     */
    public List<SysArea> findList(Map<String, Object> params);


    /**
     * @Title:
     * @Description: TODO 查询所有的区域
     * @author chunyangli
     * @date 2016/11/2 9:53
     * @param
     * @return
     * @throws
     */
    public List<SysArea> findAllList(SysArea area);
    /**
     * @Title:
     * @Description: TODO 根据参数查询区域
     * @author chunyangli
     * @date 2016/11/2 9:55
     * @param
     * @return
     * @throws
     */
    public List<SysArea> findByParentIdsLike(Map<String, Object> params);

    /**
     * 查询所有区县列表
     * @return
     */
    public List<SysArea> findAllList();
}