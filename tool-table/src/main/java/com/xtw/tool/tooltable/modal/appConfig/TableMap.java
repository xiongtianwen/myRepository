package com.xtw.tool.tooltable.modal.appConfig;

import java.io.Serializable;

public class TableMap implements Serializable {
    private static final long serialVersionUID = -7534956340227115608L;
    private String name;
    private String pk;
    private String pkType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}