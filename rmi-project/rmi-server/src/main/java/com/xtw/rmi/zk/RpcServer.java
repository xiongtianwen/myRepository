package com.xtw.rmi.zk;

import com.xtw.rmi.zk.anno.RpcAnnotation;
import com.xtw.rmi.zk.registry.RegisterCenter;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
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

    private RegisterCenter registerCenter;//注册中心
    private String addressAddress;//服务发布地址
    private Map<String,Object> serviceMap = new HashMap<String, Object>();//存储服务名称和对象

    public RpcServer(RegisterCenter registerCenter, String addressAddress) {
        this.registerCenter = registerCenter;
        this.addressAddress = addressAddress;
    }

    /**
     * 绑定服务名称和服务对象
     * @param services 可以一次性绑定多个服务
     */
    public void bind(Object... services){
        for(Object service : services){
            RpcAnnotation annotation = service.getClass().getAnnotation(RpcAnnotation.class);
            String serviceName = annotation.value().getName();
            String version = annotation.version();
            if(null != version && !"".equals(version)){
                serviceName = serviceName + "-" + version;
            }
            //绑定服务名称和服务对象
            serviceMap.put(serviceName,service);
        }
    }

    /**
     * 发布服务
     */
    public void publishService(){
        System.out.println("-------------服务发布start------------");
        ServerSocket serverSocket = null;
        try {
            //将ip和端口分隔开
            String[] addrArr = addressAddress.split(":");
            //启动一个服务监听
            serverSocket = new ServerSocket(Integer.parseInt(addrArr[1]));

            //注册所有服务
            for(String serviceName : serviceMap.keySet()){
                registerCenter.register(serviceName,addressAddress);
            }
            while (true){//循环监听
                System.out.println("-------------开始监听------------");
                Socket socket = serverSocket.accept();//开启监听
                //通过线程池处理请求
                executorService.execute(new ProcessHandler(socket,serviceMap));
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
