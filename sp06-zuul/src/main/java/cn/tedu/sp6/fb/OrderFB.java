package cn.tedu.sp6.fb;

import cn.tedu.web.util.JsonResult;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
@Component
public class OrderFB implements FallbackProvider {

    /*
        设置当前降级类，针对哪个后台服务降级
        - item-service： 只针对商品降级
        - *：    对所有服务都应用当前降级类
        - null： 对所有服务都应用当前降级类
         */
    @Override
    public String getRoute() {
        return "item-service";
    }
    // 发回给客户端的降级响应数据
    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        return new ClientHttpResponse() {
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.INTERNAL_SERVER_ERROR;
            }
            public int getRawStatusCode() throws IOException {
                return HttpStatus.INTERNAL_SERVER_ERROR.value();
            }
            public String getStatusText() throws IOException {
                return HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
            }
            public void close() {
                // 用来关闭下面的输入流
                // ByteArrayInputStream 不占用底层系统资源，不需要关闭
            }
            public InputStream getBody() throws IOException {
                // JsonResult {code:500,msg:调用后台服务失败,data:null}
                String json =
                        JsonResult.build().code(500).msg("调用后台服务失败").toString();
                return new ByteArrayInputStream(json.getBytes("UTF-8"));
            }
            public HttpHeaders getHeaders() {
                HttpHeaders h = new HttpHeaders();
                h.add("Content-Type", "application/json;charset=UTF-8");
                return h;
            }
        };
    }
}
