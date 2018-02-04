package com.foundation.dao.modules.write.disease;


import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.disease.DiseaseCategory;
import com.foundation.dao.modules.MybatisBaseDao;

/**
 * @ClassName: DiseaseCategoryDaoR
 * @Description: 疾病-疾病分类-CUD
 * @author wangsen
 * @date 2016/12/2 15:11
 * @version V1.0
 */
@MyBatisRepository
public interface DiseaseCategoryDao extends MybatisBaseDao<String, DiseaseCategory> {

}