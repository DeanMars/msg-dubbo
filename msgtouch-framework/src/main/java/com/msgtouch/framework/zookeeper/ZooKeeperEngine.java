package com.msgtouch.framework.zookeeper;

import com.msgtouch.framework.context.Constraint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

import java.util.Properties;

/**
 * Created by Dean on 2016/9/6.
 */
public class ZooKeeperEngine {
    private static Logger logger= LoggerFactory.getLogger(ZooKeeperEngine.class);

    private static ZooKeeperEngine zooKeeperEngine=null;
    private ZooKeeperEngine(){}

    public static ZooKeeperEngine getInstances(){
        if(null==zooKeeperEngine){
            synchronized (ZooKeeperEngine.class){
                if(null==zooKeeperEngine) {
                    zooKeeperEngine = new ZooKeeperEngine();
                }
            }
        }
        return zooKeeperEngine;
    }


    public void start(ApplicationContext applicationContext){
        ZookeeperSetting setting=getZookeeperSetting(applicationContext);
        ZkManager.getInstances().bind(setting);

    }



    private ZookeeperSetting getZookeeperSetting(ApplicationContext applicationContext){
        Environment environment=applicationContext.getEnvironment();
        ZookeeperSetting setting=new ZookeeperSetting();
        if(!environment.containsProperty(Constraint.PORPERTIES_MSGTOUCH_ZOOKEEPER_ADDRESS)){
            throw new IllegalArgumentException("msgtouch porperties msgtouch.zookeeper.address is needed ");
        }
        setting.hosts=environment.getProperty(Constraint.PORPERTIES_MSGTOUCH_ZOOKEEPER_ADDRESS);

        if(!environment.containsProperty(Constraint.PORPERTIES_MSGTOUCH_ZOOKEEPER_SESSIONTIMEOUT)){
            throw new IllegalArgumentException("msgtouch porperties msgtouch.zookeeper.sessiontimeout is needed ");
        }
        setting.timeout=Integer.parseInt(environment.getProperty(Constraint.PORPERTIES_MSGTOUCH_ZOOKEEPER_SESSIONTIMEOUT));

        if(!environment.containsProperty(Constraint.PORPERTIES_MSGTOUCH_SERVER_CLUSTERNAMES)){
            throw new IllegalArgumentException("msgtouch porperties app.name is needed ");
        }
        setting.clusterNames=environment.getProperty(Constraint.PORPERTIES_MSGTOUCH_SERVER_CLUSTERNAMES);

        if(!environment.containsProperty(Constraint.PORPERTIES_TCP_SERVER_PORT)){
            throw new IllegalArgumentException("msgtouch porperties msgtouch.tcp.server.port is needed ");
        }
        setting.clusterPort=environment.getProperty(Constraint.PORPERTIES_TCP_SERVER_PORT);

        return setting;
    }


}
