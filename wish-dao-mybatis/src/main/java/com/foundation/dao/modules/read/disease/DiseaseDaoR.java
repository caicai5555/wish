package com.foundation.dao.modules.read.disease;

import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.disease.Disease;
import com.foundation.dao.entity.disease.DiseaseRelations;
import com.foundation.dao.modules.MybatisBaseDao;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: DiseaseDaoR
 * @Description: 疾病-疾病-R
 * @author wangsen
 * @date 2016/12/2 15:17
 * @version V1.0
 */
@MyBatisRepository
public interface DiseaseDaoR extends MybatisBaseDao<String, Disease> {

    /**
     * @Description 更新检测名字是否存在，判定条件name==,id or pid !=
     * @param params
     * @return
     * @throws Exception
     */
    public List<Disease> queryNameExist(Map<String, Object> params) throws Exception;


    /**
     * @Description 根据id级联查询关联指标
     * @param id
     * @return
     * @throws Exception
     */
    public DiseaseRelations queryIndicatorById(String id) throws Exception;
}