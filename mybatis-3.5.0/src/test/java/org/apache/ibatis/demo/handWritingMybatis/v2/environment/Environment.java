package org.apache.ibatis.demo.handWritingMybatis.v2.environment;

public class Environment {
    private String driver;
    private String url;
    private String user;
    private String password;

    public Environment(){
        driver = "com.mysql.cj.jdbc.Driver";
        url = "jdbc:mysql://localhost:3306/testdb?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
        user = "root";
        password = "123456";
    }

    public String getDriver() {
        return driver;
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
