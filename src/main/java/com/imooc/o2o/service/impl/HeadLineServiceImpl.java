package com.imooc.o2o.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.imooc.o2o.cache.JedisUtil;
import com.imooc.o2o.dao.HeadLineDao;
import com.imooc.o2o.entity.HeadLine;
import com.imooc.o2o.exceptions.HeadLineOperationException;
import com.imooc.o2o.service.HeadLineService;

@Service
public class HeadLineServiceImpl implements HeadLineService {
	@Autowired
	private JedisUtil.Strings jedisStrings;
	@Autowired
	private JedisUtil.Keys jedisKeys;
	@Autowired
	private HeadLineDao headLineDao;
	
	private Logger logger=LoggerFactory.getLogger(HeadLineServiceImpl.class);
	@Override
	public List<HeadLine> getHeadLineList(HeadLine headLineCondition)
			throws IOException {
		// 定义redie的key前缀 
		String key = HLLISTKEY;
		//定义接收对象
		List<HeadLine> headLineList = null;
		//定义jackson数据转换操作类
		ObjectMapper mapper = new ObjectMapper();
		//拼接处redis的key
		if (headLineCondition!=null&&headLineCondition.getEnableStatus() != null) {
			key = key + "_" + headLineCondition.getEnableStatus();
		}
		//判断key是否存在
		if (!jedisKeys.exists(key)) {
			// 若不存在，则从数据库里面取出相应数据
			headLineList = headLineDao.queryHeadLine(headLineCondition);
			// 将相关的实体类集合转换成string，存入redis里面对应的key中
			String jsonString;
			try{
				jsonString = mapper.writeValueAsString(headLineList);
			}catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage());
				throw new HeadLineOperationException(e.getMessage());
			}
			jedisStrings.set(key, jsonString);
		} else {
			//若存在，则直接从redis里面取出相应数据
			String jsonString = jedisStrings.get(key);
			JavaType javaType = mapper.getTypeFactory()
					.constructParametricType(ArrayList.class, HeadLine.class);
			headLineList = mapper.readValue(jsonString, javaType);
		}
		return headLineList;
	}

//	@Override
//	@Transactional
//	public HeadLineExecution addHeadLine(HeadLine headLine,
//			CommonsMultipartFile thumbnail) {
//		if (headLine != null) {
//			headLine.setCreateTime(new Date());
//			headLine.setLastEditTime(new Date());
//			if (thumbnail != null) {
//				addThumbnail(headLine, thumbnail);
//			}
//			try {
//				int effectedNum = headLineDao.insertHeadLine(headLine);
//				if (effectedNum > 0) {
//					String prefix = HLLISTKEY;
//					Set<String> keySet = jedisKeys.keys(prefix + "*");
//					for (String key : keySet) {
//						jedisKeys.del(key);
//					}
//					return new HeadLineExecution(HeadLineStateEnum.SUCCESS,
//							headLine);
//				} else {
//					return new HeadLineExecution(HeadLineStateEnum.INNER_ERROR);
//				}
//			} catch (Exception e) {
//				throw new RuntimeException("添加区域信息失败:" + e.toString());
//			}
//		} else {
//			return new HeadLineExecution(HeadLineStateEnum.EMPTY);
//		}
//	}
//
//	@Override
//	@Transactional
//	public HeadLineExecution modifyHeadLine(HeadLine headLine,
//			CommonsMultipartFile thumbnail) {
//		if (headLine.getLineId() != null && headLine.getLineId() > 0) {
//			headLine.setLastEditTime(new Date());
//			if (thumbnail != null) {
//				HeadLine tempHeadLine = headLineDao.queryHeadLineById(headLine
//						.getLineId());
//				if (tempHeadLine.getLineImg() != null) {
//					FileUtil.deleteFile(tempHeadLine.getLineImg());
//				}
//				addThumbnail(headLine, thumbnail);
//			}
//			try {
//				int effectedNum = headLineDao.updateHeadLine(headLine);
//				if (effectedNum > 0) {
//					String prefix = HLLISTKEY;
//					Set<String> keySet = jedisKeys.keys(prefix + "*");
//					for (String key : keySet) {
//						jedisKeys.del(key);
//					}
//					return new HeadLineExecution(HeadLineStateEnum.SUCCESS,
//							headLine);
//				} else {
//					return new HeadLineExecution(HeadLineStateEnum.INNER_ERROR);
//				}
//			} catch (Exception e) {
//				throw new RuntimeException("更新头条信息失败:" + e.toString());
//			}
//		} else {
//			return new HeadLineExecution(HeadLineStateEnum.EMPTY);
//		}
//	}
//
//	@Override
//	@Transactional
//	public HeadLineExecution removeHeadLine(long headLineId) {
//		if (headLineId > 0) {
//			try {
//				HeadLine tempHeadLine = headLineDao
//						.queryHeadLineById(headLineId);
//				if (tempHeadLine.getLineImg() != null) {
//					FileUtil.deleteFile(tempHeadLine.getLineImg());
//				}
//				int effectedNum = headLineDao.deleteHeadLine(headLineId);
//				if (effectedNum > 0) {
//					String prefix = HLLISTKEY;
//					Set<String> keySet = jedisKeys.keys(prefix + "*");
//					for (String key : keySet) {
//						jedisKeys.del(key);
//					}
//					return new HeadLineExecution(HeadLineStateEnum.SUCCESS);
//				} else {
//					return new HeadLineExecution(HeadLineStateEnum.INNER_ERROR);
//				}
//			} catch (Exception e) {
//				throw new RuntimeException("删除头条信息失败:" + e.toString());
//			}
//		} else {
//			return new HeadLineExecution(HeadLineStateEnum.EMPTY);
//		}
//	}
//
//	@Override
//	@Transactional
//	public HeadLineExecution removeHeadLineList(List<Long> headLineIdList) {
//		if (headLineIdList != null && headLineIdList.size() > 0) {
//			try {
//				List<HeadLine> headLineList = headLineDao
//						.queryHeadLineByIds(headLineIdList);
//				for (HeadLine headLine : headLineList) {
//					if (headLine.getLineImg() != null) {
//						FileUtil.deleteFile(headLine.getLineImg());
//					}
//				}
//				int effectedNum = headLineDao
//						.batchDeleteHeadLine(headLineIdList);
//				if (effectedNum > 0) {
//					String prefix = HLLISTKEY;
//					Set<String> keySet = jedisKeys.keys(prefix + "*");
//					for (String key : keySet) {
//						jedisKeys.del(key);
//					}
//					return new HeadLineExecution(HeadLineStateEnum.SUCCESS);
//				} else {
//					return new HeadLineExecution(HeadLineStateEnum.INNER_ERROR);
//				}
//			} catch (Exception e) {
//				throw new RuntimeException("删除头条信息失败:" + e.toString());
//			}
//		} else {
//			return new HeadLineExecution(HeadLineStateEnum.EMPTY);
//		}
//	}
//
//	private void addThumbnail(HeadLine headLine, CommonsMultipartFile thumbnail) {
//		String dest = FileUtil.getHeadLineImagePath();
//		String thumbnailAddr = ImageUtil.generateNormalImg(thumbnail, dest);
//		headLine.setLineImg(thumbnailAddr);
//	}

}
