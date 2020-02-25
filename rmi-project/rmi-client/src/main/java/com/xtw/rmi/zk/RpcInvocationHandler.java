package com.xtw.rmi.zk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Authoor: xtw
 * @Description:
 * @Date Created in 2020/2/23 20:58
 */
public class RpcInvocationHandler implements InvocationHandler {

    private ServiceDiscovery serviceDiscovery;
    private String version;

    public RpcInvocationHandler(ServiceDiscovery serviceDiscovery, String version) {
        this.serviceDiscovery = serviceDiscovery;
        this.version = version;
    }


    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcRequestModel requestModel = new RpcRequestModel();
        requestModel.setClassName(method.getDeclaringClass().getName());
        requestModel.setMethodName(method.getName());
        requestModel.setArgs(args);
        requestModel.setParamTypes(method.getParameterTypes());
        String serviceName = requestModel.getClassName();
        if(version != null && !"".equals(version)){
            requestModel.setVersion(version);
            serviceName = serviceName + "-" + version;
        }
        String address = serviceDiscovery.discovery(serviceName);
        //通过TCP传输协议进行传输
        TcpTransport tcpTransport = new TcpTransport(address);
        return tcpTransport.send(requestModel);
    }
}
