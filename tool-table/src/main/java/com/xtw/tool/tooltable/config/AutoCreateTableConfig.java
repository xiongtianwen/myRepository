package com.xtw.tool.tooltable.config;

import com.xtw.tool.tooltable.modal.appConfig.FileMap;
import com.xtw.tool.tooltable.modal.appConfig.TableMap;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @Authoor: xtw
 * @Description:
 * @Date Created in 2020/4/17 11:45
 */
@Configuration
@PropertySource("classpath:autoCreateTableConfig.properties")
@ConfigurationProperties(prefix = "data")
public class AutoCreateTableConfig {

    private FileMap file = new FileMap();
    private TableMap table = new TableMap();

    public FileMap getFile() {
        return file;
    }

    public void setFile(FileMap file) {
        this.file = file;
    }

    public TableMap getTable() {
        return table;
    }

    public void setTable(TableMap table) {
        this.table = table;
    }

}
