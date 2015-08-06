package com.framework.module.security.domain;

import com.framework.common.domain.BaseDomain;

import java.io.Serializable;

/**
 * 项目名称：sdheryFramework-project
 * 类名称：
 * 类描述：
 * 创建人：Administrator
 * 创建时间：2015/4/22 10:29
 * 修改人：Administrator
 * 修改时间：2015/4/22 10:29
 * 修改备注：
 */
public class SysResource extends BaseDomain {
    private Integer resourceId;
    private String resourceName;
    private String resourceUrl;

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }
}
