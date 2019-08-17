package xl.start.test.config.zk;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * zookeeper配置
 * created by XUAN on 2019/8/12
 */
@Configuration
public class ZookeeperConfiguration {

    //public static final String zkServer = "140.143.206.160:2181";
    private static final String zkServer = "140.143.206.160:2181,140.143.206.160:2182,140.143.206.160:2183";

    private static final Integer timeout = 5000;

    @Autowired
    private ZKEventHandlerFactory factory;

    @Bean
    public ZooKeeper zookeeper() throws IOException {
        return new ZooKeeper(zkServer, timeout, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                factory.instance(watchedEvent.getType()).handle(watchedEvent);
            }
        });
    }
}
