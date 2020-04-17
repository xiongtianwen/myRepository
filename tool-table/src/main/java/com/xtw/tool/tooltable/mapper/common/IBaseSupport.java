package com.xtw.tool.tooltable.mapper.common;

import com.xtw.tool.tooltable.modal.TableModal;
import org.apache.ibatis.annotations.Param;


public interface IBaseSupport {

    void createNewTable(TableModal tableModal);

    void dropTable(@Param(value="tableName")String tableName);
}


