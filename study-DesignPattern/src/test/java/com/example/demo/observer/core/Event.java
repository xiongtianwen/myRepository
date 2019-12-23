package com.example.demo.observer.core;

import java.lang.reflect.Method;

/**
 * 事件
 */
public class Event {
    //事件源头
    private Object source;
    //目标
    private Object target;
    //回调函数
    private Method callback;
    //触发
    private String trigger;
    //记录时间
    private long time;

    public Event(Object target,Method callback){
        this.target = target;
        this.callback = callback;
    }

    public Object getSource() {
        return source;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public Method getCallback() {
        return callback;
    }

    public void setCallback(Method callback) {
        this.callback = callback;
    }

    public String getTrigger() {
        return trigger;
    }

    public long getTime() {
        return time;
    }

    Event setTrigger(String trigger) {
        this.trigger = trigger;
        return this;
    }

    Event setTime(long time) {
        this.time = time;
        return this;
    }

     Event setSource(Object source) {
        this.source = source;
        return this;
    }
}
