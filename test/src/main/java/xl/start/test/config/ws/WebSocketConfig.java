package xl.start.test.config.ws;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;
import xl.start.test.intercepter.WebSocketInterceptor;
import xl.start.test.websocket.SpringbootWebSocketEndPoint;
import xl.start.test.websocket.WebSocketEndPoint;

/**
 * 配置ServerEndpointExporter，调用setAnnotatedEndpointClasses()方法注册Websocket Endpoint
 *
 * 如果使用 WebSocketHandlerRegistry, WebSocketHandler 注册WebSocket, 则要加@EnableWebSocket
 *
 * created by XUAN on 2019/9/20
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        ServerEndpointExporter serverEndpointExporter = new ServerEndpointExporter();
        serverEndpointExporter.setAnnotatedEndpointClasses(WebSocketEndPoint.class);
        return serverEndpointExporter;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new SpringbootWebSocketEndPoint(), "/springbootwebsocket")
                .addInterceptors(new WebSocketInterceptor())
                .setAllowedOrigins("*");
    }
}
