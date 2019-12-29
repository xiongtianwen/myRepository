package org.apache.ibatis.demo;

import org.apache.ibatis.annotations.*;

public interface UserMapper {
    @Insert("insert into `user` (user_name,age)values(#{userName,jdbcType=VARCHAR,typeHandler=org.apache.ibatis.demo.UserTypeHandler},#{age})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer insert(UserDto userDto);

    @Select("select * from user where id = #{id}")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "userName",column = "user_name"),
            @Result(property = "age",column = "age")
    })
    UserDto selectByPrimaryKeyWithoutXml(@Param("id")String id);

    UserDto selectByPrimaryKey(String id);
}
