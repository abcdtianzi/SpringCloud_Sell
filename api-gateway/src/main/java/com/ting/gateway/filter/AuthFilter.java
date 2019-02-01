package com.ting.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.ting.gateway.constant.RedisConstant;
import com.ting.gateway.util.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * Description:鉴权过滤器
 * User: ting
 * Date: 2018-07-15
 * Time: 下午9:08
 */
@Component
public class AuthFilter extends ZuulFilter {


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 代表过滤类型
     *
     * @return
     */
    @Override
    public String filterType() {
        //前置过滤器
        return FilterConstants.PRE_TYPE;
    }

    /**
     * 代表过滤器顺序
     */
    @Override
    public int filterOrder() {
        //执行顺序，越小优先级越高
        return FilterConstants.PRE_DECORATION_FILTER_ORDER - 1;
    }

    /**
     * 代表这个过滤器是否生效
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        //请求上下文
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest httpServletRequest = requestContext.getRequest();
        /**
         * /order/create 只能买家访问（cookie有openid）
         * /order/finish 只能卖家访问（cookie有token，并且对应redis值）
         * /product/list 都可以访问
         */
        if ("/order/order/create".equals(httpServletRequest.getRequestURI())) {
            Cookie cookie = CookieUtil.get(httpServletRequest, "openid");
            if (cookie == null || StringUtils.isEmpty(cookie.getValue())) {
                //不通过
                requestContext.setSendZuulResponse(false);
                requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
            }
        }
        if ("/order/order/finish".equals(httpServletRequest.getRequestURI())) {
            Cookie cookie = CookieUtil.get(httpServletRequest, "token");
            if (cookie == null
                    || StringUtils.isEmpty(cookie.getValue())
                    || StringUtils.isEmpty(stringRedisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_TEMPLATE, cookie.getValue())))) {
                //不通过
                requestContext.setSendZuulResponse(false);
                requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
            }
        }
        return null;
    }
}
