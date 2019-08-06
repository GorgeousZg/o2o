package com.imooc.o2o.entity;

import java.util.Date;

/**
 * 微信账号
 * @author Gorgeous
 *
 */
public class WeChatAuth {
	//微信账号ID 主键ID
	private Long weChatAuthId;
	//openID 微信获取用户信息的凭证，对于某个公众号具有唯一性
	private String openId;
	//创建时间
	private Date createTime;
	//用户信息实体类
	private PersonInfo personInfo;
	//person_info表id
	private long userId;

		
	public Long getWeChatAuthId() {
		return weChatAuthId;
	}
	public void setWeChatAuthId(Long weChatAuthId) {
		this.weChatAuthId = weChatAuthId;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public PersonInfo getPersonInfo() {
		return personInfo;
	}
	public void setPersonInfo(PersonInfo personInfo) {
		this.personInfo = personInfo;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}

	
}
