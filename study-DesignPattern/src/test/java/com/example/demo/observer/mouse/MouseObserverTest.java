package com.example.demo.observer.mouse;

import java.lang.reflect.Method;

public class MouseObserverTest {

    public static void main(String[] args) {
        Method m = null;
        try{
            m = MouseObserver.class.getMethod("advice",new Class<?>[]{Object.class});
        }catch (Exception e){
            e.printStackTrace();
        }
        MouseSubscribe subscribe = new MouseSubscribe();

        subscribe.addListener(MouseEventType.ON_DOUBBLE_CLICK,new MouseObserver(),m);

        subscribe.doubleClick();

        subscribe.click();

    }
}
