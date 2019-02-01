package com.ting.gateway.util;

import org.apache.commons.lang.ObjectUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: ting
 * Date: 2018-07-30
 * Time: 下午11:26
 */
public class CookieUtil {

    public static final void set(HttpServletResponse response, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAge);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public static final Cookie get(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(ObjectUtils.equals(cookie.getName(), name)){
                    return cookie;
                }
            }
        }
        return null;
    }


}
