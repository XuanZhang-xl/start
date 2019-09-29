package xl.start.test.config.ws;

import com.alibaba.fastjson.JSONObject;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.HandshakeFailureException;
import org.springframework.web.socket.server.HandshakeHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Map;

/**
 * @EnableWebSocketMessageBroker enables WebSocket message handling, backed by a message broker.
 *
 *
 * @description: websocket stomp协议
 * @author: zhouweibo
 * @create: 2019/09/25
 **/
@Configuration
@EnableWebSocketMessageBroker
public class StompConfig implements WebSocketMessageBrokerConfigurer {


    /**
     * The configureMessageBroker() method implements the default method in WebSocketMessageBrokerConfigurer to configure the message broker.
     * It starts by calling enableSimpleBroker() to enable a simple memory-based message broker to carry the greeting messages back to the client on destinations prefixed with "/topic".
     * It also designates the "/app" prefix for messages that are bound for @MessageMapping-annotated methods. This prefix will be used to define all the message mappings;
     * for example, "/app/hello" is the endpoint that the GreetingController.greeting() method is mapped to handle.
     * @param config
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // 客户端订阅消息的请求前缀，topic一般用于广播推送，queue用于点对点推送
        config.enableSimpleBroker("/topic", "/queue");
        // 所有请求到这个ws的路径都要加app前缀
        config.setApplicationDestinationPrefixes("/app");
        // user级别的监听路径都要加 /user
        config.setUserDestinationPrefix("/user");
    }

    /**
     * The registerStompEndpoints() method registers the "/gs-guide-websocket" endpoint,
     * enabling SockJS fallback options so that alternate transports may be used if WebSocket is not available.
     * The SockJS client will attempt to connect to "/gs-guide-websocket" and use the best transport available (websocket, xhr-streaming, xhr-polling, etc).
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 建立一个路径为 /gs-guide-websocket 的websocket服务端
        registry.addEndpoint("/gs-guide-websocket")
                .setHandshakeHandler(new DefaultHandshakeHandler() {
                            protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
                                System.out.println("新客户端连接, 设置Principal");
                                System.out.println("原Principal: " + request.getPrincipal());
                                if (request instanceof ServletServerHttpRequest) {
                                    ServletServerHttpRequest servletServerHttpRequest = (ServletServerHttpRequest) request;
                                    HttpServletRequest httpRequest = servletServerHttpRequest.getServletRequest();
                                    final String user = httpRequest.getParameter("user");
                                    if (StringUtils.isEmpty(user)) {
                                        return null;
                                    }
                                    return new Principal() {
                                        @Override
                                        public String getName() {
                                            return user;
                                        }
                                    };
                                }
                                return null;
                            }
                })
                .withSockJS();
    }


    // 广播
    //@Override
    //public void registerStompEndpoints(StompEndpointRegistry registry) {
    //    registry.addEndpoint("/dssEndpointServiceTopic").setAllowedOrigins("*").withSockJS();
    //}

    // 端对端
    //@Override
    //public void registerStompEndpoints(StompEndpointRegistry registry) {
    //    registry.addEndpoint("/dssEndpointService") .setHandshakeHandler(new DefaultHandshakeHandler(){
    //        @Override
    //        protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
    //            //key就是服务器和客户端保持一致的标记，一般可以用账户名称，或者是用户ID。
    //            if (request instanceof ServletServerHttpRequest) {
    //                ServletServerHttpRequest servletServerHttpRequest = (ServletServerHttpRequest) request;
    //                HttpServletRequest httpRequest = servletServerHttpRequest.getServletRequest();
    //                //携带参数，可以cookie，请求头，或者url携带，这里采用url携带
    //                final String shopId = httpRequest.getParameter("shopId");
    //                if (StringUtils.isEmpty(shopId)) {
    //                    return null;
    //                }
    //                return new Principal() {
    //                    @Override
    //                    public String getName() {
    //                        return shopId;
    //                    }
    //                };
    //            }
    //            return null;
    //        }
    //    }).setAllowedOrigins("*").withSockJS();
    //}
    //
    //@Override
    //public void configureMessageBroker(MessageBrokerRegistry registry) {
    //    //订阅Broker名称 默认/topic
    //    registry.enableSimpleBroker("/queue","/topic");
    //    //全局使用的消息前缀
    //    registry.setApplicationDestinationPrefixes("/app");
    //    //点对点使用的订阅前缀（客户端订阅路径上会体现出来），不设置的话，默认也是/user/
    //    registry.setUserDestinationPrefix("/user");
    //}


}
