package org.apache.ibatis.demo.handWritingMybatis.v2.plugin;

import java.lang.reflect.Method;

public class HwInvocation {
    private Object target;
    private Method method;
    private Object[] args;

    public HwInvocation(Object target, Method method, Object[] args) {
        this.target = target;
        this.method = method;
        this.args = args;
    }

    public Object getTarget() {
        return target;
    }

    public Method getMethod() {
        return method;
    }

    public Object[] getArgs() {
        return args;
    }

    public Object proceed() throws Exception{
        return method.invoke(target,args);
    }

}
