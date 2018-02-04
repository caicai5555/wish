package com.foundation.dao.modules.read.disease;

import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.disease.GeneLoci;
import com.foundation.dao.modules.MybatisBaseDao;

import java.util.List;

/**
 * @ClassName: GeneLociDaoR
 * @Description: 疾病-基因位点-R
 * @author wangsen
 * @date 2016/12/6 19:02
 * @version V1.0
 */
@MyBatisRepository
public interface GeneLociDaoR  extends MybatisBaseDao<String, GeneLoci> {
    /**
     * @Descrption:根据指标信息Id查询基因
     * @param diseaseId
     * @return
     * @throws Exception
     */
    public List<GeneLoci> queryByDiseaseId(String diseaseId) throws Exception;

}