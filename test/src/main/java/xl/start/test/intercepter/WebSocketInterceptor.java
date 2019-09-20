package xl.start.test.intercepter;


import com.alibaba.fastjson.JSON;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import java.util.Map;

/**
 * created by XUAN on 2019/9/20
 */
public class WebSocketInterceptor extends HttpSessionHandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        System.out.println("执行了ws拦截器的beforeHandshake()方法");
        System.out.println(JSON.toJSONString(attributes));
        return super.beforeHandshake(request, response, wsHandler, attributes);
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
        System.out.println("执行了ws拦截器的afterHandshake()方法");
        super.afterHandshake(request, response, wsHandler, exception);
    }
}
