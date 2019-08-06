package com.imooc.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.entity.PersonInfo;
import com.imooc.o2o.entity.WeChatAuth;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WechatAuthDaoTest extends BaseTest {
	@Autowired
	private WeChatAuthDao wechatAuthDao;

	@Test
	public void testAInsertWechatAuth() throws Exception {
		//新增一条微信账号
		WeChatAuth wechatAuth = new WeChatAuth();
		PersonInfo personInfo=new PersonInfo();
		personInfo.setUserId(1L);
		wechatAuth.setPersonInfo(personInfo);
		//随意设置上openId
		wechatAuth.setOpenId("dafahizhfdhaih");
		wechatAuth.setCreateTime(new Date());
		int effectedNum = wechatAuthDao.insertWechatAuth(wechatAuth);
		assertEquals(1, effectedNum);
	}

	@Test
	public void testBQueryWechatAuthByOpenId() throws Exception {
		WeChatAuth wechatAuth = wechatAuthDao
				.queryWechatInfoByOpenId("dafahizhfdhaih");
		assertEquals("我爱你", wechatAuth.getPersonInfo().getName());
	}

//	@Test
//	public void testDeleteWechatAuth() throws Exception {
//		WeChatAuth wechatAuth = wechatAuthDao
//				.queryWechatInfoByOpenId("dafahizhfdhaih");
//		int effectedNum = wechatAuthDao.deleteWechatAuth(wechatAuth
//				.getWeChatAuthId());
//		assertEquals(1, effectedNum);
//	}
}
