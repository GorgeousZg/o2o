package com.imooc.o2o;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * ����spring��junit����,
 * junit����ʱ����springIOC����
 * @author Gorgeous
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
//����junti spring�����ļ���λ��
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml"})
public class BaseTest {
	public BaseTest(){
		System.out.println("123");
	}
	
}