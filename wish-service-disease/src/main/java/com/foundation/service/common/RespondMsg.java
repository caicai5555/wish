package com.foundation.service.common;
/**
 * 相应json信息
 * @author Administrator
 *
 */
public class RespondMsg {
	public RespondMsg(){
		
	}
	public RespondMsg(int state,String msg,Object data) {
		 this.msg=msg;
		 this.state=state;
		 this.data=data;
	}
	
	public RespondMsg(ErrorCode code,Object data) {

		 this.msg=code.getMsg();
		 this.state=code.getCode();
		 this.data=data;
	}
	
  /*
   *请求状态
   */
  public int  state;
  /*
   *返回请求信息
   */
  public String msg;
  /**
   * 数据
   */
  public Object data;
public int getState() {
	return state;
}
public void setState(int state) {
	this.state = state;
}
public String getMsg() {
	return msg;
}
public void setMsg(String msg) {
	this.msg = msg;
}
public Object getData() {
	return data;
}
public void setData(Object data) {
	this.data = data;
}
  
}
