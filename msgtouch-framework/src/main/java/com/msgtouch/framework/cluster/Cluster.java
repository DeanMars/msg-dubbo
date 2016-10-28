package com.msgtouch.framework.cluster;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Dean on 2016/9/12.
 */
public class Cluster {
    private String clusterName;
    private String ip;
    private String port;
    private Set<String> services=new HashSet<String>();

    private List<String> ext;

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public Set<String> getServices() {
        return services;
    }

    public void setServices(Set<String> services) {
        this.services = services;
    }

    public List<String> getExt() {
        return ext;
    }

    public void setExt(List<String> ext) {
        this.ext = ext;
    }
}
