package xl.start.test.service.websocket;

import com.alibaba.fastjson.JSONObject;

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
 * 原生websocket
 * created by XUAN on 2019/09/19
 */
@ServerEndpoint("/websocket/{username}")
public class WebSocketEndPoint {

    private static AtomicInteger onlineCount = new AtomicInteger(0);
    private static Map<String, WebSocketEndPoint> clients = new ConcurrentHashMap<String, WebSocketEndPoint>();
    private Session session;
    private String username;

    @OnOpen
    public void onOpen(@PathParam("username") String username, Session session) throws IOException {
        this.username = username;
        this.session = session;
        addOnlineCount();
        clients.put(username, this);
        System.out.println("已连接");

    }

    @OnClose
    public void onClose() throws IOException {
        clients.remove(username);
        subOnlineCount();
    }

    @OnMessage
    public void onMessage(String message) throws IOException {
        JSONObject jsonTo = JSONObject.parseObject(message);
        System.out.println(jsonTo.getString("to") +":"+ jsonTo.getString("msg"));

        if (jsonTo.getString("to").toLowerCase().equals("all")){
            sendMessageAll(jsonTo.getString("msg"));
        }else{
            sendMessageTo(jsonTo.getString("msg"), jsonTo.getString("to"));
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    public void sendMessageTo(String message, String to) throws IOException {
        //WebSocketEndPoint webSocketEndPoint = clients.get(to);
        //if (webSocketEndPoint == null) {
        //    System.out.println("无法找到用户" + to + "的session");
        //    return;
        //}
        //webSocketEndPoint.session.getBasicRemote().sendText(message);
        //webSocketEndPoint.session.getAsyncRemote().sendText(message);
        this.session.getAsyncRemote().sendText(message);
    }

    public void sendMessageAll(String message) throws IOException {
        for (WebSocketEndPoint item : clients.values()) {
            item.session.getAsyncRemote().sendText(message);
        }
    }

    public static int getOnlineCount() {
        return onlineCount.get();
    }

    public static  void addOnlineCount() {
        WebSocketEndPoint.onlineCount.getAndIncrement();
    }

    public static void subOnlineCount() {
        WebSocketEndPoint.onlineCount.getAndDecrement();
    }

    public static Map<String, WebSocketEndPoint> getClients() {
        return clients;
    }
}
