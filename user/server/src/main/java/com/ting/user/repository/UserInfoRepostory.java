package com.ting.user.repository;

import com.ting.user.dataobject.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: ting
 * Date: 2018-07-30
 * Time: 下午10:09
 */
//String是主键id
public interface UserInfoRepostory extends JpaRepository<UserInfo, String>{

    UserInfo findByOpenid(String openId);

}
