package com.msgtouch.framework.router;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.rpc.cluster.Router;
import com.alibaba.dubbo.rpc.cluster.RouterFactory;

/**
 * Created by Dean on 2016/10/19.
 */
public class PushRouterFactory implements RouterFactory {

    @Override
    public Router getRouter(URL url) {
        return new PushRouter(url);
    }
}
