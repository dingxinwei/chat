package com.wx.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 存储消息 离校转发
 * @author dxw
 *
 */
public class MsgCache implements Serializable{
	private String sAccount;
	private String rAccount;
	private String message;
	private Date sendTime;

	public String getsAccount() {
		return sAccount;
	}
	public void setsAccount(String sAccount) {
		this.sAccount = sAccount;
	}
	public String getrAccount() {
		return rAccount;
	}
	public void setrAccount(String rAccount) {
		this.rAccount = rAccount;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public MsgCache(String sAccount, String rAccount, String message,
			Date sendTime) {
		super();
		this.sAccount = sAccount;
		this.rAccount = rAccount;
		this.message = message;
		this.sendTime = sendTime;
	}
	public MsgCache() {
		super();
	}
	
}
