package org.apache.ibatis.demo.handWritingMybatis.v2;

import java.lang.reflect.Proxy;

public class HwConfiguration {
    public <T> T getMapper(Class<T> clazz,HwSqlsession sqlsession) {
        return (T)Proxy.newProxyInstance(
                clazz.getClassLoader(),
                new Class[]{clazz},
                new HwMapperProxy(sqlsession));
    }
}
