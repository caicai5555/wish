package com.foundation.service.archive.service;

import com.foundation.common.persistence.Page;
import com.foundation.mongo.entity.archive.FamilyArchiveMG;

import java.util.Map;

/**
 * @author wangsen
 * @ClassName: IArchiveService
 * @Description: 家庭档案服务类mongo
 * @date 2016/10/28 13:04
 */
public interface IFamilyArchiveService {

    /**
     * @Description: 保存档案
     * @param archive
     * @throws Exception
     */
    public void saveArvhice(FamilyArchiveMG archive) throws Exception;


    /**
     * @Description: 查询档案by queryParams
     * @param queryParams key为FamilyArvhice的属性
     * @return
     * @throws Exception
     */
    public FamilyArchiveMG queryByParams(Map<String, Object> queryParams) throws Exception;

    /**
     * @Description: 查询档案列表页
     * @return
     * @throws Exception
     */
    public Page queryPageList(int currentPage, int pageSize, Map<String, Object> params) throws Exception;
}
