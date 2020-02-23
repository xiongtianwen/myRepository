package com.xtw.rmi.rpc;

/**
 * @Authoor: xtw
 * @Description:
 * @Date Created in 2020/2/23 19:52
 */
public class ServerMain {
    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        RpcServer rpcServer = new RpcServer();
        rpcServer.publishService(helloService,8888);
    }
}
