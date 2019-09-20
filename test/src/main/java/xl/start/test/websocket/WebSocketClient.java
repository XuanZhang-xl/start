package xl.start.test.websocket;

import javax.websocket.ClientEndpoint;
import javax.websocket.ContainerProvider;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

/**
 *
 * websocket client
 * created by XUAN on 2019/9/20
 */
@ClientEndpoint
public class WebSocketClient {

    private Session session;

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("已与服务端建立连接");
        this.session = session;
    }

    @OnMessage
    public void onMessage(String message) {
        System.out.println("收到服务端发来的信息: " + message);
    }

    @OnClose
    public void onClose() {
        System.out.println("sessionId:" + session.getId() + "的客户端关闭");
    }

    // java客户端连接ws服务测试
    public static void main(String[] args) throws Exception {
        WebSocketClient client = new WebSocketClient();
        Session session = client.start();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        while(!input.equals("exit")) {
            session.getBasicRemote().sendText(input);
            input = br.readLine();
        }
        session.close();
    }
    private Session start() throws Exception {
        String uri = "ws://localhost:8082/websocket/lu";
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        URI r = URI.create(uri);
        return container.connectToServer(WebSocketClient.class, r);
    }
}
