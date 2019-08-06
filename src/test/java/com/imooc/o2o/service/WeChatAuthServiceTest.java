package com.imooc.o2o.service;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.dto.WechatAuthExecution;
import com.imooc.o2o.entity.PersonInfo;
import com.imooc.o2o.entity.WeChatAuth;
import com.imooc.o2o.enums.WechatAuthStateEnum;

public class WeChatAuthServiceTest extends BaseTest {
	@Autowired
	private WechatAuthService wechatAuthService;
	@Test
	public void testRegister(){
		//新增一条微信账号
		WeChatAuth weChatAuth=new WeChatAuth();
		PersonInfo personInfo=new PersonInfo();
		String openId="dafahizhfdhaih";
		//给账号设置上用户信息，但不设置上用户Id
		//希望创建微信账号的时候自动创建用户信息
		personInfo.setCreateTime(new Date());
		personInfo.setName("测试一下");
		personInfo.setUserType(1);
		weChatAuth.setPersonInfo(personInfo);
		weChatAuth.setOpenId(openId);
		weChatAuth.setCreateTime(new Date());
		WechatAuthExecution wae=wechatAuthService.register(weChatAuth);
		assertEquals(WechatAuthStateEnum.SUCCESS.getState(),wae.getState());
		System.out.println("openId-->"+openId);
		//通过openId找到新增的wechatAuth
		weChatAuth=wechatAuthService.getWechatAuthByOpenId(openId);
		//打印用户名字看看跟预期是否相符
		System.out.println(weChatAuth.getPersonInfo().getName());
	}
}
