package com.framework.common.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 项目名称：sdheryFramework-project
 * 类名称：
 * 类描述：
 * 创建人：Administrator
 * 创建时间：2015/6/18 15:45
 * 修改人：Administrator
 * 修改时间：2015/6/18 15:45
 * 修改备注：
 */
public abstract class BaseDomain implements Serializable {
    private Integer updateId;
    private Date createTime;
    private Date lastModifiedTime;

    public Integer getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Integer updateId) {
        this.updateId = updateId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(Date lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }
}
