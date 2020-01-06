package org.apache.ibatis.demo.handWritingMybatis.v2.plugin;

import org.apache.ibatis.demo.handWritingMybatis.v2.MapperData;

public class UserPlugin implements HwInterceptor{
    @Override
    public Object intercept(HwInvocation invocation) throws Throwable{
        //implement pre-processing if needed
        MapperData mapperData = (MapperData)invocation.getArgs()[0];
        Object[] parameters = (Object[])invocation.getArgs()[1];
        System.out.println(String.format("UserPlugin print sql:【%s】,param:【%s】",mapperData.getSql(),parameters[0].toString()));
        Object result = invocation.proceed();
        //implement post-processing if needed
        return result;
    }

    @Override
    public Object plugin(Object target) {
        return HwPlugin.wrap(target,this);
    }
}
