package org.apache.ibatis.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JdbcDemo {
    public static void main(String[] args) {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/testdb?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
        String user = "root";
        String pwd = "123456";
        String sql = "select * from user where id = %s";
        String param = "2";
        jdbcDemoSelect(driver,url,user,pwd,sql,param);
    }

    public static <T> T jdbcDemoSelect(String driver, String url, String user, String password,String sql,String param){
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            //1.加载类驱动
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);

            if (!conn.isClosed())
                System.out.println("succeeded connection to the database");

            //2.创建statement类对象，用来执行SQL语句
            sql = String.format(sql,param);
            statement = conn.prepareStatement(sql);
            //设置参数
//            statement.setInt(1, Integer.parseInt(param));
            //3.ResultSet类，用来存放获取的结果集
            rs = statement.executeQuery(sql);

            UserDto userDto = new UserDto();
            while (rs.next()) {
                userDto.setId(rs.getInt("id"));
                userDto.setUserName(rs.getString("user_name"));
                userDto.setAge(rs.getInt("age"));
            }
            return (T)userDto;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //释放资源
            relaseResourse(rs, statement, conn);
        }
        return null;
    }

    public static void relaseResourse(ResultSet rs,PreparedStatement stmt,Connection conn){
        try {
            if(rs!=null){
                rs.close();
            }
            if(stmt!=null){
                stmt.close();
            }
            if(conn!=null){
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
