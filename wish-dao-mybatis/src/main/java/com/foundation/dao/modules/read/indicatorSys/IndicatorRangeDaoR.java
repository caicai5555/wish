package com.foundation.dao.modules.read.indicatorSys;

import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.indicatorSys.IndicatorRange;
import com.foundation.dao.modules.MybatisBaseDao;
/**
 * @Title: IndicatorRangeDaoR
 * @Description: 指标-指标方法范围表数据持久层->R操作
 * @author SamWang(wangsammu@gmail.com)
 * @date 2016/10/14 16:39
 */
@MyBatisRepository
public interface IndicatorRangeDaoR extends MybatisBaseDao<String, IndicatorRange> {

}