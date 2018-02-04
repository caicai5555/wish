package com.foundation.service.file.service.impl;

import com.foundation.common.utils.IdGen;
import com.foundation.dao.entity.file.SysFile;
import com.foundation.dao.modules.read.file.SysFileDaoR;
import com.foundation.dao.modules.write.file.SysFileDao;
import com.foundation.service.file.service.ISysFileService;
import com.sun.tools.javac.util.Assert;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author fqh
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}(用一句话描述该文件做什么)
 * @date ${date} ${time}
 */
@Service
public class SysFileServiceImpl implements ISysFileService {

    Logger logger = LoggerFactory.getLogger(SysFileServiceImpl.class);

    @Autowired
    SysFileDao sysFileDao;
    @Autowired
    SysFileDaoR sysFileDaoR;


    @Override
    public String saveFile(SysFile sysFile) throws Exception {
        String id = IdGen.uuid();
        sysFile.setId(id);
        sysFileDao.save(sysFile);
        return id;
    }

    @Override
    public SysFile queryById(String id) throws Exception {
        return sysFileDaoR.queryById(id);
    }


    @Override
    public SysFile queryObject(Map params) throws Exception {
        return sysFileDaoR.queryObject(params);
    }

    @Override
    public List<SysFile> queryList(Map params) throws Exception {
        return sysFileDaoR.queryList(params);
    }

    @Override
    public void update(SysFile sysFile) throws Exception {
        sysFileDao.update(sysFile);
    }

    @Override
    public void delete(String id) throws Exception {
        sysFileDao.delete(id);
    }
}
