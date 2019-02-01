package com.ting.user.service;

import com.ting.user.dataobject.UserInfo;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: ting
 * Date: 2018-07-30
 * Time: 下午10:15
 */

public interface UserService {
    /**
     * openid查询
     * @param openId
     * @return
     */
    UserInfo findByOpenid(String openId);

}
