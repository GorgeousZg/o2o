package com.imooc.o2o.web.wechat;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.imooc.o2o.service.PersonInfoService;
import com.imooc.o2o.service.ShopService;
import com.imooc.o2o.service.WechatAuthService;
import com.imooc.o2o.util.wechat.WeChatUser;
import com.imooc.o2o.util.wechat.WeChatUtil;
import com.imooc.o2o.util.wechat.message.pojo.UserAccessToken;

@Controller
@RequestMapping("wechatlogin")
/**
 * 获取关注公众号之后的微信用户信息的接口，如果在微信浏览器里访问
 * https://open.weixin.qq.com/connect/oauth2/authorize?appid=您的appId&redirect_uri=http://o2o.yitiaojieinfo.com/o2o/wechatlogin/logincheck&role_type=1&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect
 * 则这里将会获取到code,之后再可以通过code获取到access_token 进而获取到用户信息
 * 
 * @author Gorgeous
 *
 */
public class WechatLoginController {

	private static Logger log = LoggerFactory
			.getLogger(WechatLoginController.class);

	@Resource
	private PersonInfoService personInfoService;
	@Resource
	private WechatAuthService WechatAuthService;

	@Resource
	private ShopService shopService;



	private static final String FRONTEND = "1";
	private static final String SHOPEND = "2";

	@RequestMapping(value = "/logincheck", method = { RequestMethod.GET })
    public String doGet(HttpServletRequest request, HttpServletResponse response) {
        log.debug("weixin login get...");
        
        // 获取微信公众号传输过来的code,通过code可获取access_token,进而获取用户信息
        String code = request.getParameter("code");
        System.out.println("code->"+code);
        // 这个state可以用来传我们自定义的信息，方便程序调用，这里也可以不用
        // String roleType = request.getParameter("state");
        log.debug("weixin login code:" + code);
        WeChatUser user = null;
        String openId = null;
        if (null != code) {
            UserAccessToken token;
            try {
                // 通过code获取access_token
                token = WeChatUtil.getUserAccessToken(code);
                log.debug("weixin login token:" + token.toString());
                // 通过token获取accessToken
                String accessToken = token.getAccess_token();
                // 通过token获取openId
                openId = token.getOpenid();
                // 通过access_token和openId获取用户昵称等信息
                user = WeChatUtil.getUserInfo(accessToken, openId);
                log.debug("weixin login user:" + user.toString());
                request.getSession().setAttribute("openId", openId);
            } catch (IOException e) {
                log.error("error in getUserAccessToken or getUserInfo or findByOpenId: " + e.toString());
                e.printStackTrace();
            }
        }
        // ======todo begin======
        // 前面咱们获取到openId后，可以通过它去数据库判断该微信帐号是否在我们网站里有对应的帐号了，
        // 没有的话这里可以自动创建上，直接实现微信与咱们网站的无缝对接。
        // ======todo end======
        if (user != null) {
            // 获取到微信验证的信息后返回到指定的路由（需要自己设定）
            return "frontend/index";
        } else {
            return null;
        }
    }
}
