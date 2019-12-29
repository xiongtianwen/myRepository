package org.apache.ibatis.demo;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;

public class Test {

    public static SqlSession getSqlsession(){
        return getSqlsessionFactory().openSession();
    }

    public static SqlSessionFactory getSqlsessionFactory(){
        SqlSessionFactory sqlSessionFactory = null;
        try {
            Reader reader = Resources.getResourceAsReader("org/apache/ibatis/demo/mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sqlSessionFactory;
    }


    public static void addMapper(Class<?> clazz){
        getSqlsessionFactory().getConfiguration().addMapper(clazz);
    }

    public static void main(String[] args) {
//          addMapper(UserMapper.class);
        SqlSessionFactory sqlSessionFactory =getSqlsessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper testMapper =  sqlSession.getMapper(UserMapper.class);
          UserDto user = testMapper.selectByPrimaryKey("2");
//          UserDto user = testMapper.selectByPrimaryKeyWithoutXml("2");
//        UserDto userDto = new UserDto();
//        userDto.setUserName("yk");
//        userDto.setAge(26);
//        testMapper.insert(userDto);
//        sqlSession.commit();
    }
}
