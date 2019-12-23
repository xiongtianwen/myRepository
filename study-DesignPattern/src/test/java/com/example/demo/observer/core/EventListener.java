package com.example.demo.observer.core;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 监听事件
 */
public class EventListener {
    private Map<Enum,Event> events = new HashMap<>();

    //添加监听时注册这个事件
    public void addListener(Enum eventType, Object target, Method callback){
        this.events.put(eventType,new Event(target,callback));
    }

    private void trigger(Event e){
//        e.setSource(this);
//        e.setTime(System.currentTimeMillis());
        try {
            //执行事件的回调函数
            e.getCallback().invoke(e.getTarget());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    protected void trigger(Enum call){
        //如果事件没有被注册就不执行监听(观察者的)操作
        if(this.events.containsKey(call)) {
            this.trigger(this.events.get(call));
        }
    }
}
