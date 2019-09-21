package xl.start.test.websocket;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 引入springboot后, 原生websocket配置的话已经不起作用了, 启动的时候没有调用WsServerContainer的addEndpoint()方法注册ws服务. TODO: springboot你干了什么?
 *
 * 使用springboot配置websocket, 需要将ServerEndpointExporter注册为bean, 它会调用WsServerContainer的addEndpoint()方法注册ws服务.
 *
 * 还有这里很奇怪, @Component明明默认是单例的, 这里却是多例???
 * 需要@Component 目测是因为ServerEndpointExporter需要获取这个bean, 我擦, 太坑爹了, 这样会导致WebSocketEndPoint可以被注入, 但是注入的WebSocketEndPoint是空的......
 * 有更好的方法吗?
 *
 * 更好方法:
 * 调用ServerEndpointExporter的setAnnotatedEndpointClasses()方法注册Websocket Endpoint, 这样就不用再多生成一个交给spring管理的多余的bean了, 看着也不纠结不奇怪了
 *
 * created by XUAN on 2019/09/19
 */
@ServerEndpoint("/websocket/{username}")
public class WebSocketEndPoint {

    // 存储所有endpoint连接, 直接暴露出去的话非常不安全, 这里仅用作测试
    public static Map<String, WebSocketEndPoint> clients = new ConcurrentHashMap<String, WebSocketEndPoint>();

    private static AtomicInteger onlineCount = new AtomicInteger(0);

    private Session session;

    private String username;

    @OnOpen
    public void onOpen(@PathParam("username") String username, Session session) throws IOException {
        this.username = username;
        this.session = session;
        addOnlineCount();
        clients.put(username, this);
        System.out.println("用户" + username + "已连接");
        sendMessageToSelf("恭喜连接成功!");
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() throws IOException {
        clients.remove(username);
        subOnlineCount();
        System.out.println("用户" + username + "已断开连接");
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message) throws IOException {
        System.out.println("收到用户" + username + "发来的消息: " + message);
        // echo
        sendMessageToSelf(message);
    }

    /**
     * 发生错误时调用
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("用户" + username + "发生错误:");
        error.printStackTrace();
    }

    public void sendMessageToSelf(String message) throws IOException {
        this.session.getAsyncRemote().sendText(message);
    }

    /**
     * 可以通过这个方法+mysql存储的方式来实现实时通知页面
     *
     * 状态更新: 更新mysql+实时通知页面
     * 页面刷新: 前端主动查询后查找mysql返回
     *
     * @param message
     * @param to
     * @throws IOException
     */
    public static void sendMessageToAnyone(String message, String to) throws IOException {
        WebSocketEndPoint webSocketEndPoint = clients.get(to);
        if (webSocketEndPoint == null) {
            System.out.println("无法找到用户" + to + "的session");
            return;
        }
        webSocketEndPoint.session.getBasicRemote().sendText(message);
        //webSocketEndPoint.session.getAsyncRemote().sendText(message);
    }

    public static void sendMessageAll(String message) throws IOException {
        for (WebSocketEndPoint item : clients.values()) {
            item.session.getAsyncRemote().sendText(message);
        }
    }

    public static int getOnlineCount() {
        return onlineCount.get();
    }

    private void addOnlineCount() {
        WebSocketEndPoint.onlineCount.getAndIncrement();
    }

    private void subOnlineCount() {
        WebSocketEndPoint.onlineCount.getAndDecrement();
    }

    public static Map<String, WebSocketEndPoint> getClients() {
        return clients;
    }
}
