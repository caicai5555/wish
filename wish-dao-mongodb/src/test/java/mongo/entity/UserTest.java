package mongo.entity;

import java.util.Date;
import java.util.Map;

public class UserTest {
	private long id;
	private String name;
	private Map<String,UserTestSub> item;
	private Date delDate;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Map<String, UserTestSub> getItem() {
		return item;
	}
	public void setItem(Map<String, UserTestSub> item) {
		this.item = item;
	}
	public Date getDelDate() {
		return delDate;
	}
	public void setDelDate(Date delDate) {
		this.delDate = delDate;
	}
	
}
