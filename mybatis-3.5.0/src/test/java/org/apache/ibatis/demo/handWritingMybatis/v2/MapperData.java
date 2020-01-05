package org.apache.ibatis.demo.handWritingMybatis.v2;

import java.util.Map;

public class MapperData<T> {
    private String sql;
    private Class<T> dataType;
    private Map<String,String> mappingColumnMap;

    public MapperData(String sql,Class<T> dataType,Map<String,String> mappingColumnMap){
        this.sql = sql;
        this.dataType = dataType;
        this.mappingColumnMap = mappingColumnMap;
    }

    public String getSql(){
        return sql;
    }

    public Class<T> getDataType() {
        return dataType;
    }

    public Map<String, String> getMappingColumnMap() {
        return mappingColumnMap;
    }
}
