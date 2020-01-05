package org.apache.ibatis.demo.handWritingMybatis.v2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class HwMapperProxy implements InvocationHandler {
    private HwSqlsession sqlsession;

    public HwMapperProxy(HwSqlsession sqlsession){
        this.sqlsession = sqlsession;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        for(String key : UserMapperXml.sqlIdMap.keySet()){
            if(key.equals(method.getDeclaringClass().getName() + "." + method.getName())){
                MapperData mapperData = UserMapperXml.sqlIdMap.get(key);
                return sqlsession.query(mapperData,args);
            }
        }
        return method.invoke(proxy,args);
    }
}
