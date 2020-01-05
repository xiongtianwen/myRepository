package org.apache.ibatis.demo.handWritingMybatis.v2;

public class HwSqlsession {
    private HwConfiguration configuration;
    private HwExecutor executor;

    public HwSqlsession(HwConfiguration configuration, HwExecutor executor) {
        this.configuration = configuration;
        this.executor = executor;
    }

    public <T> T getMapper(Class<T> clazz){
        return configuration.getMapper(clazz,this);
    }

    public <T> T query(MapperData mapperData,Object[] agrs){
        return executor.selectOne(mapperData,agrs);
    }


}
