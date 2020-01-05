package org.apache.ibatis.demo.handWritingMybatis.v1;

import static org.apache.ibatis.demo.JdbcDemo.jdbcDemoSelect;

public class SimpeHwExecutor implements HwExecutor{

    @Override
    public <T> T query(String statment, String parameter, HwSqlSession hwSqlSession) {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/testdb?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
        String user = "root";
        String pwd = "123456";
        return jdbcDemoSelect(driver,url,user,pwd,statment,parameter);
    }
}
