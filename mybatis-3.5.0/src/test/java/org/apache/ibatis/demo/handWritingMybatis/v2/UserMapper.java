package org.apache.ibatis.demo.handWritingMybatis.v2;

import org.apache.ibatis.demo.UserDto;

public interface UserMapper {

    UserDto selectByPrimaryKey(String id);
}
