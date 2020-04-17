package com.xtw.tool.tooltable;

import com.xtw.tool.tooltable.config.AutoCreateTableConfig;
import com.xtw.tool.tooltable.modal.BaseModal;
import com.xtw.tool.tooltable.modal.Constant;
import com.xtw.tool.tooltable.modal.TableModal;
import com.xtw.tool.tooltable.modal.appConfig.FileMap;
import com.xtw.tool.tooltable.modal.appConfig.TableMap;
import com.xtw.tool.tooltable.service.MysqlAutoCreateTableService;
import com.xtw.tool.tooltable.util.ExcelUtil;
import com.xtw.tool.tooltable.util.PoiUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest
class ToolTableApplicationTests {
    @Autowired
    private MysqlAutoCreateTableService autoCreateTableService;
    @Autowired
    private AutoCreateTableConfig tableConfig;

    @Test
    void contextLoads() {
        FileMap fileMap = tableConfig.getFile();
        TableMap tableMap = tableConfig.getTable();
        String fileDir = fileMap.getDir();
        File dir = new File(fileDir);
        if(dir.isDirectory()){
            for(File file : dir.listFiles()){
                String fileName = file.getName();
                String filePath = dir + "/" + fileName;
                List<TableModal> tableModalList = createMulTableFromMulSheet(filePath, tableMap.getPk(), tableMap.getPkType());
                Long start = System.currentTimeMillis();
                System.out.println("====================开始创建表=======================");
                for(TableModal tableModal : tableModalList){
                    autoCreateTableService.createTableFromExcel(tableModal);
                }
                System.out.println("====================建表结束，共耗时【"+(System.currentTimeMillis()-start)+"ms】=======================");
            }
        }
    }

    /**
     * easypoi循环读取多个excel文件，每个文件只读取一个sheet页数据，创建表
     *@param filePath 文件路径
     * @param pk 主键
     * @param pkType 主键类型
     */
    public List<TableModal> createMulTableFromExcelDir(String filePath,String pk,String pkType){
        List<TableModal> tableModalList = new ArrayList<>();
        List<BaseModal> list = ExcelUtil.importExcel(filePath, 0, 1, BaseModal.class);
        TableModal tableModal = new TableModal();
        String fileName = filePath.substring(filePath.lastIndexOf("/")+1,filePath.lastIndexOf("."));
        tableModal.setTableName(fileName);
        tableModal.setPk(pk);
        tableModal.setPkType(pkType);
        tableModal.setList(list);
        tableModalList.add(tableModal);
        return tableModalList;
    }

    /**
     * 使用poi循环读取多个excel文件，每个文件读取所有sheet页数据，创建表
     * @param filePath 文件路径
     * @param pk 主键
     * @param pkType 主键类型
     */
    public List<TableModal> createMulTableFromMulSheet(String filePath,String pk,String pkType){
        List<TableModal> tableModalList = new ArrayList<>();
        Map<String, List<Map<String, String>>> excelDataMap = PoiUtil.readExcelReturnMap(filePath);
        for(Map.Entry<String,List<Map<String, String>>>  entry : excelDataMap.entrySet()){
            String sheetName = entry.getKey();//sheet页名称作为表名
            //遍历每一行数据，excel表头为map的key
            List<BaseModal> baseModalList = new ArrayList<>();
            for(Map<String, String> rowData : entry.getValue()){
                BaseModal baseModal = new BaseModal();
                baseModal.setColumnName(rowData.get(Constant.TABLE_COLUMN_NAME));
                baseModal.setColumnType(rowData.get(Constant.TABLE_COLUMN_TYPE));
                baseModal.setColumnNotes(rowData.get(Constant.TABLE_COLUMN_NOTES));
                baseModal.setDefaultValue(rowData.get(Constant.TABLE_DEFAULT_VALUE));
                baseModal.setIsNull(rowData.get(Constant.TABLE_IS_NULL));
                baseModalList.add(baseModal);
            }
            TableModal tableModal = new TableModal();
            tableModal.setTableName(sheetName);
            tableModal.setPk(pk);
            tableModal.setPkType(pkType);
            tableModal.setList(baseModalList);
            tableModalList.add(tableModal);
        }
        return tableModalList;
    }
}
