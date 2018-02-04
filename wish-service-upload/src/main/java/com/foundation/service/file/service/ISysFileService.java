package com.foundation.service.file.service;

import com.foundation.dao.entity.file.SysFile;

import java.util.List;
import java.util.Map;

/**
 * @author wangsen
 * @ClassName: ISysFileService
 * @Description: 文件操作服务
 * @date 2016/11/24 17:04
 */
public interface ISysFileService {

    /**
     * 保存文件
     * @return
     * @throws Exception
     */
    public String saveFile(SysFile sysFile) throws Exception;

    /**
     * 根据id查询文件
     * @param id
     * @return
     * @throws Exception
     */
    public SysFile queryById(String id) throws Exception;

    /**
     * 根据params查询文件
     * @param params
     * @return
     * @throws Exception
     */
    public SysFile queryObject(Map params) throws Exception;

    /**
     * 根据条件根据文件列表
     * @param params
     * @return
     * @throws Exception
     */
    public List<SysFile> queryList(Map params) throws Exception;


    /**
     * 更新
     * @param sysFile
     * @throws Exception
     */
    public void update(SysFile sysFile) throws Exception;

    /**
     * 删除
     * @param id
     * @throws Exception
     */
    public void delete(String id) throws Exception;

}
