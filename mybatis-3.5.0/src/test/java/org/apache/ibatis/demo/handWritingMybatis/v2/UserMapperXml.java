package org.apache.ibatis.demo.handWritingMybatis.v2;

import org.apache.ibatis.demo.UserDto;

import java.util.HashMap;
import java.util.Map;

public class UserMapperXml {
    public static final String NAMESPACE = "org.apache.ibatis.demo.handWritingMybatis.v2.UserMapper";
    public static final String SQL_SELECT_BY_PRIMARY_KEY = "select * from user where id = %s";
    //key-->sql,val-->resultType
    public static Map<String,MapperData> sqlIdMap = new HashMap<>();
    //key-->javaType,val-->jdbcType
    public static Map<String,String> mappingColumnMap = new HashMap<>();
    static {
        mappingColumnMap.put("id","id");
        mappingColumnMap.put("userName","user_name");
        mappingColumnMap.put("age","age");

        sqlIdMap.put(NAMESPACE+".selectByPrimaryKey",
                new MapperData(SQL_SELECT_BY_PRIMARY_KEY, UserDto.class,mappingColumnMap));

    }


}
