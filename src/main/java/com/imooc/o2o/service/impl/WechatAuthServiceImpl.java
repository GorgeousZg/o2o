package com.imooc.o2o.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imooc.o2o.dao.PersonInfoDao;
import com.imooc.o2o.dao.WeChatAuthDao;
import com.imooc.o2o.dto.ImageHolder;
import com.imooc.o2o.dto.WechatAuthExecution;
import com.imooc.o2o.entity.PersonInfo;
import com.imooc.o2o.entity.WeChatAuth;
import com.imooc.o2o.enums.WechatAuthStateEnum;
import com.imooc.o2o.service.WechatAuthService;
import com.imooc.o2o.util.FileUtil;
import com.imooc.o2o.util.ImageUtil;

@Service
public class WechatAuthServiceImpl implements WechatAuthService {
	private static Logger log = LoggerFactory
			.getLogger(WechatAuthServiceImpl.class);
	@Autowired
	private WeChatAuthDao wechatAuthDao;
	@Autowired
	private PersonInfoDao personInfoDao;

	@Override
	public WeChatAuth getWechatAuthByOpenId(String openId) {
		return wechatAuthDao.queryWechatInfoByOpenId(openId);
	}

	@Override
	@Transactional
	public WechatAuthExecution register(WeChatAuth wechatAuth,
			ImageHolder profileImg) throws RuntimeException {
		if (wechatAuth == null || wechatAuth.getOpenId() == null) {
			return new WechatAuthExecution(WechatAuthStateEnum.NULL_AUTH_INFO);
		}
		try {
			wechatAuth.setCreateTime(new Date());
			if (wechatAuth.getPersonInfo() != null
					&& wechatAuth.getPersonInfo().getUserId() == null) {
				if (profileImg != null) {
					try {
						addProfileImg(wechatAuth, profileImg);
					} catch (Exception e) {
						log.debug("addUserProfileImg error:" + e.toString());
						throw new RuntimeException("addUserProfileImg error: "
								+ e.getMessage());
					}
				}
				try {
					wechatAuth.getPersonInfo().setCreateTime(new Date());
					wechatAuth.getPersonInfo().setLastEditTime(new Date());
					wechatAuth.getPersonInfo().setCustomerFlag(1);
					wechatAuth.getPersonInfo().setShopOwnerFlag(1);
					wechatAuth.getPersonInfo().setAdminFlag(0);
					wechatAuth.getPersonInfo().setEnableStatus(1);
					PersonInfo personInfo = wechatAuth.getPersonInfo();
					int effectedNum = personInfoDao
							.insertPersonInfo(personInfo);
					wechatAuth.setPersonInfo(wechatAuth.getPersonInfo());
					if (effectedNum <= 0) {
						throw new RuntimeException("添加用户信息失败");
					}
				} catch (Exception e) {
					log.debug("insertPersonInfo error:" + e.toString());
					throw new RuntimeException("insertPersonInfo error: "
							+ e.getMessage());
				}
			}
			int effectedNum = wechatAuthDao.insertWechatAuth(wechatAuth);
			if (effectedNum <= 0) {
				throw new RuntimeException("帐号创建失败");
			} else {
				return new WechatAuthExecution(WechatAuthStateEnum.SUCCESS,
						wechatAuth);
			}
		} catch (Exception e) {
			log.debug("insertWechatAuth error:" + e.toString());
			throw new RuntimeException("insertWechatAuth error: "
					+ e.getMessage());
		}
	}

	private void addProfileImg(WeChatAuth wechatAuth,
			ImageHolder profileImg) {
		String dest = FileUtil.getPersonInfoImagePath();
		String profileImgAddr = ImageUtil.generateThumbnail(profileImg, dest);
		wechatAuth.getPersonInfo().setProfileImg(profileImgAddr);
	}




}
