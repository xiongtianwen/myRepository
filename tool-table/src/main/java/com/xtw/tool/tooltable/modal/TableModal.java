package com.xtw.tool.tooltable.modal;

import java.io.Serializable;
import java.util.List;

/**
 * @Authoor: xtw
 * @Description:
 * @Date Created in 2020/4/17 10:39
 */
public class TableModal implements Serializable {
    private static final long serialVersionUID = 6822879136433250546L;
    private String tableName;
    private String pk;
    private String pkType;//主键类型(0自增,1UUID)
    List<BaseModal> list;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    public String getPkType() {
        return pkType;
    }

    public void setPkType(String pkType) {
        this.pkType = pkType;
    }

    public List<BaseModal> getList() {
        return list;
    }

    public void setList(List<BaseModal> list) {
        this.list = list;
    }
}
