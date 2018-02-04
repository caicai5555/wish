package com.foundation.service.usercenter.service;

import com.foundation.common.cache.CacheUtils;
import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.sys.Dict;
import com.foundation.service.usercenter.DictUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lcy on 2016/10/18.
 */
public interface ICrudService {

    /**
     * 查询字段类型列表
     * @return
     */
    public List<String> findTypeList();

    public void save(Dict dict);

    public void delete(Dict dict);
}
