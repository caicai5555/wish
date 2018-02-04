package com.foundation.service.usercenter.service;

import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.sys.Dict;

import java.util.List;
import java.util.Map;

/**
 * Created by lcy on 2016/10/18.
 */
public interface IDictService {

    public Dict get(Map<String, Object> params);
    /**
     * 查询字段类型列表
     * @return
     */
    public List<String> findTypeList();
    /**
     * 查询分页数据
     * @param page 分页对象
     * @param entity
     * @return
     */
    public Page<Dict> findPage(Page<Dict> page, Dict entity);

    public void save(Dict dict);

    public void delete(Dict dict);

    public void saveOrUpdate(Dict entity);
}
