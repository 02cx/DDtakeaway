package com.sky.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sky.constant.MessageConstant;
import com.sky.dto.UserLoginDTO;
import com.sky.entity.User;
import com.sky.exception.LoginFailedException;
import com.sky.mapper.UserMapper;
import com.sky.properties.WeChatProperties;
import com.sky.service.UserService;
import com.sky.utils.HttpClientUtil;
import com.sky.vo.UserLoginVO;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private static final  String WX_LOGIN = "https://api.weixin.qq.com/sns/jscode2session";

    @Resource
    private UserMapper userMapper;

    @Resource
    private WeChatProperties weChatProperties;

    /**
     * C端用户登录-微信登陆
     * @param userLoginDTO
     * @return
     */
    @Override
    public User wxLogin(UserLoginDTO userLoginDTO) {
        // 授权码
        String code = userLoginDTO.getCode();
        String openid = getOpenid(code);

        if(openid == null){
            throw new LoginFailedException(MessageConstant.LOGIN_FAILED);
        }

        // 根据openid查询用户信息
        User user = userMapper.getByOpenid(openid);

        if(user == null){
            // 当前是一个新用户，自动完成注册
            user = new User();
            user.setOpenid(openid);
            user.setCreateTime(LocalDateTime.now());
            userMapper.insert(user);
        }

        return user;
    }

    /**
     *  获取微信用户的openid
     * @param code
     * @return
     */
    public String getOpenid(String code){
        // 封装请求参数
        HashMap map = new HashMap();
        map.put("appid",weChatProperties.getAppid());
        map.put("secret",weChatProperties.getSecret());
        map.put("js_code",code);
        map.put("grant_type","authorization_code");

        // 调用工具类，像微信接口服务发送请求
        String json = HttpClientUtil.doGet(WX_LOGIN, map);
        JSONObject jsonObject = JSON.parseObject(json);
        String openid = jsonObject.getString("openid");
        return openid;
    }
}
