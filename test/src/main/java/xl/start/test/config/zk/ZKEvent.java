package xl.start.test.config.zk;

import org.apache.zookeeper.Watcher;

/**
 * created by XUAN on 2019/8/13
 */
public @interface ZKEvent {
    Watcher.Event.EventType value();
}
