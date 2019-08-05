package com.imooc.o2o.dto;

import java.util.List;

import com.imooc.o2o.entity.WeChatAuth;
import com.imooc.o2o.enums.WechatAuthStateEnum;


public class WechatAuthExecution {
	// 结果状态
	private int state;

	// 状态标识
	private String stateInfo;

	private int count;

	private WeChatAuth wechatAuth;

	private List<WeChatAuth> wechatAuthList;

	public WechatAuthExecution() {
	}

	// 失败的构造器
	public WechatAuthExecution(WechatAuthStateEnum stateEnum) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}

	// 成功的构造器
	public WechatAuthExecution(WechatAuthStateEnum stateEnum, WeChatAuth wechatAuth) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.wechatAuth = wechatAuth;
	}

	// 成功的构造器
	public WechatAuthExecution(WechatAuthStateEnum stateEnum,
			List<WeChatAuth> wechatAuthList) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.wechatAuthList = wechatAuthList;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public WeChatAuth getWechatAuth() {
		return wechatAuth;
	}

	public void setWechatAuth(WeChatAuth wechatAuth) {
		this.wechatAuth = wechatAuth;
	}

	public List<WeChatAuth> getWechatAuthList() {
		return wechatAuthList;
	}

	public void setWechatAuthList(List<WeChatAuth> wechatAuthList) {
		this.wechatAuthList = wechatAuthList;
	}

}
