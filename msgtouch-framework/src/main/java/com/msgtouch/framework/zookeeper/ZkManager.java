package com.msgtouch.framework.zookeeper;

import com.alibaba.fastjson.JSON;
import com.msgtouch.framework.cluster.Cluster;
import com.msgtouch.framework.context.Constraint;
import com.msgtouch.framework.zookeeper.watcher.ClusterWatcher;
import com.msgtouch.framework.zookeeper.watcher.DefaultWatcher;
import com.msgtouch.framework.zookeeper.watcher.ServiceWatcher;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.util.List;

/**
 * Created by Dean on 2016/9/6.
 */
public class ZkManager {
    private Logger logger= LoggerFactory.getLogger(ZkManager.class);
    private ZooKeeper zooKeeper;
    private static final String key="zhangchuqiang";

    private static String clusterPath=null;

    private static ZkManager zkManager=null;
    private ZkManager(){}

    public static ZkManager getInstances(){
        if(null==zkManager){
            synchronized (ZkManager.class){
                if(null==zkManager) {
                    zkManager = new ZkManager();
                }
            }
        }
        return zkManager;
    }


    public void bind(ZookeeperSetting setting){
        try {
            zooKeeper=new ZooKeeper(setting.hosts,setting.timeout,new DefaultWatcher());
            zooKeeper.addAuthInfo("digest",key.getBytes());

            //创建根节点
            ClusterWatcher clusterWatcher=new ClusterWatcher();
            String root="/"+Constraint.PORPERTIES_MSGTOUCH_ZOOKEEPER_ROOT;
            Stat stat=zooKeeper.exists(root,clusterWatcher);
            if(null==stat){
                zooKeeper.create(root,Constraint.PORPERTIES_MSGTOUCH_ZOOKEEPER_ROOT.getBytes(),ZooDefs.Ids.CREATOR_ALL_ACL, CreateMode.PERSISTENT);
            }
            zooKeeper.getChildren(root,clusterWatcher);

            //创建当前服务临时节点
            ServiceWatcher serviceWatcher=new ServiceWatcher();
            InetAddress inetAddress=InetAddress.getLocalHost();
            String ip=inetAddress.getHostAddress().toString();
            clusterPath=root+"/"+ip+":"+setting.clusterPort;
            Stat serverStat=zooKeeper.exists(clusterPath,serviceWatcher);

            Cluster cluster=new Cluster();
            cluster.setClusterName(setting.clusterNames);
            cluster.setIp(ip);
            cluster.setPort(setting.clusterPort);


            String data=JSON.toJSONString(cluster);
            logger.info("ZkManager add server services data={}",data);
            if(null==serverStat){
                zooKeeper.create(clusterPath, data.getBytes(), ZooDefs.Ids.CREATOR_ALL_ACL, CreateMode.EPHEMERAL);
            }else{
                zooKeeper.setData(clusterPath, data.getBytes(),serverStat.getVersion());
            }


            logger.info("ZkManager bind success !");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void loadCluster(List<String> clusters){

    }


    private void loadServer(List<String> servers){

    }

    public ZooKeeper getZooKeeper() {
        return zooKeeper;
    }
}
