package com.xtw.rmi.zk;

import com.xtw.rmi.zk.anno.RpcAnnotation;

/**
 * @Authoor: xtw
 * @Description:
 * @Date Created in 2020/2/23 19:49
 */
@RpcAnnotation(value = HelloService.class)
public class HelloServiceImpl1 implements HelloService {

    @Override
    public String sayHello(String msg) {
        return "Hello ->" + msg;
    }
}
