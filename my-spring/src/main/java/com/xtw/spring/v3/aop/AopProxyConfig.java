package com.xtw.spring.v3.aop;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

//只是对application.properties的expression进行封装
public class AopProxyConfig {
    private Map<Method,ProxyModel> points = new HashMap<>();

    public void put(Method target,Object aspect,Method before,Method after){
        points.put(target,new ProxyModel(aspect,before,after));
    }
    public ProxyModel get(Method target){
        return points.get(target);
    }

    public boolean containsKey(Method key){
        return this.points.containsKey(key);
    }

    public class ProxyModel{
        private Object aspect;
        private Method beforeMethod;
        private Method afterMethod;

        public ProxyModel(Object aspect, Method beforeMethod, Method afterMethod) {
            this.aspect = aspect;
            this.beforeMethod = beforeMethod;
            this.afterMethod = afterMethod;
        }

        public Object getAspect() {
            return aspect;
        }

        public Method getBeforeMethod() {
            return beforeMethod;
        }

        public Method getAfterMethod() {
            return afterMethod;
        }
    }
}
