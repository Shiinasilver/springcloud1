package cn.tedu.filter;

import cn.tedu.web.util.JsonResult;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;


import javax.servlet.http.HttpServletRequest;

@Slf4j
@Component
public class AccessFilter extends ZuulFilter {
    // 设置过滤器的类型：pre, routing, post, error
    // zuul自动配置的回调方法
    @Override
    public String filterType() {
        // return "pre";
        log.info("Zuul自动配置过滤器类型");
        return FilterConstants.PRE_TYPE;
    }
    // 过滤器的顺序号
    @Override
    public int filterOrder() {
        /*
        在默认的第5个过滤器中，向上下文对象放入了 serviceId，
        后面过滤器中才能访问这个数据
         */
        log.info("Zuul自动配置过滤器的顺序号");
        return 6;
    }

    @Override
    public boolean shouldFilter() {
        //如果客户端调用商品 要判断权限
        //否则调用用户或订单 不判断泉下
        //获得当前请求的上下文对象
        RequestContext ctx = RequestContext.getCurrentContext();
        //从上下文对象去出正在访问的serviceId
        String serviceId = (String) ctx.get(FilterConstants.SERVICE_ID_KEY);
        //判断serviceid是否是“item—service“
        return "item-service".equalsIgnoreCase(serviceId);
    }

    @Override
    public Object run() throws ZuulException {
       //获得上下文对象
        RequestContext ctx = RequestContext.getCurrentContext();
        //从上下文对象取出request对象
        HttpServletRequest request = ctx.getRequest();
        //用request接受token对象
        String token = request.getParameter("token");
        //如果token不存在
        if (StringUtils.isBlank(token)){
            //阻止继续访问
            ctx.setSendZuulResponse(false);
            //直接返回响应:JsonResult {code:500,msg:"xxx",data:null}
            String json = JsonResult.build().code(500).msg("FUCK YOU").toString();
            ctx.addZuulResponseHeader("Content-Type", "application/json;charset=UTF-8");
            ctx.setResponseBody(json);
        }
            return null;//zuul当前版本没有返回值
    }
}
