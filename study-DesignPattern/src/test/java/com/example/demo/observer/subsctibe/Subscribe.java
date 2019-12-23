package com.example.demo.observer.subsctibe;

import com.example.demo.observer.core.EventListener;

/**
 * 订阅者
 */
public class Subscribe extends EventListener {

    public void add(){
        System.out.println("调用添加方法");
        super.trigger(SubscribeEventType.ON_ADD);
    }

    public void edit(){
        System.out.println("调用修改方法");
        super.trigger(SubscribeEventType.ON_EDIT);
    }

    public void delete(){
        System.out.println("调用删除方法");
        super.trigger(SubscribeEventType.ON_DELETE);
    }

    public void query(){
        System.out.println("调用查询方法");
        super.trigger(SubscribeEventType.ON_QUERY);
    }
}
