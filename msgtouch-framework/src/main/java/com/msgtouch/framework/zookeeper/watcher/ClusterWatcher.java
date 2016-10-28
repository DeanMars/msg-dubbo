package com.msgtouch.framework.zookeeper.watcher;

import com.msgtouch.framework.zookeeper.ZkManager;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Dean on 2016/9/12.
 */
public class ClusterWatcher implements Watcher {
    private Logger logger= LoggerFactory.getLogger(ClusterWatcher.class);

    public void process(WatchedEvent watchedEvent) {
        ZooKeeper zooKeeper= ZkManager.getInstances().getZooKeeper();
        logger.info("ClusterWatcher process watchedEvent={} "+watchedEvent.toString());
        try {
            zooKeeper.getChildren(watchedEvent.getPath(),this);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
