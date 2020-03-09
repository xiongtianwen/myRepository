package com.xtw.dubbo;

/**
 * @Authoor: xtw
 * @Description:
 * @Date Created in 2020/3/3 14:47
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String msg) {
        return "Hello, I'm " + msg;
    }
}
