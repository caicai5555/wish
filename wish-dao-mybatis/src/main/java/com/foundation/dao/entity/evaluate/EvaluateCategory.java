package com.foundation.dao.entity.evaluate;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.foundation.dao.util.DataEntity;
/**
 * 
* @ClassName: EvaluateCategory 
* @Description: 评估分类
* @author chengchen 
* @date 2016年10月19日 上午10:20:31 
*
 */
public class EvaluateCategory  extends DataEntity<EvaluateCategory>{
	public static final String ROOT_ID = "0";
	private static final long serialVersionUID = 1L;
	/**分类名称*/
	private String name;
	/**分类英文名称*/
	private String enname;
	/**父节点id*/
	private String pid;
	/**类型*/
	private int type;
	/**父节点实体*/
	private EvaluateCategory parent;
	
	@JsonBackReference
	public EvaluateCategory getParent() {
		return parent;
	}
	public void setParent(EvaluateCategory parent) {
		this.parent = parent;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getEnname() {
		return enname;
	}
	public void setEnname(String enname) {
		this.enname = enname;
	}
	@JsonIgnore
	public static void sortList(List<EvaluateCategory> list, List<EvaluateCategory> sourcelist, String parentId){
		for (int i=0; i<sourcelist.size(); i++){
			EvaluateCategory c = sourcelist.get(i);
			if (StringUtils.isNotBlank(c.getPid())&&c.getPid().equals(parentId)){
				list.add(c);
				// 判断是否还有子节点, 有则继续获取子节点
				for (int j=0; j<sourcelist.size(); j++){
					EvaluateCategory child = sourcelist.get(j);
					if (StringUtils.isNotBlank(child.getPid())&& child.getPid().equals(c.getId())){
						sortList(list, sourcelist, c.getId());
						break;
					}
				}
			
			}
		}
	}
}
