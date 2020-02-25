package com.xtw.rmi.zk;

import java.lang.reflect.Proxy;

/**
 * @Authoor: xtw
 * @Description:
 * @Date Created in 2020/2/23 20:55
 */
public class RpcClientProxy {

    private ServiceDiscovery discovery;

    public RpcClientProxy(ServiceDiscovery discovery) {
        this.discovery = discovery;
    }

    public <T> T getProxy(final Class<T> interfaceCls,String version){
        return (T)Proxy.newProxyInstance(interfaceCls.getClassLoader(),
                new Class[]{interfaceCls},
                new RpcInvocationHandler(discovery,version));
    }
}
