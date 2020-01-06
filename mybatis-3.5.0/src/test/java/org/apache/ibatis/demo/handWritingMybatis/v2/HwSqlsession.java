package org.apache.ibatis.demo.handWritingMybatis.v2;

import org.apache.ibatis.demo.handWritingMybatis.v2.plugin.HwInterceptor;
import org.apache.ibatis.demo.handWritingMybatis.v2.plugin.HwInterceptorChain;

public class HwSqlsession {
    private HwConfiguration configuration;
    private HwExecutor executor;

    public HwSqlsession(HwConfiguration configuration, HwExecutor executor) {
        this.configuration = configuration;
        //注册到代理类中，executor一定要用当你返回的对象
        executor = (HwExecutor)configuration.pluginAll(executor);
        this.executor = executor;
    }

    public <T> T getMapper(Class<T> clazz){
        return configuration.getMapper(clazz,this);
    }

    public <T> T query(MapperData mapperData,Object[] args){
        return executor.selectOne(mapperData,args);
    }


}
