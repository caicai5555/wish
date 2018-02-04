package com.foundation.service.dist.service;

import com.foundation.dao.entity.archive.DistArchiveDescindicator;

import java.util.List;
import java.util.Map;

/**
 * @author wangsen
 * @ClassName: IDistArchiveIndexService
 * @Description: 档案-描述性指标项字典服务
 * @date 2016/10/13 12:56
 */
public interface IDistArchiveDescindicatorService {

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
     * @Description: 动态参数查询数量
     * @param params
     * @return
     * @throws Exception
     */
    public Integer getCount(Map<String, Object> params)throws Exception;

    /**
     * @Description: 根据入参map查询列表
     * @param params
     * @return
     * @throws Exception
     */
    public List<DistArchiveDescindicator> queryList(Map<String, Object> params)throws Exception;

    /**
     * @Description: 根据疾病Id级联查询列表
     * @param diseaseId
     * @return
     * @throws Exception
     */
    public List<DistArchiveDescindicator> queryByDiseaseId(String diseaseId)throws Exception;
}
