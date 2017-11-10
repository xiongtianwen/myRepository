package com.xtw.common;

import java.io.Serializable;
import java.util.List;

/**
 * Class Name : PageInfo.
 * Description : 封装列表及分页查询相关参数.
 * Created by Jhony Zhang on 2016-05-10.
 */
public class PageInfo<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private long pageNum;
    private long pageSize;
    private long total;
    private int pages;
    private List<T> list;

    public PageInfo(long pageNum, long pageSize, long total, List<T> list) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.setTotal(total);
        this.list = list;
    }

    public PageInfo() {
    }

    public long getPageNum() {
        return pageNum;
    }

    public void setPageNum(long pageNum) {
        this.pageNum = pageNum;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
        if (this.pageSize > 0) {
            this.pages = (int) (total / (long) this.pageSize + (long) (total % (long) this.pageSize == 0L ? 0 : 1));
        } else {
            this.pages = 0;
        }
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
