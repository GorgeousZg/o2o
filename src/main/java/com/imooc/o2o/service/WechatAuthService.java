package com.imooc.o2o.service;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.imooc.o2o.dto.ImageHolder;
import com.imooc.o2o.dto.WechatAuthExecution;
import com.imooc.o2o.entity.WeChatAuth;

public interface WechatAuthService {

	/**
	 * 
	 * @param openId
	 * @return
	 */
	WeChatAuth getWechatAuthByOpenId(String openId);

	/**
	 * 
	 * @param wechatAuth
	 * @param profileImg
	 * @return
	 * @throws RuntimeException
	 */
	WechatAuthExecution register(WeChatAuth wechatAuth,
			ImageHolder profileImg) throws RuntimeException;

}
