package com.xtw.tool.tooltable.modal.appConfig;

import java.io.Serializable;

public class FileMap implements Serializable {
    private static final long serialVersionUID = 483475094605504069L;
    private String dir;
    private String name;
    private String suffix;

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}