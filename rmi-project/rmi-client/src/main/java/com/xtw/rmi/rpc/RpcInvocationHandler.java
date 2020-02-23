package com.xtw.rmi.rpc;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Authoor: xtw
 * @Description:
 * @Date Created in 2020/2/23 20:58
 */
public class RpcInvocationHandler implements InvocationHandler {

    private String host;
    private int port;

    public RpcInvocationHandler(String host, int port) {
        this.host = host;
        this.port = port;
    }


    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcRequestModel requestModel = new RpcRequestModel();
        requestModel.setClassName(method.getDeclaringClass().getName());
        requestModel.setMethodName(method.getName());
        requestModel.setArgs(args);
        requestModel.setParamTypes(method.getParameterTypes());
        //通过TCP传输协议进行传输
        TcpTransport tcpTransport = new TcpTransport(host,port);
        return tcpTransport.send(requestModel);
    }
}
