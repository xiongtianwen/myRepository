package org.apache.ibatis.demo.HandwritingMybatisV1;

public interface HwExecutor {
    public <T> T selectOne(String statment,String parameter,HwSqlSession hwSqlSession);
}
