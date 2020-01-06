package org.apache.ibatis.demo.handWritingMybatis.v2.plugin;

public interface HwInterceptor {
    public Object intercept(HwInvocation invocation) throws Throwable;

    public Object plugin(Object target);

}
