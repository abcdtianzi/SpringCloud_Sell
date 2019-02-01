package com.ting.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * Description:token过滤器
 * User: ting
 * Date: 2018-07-15
 * Time: 下午9:08
 */
@Component
public class TokenFilter extends ZuulFilter {

    /**
     * 代表过滤类型
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
        return FilterConstants.PRE_DECORATION_FILTER_ORDER -1 ;
    }

    /**
     * 代表这个过滤器是否生效
     */
    @Override
    public boolean shouldFilter() {
        return false;
    }

    @Override
    public Object run() throws ZuulException {
        //请求上下文
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest httpServletRequest = requestContext.getRequest();
        String token = httpServletRequest.getParameter("token");
        //判断token值
        if(StringUtils.isEmpty(token)){
            //不通过
            requestContext.setSendZuulResponse(false);
            //设置返回码
            requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
        }
        return null;
    }
}
