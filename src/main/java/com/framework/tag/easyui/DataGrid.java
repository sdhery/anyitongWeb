package com.framework.tag.easyui;

import java.util.List;

/**
 * 项目名称：yishimai
 * 类名称：
 * 类描述：
 * 创建人：Administrator
 * 创建时间：2015/1/28 16:32
 * 修改人：Administrator
 * 修改时间：2015/1/28 16:32
 * 修改备注：
 */
public class DataGrid {
    private int page = 1;// 当前页
    private int rows = 10;// 每页显示记录数
    private String sort = null;// 排序字段名
    private SortDirection order = SortDirection.asc;// 按什么排序(asc,desc)
    private String field;//字段
    private String treefield;//树形数据表文本字段
    private List results;// 结果集
    private int total;//总记录数
    private String footer;//合计列

    public String getFooter() {
        return footer;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public SortDirection getOrder() {
        return order;
    }

    public void setOrder(SortDirection order) {
        this.order = order;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getTreefield() {
        return treefield;
    }

    public void setTreefield(String treefield) {
        this.treefield = treefield;
    }

    public List getResults() {
        return results;
    }

    public void setResults(List results) {
        this.results = results;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getStart() {
        if ((this.getPage() - 1) * this.rows == 0) {
            return 0;
        } else {
            return (this.getPage() - 1) * this.rows;
        }
    }
}
