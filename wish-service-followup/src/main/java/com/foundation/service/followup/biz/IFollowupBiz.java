package com.foundation.service.followup.biz;

import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.followup.Followup;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: IFollowupBiz
 * @Description: 随访诊断的业务层
 * @Author cuiyaohua
 * @Date 2016/11/23
 *
 */
public interface IFollowupBiz {
    /**
     * @Title: save
     * @Description: 保存随访诊断
     * @Author cuiyaohua
     * @Date 2016-11-23 17:26
     * @Param followup 参数
     * @Return boolean 返回类型
     * @Throws exception
     */
    boolean save(Followup followup) throws Exception;

    /**
     * @Title: save
     * @Description: 保存随访诊断
     * @Author cuiyaohua
     * @Date 2016-11-23 17:26
     * @Param followupList 参数
     * @Return boolean 返回类型
     * @Throws exception
     */
    boolean save(List<Followup> followupList) throws Exception;

    /**
     * @Title: update
     * @Description: 修改整个随访诊断
     * @Author cuiyaohua
     * @Date 2016-11-23 17:27
     * @Param followup 参数
     * @Return boolean 返回类型
     * @Throws exception
     */
    boolean update(Followup followup) throws Exception;

    /**
     * @Title: queryById
     * @Description: 通过id获取随访诊断
     * @Author cuiyaohua
     * @Date 2016-11-23 17:28
     * @Param id 参数
     * @Return followup 返回类型
     * @Throws exception
     */
    Followup queryById(String id) throws Exception;

    /**
     * @Title: queryPage
     * @Description: 获取随访诊断分页信息
     * @Author cuiyaohua
     * @Date 2016-11-23 17:19
     * @Param params 查询字段条件
     * @Param page 分页条件
     * @Return Page<Followup> 返回类型
     * @Throws exception
     */
    Page<Followup> queryPage(Map<String, Object> params, Page<Followup> page) throws Exception;

    /**
     * @Title: getCount
     * @Description: 获取符合条件的记录数
     * @Author cuiyaohua
     * @Date 2016-11-23 17:21
     * @Param params 查询条件
     * @Return int 返回类型
     * @Throws exception
     */
    int getCount(Map<String, Object> params) throws Exception;

    /**
     * @Title: deleteReal
     * @Description: 物理删除随访诊断
     * @Author cuiyaohua
     * @Date 2016-11-23 17:22
     * @Param id 参数
     * @Return boolean 返回类型
     * @Throws exception
     */
    boolean deleteReal(String id) throws Exception;
}
