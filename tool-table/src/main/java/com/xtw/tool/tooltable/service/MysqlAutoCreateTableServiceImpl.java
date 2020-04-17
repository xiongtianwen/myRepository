package com.xtw.tool.tooltable.service;

import com.xtw.tool.tooltable.mapper.MysqlMapper;
import com.xtw.tool.tooltable.modal.TableModal;
import com.xtw.tool.tooltable.service.AutoCreateTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Authoor: xtw
 * @Description:
 * @Date Created in 2020/4/16 16:58
 */
@Service
public class MysqlAutoCreateTableServiceImpl implements MysqlAutoCreateTableService {
    @Autowired
    private MysqlMapper mysqlMapper;

    @Override
    public void createTableFromExcel(TableModal tableModal) {
        mysqlMapper.dropTable(tableModal.getTableName());
        mysqlMapper.createNewTable(tableModal);
    }
}
