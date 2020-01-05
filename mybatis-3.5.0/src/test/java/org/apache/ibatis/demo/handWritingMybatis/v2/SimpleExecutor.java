package org.apache.ibatis.demo.handWritingMybatis.v2;

import org.apache.ibatis.demo.handWritingMybatis.v2.resultSet.HwResultSetHandler;
import org.apache.ibatis.demo.handWritingMybatis.v2.statement.HwStatementHandler;

public class SimpleExecutor implements HwExecutor {
    private HwStatementHandler statementHandler = new HwStatementHandler();
    private HwResultSetHandler resultSetHandler = new HwResultSetHandler();
    @Override
    public <T> T selectOne(MapperData mapperData, Object[] args) {
        return (T)statementHandler.getStatement(resultSetHandler,mapperData, args);
    }

}
