package org.apache.ibatis.demo.handWritingMybatis.v2.resultSet;

import org.apache.ibatis.demo.handWritingMybatis.v2.MapperData;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.util.Map;

public class HwResultSetHandler {
    public <T> T execute(ResultSet rs, MapperData mapperData) throws Exception{
            while (rs.next()) {
                Map<String,String> mappingColumn = mapperData.getMappingColumnMap();
                Class<T> clazz = mapperData.getDataType();
                T obj = clazz.newInstance();
                for(Map.Entry entry : mappingColumn.entrySet()){
                    String property = (String) entry.getKey();
                    String column = (String) entry.getValue();
                    getResult(rs, obj,property,column);
                }
                return obj;
            }
        return null;
    }

    private static void getResult(ResultSet rs,Object obj,String property,String column) throws Exception{
        Class clazz = obj.getClass();
        for(Field field : clazz.getDeclaredFields()){
            if(property.equals(field.getName())){
                Class<?> fieldType = field.getType();
                Method setMethod = null;
                switch (fieldType.getName()){
                    // TODO: 2020/1/5 more type is going to do
                    case "java.lang.String":
                        setMethod = clazz.getMethod("set" + toUpperCase(property),new Class[]{String.class});
                        setMethod.invoke(obj,rs.getString(column));
                        break;
                    case "java.lang.Integer":
                        setMethod = clazz.getMethod("set" + toUpperCase(property),new Class[]{Integer.class});
                        setMethod.invoke(obj,rs.getInt(column));
                        break;
                    default:
                        System.out.println(String.format("can not find the type of [ %s ]",fieldType.getName()));
                }
            }
        }
    }

    private static String toUpperCase(String str){
        if(str == null || "".equals(str)){
            return null;
        }
        return str.substring(0,1).toUpperCase()+str.substring(1);
    }
}
