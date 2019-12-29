package org.apache.ibatis.demo;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Properties;

@Intercepts({@Signature(
        type=Executor.class,
        method="query",
        args = {MappedStatement.class,Object.class, RowBounds.class, ResultHandler.class}
        )})
public class UserQueryPlugin implements Interceptor {
    private Properties properties;
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        //implement pre-processing if needed
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        BoundSql boundSql = mappedStatement.getBoundSql(invocation.getArgs()[1]);
        System.out.println(String.format("UserPugin print sql:【%s】,param:【%s】",boundSql.getSql(),boundSql.getParameterObject()));
        Object result = invocation.proceed();
        //implement post-processing if needed
        return result;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target,this);
    }

    @Override
    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
