package com.msgtouch.framework.zookeeper;

/**
 * Created by Dean on 2016/9/8.
 */
public class ZookeeperSetting {
    /** Zookeeper server hosts**/
    public String hosts;
    /** Zookeeper sessiontimeout **/
    public int timeout;
    /** 当前服务名 **/
    public String clusterNames;
    /** 服务端口**/
    public String clusterPort;
}
