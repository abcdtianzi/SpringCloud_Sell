package com.ting.user.service.impl;

import com.ting.user.dataobject.UserInfo;
import com.ting.user.repository.UserInfoRepostory;
import com.ting.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: ting
 * Date: 2018-07-30
 * Time: 下午10:16
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoRepostory userInfoRepostory;

    @Override
    public UserInfo findByOpenid(String openId) {
        return userInfoRepostory.findByOpenid(openId);
    }
}
