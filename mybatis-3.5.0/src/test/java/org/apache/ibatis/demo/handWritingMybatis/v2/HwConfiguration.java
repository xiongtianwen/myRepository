package org.apache.ibatis.demo.handWritingMybatis.v2;

import org.apache.ibatis.demo.handWritingMybatis.v2.plugin.HwInterceptor;
import org.apache.ibatis.demo.handWritingMybatis.v2.plugin.HwInterceptorChain;

import java.lang.reflect.Proxy;

public class HwConfiguration {
    private HwInterceptorChain interceptorChain = new HwInterceptorChain();

    public void setPlugins(HwInterceptor... interceptors){
        for(HwInterceptor interceptor : interceptors){
            interceptorChain.addPlugin(interceptor);
        }
    }
    public Object pluginAll(HwExecutor executor) {
        return interceptorChain.pluginAll(executor);
    }

    public <T> T getMapper(Class<T> clazz,HwSqlsession sqlsession) {
        return (T)Proxy.newProxyInstance(
                clazz.getClassLoader(),
                new Class[]{clazz},
                new HwMapperProxy(sqlsession));
    }

}
