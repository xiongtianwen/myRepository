package org.apache.ibatis.demo.handWritingMybatis.v2;

import org.apache.ibatis.demo.UserDto;
import org.apache.ibatis.demo.handWritingMybatis.v2.plugin.UserPlugin;

public class HwTestV2 {
    public static void main(String[] args){
        HwConfiguration configuration = new HwConfiguration();
        configuration.setPlugins(new UserPlugin());
        HwSqlsession sqlsession = new HwSqlsession(configuration,new SimpleExecutor());
        UserMapper mapper = sqlsession.getMapper(UserMapper.class);
        UserDto user = mapper.selectByPrimaryKey("2");
        System.out.println(user.toString());
    }
}
