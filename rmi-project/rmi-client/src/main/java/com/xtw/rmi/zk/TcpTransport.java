package com.xtw.rmi.zk;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @Authoor: xtw
 * @Description:
 * @Date Created in 2020/2/23 21:09
 */
public class TcpTransport {

    private String serviceAddress;

    public TcpTransport(String serviceAddress) {
        this.serviceAddress = serviceAddress;
    }

    public Object send(RpcRequestModel requestModel){
        Socket socket = null;
        ObjectOutputStream outputStream = null;
        ObjectInputStream inputStream = null;
        try {
            socket = newSocket();
            //获取输出流，将客户端需要调用的远程方法参数request发送给
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(requestModel);
            outputStream.flush();
            inputStream = new ObjectInputStream(socket.getInputStream());
            Object result = inputStream.readObject();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                inputStream.close();
                outputStream.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private Socket newSocket() {
        System.out.println("------------创建一个新的连接-------------");
        try {
            String[] addrArr = serviceAddress.split(":");
            return new Socket(addrArr[0],Integer.parseInt(addrArr[1]));
        } catch (IOException e) {
            System.out.println("------------创建连接失败!-------------");
            e.printStackTrace();
        }
        return null;
    }
}
