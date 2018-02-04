package mongo.entity;

import java.util.Map;

public class UserTestSub {
	private long subId;
	private String subName;
	private Map<String,Object> subIndicator;
	public long getSubId() {
		return subId;
	}
	public void setSubId(long subId) {
		this.subId = subId;
	}
	public String getSubName() {
		return subName;
	}
	public void setSubName(String subName) {
		this.subName = subName;
	}
	public Map<String, Object> getSubIndicator() {
		return subIndicator;
	}
	public void setSubIndicator(Map<String, Object> subIndicator) {
		this.subIndicator = subIndicator;
	}
}
