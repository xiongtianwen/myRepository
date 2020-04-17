package com.xtw.tool.tooltable.modal;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;

import java.io.Serializable;

/**
 * @Authoor: xtw
 * @Description:
 * @Date Created in 2020/4/17 8:45
 */
public class BaseModal implements Serializable {
    private static final long serialVersionUID = 228534811713431577L;

    @Excel(name="字段名称")
    private String columnName;

    @Excel(name="字段注释")
    private String columnNotes;

    @Excel(name="字段类型")
    private String columnType;

    @Excel(name="默认值")
    private String defaultValue;

    @Excel(name="是否为空")
    private String isNull;

    public String getColumnNotes() {
        return columnNotes;
    }

    public void setColumnNotes(String columnNotes) {
        this.columnNotes = columnNotes;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getIsNull() {
        return isNull;
    }

    public void setIsNull(String isNull) {
        this.isNull = isNull;
    }

}
