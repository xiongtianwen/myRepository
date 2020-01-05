package org.apache.ibatis.demo.handWritingMybatis.v1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class HwMapperProxy implements InvocationHandler {
    private HwSqlSession sqlSession;

    public HwMapperProxy(HwSqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(method.getDeclaringClass().getName().equals(HwConfiguration.UserMapperXml.namespace)){
            String sql = HwConfiguration.UserMapperXml.sqlMap.get(method.getName());
            return sqlSession.selectOne(sql,(String)args[0]);
        }
        return method.invoke(proxy,args);
    }
}
