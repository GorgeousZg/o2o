package com.imooc.o2o.service;

import com.imooc.o2o.dto.ImageHolder;
import com.imooc.o2o.dto.WechatAuthExecution;
import com.imooc.o2o.entity.WeChatAuth;
import com.imooc.o2o.exceptions.WeChatAuthOperationException;

public interface WechatAuthService {

	/**
	 * 通过openId查找平台对应的微信账号
	 * @param openId
	 * @return
	 */
	WeChatAuth getWechatAuthByOpenId(String openId);

	/**
	 * 注册本平台的微信账号
	 * @param wechatAuth
	 * @param profileImg
	 * @return
	 * @throws RuntimeException
	 */
	WechatAuthExecution register(WeChatAuth wechatAuth) throws WeChatAuthOperationException;

}
