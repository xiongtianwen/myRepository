package com.xtw.tool.tooltable.util;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @Authoor: xtw
 * @Description:
 * @Date Created in 2020/4/17 15:43
 */
public class PoiUtil {

    public static void main(String[] args) throws Exception {
        String path = "C:/Users/XTW/Desktop/excel/发送接口表结构.xlsx";
        Map list = readExcelReturnMap(path);
        System.out.println(list);
    }

    public static List<List<Map<String, String>>> readExcelReturnListFromMulSheet(String filePath) {
        // 存放读取的所有结果
        List<List<Map<String, String>>> resultList = new ArrayList<>();
        XSSFWorkbook xssfWorkbook = null;
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(filePath));
            xssfWorkbook = new XSSFWorkbook(inputStream);
            for (int i = 0; i < xssfWorkbook.getNumberOfSheets(); i++) {
                // 获取第i页数据
                XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(i);
                // 存放一页的结果
                List<Map<String, String>> sheetList = new ArrayList<>();
                // 记录表头的位置
                Map<Integer, String> titleMap = new HashMap<>(xssfSheet.getPhysicalNumberOfRows());
                for (int j = 0; j < xssfSheet.getPhysicalNumberOfRows(); j++) {
                    // 获取第j行数据
                    XSSFRow xssfRow = xssfSheet.getRow(j);
                    if (Objects.isNull(xssfRow)) {
                        // 如果整行为空，则跳过
                        continue;
                    }
                    // 存放一行的数据
                    Map<String, String> rowMap = new HashMap<>(xssfRow.getPhysicalNumberOfCells());
                    for (int k = 0; k < (xssfRow.getPhysicalNumberOfCells() > titleMap.size() ? xssfRow.getPhysicalNumberOfCells() : titleMap.size()); k++) {
                        if (0 == j) {
                            if (Objects.nonNull(xssfRow.getCell(k))) {
                                // 将每一页的第一行作为表头记录
                                titleMap.put(k, xssfRow.getCell(k).toString());
                            }
                        } else {
                            // 只记录有表头的数据
                            if (Objects.nonNull(titleMap.get(k))) {
                                Object cellValue = getCellValue(xssfRow.getCell(k));
                                rowMap.put(titleMap.get(k), Objects.nonNull(cellValue) ? cellValue.toString() : "");
                            }
                        }
                    }
                    if (!rowMap.isEmpty()) {
                        sheetList.add(rowMap);
                    }
                }
                if (CollectionUtils.isNotEmpty(sheetList)) {
                    resultList.add(sheetList);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                xssfWorkbook.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultList;
    }

    public static Map<String,List<Map<String, String>>> readExcelReturnMap(String filePath) {
        // 存放读取的所有结果
        Map<String,List<Map<String, String>>> resultMap = new HashMap<>();
        XSSFWorkbook xssfWorkbook = null;
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(filePath));
            xssfWorkbook = new XSSFWorkbook(inputStream);
            for (int i = 0; i < xssfWorkbook.getNumberOfSheets(); i++) {
                // 获取第i页数据
                XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(i);
                String sheetName = xssfSheet.getSheetName();
                // 存放一页的结果
                List<Map<String, String>> sheetList = new ArrayList<>();
                // 记录表头的位置
                Map<Integer, String> titleMap = new HashMap<>(xssfSheet.getPhysicalNumberOfRows());
                for (int j = 0; j < xssfSheet.getPhysicalNumberOfRows(); j++) {
                    // 获取第j行数据
                    XSSFRow xssfRow = xssfSheet.getRow(j);
                    if (Objects.isNull(xssfRow)) {
                        // 如果整行为空，则跳过
                        continue;
                    }
                    // 存放一行的数据
                    Map<String, String> rowMap = new HashMap<>(xssfRow.getPhysicalNumberOfCells());
                    for (int k = 0; k < (xssfRow.getPhysicalNumberOfCells() > titleMap.size() ? xssfRow.getPhysicalNumberOfCells() : titleMap.size()); k++) {
                        if (0 == j) {
                            if (Objects.nonNull(xssfRow.getCell(k))) {
                                // 将每一页的第一行作为表头记录
                                titleMap.put(k, xssfRow.getCell(k).toString());
                            }
                        } else {
                            // 只记录有表头的数据
                            if (Objects.nonNull(titleMap.get(k))) {
                                Object cellValue = getCellValue(xssfRow.getCell(k));
                                rowMap.put(titleMap.get(k), Objects.nonNull(cellValue) ? cellValue.toString() : "");
                            }
                        }
                    }
                    if (!rowMap.isEmpty()) {
                        sheetList.add(rowMap);
                    }
                }
                if (CollectionUtils.isNotEmpty(sheetList)) {
                    resultMap.put(sheetName,sheetList);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                xssfWorkbook.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultMap;
    }

    /**
     * 根据单元格的类型获取相应的值
     *
     * @param cell
     * @return
     */
    private static Object getCellValue(Cell cell) {
        Object cellValue;
        if (Objects.nonNull(cell) && cell.getCellTypeEnum() == CellType.NUMERIC) {
            // 数值型
            // poi读取整数会自动转成小数，这里对整数进行还原，小数不会做处理
            long longValue = Math.round(cell.getNumericCellValue());
            if (Double.parseDouble(longValue + ".0") == cell.getNumericCellValue()) {
                cellValue = longValue;
            } else {
                cellValue = cell.getNumericCellValue();
            }
        } else if (Objects.nonNull(cell) && cell.getCellTypeEnum() == CellType.FORMULA) {
            // 公式型
            // 公式计算的值不会转成小数，这里数值获取失败后会获取字符
            try {
                cellValue = cell.getNumericCellValue();
            } catch (Exception e) {
                cellValue = cell.getStringCellValue();
            }
        } else {
            // 其他类型不作处理
            cellValue = cell;
        }
        return cellValue;
    }

    /**
     * 去除Excel手误添加的空格
     *
     * @param str
     * @return
     */
    public static String stringTrim(String str) {
        return str.replaceAll("[\\u00A0]+", "").trim();
    }
}
