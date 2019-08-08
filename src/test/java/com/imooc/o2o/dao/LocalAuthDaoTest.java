package com.imooc.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.entity.LocalAuth;
import com.imooc.o2o.entity.PersonInfo;

public class LocalAuthDaoTest extends BaseTest {
	@Autowired
	private LocalAuthDao localAuthDao;
	private static final String username = "testusername";
	private static final String password = "testpassword";
	@Test
	public void testAInsertLocalAuth() throws Exception{
		//新增一条平台账号信息
		LocalAuth localAuth=new LocalAuth();
		PersonInfo personInfo=new PersonInfo();
		personInfo.setUserId(18L);
		//给平台账号绑定上用户信息
		localAuth.setPersonInfo(personInfo);
		//设置上用户名和密码
		localAuth.setUsername(username);
		localAuth.setPassword(password);
		localAuth.setCreatetime(new Date());
		int effectedNum=localAuthDao.insertLocalAuth(localAuth);
		assertEquals(1,effectedNum);
	}
	@Test
	public void testBQueryLocalByUserNameAndPwd() throws Exception{
		//按照账号和密码查询用户信息
		LocalAuth localAuth=localAuthDao.queryLocalByUserNameAndPwd(username, password);
		assertEquals("测试",localAuth.getPersonInfo().getName());
	}
	@Test
	public void testCQueryLocalByUserId() throws Exception{
		//按照用户Id查询平台账号，进而获取用户信息
		LocalAuth localAuth=localAuthDao.queryLocalByUserId(18L);
		assertEquals("测试",localAuth.getPersonInfo().getName());
	}

}
