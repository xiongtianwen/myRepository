package com.example.demo.observer.mouse;

import com.example.demo.observer.core.EventListener;

public class MouseSubscribe extends EventListener {

    public void click(){
        System.out.println("鼠标单击事件");
        trigger(MouseEventType.ON_CLICK);
    }
    public void doubleClick(){
        System.out.println("鼠标双击事件");
        trigger(MouseEventType.ON_DOUBBLE_CLICK);
    }
    public void up(){
        System.out.println("鼠标弹起事件");
        trigger(MouseEventType.ON_UP);
    }
    public void down(){
        System.out.println("鼠标按下事件");
        trigger(MouseEventType.ON_DOWN);
    }
    public void move(){
        System.out.println("鼠标移动事件");
        trigger(MouseEventType.ON_MOVE);
    }
    public void over(){
        System.out.println("鼠标悬浮事件");
        trigger(MouseEventType.ON_OVER);
    }

}
