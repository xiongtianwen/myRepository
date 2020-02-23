package com.xtw.rmi.rpc;

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

    private String host;
    private int port;

    public TcpTransport(String host, int port) {
        this.host = host;
        this.port = port;
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
            return new Socket(host,port);
        } catch (IOException e) {
            System.out.println("------------创建连接失败!-------------");
            e.printStackTrace();
        }
        return null;
    }
}
