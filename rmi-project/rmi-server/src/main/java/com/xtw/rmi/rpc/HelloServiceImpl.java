package com.xtw.rmi.rpc;

/**
 * @Authoor: xtw
 * @Description:
 * @Date Created in 2020/2/23 19:49
 */
public class HelloServiceImpl implements HelloService{

    public String sayHello(String msg) {
        return "Hello ->" + msg;
    }
}
