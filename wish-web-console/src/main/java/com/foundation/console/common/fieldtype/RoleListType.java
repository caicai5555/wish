package com.foundation.console.common.fieldtype;

import java.util.List;

import com.foundation.common.utils.Collections3;
import com.foundation.common.utils.SpringContextHolder;
import com.foundation.common.utils.StringUtils;
import com.foundation.dao.entity.sys.Role;
import com.google.common.collect.Lists;
import com.foundation.service.usercenter.service.impl.SystemServiceImpl;

/**
 * 字段类型转换
 * @author fqh
 * @version 2013-5-29
 */
public class RoleListType {

	private static SystemServiceImpl systemService = SpringContextHolder.getBean(SystemServiceImpl.class);
	
	/**
	 * 获取对象值（导入）
	 */
	public static Object getValue(String val) {
		List<Role> roleList = Lists.newArrayList();
		List<Role> allRoleList = systemService.findAllRole();
		for (String s : StringUtils.split(val, ",")){
			for (Role e : allRoleList){
				if (StringUtils.trimToEmpty(s).equals(e.getName())){
					roleList.add(e);
				}
			}
		}
		return roleList.size()>0?roleList:null;
	}

	/**
	 * 设置对象值（导出）
	 */
	public static String setValue(Object val) {
		if (val != null){
			@SuppressWarnings("unchecked")
			List<Role> roleList = (List<Role>)val;
			return Collections3.extractToString(roleList, "name", ", ");
		}
		return "";
	}
	
}
