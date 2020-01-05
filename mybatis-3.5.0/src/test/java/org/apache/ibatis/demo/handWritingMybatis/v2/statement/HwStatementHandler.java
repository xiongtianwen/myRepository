package org.apache.ibatis.demo.handWritingMybatis.v2.statement;

import org.apache.ibatis.demo.handWritingMybatis.v2.MapperData;
import org.apache.ibatis.demo.handWritingMybatis.v2.environment.Environment;
import org.apache.ibatis.demo.handWritingMybatis.v2.resultSet.HwResultSetHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class HwStatementHandler {
    public <T> T getStatement(HwResultSetHandler resultSetHandler, MapperData mapperData, Object[] args){
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            //1.加载类驱动
            Class.forName(Environment.getDriver());
            conn = DriverManager.getConnection(Environment.getUrl(), Environment.getUser(), Environment.getPassword());
            if (!conn.isClosed())
                System.out.println("succeeded connection to the database");
            //2.创建statement类对象，用来执行SQL语句
            String sql = String.format(mapperData.getSql(), args);
            statement = conn.prepareStatement(sql);
            rs = statement.executeQuery();
            return resultSetHandler.execute(rs,mapperData);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //ignore
            closeResourse(rs,statement,conn);
        }
        return null;

    }

    public static void closeResourse(ResultSet rs,PreparedStatement stmt,Connection conn){
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
