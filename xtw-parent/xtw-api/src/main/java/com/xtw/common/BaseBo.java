package com.xtw.common;

/**
 * Class Name : BaseBo.
 * Description : 查询基类，包含分页、排序等公共字段，各业务查询类继承该类即可.
 * Created by Jhony Zhang on 2016-05-06.
 */
public class BaseBo {
    /**
     * 描述: 页数
     */
    private Long page;

    /**
     * 描述: 每页记录数
     */
    private Long rows;

    /**
     * 描述: 排序条件(排序字段)
     */
    private String sidx;

    /**
     * 描述: 排序方式
     */
    private String sord;

    /**
     * 描述: 编辑标识 0/null新增 1修改
     */
    private Long editFlag;

    /**
     * 描述：操作类型 add：新增 edit：编辑
     */
    private String oper;


    private Long start;

    public Long getPage() {
        // 获取页号，判断页号是否合法，如不合法设置为1
        if (this.page == null || this.page.longValue() <= Constants.PAGE_NO) {
            page = Constants.PAGE_NO;
        }
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    public Long getRows() {
        // 获取每页记录数，判断每页记录数据是否合法，如不合法设置为应用程序参数值
        if (this.rows == null || this.rows.longValue() <= 0) {
            rows = Constants.REC_PER_PAGE;
        }
        return rows;
    }

    public Long getStart(){
        if(this.page==null || this.page==1){
            this.start = 0L;
        }else if(this.page>1){
            this.start = (page-1)*rows;
        }
        return this.start;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    public void setRows(Long rows) {
        this.rows = rows;
    }

    public String getSidx() {
        return sidx;
    }

    public void setSidx(String sidx) {
        this.sidx = sidx;
    }

    public String getSord() {
        return sord;
    }

    public void setSord(String sord) {
        this.sord = sord;
    }

    public Long getEditFlag() {
        return editFlag;
    }

    public void setEditFlag(Long editFlag) {
        this.editFlag = editFlag;
    }

    public String getOper() {
        return oper;
    }

    public void setOper(String oper) {
        this.oper = oper;
    }
}
