package com.imooc.o2o.dao;

import com.imooc.o2o.entity.WeChatAuth;

public interface WeChatAuthDao {
	/**
	 * 通过openId查询对应本平台的微信账号
	 * @param openId
	 * @return
	 */
	WeChatAuth queryWechatInfoByOpenId(String openId);

	/**
	 * 添加对应平台的微信账号
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
