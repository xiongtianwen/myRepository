package org.apache.ibatis.demo.handWritingMybatis.v1;

public interface HwExecutor {
    public <T> T query(String statment, String parameter, HwSqlSession hwSqlSession);
}
