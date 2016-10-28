package com.msgtouch.framework;


import com.msgtouch.framework.context.SpringBeanAccess;
import com.msgtouch.framework.socket.SocketEngine;
import com.msgtouch.framework.socket.dispatcher.MsgTouchMethodDispatcher;
import com.msgtouch.framework.socket.dispatcher.MsgTouchServiceEngine;
import com.msgtouch.framework.zookeeper.ZooKeeperEngine;
import org.springframework.context.ApplicationContext;

/**
 * Created by Dean on 2016/9/5.
 */
public class Bootstrap {
    private ApplicationContext springContext=null;

    private static Bootstrap bootstrap=null;

    private Bootstrap(){}

    public static Bootstrap getInstances(){
        if(null==bootstrap){
            synchronized (Bootstrap.class){
                if(null==bootstrap){
                    bootstrap=new Bootstrap();
                }
            }
        }
        return bootstrap;
    }

    private void initSpringContext(ApplicationContext applicationContext){
        SpringBeanAccess.getInstances().initSpringContext(applicationContext);
    }

    public synchronized void startServerSocket(ApplicationContext applicationContext){
        //初始化上下文
        initSpringContext(applicationContext);
        //msg service加载
        MsgTouchMethodDispatcher msgTouchMethodDispatcher= MsgTouchServiceEngine.getInstances().loadBilingService();
        //连接注册中心
        ZooKeeperEngine.getInstances().start(applicationContext);
        //启动netty server
        SocketEngine.startServer(msgTouchMethodDispatcher,applicationContext);
    }


}
