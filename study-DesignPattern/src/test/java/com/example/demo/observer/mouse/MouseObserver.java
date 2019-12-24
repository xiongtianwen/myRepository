package com.example.demo.observer.mouse;

import com.example.demo.observer.core.Observer;

public class MouseObserver implements Observer {
    @Override
    public void advice(Object source) {
        System.out.println("=================进入鼠标事件观察者的通知=================");
        System.out.println("=================目标源为:【"+source+"】=================");
    }
}
