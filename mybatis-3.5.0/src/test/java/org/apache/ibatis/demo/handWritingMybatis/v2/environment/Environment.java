package org.apache.ibatis.demo.handWritingMybatis.v2.environment;

public class Environment {
    private static String driver;
    private static String url;
    private static String user;
    private static String password;

    static{
        driver = "com.mysql.cj.jdbc.Driver";
        url = "jdbc:mysql://localhost:3306/testdb?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
        user = "root";
        password = "123456";
    }

    public static String getDriver() {
        return driver;
    }

    public static String getUrl() {
        return url;
    }

    public static String getUser() {
        return user;
    }

    public static String getPassword() {
        return password;
    }
}
