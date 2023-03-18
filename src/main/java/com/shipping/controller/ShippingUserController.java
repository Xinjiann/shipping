package com.shipping.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shipping.common.exception.code.ResultStatusCode;
import com.shipping.common.lang.Result;
import com.shipping.common.utils.AudienceProperties;
import com.shipping.common.utils.JwtHelper;
import com.shipping.common.utils.RedisUtils;
import com.shipping.common.weixin.common.OpenApi;
import com.shipping.entity.ShippingUser;
import com.shipping.entity.param.ShippingLoginParam;
import com.shipping.service.ShippingUserService;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.Account;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 物流用户 前端控制器
 * </p>
 *
 * @author lixinjian
 * @since 2023-03-15
 */
@RestController
@Slf4j
@RequestMapping("/shipping-user")
public class ShippingUserController {

    @Resource
    private ShippingUserService shippingUserService;

    @Resource
    private RedisUtils redisUtils;

    @Resource
    private AudienceProperties audienceProperties;

    /**
     * 登陆/注册
     * @param loginParam
     * @return
     * @throws IOException
     */
    @PostMapping(value = "login")
    public Result login(@RequestBody ShippingLoginParam loginParam) throws IOException {
        String data = OpenApi.getWeixinData(loginParam.getJsCode());
        JSONObject jsonObj = JSONObject.parseObject(data);
        ShippingUser user = new ShippingUser();
        if (jsonObj.containsKey("session_key")) {
            log.info("================调微信成功=====================");
            String openId = jsonObj.get("openid").toString();
            QueryWrapper<ShippingUser> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("openId", openId);
            ShippingUser userInfo = shippingUserService.getOne(queryWrapper);
            if (userInfo == null) {
                user.setOpenid(openId);
                user.setName(loginParam.getName());
                user.setPhone(loginParam.getPhone());
                user.setAvatar(loginParam.getAvatar());
                user.setCreateTime(new Date());
                user.setDelete(0);
                shippingUserService.save(user);
            }
        } else {
            return Result.fail(ResultStatusCode.SYSTEM_ERR.getMsg());
        }
        //一个用户同时只能有一台设备登录（用户端）
        String redisToken = redisUtils.getToken(user.getId());
        if (StringUtils.isNotEmpty(redisToken)) {
            String HeadStr = redisToken.substring(0, 6).toLowerCase();
            if (HeadStr.equals("bearer")) {
                redisToken = redisToken.substring(6);
                Claims claims = JwtHelper.parseJWT(redisToken, audienceProperties.getBase64Secret());
                //判断密钥是否相等，如果不等则认为时无效的token
                if (claims != null) {
                    return Result.fail(ResultStatusCode.LOGINED_IN.getMsg());
                }
            }
        }
        return Result.success(redisLoginInfo(user));
    }

    private Map<String, Object> redisLoginInfo(ShippingUser user) {
        //设置单次的token的过期时间为凌晨3点-4点，用于避免token在即将失效时继续使用旧的token访问
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, +1);
        cal.set(Calendar.HOUR_OF_DAY, 3);
        //拼装accessToken
        String accessToken = JwtHelper.createJWT(user.getName(), user.getId(),
                audienceProperties.getClientId(), audienceProperties.getName(),
                cal.getTimeInMillis() - System.currentTimeMillis(), audienceProperties.getBase64Secret());
        //将该用户的access_token储存到redis服务器，保证一段时间内只能有一个有效的access_token
        redisUtils.setToken(user.getId(), accessToken, cal.getTimeInMillis() - System.currentTimeMillis());
        //获取refresh_token，有效期为7天，每次通过refresh_token获取access_token时，会刷新refresh_token的时间
        String refreshToken = JwtHelper.createRefreshToken(user.getName(), user.getId(), audienceProperties.getClientId(), audienceProperties.getName(), audienceProperties.getBase64Secret());
        redisUtils.setRefreshToken(user.getId(), refreshToken);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("access_token", "bearer" + accessToken);
        result.put("refresh_token", "bearer" + refreshToken);
        result.put("user", user);
        return result;
    }

}
