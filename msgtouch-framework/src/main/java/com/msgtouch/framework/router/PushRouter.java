package com.msgtouch.framework.router;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.cluster.Router;
import com.alibaba.dubbo.rpc.cluster.router.condition.ConditionRouter;

import java.util.List;

/**
 * Created by Dean on 2016/10/19.
 */
public class PushRouter implements Router, Comparable<Router> {

    private final URL url;
    private final int priority;
    private final boolean force;

    public PushRouter(URL url) {
        this.url = url;
        this.priority = url.getParameter("priority", 0);
        this.force = url.getParameter("force", false);
    }

    @Override
    public URL getUrl() {
        return null;
    }

    @Override
    public <T> List<Invoker<T>> route(List<Invoker<T>> list, URL url, Invocation invocation) throws RpcException {
        if(null!=list&&list.size()>0){



            return list;
        }
        return list;
    }

    @Override
    public int compareTo(Router o) {
        if(o != null && o.getClass() == ConditionRouter.class) {
            PushRouter c = (PushRouter)o;
            return this.priority == c.priority?this.url.toFullString().compareTo(c.url.toFullString()):(this.priority > c.priority?1:-1);
        } else {
            return 1;
        }
    }
}
