package com.xtw.rmi.rpc;

import java.lang.reflect.Proxy;

/**
 * @Authoor: xtw
 * @Description:
 * @Date Created in 2020/2/23 20:55
 */
public class RpcClientProxy {

    public <T> T getProxy(final Class<T> interfaceCls,final String host,final int port){
        return (T)Proxy.newProxyInstance(interfaceCls.getClassLoader(),
                new Class[]{interfaceCls},
                new RpcInvocationHandler(host,port));
    }
}
