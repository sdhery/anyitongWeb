package com.framework.common.dao.impl;

/**
 * 项目名称：sdheryFramework-project
 * 类名称：sql适配器
 * 类描述：
 * 创建人：Administrator
 * 创建时间：2015/4/3 16:08
 * 修改人：Administrator
 * 修改时间：2015/4/3 16:08
 * 修改备注：
 */
public class SQLAdapter {
    String sql;

    /**
     * @param sql
     */
    public SQLAdapter(String sql) {
        this.sql = sql;
    }

    /**
     * @return the sql
     */
    public String getSql() {
        return sql;
    }

    /**
     * @param sql the sql to set
     */
    public void setSql(String sql) {
        this.sql = sql;
    }
}
