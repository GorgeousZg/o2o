package com.imooc.o2o.util.wechat.message.resp;

import com.imooc.o2o.util.baidu.Point;

/** 
 * 文本消息 
 * 消息基类（公众帐号 -> 普通帐号）
 * @author Gorgeous
 * @date 2019/8/6
 */  
public class TextMessage extends BaseMessage {  
    // 回复的消息内容  
    private String Content;  
    //坐标点
    private Point point;
    //openId
    private String openId;
  
    public String getContent() {  
        return Content;  
    }  
  
    public void setContent(String content) {  
        Content = content;  
    }

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}  
}  