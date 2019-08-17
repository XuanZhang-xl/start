package xl.start.test.config.zk.eventhandler;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.springframework.stereotype.Component;
import xl.start.test.config.zk.ZKEvent;

/**
 * 空事件处理类
 * created by XUAN on 2019/8/13
 */
@ZKEvent(Watcher.Event.EventType.DataWatchRemoved)
@Component("NodeWatchRemovedEventHandler")
public class DataWatchRemovedEventHandler implements ZookeeperEventHandler {

    @Override
    public void handle(WatchedEvent watchedEvent) {

    }
}
