package xl.start.test.config.zk;

import org.apache.zookeeper.Watcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import xl.start.test.config.zk.eventhandler.ZookeeperEventHandler;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by gqwu on 2018/4/19.
 */
@Component
public class ZKEventHandlerFactory {
    private static final Logger logger = LoggerFactory.getLogger(ZKEventHandlerFactory.class);

    @Autowired
    private ApplicationContext springContext;

    private static Map<Watcher.Event.EventType, ZookeeperEventHandler> handlerMap = new HashMap<>();

    @PostConstruct
    public void init() {
        Collection states = this.springContext.getBeansWithAnnotation(ZKEvent.class).values();
        for (Object object : states) {
            if (!(object instanceof ZookeeperEventHandler)) {
                continue;
            }
            ZookeeperEventHandler handler = (ZookeeperEventHandler) object;
            ZKEvent zkEvent = handler.getClass().getAnnotation(ZKEvent.class);
            handlerMap.put(zkEvent.value(), handler);
            logger.info("Zookeeper事件类型：[{}]，处理类：[{}]", zkEvent.value().name() ,handler.getClass());
        }
    }

    public ZookeeperEventHandler instance (Watcher.Event.EventType eventType) {
        ZookeeperEventHandler handler = handlerMap.get(eventType);
        if (handler != null) {
            return handler;
        }
        logger.error("Zookeeper事件类型：[{}]，没有匹配的处理类", eventType.name());
        throw new RuntimeException("Zookeeper事件类型：[" + eventType.name() + "]，没有匹配的处理类");
    }
}
