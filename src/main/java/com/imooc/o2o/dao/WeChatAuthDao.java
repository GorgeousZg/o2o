package com.imooc.o2o.dao;

import com.imooc.o2o.entity.WeChatAuth;

public interface WeChatAuthDao {
	/**
	 * 
	 * @param openId
	 * @return
	 */
	WeChatAuth queryWechatInfoByOpenId(String openId);

	/**
	 * 
	 * @param wechatAuth
	 * @return
	 */
	int insertWechatAuth(WeChatAuth wechatAuth);

	/**
	 * 
	 * @param wechatAuthId
	 * @return
	 */
	int deleteWechatAuth(Long wechatAuthId);
}
