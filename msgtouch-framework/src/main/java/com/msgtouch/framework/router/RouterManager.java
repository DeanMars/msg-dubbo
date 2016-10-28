package com.msgtouch.framework.router;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.registry.Registry;
import com.alibaba.dubbo.registry.RegistryFactory;

/**
 * Created by Dean on 2016/10/19.
 */
public class RouterManager {

    public static void setPushRouter(){
        RegistryFactory registryFactory = ExtensionLoader.getExtensionLoader(RegistryFactory.class)
                .getAdaptiveExtension();
        Registry registry = registryFactory.getRegistry(URL.valueOf("zookeeper://10.10.159.215:2181"));
        registry.register(URL.valueOf(
                "routers://0.0.0.0/com.msgtouch.common.service.MsgService?name=providerApp&category=routers&router=push&dynamic=false"));
    }

}
