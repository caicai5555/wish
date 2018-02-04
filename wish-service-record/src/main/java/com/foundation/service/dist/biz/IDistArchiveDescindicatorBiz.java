package com.foundation.service.dist.biz;

import com.foundation.dao.entity.archive.DistArchiveDescindicator;

import java.util.List;
import java.util.Map;

/**
 * @author wangsen
 * @ClassName: IDistArchiveIndexBiz
 * @Description: 档案-描述性指标字典表业务类
 * @date 2016/10/13 13:33
 */
public interface IDistArchiveDescindicatorBiz {

    /**
     * @Description: 保存结论项实体
     * @param distArchiveDescindicator
     * @return
     * @throws Exception
     */
    public DistArchiveDescindicator save(DistArchiveDescindicator distArchiveDescindicator) throws Exception;

    /**
     * @Description: 根据主键查询实体
     * @return
     * @throws Exception
     */
    public DistArchiveDescindicator queryById(String id) throws Exception;

    /**
     * @Description: 根据编码查询实体
     * @return
     * @throws Exception
     */
    public DistArchiveDescindicator queryByCode(String code) throws  Exception;

    /**
     * @Description: 根据id删除实体（逻辑删除）
     * @param id
     * @return
     * @throws Exception
     */
    public Integer deleteIndexById(String id) throws Exception;

    /**
     * @Description: 根据主键更新实体
     * @param distArchiveDescindicator
     * @return
     * @throws Exception
     */
    public  void updateById(DistArchiveDescindicator distArchiveDescindicator)throws Exception;

    /**
     * @Description: 根据入参map查询列表
     * @param params
     * @return
     * @throws Exception
     */
    public List<DistArchiveDescindicator> queryList(Map<String,Object> params)throws Exception;

}
