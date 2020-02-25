package com.xtw.rmi.zk;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.Map;

/**
 * @Authoor: xtw
 * @Description:
 * @Date Created in 2020/2/23 19:57
 */
public class ProcessHandler implements Runnable {

    private Socket socket;
    private Map<String,Object> serviceMap;//存储注册的服务对象(服务端发布的服务)

    public ProcessHandler(Socket socket, Map<String,Object> serviceMap) {
        this.socket = socket;
        this.serviceMap = serviceMap;
    }

    public void run() {
        ObjectInputStream inputStream = null;
        ObjectOutputStream outputStream = null;
        try {
            //获取客户端的输入流
            inputStream = new ObjectInputStream(socket.getInputStream());
            //反序列化客户端传过来的对象,客户端传过来的也必须是RpcRequestModel对象
            RpcRequestModel requestModel = (RpcRequestModel)inputStream.readObject();
            Object result = invoke(requestModel);
            //获取客户端输出流
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            //将方法执行结果用输出流返回给客户端
            outputStream.writeObject(result);
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                inputStream.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private Object invoke(RpcRequestModel requestModel) throws InvocationTargetException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException {
        String className = requestModel.getClassName();
        Method method = Class.forName(className)
                .getMethod(requestModel.getMethodName(),requestModel.getParamTypes());
        String version = requestModel.getVersion();
        String serviceName = className;
        if(version != null && !"".equals(version)){
            serviceName  = serviceName + "-" + version;
        }
        Object service = serviceMap.get(serviceName);
        //利用反射调用发布的服务中的方法
        return method.invoke(service,requestModel.getArgs());
    }
}
