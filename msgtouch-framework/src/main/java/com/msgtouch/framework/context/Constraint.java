package com.msgtouch.framework.context;

/**
 * Created by Dean on 2016/9/6.
 */
public class Constraint {
    //zookeeper hosts
    public static final String PORPERTIES_MSGTOUCH_ZOOKEEPER_ADDRESS="msgtouch.zookeeper.address";
    //zookeeper sessiontimeout
    public static final String PORPERTIES_MSGTOUCH_ZOOKEEPER_SESSIONTIMEOUT="msgtouch.zookeeper.sessiontimeout";
    //zookeeper msgtouch
    public static final String PORPERTIES_MSGTOUCH_ZOOKEEPER_ROOT="msgtouch";


    //msgtouch 节点名
    public static final String PORPERTIES_MSGTOUCH_SERVER_CLUSTERNAMES="app.name";



    public static final String PORPERTIES_TCP_SERVER_PORT="msgtouch.tcp.server.port";
    /**Netty层Worker类线程数*/
    public static final String PORPERTIES_TCP_SERVER_WORKERTHREADSIZE="msgtouch.tcp.server.workerThreadSize";
    /**Message处理线程池大小**/
    public static final String PORPERTIES_TCP_SERVER_CMDTHREADSIZE ="msgtouch.tcp.server.cmdThreadSize";
    /**Netty层Boss类线程数**/
    public static final String PORPERTIES_TCP_SERVER_BOSSTHREADSIZE="msgtouch.tcp.server.bossThreadSize";

}
