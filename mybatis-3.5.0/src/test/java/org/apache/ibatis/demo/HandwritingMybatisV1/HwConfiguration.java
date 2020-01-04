package org.apache.ibatis.demo.HandwritingMybatisV1;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class HwConfiguration {

    public <T> T getMapper(Class<T> clazz,HwSqlSession hwSqlSession){
        return (T)Proxy.newProxyInstance(clazz.getClassLoader(),
                new Class[]{clazz},
                new HwMapperProxy(hwSqlSession));
    }

    static class UserMapperXml{
        public static final String namespace = "org.apache.ibatis.demo.HandwritingMybatisV1.HwUserMapper";
        public static final String sql = "select * from user where id = %s";
        public static Map<String,String> sqlMap = new HashMap<>();
        static{
            sqlMap.put("selectByPrimaryKey",sql);
        }
    }

}
