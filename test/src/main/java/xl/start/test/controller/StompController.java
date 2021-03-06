package xl.start.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.HtmlUtils;
import xl.start.test.vo.ResponseVo;
import xl.start.test.vo.ws.Greeting;
import xl.start.test.vo.ws.HelloMessage;

/**
 * created by XUAN on 2019/9/27
 */
@Controller
public class StompController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    /**
     * The @MessageMapping annotation ensures that if a message is sent to destination "/hello", then the greeting() method is called.
     * The payload of the message is bound to a HelloMessage object which is passed into greeting().
     *
     * The return value is broadcast to all subscribers to "/topic/greetings" as specified in the @SendTo annotation.
     * Note that the name from the input message is sanitized since in this case it will be echoed back and re-rendered in the browser DOM on the client side.
     *
     * 建立一个路径为 /app/hello 的服务端
     * 并且将返回广播到路径  /queue/greetings 中去
     * @SendTo 返回的路径不用加 /user  噗嗤......
     *
     *
     * @param message
     * @return
     * @throws Exception
     */
    @MessageMapping("/hello")
    @SendTo("/queue/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

    @RequestMapping("/stomp/all")
    public ResponseVo stompAll (String message) {
        messagingTemplate.convertAndSend("/topic/greetings", new Greeting("service echo: " + message));
        return ResponseVo.buildSuccess();
    }

    /**
     * toUser结尾的方法都会在destination前加 /user前缀
     * 所以实际前段要监听 /user/queue/greetings
     *
     * {@See SimpMessagingTemplate}
     *
     * @param user
     * @param message
     * @return
     */
    @RequestMapping("/stomp/user")
    public ResponseVo stompUser (String user, String message) {
        messagingTemplate.convertAndSendToUser(user, "/queue/greetings", new Greeting("service echo: " + message));
        return ResponseVo.buildSuccess();
    }



}
