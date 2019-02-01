package com.ting.user.controller;

import com.ting.user.constant.CookieConstant;
import com.ting.user.constant.RedisConstant;
import com.ting.user.dataobject.UserInfo;
import com.ting.user.enums.ResultEnum;
import com.ting.user.enums.RoleEnum;
import com.ting.user.service.UserService;
import com.ting.user.utils.CookieUtil;
import com.ting.user.utils.ResultVoUtil;
import com.ting.user.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: ting
 * Date: 2018-07-30
 * Time: 下午10:25
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/buyer")
    public ResultVo buyer(@RequestParam("openid") String openid, HttpServletResponse response) {
        //判断是否登录


        //1.openid和数据库数据匹配
        UserInfo userInfo = userService.findByOpenid(openid);
        if (userInfo == null) {
            return ResultVoUtil.error(ResultEnum.LOGIN_ERROR);
        }
        //2.判断角色
        if (RoleEnum.BUYER.getCode() != userInfo.getRole()) {
            return ResultVoUtil.error(ResultEnum.ROLE_ERROR);
        }

        //3.设置cookie的openid
        CookieUtil.set(response, CookieConstant.Openid, openid, CookieConstant.expire);

        return ResultVoUtil.success();

    }

    @RequestMapping("/seller")
    public ResultVo seller(@RequestParam("openid") String openid, HttpServletRequest request, HttpServletResponse response) {

        //判断是否登录
        Cookie cookie = CookieUtil.get(request, CookieConstant.Token);
        if (cookie != null &&
                !StringUtils.isEmpty(stringRedisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_TEMPLATE, cookie.getValue())))) {
            return ResultVoUtil.success();
        }

        //1.openid和数据库数据匹配
        UserInfo userInfo = userService.findByOpenid(openid);
        if (userInfo == null) {
            return ResultVoUtil.error(ResultEnum.LOGIN_ERROR);
        }
        //2.判断角色
        if (RoleEnum.SELLER.getCode() != userInfo.getRole()) {
            return ResultVoUtil.error(ResultEnum.ROLE_ERROR);
        }

        //3.redis 写入ket=token_uuid value=openid
        String token = UUID.randomUUID().toString();
        Integer expire = CookieConstant.expire;
        stringRedisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_TEMPLATE, token),
                openid,
                expire,
                TimeUnit.SECONDS);

        //3.设置cookie的openid
        CookieUtil.set(response, CookieConstant.Token, token, CookieConstant.expire);

        return ResultVoUtil.success();
    }
}
