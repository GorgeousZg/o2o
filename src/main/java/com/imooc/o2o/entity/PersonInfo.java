package com.imooc.o2o.entity;

import java.util.Date;

/**
 * 用户信息
 * @author Gorgeous
 *
 */
public class PersonInfo {
	//用户id
	private Long userId;
	//姓名
	private String name;
	//头像地址
	private String profileImg;
	//邮箱
	private String email;
	//性别
	private String gender;
	//用户状态
	private Integer enableStatus;
	//用户身份标识 1:顾客 2:店家 3:超级管理员
	private Integer userType;
	//创建日期
	private Date createTime;
	//修改时间
	private Date lastEditTime;
	public Long getUserid() {
		return userId;
	}
	public void setUserid(Long userid) {
		this.userId = userid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProfileImg() {
		return profileImg;
	}
	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Integer getEnableStatus() {
		return enableStatus;
	}
	public void setEnableStatus(Integer enableStatus) {
		this.enableStatus = enableStatus;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getLastEditTime() {
		return lastEditTime;
	}
	public void setLastEditTime(Date lastEditTime) {
		this.lastEditTime = lastEditTime;
	}
	
}
