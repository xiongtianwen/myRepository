package com.xtw.dubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Authoor: xtw
 * @Description:
 * @Date Created in 2020/3/3 14:54
 */
public class ConsumerApp {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"dubbo-client.xml"});
        context.start();
        HelloService helloService = (HelloService)context.getBean("helloService");
        System.out.println(helloService.sayHello("xtw"));
    }
}
