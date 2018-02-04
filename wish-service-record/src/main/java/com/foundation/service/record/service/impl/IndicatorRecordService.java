package com.foundation.service.record.service.impl;

import com.foundation.mongo.entity.archive.Archive;
import com.foundation.mongo.entity.archive.FamilyArchiveMG;
import com.foundation.mongo.entity.record.BaseRecordEntity;
import com.foundation.mongo.entity.record.FamilyConcIndicator;
import com.foundation.service.archive.service.impl.ArchiveService;
import com.foundation.service.archive.service.impl.FamilyArchiveService;
import com.foundation.service.common.IndicatorType;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.foundation.service.record.service.IIndicatorRecordService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangsen
 * @ClassName: IIndicatorRecordService
 * @Description: 指标项公共查服务
 * @date 2016/10/18 17:04
 */
@Service
public class IndicatorRecordService extends BaseRecordServiceImpl implements IIndicatorRecordService {
    private Log logger = LogFactory.getLog(IndicatorRecordService.class);
    @Autowired
    ArchiveService archiveService;

    @Autowired
    FamilyArchiveService familyArchiveService;

    /**
     * @param recordEntity
     * @param archiveFlag  归档标识,true：归档对应数据库状态1；
     *                     false:不归档对应数据库状态0；
     * @return
     * @throws Exception
     * @Description: 保存指标记录
     */
    public boolean saveIndicator(BaseRecordEntity recordEntity, boolean archiveFlag) throws Exception {

        try {
            //插入指标记录
            boolean recordFlag = this.saveRecord(recordEntity);
            if (recordFlag && archiveFlag) {

                Archive archive = new Archive();
                archive.setUserId(recordEntity.getUserId());
                archive.setCertNum(recordEntity.getCertNum());
                archive.setEvent(recordEntity.getEvent());
                archive.setSource(recordEntity.getSource());
                archive.setDateStr(recordEntity.getDateStr());
                archive.setUpdateTime(recordEntity.getUpdateTime());

                //根据class类型获取二级key值
                String indicatorType = toLowerFirstStr(recordEntity.getClass().getSimpleName());

                //设置更新新二级document->key
                Map<String, BaseRecordEntity> value = new HashMap<>();
                value.put(indicatorType + "." + recordEntity.getCode(), recordEntity);
                archive.setDynamicValue(value);
                //BeanUtils.setProperty(archive, indicatorType, value);

                // BeanUtils.setProperty(archive,indicatorType +".$."+recordEntity.getCode(),value);

                //校验必要参数
                if (archive.validate()) {
                    archiveService.saveArvhice(archive);
                } else {
                    return false;
                }
            }else if(recordFlag==false){
                throw new Exception("保持mongd指标出错recordEntity："+recordEntity.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("error:::[IndicatorRecordService.saveRecord saveIndicator(BaseRecordEntity recordEntity, boolean archiveFlag)]," + e);
        }
        return true;
    }

    /**
     * @param recordEntity
     * @param archiveFlag
     * @return
     * @throws Exception
     * @Description: 保家庭指标记录存指标记录，归档到家庭档案
     */
    public boolean saveFamilyIndicator(FamilyConcIndicator recordEntity, boolean archiveFlag) throws Exception {
        //插入指标记录
        boolean recordFlag = this.saveRecord(recordEntity);
        if (recordFlag && archiveFlag) {
            FamilyArchiveMG familyArchive = new FamilyArchiveMG();
            BeanUtils.copyProperties(familyArchive, recordEntity);
            //根据class类型获取二级key值
            //String indicatorType = toLowerFirstStr(recordEntity.getClass().getSimpleName());

            //设置更新新二级document->key
            Map<String, FamilyConcIndicator> value = new HashMap<>();
            value.put(/*indicatorType*/ IndicatorType.CONCINDICATOR.getType() + "." + recordEntity.getCode(), recordEntity);
            familyArchive.setFamilyConcIndicator(value);

            familyArchiveService.saveArvhice(familyArchive);
        }
        return false;
    }

    /**
     * @param recordEntitys
     * @param archiveFlag   归档标识,true：归档对应数据库状态1；
     *                      false:不归档对应数据库状态0；
     * @return
     * @throws Exception
     * @Description: 保存指标记录
     */
    public boolean saveIndicators(Map<String, BaseRecordEntity> recordEntitys, boolean archiveFlag) throws Exception {
        for (Map.Entry<String, BaseRecordEntity> baseRecordEntity : recordEntitys.entrySet()) {
            this.saveIndicator(baseRecordEntity.getValue(), archiveFlag);
        }
        return true;
    }


    /**
     * @param str
     * @return
     * @Description 首字母转小写
     */
    private String toLowerFirstStr(String str) {
        if (Character.isLowerCase(str.charAt(0))) return str;
        char[] chars = str.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }
}
