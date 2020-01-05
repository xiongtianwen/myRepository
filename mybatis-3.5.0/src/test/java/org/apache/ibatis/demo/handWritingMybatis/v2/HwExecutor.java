package org.apache.ibatis.demo.handWritingMybatis.v2;

public interface HwExecutor {

    <T> T selectOne(MapperData mapperData, Object[] agrs);
}
