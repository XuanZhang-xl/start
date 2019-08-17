package xl.start.test.config.zk.eventhandler;

import org.apache.zookeeper.WatchedEvent;

/**
 * created by XUAN on 2019/8/13
 */
public interface ZookeeperEventHandler {

    /**
     * 处理zk事件
     * @param watchedEvent
     */
    public void handle(WatchedEvent watchedEvent);
}
