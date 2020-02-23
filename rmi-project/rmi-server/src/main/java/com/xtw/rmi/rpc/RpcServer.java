package com.xtw.rmi.rpc;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Authoor: xtw
 * @Description:
 * @Date Created in 2020/2/23 20:15
 */
public class RpcServer {

    //创建一个线程池
    private final ExecutorService executorService = Executors.newCachedThreadPool();

    public void publishService(final Object service,int port){
        System.out.println("-------------服务发布start------------");
        ServerSocket serverSocket = null;
        try {
            //启动一个服务监听
            serverSocket = new ServerSocket(port);
            while (true){//循环监听
                System.out.println("-------------开始服务监听------------");
                Socket socket = serverSocket.accept();//开启监听
                //通过线程池处理请求
                executorService.execute(new ProcessHandler(socket,service));
                System.out.println("-------------服务调用完成------------");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
