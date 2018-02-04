package com.foundation.dao.modules.read.disease;


import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.disease.DiseaseCategory;
import com.foundation.dao.entity.disease.GeneLoci;
import com.foundation.dao.modules.MybatisBaseDao;

import java.util.List;

/**
 * @ClassName: DiseaseCategoryDaoR
 * @Description: 疾病-疾病分类-R
 * @author wangsen
 * @date 2016/12/2 15:11
 * @version V1.0
 */
@MyBatisRepository
public interface DiseaseCategoryDaoR extends MybatisBaseDao<String, DiseaseCategory> {
    /**
     * @Descrption:根据指标信息Id查询基因位点
     * @param deseaseInfoId
     * @return
     * @throws Exception
     */
    public List<GeneLoci> queryByDeseaseInfoId(String deseaseInfoId) throws Exception;

}