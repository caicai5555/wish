package com.foundation.dao.entity.commonDict;

import com.foundation.dao.util.TreeEntity;
import org.hibernate.validator.constraints.Length;

/**
 * 区域Entity
 * @author fqh
 * @version 2013-05-15
 */
public class SysArea extends TreeEntity<SysArea> {

	private static final long serialVersionUID = 1L;
//	private Area parent;	// 父级编号
//	private String parentIds; // 所有父级编号
	private String code; 	// 区域编码
//	private String name; 	// 区域名称
//	private Integer sort;		// 排序
	private String type; 	// 区域类型（1：国家；2：省份、直辖市；3：地市；4：区县）
	
	public SysArea(){
		super();
		this.sort = 30;
	}

	public SysArea(String id){
		super(id);
	}
	
//	@JsonBackReference
//	@NotNull
	public SysArea getParent() {
		return parent;
	}

	public void setParent(SysArea parent) {
		this.parent = parent;
	}
//
//	@Length(min=1, max=2000)
//	public String getParentIds() {
//		return parentIds;
//	}
//
//	public void setParentIds(String parentIds) {
//		this.parentIds = parentIds;
//	}
//	
//	@Length(min=1, max=100)
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public Integer getSort() {
//		return sort;
//	}
//
//	public void setSort(Integer sort) {
//		this.sort = sort;
//	}

	@Length(min=1, max=1)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Length(min=0, max=100)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
//
//	public String getParentId() {
//		return parent != null && parent.getId() != null ? parent.getId() : "0";
//	}


	@Override
	public String toString() {
		return "SysArea{" +
				"code='" + code + '\'' +
				", type='" + type + '\'' +
				"} " + super.toString();
	}
}