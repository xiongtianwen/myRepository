package com.example.demo.observer.subsctibe;

import java.lang.reflect.Method;

public class ObserverTest {

    public static void main(String[] args) {

        Method advice = null;
        try {
            //要执行的回调函数
            advice = Observer.class.getMethod("advice",new Class<?>[]{Observer.class});
        } catch (Exception e) {
            e.printStackTrace();
        }

        //订阅者
        Subscribe subscribe = new Subscribe();

        subscribe.addListener(SubscribeEventType.ON_ADD,new Observer(),advice);

        subscribe.add();

    }
}
