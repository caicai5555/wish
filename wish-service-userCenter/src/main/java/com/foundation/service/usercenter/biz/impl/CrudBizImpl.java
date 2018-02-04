package com.foundation.service.usercenter.biz.impl;

import com.foundation.dao.entity.sys.Dict;
import com.foundation.service.usercenter.biz.ICrudBiz;
import com.foundation.service.usercenter.service.ICrudService;
import com.foundation.service.usercenter.service.IDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lcy on 2016/10/18.
 */
@Service
public class CrudBizImpl implements ICrudBiz{
 @Autowired(required = false)
 private IDictService dictService;

 /**
  * @return
  * @throws
  * @Title:
  * @Description: 查询字段类型列表
  * @author chunyangli
  * @date 2016/10/18 10:06
  */
 @Override
 public List<String> findTypeList() {
  return dictService.findTypeList();
 }

 /**
  * @param dict
  * @return
  * @throws
  * @Title:
  * @Description: 保存字典
  * @author chunyangli
  * @date 2016/10/18 10:10
  */
 @Override
 public void save(Dict dict) {
  dictService.save(dict);

 }

 /**
  * @param dict
  * @return
  * @throws
  * @Title:
  * @Description: 删除字典
  * @author chunyangli
  * @date 2016/10/18 10:10
  */
 @Override
 public void delete(Dict dict) {
  dictService.delete(dict);

 }
}
