package com.ting.gateway.filter;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import com.ting.gateway.exception.RateLimiterException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * Description:限流过滤器  pre优先级要最高
 * User: ting
 * Date: 2018-07-15
 * Time: 下午10:10
 */
@Component
public class RateLimiterFilter extends ZuulFilter {

    //每秒中放入100个令牌  guava令牌桶算法
    private static final RateLimiter RATE_LIMITER = RateLimiter.create(100);

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.SERVLET_DETECTION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        //如果没有拿到令牌就返回
        if(!RATE_LIMITER.tryAcquire()){
            //抛异常返回
            throw new RateLimiterException();
        }
        return null;
    }
}
