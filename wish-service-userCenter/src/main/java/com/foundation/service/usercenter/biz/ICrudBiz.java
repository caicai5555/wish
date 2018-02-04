package com.foundation.service.usercenter.biz;

import com.foundation.dao.entity.sys.Dict;

import java.util.List;

/**
 * Created by lcy on 2016/10/18.
 */
public interface ICrudBiz {

   /**
    * @Title:
    * @Description: 查询字段类型列表
    * @author chunyangli
    * @date 2016/10/18 10:06
    * @param     
    * @return    
    * @throws 
    */
    public List<String> findTypeList();
    /**
     * @Title:
     * @Description: 保存字典
     * @author chunyangli
     * @date 2016/10/18 10:10
     * @param
     * @return
     * @throws
     */

    public void save(Dict dict);
    /**
     * @Title:
     * @Description: 删除字典
     * @author chunyangli
     * @date 2016/10/18 10:10
     * @param     
     * @return    
     * @throws 
     */

    public void delete(Dict dict);
}
