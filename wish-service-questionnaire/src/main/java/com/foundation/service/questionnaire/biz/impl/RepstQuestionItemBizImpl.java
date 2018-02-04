package com.foundation.service.questionnaire.biz.impl;

import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.questionnaire.RepstQuestionItem;
import com.foundation.service.questionnaire.biz.IRepstQuestionItemBiz;
import com.foundation.service.questionnaire.service.IRepstQuestionItemService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: RepstQuesionnaireBizImpl
 * @Description: 试题项的业务层
 * @Author cuiyaohua
 * @Date 2016/11/23
 *
 */
@Service
public class RepstQuestionItemBizImpl implements IRepstQuestionItemBiz {

    @Autowired
    private IRepstQuestionItemService repstQuestionItemService;

    @Override
    public boolean save(RepstQuestionItem repstQuestionItem) throws Exception {
        return repstQuestionItemService.save(repstQuestionItem);
    }

    @Override
    public boolean save(List<RepstQuestionItem> repstQuestionItemList) throws Exception {
        return repstQuestionItemService.save(repstQuestionItemList);
    }

    @Override
    public boolean update(RepstQuestionItem repstQuestionItem) throws Exception {
        return repstQuestionItemService.update(repstQuestionItem);
    }

    @Override
    public RepstQuestionItem queryById(String id) throws Exception {
        return repstQuestionItemService.queryById(id);
    }

    @Override
    public Page<RepstQuestionItem> queryPage(Map<String, Object> params, Page<RepstQuestionItem> page) throws Exception {
        if (params == null) params = Maps.newHashMap();
        return repstQuestionItemService.queryPage(params, page);
    }

    @Override
    public int getCount(Map<String, Object> params) throws Exception {
        return repstQuestionItemService.getCount(params);
    }

    @Override
    public boolean deleteReal(String id) throws Exception {
        return repstQuestionItemService.deleteReal(id);
    }


}
