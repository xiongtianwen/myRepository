package org.apache.ibatis.demo.HandwritingMybatisV1;

public class HwSqlSession {
    private HwConfiguration configuration;
    private HwExecutor executor;

    public HwSqlSession(HwConfiguration configuration, HwExecutor executor) {
        this.configuration = configuration;
        this.executor = executor;
    }

    public <T> T getMapper(Class<T> clazz){
        return configuration.getMapper(clazz,this);
    }

    public <T> T selectOne(String statment,String parameter){
        return executor.selectOne(statment,parameter,this);
    }



}
